package priv.mfurmane.szlachtownica.model.main;

import priv.mfurmane.szlachtownica.engine.MainEngine;
import priv.mfurmane.szlachtownica.model.simulation.SimulationRegion;
import priv.mfurmane.szlachtownica.model.simulation.terrain.Material;
import priv.mfurmane.szlachtownica.model.simulation.terrain.PlaceCharacteristic;
import priv.mfurmane.szlachtownica.model.simulation.terrain.PlaceType;
import priv.mfurmane.szlachtownica.model.simulation.terrain.TerrainCharacteristic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModelPlace {
    private Long id;
    private Long regionId;
    private PlaceType type;
    private Integer level;
    private final List<PlaceCharacteristic> characteristics = new ArrayList<>();

    private Integer minLevel;
    private Integer maxLevel;
    private Double localLoyalty;
    private Double culturalEvolution;
    private Double technologyAdoption;
    private Double migrationAttractiveness;
    private Double tradeAttractiveness;
    private Double expanse;
    private Double productionSpeed;
    private Double instability;
    private Double comfort;
    private Double order;
    private Double safety;

    public Integer getMinLevel() {
        return minLevel;
    }

    public Integer getMaxLevel() {
        return maxLevel;
    }

    public Double getLocalLoyalty() {
        return localLoyalty;
    }

    public Double getCulturalEvolution() {
        return culturalEvolution;
    }

    public Double getTechnologyAdoption() {
        return technologyAdoption;
    }

    public Double getMigrationAttractiveness() {
        return migrationAttractiveness;
    }

    public Double getTradeAttractiveness() {
        return tradeAttractiveness;
    }

    public Double getExpanse() {
        return expanse;
    }

    public Double getProductionSpeed() {
        return productionSpeed;
    }

    public Double getInstability() {
        return instability;
    }

    public Double getComfort() {
        return comfort;
    }

    public Double getOrder() {
        return order;
    }

    public Double getSafety() {
        return safety;
    }

    public Map<Material, Double> getBuildingsWithMaterials() {
        return buildingsWithMaterials;
    }

    //budynki z konkretnego materiału, oroentacyjnie
    private final Map<Material, Double> buildingsWithMaterials = new HashMap<>();

    public ModelPlace(Builder builder) {
        this.id = builder.id;
        this.regionId = builder.regionId;
        this.type = builder.type;
        this.level = builder.level;
        this.characteristics.addAll(builder.characteristics);

        this.minLevel = builder.minLevel;
        this.maxLevel = builder.maxLevel;
        this.localLoyalty = builder.localLoyalty;
        this.culturalEvolution = builder.culturalEvolution;
        this.technologyAdoption = builder.technologyAdoption;
        this.migrationAttractiveness = builder.migrationAttractiveness;
        this.tradeAttractiveness = builder.tradeAttractiveness;
        this.expanse = builder.expanse;
        this.productionSpeed = builder.productionSpeed;
        this.instability = builder.instability;
        this.comfort = builder.comfort;
        this.order = builder.order;
        this.safety = builder.safety;
    }

    public static Builder builder() {
        return  new Builder();
    }

    public SimulationRegion region() {
        return MainEngine.getInstance().getRegionRegistry().get(regionId);
    }

    public Long getRegion() {
        return regionId;
    }

    public Long getId() {
        return id;
    }

    public PlaceType getType() {
        return type;
    }

    public Integer getLevel() {
        return level;
    }

    public List<PlaceCharacteristic> getCharacteristics() {
        return characteristics;
    }

    public static Long newLake() {
        //TODO determine id
        String name = MainEngine.getInstance().getPlaceNameProvider().getLakeName();
        //TODO register lake in places
        return null;
    }

    public static Long newRiver() {
        //TODO determine id
        String name = MainEngine.getInstance().getPlaceNameProvider().getRiverName();
        //TODO register river in places
        return null;
    }

    public static Long newVillage() {
        //TODO determine id
        String name = MainEngine.getInstance().getPlaceNameProvider().getVillageName();
        //TODO name river
        //TODO register river in places
        return null;
    }




    public boolean hasCoast() {
        return region().getModelRegion().getCharacteristics().contains(TerrainCharacteristic.COAST);
    }
    public boolean hasLake() {
        return region().getModelRegion().getCharacteristics().contains(TerrainCharacteristic.LAKES);
    }
    public boolean hasRiver() {
        return region().getModelRegion().getCharacteristics().contains(TerrainCharacteristic.RIVERS);
    }
    public boolean hasProminentEnchant() {
        return region().getModelRegion().getEnchantmentLevel() >= 3;
    }
    public boolean hasProperLevel(int min, int max) {
        return level >= min && level <= max;
    }
    public boolean hasLumberingPotential() {
        return region().getModelRegion().getWoodRichness() > 8;
    //woodRichness
    }
    public boolean hasMiningPotential() {
        return false;
    //zasoby
    }
    public boolean hasFamine() {
        return false;
    //głodujemy my lub okolica
    }
    public boolean hasTradingPotential() {
        return false;
    }
    public boolean hasDangers() {
        return false;
    }
    public boolean hasTechnoPotential() {
        return false;
    }

    public boolean hasPreference(PlaceCharacteristic characteristic) {
        return false;
    }

    public static class Builder {
        private Material preferedMaterial;
        private Long id;
        private Long regionId;
        private PlaceType type;
        private Integer level;
        private final List<PlaceCharacteristic> characteristics = new ArrayList<>();

        private Integer minLevel;
        private Integer maxLevel;
        private Double localLoyalty;
        private Double culturalEvolution;
        private Double technologyAdoption;
        private Double migrationAttractiveness;
        private Double tradeAttractiveness;
        private Double expanse;
        private Double productionSpeed;
        private Double instability;
        private Double comfort;
        private Double order;
        private Double safety;

        public Builder setPreferedMaterial(Material preferedMaterial) {
            this.preferedMaterial = preferedMaterial;
            return this;
        }

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setRegionId(Long regionId) {
            this.regionId = regionId;
            return this;
        }

        public Builder setType(PlaceType type) {
            this.type = type;
            return this;
        }

        public Builder setLevel(Integer level) {
            this.level = level;
            return this;
        }

        public Builder setMinLevel(Integer minLevel) {
            this.minLevel = minLevel;
            return this;
        }

        public Builder setMaxLevel(Integer maxLevel) {
            this.maxLevel = maxLevel;
            return this;
        }

        public Builder setLocalLoyalty(Double localLoyalty) {
            this.localLoyalty = localLoyalty;
            return this;
        }

        public Builder setCulturalEvolution(Double culturalEvolution) {
            this.culturalEvolution = culturalEvolution;
            return this;
        }

        public Builder setTechnologyAdoption(Double technologyAdoption) {
            this.technologyAdoption = technologyAdoption;
            return this;
        }

        public Builder setMigrationAttractiveness(Double migrationAttractiveness) {
            this.migrationAttractiveness = migrationAttractiveness;
            return this;
        }

        public Builder setTradeAttractiveness(Double tradeAttractiveness) {
            this.tradeAttractiveness = tradeAttractiveness;
            return this;
        }

        public Builder setExpanse(Double expanse) {
            this.expanse = expanse;
            return this;
        }

        public Builder setProductionSpeed(Double productionSpeed) {
            this.productionSpeed = productionSpeed;
            return this;
        }

        public Builder setInstability(Double instability) {
            this.instability = instability;
            return this;
        }

        public Builder setComfort(Double comfort) {
            this.comfort = comfort;
            return this;
        }

        public Builder setOrder(Double order) {
            this.order = order;
            return this;
        }

        public Builder setSafety(Double safety) {
            this.safety = safety;
            return this;
        }

        public ModelPlace build() {
            return new ModelPlace(this);
        }

    }

}
