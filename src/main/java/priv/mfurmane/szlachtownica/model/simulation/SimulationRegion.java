package priv.mfurmane.szlachtownica.model.simulation;

import priv.mfurmane.szlachtownica.model.config.ConfigurationRegion;
import priv.mfurmane.szlachtownica.model.main.ModelRegion;

public class SimulationRegion {
    private ModelRegion modelRegion;
    private ConfigurationRegion configurationRegion;

    public ModelRegion getModelRegion() {
        return modelRegion;
    }

    public SimulationRegion setModelRegion(ModelRegion modelRegion) {
        this.modelRegion = modelRegion;
        return this;
    }

    public ConfigurationRegion getConfigurationRegion() {
        return configurationRegion;
    }

    public SimulationRegion setConfigurationRegion(ConfigurationRegion configurationRegion) {
        this.configurationRegion = configurationRegion;
        return this;
    }
}
