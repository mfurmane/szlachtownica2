package priv.mfurmane.szlachtownica.worldgen;

/**
 * Pojedynczy etap pieczenia świata (elevation, geology, hydrology, ...).
 * Etapy są CZYSTE: czytają i piszą stan w {@link WorldGenContext} (w pamięci).
 * Zapisywanie na dysk/bazę robi orchestrator ({@link WorldGenerator}) i tylko
 * gdy {@code config.persist == true} — dzięki temu persystencję można wyłączyć
 * jednym przełącznikiem, a etapy zostają testowalne bez I/O.
 */
public interface WorldGenStage {
    String id();

    void run(WorldGenContext ctx);
}
