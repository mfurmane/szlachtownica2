package priv.mfurmane.szlachtownica.engine.registry;

import priv.mfurmane.szlachtownica.engine.MainEngine;
import priv.mfurmane.szlachtownica.model.main.ModelProvince;
import priv.mfurmane.szlachtownica.model.simulation.SimulationProvince;

import java.util.HashMap;
import java.util.Map;

public class ProvinceRegistry {
    private final Map<Long, SimulationProvince> registry = new HashMap<>();
    private MainEngine engine;

    public void register(SimulationProvince province) {
        registry.put(province.getModel().getId(), province);
    }

    public SimulationProvince get(Long id) {
        return registry.get(id);
    }

    public void setEngine(MainEngine mainEngine) {
        engine = mainEngine;
    }
}
