package priv.mfurmane.szlachtownica.worldgen;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Podgląd hydrologii: przygaszony teren z cieniowaniem + jeziora + rzeki, których
 * grubość rośnie z akumulacją spływu. Północ do góry. Czysta Java.
 */
public class HydrologyRaster {

    private static final int[] RIVER = {30, 90, 170};
    private static final int[] LAKE = {60, 120, 190};

    public static void writePng(WorldGenContext ctx, File out) {
        float[][] e = ctx.elevation;
        if (e == null || ctx.flowAccum == null) {
            return;
        }
        int w = ctx.width, h = ctx.height;
        double maxLand = 1;
        for (float[] row : e) {
            for (float v : row) {
                if (v > maxLand) maxLand = v;
            }
        }
        double thr = ctx.config.riverDrainageKm2 * 1_000_000.0 / (ctx.cellSize * ctx.cellSize);

        int[][] pix = new int[h][w]; // kolor w orientacji świata (north-up przy zapisie)
        double vex = 3.0;
        double lx = -0.7, ly = -0.7, lz = 0.6;
        double ll = Math.sqrt(lx * lx + ly * ly + lz * lz);
        lx /= ll; ly /= ll; lz /= ll;

        // 1) baza: przygaszony teren + hillshade
        for (int j = 0; j < h; j++) {
            for (int i = 0; i < w; i++) {
                double v = e[j][i];
                int[] col;
                if (v < ctx.config.seaLevel) {
                    col = new int[]{150, 180, 205}; // morze jasne, żeby rzeki się wyróżniały
                } else {
                    double t = v / maxLand;
                    int g = (int) (170 - 60 * t);
                    col = new int[]{(int) (150 + 40 * t), g + 20, (int) (120 + 10 * t)};
                    int il = Math.max(0, i - 1), ir = Math.min(w - 1, i + 1);
                    int jt = Math.max(0, j - 1), jb = Math.min(h - 1, j + 1);
                    double dzdx = (e[j][ir] - e[j][il]) / (2 * ctx.cellSize) * vex;
                    double dzdy = (e[jb][i] - e[jt][i]) / (2 * ctx.cellSize) * vex;
                    double nx = -dzdx, ny = -dzdy, nz = 1;
                    double nl = Math.sqrt(nx * nx + ny * ny + nz * nz);
                    double shade = Math.max(0, (nx * lx + ny * ly + nz * lz) / nl);
                    double f = 0.65 + 0.35 * shade;
                    col = new int[]{c(col[0] * f), c(col[1] * f), c(col[2] * f)};
                }
                pix[j][i] = rgb(col);
            }
        }
        // 2) jeziora
        for (int j = 0; j < h; j++) {
            for (int i = 0; i < w; i++) {
                if (ctx.lake != null && ctx.lake[j][i]) {
                    pix[j][i] = rgb(LAKE);
                }
            }
        }
        // 3) rzeki — grubość wg akumulacji (log), okrągłym pędzlem.
        //    Promień rośnie łagodnie i ma twardy sufit, więc rzeki nie „eskalują"
        //    grubościowo skokami; okrągły pędzel usuwa kanciastość kwadratowego stempla.
        for (int j = 0; j < h; j++) {
            for (int i = 0; i < w; i++) {
                if (ctx.river == null || !ctx.river[j][i]) {
                    continue;
                }
                double relief = Math.log10(Math.max(1, ctx.flowAccum[j][i] / thr));
                double rad = Math.min(2.2, 0.4 + 0.55 * relief);
                int ri = (int) Math.ceil(rad);
                double rr = rad * rad;
                for (int dj = -ri; dj <= ri; dj++) {
                    for (int di = -ri; di <= ri; di++) {
                        if (di * di + dj * dj > rr) {
                            continue; // okrągły pędzel
                        }
                        int ni = i + di, nj = j + dj;
                        if (ni >= 0 && nj >= 0 && ni < w && nj < h) {
                            pix[nj][ni] = rgb(RIVER);
                        }
                    }
                }
            }
        }

        BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        for (int j = 0; j < h; j++) {
            for (int i = 0; i < w; i++) {
                img.setRGB(i, h - 1 - j, pix[j][i]); // north-up
            }
        }
        try {
            ImageIO.write(img, "png", out);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static int rgb(int[] c) {
        return (c(c[0]) << 16) | (c(c[1]) << 8) | c(c[2]);
    }

    private static int c(double x) {
        return (int) Math.max(0, Math.min(255, x));
    }
}
