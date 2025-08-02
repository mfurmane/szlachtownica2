package priv.mfurmane.szlachtownica.engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import priv.mfurmane.szlachtownica.engine.events.EventFactory;
import priv.mfurmane.szlachtownica.engine.registry.PersonRegistry;
import priv.mfurmane.szlachtownica.model.PersonFactory;
import priv.mfurmane.szlachtownica.model.Race;
import priv.mfurmane.szlachtownica.model.naming.NamingProvider;
import priv.mfurmane.szlachtownica.model.simulation.SimulationPerson;
import priv.mfurmane.szlachtownica.model.simulation.goal.GoalEngine;

import javax.annotation.PostConstruct;
import java.util.Comparator;
import java.util.Map;

@Component
public class MainEngine {
    @Autowired
    private EventManager eventManager;
    @Autowired
    private EventFactory eventFactory;
    @Autowired
    private PersonFactory personFactory;
//    @Autowired
//    private FamilyFactory familyFactory;
    @Autowired
    private PredefinedDataFiller dataFiller;
    @Autowired
    private PersonRegistry personRegistry;
    @Autowired
    private GoalEngine goalEngine;
    @Autowired
    private NamingProvider namingProvider;

    @PostConstruct
    public void inject() {
        eventManager.setEngine(this);
        eventFactory.setEngine(this);
        personFactory.setEngine(this);
//        familyFactory.setEngine(this);
        dataFiller.setEngine(this);
        personRegistry.setEngine(this);
        goalEngine.setEngine(this);
        namingProvider.setEngine(this);

        for (int i = 0; i < 10; i++) {
            SimulationPerson simulationPerson = personFactory.newPerson(Race.HUMAN);
            System.out.println("###################################################");
            System.out.println(simulationPerson.getModel().getName());
            simulationPerson.getGoals().entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).forEach((goal) -> System.out.println(goal.getValue() + ": " + goal.getKey().name()));

        }
    }

    public NamingProvider getNamingProvider() {
        return namingProvider;
    }

    public GoalEngine getGoalEngine() {
        return goalEngine;
    }

    public PersonRegistry getPersonRegistry() {
        return personRegistry;
    }

    public EventManager getEventManager() {
        return eventManager;
    }

    public EventFactory getEventFactory() {
        return eventFactory;
    }

    public PersonFactory getPersonFactory() {
        return personFactory;
    }

//    public FamilyFactory getFamilyFactory() {
//        return familyFactory;
//    }

    public PredefinedDataFiller getDataFiller() {
        return dataFiller;
    }
}
