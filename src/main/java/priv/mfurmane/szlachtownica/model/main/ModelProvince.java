package priv.mfurmane.szlachtownica.model.main;

import org.locationtech.jts.geom.Polygon;
import priv.mfurmane.szlachtownica.engine.MainEngine;
import priv.mfurmane.szlachtownica.model.config.ConfigurationProvince;
import priv.mfurmane.szlachtownica.model.config.ConfigurationSubProvince;
import priv.mfurmane.szlachtownica.model.main.entities.EntityProvince;
import priv.mfurmane.szlachtownica.model.main.entities.EntitySubProvince;
import priv.mfurmane.szlachtownica.model.simulation.SimulationRegion;
import priv.mfurmane.szlachtownica.model.simulation.SimulationSubProvince;
import priv.mfurmane.szlachtownica.model.simulation.terrain.EnchantType;
import priv.mfurmane.szlachtownica.model.simulation.terrain.RegionType;
import priv.mfurmane.szlachtownica.model.simulation.terrain.TerrainShape;

import java.util.*;

public class ModelProvince {

    private Long id;

    private String name;

    private Polygon area; //not null

    private final List<ModelSubProvince> subProvinces = new ArrayList<>();

    private final List<RegionType> preferredDirections = new ArrayList<>();

    public Polygon getArea() {
        return area;
    }

    public ModelProvince setArea(Polygon area) {
        this.area = area;
        return this;
    }

    public ModelSubProvince initializeSubProvinces(ConfigurationSubProvince subProvinceConf, ConfigurationProvince conf, EntitySubProvince sub) {
        ModelSubProvince subProvinceModel = new ModelSubProvince(subProvinceConf.getClimate(), subProvinceConf.getHumidity(), sub);
        subProvinceModel.setArea(sub.getArea());
        SimulationSubProvince subProvince = new SimulationSubProvince();
        subProvince.setModel(subProvinceModel);
        subProvince.setConf(subProvinceConf);
        subProvinces.add(subProvinceModel);
        MainEngine.getInstance().getSubProvinceRegistry().register(subProvince);
        int naturalRegions = subProvinceConf.getRegionsCount() - subProvinceConf.getInitiallyOccupied();
        for (int i = 0; i < subProvinceConf.getInitiallyOccupied(); i++) {
            List<Long> cities = new ArrayList<>();
            if (i < conf.getInitialCities().size()) {
                cities.add(conf.getInitialCities().get(i));
            }
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
                    .build().getModelRegion());
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
                    .build().getModelRegion());
        }
        return subProvinceModel;
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
        this.id = builder.id != null ? builder.id : this.id;
        this.name = builder.name;
    }

    public ModelProvince() {}

    public void mergeFrom(ModelProvince other) {
        if (other.id != null) this.id = other.id;
        if (other.name != null) this.name = other.name;

    }

    public List<SimulationRegion> getRegions() {
        List<SimulationRegion> result = new ArrayList<>();
        subProvinces.forEach(subProvinceId -> {
            result.addAll(MainEngine.getInstance().getSubProvinceRegistry().get(subProvinceId.getId()).getModel().regions());
        });
        return result;
    }

    public long getId() {
        return id;
    }

    public List<SimulationSubProvince> getSubProvinces() {
        return subProvinces.stream().map(sp -> MainEngine.getInstance().getSubProvinceRegistry().get(sp.getId())).toList();
    }

    public String getName() {
        return name;
    }


    public List<RegionType> getPreferredDirections() {
        return preferredDirections;
    }

    public EntityProvince toEntity() {
        EntityProvince province = new EntityProvince();
        province.setId(id);
        province.setName(name);
        province.setArea(area);
        return province;
    }

    public static class Builder {
        private List<ModelProvince> areas = new ArrayList<>();
        private List<ModelRegion> regions = new ArrayList<>();
        private List<RegionType> preferredDirections = new ArrayList<>();
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

        public Builder setAreas(List<ModelProvince> areas) {
            this.areas = areas;
            return this;
        }

        public Builder setRegions(List<ModelRegion> regions) {
            this.regions = regions;
            return this;
        }

        public Builder setPreferredDirections(List<RegionType> preferredDirections) {
            this.preferredDirections = preferredDirections;
            return this;
        }

        public ModelProvince build() {
            return new ModelProvince(this);
        }
    }
}
