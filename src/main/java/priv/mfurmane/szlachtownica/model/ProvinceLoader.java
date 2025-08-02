package priv.mfurmane.szlachtownica.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import priv.mfurmane.szlachtownica.engine.MainEngine;
import priv.mfurmane.szlachtownica.model.config.FromFileProvince;
import priv.mfurmane.szlachtownica.model.simulation.SimulationProvince;

import java.io.File;
import java.io.IOException;

@Component
public class ProvinceLoader {
    private MainEngine engine;

    private final ObjectMapper mapper = new ObjectMapper();

    public ProvinceLoader() {
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    }

    public void setEngine(MainEngine mainEngine) {
        this.engine = mainEngine;
    }

    public SimulationProvince readProvince(File file) throws IOException {
        SimulationProvince province = engine.getProvinceFactory().newProvince();
        FromFileProvince fromFile = mapper.readValue(file, FromFileProvince.class);
        province.getConf().mergeFrom(fromFile.getConf());
        province.getModel().mergeFrom(fromFile.getModel());
        province.getConf().getSubProvinces().forEach(subProvince -> {
            province.getModel().initializeSubProvinces(subProvince, province.getConf());
        });
//        engine.getEventManager().registerEventsFor(fromFile);
//        engine.getPersonRegistry().register(person);
        return province;
    }

}
