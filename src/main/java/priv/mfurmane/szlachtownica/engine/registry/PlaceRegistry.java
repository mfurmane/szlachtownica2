package priv.mfurmane.szlachtownica.engine.registry;

import org.springframework.stereotype.Component;
import priv.mfurmane.szlachtownica.engine.MainEngine;
import priv.mfurmane.szlachtownica.model.main.ModelPlace;
import priv.mfurmane.szlachtownica.model.simulation.SimulationPerson;
import priv.mfurmane.szlachtownica.model.simulation.SimulationPlace;

import java.util.HashMap;
import java.util.Map;

@Component
public class PlaceRegistry {
    private final Map<Long, SimulationPlace> registry = new HashMap<>();
    private MainEngine engine;

    public void register(SimulationPlace place) {
        registry.put(place.getModel().getId(), place);
    }

    public SimulationPlace get(Long id) {
        return registry.get(id);
    }

    public void setEngine(MainEngine mainEngine) {
        engine = mainEngine;
    }
}
