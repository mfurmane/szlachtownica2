package priv.mfurmane.szlachtownica.worldgen;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Podgryw mapy geologicznej do PNG: kolor wg {@link BedrockType} + cieniowanie
 * zboczy z elewacji (dla czytelności). Morze na niebiesko. Północ do góry.
 */
public class BedrockRaster {

    public static void writePng(WorldGenContext ctx, File out) {
        BedrockType[][] rock = ctx.bedrock;
        float[][] e = ctx.elevation;
        if (rock == null) {
            return;
        }
        int w = ctx.width, h = ctx.height;
        BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        double vex = 3.0;
        double lx = -0.7, ly = -0.7, lz = 0.6;
        double ll = Math.sqrt(lx * lx + ly * ly + lz * lz);
        lx /= ll; ly /= ll; lz /= ll;
        for (int j = 0; j < h; j++) {
            for (int i = 0; i < w; i++) {
                int[] col;
                if (rock[j][i] == null) {
                    col = new int[]{60, 110, 170}; // morze
                } else {
                    col = colorFor(rock[j][i]);
                    if (e != null) {
                        int il = Math.max(0, i - 1), ir = Math.min(w - 1, i + 1);
                        int jt = Math.max(0, j - 1), jb = Math.min(h - 1, j + 1);
                        double dzdx = (e[j][ir] - e[j][il]) / (2 * ctx.cellSize) * vex;
                        double dzdy = (e[jb][i] - e[jt][i]) / (2 * ctx.cellSize) * vex;
                        double nx = -dzdx, ny = -dzdy, nz = 1;
                        double nl = Math.sqrt(nx * nx + ny * ny + nz * nz);
                        double shade = Math.max(0, (nx * lx + ny * ly + nz * lz) / nl);
                        double f = 0.6 + 0.4 * shade;
                        col = new int[]{c(col[0] * f), c(col[1] * f), c(col[2] * f)};
                    }
                }
                img.setRGB(i, h - 1 - j, (col[0] << 16) | (col[1] << 8) | col[2]);
            }
        }
        try {
            ImageIO.write(img, "png", out);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static int[] colorFor(BedrockType b) {
        return switch (b) {
            case GRANITE -> new int[]{210, 180, 185};
            case BASALT -> new int[]{80, 76, 92};
            case SCHIST -> new int[]{150, 135, 175};
            case LIMESTONE -> new int[]{226, 216, 172};
            case SANDSTONE -> new int[]{216, 186, 122};
            case SHALE -> new int[]{120, 142, 120};
        };
    }

    private static int c(double x) {
        return (int) Math.max(0, Math.min(255, x));
    }
}
