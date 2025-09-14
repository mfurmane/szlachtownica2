package priv.mfurmane.szlachtownica.model.simulation.population;

public enum SettlersAttitude {

    HARMONIOUS (2.2, 1.2, 1.2, 1.5, 1.5, 1.6, 1.4, 0.25, 2.0),
    COOPERATIVE (2.0, 1.2, 1.2, 1.8, 1.8, 1.4, 1.6, 0.5, 1.4),
    CURIOUS (1.0, 2.0, 2.5, 1.0, 1.0, 1.8, 1.2, 0.7, 1.1),
    FRIENDLY (1.4, 2.0, 1.2, 2.0, 2.0, 1.2, 1.1, 0.7, 1.3),
    OPTIMISTIC (1.2, 1.5, 2.0, 1.7, 1.7, 1.3, 1.2, 0.95, 1.2),
    HARDWORKING (1.0, 1.2, 1.7, 1.0, 1.0, 2.0, 2.0, 0.9, 1.1),
    INWARD (2.0, 1.0, 1.0, 0.8, 0.8, 1.3, 1.2, 0.6, 1.4),
    IMPULSIVE (0.8, 1.2, 1.8, 0.9, 0.9, 1.3, 1.3, 1.5, 0.6),
    ROUGH (1.4, 0.8, 0.9, 0.7, 0.7, 1.2, 1.7, 0.7, 1.2),
    STOIC (1.0, 0.8, 1.0, 1.0, 1.0, 1.1, 1.0, 0.7, 1.4),
    NEUTRAL (1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0),
    ISOLATED (2.0, 0.9, 0.8, 0.5, 0.5, 0.8, 1.4, 0.8, 0.9),
    SUBMISSIVE (0.8, 0.8, 1.2, 1.2, 1.2, 0.6, 1.2, 2.0, 1.7),
    UNSTABLE (0.75, 0.9, 1.2, 0.9, 0.9, 0.9, 0.9, 1.5, 0.8),
    FANATICAL (0.9, 0.9, 1.1, 0.8, 0.8, 1.0, 0.8, 2.5, 1.0),
    MELANCHOLIC (1.1, 0.9, 0.8, 0.9, 0.9, 0.7, 0.8, 1.0, 1.3),
    ILL_TEMPERED (0.7, 0.7, 0.9, 0.7, 0.7, 0.8, 1.1, 2.0, 0.7),
    WARLIKE (0.5, 0.9, 1.2, 0.4, 0.4, 0.75, 0.8, 2.5, 0.5),
    PARANOID (0.4, 0.6, 0.1, 0.7, 0.7, 0.7, 0.9, 2.0, 0.8),
    ENVIOUS (0.1, 0.2, 0.9, 0.3, 0.3, 0.3, 0.8, 2.5, 0.5),
    APATHETIC (0.9, 0.4, 0.4, 0.9, 0.9, 0.5, 0.5, 2.0, 1.0);

    private final Double localLoyalty;
    private final Double culturalEvolution;
    private final Double technologyAdoption;
    private final Double migrationAttractiveness;
    private final Double tradeAttractiveness;
    private final Double expanse;
    private final Double productionSpeed;
    private final Double instability;
    private final Double order;

    SettlersAttitude(Double localLoyalty, Double culturalEvolution, Double technologyAdoption, Double migrationAttractiveness, Double tradeAttractiveness, Double expanse, Double productionSpeed, Double instability, Double order) {
        this.localLoyalty = localLoyalty;
        this.culturalEvolution = culturalEvolution;
        this.technologyAdoption = technologyAdoption;
        this.migrationAttractiveness = migrationAttractiveness;
        this.tradeAttractiveness = tradeAttractiveness;
        this.expanse = expanse;
        this.productionSpeed = productionSpeed;
        this.instability = instability;
        this.order = order;
    }

    public Double getLocalLoyalty() {
        return localLoyalty;
    }

    public Double getCulturalEvolution() {
        return culturalEvolution;
    }

    public Double getTechnologyAdoption() {
        return technologyAdoption;
    }

    public Double getMigrationAttractiveness() {
        return migrationAttractiveness;
    }

    public Double getTradeAttractiveness() {
        return tradeAttractiveness;
    }

    public Double getExpanse() {
        return expanse;
    }

    public Double getProductionSpeed() {
        return productionSpeed;
    }

    public Double getInstability() {
        return instability;
    }

    public Double getOrder() {
        return order;
    }
}
