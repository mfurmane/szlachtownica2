package priv.mfurmane.szlachtownica.worldgen;

/**
 * Etap 5: wilgotność z geografii (opad) — cień opadowy od wiatru.
 *
 * Model advekcji wilgoci (raster, bez JTS → testowalny offline):
 *  - wiatr zachodni (W→E, „ryczące czterdziestki" dla ~40–46°S) niesie zasób
 *    wilgoci przez wiersz;
 *  - nad morzem zasób się nasyca (parowanie), nad lądem wytrąca się w opad —
 *    tym mocniej, im bardziej powietrze wznosi się po NAWIETRZNYM stoku
 *    (efekt orograficzny); za grzbietem powietrze osusza się → cień opadowy;
 *  - policzony opad łączymy z PRIOREM (ręczna wilgotność regionu): finalna =
 *    prior + siła·(opad_znorm − 0.5). Ręczny zamysł zostaje kotwicą, teren go
 *    realistycznie koryguje.
 *
 * Wynik: {@code ctx.humidityGrid} (0..1) — konsumowany przez hydrologię.
 */
public class HumidityStage implements WorldGenStage {

    @Override
    public String id() {
        return "humidity";
    }

    @Override
    public void run(WorldGenContext ctx) {
        if (ctx.elevation == null) {
            throw new IllegalStateException("HumidityStage wymaga ctx.elevation");
        }
        int w = ctx.width, h = ctx.height;
        WorldGenConfig c = ctx.config;
        float[][] elev = ctx.elevation;
        double seaLevel = c.seaLevel;

        // 1) Advekcja wilgoci wiatrem zachodnim (W→E). Sygnałem wilgotności jest
        //    ZASÓB wilgoci docierający do komórki (ambient) — maleje w głąb lądu,
        //    a wznoszenie po nawietrznym stoku wytrąca opad i osusza powietrze za
        //    grzbietem (cień opadowy). Ambient jest z natury 0..1, więc nie wymaga
        //    kruchej normalizacji przez pojedyncze szczyty.
        float[][] ambient = new float[h][w];
        for (int j = 0; j < h; j++) {
            double m = c.moistureMax; // wlot od zachodu = nasycony (ocean za brzegiem)
            for (int i = 0; i < w; i++) {
                boolean sea = elev[j][i] < seaLevel;
                if (sea) {
                    m += (c.moistureMax - m) * c.moistureEvap; // nasycanie nad morzem
                    ambient[j][i] = (float) m;
                } else {
                    ambient[j][i] = (float) m; // zasób docierający do komórki (przed opadem)
                    double prev = i > 0 ? elev[j][i - 1] : elev[j][i];
                    double rise = Math.max(0, elev[j][i] - prev); // wznoszenie w kierunku wiatru
                    double oro = c.orographicFactor * Math.min(1.0, rise / c.orographicRiseScale);
                    double p = m * (c.precipBaseFraction + oro); // wytrącony opad
                    m = Math.max(0, m - p); // ubytek → cień opadowy dalej na wschód
                }
            }
        }

        // 2) Średni zasób nad lądem — oś obrotu korekty (żeby prior nie przesuwał
        //    się globalnie, tylko był redystrybuowany terenem).
        double sum = 0;
        int n = 0;
        for (int j = 0; j < h; j++) {
            for (int i = 0; i < w; i++) {
                if (elev[j][i] >= seaLevel) {
                    sum += ambient[j][i];
                    n++;
                }
            }
        }
        double meanM = n > 0 ? sum / n : 0.5;

        // 3) Połączenie z priorem (ręczna wilgotność regionu) → finalna wilgotność.
        float[][] hum = new float[h][w];
        for (int j = 0; j < h; j++) {
            for (int i = 0; i < w; i++) {
                if (elev[j][i] < seaLevel) {
                    hum[j][i] = 1f; // morze: pełna wilgoć (nieistotne dla lądowej hydrologii)
                    continue;
                }
                double prior = priorAt(ctx, i, j);
                double v = prior + c.humidityCorrectionStrength * (ambient[j][i] - meanM);
                hum[j][i] = (float) Math.max(0, Math.min(1, v));
            }
        }
        ctx.humidityGrid = hum;
    }

    /** Prior wilgotności: z ręcznego pola regionów (ctx.humidity), inaczej neutralny 0.5. */
    private static double priorAt(WorldGenContext ctx, int i, int j) {
        if (ctx.humidity == null) {
            return 0.5;
        }
        double v = ctx.humidity.at(ctx.worldX(i), ctx.worldY(j));
        return v < 0 ? 0.5 : Math.max(0, Math.min(1, v));
    }
}
