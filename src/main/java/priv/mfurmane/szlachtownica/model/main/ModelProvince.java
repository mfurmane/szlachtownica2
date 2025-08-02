package priv.mfurmane.szlachtownica.model.main;

import priv.mfurmane.szlachtownica.model.config.ConfigurationProvince;
import priv.mfurmane.szlachtownica.model.config.ConfigurationSubProvince;
import priv.mfurmane.szlachtownica.model.simulation.terrain.*;

import java.util.*;

public class ModelProvince {
    private Long id;
    private String name;
    private final List<ModelSubProvince> areas = new ArrayList<>();
    private final List<RegionType> preferredDirections = new ArrayList<>();
    private Double growthPotential;
    private Double instability;

    public void initializeSubProvinces(ConfigurationSubProvince subProvinceConf, ConfigurationProvince conf) {
        ModelSubProvince subProvince = new ModelSubProvince(subProvinceConf.getClimate(), subProvinceConf.getHumidity());
        int naturalRegions = subProvinceConf.getRegionsCount() - subProvinceConf.getInitiallyOccupied();
        for (int i = 0; i < subProvinceConf.getInitiallyOccupied(); i++) {
            subProvince.getRegions().add(ModelRegion.builder()
                    .setClimate(subProvinceConf.getClimate())
                    .setHumidity(subProvinceConf.getHumidity())
                    .setTerrainShape(chooseTerrainShape(conf))
                    .setType(chooseType(conf.getInitialSettlersProfile()))
                    .setEnchant(chooseEnchant(conf))
                    .build());
        }
        for (int i = 0; i < naturalRegions; i++) {
            subProvince.getRegions().add(ModelRegion.builder()
                    .setClimate(subProvinceConf.getClimate())
                    .setHumidity(subProvinceConf.getHumidity())
                    .setTerrainShape(chooseTerrainShape(conf))
                    .setType(chooseType(conf.getInitialNaturalProfile()))
                    .setEnchant(chooseEnchant(conf))
                    .build());
        }
    }

    private static EnchantType chooseEnchant(ConfigurationProvince conf) {
        return randomFromMap(conf.getEnchantDistribution()).orElse(EnchantType.NONE);
    }

    private static TerrainShape chooseTerrainShape(ConfigurationProvince conf) {
        return randomFromMap(conf.getTerrainProfile()).orElse(TerrainShape.FLATLANDS);
    }

    private static RegionType chooseType(Map<RegionType, Double> regions) {
        if (new Random().nextDouble() < 0.10)
            return getRandomType();
        return randomFromMap(regions).orElse(getRandomType());
    }

    private static <T> Optional<T> randomFromMap(Map<T, Double> distribution) {
        double totalWeight = distribution.values().stream().mapToDouble(Double::doubleValue).sum();
        double roll = new Random().nextDouble() * totalWeight;
        double cumulative = 0.0;
        for (Map.Entry<T, Double> entry : distribution.entrySet()) {
            cumulative += entry.getValue();
            if (roll <= cumulative) {
                return Optional.of(entry.getKey());
            }
        }
        return Optional.empty();
    }

    public static Builder builder() {
        return new Builder();
    }

    private static RegionType getRandomType() {
        return null;
    }

    public ModelProvince(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.growthPotential = builder.growthPotential;
        this.instability = builder.instability;
    }

    public void mergeFrom(ModelProvince other) {
        if (other.id != null) this.id = other.id;
        if (other.name != null) this.name = other.name;
        if (other.growthPotential != null) this.growthPotential = other.growthPotential;
        if (other.instability != null) this.instability = other.instability;

    }

    public List<ModelSubProvince> getAreas() {
        return areas;
    }

    public List<ModelRegion> getRegions() {
        List<ModelRegion> result = new ArrayList<>();
        areas.forEach(modelSubProvince -> {
            result.addAll(modelSubProvince.getRegions());
        });
        return result;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public List<RegionType> getPreferredDirections() {
        return preferredDirections;
    }

    public Double getGrowthPotential() {
        return growthPotential;
    }

    public Double getInstability() {
        return instability;
    }

    public static class Builder {
        private final List<ModelProvince> areas = new ArrayList<>();
        private final List<ModelRegion> regions = new ArrayList<>();
        private final List<RegionType> preferredDirections = new ArrayList<>();
        private Long id;
        private String name;
        private Double growthPotential;
        private Double instability;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setGrowthPotential(Double growthPotential) {
            this.growthPotential = growthPotential;
            return this;
        }

        public Builder setInstability(Double instability) {
            this.instability = instability;
            return this;
        }

        public ModelProvince build() {
            return new ModelProvince(this);
        }
    }
}
