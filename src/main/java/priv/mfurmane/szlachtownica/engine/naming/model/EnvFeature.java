package priv.mfurmane.szlachtownica.engine.naming.model;

/**
 * Cecha środowiska dostępna w okolicy wioski. Przekazywana do generatora jako
 * kontekst — rdzeń słowa użyty w nazwie może wymagać obecności danej cechy
 * (np. "czapla"/"most" wymaga WATER), żeby nie tworzyć "Stawowej Czapli" na
 * skalistym płaskowyżu.
 */
public enum EnvFeature {
    WATER,      // rzeka / jezioro / staw — cokolwiek mokrego
    WETLAND,    // bagno / mokradło / trzęsawisko
    FOREST,     // las / bór / gęsty drzewostan
    MOUNTAINS,  // góry / skały / urwiska / płaskowyż
    SAND,       // piaski / wydmy / wybrzeże
    MEADOW      // łąki / otwarte niziny
}
