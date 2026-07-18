package priv.mfurmane.szlachtownica.worldgen;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Podgląd temperatury: rampa zimno→ciepło (biel/błękit → zieleń → czerwień).
 * Północ do góry. Czysta Java.
 */
public class TemperatureRaster {

    // Punkty rampy: {°C, R, G, B}
    private static final double[][] RAMP = {
            {-10, 245, 245, 255},
            {0, 70, 100, 205},
            {8, 70, 175, 175},
            {14, 95, 180, 95},
            {20, 232, 200, 80},
            {26, 214, 74, 52},
            {34, 150, 30, 40},
    };

    public static void writePng(WorldGenContext ctx, File out) {
        float[][] t = ctx.temperature;
        if (t == null) {
            return;
        }
        int w = ctx.width, h = ctx.height;
        BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        for (int j = 0; j < h; j++) {
            for (int i = 0; i < w; i++) {
                img.setRGB(i, h - 1 - j, color(t[j][i])); // north-up
            }
        }
        try {
            ImageIO.write(img, "png", out);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static int color(double c) {
        if (c <= RAMP[0][0]) {
            return rgb(RAMP[0]);
        }
        if (c >= RAMP[RAMP.length - 1][0]) {
            return rgb(RAMP[RAMP.length - 1]);
        }
        for (int k = 0; k < RAMP.length - 1; k++) {
            if (c >= RAMP[k][0] && c < RAMP[k + 1][0]) {
                double f = (c - RAMP[k][0]) / (RAMP[k + 1][0] - RAMP[k][0]);
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
