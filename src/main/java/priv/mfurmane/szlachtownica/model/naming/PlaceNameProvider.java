package priv.mfurmane.szlachtownica.model.naming;

import morfologik.stemming.Dictionary;
import morfologik.stemming.DictionaryLookup;
import morfologik.stemming.WordData;
import morfologik.stemming.polish.PolishStemmer;
import org.springframework.util.StringUtils;
import priv.mfurmane.szlachtownica.engine.MainEngine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlaceNameProvider {
    private MainEngine engine;
    public void setEngine(MainEngine engine) {
        this.engine = engine;
    }

    private static final Random rand = new Random();
    private static final String[] undefinedParts = {};
    private static final String[] villageParts = {};
    private final List<WordData> adjectives = new ArrayList<>();
    private DictionaryLookup lookup;
    private final String[] syl1 = {"e", "u", "a"};
    private final String[] syl2 = {"de", "no", "nu", "sa", "ma", "si", "ga", "ge", "ca", "qu", "os", "go", "xa", "is", "ta", "er", "an", "ra", "ve", "as", "na", "re", "vi", "ti", "ha", "ni", "da", "li", "fe"};
    private final String[] syl3 = {"dol", "dur", "gol", "gon", "lon", "del", "lut", "gil", "ria", "nus", "kor", "lin", "nor", "jir", "gal", "dar", "rin", "mir", "ler", "nal", "pen", "rel", "ren", "tel", "wor", "per", "vel", "lar", "tir", "ran", "zel", "tan", "vis", "ver", "van", "sar", "var", "zar"};
    private final String[] syl4 = {"more", "vore"};
    private final String[] forbidden = {"Gilgamore", "Lertavore", "Egerenna", "Verdalon", "Astaria", "Ergondol", "Sartama", "Wornimore", "Korsana", "Zelderin", "Caravista", "Uvarra", "Jirdenal", "Xalivore", "Pernagol", "Isvellin", "Repenvore", "Tantanor", "Durnatel", "Vizarna", "Hasirel", "Naderia", "Quvelmore", "Residel", "Qurena", "Sardena", "Mirnadel", "Nadarvel", "Vangodar", "Lutnaran", "Felarna", "Tirnugal", "Nonusta", "Anverna", "Golveran", "Ostivore"};

    public void prepareDict() throws IOException {
        PolishStemmer stemmer = new PolishStemmer();
        Dictionary dict = stemmer.getDictionary(); // wczytuje słownik z jar-a
        lookup = new DictionaryLookup(dict);
        lookup.iterator().forEachRemaining(word -> {
            if (word.getTag().toString().startsWith("adj")) {
                adjectives.add(word);
            }
        });
    }

    public String getRandomForm(String requiredTag) {
        WordData base = adjectives.get(rand.nextInt(adjectives.size()));
        String stem = base.getStem().toString();

        return lookup.lookup(stem).stream()
                .filter(wd -> wd.getTag().toString().equals(requiredTag))
                .map(wd -> wd.getWord().toString())
                .findFirst()
                .orElse(stem);
    }

//    public static void main(String[] args) throws IOException {
//        SettlementNameProvider provider = new SettlementNameProvider();
//        provider.prepareDict();
//        System.out.println(provider.adjectives.size());
//        provider.adjectives.forEach(adj -> {
//            System.out.println(adj.getStem());
//        });
//        String adj = provider.getRandomForm("adj:sg:nom:m1");
//        System.out.println("Wylosowany przymiotnik: " + adj);
//    }

    public String getCityName() {
        StringBuilder builder = new StringBuilder();
        prefix(builder);
        first(builder);
        second(builder);
        third(builder);
        String predefined = builder.toString();
        return validate(StringUtils.capitalize(predefined));
    }

    public String getLakeName() {
        return "";
    }

    public String getRiverName() {
        return "";
    }

    public String getVillageName() {
//        Dictionary dict = Dictionary.readDefault();
//
//        DictionaryLookup lookup = new DictionaryLookup(dict);
//
//        List<String> adjectives = lookup.getEntries().stream()
//                .filter(e -> e.getTag().contains("adj")) // tylko przymiotniki
//                .map(WordData::getStem)
//                .distinct()
//                .collect(Collectors.toList());
//
//        Random rand = new Random();
//        String randomAdj = adjectives.get(rand.nextInt(adjectives.size()));
//
//        System.out.println(randomAdj);
        return getCityName();
    }

    public String getUpdatedNameIfNeeded(String input) {
        return input.contains(" ") ? getCityName() : input;
    }

    private void prefix(StringBuilder builder) {
        if (rand.nextDouble() < 0.05) {
            single(builder);
        }
    }

    private void first(StringBuilder builder) {
        if (rand.nextDouble() < 0.7) {
            triple(builder);
        } else {
            duble(builder);
        }
    }

    private void second(StringBuilder builder) {
        if (rand.nextDouble() < 0.7) {
            duble(builder);
        } else {
            triple(builder);
        }
    }

    private void third(StringBuilder builder) {
        if (rand.nextDouble() < 0.6) {
            triple(builder);
        } else if (rand.nextDouble() < 0.7) {
            quadruple(builder);
        } else {
            duble(builder);
        }
    }

    private String validate(String input) {
        if (List.of(forbidden).contains(input)) {
            return getCityName();
        }
        return input.replace("ii", "ia");
    }

    private void single(StringBuilder builder) {
        builder.append(syl1[rand.nextInt(syl1.length)]);
    }

    private void duble(StringBuilder builder) {
        builder.append(syl2[rand.nextInt(syl2.length)]);
    }

    private void triple(StringBuilder builder) {
        builder.append(syl3[rand.nextInt(syl3.length)]);
    }

    private void quadruple(StringBuilder builder) {
        builder.append(syl4[rand.nextInt(syl4.length)]);
    }

//    public static void main(String[] args) {
//        SettlementNameProvider provider = new SettlementNameProvider();
//        Set<String> set = new HashSet<>();
//        for (int i = 0; i < 1000; i++) {
//            set.add(provider.getCityName());
//        }
//        System.out.println(set.size());
//        set.forEach(System.out::println);
//    }


}
