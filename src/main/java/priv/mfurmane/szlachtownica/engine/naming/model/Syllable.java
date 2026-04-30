package priv.mfurmane.szlachtownica.engine.naming.model;

public record Syllable(String onset, String nucleus, String coda) {
    public String render() {
        return onset + nucleus + coda;
    }
}
