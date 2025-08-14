package priv.mfurmane.szlachtownica.model.main;

import priv.mfurmane.szlachtownica.engine.MainEngine;
import priv.mfurmane.szlachtownica.model.simulation.SimulationPlace;
import priv.mfurmane.szlachtownica.model.simulation.SimulationSubProvince;
import priv.mfurmane.szlachtownica.model.simulation.terrain.*;

import java.util.*;

public class ModelRegion {
    public static final Random rand = new Random();
    private Long id;
    private Long subProvinceId;
    private final List<Long> places = new ArrayList<>();
    private Humidity humidity;
    private Climate climate;
    private Boolean coast;
    private final TerrainShape terrainShape;
    private SoilType soilType;
    //    private final Double naturalReachness;
    private final Map<TerrainResource, Integer> naturalResources = new HashMap<>();
    private RegionType type;
    private Integer developmentLevel;
    private EnchantType enchant;
    private Integer enchantmentLevel;
    private final List<TerrainCharacteristic> characteristics = new ArrayList<>();
    private final Map<ProductionType, Integer> production = new HashMap<>();
    private final Map<ImportNeed, Integer> importNeeded = new HashMap<>();

    public SimulationSubProvince getSubProvinceId() {
        return MainEngine.getInstance().getSubProvinceRegistry().get(subProvinceId);
    }

    public List<SimulationPlace> places() {
        return places.stream().map(MainEngine.getInstance().getPlaceRegistry()::get).toList();
    }

    public List<Long> getPlaces() {
        return places;
    }

    public ModelRegion(Builder builder) {
        coast = builder.coast;
        humidity = builder.humidity;
        climate = builder.climate;
        terrainShape = builder.terrainShape;
        soilType = determineSoilType();
        type = builder.type != null ? builder.type : determineType();
        developmentLevel = builder.developmentLevel;
        enchant = builder.enchant;
        if (enchant == null) {
            enchant = EnchantType.NONE;
        }
        enchantmentLevel = builder.enchantmentLevel != null ? builder.enchantmentLevel : chooseEnchantmentLevel(builder.enchant);
//        naturalReachness = builder.naturalReachness;
        naturalResources.putAll(builder.naturalResources);
        Arrays.stream(soilType.getResources()).forEach(resource -> {
            if (rand.nextDouble() < resource.getAvailability()) {
                naturalResources.merge(resource, getAverageTons(resource, calculateProportions(builder.naturalReachness)), Integer::sum);
            }
        });
        places.addAll(builder.startCities);
        boolean hasLake = false;
        boolean hasRiver = false;
        for (int i = 0; i < builder.lakesRichness; i++) {
            if (rand.nextDouble() < builder.lakesRichness / 20.0) {
                places.add(ModelPlace.newLake());
                hasLake = true;
            }
        }
        for (int i = 0; i < builder.riversRichness; i++) {
            if (rand.nextDouble() < builder.riversRichness / 20.0) {
                places.add(ModelPlace.newRiver());
                hasRiver = true;
            }
        }
        if (hasLake) characteristics.add(TerrainCharacteristic.LAKES);
        if (hasRiver) characteristics.add(TerrainCharacteristic.RIVERS);
        defineVillages();
    }

    private void defineVillages() {

    }

    public void startSimulation() {
        places.forEach(place -> {
            SimulationPlace sp = MainEngine.getInstance().getPlaceRegistry().get(place);
            if (sp.getModel().getCharacteristics().contains(PlaceCharacteristic.WILDERNESS_ENCHANT)) {
                enchant = EnchantType.WILDERNESS;
                enchantmentLevel = 3;
            }
        });
        //TODO run simulation
    }

    private Integer chooseEnchantmentLevel(EnchantType enchant) {
        if (enchant == null || enchant == EnchantType.NONE) {
            return 0;
        }
        int level = 1;
        while (level < 5 && rand.nextDouble() < 0.15) {
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

//        available.forEach(System.out::println);
        return available.get(rand.nextInt(available.size()));
    }

    public static Builder builder() {
        return new Builder();
    }

    public Long getId() {
        return id;
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

        public ModelRegion build() {
            return new ModelRegion(this);
        }
    }
}
