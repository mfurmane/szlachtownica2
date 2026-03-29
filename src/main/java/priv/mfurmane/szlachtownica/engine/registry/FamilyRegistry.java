package priv.mfurmane.szlachtownica.engine.registry;

import org.springframework.stereotype.Component;
import priv.mfurmane.szlachtownica.engine.MainEngine;
import priv.mfurmane.szlachtownica.model.main.ModelFamily;
import priv.mfurmane.szlachtownica.model.main.ModelSubProvince;
import priv.mfurmane.szlachtownica.model.simulation.SimulationFamily;

import java.util.HashMap;
import java.util.Map;

@Component
public class FamilyRegistry {
    private final Map<Long, SimulationFamily> registry = new HashMap<>();
    private MainEngine engine;

    public void register(SimulationFamily family) {
        registry.put(family.getModel().getId(), family);
    }

    public SimulationFamily get(Long id) {
        return registry.get(id);
    }

    public void setEngine(MainEngine mainEngine) {
        engine = mainEngine;
    }

}
