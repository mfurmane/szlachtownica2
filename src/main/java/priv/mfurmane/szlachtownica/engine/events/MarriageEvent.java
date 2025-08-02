package priv.mfurmane.szlachtownica.engine.events;

import priv.mfurmane.szlachtownica.model.simulation.SimulationPerson;

import java.util.Calendar;

public class MarriageEvent extends CalendarEvent {
    private final SimulationPerson oneSide;
    private final SimulationPerson twoSide;

    protected MarriageEvent(Calendar time, SimulationPerson oneSide, SimulationPerson twoSide) {
        super(time, oneSide);
        this.oneSide = oneSide;
        this.twoSide = twoSide;
    }

    @Override
    public void act() {
        //olać owner, użyć oneSide i twoSide

    }

    public SimulationPerson getOneSide() {
        return oneSide;
    }

    public SimulationPerson getTwoSide() {
        return twoSide;
    }
}
