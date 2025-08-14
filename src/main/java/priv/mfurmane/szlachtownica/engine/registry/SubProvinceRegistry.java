package priv.mfurmane.szlachtownica.engine.registry;

import priv.mfurmane.szlachtownica.engine.MainEngine;
import priv.mfurmane.szlachtownica.model.main.ModelSubProvince;
import priv.mfurmane.szlachtownica.model.simulation.SimulationSubProvince;

import java.util.HashMap;
import java.util.Map;

public class SubProvinceRegistry {
    private final Map<Long, SimulationSubProvince> registry = new HashMap<>();
    private MainEngine engine;

    public void register(SimulationSubProvince subProvince) {
        registry.put(subProvince.getModel().getId(), subProvince);
    }

    public SimulationSubProvince get(Long id) {
        return registry.get(id);
    }

    public void setEngine(MainEngine mainEngine) {
        engine = mainEngine;
    }
}
