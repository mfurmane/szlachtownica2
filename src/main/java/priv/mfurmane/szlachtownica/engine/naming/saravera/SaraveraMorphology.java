package priv.mfurmane.szlachtownica.engine.naming.saravera;

import priv.mfurmane.szlachtownica.engine.naming.model.*;

import java.util.Map;

public class SaraveraMorphology implements Morphology {
//    private static final String[] townPart3 = {"more", "vore", "gol", "dol", "na", "lon", "ia", "ma", "rin", "ta", "ra", "nal", "lin", "nor", "tel", "rel", "del", "el", "ria", "vel", "al", "gal", "noh"};
//    private final Map<String, Double> nuclei = new WeightMapBuilder()
//            .add(1.0, "a", "e", "i")
//            .add(0.75, "o")
//            .add(0.25, "u")
//            .build();

    private final Map<WordType, Map<String, Double>> endings = Map.of(
            WordType.CITY, new WeightMapBuilder()
                    .add(1.0, "twich", "vell", "dell")
                    .add(0.75, "lash", "sill")
                    .add(0.5, "swin")
                    .add(0.25, "loi")
                    .build(),
            WordType.PLACE, new WeightMapBuilder()
                    .add(1.0, "a", "e", "i")
                    .add(0.75, "o")
                    .add(0.25, "u")
                    .build()
            );

//    private final Map<WordType, List<String>> endings = Map.of(
//            WordType.CITY, List.of("more", "vore", "gol", "dol", "na", "lon", "ia", "ma", "rin", "ta", "ra", "nal", "lin", "nor", "tel", "rel", "del", "el", "ria", "vel", "al", "gal", "noh"),
//            WordType.PLACE, List.of(""),
//            WordType.PERSON, List.of(""),
//            WordType.VERB, List.of(""),
//            WordType.ABSTRACT, List.of("")
//    );

    @Override
    public Map<String, Double> allowedEndings(WordType type) {
        return endings.getOrDefault(type, Map.of());
    }

    @Override
    public boolean allowedAfterSyllable(Syllable previous, String ending) {
//        "n", "r", "l", "s", "")
        boolean emptyCoda = previous.coda().isEmpty();
        boolean rCoda = previous.coda().equals("r");
        boolean nCoda = previous.coda().equals("n");
        boolean lCoda = previous.coda().equals("l");
        boolean startsWithR = ending.startsWith("r");
        boolean startsWithG = ending.startsWith("g");
        boolean endsWithL = ending.endsWith("l");
        boolean moore = !ending.equals("moore");

        boolean noLols = !endsWithL || !lCoda;
        boolean allowedGol = emptyCoda || rCoda || nCoda || !startsWithG;
        boolean noRAfterConsonant = emptyCoda || !(startsWithR);
        boolean noMooreAfterR = !rCoda || moore;
        return noRAfterConsonant && noMooreAfterR && allowedGol && noLols;
    }

    @Override
    public String applyEnding(String stem, Syllable prev, WordType type) {

        Map<String, Double> opts = endings.getOrDefault(type, Map.of());

        if (opts.isEmpty()) return stem;

        String ending = WeightedPicker.pick(opts);

        while(!allowedAfterSyllable(prev, ending)) {
            ending = WeightedPicker.pick(opts);
        }

//        // prosta fonotaktyczna poprawka:
//        if (stem.endsWith("a") && ending.startsWith("a")) {
//            stem = stem.substring(0, stem.length() - 1);
//        }

        return stem + ending;
    }
}
