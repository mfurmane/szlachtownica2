package priv.mfurmane.szlachtownica.model.main;

import org.locationtech.jts.geom.Polygon;
import priv.mfurmane.szlachtownica.engine.MainEngine;
import priv.mfurmane.szlachtownica.model.config.ConfigurationRegion;
import priv.mfurmane.szlachtownica.model.simulation.SimulationPlace;
import priv.mfurmane.szlachtownica.model.simulation.SimulationRegion;
import priv.mfurmane.szlachtownica.model.simulation.SimulationSubProvince;
import priv.mfurmane.szlachtownica.model.simulation.population.Population;
import priv.mfurmane.szlachtownica.model.simulation.terrain.*;

import java.util.*;

public class ModelRegion implements Comparable<ModelRegion> {
    public static final Random rand = new Random();
    private Long id;
    private Long subProvinceId;
    private final List<ModelPlace> places = new ArrayList<>();
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
    private Boolean river;
    private Boolean lake;
    private TerrainShape terrainShape;
    private SoilType soilType;
    //    private final Double naturalReachness;
    private final Map<TerrainResource, Integer> naturalResources = new HashMap<>();
    private RegionType type;
    private Integer developmentLevel;
    private EnchantType enchant;
    private Integer enchantmentLevel;
    private final List<TerrainCharacteristic> characteristics = new ArrayList<>();
    //Sumaryczny, wyciągany z miejsc
    private final Map<ProductionType, Integer> production = new HashMap<>();
    //Sumaryczny, wyciągany z miejsc
    private final Map<ExploitationType, Integer> exploitation = new HashMap<>();
    //Sumaryczny, wyciągany z miejsc
    private final Map<ImportNeed, Integer> importNeeded = new HashMap<>();
    //Populacje nieprzywiązane do konkretnych miejsc
    private final List<Population> populations = new ArrayList<>();
    private Polygon area; //not null

    public Polygon getArea() {
        return area;
    }

    public ModelRegion setArea(Polygon area) {
        this.area = area;
        return this;
    }

    public List<Population> getPopulations() {
        return populations;
    }

    public Integer getWoodRichness() {
        return woodRichness;
    }

    public Double getFertility() {
        return fertility;
    }

    public Double getEfficiency() {
        return efficiency;
    }

    public Double getPlantingEasiness() {
        return plantingEasiness;
    }

    public Double getFarmingEasiness() {
        return farmingEasiness;
    }

    public Double getHealth() {
        return health;
    }

    public Double getWindOfChange() {
        return windOfChange;
    }

    public Double getExpansion() {
        return expansion;
    }

    public Double getAttitude() {
        return attitude;
    }

    public Double getStability() {
        return stability;
    }

    public void updateMods() {
        fertility = soilType.getFertility() * humidity.getFertility() * climate.getFertility() * terrainShape.getFertility() * enchant.getFertility(enchantmentLevel);
        plantingEasiness = soilType.getPlantingEasiness() * enchant.getPlantingEasiness(enchantmentLevel);
        farmingEasiness = enchant.getFarmingEasiness(enchantmentLevel); //TODO
        efficiency = enchant.getEfficiency(enchantmentLevel); //TODO
        health = enchant.getHealth(enchantmentLevel); //TODO
        windOfChange = enchant.getWindOfChange(enchantmentLevel); //TODO
        expansion = enchant.getExpansion(enchantmentLevel); //TODO
        attitude = enchant.getAttitude(enchantmentLevel); //TODO
        stability = enchant.getStability(enchantmentLevel); //TODO
    }

    public Boolean getRiver() {
        return river;
    }

    public ModelRegion setRiver(Boolean river) {
        this.river = river;
        return this;
    }

    public Boolean getLake() {
        return lake;
    }

    public ModelRegion setLake(Boolean lake) {
        this.lake = lake;
        return this;
    }

    public Boolean getCoast() {
        return coast;
    }

    public SimulationSubProvince getSubProvinceId() {
        return MainEngine.getInstance().getSubProvinceRegistry().get(subProvinceId);
    }

    public List<ModelPlace> getPlaces() {
        return places;
    }

    public ModelRegion() {}

