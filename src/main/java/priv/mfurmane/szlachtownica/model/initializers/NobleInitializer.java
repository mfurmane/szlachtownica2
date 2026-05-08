package priv.mfurmane.szlachtownica.model.initializers;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import priv.mfurmane.szlachtownica.engine.MainEngine;
import priv.mfurmane.szlachtownica.engine.naming.ernizjum.ErnizjumPhonotactic;
import priv.mfurmane.szlachtownica.engine.naming.model.Phonotactic;
import priv.mfurmane.szlachtownica.engine.naming.nereneth.NerenethPhonotactic;
import priv.mfurmane.szlachtownica.model.Race;
import priv.mfurmane.szlachtownica.model.Sex;
import priv.mfurmane.szlachtownica.model.config.ConfigurationPerson;
import priv.mfurmane.szlachtownica.model.main.ModelPerson;
import priv.mfurmane.szlachtownica.model.simulation.SimulationFamily;
import priv.mfurmane.szlachtownica.model.simulation.SimulationPerson;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class NobleInitializer {
    private MainEngine engine;

    private final Phonotactic ernizjumPhonotactic = new ErnizjumPhonotactic();
    private final Phonotactic nerenethPhonotactic = new NerenethPhonotactic();

    private final ObjectMapper mapper = new ObjectMapper();

    public NobleInitializer() {
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    }

    public void setEngine(MainEngine mainEngine) {
        this.engine = mainEngine;
    }

    public void initializeNobles() {
        List<SimulationFamily> families = new ArrayList<>();
        SimulationFamily tagar = initializeTagar(families);

        List<SimulationPerson> persons = new ArrayList<>();
        SimulationPerson tagara = initializeTagara(persons, tagar);

        persons.forEach(person -> {
            System.out.println("# " + person.getModel().getName());
        });

    }

    private SimulationFamily initializeTagar(List<SimulationFamily> families) {
        SimulationFamily family = new SimulationFamily();

        families.add(family);
        return family;
    }

    public SimulationPerson initializeNamedPerson(String name, String middlename, int bornYear, List<SimulationPerson> persons) {
        SimulationPerson person = new SimulationPerson();
        ConfigurationPerson conf = ConfigurationPerson.builder()
                .build();
        ModelPerson model = ModelPerson.builder()
                .build();
        return mergeAndRegister(person, conf, model, persons);
    }

    public static void main(String[] args) {
        NobleInitializer initializer = new NobleInitializer();

        for (int i = 0; i < 500; i++) {
//            System.out.println(ElvenNameGenerator.getMaleName() + " " + ElvenNameGenerator.getInnername(CharacterTrait.getRandom()) + " " + ElvenNameGenerator.getSurname());
//            System.out.println(ElvenNameGenerator.getFemaleName() + " " + ElvenNameGenerator.getInnername(CharacterTrait.getRandom()) + " " + ElvenNameGenerator.getSurname());
//            System.out.println(DwarvenNameGenerator.getMaleName() + " " + DwarvenNameGenerator.getMaleFather(CharacterTrait.getRandom()) + " " + DwarvenNameGenerator.getSurname());
//            System.out.println(DwarvenNameGenerator.getFemaleName() + " " + DwarvenNameGenerator.getFemaleFather(CharacterTrait.getRandom()) + " " + DwarvenNameGenerator.getSurname());

//            System.out.println(initializer.determineName(false));
        }
    }

    private SimulationPerson initializeTagara(List<SimulationPerson> persons, SimulationFamily tagar) {
        SimulationPerson person = new SimulationPerson();
        ConfigurationPerson conf = ConfigurationPerson.builder()

                .build();
        ModelPerson model = ModelPerson.builder()
                .setId(1L)
                .setName("Tagara")
                .setRace(Race.HUMAN)
                .setBorn(LocalDate.parse("1168-03-17"))
                .setDied(LocalDate.parse("1226-09-02"))
                .setMiddleName("I Budownicza")
                .setFamily(tagar.getModel())
                .setSex(Sex.FEMALE)
                .build();
        return mergeAndRegister(person, conf, model, persons);
    }

    private static SimulationPerson mergeAndRegister(SimulationPerson person, ConfigurationPerson conf, ModelPerson model, List<SimulationPerson> persons) {
        person.setConf(conf);
        person.setModel(model);
        if (MainEngine.getInstance() != null) {
            MainEngine.getInstance().getPersonRegistry().register(person);
        }
        persons.add(person);
        return person;
    }
}
