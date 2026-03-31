package priv.mfurmane.szlachtownica.engine.registry;

import org.springframework.stereotype.Component;
import priv.mfurmane.szlachtownica.engine.MainEngine;
import priv.mfurmane.szlachtownica.model.main.ModelRiver;
import priv.mfurmane.szlachtownica.model.main.ModelSeaPart;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SeaPartRegistry {
    private final Map<Long, ModelSeaPart> registry = new HashMap<>();
    private MainEngine engine;

    public void register(ModelSeaPart seaPart) {
        registry.put(seaPart.getId(), seaPart);
    }

    public ModelSeaPart get(Long id) {
        return registry.get(id);
    }

    public List<ModelSeaPart> getAll() {
        return registry.values().stream().toList();
    }

    public void setEngine(MainEngine mainEngine) {
        engine = mainEngine;
    }
}
