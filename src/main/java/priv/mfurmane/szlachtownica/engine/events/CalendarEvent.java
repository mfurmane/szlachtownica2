package priv.mfurmane.szlachtownica.engine.events;

import priv.mfurmane.szlachtownica.model.simulation.SimulationPerson;

import java.util.Calendar;

public abstract class CalendarEvent {
    private final Calendar time;
    private final SimulationPerson person;

    public abstract void act();

    protected CalendarEvent(Calendar time, SimulationPerson person) {
        this.time = time;
        this.person = person;
    }

    public Calendar getTime() {
        return time;
    }

    public SimulationPerson getPerson() {
        return person;
    }
}
