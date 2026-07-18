package priv.mfurmane.szlachtownica.worldgen;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/**
 * Autorski highmap wczytany jako pole jasności 0..1 (makro-relief hybrydy).
 * Duże PNG (rzędu 60 Mpix) wczytuje z subsamplingiem — nie dekoduje pełnej
 * bitmapy do pamięci, tylko od razu do docelowej, mniejszej rozdzielczości.
 *
 * Próbkowanie bilinearne po znormalizowanych współrzędnych {@code (u,v)} w [0,1],
 * gdzie {@code v=0} to góra obrazu (północ). Poza [0,1] zwraca -1.
 *
 * Czysta Java (javax.imageio) — bez JTS, testowalne offline.
 */
public class HighmapBias {

    private final float[][] gray; // [h][w], 0..1
    private final int w;
    private final int h;

    private HighmapBias(float[][] gray) {
        this.gray = gray;
        this.h = gray.length;
        this.w = gray[0].length;
    }

    /** Jasność 0..1 w punkcie (u,v); v=0 = góra. Poza obrazem: -1. */
    public double sample(double u, double v) {
        if (u < 0 || u > 1 || v < 0 || v > 1) {
            return -1;
        }
        double fx = u * (w - 1);
        double fy = v * (h - 1);
        int x0 = (int) Math.floor(fx), y0 = (int) Math.floor(fy);
        int x1 = Math.min(w - 1, x0 + 1), y1 = Math.min(h - 1, y0 + 1);
        double tx = fx - x0, ty = fy - y0;
        double top = gray[y0][x0] * (1 - tx) + gray[y0][x1] * tx;
        double bot = gray[y1][x0] * (1 - tx) + gray[y1][x1] * tx;
        return top * (1 - ty) + bot * ty;
    }

    public int width() {
        return w;
    }

    public int height() {
        return h;
    }

    /**
     * Wczytuje highmap z pliku, redukując dłuższy bok do co najwyżej {@code maxDim}
     * przez subsampling czytnika (oszczędza pamięć na wielkich PNG).
     */
    public static HighmapBias load(File file, int maxDim) throws IOException {
        try (ImageInputStream iis = ImageIO.createImageInputStream(file)) {
            Iterator<ImageReader> readers = ImageIO.getImageReaders(iis);
            if (!readers.hasNext()) {
                throw new IOException("Brak czytnika obrazu dla " + file);
            }
            ImageReader reader = readers.next();
            try {
                reader.setInput(iis, true, true);
                int srcW = reader.getWidth(0);
                int srcH = reader.getHeight(0);
                int sub = Math.max(1, (int) Math.ceil((double) Math.max(srcW, srcH) / Math.max(1, maxDim)));
                ImageReadParam param = reader.getDefaultReadParam();
                param.setSourceSubsampling(sub, sub, 0, 0);
                BufferedImage img = reader.read(0, param);
                return fromImage(img);
            } finally {
                reader.dispose();
            }
        }
    }

    private static HighmapBias fromImage(BufferedImage img) {
        int w = img.getWidth(), h = img.getHeight();
        float[][] gray = new float[h][w];
        Raster raster = img.getRaster();
        int bands = raster.getNumBands();
        int bits = img.getSampleModel().getSampleSize(0); // 8 lub 16
        double max = (1L << bits) - 1;                     // 255 albo 65535
        int[] px = new int[bands];
        for (int j = 0; j < h; j++) {
            for (int i = 0; i < w; i++) {
                raster.getPixel(i, j, px);
                double lum = bands >= 3
                        ? 0.299 * px[0] + 0.587 * px[1] + 0.114 * px[2] // luminancja
                        : px[0];
                gray[j][i] = (float) Math.max(0, Math.min(1, lum / max));
            }
        }
        return new HighmapBias(gray);
    }
}
