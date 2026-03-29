package priv.mfurmane.szlachtownica.model.main.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.locationtech.jts.geom.Polygon;
import priv.mfurmane.szlachtownica.model.main.ModelRegion;
import priv.mfurmane.szlachtownica.model.simulation.terrain.*;

@Entity
@Table(name = "region")
public class EntityRegion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JdbcTypeCode(SqlTypes.GEOMETRY)
    @Column(name = "geom", columnDefinition = "geometry(Polygon, 4326)") // <- PostGIS
    private Polygon area;

    @ManyToOne
    @JoinColumn(name = "sub_province_id")
    private EntitySubProvince subProvince;

    @Column(name = "humidity")
    @Enumerated(EnumType.STRING)
    private Humidity humidity;
    @Column(name = "climate")
    @Enumerated(EnumType.STRING)
    private Climate climate;
    @Column(name = "terrain_shape")
    @Enumerated(EnumType.STRING)
    private TerrainShape terrainShape;
    @Column(name = "soil_type")
    @Enumerated(EnumType.STRING)
    private SoilType soilType;
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private RegionType type;
    @Column(name = "enchant")
    @Enumerated(EnumType.STRING)
    private EnchantType enchant;
    @Column(name = "fertility")
    private Double fertility;
    @Column(name = "efficiency")
    private Double efficiency;
    @Column(name = "planting_easiness")
    private Double plantingEasiness;
    @Column(name = "farming_easiness")
    private Double farmingEasiness;
    @Column(name = "health")
    private Double health;
    @Column(name = "wind_of_change")
    private Double windOfChange;
    @Column(name = "expansion")
    private Double expansion;
    @Column(name = "attitude")
    private Double attitude;
    @Column(name = "stability")
    private Double stability;
    @Column(name = "wood_richness")
    private Integer woodRichness;
    @Column(name = "development_level")
    private Integer developmentLevel;
    @Column(name = "enchantment_level")
    private Integer enchantmentLevel;

    public EntityRegion() {
    }

    public EntityRegion(ModelRegion modelRegion) {
        this.area = modelRegion.getArea();
        this.humidity = modelRegion.getHumidity();
        this.climate = modelRegion.getClimate();
        this.terrainShape = modelRegion.getTerrainShape();
        this.soilType = modelRegion.getSoilType();
        this.type = modelRegion.getType();
        this.enchant = modelRegion.getEnchant();
        this.fertility = modelRegion.getFertility();
        this.efficiency = modelRegion.getEfficiency();
        this.plantingEasiness = modelRegion.getPlantingEasiness();
        this.farmingEasiness = modelRegion.getFarmingEasiness();
        this.health = modelRegion.getHealth();
        this.windOfChange = modelRegion.getWindOfChange();
        this.expansion = modelRegion.getExpansion();
        this.attitude = modelRegion.getAttitude();
        this.stability = modelRegion.getStability();
        this.woodRichness = modelRegion.getWoodRichness();
        this.developmentLevel = modelRegion.getDevelopmentLevel();
        this.enchantmentLevel = modelRegion.getEnchantmentLevel();
    }

    @Column(name = "coast")


//    private final List<Long> places = new ArrayList<>();

//    private Boolean coast;
//    //    private final Double naturalReachness;
//    private final Map<TerrainResource, Integer> naturalResources = new HashMap<>();
//    private final List<TerrainCharacteristic> characteristics = new ArrayList<>();
//    private final Map<ProductionType, Integer> production = new HashMap<>();
//    private final Map<ImportNeed, Integer> importNeeded = new HashMap<>();
//    private final List<Population> populations = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public EntityRegion setId(Long id) {
        this.id = id;
        return this;
    }

    public Polygon getArea() {
        return area;
    }

    public EntityRegion setArea(Polygon area) {
        this.area = area;
        return this;
    }

    public EntitySubProvince getSubProvince() {
        return subProvince;
    }

    public EntityRegion setSubProvince(EntitySubProvince subProvince) {
        this.subProvince = subProvince;
        return this;
    }

    public Humidity getHumidity() {
        return humidity;
    }

    public EntityRegion setHumidity(Humidity humidity) {
        this.humidity = humidity;
        return this;
    }

    public Climate getClimate() {
        return climate;
    }

    public EntityRegion setClimate(Climate climate) {
        this.climate = climate;
        return this;
    }

    public TerrainShape getTerrainShape() {
        return terrainShape;
    }

    public EntityRegion setTerrainShape(TerrainShape terrainShape) {
        this.terrainShape = terrainShape;
        return this;
    }

    public SoilType getSoilType() {
        return soilType;
    }

    public EntityRegion setSoilType(SoilType soilType) {
        this.soilType = soilType;
        return this;
    }

    public RegionType getType() {
        return type;
    }

    public EntityRegion setType(RegionType type) {
        this.type = type;
        return this;
    }

    public EnchantType getEnchant() {
        return enchant;
    }

    public EntityRegion setEnchant(EnchantType enchant) {
        this.enchant = enchant;
        return this;
    }

    public Double getFertility() {
        return fertility;
    }

    public EntityRegion setFertility(Double fertility) {
        this.fertility = fertility;
        return this;
    }

    public Double getEfficiency() {
        return efficiency;
    }

    public EntityRegion setEfficiency(Double efficiency) {
        this.efficiency = efficiency;
        return this;
    }

    public Double getPlantingEasiness() {
        return plantingEasiness;
    }

    public EntityRegion setPlantingEasiness(Double plantingEasiness) {
        this.plantingEasiness = plantingEasiness;
        return this;
    }

    public Double getFarmingEasiness() {
        return farmingEasiness;
    }

    public EntityRegion setFarmingEasiness(Double farmingEasiness) {
        this.farmingEasiness = farmingEasiness;
        return this;
    }

    public Double getHealth() {
        return health;
    }

    public EntityRegion setHealth(Double health) {
        this.health = health;
        return this;
    }

    public Double getWindOfChange() {
        return windOfChange;
    }

    public EntityRegion setWindOfChange(Double windOfChange) {
        this.windOfChange = windOfChange;
        return this;
    }

    public Double getExpansion() {
        return expansion;
    }

    public EntityRegion setExpansion(Double expansion) {
        this.expansion = expansion;
        return this;
    }

    public Double getAttitude() {
        return attitude;
    }

    public EntityRegion setAttitude(Double attitude) {
        this.attitude = attitude;
        return this;
    }

    public Double getStability() {
        return stability;
    }

    public EntityRegion setStability(Double stability) {
        this.stability = stability;
        return this;
    }

    public Integer getWoodRichness() {
        return woodRichness;
    }

    public EntityRegion setWoodRichness(Integer woodRichness) {
        this.woodRichness = woodRichness;
        return this;
    }

    public Integer getDevelopmentLevel() {
        return developmentLevel;
    }

    public EntityRegion setDevelopmentLevel(Integer developmentLevel) {
        this.developmentLevel = developmentLevel;
        return this;
    }

    public Integer getEnchantmentLevel() {
        return enchantmentLevel;
    }

    public EntityRegion setEnchantmentLevel(Integer enchantmentLevel) {
        this.enchantmentLevel = enchantmentLevel;
        return this;
    }
}
