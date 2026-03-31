package priv.mfurmane.szlachtownica.engine.registry;

import org.springframework.stereotype.Component;
import priv.mfurmane.szlachtownica.engine.MainEngine;
import priv.mfurmane.szlachtownica.model.main.ModelLake;
import priv.mfurmane.szlachtownica.model.main.ModelRiver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class RiverRegistry {
    private final Map<Long, ModelRiver> registry = new HashMap<>();
    private MainEngine engine;

    public void register(ModelRiver river) {
        registry.put(river.getId(), river);
    }

    public ModelRiver get(Long id) {
        return registry.get(id);
    }

    public List<ModelRiver> getAll() {
        return registry.values().stream().toList();
    }

    public void setEngine(MainEngine mainEngine) {
        engine = mainEngine;
    }
}
