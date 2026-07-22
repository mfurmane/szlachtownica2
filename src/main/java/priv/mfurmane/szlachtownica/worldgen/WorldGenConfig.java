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
    /** Wysokość wypiętrzenia gór (m), gdy ModelMountains nie ma ustawionego height. */
    public double defaultMountainHeight = 1800;

    // --- Hybryda: autorski highmap jako makro-relief ---
    /**
     * Ścieżka do autorskiego highmapa (grayscale PNG w tej samej ramce bbox).
     * null/pusto = tryb czysto proceduralny (highmap ignorowany).
     */
    public String highmapPath = null;
    /** Górny limit dłuższego boku przy wczytywaniu highmapa (subsampling — oszczędza pamięć). */
    public int highmapMaxDim = 2048;
    /** Udział highmapa w makro-reliefie 0..1 (reszta to proceduralny fBm). */
    public double highmapWeight = 0.85;
    /** Wysokość niziny highmapa (m) — poziom dla czerni lądu (hm=0). Nadmorskie
     *  prowincje kanonu (Larazza/Saraidan ~100 m). */
    public double highmapLowland = 100;
    /** Wysokość najjaśniejszego piksela highmapa (m) — szczyt (hm=1). Kanon:
     *  arkusz Metryczka mapuje highmap 0→2000 m (Uvarra/Międzygórze = 2000 m, max). */
    public double highmapHeightScale = 2000;
    /** Amplituda proceduralnego detalu (m) dokładanego na highmap, by erozja miała co rzeźbić. */
    public double highmapDetailMeters = 70;

    // --- Parametry etapu geologii ---
    /** Rozmiar formacji geologicznych w metrach (większe = większe pasy litologii). */
    public double geologyScaleMeters = 30000;
    /** Liczba oktaw fBm dla domen geologicznych. */
    public int geologyOctaves = 3;

    // --- Parametry etapu 3b: erozji hydraulicznej (kropelkowej) ---
    /** Liczba kropli na komórkę rastra (więcej = mocniejsze wcięcie dolin, wolniej). */
    public double erosionDropletDensity = 1.2;
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

    // --- Etap wypalania predefiniowanych rzek/jezior (stream burning) ---
    /** Czy wypalać predefiniowane rzeki (ModelRiver) w DEM przed hydrologią. */
    public boolean burnRivers = true;
    /** Czy wypalać predefiniowane jeziora (ModelLake) jako niecki. */
    public boolean burnLakes = true;
    /** Głębokość koryta wypalanego wzdłuż rzeki (m poniżej terenu). */
    public double riverBurnDepth = 45;
    /** Promień koryta rzeki (komórki) — szerokość wypalanego rowka. */
    public int riverBurnRadius = 1;
    /** Głębokość niecki jeziora poniżej najniższego punktu obrzeża (m). */
    public double lakeBurnDepth = 40;

    // --- Etap 4: klimat (temperatura) ---
    /**
     * Średnia temp. na poziomie morza przy północnej krawędzi mapy (°C, ciepło —
     * bliżej równika). KANON (arkusz Geografia): 39°S = 22,8°C, gradient −0,7°C/°;
     * północna krawędź ≈ 39,9°S → ~22,2°C. Chłód Orvanoru itd. bierze się z
     * WYSOKOŚCI (spadek 6,5°C/km), nie z zimnego poziomu morza.
     */
    public double tempNorthC = 22.2;
    /** Średnia temp. na poziomie morza przy południowej krawędzi mapy (°C).
     *  KANON: 46,5°S ≈ 17,6°C (22,8 − 0,7·7,5). */
    public double tempSouthC = 17.6;
    /** Pionowy gradient temperatury (°C na 1000 m). KANON: 0,65°C/100 m = 6,5°C/km. */
    public double lapseRatePerKm = 6.5;

    // --- Etap 5: wilgotność z geografii (wiatr + cień opadowy) ---
    /**
     * Kierunek dominującego wiatru w stopniach (0 = z zachodu na wschód — wiatry
     * zachodnie, właściwe dla ~40–46°S). Na razie model używa składowej W→E.
     */
    public double prevailingWindDeg = 0;
    /** Maks. zasób wilgoci niesiony przez wiatr (nad morzem dąży do tej wartości). */
    public double moistureMax = 1.0;
    /** Tempo nasycania wilgoci nad morzem (na komórkę). */
    public double moistureEvap = 0.25;
    /** Bazowy ułamek wilgoci wytrącany nad lądem na komórkę (opad tła). */
    public double precipBaseFraction = 0.015;
    /** Siła efektu orograficznego (opad na nawietrznym wzniesieniu). */
    public double orographicFactor = 0.5;
    /** Wzniesienie (m/komórkę) dające pełny efekt orograficzny. */
    public double orographicRiseScale = 25;
    /**
     * Siła korekty terenowej nakładanej na prior (ręczną wilgotność). 0 = tylko
     * prior; 1 = mocna korekta. Finalna = prior + siła·(sym − 0.5), przycięta 0..1.
     */
    public double humidityCorrectionStrength = 0.45;

    // --- Sprzężenie wilgotności z hydrologią (opad → spływ, zalewanie) ---
    /** Opad (waga spływu na komórkę) przy ekstremalnej suszy (wilgotność 0). */
    public double rainfallMin = 0.2;
    /** Opad (waga spływu na komórkę) przy ekstremalnej mokrości (wilgotność 1). */
    public double rainfallMax = 2.0;
    /** Mnożnik progu STRUMIENIA przy suszy (wilgotność 0) — wyżej = mniej cieków. */
    public double streamDryMultiplier = 2.5;
    /** Mnożnik progu STRUMIENIA przy mokrości (wilgotność 1) — niżej = gęsta sieć strumyków. */
    public double streamWetMultiplier = 0.35;

    // --- Mokradła/bagna (osobna kategoria od jeziora) ---
    /**
     * Min. głębokość płytkiego rozlania (m), by zamiast suchego lądu było bagno.
     * Nisko — szerokie, płytkie niecki mokrych nizin (dawniej fałszywe „jeziora")
     * stają się bagnem/mokradłem zamiast otwartej wody.
     */
    public double marshMinDepth = 3.0;
    /** Min. wilgotność, przy której płytka woda staje się bagnem. */
    public double marshHumidity = 0.5;

    // --- Parametry etapu hydrologii ---
    /**
     * Bazowa min. powierzchnia zlewni (km²), by komórka stała się strumieniem/rzeką.
     * Skalowana wilgotnością (streamDry/WetMultiplier). Niżej = gęstsza sieć cieków.
     */
    public double riverDrainageKm2 = 15;
    /**
     * Min. głębokość wypełnienia (m), by uznać zagłębienie za JEZIORO (otwarta woda).
     * Wysoki próg — tylko głębokie niecki; płytsze rozlania idą w bagno, nie w wielkie
     * jeziora. NIE skalowany wilgotnością (mokre niziny → bagna, nie gigantyczne jeziora).
     */
    public double lakeMinDepth = 22;
}