    public ModelRegion(Builder builder) {
        setGeneralProfile(new GeneralProfile(builder.humidity, builder.climate, builder.terrainShape, builder.enchant));
        woodRichness = builder.woodRichness;
        type = builder.type != null ? builder.type : determineType();
        developmentLevel = builder.developmentLevel;
//        naturalReachness = builder.naturalReachness;
        naturalResources.putAll(builder.naturalResources);
        Arrays.stream(soilType.getResources()).forEach(resource -> {
            if (rand.nextDouble() < resource.getAvailability()) {
                naturalResources.merge(resource, getAverageTons(resource, calculateProportions(builder.naturalReachness)), Integer::sum);
            }
        });
//        places.addAll(builder.startCities);
        boolean hasLake = false;
        boolean hasRiver = false;
        for (int i = 0; i < builder.lakesRichness; i++) {
            if (rand.nextDouble() < builder.lakesRichness / 20.0) {
//                places.add(ModelPlace.newLake());
                hasLake = true;
            }
        }
        for (int i = 0; i < builder.riversRichness; i++) {
            if (rand.nextDouble() < builder.riversRichness / 20.0) {
//                places.add(ModelPlace.newRiver());
                hasRiver = true;
            }
        }
        if (hasLake) characteristics.add(TerrainCharacteristic.LAKES);
        if (hasRiver) characteristics.add(TerrainCharacteristic.RIVERS);
        defineVillages();
    }

    public record GeneralProfile(Humidity humidity, Climate climate, TerrainShape terrainShape, EnchantType enchant) {}

    public ModelRegion setGeneralProfile(GeneralProfile profile) {
        this.humidity = profile.humidity;
        this.climate = profile.climate;
        this.terrainShape = profile.terrainShape;
        this.soilType = determineSoilType();
        this.enchant = profile.enchant;
        if (this.enchant == null) {
            this.enchant = EnchantType.NONE;
        }
        this.enchantmentLevel = chooseEnchantmentLevel(this.enchant);
        updateMods();
        return this;
    }

    private void defineVillages() {
        //TODO zrobić w pętli
//        places.add(ModelPlace.newVillage());
        //TODO czy robić ewentualne osady dedykowane rodzinom i koronie?
        Long groupId = null;
//        places.add(ModelPlace.newVillage(PopulationType.SETTLERS, AffiliationType.FAMILY, groupId));
//        places.add(ModelPlace.newVillage(PopulationType.SETTLERS, AffiliationType.CROWN, groupId));
        //TODO uwarunkować barbarzyńców
//        places.add(ModelPlace.newVillage(PopulationType.BARBARIANS, AffiliationType.NONE, null));
    }

    public void startSimulation() {
        places.forEach(place -> {
//            SimulationPlace sp = MainEngine.getInstance().getPlaceRegistry().get(place);
//            if (sp.getModel().getCharacteristics().contains(PlaceCharacteristic.WILDERNESS_ENCHANT)) {
//                enchant = EnchantType.WILDERNESS;
//                enchantmentLevel = 3;
//            }
        });
        //TODO run simulation
    }

    private Integer chooseEnchantmentLevel(EnchantType enchant) {
        if (enchant == null || enchant == EnchantType.NONE) {
            return 0;
        }
        //TODO może od czegoś to jakoś sensownie uzależnić?
        int level = 1;
        while (level < 5 && rand.nextDouble() < 0.02) {
            level++;
        }
        return level;
    }

    private static Double calculateProportions(Double naturalReachness) {
        return naturalReachness / 20.0;
    }

    private static int getAverageTons(TerrainResource resource, Double reachness) {
        ResourceDeposit[] deposits = resource.getCategory().getDeposits();
        double bias = Math.pow(rand.nextDouble(), 1.5 - reachness); // reachness ∈ [0.0, 1.0]
        int index = (int) (bias * deposits.length);
        index = Math.min(index, deposits.length - 1); // zabezpieczenie
        return (int) (deposits[index].getAverageTons() * reachness);
//        return deposits[rand.nextInt()].getAverageTons();
    }

    private RegionType determineType() {
        //TODO z soilType, humidity, climate
        return RegionType.FOREST;
    }

