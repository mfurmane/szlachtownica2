//package priv.mfurmane.szlachtownica.engine.naming.unused;
//
//import priv.mfurmane.szlachtownica.engine.naming.model.Phonotactic;
//import priv.mfurmane.szlachtownica.engine.naming.model.Syllable;
//
//import java.util.Map;
//import java.util.Set;
//
//public class PolishPhonotactic implements Phonotactic {
//    private final Set<String> onsets = Set.of(
//            "b", "br", "bl",
//            "k", "kr", "kl",
//            "p", "pr", "pl",
//            "t", "tr", "tw",
//            "d", "dr",
//            "m", "n",
//            "s", "st", "str",
//            "z", "dz",
//            "cz", "sz", "rz"
//    );
//
//    private final Set<String> nuclei = Set.of(
//            "a", "e", "i", "o", "u", "y",
//            "ą", "ę"
//    );
//
//    private final Set<String> codas = Set.of(
//            "", "k", "t", "n", "m", "r",
//            "cz", "sz", "ść", "sk"
//    );
//
//    @Override
//    public boolean isValid(String word) {
//        return word != null
//                && word.length() >= 2
//                && Character.isLowerCase(word.charAt(0)) == false;
//    }
//
//    @Override
//    public boolean isValidSyllable(Syllable s) {
//        return onsets.contains(s.onset())
//                && nuclei.contains(s.nucleus())
//                && codas.contains(s.coda());
//    }
//
//    @Override
//    public boolean canFollow(Syllable prev, Syllable next) {
//        // typowa polska zasada:
//        // unikamy powtórzeń identycznych zbitek
//        return !prev.onset().equals(next.onset());
//    }
//
//    @Override
//    public Map<String, Double> onsets() {
//        return onsets;
//    }
//
//    @Override
//    public Set<String> nuclei() {
//        return nuclei;
//    }
//
//    @Override
//    public Set<String> codas() {
//        return codas;
//    }
//
//    @Override
//    public int maxOnsetLength() {
//        return 3;
//    }
//
//    @Override
//    public int maxCodaLength() {
//        return 2;
//    }
//
//    @Override
//    public int minSyllables() {
//        return 2;
//    }
//
//    @Override
//    public int maxSyllables() {
//        return 4;
//    }
//}
