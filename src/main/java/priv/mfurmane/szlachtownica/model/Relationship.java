package priv.mfurmane.szlachtownica.model;

public class Relationship {

    protected Relationship() {

    }

    enum RelationType {
        LEGAL,      // ślub
        AFFAIR,     // romans
        FWB,        // friends with benefits
        ONS,        // one-night stand
        ARRANGED,   // ślub polityczny (opcjonalnie)
    }
    enum EndType {
        DEATH,
        DIVORCE,
        ABANDONED,
        EXECUTED,
        ANNULLED // np. unieważnienie ślubu kościelnego
    }
}
