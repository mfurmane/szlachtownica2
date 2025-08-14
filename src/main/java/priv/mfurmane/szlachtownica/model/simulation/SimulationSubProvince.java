package priv.mfurmane.szlachtownica.model.simulation;

import priv.mfurmane.szlachtownica.model.config.ConfigurationProvince;
import priv.mfurmane.szlachtownica.model.config.ConfigurationSubProvince;
import priv.mfurmane.szlachtownica.model.main.ModelProvince;
import priv.mfurmane.szlachtownica.model.main.ModelSubProvince;

public class SimulationSubProvince {
    private ModelSubProvince model;
    private ConfigurationSubProvince conf;

    public ModelSubProvince getModel() {
        return model;
    }

    public SimulationSubProvince setModel(ModelSubProvince model) {
        this.model = model;
        return this;
    }

    public ConfigurationSubProvince getConf() {
        return conf;
    }

    public SimulationSubProvince setConf(ConfigurationSubProvince conf) {
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
