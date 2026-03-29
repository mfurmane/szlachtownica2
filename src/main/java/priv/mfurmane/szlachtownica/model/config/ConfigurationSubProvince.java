package priv.mfurmane.szlachtownica.model.config;

import priv.mfurmane.szlachtownica.model.simulation.terrain.Climate;
import priv.mfurmane.szlachtownica.model.simulation.terrain.Humidity;

public class ConfigurationSubProvince {
    private int regionsCount;
    private int initiallyOccupied;
    private boolean coast;
    private Climate climate;
    private Humidity humidity;

    public ConfigurationSubProvince() { }

    public ConfigurationSubProvince(int regionsCount, int initiallyOccupied, Climate climate, Humidity humidity, Boolean coast) {
        this.regionsCount = regionsCount;
        this.initiallyOccupied = initiallyOccupied;
        this.climate = climate;
        this.humidity = humidity;
        this.coast = coast;
    }

    public boolean isCoast() {
        return coast;
    }

    public int getRegionsCount() {
        return regionsCount;
    }

    public int getInitiallyOccupied() {
        return initiallyOccupied;
    }

    public Climate getClimate() {
        return climate;
    }

    public Humidity getHumidity() {
        return humidity;
    }
}
