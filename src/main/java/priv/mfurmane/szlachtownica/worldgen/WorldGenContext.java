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
    public float[][] elevation;     // [height][width], metry n.p.m.
    public BedrockType[][] bedrock; // [height][width], skała macierzysta; null poza lądem
    public float[][] flowAccum;     // [height][width], akumulacja spływu (w komórkach)
    public boolean[][] river;       // [height][width], komórka rzeczna
    public boolean[][] lake;        // [height][width], komórka jeziora (głęboka niecka = otwarta woda)
    public boolean[][] marsh;       // [height][width], mokradło/bagno (płytka woda / płaski mokry teren)

    // Wejścia terenowe (wypełnia adapter z geometrii prowincji/gór/morza)
    public List<Peak> peaks = new ArrayList<>();
    public LandMask landMask; // null => całość traktowana jako ląd (fallback)
    public SeaMask seaMask;   // null => morze = poza lądem (fallback); inaczej morze = tylko tu
    public HeightBias heightBias; // null => brak highmapa (czysto proceduralnie); inaczej makro-relief

    // Predefiniowane cieki/akweny do „wypalenia" w DEM (world-metric łamane).
    // Każdy element to [n][2] = {x,y} w metrach. null => nic nie wypalamy.
    public List<double[][]> riverPaths;  // łamane linii rzek (kolejność = bieg cieku)
    public List<double[][]> lakePolys;   // pierścienie zewn. poligonów jezior

    // Maski komórek wypalonych — autorska woda odporna na wilgotność (Nil na pustyni).
    public boolean[][] burnedRiver;      // oś wypalonego cieku → zawsze rzeka
    public boolean[][] burnedLake;       // wnętrze wypalonego jeziora → zawsze jezioro

    // Pole wilgotności (opad): 0 = ekstremalnie sucho, 1 = ekstremalnie mokro.
    // null => hydrologia liczy jednorodny opad (jak dawniej).
    public HumidityField humidity;

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

    /** Czy dany punkt świata jest lądem. */
    @FunctionalInterface
    public interface LandMask {
        boolean isLand(double worldX, double worldY);
    }

    /** Czy dany punkt świata jest morzem (rzeczywiste akweny, nie „poza lądem"). */
    @FunctionalInterface
    public interface SeaMask {
        boolean isSea(double worldX, double worldY);
    }

    /**
     * Autorski highmap jako makro-relief. Zwraca jasność 0..1 (0 = nizina/dno,
     * 1 = najwyższy szczyt) dla punktu świata, albo wartość ujemną, gdy punkt jest
     * poza obrazem (wtedy etap wysokości spada na czystą proceduralną bazę).
     */
    @FunctionalInterface
    public interface HeightBias {
        double at(double worldX, double worldY);
    }

    /**
     * Wilgotność (opad) w punkcie świata: 0..1 (sucho→mokro), albo wartość
     * ujemna, gdy punkt jest poza regionami (hydrologia bierze wtedy neutralną).
     */
    @FunctionalInterface
    public interface HumidityField {
        double at(double worldX, double worldY);
    }

    /** Szczyt/wypiętrzenie: pozycja w metrach, wysokość dodawana, promień wpływu. */
    public record Peak(double x, double y, double height, double radius) {}
}
