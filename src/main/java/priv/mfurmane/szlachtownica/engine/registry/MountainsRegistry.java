package priv.mfurmane.szlachtownica.engine.registry;

import org.springframework.stereotype.Component;
import priv.mfurmane.szlachtownica.engine.MainEngine;
import priv.mfurmane.szlachtownica.model.main.ModelLake;
import priv.mfurmane.szlachtownica.model.main.ModelMountains;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MountainsRegistry {
    private final Map<Long, ModelMountains> registry = new HashMap<>();
    private MainEngine engine;

    public void register(ModelMountains mountains) {
        registry.put(mountains.getId(), mountains);
    }

    public ModelMountains get(Long id) {
        return registry.get(id);
    }

    public List<ModelMountains> getAll() {
        return registry.values().stream().toList();
    }

    public void setEngine(MainEngine mainEngine) {
        engine = mainEngine;
    }
}
