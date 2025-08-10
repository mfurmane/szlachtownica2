package priv.mfurmane.szlachtownica.model.config;

import priv.mfurmane.szlachtownica.model.simulation.terrain.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigurationProvince {
//    private List<Climate> climate = new ArrayList<>();
//    private List<Humidity> humidity = new ArrayList<>();
    private List<ConfigurationSubProvince> subProvinces = new ArrayList<>();
    private Map<RegionType, Double> initialNaturalProfile = new HashMap<>();
    private Map<RegionType, Double> initialSettlersProfile = new HashMap<>();
    private Map<EnchantType, Double> enchantDistribution = new HashMap<>();
    private List<Long> initialCities = new ArrayList<>();
    private Map<TerrainShape, Double> terrainProfile = new HashMap<>();
    private Integer woodRichness;
    private Integer resourceRichness;
    private Integer lakesRichness;
    private Integer riversRichness;
    private Integer simulationStart;
    private Double growthPotential;
    private Double instability;

    public ConfigurationProvince(Builder builder) {
        this.subProvinces = builder.subProvinces;
        this.initialNaturalProfile = builder.initialNaturalProfile;
        this.initialSettlersProfile = builder.initialSettlersProfile;
        this.woodRichness = builder.woodRichness;
        this.resourceRichness = builder.resourceRichness;
        this.lakesRichness = builder.lakesRichness;
        this.riversRichness = builder.riversRichness;
        this.initialCities = builder.initialCities;
        this.enchantDistribution = builder.enchantDistribution;
        this.terrainProfile = builder.terrainProfile;
        this.simulationStart = builder.simulationStart;
        this.growthPotential = builder.growthPotential;
        this.instability = builder.instability;
    }

    public void mergeFrom(ConfigurationProvince other) {
        if (other.subProvinces != null) this.subProvinces = other.subProvinces;
        if (other.initialNaturalProfile != null) this.initialNaturalProfile = other.initialNaturalProfile;
        if (other.initialSettlersProfile != null) this.initialSettlersProfile = other.initialSettlersProfile;
        if (other.woodRichness != null) this.woodRichness = other.woodRichness;
        if (other.resourceRichness != null) this.resourceRichness = other.resourceRichness;
        if (other.lakesRichness != null) this.lakesRichness = other.lakesRichness;
        if (other.riversRichness != null) this.riversRichness = other.riversRichness;
        if (other.initialCities != null) this.initialCities = other.initialCities;
        if (other.enchantDistribution != null) this.enchantDistribution = other.enchantDistribution;
        if (other.terrainProfile != null) this.terrainProfile = other.terrainProfile;
        if (other.simulationStart != null) this.simulationStart = other.simulationStart;
        if (other.growthPotential != null) this.growthPotential = other.growthPotential;
        if (other.instability != null) this.instability = other.instability;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Double getGrowthPotential() {
        return growthPotential;
    }

    public Double getInstability() {
        return instability;
    }

    public Integer getLakesRichness() {
        return lakesRichness;
    }

    public Integer getRiversRichness() {
        return riversRichness;
    }

    public Integer getSimulationStart() {
        return simulationStart;
    }

    public List<ConfigurationSubProvince> getSubProvinces() {
        return subProvinces;
    }

    public Map<RegionType, Double> getInitialNaturalProfile() {
        return initialNaturalProfile;
    }

    public Map<RegionType, Double> getInitialSettlersProfile() {
        return initialSettlersProfile;
    }

    public int getWoodRichness() {
        return woodRichness;
    }

    public int getResourceRichness() {
        return resourceRichness;
    }

    public Map<EnchantType, Double> getEnchantDistribution() {
        return enchantDistribution;
    }

    public List<Long> getInitialCities() {
        return initialCities;
    }

    public Map<TerrainShape, Double> getTerrainProfile() {
        return terrainProfile;
    }

    public static class Builder {
        private List<ConfigurationSubProvince> subProvinces = new ArrayList<>();
        private Map<RegionType, Double> initialNaturalProfile = new HashMap<>();
        private Map<RegionType, Double> initialSettlersProfile = new HashMap<>();
        private int woodRichness;
        private int resourceRichness;
        private Integer lakesRichness;
        private Integer riversRichness;
        private Integer simulationStart;
        private Map<EnchantType, Double> enchantDistribution = new HashMap<>();
        private List<Long> initialCities = new ArrayList<>();
        private Map<TerrainShape, Double> terrainProfile = new HashMap<>();
        private Double growthPotential;
        private Double instability;

        public Builder setGrowthPotential(Double growthPotential) {
            this.growthPotential = growthPotential;
            return this;
        }

        public Builder setInstability(Double instability) {
            this.instability = instability;
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

        public Builder setSimulationStart(Integer simulationStart) {
            this.simulationStart = simulationStart;
            return this;
        }

        public Builder setSubProvinces(List<ConfigurationSubProvince> subProvinces) {
            this.subProvinces = subProvinces;
            return this;
        }

        public Builder setInitialNaturalProfile(Map<RegionType, Double> initialNaturalProfile) {
            this.initialNaturalProfile = initialNaturalProfile;
            return this;
        }

        public Builder setInitialSettlersProfile(Map<RegionType, Double> initialSettlersProfile) {
            this.initialSettlersProfile = initialSettlersProfile;
            return this;
        }

        public Builder setWoodRichness(int woodRichness) {
            this.woodRichness = woodRichness;
            return this;
        }

        public Builder setResourceRichness(int resourceRichness) {
            this.resourceRichness = resourceRichness;
            return this;
        }

        public Builder setEnchantDistribution(Map<EnchantType, Double> enchantDistribution) {
            this.enchantDistribution = enchantDistribution;
            return this;
        }

        public Builder setInitialCities(List<Long> initialCities) {
            this.initialCities = initialCities;
            return this;
        }

        public Builder setTerrainProfile(Map<TerrainShape, Double> terrainProfile) {
            this.terrainProfile = terrainProfile;
            return this;
        }

        public ConfigurationProvince build() {
            return new ConfigurationProvince(this);
        }
    }
}
