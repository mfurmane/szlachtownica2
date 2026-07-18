package priv.mfurmane.szlachtownica.worldgen;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Podgląd wilgotności (0..1): sucho (piaskowy brąz) → mokro (ciemna zieleń/błękit).
 * Morze na szaro-niebiesko. Północ do góry. Czysta Java.
 */
public class HumidityRaster {

    // Rampa: {poziom, R, G, B} — od suchego do mokrego.
    private static final double[][] RAMP = {
            {0.0, 200, 175, 120},
            {0.25, 205, 200, 120},
            {0.5, 150, 185, 110},
            {0.75, 80, 160, 110},
            {1.0, 40, 110, 130},
    };

    public static void writePng(WorldGenContext ctx, File out) {
        float[][] hum = ctx.humidityGrid;
        if (hum == null) {
            return;
        }
        int w = ctx.width, h = ctx.height;
        float[][] elev = ctx.elevation;
        double seaLevel = ctx.config.seaLevel;
        BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        for (int j = 0; j < h; j++) {
            for (int i = 0; i < w; i++) {
                int col;
                if (elev != null && elev[j][i] < seaLevel) {
                    col = (120 << 16) | (150 << 8) | 175; // morze
                } else {
                    col = color(hum[j][i]);
                }
                img.setRGB(i, h - 1 - j, col); // north-up
            }
        }
        try {
            ImageIO.write(img, "png", out);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static int color(double v) {
        if (v <= RAMP[0][0]) return rgb(RAMP[0]);
        if (v >= RAMP[RAMP.length - 1][0]) return rgb(RAMP[RAMP.length - 1]);
        for (int k = 0; k < RAMP.length - 1; k++) {
            if (v >= RAMP[k][0] && v < RAMP[k + 1][0]) {
                double f = (v - RAMP[k][0]) / (RAMP[k + 1][0] - RAMP[k][0]);
                int r = (int) (RAMP[k][1] + f * (RAMP[k + 1][1] - RAMP[k][1]));
                int g = (int) (RAMP[k][2] + f * (RAMP[k + 1][2] - RAMP[k][2]));
                int b = (int) (RAMP[k][3] + f * (RAMP[k + 1][3] - RAMP[k][3]));
                return (r << 16) | (g << 8) | b;
            }
        }
        return rgb(RAMP[RAMP.length - 1]);
    }

    private static int rgb(double[] c) {
        return ((int) c[1] << 16) | ((int) c[2] << 8) | (int) c[3];
    }
}
