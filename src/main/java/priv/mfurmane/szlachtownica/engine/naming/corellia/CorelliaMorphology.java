//package priv.mfurmane.szlachtownica.engine.naming.corellia;
//
//import priv.mfurmane.szlachtownica.engine.naming.model.Morphology;
//import priv.mfurmane.szlachtownica.engine.naming.model.WordType;
//
//import java.util.*;
//
//public class CorelliaMorphology implements Morphology {
//
//    private final Map<WordType, List<String>> endings = Map.of(
//            WordType.CITY, List.of(""),
//            WordType.PLACE, List.of(""),
//            WordType.PERSON, List.of(""),
//            WordType.VERB, List.of(""),
//            WordType.ABSTRACT, List.of("")
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
