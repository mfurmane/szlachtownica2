package priv.mfurmane.szlachtownica.engine.registry;

import org.springframework.stereotype.Component;
import priv.mfurmane.szlachtownica.engine.MainEngine;
import priv.mfurmane.szlachtownica.model.main.ModelLake;
import priv.mfurmane.szlachtownica.model.simulation.SimulationProvince;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class LakeRegistry {
    private final Map<Long, ModelLake> registry = new HashMap<>();
    private MainEngine engine;

    public void register(ModelLake lake) {
        registry.put(lake.getId(), lake);
    }

    public ModelLake get(Long id) {
        return registry.get(id);
    }

    public List<ModelLake> getAll() {
        return registry.values().stream().toList();
    }

    public void setEngine(MainEngine mainEngine) {
        engine = mainEngine;
    }
}
