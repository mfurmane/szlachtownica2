package priv.mfurmane.szlachtownica.model;

import priv.mfurmane.szlachtownica.engine.naming.model.*;
import priv.mfurmane.szlachtownica.engine.naming.nereneth.NerenethPhonotactic;

import java.util.Map;

public class ElvenNameGenerator {

    public static final Map<CharacterTrait, String> internames = Map.ofEntries(
            Map.entry(CharacterTrait.CHILDHOOD, "Ino"),

            Map.entry(CharacterTrait.MODESTY, "Aio"),
            Map.entry(CharacterTrait.ALTRUISM, "Ana"),
            Map.entry(CharacterTrait.OPTIMISM, "Ani"),
            Map.entry(CharacterTrait.GENTLENESS, "Asi"),
            Map.entry(CharacterTrait.COURAGE, "Ave"),
            Map.entry(CharacterTrait.EGOISM, "Ban"),
            Map.entry(CharacterTrait.SUSPICION, "Coe"),
            Map.entry(CharacterTrait.CALMNESS, "Del"),
            Map.entry(CharacterTrait.COWARDICE, "Doe"),
            Map.entry(CharacterTrait.EARTHLINESS, "Don"),
            Map.entry(CharacterTrait.PRIDE, "Eli"),
            Map.entry(CharacterTrait.ROMANTICISM, "Esi"),
            Map.entry(CharacterTrait.HONESTY, "Isa"),
            Map.entry(CharacterTrait.PASSION, "Ivo"),
            Map.entry(CharacterTrait.RUTHLESSNESS, "Kel"),
            Map.entry(CharacterTrait.TRUST, "Lia"),
            Map.entry(CharacterTrait.DEPRESSION, "Min"),
            Map.entry(CharacterTrait.SERIOUSNESS, "Nia"),
            Map.entry(CharacterTrait.CAUTION, "Ori"),
            Map.entry(CharacterTrait.MYSTICISM, "Sae"),
            Map.entry(CharacterTrait.IMPATIENCE, "San"),
            Map.entry(CharacterTrait.RESPONSIBILITY, "Sel"),
            Map.entry(CharacterTrait.IRRESPONSIBILITY, "Sia"),
            Map.entry(CharacterTrait.IMPULSIVITY, "Sin"),
            Map.entry(CharacterTrait.REALNESS, "Uro"),
            Map.entry(CharacterTrait.PERSEVERANCE, "Vae"),
            Map.entry(CharacterTrait.DECEPTIVENESS, "Vel"),
            Map.entry(CharacterTrait.PLAYFULNESS, "Ven"),
            Map.entry(CharacterTrait.VIRTUE, "Via"),
            Map.entry(CharacterTrait.BLOODTHIRSTINESS, "Vir")
    );

    private static final NerenethPhonotactic phonotactic = new NerenethPhonotactic();
    public static final Map<Integer, Double> nameMap = Map.of(2, 1.0, 3, 1.0);
    public static final Map<Integer, Double> surnameMap = Map.of(1, 0.2, 2, 4.0, 3, 0.0);

    public static String getSurname() {
        return phonotactic.generateCapitalizedWordWithHardCoda(WordType.PERSON, surnameMap);
    }

    public static String getMaleName() {
        return phonotactic.generateCapitalizedWordWithHardCoda(WordType.PERSON, nameMap);
    }

    public static String getFemaleName() {
        return phonotactic.generateCapitalizedWordWithHardCoda(WordType.PERSON, nameMap) + "a";
    }

    public static String getInnername(CharacterTrait trait) {
        return internames.get(trait);
    }

}
