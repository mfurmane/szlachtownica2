package priv.mfurmane.szlachtownica.worldgen;

/**
 * Etap 2: skała macierzysta ({@code ctx.bedrock}). Formacje są spójne
 * przestrzennie (niskoczęstotliwościowy fBm = duże pasy litologii), a wybór typu
 * kontrolowany wysokością i "domenami wulkanicznymi":
 *  - wysoko → skały krystaliczne/metamorficzne (granit/łupek), wulkaniczne bazalty;
 *  - nisko  → skały osadowe (łupek ilasty / piaskowiec / wapień), z pokrywami bazaltu.
 * Aluwia (osady rzeczne) NIE tu — dojdą w hydrologii/glebie. Wymaga elewacji z Etapu 1.
 */
public class GeologyStage implements WorldGenStage {

    @Override
    public String id() {
        return "geology";
    }

    @Override
    public void run(WorldGenContext ctx) {
        if (ctx.elevation == null) {
            throw new IllegalStateException("GeologyStage wymaga ctx.elevation — uruchom ElevationStage wcześniej");
        }
        int w = ctx.width, h = ctx.height;
        WorldGenConfig c = ctx.config;

        double maxLand = 1;
        for (float[] row : ctx.elevation) {
            for (float v : row) {
                if (v > maxLand) maxLand = v;
            }
        }

        double fLith = ctx.cellSize / c.geologyScaleMeters;         // px → domena litologii
        double fVolc = ctx.cellSize / (c.geologyScaleMeters * 1.7); // domeny wulkaniczne (rzadsze)

        BedrockType[][] rock = new BedrockType[h][w];
        for (int j = 0; j < h; j++) {
            for (int i = 0; i < w; i++) {
                double elev = ctx.elevation[j][i];
                if (elev < c.seaLevel) {
                    rock[j][i] = null; // poza lądem
                    continue;
                }
                double lith = Noise.fbm(i * fLith, j * fLith, c.geologyOctaves, c.seed + 11);
                double volc = Noise.fbm(i * fVolc, j * fVolc, c.geologyOctaves, c.seed + 29);
                rock[j][i] = classify(elev / maxLand, lith, volc);
            }
        }
        ctx.bedrock = rock;
    }

    private static BedrockType classify(double heightNorm, double lith, double volc) {
        if (heightNorm > 0.55) { // góry / wyżyny
            if (volc > 0.62) {
                return BedrockType.BASALT;
            }
            return lith > 0.5 ? BedrockType.GRANITE : BedrockType.SCHIST;
        }
        // niziny
        if (volc > 0.70) {
            return BedrockType.BASALT; // pokrywy bazaltowe
        }
        if (lith < 0.40) {
            return BedrockType.SHALE;
        }
        if (lith < 0.60) {
            return BedrockType.SANDSTONE;
        }
        return BedrockType.LIMESTONE;
    }
}
