package priv.mfurmane.szlachtownica.model.simulation;

import priv.mfurmane.szlachtownica.model.config.ConfigurationProvince;
import priv.mfurmane.szlachtownica.model.main.ModelProvince;

import java.util.ArrayList;
import java.util.List;

public class SimulationProvince {
    private ModelProvince model;
    private ConfigurationProvince conf;

    public ModelProvince getModel() {
        return model;
    }

    public SimulationProvince setModel(ModelProvince model) {
        this.model = model;
        return this;
    }

    public ConfigurationProvince getConf() {
        return conf;
    }

    public SimulationProvince setConf(ConfigurationProvince conf) {
        this.conf = conf;
        return this;
    }

//    public void init() {
//        int subRegions = conf.getRegions().length;
//        if (subRegions == 1) {
//
//        } else {
//            for (int i = 0; i < subRegions; i++) {
//                Integer regions = conf.getAreas().get(i);
//                Integer occupiedRegions = conf.getInitiallyOccupiedRegions()[i];
//                getModel().getAreas().add(new initializeSubProvinces(regions, occupiedRegions, this));
////                getModel().getRegions()
//            }
//        }
//    }
}
