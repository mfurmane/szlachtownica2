package priv.mfurmane.szlachtownica.engine.registry;

import org.springframework.stereotype.Component;
import priv.mfurmane.szlachtownica.engine.MainEngine;
import priv.mfurmane.szlachtownica.model.main.ModelPlace;
import priv.mfurmane.szlachtownica.model.simulation.SimulationPerson;

import java.util.HashMap;
import java.util.Map;

@Component
public class PlaceRegistry {
    private final Map<Long, ModelPlace> registry = new HashMap<>();
    private MainEngine engine;

    public void register(ModelPlace place) {
        registry.put(place.getId(), place);
    }

    public ModelPlace get(Long id) {
        return registry.get(id);
    }

    public void setEngine(MainEngine mainEngine) {
        engine = mainEngine;
    }
}
