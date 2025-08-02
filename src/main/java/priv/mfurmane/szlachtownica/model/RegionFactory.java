package priv.mfurmane.szlachtownica.model;

import org.springframework.stereotype.Component;
import priv.mfurmane.szlachtownica.engine.MainEngine;
import priv.mfurmane.szlachtownica.model.config.ConfigurationRegion;
import priv.mfurmane.szlachtownica.model.main.ModelPlace;
import priv.mfurmane.szlachtownica.model.main.ModelRegion;
import priv.mfurmane.szlachtownica.model.simulation.terrain.Climate;
import priv.mfurmane.szlachtownica.model.simulation.terrain.EnchantType;
import priv.mfurmane.szlachtownica.model.simulation.terrain.Humidity;

import java.util.List;
import java.util.Random;

@Component
public class RegionFactory {
    private static final Random rand = new Random();
    private MainEngine engine;

    public void setEngine(MainEngine mainEngine) {
        this.engine = mainEngine;
    }

    private ModelRegion getModel(Humidity[] humidityRange, Climate[] climateRange, List<ModelPlace> places) {

        return ModelRegion.builder()
                .setClimate(climateRange[rand.nextInt(climateRange.length)])
                .setHumidity(humidityRange[rand.nextInt(humidityRange.length)])
                .setEnchant(rand.nextDouble() < 0.01 ? EnchantType.values()[EnchantType.values().length] : EnchantType.NONE)
                .setPlaces(places)
//                .setType()
//                .setNaturalResources()
                .build();
    }

    private ConfigurationRegion getConf() {
        return null;
//        return ConfigurationRegion.builder()
//
//                .build();
    }

    public static void main(String[] args) {

//        Climate climate;


    }

}
