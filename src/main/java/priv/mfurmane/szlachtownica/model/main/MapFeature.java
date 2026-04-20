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

public abstract class MapFeature {
    protected Long id;
    protected Long regionId;
    protected final List<ModelRegion> regions = new ArrayList<>();

    private final List<PlaceCharacteristic> characteristics = new ArrayList<>();
    protected Geometry mainGeometry;

    public Long getId() {
        return id;
    }

    public SimulationRegion region() {
        return MainEngine.getInstance().getRegionRegistry().get(regionId);
    }
    public Long getRegion() {
        return regionId;
    }

    public MapFeature setRegionId(Long regionId) {
        this.regionId = regionId;
        return this;
    }

    public List<PlaceCharacteristic> getCharacteristics() {
        return characteristics;
    }

    public Geometry getMainGeometry() {
        return mainGeometry;
    }

    public MapFeature setMainGeometry(Geometry mainGeometry) {
        this.mainGeometry = mainGeometry;
        return this;
    }

    public List<ModelRegion> getRegions() {
        return regions;
    }
}
