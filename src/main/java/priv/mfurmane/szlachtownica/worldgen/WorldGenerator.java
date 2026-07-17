package priv.mfurmane.szlachtownica.worldgen;

import java.util.List;

/**
 * Orchestrator pieczenia świata: uruchamia etapy po kolei na jednym
 * {@link WorldGenContext}. Persystencja artefaktów jest tu (a nie w etapach) i
 * tylko gdy {@code config.persist == true} — na razie wyłączona (patrz FIXME).
 */
public class WorldGenerator {

    private final List<WorldGenStage> stages;

    public WorldGenerator(List<WorldGenStage> stages) {
        this.stages = stages;
    }

    public WorldGenContext bake(WorldGenContext ctx) {
        for (WorldGenStage stage : stages) {
            long t0 = System.currentTimeMillis();
            stage.run(ctx);
            System.out.println("[worldgen] " + stage.id() + " OK (" + (System.currentTimeMillis() - t0) + "ms)");

            if (ctx.config.persist) {
                // FIXME: brak warstwy snapshotów — tu wyląduje zapis artefaktu etapu
                //  (raster do pliku/bazy + metadane). Dopóki persist=false, nic nie piszemy.
                // persist(stage.id(), ctx);
                System.out.println("[worldgen] persist(" + stage.id() + ") pominięte — FIXME: brak snapshotów");
            }
        }
        return ctx;
    }
}
