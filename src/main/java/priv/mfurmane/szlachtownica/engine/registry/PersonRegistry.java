package priv.mfurmane.szlachtownica.engine.registry;

import org.springframework.stereotype.Component;
import priv.mfurmane.szlachtownica.engine.MainEngine;
import priv.mfurmane.szlachtownica.model.simulation.SimulationPerson;

import java.util.HashMap;
import java.util.Map;

@Component
public class PersonRegistry {
    private final Map<Long, SimulationPerson> registry = new HashMap<>();
    private MainEngine engine;

    public void register(SimulationPerson person) {
        registry.put(person.getModel().getId(), person);
    }

    public SimulationPerson get(Long id) {
        return registry.get(id);
    }

    public void setEngine(MainEngine mainEngine) {
        engine = mainEngine;
    }
}
