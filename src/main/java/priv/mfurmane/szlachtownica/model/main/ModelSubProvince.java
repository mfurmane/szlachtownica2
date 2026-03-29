package priv.mfurmane.szlachtownica.model.main;

import org.locationtech.jts.geom.Polygon;
import priv.mfurmane.szlachtownica.engine.MainEngine;
import priv.mfurmane.szlachtownica.model.main.entities.EntitySubProvince;
import priv.mfurmane.szlachtownica.model.simulation.SimulationProvince;
import priv.mfurmane.szlachtownica.model.simulation.SimulationRegion;
import priv.mfurmane.szlachtownica.model.simulation.terrain.Climate;
import priv.mfurmane.szlachtownica.model.simulation.terrain.Humidity;

import java.util.ArrayList;
import java.util.List;

public class ModelSubProvince {

    private Long id;

    private Polygon area; //not null

    private ModelProvince province;

    private final List<ModelRegion> regions = new ArrayList<>();

    private Climate climate;

    private Humidity humidity;

//    public ModelSubProvince() {}

    public ModelSubProvince(Climate climate, Humidity humidity, EntitySubProvince sub) {
        this.id = sub.getId();
        this.climate = climate;
        this.humidity = humidity;
    }

    public Polygon getArea() {
        return area;
    }

    public ModelSubProvince setArea(Polygon area) {
        this.area = area;
        return this;
    }

    public Long getId() {
        return id;
    }

    public List<SimulationRegion> regions() {
        return regions.stream().map(id -> MainEngine.getInstance().getRegionRegistry().get(id.getId())).toList();
    }

    public List<ModelRegion> getRegions() {
        return regions;
    }

    public SimulationProvince province() {
        return MainEngine.getInstance().getProvinceRegistry().get(province.getId());
    }

    public ModelProvince getProvince() {
        return province;
    }

    public Climate getClimate() {
        return climate;
    }

    public Humidity getHumidity() {
        return humidity;
    }
}
