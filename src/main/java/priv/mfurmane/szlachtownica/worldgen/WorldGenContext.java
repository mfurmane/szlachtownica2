package priv.mfurmane.szlachtownica.worldgen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Wspólny stan pieczenia świata przekazywany między etapami. Trzyma georeferencję
 * rastra (świat metryczny → piksel), warstwy rastrowe i wejścia terenowe.
 * Nie zależy od JTS — geometrię wstrzykuje adapter (peaks + landMask).
 */
public class WorldGenContext {

    public final WorldGenConfig config;
    public final Random rng;

    // Georeferencja rastra
    public int width;
    public int height;
    public double minX;      // metry świata dla piksela (0,0)
    public double minY;
    public double cellSize;  // metry na piksel

    // Warstwy (wypełniane przez kolejne etapy)
    public float[][] elevation; // [height][width], metry n.p.m.

    // Wejścia terenowe (wypełnia adapter z geometrii prowincji/gór/morza)
    public List<Peak> peaks = new ArrayList<>();
    public LandMask landMask; // null => całość traktowana jako ląd

    public WorldGenContext(WorldGenConfig config) {
        this.config = config;
        this.rng = new Random(config.seed);
    }

    /** Współrzędna świata (metry) środka piksela kolumny i. */
    public double worldX(int i) {
        return minX + (i + 0.5) * cellSize;
    }

    /** Współrzędna świata (metry) środka piksela wiersza j. */
    public double worldY(int j) {
        return minY + (j + 0.5) * cellSize;
    }

    /** Czy dany punkt świata jest lądem (górski szczyt wypiętrzenia). */
    @FunctionalInterface
    public interface LandMask {
        boolean isLand(double worldX, double worldY);
    }

    /** Szczyt/wypiętrzenie: pozycja w metrach, wysokość dodawana, promień wpływu. */
    public record Peak(double x, double y, double height, double radius) {}
}
