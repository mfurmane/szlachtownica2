package priv.mfurmane.szlachtownica.model.main;

import org.locationtech.jts.geom.Point;
import priv.mfurmane.szlachtownica.model.simulation.terrain.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModelPlace {
    private Long id;
    private String name;
    private final List<PlaceCharacteristic> placeCharacteristics = new ArrayList<>();
    private Point location;
//    private Polygon area;

    private Integer populationLimit;
    private final Populatable populatable = new Populatable();
    private final Map<ProductionType, Integer> production = new HashMap<>();
    private final Map<ExploitationType, Integer> exploitation = new HashMap<>();
    private final Map<ImportNeed, Integer> importNeeded = new HashMap<>();

    private PlaceState state;
    private PlaceType type;
    private Integer level;
    private Integer minLevel;
    private Integer maxLevel;
    private Double culturalEvolution;
    private Double technologyAdoption;
    private Double migrationAttractiveness;
    private Double tradeAttractiveness;
    private Double comfort;
    private Double order;
    private Double safety;

    public Long getId() {
        return id;
    }

    public ModelPlace setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ModelPlace setName(String name) {
        this.name = name;
        return this;
    }

    public PlaceType getType() {
        return type;
    }

    public ModelPlace setType(PlaceType type) {
        this.type = type;
        return this;
    }

    public PlaceState getState() {
        return state;
    }

    public ModelPlace setState(PlaceState state) {
        this.state = state;
        return this;
    }

    public List<PlaceCharacteristic> getPlaceCharacteristics() {
        return placeCharacteristics;
    }

    public Map<ProductionType, Integer> getProduction() {
        return production;
    }

    public Map<ExploitationType, Integer> getExploitation() {
        return exploitation;
    }

    public Map<ImportNeed, Integer> getImportNeeded() {
        return importNeeded;
    }

    public Populatable getPopulatable() {
        return populatable;
    }

    public Integer getPopulationLimit() {
        return populationLimit;
    }

    public ModelPlace setPopulationLimit(Integer populationLimit) {
        this.populationLimit = populationLimit;
        return this;
    }

    public Integer getLevel() {
        return level;
    }

    public ModelPlace setLevel(Integer level) {
        this.level = level;
        return this;
    }

    public Integer getMinLevel() {
        return minLevel;
    }

    public ModelPlace setMinLevel(Integer minLevel) {
        this.minLevel = minLevel;
        return this;
    }

    public Integer getMaxLevel() {
        return maxLevel;
    }

    public ModelPlace setMaxLevel(Integer maxLevel) {
        this.maxLevel = maxLevel;
        return this;
    }

    public Double getCulturalEvolution() {
        return culturalEvolution;
    }

    public ModelPlace setCulturalEvolution(Double culturalEvolution) {
        this.culturalEvolution = culturalEvolution;
        return this;
    }

    public Double getTechnologyAdoption() {
        return technologyAdoption;
    }

    public ModelPlace setTechnologyAdoption(Double technologyAdoption) {
        this.technologyAdoption = technologyAdoption;
        return this;
    }

    public Double getMigrationAttractiveness() {
        return migrationAttractiveness;
    }

    public ModelPlace setMigrationAttractiveness(Double migrationAttractiveness) {
        this.migrationAttractiveness = migrationAttractiveness;
        return this;
    }

    public Double getTradeAttractiveness() {
        return tradeAttractiveness;
    }

    public ModelPlace setTradeAttractiveness(Double tradeAttractiveness) {
        this.tradeAttractiveness = tradeAttractiveness;
        return this;
    }

    public Double getComfort() {
        return comfort;
    }

    public ModelPlace setComfort(Double comfort) {
        this.comfort = comfort;
        return this;
    }

    public Double getOrder() {
        return order;
    }

    public ModelPlace setOrder(Double order) {
        this.order = order;
        return this;
    }

    public Double getSafety() {
        return safety;
    }

    public ModelPlace setSafety(Double safety) {
        this.safety = safety;
        return this;
    }

    public Point getLocation() {
        return location;
    }

    public ModelPlace setLocation(Point location) {
        this.location = location;
        return this;
    }
}
