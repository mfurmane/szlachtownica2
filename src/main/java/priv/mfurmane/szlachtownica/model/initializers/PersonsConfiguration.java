package priv.mfurmane.szlachtownica.model.initializers;

import priv.mfurmane.szlachtownica.engine.MainEngine;
import priv.mfurmane.szlachtownica.model.Race;
import priv.mfurmane.szlachtownica.model.Sex;
import priv.mfurmane.szlachtownica.model.config.ConfigurationPerson;
import priv.mfurmane.szlachtownica.model.main.ModelPerson;
import priv.mfurmane.szlachtownica.model.simulation.SimulationFamily;
import priv.mfurmane.szlachtownica.model.simulation.SimulationPerson;

import java.time.LocalDate;
import java.util.List;

public class PersonsConfiguration {
    public PersonsConfiguration() {
    }

    static SimulationPerson mergeAndRegisterPerson(SimulationPerson person, ConfigurationPerson conf, ModelPerson model, List<SimulationPerson> persons) {
        person.setConf(conf);
        person.setModel(model);
        if (MainEngine.getInstance() != null) {
            MainEngine.getInstance().getPersonRegistry().register(person);
        }
        persons.add(person);
        return person;
    }

    public SimulationPerson initializeNamedPerson(String name, String middlename, int bornYear, List<SimulationPerson> persons) {
        SimulationPerson person = new SimulationPerson();
        ConfigurationPerson conf = ConfigurationPerson.builder()
                .build();
        ModelPerson model = ModelPerson.builder()
                .build();
        return PersonsConfiguration.mergeAndRegisterPerson(person, conf, model, persons);
    }

    SimulationPerson initializeTagara(List<SimulationPerson> persons, SimulationFamily family) {
        SimulationPerson person = new SimulationPerson();
        ConfigurationPerson conf = ConfigurationPerson.builder()
                //AGE PHASES
                .setHornyAge(16)
                .setStableAge(20)
                .setMarriageAge(18)
                .setMourningTime(16)
                //INTERPERSONAL
                .setAmorous(0.1)
                .setAttachment(0.9)
                .setDivorcable(0.01)
                .setHomo(0.1)
                .setHorny(0.7)
                .setImpulsive(0.1)
                .setInfluence(1.0)
                .setInterracial(0.5)
                .setJealous(0.4)
                .setLoyal(0.96)
                .setManipulative(0.3)
                .setPoliamoric(0.05)
                .setRevengous(0.1)
                .setVisibility(1.0)
                //PERSONAL
                .setAmbition(0.95)
                .setBeauty(0.6)
                .setCharisma(0.9)
                .setCunning(0.7)
                .setDiplomatic(0.9)
                .setHonor(0.9)
                .setIntellect(0.9)
                .setMoral(0.95)
                .setParanoid(0.4)
                .setProud(0.5)
                .setTemperament(0.3)
                //PHYSICAL
                .setFertility(0.7)
                .setHealth(0.7)
                .build();
        ModelPerson model = ModelPerson.builder()
                .setId(1L)
                .setName("Tagara")
                .setRace(Race.HUMAN)
                .setBorn(LocalDate.parse("1168-03-17"))
                .setDied(LocalDate.parse("1226-09-02"))
                .setMiddleName("I Budownicza")
                .setFamily(family.getModel())
                .setSex(Sex.FEMALE)
                .build();
        return mergeAndRegisterPerson(person, conf, model, persons);
    }

    public SimulationPerson initializeRiana(List<SimulationPerson> persons, SimulationFamily family) {
        return null;
    }

    public SimulationPerson initializeUrg(List<SimulationPerson> persons, SimulationFamily family) {
        return null;
    }

    public SimulationPerson initializeMerinaI(List<SimulationPerson> persons, SimulationFamily family) {
        return null;
    }

    public SimulationPerson initializeOrsa(List<SimulationPerson> persons, SimulationFamily family) {
        return null;
    }

    public SimulationPerson initializeFallonI(List<SimulationPerson> persons, SimulationFamily family) {
        return null;
    }

    public SimulationPerson initializeMerinaII(List<SimulationPerson> persons, SimulationFamily family) {
        return null;
    }

    public SimulationPerson initializeErwinI(List<SimulationPerson> persons, SimulationFamily tagar) {
        return null;
    }

    public SimulationPerson initializeEolI(List<SimulationPerson> persons, SimulationFamily tagar) {
        return null;
    }

    public SimulationPerson initializeMerinaIII(List<SimulationPerson> persons, SimulationFamily tagar) {
        return null;
    }

    public SimulationPerson initializeErwinII(List<SimulationPerson> persons, SimulationFamily tagar) {
        return null;
    }

    public SimulationPerson initializeEolII(List<SimulationPerson> persons, SimulationFamily tagar) {
        return null;
    }

    public SimulationPerson initializeRanalI(List<SimulationPerson> persons, SimulationFamily tagar) {
        return null;
    }

    public SimulationPerson initializeErwinIII(List<SimulationPerson> persons, SimulationFamily tagar) {
        return null;
    }

    public SimulationPerson initializeRoelekI(List<SimulationPerson> persons, SimulationFamily tagar) {
        return null;
    }

    public SimulationPerson initializeAleraI(List<SimulationPerson> persons, SimulationFamily tagar) {
        return null;
    }

    public SimulationPerson initializeGarvonI(List<SimulationPerson> persons, SimulationFamily tagar) {
        return null;
    }

    public SimulationPerson initializeGarvonII(List<SimulationPerson> persons, SimulationFamily holzer) {
        return null;
    }

    public SimulationPerson initializeOsaraI(List<SimulationPerson> persons, SimulationFamily holzer) {
        return null;
    }

    public SimulationPerson initializeLasteraI(List<SimulationPerson> persons, SimulationFamily holzer) {
        return null;
    }

    public SimulationPerson initializeGarvonIII(List<SimulationPerson> persons, SimulationFamily holzer) {
        return null;
    }

    public SimulationPerson initializeGalinaI(List<SimulationPerson> persons, SimulationFamily holzer) {
        return null;
    }

    public SimulationPerson initializeRanalII(List<SimulationPerson> persons, SimulationFamily holzer) {
        return null;
    }

    public SimulationPerson initializeMereinaI(List<SimulationPerson> persons, SimulationFamily holzer) {
        return null;
    }

    public SimulationPerson initializeAleraII(List<SimulationPerson> persons, SimulationFamily holzer) {
        return null;
    }

    public SimulationPerson initializeLasteraII(List<SimulationPerson> persons, SimulationFamily holzer) {
        return null;
    }

    public SimulationPerson initializeFallonII(List<SimulationPerson> persons, SimulationFamily holzer) {
        return null;
    }
}