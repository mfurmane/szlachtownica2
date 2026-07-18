package priv.mfurmane.szlachtownica.worldgen;

import java.util.PriorityQueue;

/**
 * Etap 3: erozja/hydrologia na rastrze. Algorytm:
 *  1. Priority-flood (Barnes) — wypełnia zagłębienia i nadaje kierunki spływu D8
 *     tak, że każda komórka lądu ma ścieżkę do morza/krawędzi. Wypełnione misy
 *     (filled &gt; teren) to JEZIORA.
 *  2. Akumulacja spływu — w odwrotnej kolejności zdejmowania z kolejki (od
 *     źródeł ku ujściom) sumuje spływ w dół.
 *  3. RZEKI — komórki lądu z akumulacją powyżej progu (poza jeziorami).
 *
 * Wymaga {@code ctx.elevation} (morze = poniżej seaLevel). Wynik: flowAccum,
 * river, lake w kontekście. Czysta Java (raster) → testowalne offline.
 */
public class HydrologyStage implements WorldGenStage {

    private static final int[] DX = {1, -1, 0, 0, 1, -1, 1, -1};
    private static final int[] DY = {0, 0, 1, -1, 1, -1, -1, 1};
    private static final int[] OPP = {1, 0, 3, 2, 5, 4, 7, 6};

    @Override
    public String id() {
        return "hydrology";
    }

