package priv.mfurmane.szlachtownica.model.main;

import priv.mfurmane.szlachtownica.model.simulation.terrain.Climate;
import priv.mfurmane.szlachtownica.model.simulation.terrain.Humidity;

import java.util.ArrayList;
import java.util.List;

public class ModelSubProvince {
    private final List<ModelRegion> regions = new ArrayList<>();
    private final Climate climate;
    private final Humidity humidity;

    public ModelSubProvince(Climate climate, Humidity humidity) {
        this.climate = climate;
        this.humidity = humidity;
    }

    public List<ModelRegion> getRegions() {
        return regions;
    }

    public Climate getClimate() {
        return climate;
    }

    public Humidity getHumidity() {
        return humidity;
    }
}
