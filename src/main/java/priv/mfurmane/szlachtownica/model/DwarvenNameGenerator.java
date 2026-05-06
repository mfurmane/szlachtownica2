package priv.mfurmane.szlachtownica.model;

import priv.mfurmane.szlachtownica.engine.naming.dwarven.DwarvenPhonotactic;
import priv.mfurmane.szlachtownica.engine.naming.model.Phonotactic;
import priv.mfurmane.szlachtownica.engine.naming.model.WordType;
import priv.mfurmane.szlachtownica.engine.naming.nereneth.NerenethPhonotactic;

import java.util.Map;

public class DwarvenNameGenerator {

    private static final Phonotactic phonotactic = new DwarvenPhonotactic();
    public static final Map<Integer, Double> nameMap = Map.of(2, 1.0, 3, 1.0);
    public static final Map<Integer, Double> surnameMap = Map.of(1, 0.2, 2, 4.0, 3, 0.0);

    public static String getSurname() {
        //TODO mechanizm z Runiczną Piąchą
        return "Twoja Dupa";
//        return phonotactic.generateCapitalizedWordWithHardCoda(WordType.PERSON, surnameMap);
    }

    public static String getMaleName() {
        return phonotactic.generateCapitalizedWordWithHardCoda(WordType.PERSON, nameMap);
    }

    public static String getFemaleName() {
        return phonotactic.generateCapitalizedWordWithHardCoda(WordType.PERSON, nameMap) + "a";
    }

    public static String getMaleFather(CharacterTrait trait) {
        return phonotactic.generateCapitalizedWordWithHardCoda(WordType.PERSON, nameMap) + "son";
    }

    public static String getFemaleFather(CharacterTrait trait) {
        return phonotactic.generateCapitalizedWordWithHardCoda(WordType.PERSON, nameMap) + "detter";
    }

}
