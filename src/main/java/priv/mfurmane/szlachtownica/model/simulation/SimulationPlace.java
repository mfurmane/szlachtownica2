package priv.mfurmane.szlachtownica.model.simulation;

import priv.mfurmane.szlachtownica.model.config.ConfigurationPlace;
import priv.mfurmane.szlachtownica.model.main.ModelPlace;

public class SimulationPlace {
    private ModelPlace model;
    private ConfigurationPlace conf;

    public ModelPlace getModel() {
        return model;
    }

    public SimulationPlace setModel(ModelPlace model) {
        this.model = model;
        return this;
    }

    public ConfigurationPlace getConf() {
        return conf;
    }

    public SimulationPlace setConf(ConfigurationPlace conf) {
        this.conf = conf;
        return this;
    }
}
