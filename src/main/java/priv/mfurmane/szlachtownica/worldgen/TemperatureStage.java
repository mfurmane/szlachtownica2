package priv.mfurmane.szlachtownica.worldgen;

/**
 * Etap 4: średnia temperatura ({@code ctx.temperature}, °C).
 *
 * Model (raster, bez JTS → testowalny offline):
 *  - baza z szerokości geograficznej: liniowo od {@code tempSouthC} na południu
 *    (dalej od równika = zimniej) do {@code tempNorthC} na północy;
 *  - spadek z wysokością: {@code lapseRatePerKm} °C na każdy 1000 m n.p.m.;
 *  - morze bierze samą bazę szerokościową (bez spadku wysokościowego).
 *
 * Kierunek: wiersz {@code j=0} to południe (mniejsze worldY = minY), a
 * {@code j=h-1} północ — zgodnie z georeferencją kontekstu (north-up przy zapisie).
 */
public class TemperatureStage implements WorldGenStage {

    @Override
    public String id() {
        return "temperature";
    }

    @Override
    public void run(WorldGenContext ctx) {
        if (ctx.elevation == null) {
            throw new IllegalStateException("TemperatureStage wymaga ctx.elevation");
        }
        int w = ctx.width, h = ctx.height;
        WorldGenConfig c = ctx.config;
        float[][] temp = new float[h][w];
        for (int j = 0; j < h; j++) {
            double v = h > 1 ? (double) j / (h - 1) : 1.0;      // 0 = południe, 1 = północ
            double seaTemp = c.tempSouthC + v * (c.tempNorthC - c.tempSouthC);
            for (int i = 0; i < w; i++) {
                double t = seaTemp;
                double e = ctx.elevation[j][i];
                if (e > c.seaLevel) {
                    t -= c.lapseRatePerKm * (e - c.seaLevel) / 1000.0; // spadek z wysokością
                }
                temp[j][i] = (float) t;
            }
        }
        ctx.temperature = temp;
    }
}
