package priv.mfurmane.szlachtownica.engine.events;

import priv.mfurmane.szlachtownica.model.simulation.SimulationPerson;

import java.util.Calendar;

public class InseminationEvent extends CalendarEvent {
//    private final SimulationPerson kid;

    protected InseminationEvent(Calendar time, SimulationPerson person) {
        super(time, person);
//        this.kid = person.getEngine().getPersonFactory().
    }

    protected InseminationEvent(Calendar time, SimulationPerson person, SimulationPerson kid) {
        super(time, person);
//        this.kid = kid;
    }

    @Override
    public void act() {
//        SimulationPerson mother = getPerson().getRelations().getMother();
//        mother.insemination();
    }

}
