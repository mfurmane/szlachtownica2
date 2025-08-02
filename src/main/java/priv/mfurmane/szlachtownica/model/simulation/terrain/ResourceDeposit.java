package priv.mfurmane.szlachtownica.model.simulation.terrain;

public enum ResourceDeposit {
    MICRO(10),
    PETITE(40),
    TINY(70),
    VERY_SMALL(100),
    SMALL(400),
    RATHER_SMALL(700),
    MEDIUM(1000),
    BIG(4000),
    VERY_BIG(7000),
    EXTRA_BIG(10000),
    LARGE(40000),
    VERY_LARGE(70000),
    HUGE(100000),
    VERY_HUGE(400000),
    GIANT(700000),
    ENORMOUS(1000000),
    TITANIC(4000000),
    COLOSSAL(7000000),
    IMPOSSIBLE(10000000);

    private final int averageTons;

    ResourceDeposit(int averageTons) {
        this.averageTons = averageTons;
    }

    public int getAverageTons() {
        return averageTons;
    }
}
