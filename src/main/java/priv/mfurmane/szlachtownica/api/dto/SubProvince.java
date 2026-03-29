package priv.mfurmane.szlachtownica.api.dto;

import org.wololo.geojson.Geometry;
import priv.mfurmane.szlachtownica.model.simulation.terrain.Climate;
import priv.mfurmane.szlachtownica.model.simulation.terrain.Humidity;

import java.util.ArrayList;
import java.util.List;

public class SubProvince {
    private Long id;
    private Geometry area;
    private List<Region> regions = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public SubProvince setId(Long id) {
        this.id = id;
        return this;
    }

    public Geometry getArea() {
        return area;
    }

    public SubProvince setArea(Geometry area) {
        this.area = area;
        return this;
    }

    public List<Region> getRegions() {
        return regions;
    }

    public SubProvince setRegions(List<Region> regions) {
        this.regions = regions;
        return this;
    }
}
