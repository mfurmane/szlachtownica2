package priv.mfurmane.szlachtownica.model.main;

import priv.mfurmane.szlachtownica.model.simulation.terrain.EnchantType;
import priv.mfurmane.szlachtownica.model.simulation.terrain.PlaceCharacteristic;
import priv.mfurmane.szlachtownica.model.simulation.terrain.PlaceType;

import java.util.ArrayList;
import java.util.List;

public class ModelPlace {
    private long id;
    private PlaceType type;
    private Integer level;
    private final List<PlaceCharacteristic> characteristics = new ArrayList<>();


}
