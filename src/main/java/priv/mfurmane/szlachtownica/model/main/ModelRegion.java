package priv.mfurmane.szlachtownica.model.main;

import priv.mfurmane.szlachtownica.model.simulation.terrain.*;

import java.util.*;

public class ModelRegion {
    public static final Random rand = new Random();
    private Humidity humidity;
    private Climate climate;
    private TerrainShape terrainShape;
    private SoilType soilType;
//    private final Double naturalReachness;
    private final Map<TerrainResource, Integer> naturalResources = new HashMap<>();
    private RegionType type;
    private EnchantType enchant;
    private final List<ModelPlace> places = new ArrayList<>();
    private final Map<ProductionType, Integer> production = new HashMap<>();
    private final Map<ImportNeed, Integer> importNeeded = new HashMap<>();

    public ModelRegion(Builder builder) {
        humidity = builder.humidity;
        climate = builder.climate;
        terrainShape = builder.terrainShape;
        soilType = determineSoilType();
        type = builder.type != null ? builder.type : determineType();
        enchant = builder.enchant;
        places.addAll(builder.places);
//        naturalReachness = builder.naturalReachness;
        naturalResources.putAll(builder.naturalResources);
        Arrays.stream(soilType.getResources()).forEach(resource -> {
            if (rand.nextDouble() < resource.getAvailability()) {
                naturalResources.merge(resource, getAverageTons(resource, calculateProportions(builder.naturalReachness)), Integer::sum);
            }
        });
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

    public TerrainShape getTerrainShape() {
        return terrainShape;
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

    public List<ModelPlace> getPlaces() {
        return places;
    }

    public static class Builder {
        public TerrainShape terrainShape;
        private Humidity humidity = Humidity.NEUTRAL;
        private Climate climate = Climate.NEUTRAL;
//        private SoilType soilType;
        private Double naturalReachness = 0.1;
        private Map<TerrainResource, Integer> naturalResources = new HashMap<>();
        private RegionType type;
        private EnchantType enchant = EnchantType.NONE;
        private List<ModelPlace> places = new ArrayList<>();

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

        public Builder setPlaces(List<ModelPlace> places) {
            this.places = places;
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
