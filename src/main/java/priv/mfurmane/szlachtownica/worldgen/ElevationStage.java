package priv.mfurmane.szlachtownica.worldgen;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Etap 1: wypełnia raster wysokości {@code ctx.elevation}.
 *
 * Model:
 *  - maska ląd/morze z {@code ctx.landMask};
 *  - ląd = kontynentalny szum fBm × wysokość bazowa, wyciszony przy brzegu
 *    (rampa od odległości do morza) + wypiętrzenie od szczytów ({@code ctx.peaks});
 *  - morze = ujemna batymetria rosnąca z odległością od lądu.
 *
 * Całość na siatce rastra — bez JTS, więc testowalne offline. Geometrię (maska,
 * szczyty) dostarcza adapter, który wypełnia kontekst.
 */
public class ElevationStage implements WorldGenStage {

    private static final int SEA = 0;
    private static final int LAND = 1;

    @Override
    public String id() {
        return "elevation";
    }

    @Override
    public void run(WorldGenContext ctx) {
        int w = ctx.width, h = ctx.height;
        WorldGenConfig c = ctx.config;
        float[][] elev = new float[h][w];

        // 1) maska ląd/morze. Morze = rzeczywiste akweny (seaMask); gdy brak seaMask
        //    — fallback: morze to wszystko poza lądem. Punkty poza prowincjami, ale
        //    nie w morzu (lądy za granicą S/W/E), zostają lądem.
        boolean[][] land = new boolean[h][w];
        for (int j = 0; j < h; j++) {
            for (int i = 0; i < w; i++) {
                double wx = ctx.worldX(i), wy = ctx.worldY(j);
                boolean sea = ctx.seaMask != null
                        ? ctx.seaMask.isSea(wx, wy)
                        : !(ctx.landMask == null || ctx.landMask.isLand(wx, wy));
                land[j][i] = !sea;
            }
        }

        // 2) transformaty odległości (w pikselach): do morza i do lądu
        int[][] distToSea = bfsDistance(land, false, w, h);
        int[][] distToLand = bfsDistance(land, true, w, h);

        // 3) wypełnienie
        double noiseScale = c.continentalScaleMeters / ctx.cellSize; // komórka szumu w px
        double coastPx = Math.max(1.0, c.coastWidthMeters / ctx.cellSize);
        for (int j = 0; j < h; j++) {
            for (int i = 0; i < w; i++) {
                if (land[j][i]) {
                    double continental = Noise.fbm(i / noiseScale, j / noiseScale, c.elevationOctaves, c.seed); // [0,1]
                    double coastRamp = smoothstep(0, coastPx, distToSea[j][i]);                            // 0 brzeg → 1 w głąb
                    double base = continental * c.baseHeight * coastRamp;
                    double uplift = upliftAt(ctx, i, j);
                    elev[j][i] = (float) (c.seaLevel + base + uplift);
                } else {
                    double depthRamp = smoothstep(0, coastPx, distToLand[j][i]);
                    elev[j][i] = (float) (c.seaLevel - depthRamp * c.seaDepth);
                }
            }
        }
        ctx.elevation = elev;
    }

    private double upliftAt(WorldGenContext ctx, int i, int j) {
        if (ctx.peaks == null || ctx.peaks.isEmpty()) {
            return 0;
        }
        double wx = ctx.worldX(i), wy = ctx.worldY(j);
        // MAKSIMUM, nie suma — grzbiet złożony z wielu nachodzących próbek wzdłuż
        // linii pasma wznosi się do ~height, a nie do sumy wszystkich garbów.
        double best = 0;
        for (WorldGenContext.Peak p : ctx.peaks) {
            if (p.radius() <= 0) {
                continue;
            }
            double dx = wx - p.x(), dy = wy - p.y();
            double d2 = dx * dx + dy * dy;
            double r2 = p.radius() * p.radius();
            double contrib = p.height() * Math.exp(-d2 / r2); // gaussowski garb
            if (contrib > best) {
                best = contrib;
            }
        }
        return best;
    }

    // Wielo-źródłowe BFS: odległość (w pikselach) do najbliższej komórki o zadanym
    // stanie lądu (target). Komórki bez dostępu do źródła dostają "duża" wartość.
    private static int[][] bfsDistance(boolean[][] land, boolean target, int w, int h) {
        int[][] dist = new int[h][w];
        int far = w + h + 1;
        for (int[] row : dist) {
            java.util.Arrays.fill(row, far);
        }
        Deque<int[]> queue = new ArrayDeque<>();
        for (int j = 0; j < h; j++) {
            for (int i = 0; i < w; i++) {
                if (land[j][i] == target) {
                    dist[j][i] = 0;
                    queue.add(new int[]{i, j});
                }
            }
        }
        int[][] nb = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int ci = cur[0], cj = cur[1];
            for (int[] d : nb) {
                int ni = ci + d[0], nj = cj + d[1];
                if (ni < 0 || nj < 0 || ni >= w || nj >= h) {
                    continue;
                }
                if (dist[nj][ni] > dist[cj][ci] + 1) {
                    dist[nj][ni] = dist[cj][ci] + 1;
                    queue.add(new int[]{ni, nj});
                }
            }
        }
        return dist;
    }

    private static double smoothstep(double edge0, double edge1, double x) {
        double t = Math.max(0, Math.min(1, (x - edge0) / (edge1 - edge0)));
        return t * t * (3 - 2 * t);
    }
}
