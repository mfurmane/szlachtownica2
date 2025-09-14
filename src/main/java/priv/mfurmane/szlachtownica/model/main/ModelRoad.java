package priv.mfurmane.szlachtownica.model.main;

import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;
import priv.mfurmane.szlachtownica.engine.MainEngine;
import priv.mfurmane.szlachtownica.model.simulation.population.Affiliation;
import priv.mfurmane.szlachtownica.model.simulation.population.AffiliationType;
import priv.mfurmane.szlachtownica.model.simulation.population.Population;
import priv.mfurmane.szlachtownica.model.simulation.population.PopulationType;
import priv.mfurmane.szlachtownica.model.simulation.terrain.Material;
import priv.mfurmane.szlachtownica.model.simulation.terrain.PlaceCharacteristic;
import priv.mfurmane.szlachtownica.model.simulation.terrain.PlaceType;
import priv.mfurmane.szlachtownica.model.simulation.terrain.TerrainCharacteristic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModelRoad extends MapFeature {
    private final Populatable populatable = new Populatable();
    private final Map<Material, Double> partsWithMaterials = new HashMap<>();
    private Integer width = 1;
    private LineString line;

    public Integer getWidth() {
        return width;
    }

    public ModelRoad setWidth(Integer width) {
        this.width = width;
        return this;
    }

    public LineString getLine() {
        return line;
    }

    public ModelRoad setLine(LineString line) {
        this.line = line;
        return this;
    }

    public Populatable getPopulatable() {
        return populatable;
    }

    public Map<Material, Double> getPartsWithMaterials() {
        return partsWithMaterials;
    }
    //budynki z konkretnego materiału, oroentacyjnie

}