    private SoilType determineSoilType() {
        List<SoilType> available = Arrays.stream(humidity.getSoilTypes())
                .filter(type -> Arrays.asList(climate.getSoilTypes()).contains(type))
                .filter(type -> Arrays.asList(terrainShape.getSoilTypes()).contains(type))
                .toList();
        int size = available.size();
        return size > 0 ? available.get(rand.nextInt(size)) : SoilType.SANDY;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Long getId() {
        return id;
    }

    public ModelRegion setId(Long id) {
        this.id = id;
        return this;
    }

    public List<TerrainCharacteristic> getCharacteristics() {
        return characteristics;
    }

    public ModelRegion setDevelopmentLevel(Integer developmentLevel) {
        this.developmentLevel = developmentLevel;
        return this;
    }

    public ModelRegion setEnchantmentLevel(Integer enchantmentLevel) {
        this.enchantmentLevel = enchantmentLevel;
        return this;
    }

    public TerrainShape getTerrainShape() {
        return terrainShape;
    }

    public Integer getDevelopmentLevel() {
        return developmentLevel;
    }

    public Integer getEnchantmentLevel() {
        return enchantmentLevel;
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

    public Humidity getHumidity() {
        return humidity;
    }

    public ModelRegion setHumidity(Humidity humidity) {
        this.humidity = humidity;
        return this;
    }

    public Climate getClimate() {
        return climate;
    }

    public ModelRegion setClimate(Climate climate) {
        this.climate = climate;
        return this;
    }

    public SoilType getSoilType() {
        return soilType;
    }

    public ModelRegion setSoilType(SoilType soilType) {
        this.soilType = soilType;
        return this;
    }

    public Map<TerrainResource, Integer> getNaturalResources() {
        return naturalResources;
    }

    public RegionType getType() {
        return type;
    }

    public ModelRegion setType(RegionType type) {
        this.type = type;
        return this;
    }

    public EnchantType getEnchant() {
        return enchant;
    }

    public ModelRegion setEnchant(EnchantType enchant) {
        this.enchant = enchant;
        return this;
    }

    public ModelRegion setCoast(boolean coast) {
        this.coast = coast;
        return this;
    }

    private Double attractiveness() {
        double base = 1.0;
        if (coast && river) {
            base *= 1.5;
        } else {
            if (coast) {
                base *= 1.2;
            }
            if (river) {
                base *= 1.3;
            }
        }
        if (lake) {
            if (!coast && !river) {
                base *= 1.3;
            } else {
                base *= 1.1;
            }
        }
        if (base < 1.01) {
            base *= 0.7;
        }
        double foodAccess = 0.4 * fertility + 0.3 * plantingEasiness + 0.2 * farmingEasiness + 0.1 * efficiency;
        if (foodAccess < 1) {
            foodAccess *= 0.6;
        }
        double livingQuality = 0.4 * stability + 0.3 * health + 0.3 * attitude;
        if (livingQuality < 1) {
            livingQuality *= 0.85;
        }
        return (0.35 * base + 0.5 * foodAccess + 0.15 * livingQuality) * foodAccess * livingQuality;
    }

    @Override
    public int compareTo(ModelRegion other) {
        return attractiveness().compareTo(other.attractiveness());
    }

    public ModelRegion setWoodRichness(Integer woodRichness) {
        this.woodRichness = woodRichness;
        return this;
    }

    public static class Builder {
        public TerrainShape terrainShape;
        private Humidity humidity = Humidity.NEUTRAL;
        private Climate climate = Climate.NEUTRAL;
        private Boolean coast = false;
//        private SoilType soilType;
        private Double naturalReachness = 0.1;
        private Map<TerrainResource, Integer> naturalResources = new HashMap<>();
        private RegionType type;
        private Integer developmentLevel;
        private EnchantType enchant = EnchantType.NONE;
        private Integer enchantmentLevel;
        private Integer lakesRichness;
        private Integer riversRichness;
        private Integer woodRichness;
        private List<Long> startCities = new ArrayList<>();

        public Builder setCoast(Boolean coast) {
            this.coast = coast;
            return this;
        }

        public Builder setStartCities(List<Long> startCities) {
            this.startCities = startCities;
            return this;
        }

        public Builder setLakesRichness(Integer lakesRichness) {
            this.lakesRichness = lakesRichness;
            return this;
        }

        public Builder setRiversRichness(Integer riversRichness) {
            this.riversRichness = riversRichness;
            return this;
        }

        public Builder setEnchantmentLevel(Integer enchantmentLevel) {
            this.enchantmentLevel = enchantmentLevel;
            return this;
        }

        public Builder setDevelopmentLevel(Integer developmentLevel) {
            this.developmentLevel = developmentLevel;
            return this;
        }

        public Builder setNaturalReachness(Double naturalReachness) {
            this.naturalReachness = naturalReachness;
            return this;
        }

        public Builder setTerrainShape(TerrainShape terrainShape) {
            this.terrainShape = terrainShape;
            return this;
        }

        public Builder setNaturalResources(Map<TerrainResource, Integer> naturalResources) {
            this.naturalResources = naturalResources;
            return this;
        }

        public Builder setHumidity(Humidity humidity) {
            this.humidity = humidity;
            return this;
        }

        public Builder setClimate(Climate climate) {
            this.climate = climate;
            return this;
        }

        public Builder setType(RegionType type) {
            this.type = type;
            return this;
        }

        public Builder setEnchant(EnchantType enchant) {
            this.enchant = enchant;
            return this;
        }

        public Builder setWoodRichness(int woodRichness) {
            this.woodRichness = woodRichness;
            return this;
        }

        public SimulationRegion build() {
            SimulationRegion region = new SimulationRegion();
            region.setModelRegion(new ModelRegion(this));
            region.setConfigurationRegion(new ConfigurationRegion());
            MainEngine.getInstance().getRegionRegistry().register(region);
            return region;
        }
    }
}
