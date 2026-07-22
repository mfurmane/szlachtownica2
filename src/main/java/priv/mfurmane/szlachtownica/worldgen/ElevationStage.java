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

        // 2b) kanoniczna kotwica wysokości prowincji: raster próbek + wygładzenie na
        //     granicach (żeby między prowincjami o różnej średniej nie było klifu).
        float[][] anchor = buildBlurredAnchor(ctx, land, w, h);

        // 3) wypełnienie
        double noiseScale = c.continentalScaleMeters / ctx.cellSize; // komórka szumu w px
        double coastPx = Math.max(1.0, c.coastWidthMeters / ctx.cellSize);
        for (int j = 0; j < h; j++) {
            for (int i = 0; i < w; i++) {
                if (land[j][i]) {
                    double continental = Noise.fbm(i / noiseScale, j / noiseScale, c.elevationOctaves, c.seed); // [0,1]
                    double coastRamp = smoothstep(0, coastPx, distToSea[j][i]);                            // 0 brzeg → 1 w głąb
                    double landHeight;
                    double hm = ctx.heightBias != null ? ctx.heightBias.at(ctx.worldX(i), ctx.worldY(j)) : -1;
                    double anc = anchor != null ? anchor[j][i] : -1;
                    if (anc >= 0) {
                        // Kotwica: poziom terenu przypięty do kanonicznej średniej wysokości
                        // prowincji (arkusz Geografia), a highmap/fBm dają tylko relief WOKÓŁ
                        // tej średniej. Dzięki temu Orvanor jest wysoki, a Alstederia niska
                        // niezależnie od tego, jak niedbale narysowany jest highmap.
                        double relief = ((hm >= 0 ? hm : continental) - 0.5) * 2 * c.elevationAnchorRelief;
                        double detail = (continental - 0.5) * 2 * c.highmapDetailMeters;
                        landHeight = anc + relief + detail;
                    } else if (hm >= 0) {
                        // Hybryda bez kotwicy: makro z highmapa (nizina→szczyt) zmieszane z
                        // proceduralnym fBm, plus mały proceduralny detal, żeby erozja miała
                        // fakturę nawet tam, gdzie highmap jest niedbały/płaski.
                        double hmH = c.highmapLowland + hm * (c.highmapHeightScale - c.highmapLowland);
                        double fbmH = continental * c.baseHeight;
                        double detail = (continental - 0.5) * 2 * c.highmapDetailMeters;
                        landHeight = c.highmapWeight * hmH + (1 - c.highmapWeight) * fbmH + detail;
                    } else {
                        landHeight = continental * c.baseHeight;
                    }
                    double uplift = upliftAt(ctx, i, j);
                    elev[j][i] = (float) (c.seaLevel + landHeight * coastRamp + uplift);
                } else {
                    double depthRamp = smoothstep(0, coastPx, distToLand[j][i]);
                    elev[j][i] = (float) (c.seaLevel - depthRamp * c.seaDepth);
                }
            }
        }
        ctx.elevation = elev;
    }

    /**
     * Buduje raster kanonicznej kotwicy wysokości i wygładza go na granicach prowincji.
     * Zwraca {@code null}, gdy nie ma {@code ctx.elevationAnchor}. Komórki bez prowincji
     * (morze/za granicą) dostają wartość ujemną (brak kotwicy → hybryda/fBm w pętli).
     * Wygładzanie: box-blur świadomy „ważnych" komórek — uśrednia tylko po komórkach
     * z prowincją, więc morze nie zaniża nadmorskich średnich, a granice łagodnieją.
     */
    private static float[][] buildBlurredAnchor(WorldGenContext ctx, boolean[][] land, int w, int h) {
        if (ctx.elevationAnchor == null) {
            return null;
        }
        WorldGenConfig c = ctx.config;
        float[][] raw = new float[h][w];
        boolean any = false;
        for (int j = 0; j < h; j++) {
            for (int i = 0; i < w; i++) {
                double v = land[j][i] ? ctx.elevationAnchor.at(ctx.worldX(i), ctx.worldY(j)) : -1;
                raw[j][i] = (float) v;
                if (v >= 0) {
                    any = true;
                }
            }
        }
        if (!any) {
            return null;
        }
        int radius = (int) Math.round(c.elevationAnchorBlurMeters / ctx.cellSize);
        if (radius < 1) {
            return raw;
        }
        return boxBlurValid(raw, w, h, radius);
    }

    /**
     * Rozdzielny box-blur ignorujący komórki z wartością ujemną (brak prowincji).
     * Komórka bez prowincji pozostaje ujemna; komórki z prowincją uśredniają się
     * tylko po ważnych sąsiadach (granice prowincji łagodnieją, morze nie wchodzi).
     */
    private static float[][] boxBlurValid(float[][] src, int w, int h, int radius) {
        float[][] tmp = new float[h][w];
        // poziomo
        for (int j = 0; j < h; j++) {
            for (int i = 0; i < w; i++) {
                if (src[j][i] < 0) {
                    tmp[j][i] = -1;
                    continue;
                }
                double sum = 0;
                int cnt = 0;
                int lo = Math.max(0, i - radius), hi = Math.min(w - 1, i + radius);
                for (int k = lo; k <= hi; k++) {
                    if (src[j][k] >= 0) {
                        sum += src[j][k];
                        cnt++;
                    }
                }
                tmp[j][i] = cnt > 0 ? (float) (sum / cnt) : src[j][i];
            }
        }
        // pionowo
        float[][] out = new float[h][w];
        for (int j = 0; j < h; j++) {
            for (int i = 0; i < w; i++) {
                if (tmp[j][i] < 0) {
                    out[j][i] = -1;
                    continue;
                }
                double sum = 0;
                int cnt = 0;
                int lo = Math.max(0, j - radius), hi = Math.min(h - 1, j + radius);
                for (int k = lo; k <= hi; k++) {
                    if (tmp[k][i] >= 0) {
                        sum += tmp[k][i];
                        cnt++;
                    }
                }
                out[j][i] = cnt > 0 ? (float) (sum / cnt) : tmp[j][i];
            }
        }
        return out;
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
