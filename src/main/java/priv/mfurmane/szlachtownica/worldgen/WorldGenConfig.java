package priv.mfurmane.szlachtownica.worldgen;

/**
 * Parametry pieczenia świata. Trzymane w jednym miejscu, żeby dało się je
 * później wystawić na REST i stroić bez rekompilacji.
 */
public class WorldGenConfig {

    /** Ziarno — ta sama wartość = ten sam świat (reprodukowalność, debug). */
    public long seed = 1L;

    /**
     * FIXME: warstwa snapshotów (zapis rastrów + stanu) jeszcze nie istnieje.
     * Dopóki false — cały pipeline liczy się w pamięci i NIC nie jest zapisywane.
     * Gdy powstanie persystencja, przełącznik włączy zapis artefaktów etapów.
     */
    public boolean persist = false;

    /** Rozdzielczość rastra (metry na piksel). Por. HighMapUtils.metersPerPixel. */
    public double metersPerPixel = 100;

    // --- Parametry etapu wysokości (DEM) ---
    /** Poziom morza w metrach (punkt odniesienia). */
    public double seaLevel = 0;
    /** Maks. wysokość bazowa lądu bez gór (m). */
    public double baseHeight = 700;
    /** Metry na komórkę szumu kontynentalnego (większe = większe, gładsze formy). */
    public double continentalScaleMeters = 35000;
    /** Liczba oktaw fBm dla lądu (więcej = drobniejszy relief, lepiej dla drenażu). */
    public int elevationOctaves = 6;
    /** Szerokość strefy nadbrzeżnego wypłycenia/wypłaszczenia (m). */
    public double coastWidthMeters = 18000;
    /** Maks. głębokość morza przy brzegu→dalej (m). */
    public double seaDepth = 300;

    // --- Parametry etapu geologii ---
    /** Rozmiar formacji geologicznych w metrach (większe = większe pasy litologii). */
    public double geologyScaleMeters = 30000;
    /** Liczba oktaw fBm dla domen geologicznych. */
    public int geologyOctaves = 3;

    // --- Parametry etapu 3b: erozji hydraulicznej (kropelkowej) ---
    /** Liczba kropli na komórkę rastra (więcej = mocniejsze wcięcie dolin, wolniej). */
    public double erosionDropletDensity = 0.6;
    /** Promień pędzla erozji (rozmycie, by nie robić kolców). */
    public int erosionRadius = 3;
    /** Maks. długość życia kropli (kroków). */
    public int erosionMaxLifetime = 40;
    /** Bezwładność kierunku kropli 0..1 (mniej = wierniej podąża spadkiem). */
    public double erosionInertia = 0.06;
    /** Współczynnik pojemności transportu osadu. */
    public double erosionCapacity = 5;
    /** Minimalny spadek (stabilizuje pojemność na płaskim). */
    public double erosionMinSlope = 0.01;
    /** Tempo erozji 0..1. */
    public double erosionErode = 0.3;
    /** Tempo depozycji 0..1. */
    public double erosionDeposit = 0.3;
    /** Parowanie na krok 0..1 (skraca zasięg kropli). */
    public double erosionEvaporation = 0.02;
    /** Grawitacja (przyspieszanie na spadku). */
    public double erosionGravity = 6;
    /** Jak bardzo twardość skały macierzystej spowalnia erozję 0..1. */
    public double erosionHardnessInfluence = 0.7;

    // --- Parametry etapu hydrologii ---
    /**
     * Min. powierzchnia zlewni (km²), by komórka stała się rzeką. Niezależne od
     * rozdzielczości — w komórki przelicza się przez rozmiar piksela.
     */
    public double riverDrainageKm2 = 25;
    /** Min. głębokość wypełnienia (m), by uznać zagłębienie za jezioro. */
    public double lakeMinDepth = 0.5;
}
