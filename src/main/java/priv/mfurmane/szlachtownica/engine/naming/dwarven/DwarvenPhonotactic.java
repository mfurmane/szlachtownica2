package priv.mfurmane.szlachtownica.engine.naming.dwarven;

import priv.mfurmane.szlachtownica.engine.naming.model.*;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class DwarvenPhonotactic implements Phonotactic {

    private final Morphology morphology = new DwarvenMorphology();

    private final Map<String, Double> onsets = new WeightMapBuilder()
//            .add(3.0, "")
            .add(2.0, "g", "d", "r", "w")
            .add(0.5, "s", "m", "n", "v")
            .build();

    private final Map<String, Double> nuclei = new WeightMapBuilder()
            .add(1.0, "a", "i")
            .add(0.25, "o", "e", "u", "y")
            .build();

    private final Map<String, Double> codas = new WeightMapBuilder()
            .add(2.0, "g", "d", "r", "w")
            .add(0.5, "s", "m", "n", "v")
            .build();

    @Override
    public boolean isValid(String word) {
        if (word == null || word.isBlank()) return false;
        // bardzo uproszczony parser sylabowy
        // (w praktyce możesz zrobić pełny tokenizer)
        return word.length() > 2;
    }

    @Override
    public boolean isValidSyllable(Syllable s) {
        return onsets.containsKey(s.onset()) && nuclei.containsKey(s.nucleus()) && codas.containsKey(s.coda());
    }

    @Override
    public boolean canFollow(Syllable prev, Syllable next) {
        return true;
    }

    @Override
    public boolean canFollowInSyllable(String previous, String next) {
        return true;
    }

    @Override
    public String generateCapitalizedWordWithHardCoda(WordType wordType, Map<Integer, Double> syllabes) {
        String generated = generateCapitalizedWord(wordType, syllabes);
        if (!codas.containsKey(generated.substring(generated.length() - 1, generated.length() - 0))) {
//            generated += mainCodas[ThreadLocalRandom.current().nextInt(mainCodas.length)];
        }
        return generated;
    }

    @Override
    public Morphology getMorphology() {
        return this.morphology;
    }

    @Override
    public Map<String, Double> onsets() {
        return onsets;
    }

    @Override
    public Map<String, Double> nuclei() {
        return nuclei;
    }

    @Override
    public Map<String, Double> codas() {
        return codas;
    }

    @Override
    public int maxOnsetLength() {
        return 2;
    }

    @Override
    public int maxCodaLength() {
        return 2;
    }

    @Override
    public int minSyllables() {
        return 2;
    }

    @Override
    public int maxSyllables() {
        return 4;
    }

}
