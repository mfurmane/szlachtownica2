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

    //budynki z konkretnego materiału, oroentacyjnie
    private final Map<Material, Double> buildingsWithMaterials = new HashMap<>();

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
        return false;
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


}
