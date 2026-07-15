package priv.mfurmane.szlachtownica.engine.naming.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordCore {
//    String debugName;
//    String stem;

    String base; // debug / identyfikator
    String singleEnder;
    WordGender selfGender;
    PluralType pluralType;
    WordCategory category = WordCategory.TERRAIN; // domyślnie, nadpisywane przy inicjalizacji

    NounForms noun;
    List<AdjectiveForms> adjective = new ArrayList<>();
    Map<Phonotactic, String> meanings = new HashMap<>();

    public WordCore(String base, WordGender selfGender, PluralType pluralType, NounForms noun, List<AdjectiveForms> adjective, String singleEnder) {
        this.base = base;
        this.selfGender = selfGender;
        this.pluralType = pluralType;
        this.noun = noun;
        this.adjective = adjective;
        this.singleEnder = singleEnder;
    }

    public String getNoun(WordCount count) {
        return switch (count) {
            case SINGULAR -> noun.singular;
            case PLURAL -> noun.plural;
        };
    }

    public String getAdjective(WordCount count, WordGender gender, PluralType pluralType, int variant) {
        return switch (count) {
            case SINGULAR -> switch (gender) {
                case MASCULINE -> adjective.get(Math.min(adjective.size() - 1, variant)).masculine;
                case FEMININE -> adjective.get(Math.min(adjective.size() - 1, variant)).feminine;
                case NEUTRAL -> adjective.get(Math.min(adjective.size() - 1, variant)).neutral;
            };

            case PLURAL -> switch (pluralType) {
                case MASC_PERSONAL -> adjective.get(Math.min(adjective.size() - 1, variant)).pluralMasculine;
                case NON_MASC_PERSONAL -> adjective.get(Math.min(adjective.size() - 1, variant)).pluralFeminine;
            };
        };
    }

    //    public WordCore(String debugName, String stem, WordGender selfGender) {
//        this.debugName = debugName;
//        this.stem = stem;
//        this.selfGender = selfGender;
//    }
//
//    public String getNoun(WordCount count) {
//        switch (count) {
//            case SINGULAR -> {
//                switch (selfGender) {
//                    case FEMININE -> {
//                        return stem + "a";
//                    }
//                    case NEUTRAL -> {
//                        return stem + "o";
//                    }
//                    case MASCULINE -> {
//                        return stem;
//                    }
//                }
//            }
//            case PLURAL -> {
//                switch (selfGender) {
//                    case FEMININE -> {
//                        return stem + "y";
//                    }
//                    case NEUTRAL -> {
//                        return stem + "a";
//                    }
//                    case MASCULINE -> {
//                        return stem + "i";
//                    }
//                }
//            }
//        }
//        return stem;
//    }
//
//    public String getAdjective(WordCount count, WordGender gender) {
//        switch (count) {
//            case SINGULAR -> {
//                switch (gender) {
//                    case FEMININE -> {
//                        return stem + "na";
//                    }
//                    case NEUTRAL -> {
//                        return stem + "ne";
//                    }
//                    case MASCULINE -> {
//                        return stem + "ny";
//                    }
//                }
//            }
//            case PLURAL -> {
//                switch (gender) {
//                    case FEMININE -> {
//                        return stem + "ne";
//                    }
//                    case NEUTRAL -> {
//                        return stem + "ne";
//                    }
//                    case MASCULINE -> {
//                        return stem + "ni";
//                    }
//                }
//            }
//        }
//        return stem + "x";
//    }
//
//    public String getDebugName() {
//        return debugName;
//    }
//
//    public String getStem() {
//        return stem;
//    }
//
//    public WordGender getSelfGender() {
//        return selfGender;
//    }

    public Map<Phonotactic, String> getMeanings() {
        return meanings;
    }

    public boolean hasPlural() {
        return noun.plural != null;
    }

    public String getBase() {
        return base;
    }

    public WordGender getSelfGender() {
        return selfGender;
    }

    public PluralType getPluralType() {
        return pluralType;
    }

    public NounForms getNoun() {
        return noun;
    }

    public List<AdjectiveForms> getAdjectiveForms() {
        return adjective;
    }

    public String getSingleEnder() {
        return singleEnder;
    }

    public WordCategory getCategory() {
        return category;
    }

    public void setCategory(WordCategory category) {
        this.category = category;
    }

    // Gotowe, jednowyrazowe formy ludowe (nazwy typu Żabianka, Zażabie),
    // zapisane w singleEnder jako lista rozdzielona średnikami. Puste -> brak.
    public List<String> getSingleForms() {
        if (singleEnder == null || singleEnder.isBlank()) {
            return List.of();
        }
        return List.of(singleEnder.split(";"));
    }

    public void setSingleEnder(String singleEnder) {
        this.singleEnder = singleEnder;
    }

    //    public String getNoun(WordCount wordCount) {
//        return ;
//    }
}
