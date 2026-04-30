package priv.mfurmane.szlachtownica.engine.naming.saravera;

import priv.mfurmane.szlachtownica.engine.naming.model.Morphology;
import priv.mfurmane.szlachtownica.engine.naming.model.Phonotactic;
import priv.mfurmane.szlachtownica.engine.naming.model.Syllable;
import priv.mfurmane.szlachtownica.engine.naming.model.WeightMapBuilder;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class SaraveraPhonotactic implements Phonotactic {

    private final Morphology morphology = new SaraveraMorphology();

    private final Map<String, Double> onsets = new WeightMapBuilder()
//            .add(3.0, "")
            .add(2.0, "g", "d", "l", "s", "r", "v")
            .add(1.5, "is", "il", "as", "av", "ev", "er")
            .add(1.0, "m", "c", "n", "el", "eg", "os", "or", "es", "an", "ag")
            .add(0.75, "ig", "og", "om", "id", "im", "em", "en", "ad")
            .add(0.5, "z", "x", "t","qu", "p", "ol", "on", "in", "ir", "us", "uv", "ak", "al", "am", "ar", "at")
            .add(0.25, "b", "k", "h", "ot", "ed", "et", "ug", "ur", "az")
            .add(0.05, "w", "f", "j")
            .build();

    private final Map<String, Double> nuclei = new WeightMapBuilder()
            .add(1.0, "a", "e", "i")
            .add(0.75, "o")
            .add(0.25, "u")
            .build();
//            = Set.of("a", "e", "i", "o", "u");

    private final Map<String, Double> codas = new WeightMapBuilder()
            .add(3.0, "n", "r", "l", "")
//            .add(2.5, "")
            .add(2.0, "s")
//            .add(1.5, "h")
//            .add(1.0, "")
//            .add(0.75, "")
//            .add(0.5, "")
//            .add(0.25, "")
//            .add(0.05, "")
            .build();
//    b,c,d,f,g,h,j,k,l,m,n,p,r,s,t,v,w,x,z
//            = Set.of("", "l", "n", "r", "m", "s");

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
        boolean nextStartsWithA = next.onset().startsWith("a");
        boolean nextStartsWithE = next.onset().startsWith("e");
        boolean nextStartsWithI = next.onset().startsWith("i");
        boolean nextStartsWithO = next.onset().startsWith("o");
        boolean nextStartsWithU = next.onset().startsWith("u");
//        boolean prevEndsWithL = prev.coda().equals("l");
//        boolean nextStartsWithL = next.onset().equals("l");
//        boolean nextOnsetContainsL = next.onset().contains("l");
        boolean nextStartsWithVocal = nextStartsWithA || nextStartsWithE || nextStartsWithI || nextStartsWithO || nextStartsWithU;

        boolean nextAndPreviousTooSimilar = !next.onset().equals(prev.onset()) || ThreadLocalRandom.current().nextDouble() < 0.2;
        boolean preventDoubleVocals = !(prevEndsEmpty && nextStartsWithVocal);
//        boolean noIlElAfterL = !prevEndsWithL || nextStartsWithL || !nextOnsetContainsL;
        boolean avoidSandwich =
                !(prev.coda().length() == 1
                        && next.onset().length() == 1
                        && prev.coda().equals(next.onset()));
        return preventDoubleVocals && avoidSandwich && nextAndPreviousTooSimilar;
    }

    @Override
    public boolean canFollowInSyllable(String previous, String next) {
        boolean previousIsQ = previous.equals("qu");
        boolean previousStartsWithL = previous.startsWith("l");
        boolean nextIsU = next.equals("u");
        boolean previousContainsR = previous.contains("r");
        boolean nextIsR = next.equals("r");
        boolean nextIsL = next.equals("l");

        boolean noLol = !previousStartsWithL || !nextIsL;
        boolean noUAfterQu = !previousIsQ || !nextIsU;
        boolean noRAfterPreviousR = !(previousContainsR && nextIsR);
        return noUAfterQu && noRAfterPreviousR && noLol;
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

//    private static final String[] townPart2 = {"ga", "ta", "ti", "te", "na", "ge", "ren", "da", "tar", "ni", "ra", "vel", "zar", "si", "pen", "go", "lar", "ver", "mar", "dor", "len", "nor", "bel", "ran",
//            "lin", "min", "tor", "dan", "gan",
//            "sel", "var", "tir", "mir", "dor",
//            "nel", "vor", "kar", "dar"};
//    private static final String[] townPart3 = {"more", "vore", "gol", "dol", "na", "lon", "ia", "ma", "rin", "ta", "ra", "nal", "lin", "nor", "tel", "rel", "del", "el", "ria", "vel", "al", "gal", "noh"};

//        String[] uglyParts = {
//                "aa", "ay", "ee", "ey", "ii", "iy", "oo", "oy", "uu", "uy", "ya", "ye", "yi", "yo", "yu", "yy",
//                "xb", "xc", "xd", "xf", "xg", "xh", "xj", "xk", "xl", "xm", "xn", "xp", "xq", "xr", "xs", "xt", "xv", "xw", "xx", "xz",
//                "bx", "cx", "dx", "fx", "gx", "hx", "jx", "kx", "lx", "mx", "nx", "px", "qx", "rx", "sx", "tx", "vx", "wx", "zx",
//                "bc", "bf", "bj", "bk", "bm", "bp", "bs", "bt", "bw",
//                "cb", "cd", "cf", "cg", "cj", "cp", "cs", "cw", "cz",
//                "dc", "df", "dj", "dl", "dp", "dq", "dw", "dz",
//                "fb", "fc", "fd", "fg", "fh", "fj", "fk", "fl", "fm", "fn", "fp", "fq", "fs", "ft", "fv", "fw", "fz",
//                "gc", "gf", "gj", "gk", "gl", "gp", "gq", "gs", "gt", "gw",
//                "hb", "hc", "hd", "hf", "hg", "hh", "hj", "hk", "hl", "hm", "hn", "hp", "hq", "hr", "hs", "ht", "hv", "hw", "hz",
//                "jb", "jc", "jd", "jf", "jg", "jh", "jj", "jk", "jl", "jm", "jn", "jp", "jq", "jr", "js", "jt", "jv", "jw", "jz",
//                "kb", "kc", "kd", "kf", "kg", "kh", "kj", "km", "kp", "kq", "kr", "ks", "kt", "kv", "kw", "kz",
//                "lb", "lc", "ld", "lf", "lg", "lh", "lj", "lk", "lm", "ln", "lp", "lq", "lr", "ls", "lt", "lw",
//                "mc", "mf", "mj", "ml", "mp", "mq", "ms", "mw",
//                "nc", "nf", "nj", "nl", "nq", "nw",
//                "pb", "pc", "pd", "pf", "pg", "ph", "pj", "pk", "pl", "pm", "pn", "pq", "pr", "ps", "pt", "pv", "pw", "pz",
//                "qb", "qc", "qd", "qf", "qg", "qh", "qj", "qk", "ql", "qm", "qn", "qp", "qq", "qr", "qs", "qt", "qv", "qw", "qz",
//                "rf", "rj", "rl", "rp", "rq", "rt", "rw",
//                "sb", "sc", "sd", "sf", "sg", "sj", "sl", "sq", "sr", "sw", "sz",
//                "tb", "tc", "td", "tf", "tg", "tj", "tk", "tp", "tq", "ts", "tw", "tz",
//                "vb", "vc", "vd", "vf", "vh", "vj", "vk", "vl", "vm", "vp", "vq", "vr", "vs", "vt", "vw", "vz",
//                "wb", "wc", "wd", "wf", "wg", "wh", "wj", "wk", "wl", "wm", "wn", "wp", "wq", "wr", "ws", "wt", "wv", "ww", "wz",
//                "zc", "zf", "zj", "zl", "zp", "zq", "zw"
//        };
//        if (fullName.matches(".*[^aeiouy]{3,}.*")) {
//            return false;
//        }
//        for (String part : uglyParts) {
//            if (fullName.contains(part)) {
//                System.out.println("Rejected: " + fullName + " because of: " + part);
//                return false;
//            }
//        }
//        return true;
//    }
//
//    private String getRandom(ArrayList<String> array) {
//        return array.get(ThreadLocalRandom.current().nextInt(array.size()));
//    }

