package priv.mfurmane.szlachtownica.model.main;

import priv.mfurmane.szlachtownica.engine.MainEngine;
import priv.mfurmane.szlachtownica.model.simulation.SimulationProvince;
import priv.mfurmane.szlachtownica.model.simulation.SimulationRegion;
import priv.mfurmane.szlachtownica.model.simulation.terrain.Climate;
import priv.mfurmane.szlachtownica.model.simulation.terrain.Humidity;

import java.util.ArrayList;
import java.util.List;

public class ModelSubProvince {
    private Long id;
    private Long provinceId;
    private final List<Long> regions = new ArrayList<>();
    private final Climate climate;
    private final Humidity humidity;

    public ModelSubProvince(Climate climate, Humidity humidity) {
        this.climate = climate;
        this.humidity = humidity;
    }

    public Long getId() {
        return id;
    }

    public List<SimulationRegion> regions() {
        return regions.stream().map(id -> MainEngine.getInstance().getRegionRegistry().get(id)).toList();
    }

    public List<Long> getRegions() {
        return regions;
    }

    public SimulationProvince province() {
        return MainEngine.getInstance().getProvinceRegistry().get(provinceId);
    }

    public Long getProvince() {
        return provinceId;
    }

    public Climate getClimate() {
        return climate;
    }

    public Humidity getHumidity() {
        return humidity;
    }
}
