package priv.mfurmane.szlachtownica.model.main;

import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;
import priv.mfurmane.szlachtownica.engine.MainEngine;
import priv.mfurmane.szlachtownica.model.simulation.SimulationRegion;
import priv.mfurmane.szlachtownica.model.simulation.population.Affiliation;
import priv.mfurmane.szlachtownica.model.simulation.population.AffiliationType;
import priv.mfurmane.szlachtownica.model.simulation.population.Population;
import priv.mfurmane.szlachtownica.model.simulation.population.PopulationType;
import priv.mfurmane.szlachtownica.model.simulation.terrain.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModelSettlement extends MapFeature {
    private final Populatable populatable = new Populatable();
    private Double expanse;
    private Point location;
    private Polygon area;

    public Populatable getPopulatable() {
        return populatable;
    }

    public Double getExpanse() {
        return expanse;
    }

    public ModelSettlement setExpanse(Double expanse) {
        this.expanse = expanse;
        return this;
    }

    public Point getLocation() {
        return location;
    }

    public ModelSettlement setLocation(Point location) {
        this.location = location;
        return this;
    }

    public Polygon getArea() {
        return area;
    }

    public ModelSettlement setArea(Polygon area) {
        this.area = area;
        return this;
    }

    public Map<Material, Double> getBuildingsWithMaterials() {
        return buildingsWithMaterials;
    }

    //budynki z konkretnego materiału, oroentacyjnie
    private final Map<Material, Double> buildingsWithMaterials = new HashMap<>();

    public ModelSettlement(Builder builder) {
        this.id = builder.id;
        this.regionId = builder.regionId;
//        this.populatable.setLevel(builder.level);
        this.getCharacteristics().addAll(builder.characteristics);

//        this.populatable.setMinLevel(builder.minLevel);
//        this.populatable.setMaxLevel(builder.maxLevel);
//        this.populatable.setCulturalEvolution(builder.culturalEvolution);
//        this.populatable.setTechnologyAdoption(builder.technologyAdoption);
//        this.populatable.setMigrationAttractiveness(builder.migrationAttractiveness);
//        this.populatable.setTradeAttractiveness(builder.tradeAttractiveness);
        this.expanse = builder.expanse;
        this.populatable.setInstability(builder.instability);
//        this.populatable.setComfort(builder.comfort);
//        this.populatable.setOrder(builder.order);
//        this.populatable.setSafety(builder.safety);
    }

    public static Builder builder() {
        return  new Builder();
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
        return newVillage(PopulationType.SETTLERS, AffiliationType.NONE, null);
    }

    public static Long newVillage(PopulationType populationType, AffiliationType affiliationType, Long groupId) {
        //TODO determine id
        String name = MainEngine.getInstance().getPlaceNameProvider().getVillageName();
        Population population = new Population();
        population.setType(populationType);
        Affiliation affiliation = new Affiliation();
        affiliation.setType(affiliationType);
        affiliation.setGroup(groupId);
        population.setAffiliation(affiliation);
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
//    public boolean hasProperLevel(int min, int max) {
//        return populatable.getLevel() >= min && populatable.getLevel() <= max;
//    }
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
        private Double culturalEvolution;
        private Double technologyAdoption;
        private Double migrationAttractiveness;
        private Double tradeAttractiveness;
        private Double expanse;
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

        public ModelSettlement build() {
            return new ModelSettlement(this);
        }

    }

}
