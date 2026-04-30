//package priv.mfurmane.szlachtownica.engine.naming.unused;
//
//import priv.mfurmane.szlachtownica.engine.naming.model.Morphology;
//import priv.mfurmane.szlachtownica.engine.naming.model.WordType;
//
//import java.util.*;
//
//public class PolishMorphology implements Morphology {
//
//    private final Map<WordType, List<String>> endings = Map.of(
//            WordType.CITY, List.of("ów", "owo", "ice", "in", "sk"),
//            WordType.PLACE, List.of("nia", "ska", "nia", "nica"),
//            WordType.PERSON, List.of("ek", "ka", "icz", "ski", "owski"),
//            WordType.VERB, List.of("ać", "ić", "ować", "nąć"),
//            WordType.ABSTRACT, List.of("ość", "enie", "acja", "izm")
//    );
//
//    @Override
//    public Map<String, Double> allowedEndings(WordType type) {
//        return new HashSet<>(endings.getOrDefault(type, List.of()));
//    }
//
//    @Override
//    public String applyEnding(String stem, WordType type, Random rng) {
//
//        List<String> opts = endings.getOrDefault(type, List.of());
//
//        if (opts.isEmpty()) return stem;
//
//        String ending = opts.get(rng.nextInt(opts.size()));
//
//        // prosta fonotaktyczna poprawka:
//        if (stem.endsWith("a") && ending.startsWith("a")) {
//            stem = stem.substring(0, stem.length() - 1);
//        }
//
//        return stem + ending;
//    }
//}
