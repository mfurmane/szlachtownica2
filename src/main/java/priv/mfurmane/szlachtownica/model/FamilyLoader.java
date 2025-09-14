package priv.mfurmane.szlachtownica.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import priv.mfurmane.szlachtownica.engine.MainEngine;
import priv.mfurmane.szlachtownica.model.config.FromFilePerson;
import priv.mfurmane.szlachtownica.model.simulation.SimulationFamily;
import priv.mfurmane.szlachtownica.model.simulation.SimulationPerson;
import priv.mfurmane.szlachtownica.model.simulation.population.Group;
import priv.mfurmane.szlachtownica.model.simulation.population.GroupType;

import java.io.File;
import java.io.IOException;

@Component
public class FamilyLoader {
    private MainEngine engine;

    public void setEngine(MainEngine mainEngine) {
        this.engine = mainEngine;
    }

    private final ObjectMapper mapper = new ObjectMapper();

    public FamilyLoader() {
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    }

    public SimulationFamily readPerson(File file) throws IOException {
//        Race race = Race.valueOf(file.getName().split("#")[0]);
//        SimulationPerson person = engine.getPersonFactory().newPerson(race);
//        FromFilePerson fromFile = mapper.readValue(file, FromFilePerson.class);
//        person.getConf().mergeFrom(fromFile.getConf());
//        person.getModel().mergeFrom(fromFile.getModel());
//        engine.getEventManager().registerEventsFor(fromFile);
//        engine.getPersonRegistry().register(person);
//        return person;
        SimulationFamily family = new SimulationFamily();
//        family.getConf().mergeFrom(fromFile.getConf());
//        family.getModel().mergeFrom(fromFile.getModel());

        registerFamilyGroup(family);
        return family;
    }

    public void registerFamilyGroup(SimulationFamily family) {
        Group group = new Group();
        group.setType(GroupType.FAMILY);

    }

}
