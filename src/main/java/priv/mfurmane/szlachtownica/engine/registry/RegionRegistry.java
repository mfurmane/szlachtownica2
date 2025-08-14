package priv.mfurmane.szlachtownica.engine.registry;

import priv.mfurmane.szlachtownica.engine.MainEngine;
import priv.mfurmane.szlachtownica.model.main.ModelPlace;
import priv.mfurmane.szlachtownica.model.main.ModelRegion;
import priv.mfurmane.szlachtownica.model.simulation.SimulationRegion;

import java.util.HashMap;
import java.util.Map;

public class RegionRegistry {
    private final Map<Long, SimulationRegion> registry = new HashMap<>();
    private MainEngine engine;

    public void register(SimulationRegion region) {
        registry.put(region.getModelRegion().getId(), region);
    }

    public SimulationRegion get(Long id) {
        return registry.get(id);
    }

    public void setEngine(MainEngine mainEngine) {
        engine = mainEngine;
    }
}
