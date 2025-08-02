package priv.mfurmane.szlachtownica.model.simulation.goal;

import org.springframework.stereotype.Component;
import priv.mfurmane.szlachtownica.engine.MainEngine;
import priv.mfurmane.szlachtownica.engine.events.CalendarEvent;
import priv.mfurmane.szlachtownica.engine.events.EventFactory;
import priv.mfurmane.szlachtownica.model.simulation.SimulationPerson;

import java.time.LocalDate;
import java.util.Map;
import java.util.function.BiFunction;

@Component
public class GoalEngine {
    private MainEngine engine;
    public void setEngine(MainEngine mainEngine) {
        this.engine = mainEngine;
    }

    public void registerGoal(SimulationPerson person, Goal goal, int strength, LocalDate until) {
        Map<Goal, Integer> goals = person.getGoals();
        goals.merge(goal, strength, Integer::sum);
        if (until != null) {
            CalendarEvent event = engine.getEventFactory().decreaseGoalEvent(person, goal, strength, until);
            engine.getEventManager().registerFutureEvent(event);
        }
    }

    

}
