package priv.mfurmane.szlachtownica.worldgen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Etap 3b: erozja hydrauliczna (symulacja kropli). Wiele kropli spływa zgodnie ze
 * spadkiem, zbierając i osadzając materiał — wcina doliny i osadza mady w dołach,
 * dzięki czemu późniejsza hydrologia prowadzi rzeki dendrytycznie.
 *
 * Modyfikuje {@code ctx.elevation} w miejscu. Tempo erozji jest modulowane
 * twardością skały macierzystej ({@code ctx.bedrock}) — granit stawia opór,
 * iłowiec ustępuje. Czysta Java (raster) → testowalne offline.
 *
 * Wariant klasycznej erozji kropelkowej (Musgrave / Beyer / Lague).
 */
public class ErosionStage implements WorldGenStage {

    @Override
    public String id() {
        return "erosion";
    }

    @Override
    public void run(WorldGenContext ctx) {
        if (ctx.elevation == null) {
            throw new IllegalStateException("ErosionStage wymaga ctx.elevation");
        }
        int w = ctx.width, h = ctx.height;
        float[][] elev = ctx.elevation;
        WorldGenConfig c = ctx.config;
        Random rnd = new Random(c.seed * 1000003L + 3);

        // Pędzel erozji: przesunięcia + wagi (rozmycie liniowe do promienia).
        List<int[]> brushOff = new ArrayList<>();
        List<Double> brushW = new ArrayList<>();
        double wSum = 0;
        int radius = Math.max(1, c.erosionRadius);
        for (int dy = -radius; dy <= radius; dy++) {
            for (int dx = -radius; dx <= radius; dx++) {
                double d = Math.sqrt(dx * dx + dy * dy);
                if (d <= radius) {
                    double weight = 1 - d / radius;
                    brushOff.add(new int[]{dx, dy});
                    brushW.add(weight);
                    wSum += weight;
                }
            }
        }
        for (int k = 0; k < brushW.size(); k++) {
            brushW.set(k, brushW.get(k) / wSum);
        }

        long droplets = (long) (w * (long) h * c.erosionDropletDensity);
        for (long d = 0; d < droplets; d++) {
            simulateDroplet(ctx, elev, w, h, c, rnd, brushOff, brushW);
        }
    }

    private void simulateDroplet(WorldGenContext ctx, float[][] elev, int w, int h,
                                 WorldGenConfig c, Random rnd, List<int[]> brushOff, List<Double> brushW) {
        double posX = rnd.nextDouble() * (w - 1);
        double posY = rnd.nextDouble() * (h - 1);
        double dirX = 0, dirY = 0;
        double speed = 1, water = 1, sediment = 0;

        for (int life = 0; life < c.erosionMaxLifetime; life++) {
            int nodeX = (int) posX, nodeY = (int) posY;
            double cellX = posX - nodeX, cellY = posY - nodeY;

            double[] hg = heightAndGradient(elev, nodeX, nodeY, cellX, cellY);
            double oldHeight = hg[0], gx = hg[1], gy = hg[2];

            // aktualizacja kierunku (bezwładność + spadek)
            dirX = dirX * c.erosionInertia - gx * (1 - c.erosionInertia);
            dirY = dirY * c.erosionInertia - gy * (1 - c.erosionInertia);
            double len = Math.sqrt(dirX * dirX + dirY * dirY);
            if (len != 0) {
                dirX /= len;
                dirY /= len;
            }
            posX += dirX;
            posY += dirY;

            // koniec: brak ruchu / poza mapą (margines na interpolację) / do morza
            if ((dirX == 0 && dirY == 0) || posX < 0 || posX >= w - 1 || posY < 0 || posY >= h - 1) {
                break;
            }
            int nnx = (int) posX, nny = (int) posY;
            double newHeight = heightAndGradient(elev, nnx, nny, posX - nnx, posY - nny)[0];
            if (newHeight < c.seaLevel) {
                break; // wpłynęła do morza
            }
            double heightDiff = newHeight - oldHeight;

            double capacity = Math.max(-heightDiff, c.erosionMinSlope) * speed * water * c.erosionCapacity;

            if (sediment > capacity || heightDiff > 0) {
                // depozycja (przy podejściu pod górę wypełnij co najwyżej różnicę)
                double amount = heightDiff > 0
                        ? Math.min(heightDiff, sediment)
                        : (sediment - capacity) * c.erosionDeposit;
                sediment -= amount;
                deposit(elev, nodeX, nodeY, cellX, cellY, amount);
            } else {
                // erozja — modulowana twardością skały pod kroplą
                double hardness = hardnessAt(ctx, nodeX, nodeY);
                double factor = 1 - hardness * c.erosionHardnessInfluence;
                double amount = Math.min((capacity - sediment) * c.erosionErode, -heightDiff) * factor;
                double eroded = erodeBrush(elev, nodeX, nodeY, amount, brushOff, brushW, w, h, c.seaLevel);
                sediment += eroded;
            }

            speed = Math.sqrt(Math.max(0, speed * speed + heightDiff * c.erosionGravity));
            water *= (1 - c.erosionEvaporation);
            if (water < 1e-3) {
                break;
            }
        }
    }

    // Wysokość i gradient interpolowane biliniowo w punkcie (nodeX+cellX, nodeY+cellY).
    private static double[] heightAndGradient(float[][] elev, int nodeX, int nodeY, double x, double y) {
        double h00 = elev[nodeY][nodeX];
        double h10 = elev[nodeY][nodeX + 1];
        double h01 = elev[nodeY + 1][nodeX];
        double h11 = elev[nodeY + 1][nodeX + 1];
        double gx = (h10 - h00) * (1 - y) + (h11 - h01) * y;
        double gy = (h01 - h00) * (1 - x) + (h11 - h10) * x;
        double height = h00 * (1 - x) * (1 - y) + h10 * x * (1 - y) + h01 * (1 - x) * y + h11 * x * y;
        return new double[]{height, gx, gy};
    }

    private static void deposit(float[][] elev, int nodeX, int nodeY, double x, double y, double amount) {
        elev[nodeY][nodeX] += (float) (amount * (1 - x) * (1 - y));
        elev[nodeY][nodeX + 1] += (float) (amount * x * (1 - y));
        elev[nodeY + 1][nodeX] += (float) (amount * (1 - x) * y);
        elev[nodeY + 1][nodeX + 1] += (float) (amount * x * y);
    }

    private static double erodeBrush(float[][] elev, int nodeX, int nodeY, double amount,
                                     List<int[]> brushOff, List<Double> brushW, int w, int h, double seaLevel) {
        double total = 0;
        for (int k = 0; k < brushOff.size(); k++) {
            int[] off = brushOff.get(k);
            int bx = nodeX + off[0], by = nodeY + off[1];
            if (bx < 0 || by < 0 || bx >= w || by >= h) {
                continue;
            }
            double take = amount * brushW.get(k);
            // nie wcinaj poniżej poziomu morza (utrzymuje linię brzegową)
            double room = elev[by][bx] - seaLevel;
            if (room <= 0) {
                continue;
            }
            take = Math.min(take, room);
            elev[by][bx] -= (float) take;
            total += take;
        }
        return total;
    }

    private static double hardnessAt(WorldGenContext ctx, int nodeX, int nodeY) {
        if (ctx.bedrock == null) {
            return 0;
        }
        BedrockType b = ctx.bedrock[nodeY][nodeX];
        return b == null ? 0 : b.getHardness();
    }
}
