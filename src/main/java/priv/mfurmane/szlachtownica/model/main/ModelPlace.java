package priv.mfurmane.szlachtownica.model.main;

import priv.mfurmane.szlachtownica.engine.MainEngine;
import priv.mfurmane.szlachtownica.model.simulation.terrain.PlaceCharacteristic;
import priv.mfurmane.szlachtownica.model.simulation.terrain.PlaceType;

import java.util.ArrayList;
import java.util.List;

public class ModelPlace {
    private long id;
    private PlaceType type;
    private Integer level;
    private final List<PlaceCharacteristic> characteristics = new ArrayList<>();

    public long getId() {
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
}
