package priv.mfurmane.szlachtownica.model.main;

import org.locationtech.jts.geom.Point;
import priv.mfurmane.szlachtownica.model.simulation.population.Population;
import priv.mfurmane.szlachtownica.model.simulation.terrain.PlaceState;

import java.util.ArrayList;
import java.util.List;

public class Populatable {
    private PlaceState state;
    private Integer level;
    private final List<Population> populations = new ArrayList<>();

    private Integer minLevel;
    private Integer maxLevel;
    private Double culturalEvolution;
    private Double technologyAdoption;
    private Double migrationAttractiveness;
    private Double tradeAttractiveness;
    private Double instability;
    private Double comfort;
    private Double order;
    private Double safety;

    public PlaceState getState() {
        return state;
    }

    public Populatable setState(PlaceState state) {
        this.state = state;
        return this;
    }

    public Integer getLevel() {
        return level;
    }

    public Populatable setLevel(Integer level) {
        this.level = level;
        return this;
    }

    public List<Population> getPopulations() {
        return populations;
    }

    public Integer getMinLevel() {
        return minLevel;
    }

    public Populatable setMinLevel(Integer minLevel) {
        this.minLevel = minLevel;
        return this;
    }

    public Integer getMaxLevel() {
        return maxLevel;
    }

    public Populatable setMaxLevel(Integer maxLevel) {
        this.maxLevel = maxLevel;
        return this;
    }

    public Double getCulturalEvolution() {
        return culturalEvolution;
    }

    public Populatable setCulturalEvolution(Double culturalEvolution) {
        this.culturalEvolution = culturalEvolution;
        return this;
    }

    public Double getTechnologyAdoption() {
        return technologyAdoption;
    }

    public Populatable setTechnologyAdoption(Double technologyAdoption) {
        this.technologyAdoption = technologyAdoption;
        return this;
    }

    public Double getMigrationAttractiveness() {
        return migrationAttractiveness;
    }

    public Populatable setMigrationAttractiveness(Double migrationAttractiveness) {
        this.migrationAttractiveness = migrationAttractiveness;
        return this;
    }

    public Double getTradeAttractiveness() {
        return tradeAttractiveness;
    }

    public Populatable setTradeAttractiveness(Double tradeAttractiveness) {
        this.tradeAttractiveness = tradeAttractiveness;
        return this;
    }

    public Double getInstability() {
        return instability;
    }

    public Populatable setInstability(Double instability) {
        this.instability = instability;
        return this;
    }

    public Double getComfort() {
        return comfort;
    }

    public Populatable setComfort(Double comfort) {
        this.comfort = comfort;
        return this;
    }

    public Double getOrder() {
        return order;
    }

    public Populatable setOrder(Double order) {
        this.order = order;
        return this;
    }

    public Double getSafety() {
        return safety;
    }

    public Populatable setSafety(Double safety) {
        this.safety = safety;
        return this;
    }
}
