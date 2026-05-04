package priv.mfurmane.szlachtownica.engine.naming.nereneth;

import priv.mfurmane.szlachtownica.engine.naming.model.*;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class NerenethPhonotactic implements Phonotactic {

    private final Morphology morphology = new NerenethMorphology();

    private final Map<String, Double> onsets = new WeightMapBuilder()
            .add(3.0, "", "s")
            .add(2.0, "v")
            .add(1.0, "l", "r", "qu")
            .add(0.5, "n", "g", "th")
            .add(0.2, "c", "t")
            .add(0.05, "b", "m", "f", "d", "k")
            .build();
    private final Map<String, Double> nuclei = new WeightMapBuilder()
            .add(6.0, "a", "e", "ae", "ea", "i")
//            .add(1.5, "oe", "eo", "oa", "ao")
            .add(0.6, "oe", "eo", "oa", "ao")
            .add(0.2, "ua", "ue", "au", "eu")
            .add(0.05, "ia", "ie")
            .build();
    private final Map<String, Double> codas = new WeightMapBuilder()
            .add(4.0, "")
            .add(1.0, "r", "l")
            .add(0.3, "n")
            .build();
    private final String[] mainCodas = {"r", "l", "n", "s"};

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
        boolean prevEndsEmpty = prev.coda().isEmpty();
        boolean nextStartsEmpty = next.onset().isEmpty();
//        boolean nextStartsWithA = next.onset().startsWith("a");
//        boolean nextStartsWithE = next.onset().startsWith("e");
//        boolean nextStartsWithI = next.onset().startsWith("i");
//        boolean nextStartsWithO = next.onset().startsWith("o");
//        boolean nextStartsWithU = next.onset().startsWith("u");
////        boolean prevEndsWithL = prev.coda().equals("l");
////        boolean nextStartsWithL = next.onset().equals("l");
////        boolean nextOnsetContainsL = next.onset().contains("l");
//        boolean nextStartsWithVocal = nextStartsWithA || nextStartsWithE || nextStartsWithI || nextStartsWithO || nextStartsWithU;
//
//        boolean nextAndPreviousTooSimilar = !next.onset().equals(prev.onset()) || ThreadLocalRandom.current().nextDouble() < 0.2;
//        boolean preventDoubleVocals = !(prevEndsEmpty && nextStartsWithVocal);
////        boolean noIlElAfterL = !prevEndsWithL || nextStartsWithL || !nextOnsetContainsL;
        boolean avoidSandwich =
                !(prev.coda().length() == 1
                        && next.onset().length() == 1
                        && prev.coda().equals(next.onset()));
        boolean noEmptyTransitions = !(prevEndsEmpty && nextStartsEmpty);
        return noEmptyTransitions && avoidSandwich
                && noReturningOnset("r", prev, next)
                && noReturningOnset("qu", prev, next);
    }

    private boolean noReturningOnset(String onset, Syllable prev, Syllable next) {
        boolean prevROnset = prev.onset().equals(onset);
        boolean nextROnset = next.onset().equals(onset);
        return !(prevROnset && nextROnset);
    }

    @Override
    public boolean canFollowInSyllable(String previous, String next) {
//        boolean previousIsQ = previous.equals("qu");
//        boolean previousStartsWithL = previous.startsWith("l");
//        boolean nextIsU = next.equals("u");
//        boolean previousContainsR = previous.contains("r");
//        boolean nextIsR = next.equals("r");
//        boolean nextIsL = next.equals("l");
//
//        boolean noLol = !previousStartsWithL || !nextIsL;
//        boolean noUAfterQu = !previousIsQ || !nextIsU;
//        boolean noRAfterPreviousR = !(previousContainsR && nextIsR);
//        return noUAfterQu && noRAfterPreviousR && noLol;
        return true;
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

    public String generateCapitalizedWordWithHardCoda(WordType wordType, Map<Integer, Double> syllabes) {
        String generated = generateCapitalizedWord(wordType, syllabes);
        if (!codas.containsKey(generated.substring(generated.length() - 1, generated.length() - 0))) {
            generated += mainCodas[ThreadLocalRandom.current().nextInt(mainCodas.length)];
        }
        return generated;
    }
}


