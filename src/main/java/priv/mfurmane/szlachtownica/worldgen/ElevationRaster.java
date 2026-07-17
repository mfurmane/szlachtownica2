package priv.mfurmane.szlachtownica.worldgen;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Podgląd rastra wysokości do PNG: rampa hipsometryczna (morze → nizina → góry →
 * śnieg) + cieniowanie zboczy (hillshade). Północ do góry. Czysta Java.
 */
public class ElevationRaster {

    public static void writePng(WorldGenContext ctx, File out) {
        float[][] e = ctx.elevation;
        if (e == null) {
            return;
        }
        int w = ctx.width, h = ctx.height;
        double maxLand = 1, maxDepth = 1;
        for (float[] row : e) {
            for (float v : row) {
                if (v > maxLand) maxLand = v;
                if (-v > maxDepth) maxDepth = -v;
            }
        }
        BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        double vex = 3.0; // przewyższenie dla czytelności cieni
        double lx = -0.7, ly = -0.7, lz = 0.6;
        double ll = Math.sqrt(lx * lx + ly * ly + lz * lz);
        lx /= ll; ly /= ll; lz /= ll;
        for (int j = 0; j < h; j++) {
            for (int i = 0; i < w; i++) {
                double v = e[j][i];
                int[] col = v < 0 ? sea(v, maxDepth) : land(v / maxLand);
                if (v >= 0) {
                    int il = Math.max(0, i - 1), ir = Math.min(w - 1, i + 1);
                    int jt = Math.max(0, j - 1), jb = Math.min(h - 1, j + 1);
                    double dzdx = (e[j][ir] - e[j][il]) / (2 * ctx.cellSize) * vex;
                    double dzdy = (e[jb][i] - e[jt][i]) / (2 * ctx.cellSize) * vex;
                    double nx = -dzdx, ny = -dzdy, nz = 1;
                    double nl = Math.sqrt(nx * nx + ny * ny + nz * nz);
                    double shade = Math.max(0, (nx * lx + ny * ly + nz * lz) / nl);
                    double f = 0.55 + 0.45 * shade;
                    col = new int[]{c(col[0] * f), c(col[1] * f), c(col[2] * f)};
                }
                int row = h - 1 - j; // północ do góry
                img.setRGB(i, row, (col[0] << 16) | (col[1] << 8) | col[2]);
            }
        }
        try {
            ImageIO.write(img, "png", out);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static int[] sea(double v, double maxDepth) {
        double t = Math.max(0, Math.min(1, -v / maxDepth));
        return new int[]{(int) (60 - 30 * t), (int) (120 - 40 * t), (int) (180 - 40 * t)};
    }

    private static int[] land(double t) {
        double[][] s = {{0, 80, 130, 70}, {0.12, 120, 165, 90}, {0.35, 190, 180, 120},
                {0.6, 150, 110, 70}, {0.85, 190, 180, 175}, {1, 255, 255, 255}};
        for (int k = 0; k < s.length - 1; k++) {
            if (t <= s[k + 1][0]) {
                double u = (t - s[k][0]) / (s[k + 1][0] - s[k][0]);
                return new int[]{(int) lerp(s[k][1], s[k + 1][1], u),
                        (int) lerp(s[k][2], s[k + 1][2], u), (int) lerp(s[k][3], s[k + 1][3], u)};
            }
        }
        return new int[]{255, 255, 255};
    }

    private static double lerp(double a, double b, double t) {
        return a + t * (b - a);
    }

    private static int c(double x) {
        return (int) Math.max(0, Math.min(255, x));
    }
}
