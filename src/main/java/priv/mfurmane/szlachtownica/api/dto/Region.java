package priv.mfurmane.szlachtownica.api.dto;

import org.wololo.geojson.Geometry;
import priv.mfurmane.szlachtownica.model.main.ModelRegion;
import priv.mfurmane.szlachtownica.model.simulation.population.Population;
import priv.mfurmane.szlachtownica.model.simulation.terrain.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Region {
    private Long id;
    private Geometry area;

    private Humidity humidity;
    private Climate climate;
    private Double fertility;
    private Double efficiency;
    private Double plantingEasiness;
    private Double farmingEasiness;
    private Double health;
    private Double windOfChange;
    private Double expansion;
    private Double attitude;
    private Double stability;
    private Integer woodRichness;
    private Boolean coast;
    private TerrainShape terrainShape;
    private SoilType soilType;
    //    private final Double naturalReachness;
    private final Map<TerrainResource, Integer> naturalResources = new HashMap<>();
    private RegionType type;
    private Integer developmentLevel;
    private EnchantType enchant;
    private Integer enchantmentLevel;
    private List<Place> places = new ArrayList<>();
//    private final List<TerrainCharacteristic> characteristics = new ArrayList<>();
//    private final Map<ProductionType, Integer> production = new HashMap<>();
//    private final Map<ImportNeed, Integer> importNeeded = new HashMap<>();
//    private final List<Population> populations = new ArrayList<>();

    public Region() {}

    public Region(ModelRegion reg) {
        this.id = reg.getId();
        this.humidity = reg.getHumidity();
        this.climate = reg.getClimate();
        this.fertility = reg.getFertility();
        this.efficiency = reg.getEfficiency();
        this.plantingEasiness = reg.getPlantingEasiness();
        this.farmingEasiness = reg.getFarmingEasiness();
        this.health = reg.getHealth();
        this.windOfChange = reg.getWindOfChange();
        this.expansion = reg.getExpansion();
        this.attitude = reg.getAttitude();
        this.stability = reg.getStability();
        this.woodRichness = reg.getWoodRichness();
        this.coast = reg.getCoast();
        this.terrainShape = reg.getTerrainShape();
        this.soilType = reg.getSoilType();
        this.type = reg.getType();
        this.developmentLevel = reg.getDevelopmentLevel();
        this.enchant = reg.getEnchant();
        this.enchantmentLevel = reg.getEnchantmentLevel();
    }

    public List<Place> getPlaces() {
        return places;
    }

    public Region setPlaces(List<Place> places) {
        this.places = places;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Region setId(Long id) {
        this.id = id;
        return this;
    }

    public Geometry getArea() {
        return area;
    }

    public Region setArea(Geometry area) {
        this.area = area;
        return this;
    }

    public Humidity getHumidity() {
        return humidity;
    }

    public Region setHumidity(Humidity humidity) {
        this.humidity = humidity;
        return this;
    }

    public Climate getClimate() {
        return climate;
    }

    public Region setClimate(Climate climate) {
        this.climate = climate;
        return this;
    }

    public Double getFertility() {
        return fertility;
    }

    public Region setFertility(Double fertility) {
        this.fertility = fertility;
        return this;
    }

    public Double getEfficiency() {
        return efficiency;
    }

    public Region setEfficiency(Double efficiency) {
        this.efficiency = efficiency;
        return this;
    }

    public Double getPlantingEasiness() {
        return plantingEasiness;
    }

    public Region setPlantingEasiness(Double plantingEasiness) {
        this.plantingEasiness = plantingEasiness;
        return this;
    }

    public Double getFarmingEasiness() {
        return farmingEasiness;
    }

    public Region setFarmingEasiness(Double farmingEasiness) {
        this.farmingEasiness = farmingEasiness;
        return this;
    }

    public Double getHealth() {
        return health;
    }

    public Region setHealth(Double health) {
        this.health = health;
        return this;
    }

    public Double getWindOfChange() {
        return windOfChange;
    }

    public Region setWindOfChange(Double windOfChange) {
        this.windOfChange = windOfChange;
        return this;
    }

    public Double getExpansion() {
        return expansion;
    }

    public Region setExpansion(Double expansion) {
        this.expansion = expansion;
        return this;
    }

    public Double getAttitude() {
        return attitude;
    }

    public Region setAttitude(Double attitude) {
        this.attitude = attitude;
        return this;
    }

    public Double getStability() {
        return stability;
    }

    public Region setStability(Double stability) {
        this.stability = stability;
        return this;
    }

    public Integer getWoodRichness() {
        return woodRichness;
    }

    public Region setWoodRichness(Integer woodRichness) {
        this.woodRichness = woodRichness;
        return this;
    }

    public Boolean getCoast() {
        return coast;
    }

    public Region setCoast(Boolean coast) {
        this.coast = coast;
        return this;
    }

    public TerrainShape getTerrainShape() {
        return terrainShape;
    }

    public Region setTerrainShape(TerrainShape terrainShape) {
        this.terrainShape = terrainShape;
        return this;
    }

    public SoilType getSoilType() {
        return soilType;
    }

    public Region setSoilType(SoilType soilType) {
        this.soilType = soilType;
        return this;
    }

    public Map<TerrainResource, Integer> getNaturalResources() {
        return naturalResources;
    }

    public RegionType getType() {
        return type;
    }

    public Region setType(RegionType type) {
        this.type = type;
        return this;
    }

    public Integer getDevelopmentLevel() {
        return developmentLevel;
    }

    public Region setDevelopmentLevel(Integer developmentLevel) {
        this.developmentLevel = developmentLevel;
        return this;
    }

    public EnchantType getEnchant() {
        return enchant;
    }

    public Region setEnchant(EnchantType enchant) {
        this.enchant = enchant;
        return this;
    }

    public Integer getEnchantmentLevel() {
        return enchantmentLevel;
    }

    public Region setEnchantmentLevel(Integer enchantmentLevel) {
        this.enchantmentLevel = enchantmentLevel;
        return this;
    }
}
