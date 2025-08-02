package priv.mfurmane.szlachtownica.engine.events;

import org.springframework.stereotype.Component;
import priv.mfurmane.szlachtownica.engine.MainEngine;
import priv.mfurmane.szlachtownica.model.simulation.SimulationPerson;
import priv.mfurmane.szlachtownica.model.simulation.goal.Goal;

import java.time.LocalDate;
import java.util.Calendar;

@Component
public class EventFactory {
    private MainEngine engine;

    public CalendarEvent deathEvent(Calendar time, SimulationPerson person) {
        return new DeathEvent(time, person);
    }
    public CalendarEvent familyEndEvent(Calendar time, SimulationPerson person) {
        return new FamilyEndEvent(time, person);
    }
    public CalendarEvent inseminationEvent(Calendar time, SimulationPerson person) {
        return new InseminationEvent(time, person);
    }
    public CalendarEvent marriageEvent(Calendar time, SimulationPerson person, SimulationPerson otherPerson) {
        return new MarriageEvent(time, person, otherPerson);
    }
    public CalendarEvent decreaseGoalEvent(SimulationPerson person, Goal goal, int strength, LocalDate until) {
        return null;
    }
//    public CalendarEvent predefinedKidEvent(Calendar time, Person person, Person kid) {
//        return new KidEvent(time, person, kid);
//    }




    public void setEngine(MainEngine mainEngine) {
        this.engine = mainEngine;
    }
}
