package priv.mfurmane.szlachtownica.model;

import priv.mfurmane.szlachtownica.model.simulation.SimulationFamily;

public class VassalFrom {

    private SimulationFamily senior;
    private int year;

    public VassalFrom(SimulationFamily senior, int year) {
        this.senior = senior;
        this.year = year;
    }

    public SimulationFamily getSenior() {
        return senior;
    }

    public VassalFrom setSenior(SimulationFamily senior) {
        this.senior = senior;
        return this;
    }

    public int getYear() {
        return year;
    }

    public VassalFrom setYear(int year) {
        this.year = year;
        return this;
    }
}
