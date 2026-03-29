package priv.mfurmane.szlachtownica.model.main;

import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;
import priv.mfurmane.szlachtownica.engine.MainEngine;
import priv.mfurmane.szlachtownica.model.simulation.SimulationRegion;
import priv.mfurmane.szlachtownica.model.simulation.population.Affiliation;
import priv.mfurmane.szlachtownica.model.simulation.population.AffiliationType;
import priv.mfurmane.szlachtownica.model.simulation.population.Population;
import priv.mfurmane.szlachtownica.model.simulation.population.PopulationType;
import priv.mfurmane.szlachtownica.model.simulation.terrain.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModelPlace {
    private Long id;
    private PlaceType type;
    private String name;
    private final Populatable populatable = new Populatable();
    private Integer populationLimit;
    private Point location;
    private Polygon area;

    public Long getId() {
        return id;
    }

    public ModelPlace setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ModelPlace setName(String name) {
        this.name = name;
        return this;
    }

    public Polygon getArea() {
        return area;
    }

    public ModelPlace setArea(Polygon area) {
        this.area = area;
        return this;
    }

    public Point getLocation() {
        return location;
    }

    public ModelPlace setLocation(Point location) {
        this.location = location;
        return this;
    }

    public PlaceType getType() {
        return type;
    }

    public ModelPlace setType(PlaceType type) {
        this.type = type;
        return this;
    }

    public Populatable getPopulatable() {
        return populatable;
    }

    public Integer getPopulationLimit() {
        return populationLimit;
    }

    public ModelPlace setPopulationLimit(Integer populationLimit) {
        this.populationLimit = populationLimit;
        return this;
    }
}
