package priv.mfurmane.szlachtownica.worldgen;

/**
 * Skała macierzysta (litologia) — wynik etapu geologii. Gleba (późniejszy etap)
 * dziedziczy po niej charakter, a {@code hardness} steruje erozją w hydrologii.
 * Nie mylić z {@code SoilType} (to jest podłoże, z którego gleba dopiero powstaje).
 */
public enum BedrockType {
    GRANITE(0.90),    // magmowa głębinowa; kwaśne, ubogie gleby; twarda
    BASALT(0.80),     // wulkaniczna; ciemne, żyzne gleby
    SCHIST(0.75),     // metamorficzna; gleby szkieletowe/kamieniste
    LIMESTONE(0.50),  // wapień; gleby zasadowe, kras
    SANDSTONE(0.45),  // piaskowiec; gleby piaszczyste, przepuszczalne
    SHALE(0.35);      // łupki/iłowce; gleby ilaste, słabo przepuszczalne

    private final double hardness; // odporność na erozję 0..1 (wejście do hydrologii)

    BedrockType(double hardness) {
        this.hardness = hardness;
    }

    public double getHardness() {
        return hardness;
    }
}
