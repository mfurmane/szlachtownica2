package priv.mfurmane.szlachtownica.engine.naming.model;

import java.util.*;

public interface Phonotactic {

    // =========================
    // VALIDATION CORE
    // =========================

    boolean isValid(String word);

    boolean isValidSyllable(Syllable syllable);

    boolean canFollow(Syllable previous, Syllable next);

    boolean canFollowInSyllable(String previous, String next);

    Morphology getMorphology();

    // =========================
    // LANGUAGE STRUCTURE
    // =========================

    Map<String, Double> onsets();
    Map<String, Double> nuclei();
    Map<String, Double> codas();

    int maxOnsetLength();
    int maxCodaLength();

    int minSyllables();
    int maxSyllables();

    // =========================
    // GENERATION SUPPORT
    // =========================

    default Syllable randomSyllable() {
        String onset = pickRandom(onsets());
        String nucleus = pickRandom(nuclei(), onset);
        String coda = pickRandom(codas(), onset + nucleus);
        return new Syllable(
                onset,
                nucleus,
                coda
        );
    }

    default String pickRandom(Map<String, Double> map) {
        return WeightedPicker.pick(map);
    }

    default String pickRandom(Map<String, Double> next, String previous) {
        String picked = WeightedPicker.pick(next);
        while (!canFollowInSyllable(previous, picked)) {
            picked = WeightedPicker.pick(next);
        }
        return picked;
    }

    default String generateCapitalizedWord(WordType type, Map<Integer, Double> syllables) {
        return capitalize(generateWord(type, syllables));
    }

    default String generateWord(WordType type, Map<Integer, Double> syllables) {

//        int syllables = minSyllables() + rng.nextInt(maxSyllables() - minSyllables() + 1);

        StringBuilder stem = new StringBuilder();
//        String onset = WeightedPicker.pick(onsetWeights, rng);
//        String nucleus = WeightedPicker.pick(nucleusWeights, rng);
//        String coda = WeightedPicker.pick(codaWeights, rng);

        int syllablesValue = WeightedPicker.pick(syllables);

        Syllable prev = null;

        for (int i = 0; i < syllablesValue; i++) {
            Syllable next;

            int safety = 0;
            do {
                next = randomSyllable();
                safety++;
            } while (prev != null && !canFollow(prev, next) && safety < 20);

            stem.append(next.render());
            prev = next;
        }

        String finalWord = getMorphology().applyEnding(stem.toString(), prev, type);

        return finalWord;
    }

    default String capitalize(String s) {
        if (s == null || s.isEmpty()) return s;
        return s.substring(0, 1).toUpperCase(Locale.ROOT) + s.substring(1);
    }
}





//public interface Phonotactic {
//    /*1. Zbiory:
//onset (początek sylaby)
//nucleus (samogłoski)
//coda (koniec sylaby)
//2. Reguły:
//które onsety mogą iść z którymi nucleusami
//jakie zbitki są zabronione
//3. Generator:
//losujesz sylaby wg reguł
//sklejasz
//🔥 Bonus: bardziej zaawansowane podejście
//
//Jeśli chcesz iść dalej (i to już jest „pro level”):
//
//Markov chains (łańcuchy Markowa) na literach/sylabach
//gramatyki generatywne
//fonotaktyczne constraint satisfaction
//*/
//
//}
