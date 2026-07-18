package priv.mfurmane.szlachtownica.worldgen;

import java.util.ArrayList;
import java.util.List;

/**
 * Etap wypalania (stream burning): wcina predefiniowane rzeki i jeziora w DEM
 * PO erozji, a PRZED hydrologią. Dzięki temu autorskie cieki (Wisła/Pontar tego
 * świata) stają się głównymi nurtami — akumulacja spływu podąża nimi, a rzeki
 * proceduralne degradują się do ich dopływów. Delta powstaje tam, gdzie
 * rozgałęziony nurt dochodzi do nadmorskiej niziny.
 *
 * Rzeka: łamana rasteryzowana do łańcucha pikseli, koryto obniżane monotonicznie
 * w stronę niższego końca (ujścia) — bieżące minimum od źródła gwarantuje spływ
 * do morza niezależnie od garbów terenu. Jezioro: wnętrze poligonu spłaszczone
 * poniżej obrzeża, więc hydrologia rozpozna nieckę jako jezioro.
 *
 * Czysta Java (raster) — testowalne offline.
 */
public class BurnStage implements WorldGenStage {

    @Override
    public String id() {
        return "burn";
    }

    @Override
    public void run(WorldGenContext ctx) {
        if (ctx.elevation == null) {
            throw new IllegalStateException("BurnStage wymaga ctx.elevation");
        }
        WorldGenConfig c = ctx.config;
        if (c.burnRivers && ctx.riverPaths != null) {
            ctx.burnedRiver = new boolean[ctx.height][ctx.width];
            for (double[][] path : ctx.riverPaths) {
                burnRiver(ctx, path, c.riverBurnDepth, c.riverBurnRadius);
            }
        }
        if (c.burnLakes && ctx.lakePolys != null) {
            ctx.burnedLake = new boolean[ctx.height][ctx.width];
            for (double[][] ring : ctx.lakePolys) {
                burnLake(ctx, ring, c.lakeBurnDepth);
            }
        }
    }

    // --- Rzeki ---

    private void burnRiver(WorldGenContext ctx, double[][] path, double depth, int radius) {
        List<int[]> chain = rasterizeChain(ctx, path);
        if (chain.size() < 2) {
            return;
        }
        float[][] elev = ctx.elevation;
        // Wcięcie o stałej głębokości względem terenu w osi cieku (snapshot bazy
        // sprzed żłobienia, żeby nakładające się komórki nie pogłębiały się w
        // nieskończoność). Koryto podąża za terenem — bez kanionów; kierunek
        // spływu wyznacza priority-flood na wypalonym korytarzu.
        double[] base = new double[chain.size()];
        for (int k = 0; k < chain.size(); k++) {
            int[] p = chain.get(k);
            base[k] = elev[p[1]][p[0]];
        }
        for (int k = 0; k < chain.size(); k++) {
            int[] p = chain.get(k);
            int i = p[0], j = p[1];
            ctx.burnedRiver[j][i] = true; // oś cieku → zawsze rzeka (odporna na suszę)
            float target = (float) (base[k] - depth);
            for (int dj = -radius; dj <= radius; dj++) {
                for (int di = -radius; di <= radius; di++) {
                    int ni = i + di, nj = j + dj;
                    if (ni < 0 || nj < 0 || ni >= ctx.width || nj >= ctx.height) {
                        continue;
                    }
                    if (elev[nj][ni] > target) {
                        elev[nj][ni] = target;
                    }
                }
            }
        }
    }

    /** Łamana świata → uporządkowany łańcuch pikseli (bez powtórzeń sąsiednich). */
    private List<int[]> rasterizeChain(WorldGenContext ctx, double[][] path) {
        List<int[]> out = new ArrayList<>();
        int lastI = Integer.MIN_VALUE, lastJ = Integer.MIN_VALUE;
        for (int s = 0; s < path.length - 1; s++) {
            double x0 = path[s][0], y0 = path[s][1];
            double x1 = path[s + 1][0], y1 = path[s + 1][1];
            double dx = x1 - x0, dy = y1 - y0;
            double lenM = Math.hypot(dx, dy);
            int steps = Math.max(1, (int) Math.ceil(lenM / (ctx.cellSize * 0.5)));
            for (int k = 0; k <= steps; k++) {
                double t = (double) k / steps;
                int i = (int) Math.round((x0 + t * dx - ctx.minX) / ctx.cellSize - 0.5);
                int j = (int) Math.round((y0 + t * dy - ctx.minY) / ctx.cellSize - 0.5);
                if (i < 0 || j < 0 || i >= ctx.width || j >= ctx.height) {
                    continue;
                }
                if (i != lastI || j != lastJ) {
                    out.add(new int[]{i, j});
                    lastI = i;
                    lastJ = j;
                }
            }
        }
        return out;
    }

    // --- Jeziora ---

    private void burnLake(WorldGenContext ctx, double[][] ring, double depth) {
        if (ring.length < 3) {
            return;
        }
        float[][] elev = ctx.elevation;
        // Pierścień w pikselach + obwiednia + minimum obrzeża.
        double[][] px = new double[ring.length][2];
        int minI = ctx.width, maxI = -1, minJ = ctx.height, maxJ = -1;
        double rim = Double.POSITIVE_INFINITY;
        for (int k = 0; k < ring.length; k++) {
            double pi = (ring[k][0] - ctx.minX) / ctx.cellSize - 0.5;
            double pj = (ring[k][1] - ctx.minY) / ctx.cellSize - 0.5;
            px[k][0] = pi;
            px[k][1] = pj;
            int ii = (int) Math.round(pi), jj = (int) Math.round(pj);
            minI = Math.min(minI, ii);
            maxI = Math.max(maxI, ii);
            minJ = Math.min(minJ, jj);
            maxJ = Math.max(maxJ, jj);
            if (ii >= 0 && jj >= 0 && ii < ctx.width && jj < ctx.height) {
                rim = Math.min(rim, elev[jj][ii]);
            }
        }
        if (rim == Double.POSITIVE_INFINITY) {
            return;
        }
        float basin = (float) (rim - depth);
        minI = Math.max(0, minI);
        minJ = Math.max(0, minJ);
        maxI = Math.min(ctx.width - 1, maxI);
        maxJ = Math.min(ctx.height - 1, maxJ);
        for (int j = minJ; j <= maxJ; j++) {
            for (int i = minI; i <= maxI; i++) {
                if (pointInRing(i, j, px)) {
                    ctx.burnedLake[j][i] = true; // wnętrze → zawsze jezioro (odporne na suszę)
                    if (elev[j][i] > basin) {
                        elev[j][i] = basin;
                    }
                }
            }
        }
    }

    /** Test punktu w pierścieniu (parzystość przecięć), współrzędne pikselowe. */
    private boolean pointInRing(double x, double y, double[][] ring) {
        boolean in = false;
        for (int a = 0, b = ring.length - 1; a < ring.length; b = a++) {
            double xa = ring[a][0], ya = ring[a][1];
            double xb = ring[b][0], yb = ring[b][1];
            if (((ya > y) != (yb > y))
                    && (x < (xb - xa) * (y - ya) / (yb - ya) + xa)) {
                in = !in;
            }
        }
        return in;
    }
}
