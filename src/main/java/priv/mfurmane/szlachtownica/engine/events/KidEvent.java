package priv.mfurmane.szlachtownica.engine.events;

import priv.mfurmane.szlachtownica.model.simulation.SimulationPerson;

import java.util.Calendar;

public class KidEvent extends CalendarEvent {

//    private final SimulationPerson kid;

    protected KidEvent(Calendar time, SimulationPerson person, SimulationPerson kid) {
        super(time, person);
//        this.kid = kid;
    }

    @Override
    public void act() {

    }

//    public SimulationPerson getKid() {
//        return kid;
//    }
}
