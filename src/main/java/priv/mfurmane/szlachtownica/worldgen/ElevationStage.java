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

        // 1) maska ląd/morze
        boolean[][] land = new boolean[h][w];
        for (int j = 0; j < h; j++) {
            for (int i = 0; i < w; i++) {
                land[j][i] = ctx.landMask == null || ctx.landMask.isLand(ctx.worldX(i), ctx.worldY(j));
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
                    double continental = fbm(i / noiseScale, j / noiseScale, c.elevationOctaves, c.seed); // [0,1]
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
        double sum = 0;
        for (WorldGenContext.Peak p : ctx.peaks) {
            if (p.radius() <= 0) {
                continue;
            }
            double dx = wx - p.x(), dy = wy - p.y();
            double d2 = dx * dx + dy * dy;
            double r2 = p.radius() * p.radius();
            sum += p.height() * Math.exp(-d2 / r2); // gaussowski garb
        }
        return sum;
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

    // --- fBm (value noise) ---

    private static double fbm(double x, double y, int octaves, long seed) {
        double sum = 0, amp = 1, total = 0, freq = 1;
        for (int o = 0; o < octaves; o++) {
            sum += amp * valueNoise2D(x * freq, y * freq, seed + o * 1013L);
            total += amp;
            amp *= 0.5;
            freq *= 2.0;
        }
        return sum / total; // [0,1]
    }

    private static double valueNoise2D(double x, double y, long seed) {
        int x0 = (int) Math.floor(x), y0 = (int) Math.floor(y);
        double tx = fade(x - x0), ty = fade(y - y0);
        double v00 = hash(x0, y0, seed), v10 = hash(x0 + 1, y0, seed);
        double v01 = hash(x0, y0 + 1, seed), v11 = hash(x0 + 1, y0 + 1, seed);
        return lerp(lerp(v00, v10, tx), lerp(v01, v11, tx), ty);
    }

    private static double hash(int x, int y, long seed) {
        long h = x * 0x1f1f1f1fL ^ (y * 0x8da6b343L) ^ (seed * 0xca5a5a5aL);
        h ^= (h >>> 13);
        h *= 0x9E3779B97F4A7C15L;
        h ^= (h >>> 7);
        return (h & 0xffffffffL) / (double) 0xffffffffL; // [0,1]
    }

    private static double fade(double t) {
        return t * t * (3 - 2 * t);
    }

    private static double lerp(double a, double b, double t) {
        return a + t * (b - a);
    }

    private static double smoothstep(double edge0, double edge1, double x) {
        double t = Math.max(0, Math.min(1, (x - edge0) / (edge1 - edge0)));
        return t * t * (3 - 2 * t);
    }
}
