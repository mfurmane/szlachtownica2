package priv.mfurmane.szlachtownica.model.main;

import org.locationtech.jts.geom.Point;
import priv.mfurmane.szlachtownica.model.simulation.population.Population;
import priv.mfurmane.szlachtownica.model.simulation.terrain.PlaceState;

import java.util.ArrayList;
import java.util.List;

public class Populatable {
    private final List<Population> populations = new ArrayList<>();
    private Double instability;

    public List<Population> getPopulations() {
        return populations;
    }

    public Double getInstability() {
        return instability;
    }

    public Populatable setInstability(Double instability) {
        this.instability = instability;
        return this;
    }
}
