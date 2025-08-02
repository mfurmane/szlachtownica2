package priv.mfurmane.szlachtownica.engine.events;

import priv.mfurmane.szlachtownica.model.simulation.SimulationPerson;

import java.util.Calendar;

public class DeathEvent extends CalendarEvent {

    protected DeathEvent(Calendar time, SimulationPerson person) {
        super(time, person);
    }

    @Override
    public void act() {

    }
}