    @Override
    public void run(WorldGenContext ctx) {
        if (ctx.elevation == null) {
            throw new IllegalStateException("HydrologyStage wymaga ctx.elevation");
        }
        int w = ctx.width, h = ctx.height;
        float[][] elev = ctx.elevation;
        double seaLevel = ctx.config.seaLevel;

        float[][] filled = new float[h][w];
        int[][] dir = new int[h][w];       // indeks sąsiada w dół; -1 = brak (morze/ujście)
        boolean[][] closed = new boolean[h][w];
        int[] order = new int[w * h];      // kolejność zdejmowania z PQ (encoded j*w+i)
        int orderCount = 0;

        for (int[] row : dir) {
            java.util.Arrays.fill(row, -1);
        }

        // Kolejka priorytetowa po wysokości "filled" (min-heap).
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) ->
                Float.compare(filled[a[1]][a[0]], filled[b[1]][b[0]]));

        // Źródła: morze + krawędzie mapy (woda schodzi tam).
        for (int j = 0; j < h; j++) {
            for (int i = 0; i < w; i++) {
                boolean sea = elev[j][i] < seaLevel;
                boolean border = i == 0 || j == 0 || i == w - 1 || j == h - 1;
                if (sea || border) {
                    filled[j][i] = elev[j][i];
                    closed[j][i] = true;
                    pq.add(new int[]{i, j});
                }
            }
        }

        while (!pq.isEmpty()) {
            int[] c = pq.poll();
            int ci = c[0], cj = c[1];
            order[orderCount++] = cj * w + ci;
            for (int k = 0; k < 8; k++) {
                int ni = ci + DX[k], nj = cj + DY[k];
                if (ni < 0 || nj < 0 || ni >= w || nj >= h || closed[nj][ni]) {
                    continue;
                }
                filled[nj][ni] = Math.max(elev[nj][ni], filled[cj][ci]);
                dir[nj][ni] = OPP[k]; // od sąsiada w stronę c
                closed[nj][ni] = true;
                pq.add(new int[]{ni, nj});
            }
        }

        // Mikro-jitter na wypełnionym DEM: łamie równoległy „combing" D8 na płaskich
        // dnach na naturalny dendryt. Amplituda ~2 cm — poniżej realnego reliefu, więc
        // nie tworzy fałszywego drenażu ani nie rusza progów jeziora/bagna.
        for (int j = 0; j < h; j++) {
            for (int i = 0; i < w; i++) {
                filled[j][i] += (float) (hash01(i, j) * 0.02);
            }
        }

        // Kierunek spływu = najstromszy spadek po WYPEŁNIONYM DEM (w dolinach zbiega
        // się dendrytycznie). Płaskie dna (wypełnione misy) nie mają spadku → wtedy
        // fallback na kierunek z priority-flood (dir), który routuje płaskie ku ujściu.
        int[][] flow = new int[h][w];
        for (int[] row : flow) {
            java.util.Arrays.fill(row, -1);
        }
        for (int j = 0; j < h; j++) {
            for (int i = 0; i < w; i++) {
                if (elev[j][i] < seaLevel) {
                    continue;
                }
                int best = -1;
                float bestVal = filled[j][i];
                for (int k = 0; k < 8; k++) {
                    int ni = i + DX[k], nj = j + DY[k];
                    if (ni < 0 || nj < 0 || ni >= w || nj >= h) {
                        continue;
                    }
                    if (filled[nj][ni] < bestVal) {
                        bestVal = filled[nj][ni];
                        best = k;
                    }
                }
                flow[j][i] = best >= 0 ? best : dir[j][i]; // fallback: płaskie → ku ujściu
            }
        }

        // Akumulacja: init = OPAD (ważony wilgotnością) na lądzie, 0 na morzu.
        // Mokre regiony wnoszą więcej spływu → grubsze/gęstsze rzeki proceduralne;
        // suche wnoszą mało. Suma w dół w odwrotnej kolejności.
        WorldGenConfig cfg = ctx.config;
        float[][] accum = new float[h][w];
        for (int j = 0; j < h; j++) {
            for (int i = 0; i < w; i++) {
                if (elev[j][i] < seaLevel) {
                    accum[j][i] = 0;
                } else {
                    double lvl = humidityLevel(ctx, i, j);
                    accum[j][i] = (float) (cfg.rainfallMin + lvl * (cfg.rainfallMax - cfg.rainfallMin));
                }
            }
        }
        for (int idx = orderCount - 1; idx >= 0; idx--) {
            int enc = order[idx];
            int ci = enc % w, cj = enc / w;
            int d = flow[cj][ci];
            if (d >= 0) {
                int ni = ci + DX[d], nj = cj + DY[d];
                accum[nj][ni] += accum[cj][ci];
            }
        }

        // Klasyfikacja wody: jezioro (głęboka niecka) / bagno (płytka woda lub płaski
        // mokry teren) / strumień (próg skalowany wilgotnością). Autorska woda wypalona
        // jest wymuszana i odporna na wilgotność.
        boolean[][] lake = new boolean[h][w];
        boolean[][] river = new boolean[h][w];
        boolean[][] marsh = new boolean[h][w];
        // Baza progu strumienia: powierzchnia zlewni (km²) → liczba komórek.
        double baseThr = cfg.riverDrainageKm2 * 1_000_000.0 / (ctx.cellSize * ctx.cellSize);
        for (int j = 0; j < h; j++) {
            for (int i = 0; i < w; i++) {
                if (elev[j][i] < seaLevel) {
                    continue;
                }
                // Autorska woda (wypalona) — odporna na wilgotność: zawsze obecna.
                if (ctx.burnedRiver != null && ctx.burnedRiver[j][i]) {
                    river[j][i] = true;
                    continue;
                }
                if (ctx.burnedLake != null && ctx.burnedLake[j][i]) {
                    lake[j][i] = true;
                    continue;
                }
                double lvl = humidityLevel(ctx, i, j);
                double depth = filled[j][i] - elev[j][i];
                // Próg strumienia skalowany wilgotnością: mokro → gęsta sieć cieków.
                double thr = baseThr * (cfg.streamDryMultiplier
                        + lvl * (cfg.streamWetMultiplier - cfg.streamDryMultiplier));

                if (depth >= cfg.lakeMinDepth) {
                    lake[j][i] = true;                         // głęboka niecka = otwarta woda
                } else if (depth >= cfg.marshMinDepth && lvl >= cfg.marshHumidity) {
                    marsh[j][i] = true;                        // płytkie rozlanie w wilgoci = bagno
                } else if (accum[j][i] >= thr) {
                    river[j][i] = true;                        // strumień/rzeka
                }
            }
        }

        ctx.flowAccum = accum;
        ctx.lake = lake;
        ctx.river = river;
        ctx.marsh = marsh;
    }

    /** Deterministyczny hash [0,1) z (i,j) — do mikro-jittera rozbijającego combing. */
    private static double hash01(int i, int j) {
        long z = (i * 0x1f1f1f1fL) ^ (j * 0x8da6b343L);
        z ^= (z >>> 13);
        z *= 0x9E3779B97F4A7C15L;
        z ^= (z >>> 7);
        return (z & 0xffffffffL) / (double) 0x100000000L;
    }

    /**
     * Wilgotność 0..1 w komórce. Priorytet: policzone pole {@code humidityGrid}
     * (Etap 5: prior + korekta terenu), potem ręczne pole {@code humidity},
     * inaczej neutralna 0.5.
     */
    private static double humidityLevel(WorldGenContext ctx, int i, int j) {
        if (ctx.humidityGrid != null) {
            return ctx.humidityGrid[j][i];
        }
        if (ctx.humidity == null) {
            return 0.5;
        }
        double v = ctx.humidity.at(ctx.worldX(i), ctx.worldY(j));
        if (v < 0) {
            return 0.5;
        }
        return Math.max(0, Math.min(1, v));
    }
}
