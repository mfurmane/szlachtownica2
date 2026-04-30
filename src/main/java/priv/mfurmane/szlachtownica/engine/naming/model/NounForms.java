package priv.mfurmane.szlachtownica.engine.naming.model;

public class NounForms {
    String singular;   // step
    String plural;     // stepy
    WordGender gender;

    public NounForms(String singular, String plural) {
        this.singular = singular;
        this.plural = plural;
    }

    public static NounForms createAy(String base) {
        return new NounForms(base + "a", base + "y");
    }

    public static NounForms createAi(String base) {
        return new NounForms(base + "a", base + "i");
    }

    public static NounForms createTa(String base) {
        return new NounForms(base + "ta", base + "ty");
    }

    public static NounForms createEk(String base) {
        return new NounForms(base + "ek", base + "ki");
    }

    public static NounForms createE(String base) {
        return new NounForms(base + "e", base + "a");
    }

    public static NounForms createK(String base) {
        return new NounForms(base + "k", base + "ki");
    }

    public static NounForms createNie(String base) {
        return new NounForms(base + "ń", base + "nie");
    }

    public static NounForms createO(String base) {
        return new NounForms(base + "o", base + "a");
    }

    public static NounForms createEmptyY(String base) {
        return new NounForms(base, base + "y");
    }

    public static NounForms createEc(String base) {
        return new NounForms(base + "ec", base + "cy");
    }
}
