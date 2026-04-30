package priv.mfurmane.szlachtownica.engine.naming.model;

import java.util.Map;

public interface Morphology {

    Map<String, Double> allowedEndings(WordType type);

    boolean allowedAfterSyllable(Syllable previous, String ending);

    String applyEnding(String stem, Syllable prev, WordType type);
}
