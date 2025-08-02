package priv.mfurmane.szlachtownica.model.main;

import priv.mfurmane.szlachtownica.model.simulation.SimulationRegion;

import java.util.ArrayList;
import java.util.List;

public class ModelProvince {
    private final List<ModelRegion> regions = new ArrayList<>();

    public List<ModelRegion> getRegions() {
        return regions;
    }
}
