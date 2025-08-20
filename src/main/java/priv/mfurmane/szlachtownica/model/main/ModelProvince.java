package priv.mfurmane.szlachtownica.model.main;

import priv.mfurmane.szlachtownica.engine.MainEngine;
import priv.mfurmane.szlachtownica.model.config.ConfigurationProvince;
import priv.mfurmane.szlachtownica.model.config.ConfigurationSubProvince;
import priv.mfurmane.szlachtownica.model.simulation.SimulationRegion;
import priv.mfurmane.szlachtownica.model.simulation.SimulationSubProvince;
import priv.mfurmane.szlachtownica.model.simulation.terrain.*;

import java.util.*;

public class ModelProvince {
    private Long id;
    private String name;
    private final List<Long> subProvinces = new ArrayList<>();
    private final List<RegionType> preferredDirections = new ArrayList<>();

    public void initializeSubProvinces(ConfigurationSubProvince subProvinceConf, ConfigurationProvince conf) {
        ModelSubProvince subProvinceModel = new ModelSubProvince(subProvinceConf.getClimate(), subProvinceConf.getHumidity());
        SimulationSubProvince subProvince = new SimulationSubProvince();
        subProvince.setModel(subProvinceModel);
        subProvince.setConf(subProvinceConf);
        MainEngine.getInstance().getSubProvinceRegistry().register(subProvince);
        int naturalRegions = subProvinceConf.getRegionsCount() - subProvinceConf.getInitiallyOccupied();
        for (int i = 0; i < subProvinceConf.getInitiallyOccupied(); i++) {
            List<Long> cities = new ArrayList<>();
            if (i < conf.getInitialCities().size()) {
                cities.add(conf.getInitialCities().get(i));
            }
            subProvinces.add(subProvinceModel.getId());
            subProvinceModel.getRegions().add(ModelRegion.builder()
                    .setCoast(subProvinceConf.isCoast() && new Random().nextDouble() < 0.7)
                    .setClimate(subProvinceConf.getClimate())
                    .setHumidity(subProvinceConf.getHumidity())
                    .setDevelopmentLevel(1)
                    .setTerrainShape(chooseTerrainShape(conf))
                    .setType(chooseType(conf.getInitialSettlersProfile()))
                    .setEnchant(chooseEnchant(conf))
                    .setStartCities(cities)
                    .setLakesRichness(conf.getLakesRichness())
                    .setRiversRichness(conf.getRiversRichness())
                    .setWoodRichness(conf.getWoodRichness())
                    .build().getModelRegion().getId());
        }
        for (int i = 0; i < naturalRegions; i++) {
            subProvinceModel.getRegions().add(ModelRegion.builder()
                    .setCoast(subProvinceConf.isCoast() && new Random().nextDouble() < 0.7)
                    .setClimate(subProvinceConf.getClimate())
                    .setHumidity(subProvinceConf.getHumidity())
                    .setDevelopmentLevel(determineNatureStrength(conf))
                    .setTerrainShape(chooseTerrainShape(conf))
                    .setType(chooseType(conf.getInitialNaturalProfile()))
                    .setEnchant(chooseEnchant(conf))
                    .setLakesRichness(conf.getLakesRichness())
                    .setRiversRichness(conf.getRiversRichness())
                    .setWoodRichness(conf.getWoodRichness())
                    .build().getModelRegion().getId());
        }
        //TODO register StartProvinceSettlementEvent
    }

    private static Integer determineNatureStrength(ConfigurationProvince conf) {
        int max = (int) Math.ceil(conf.getWoodRichness() / 5.0);
        return new Random().nextInt(max) + 1;
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
    }

    public void mergeFrom(ModelProvince other) {
        if (other.id != null) this.id = other.id;
        if (other.name != null) this.name = other.name;

    }

    public List<SimulationRegion> getRegions() {
        List<SimulationRegion> result = new ArrayList<>();
        subProvinces.forEach(subProvinceId -> {
            result.addAll(MainEngine.getInstance().getSubProvinceRegistry().get(subProvinceId).getModel().regions());
        });
        return result;
    }

    public long getId() {
        return id;
    }

    public List<SimulationSubProvince> getSubProvinces() {
        return subProvinces.stream().map(MainEngine.getInstance().getSubProvinceRegistry()::get).toList();
    }

    public String getName() {
        return name;
    }


    public List<RegionType> getPreferredDirections() {
        return preferredDirections;
    }

    public static class Builder {
        private final List<ModelProvince> areas = new ArrayList<>();
        private final List<ModelRegion> regions = new ArrayList<>();
        private final List<RegionType> preferredDirections = new ArrayList<>();
        private Long id;
        private String name;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public ModelProvince build() {
            return new ModelProvince(this);
        }
    }
}
