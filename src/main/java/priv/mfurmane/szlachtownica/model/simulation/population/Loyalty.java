package priv.mfurmane.szlachtownica.model.simulation.population;

public class Loyalty {
    private Long otherGroup;
    private double value;

    public Long getGreaterGroup() {
        return otherGroup;
    }

    public Loyalty setGreaterGroup(Long greaterGroup) {
        this.otherGroup = greaterGroup;
        return this;
    }

    public double getValue() {
        return value;
    }

    public Loyalty setValue(double value) {
        this.value = value;
        return this;
    }
}
