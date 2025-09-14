package priv.mfurmane.szlachtownica.model.simulation.population;

import java.util.ArrayList;
import java.util.List;

public class Population {
    private PopulationType type;
    private Affiliation affiliation;
    private final List<SettlersAttitude> attitude = new ArrayList<>();
    private Double loyalty;
    private Double solidarity;
    private Double expanse;
    private Double productionSpeed;
    private Double lawfulness;
    private Double strength;
    private Integer count;

    public PopulationType getType() {
        return type;
    }

    public Population setType(PopulationType type) {
        this.type = type;
        return this;
    }

    public Affiliation getAffiliation() {
        return affiliation;
    }

    public Population setAffiliation(Affiliation affiliation) {
        this.affiliation = affiliation;
        return this;
    }

    public List<SettlersAttitude> getAttitude() {
        return attitude;
    }

    public Double getLoyalty() {
        return loyalty;
    }

    public Population setLoyalty(Double loyalty) {
        this.loyalty = loyalty;
        return this;
    }

    public Double getSolidarity() {
        return solidarity;
    }

    public Population setSolidarity(Double solidarity) {
        this.solidarity = solidarity;
        return this;
    }

    public Double getExpanse() {
        return expanse;
    }

    public Population setExpanse(Double expanse) {
        this.expanse = expanse;
        return this;
    }

    public Double getProductionSpeed() {
        return productionSpeed;
    }

    public Population setProductionSpeed(Double productionSpeed) {
        this.productionSpeed = productionSpeed;
        return this;
    }

    public Double getLawfulness() {
        return lawfulness;
    }

    public Population setLawfulness(Double lawfulness) {
        this.lawfulness = lawfulness;
        return this;
    }

    public Double getStrength() {
        return strength;
    }

    public Population setStrength(Double strength) {
        this.strength = strength;
        return this;
    }

    public Integer getCount() {
        return count;
    }

    public Population setCount(Integer count) {
        this.count = count;
        return this;
    }
}
