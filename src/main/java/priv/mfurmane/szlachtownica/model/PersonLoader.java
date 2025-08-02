package priv.mfurmane.szlachtownica.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import priv.mfurmane.szlachtownica.engine.MainEngine;
import priv.mfurmane.szlachtownica.model.config.ConfigurationPerson;
import priv.mfurmane.szlachtownica.model.config.FromFilePerson;
import priv.mfurmane.szlachtownica.model.simulation.SimulationPerson;

import java.io.File;
import java.io.IOException;

@Component
public class PersonLoader {
    private MainEngine engine;

    private final ObjectMapper mapper = new ObjectMapper();

    public PersonLoader() {
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    }

    public void setEngine(MainEngine mainEngine) {
        this.engine = mainEngine;
    }

    public SimulationPerson readPerson(File file) throws IOException {
        Race race = Race.valueOf(file.getName().split("#")[0]);
        SimulationPerson person = engine.getPersonFactory().newPerson(race);
        FromFilePerson fromFile = mapper.readValue(file, FromFilePerson.class);
        person.getConf().mergeFrom(fromFile.getConf());
        person.getModel().mergeFrom(fromFile.getModel());
        engine.getEventManager().registerEventsFor(fromFile);
        engine.getPersonRegistry().register(person);
        return person;
    }

}
