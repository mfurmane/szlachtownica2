package priv.mfurmane.szlachtownica.model;

import priv.mfurmane.szlachtownica.engine.naming.model.*;
import priv.mfurmane.szlachtownica.engine.naming.nereneth.NerenethPhonotactic;
import priv.mfurmane.szlachtownica.engine.naming.saravera.SaraveraPhonotactic;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class ElvenNameGenerator {

    private static final NerenethPhonotactic phonotactic = new NerenethPhonotactic();
    public static final Map<Integer, Double> nerenethSyllablesCountMap = Map.of(2, 1.0, 3, 1.0);

    public static String getSurname() {
        return phonotactic.generateCapitalizedWord(WordType.PERSON, nerenethSyllablesCountMap);
    }

    public static String getMaleName() {
        return phonotactic.generateCapitalizedWordWithHardCoda(WordType.PERSON, nerenethSyllablesCountMap);
    }

    public static String getFemaleName() {
        return phonotactic.generateCapitalizedWordWithHardCoda(WordType.PERSON, nerenethSyllablesCountMap) + "a";
    }

}
