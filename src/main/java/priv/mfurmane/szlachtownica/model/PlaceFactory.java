package priv.mfurmane.szlachtownica.model;

import org.springframework.stereotype.Component;
import priv.mfurmane.szlachtownica.engine.MainEngine;
import priv.mfurmane.szlachtownica.model.config.ConfigurationPlace;
import priv.mfurmane.szlachtownica.model.main.ModelPlace;
import priv.mfurmane.szlachtownica.model.simulation.SimulationPlace;

import java.util.Random;

@Component
public class PlaceFactory {
    private static final Random random = new Random();
    private MainEngine engine;

    public void setEngine(MainEngine mainEngine) {
        this.engine = mainEngine;
    }

    public SimulationPlace newPlace() {
        return createPlace(0.0,0.0);
    }

    public SimulationPlace newPlace(Double instability, Double growthPotential) {
        return createPlace(instability, growthPotential);
    }

    private SimulationPlace createPlace(Double instability, Double growthPotential) {
        SimulationPlace place = new SimulationPlace();
        ModelPlace model = getModel(instability, growthPotential);
        ConfigurationPlace conf = getConf();
        place.setModel(model);
        place.setConf(conf);
        return place;
    }

    private ConfigurationPlace getConf() {
        return ConfigurationPlace.builder().build();
    }

    private ModelPlace getModel(Double instability, Double growthPotential) {
        return ModelPlace.builder()
//                .setGrowthPotential(varyAboutStat(growthPotential))
//                .setInstability(varyAboutStat(instability))
                .build();
    }

    private Double varyAboutStat(Double value) {
        return Math.min(value * random.nextDouble(0.4,1.6), 0.8);
    }

}
