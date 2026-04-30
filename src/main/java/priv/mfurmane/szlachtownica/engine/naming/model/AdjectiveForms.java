package priv.mfurmane.szlachtownica.engine.naming.model;

public class AdjectiveForms {
    String masculine;  // stepowy
    String feminine;   // stepowa
    String neutral;     // stepowe
    String pluralMasculine;     // stepowi
    String pluralFeminine;     // stepowe

    public AdjectiveForms(String masculine, String feminine, String neutral, String pluralMasculine, String pluralFeminine) {
        this.masculine = masculine;
        this.feminine = feminine;
        this.neutral = neutral;
        this.pluralMasculine = pluralMasculine;
        this.pluralFeminine = pluralFeminine;
    }

    public static AdjectiveForms createOwy(String base) {
        return new AdjectiveForms(base + "owy", base + "owa", base + "owe", base + "owi", base + "owe");
    }

    public static AdjectiveForms createNy(String base) {
        return new AdjectiveForms(base + "ny", base + "na", base + "ne", base + "ni", base + "ne");
    }

    public static AdjectiveForms createCy(String base) {
        return new AdjectiveForms(base + "cy", base + "ca", base + "ce", base + "cy", base + "ce");
    }

    public static AdjectiveForms createI(String base) {
        return new AdjectiveForms(base + "i", base + "ia", base + "ie", base + "i", base + "ie");
    }

    public static AdjectiveForms createCzy(String base) {
        return new AdjectiveForms(base + "czy", base + "cza", base + "cze", base + "czy", base + "cze");
    }

    public static AdjectiveForms createNski(String base) {
        return new AdjectiveForms(base + "ński", base + "ńska", base + "ńskie", base + "ńscy", base + "ńskie");
    }

    public static AdjectiveForms createNny(String base) {
        return new AdjectiveForms(base + "nny", base + "nna", base + "nne", base + "nni", base + "nne");
    }

    public static AdjectiveForms createEcki(String base) {
        return new AdjectiveForms(base + "ecki", base + "ecka", base + "eckie", base + "eccy", base + "eckie");
    }
}
