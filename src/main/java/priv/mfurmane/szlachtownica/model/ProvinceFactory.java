package priv.mfurmane.szlachtownica.model;

import org.springframework.stereotype.Component;
import priv.mfurmane.szlachtownica.engine.MainEngine;
import priv.mfurmane.szlachtownica.model.config.ConfigurationProvince;
import priv.mfurmane.szlachtownica.model.main.ModelProvince;
import priv.mfurmane.szlachtownica.model.simulation.SimulationProvince;
import priv.mfurmane.szlachtownica.model.simulation.terrain.Climate;
import priv.mfurmane.szlachtownica.model.simulation.terrain.Humidity;

import java.util.*;

@Component
public class ProvinceFactory {
    private static final Random random = new Random();
    private MainEngine engine;

    public void setEngine(MainEngine mainEngine) {
        this.engine = mainEngine;
    }

    public SimulationProvince newProvince() {
        return createProvince(0.0,0.0);
    }

    public SimulationProvince newProvince(Double instability, Double growthPotential) {
        return createProvince(instability, growthPotential);
    }

    private SimulationProvince createProvince(Double instability, Double growthPotential) {
        SimulationProvince province = new SimulationProvince();
        ModelProvince model = getModel(instability, growthPotential);
        ConfigurationProvince conf = getConf();
        province.setModel(model);
        province.setConf(conf);
        engine.getProvinceRegistry().register(province);
        return province;
    }

    private ConfigurationProvince getConf() {
        return ConfigurationProvince.builder().build();
    }

    private ModelProvince getModel(Double instability, Double growthPotential) {
        return ModelProvince.builder()
//                .setGrowthPotential(varyAboutStat(growthPotential))
//                .setInstability(varyAboutStat(instability))
                .build();
    }

    private Double varyAboutStat(Double value) {
        return Math.min(value * random.nextDouble(0.4,1.6), 0.8);
    }

}
