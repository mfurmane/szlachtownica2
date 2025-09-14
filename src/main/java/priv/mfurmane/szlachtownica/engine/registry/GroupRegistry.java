package priv.mfurmane.szlachtownica.engine.registry;

import org.springframework.stereotype.Component;
import priv.mfurmane.szlachtownica.engine.MainEngine;
import priv.mfurmane.szlachtownica.model.simulation.SimulationPerson;
import priv.mfurmane.szlachtownica.model.simulation.population.Group;
import priv.mfurmane.szlachtownica.model.simulation.population.GroupRelation;
import priv.mfurmane.szlachtownica.model.simulation.population.GroupRelationType;

import java.util.HashMap;
import java.util.Map;

@Component
public class GroupRegistry {
    private final Map<Long, Group> registry = new HashMap<>();
    private final Map<TwoGroups, GroupRelation> relations = new HashMap<>();
    private MainEngine engine;

    public void register(Group person) {
        registry.put(person.getId(), person);
    }

    public GroupRelation getRelation(Long g1, Long g2) {
        TwoGroups key = new TwoGroups(g1, g2);
        relations.computeIfAbsent(key, k -> new GroupRelation(g1, g2));
        return relations.get(key);
    }

    public Group get(Long id) {
        return registry.get(id);
    }

    public void setEngine(MainEngine mainEngine) {
        engine = mainEngine;
    }
}
