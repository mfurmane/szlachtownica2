package priv.mfurmane.szlachtownica.model.config;

import priv.mfurmane.szlachtownica.model.main.ModelPlace;
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
    private List<ModelPlace> initialCities = new ArrayList<>();
    private Map<TerrainShape, Double> terrainProfile = new HashMap<>();
    private Integer woodRichness;
    private Integer resourceRichness;

    public ConfigurationProvince(Builder builder) {
        this.subProvinces = builder.subProvinces;
        this.initialNaturalProfile = builder.initialNaturalProfile;
        this.initialSettlersProfile = builder.initialSettlersProfile;
        this.woodRichness = builder.woodRichness;
        this.resourceRichness = builder.resourceRichness;
        this.initialCities = builder.initialCities;
        this.enchantDistribution = builder.enchantDistribution;
        this.terrainProfile = builder.terrainProfile;
    }

    public void mergeFrom(ConfigurationProvince other) {
        if (other.subProvinces != null) this.subProvinces = other.subProvinces;
        if (other.initialNaturalProfile != null) this.initialNaturalProfile = other.initialNaturalProfile;
        if (other.initialSettlersProfile != null) this.initialSettlersProfile = other.initialSettlersProfile;
        if (other.woodRichness != null) this.woodRichness = other.woodRichness;
        if (other.resourceRichness != null) this.resourceRichness = other.resourceRichness;
        if (other.initialCities != null) this.initialCities = other.initialCities;
        if (other.enchantDistribution != null) this.enchantDistribution = other.enchantDistribution;
        if (other.terrainProfile != null) this.terrainProfile = other.terrainProfile;
    }

    public static Builder builder() {
        return new Builder();
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

    public List<ModelPlace> getInitialCities() {
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
        private Map<EnchantType, Double> enchantDistribution = new HashMap<>();
        private List<ModelPlace> initialCities = new ArrayList<>();
        private Map<TerrainShape, Double> terrainProfile = new HashMap<>();

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

        public Builder setInitialCities(List<ModelPlace> initialCities) {
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
