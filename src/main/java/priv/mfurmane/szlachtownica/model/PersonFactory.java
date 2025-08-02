package priv.mfurmane.szlachtownica.model;

import org.springframework.stereotype.Component;
import priv.mfurmane.szlachtownica.engine.MainEngine;
import priv.mfurmane.szlachtownica.model.config.ConfigurationPerson;
import priv.mfurmane.szlachtownica.model.main.ModelPerson;
import priv.mfurmane.szlachtownica.model.simulation.SimulationPerson;

import java.time.LocalDate;
import java.util.Random;

@Component
public class PersonFactory {
    private static final Random rand = new Random();
    private MainEngine engine;

    public void setEngine(MainEngine mainEngine) {
        this.engine = mainEngine;
    }

    private SimulationPerson createPerson(Race race, LocalDate born, Long family, Long father, Long mother) {
        SimulationPerson person = new SimulationPerson();
        ModelPerson model = getModel(race, born, family, father, mother);
        person.setModel(model);
        person.setConf(getConf(race, false));
        person.initialize();
        return person;
    }

    public SimulationPerson newPerson(Race race, LocalDate born, Long family, Long father, Long mother) {
        return createPerson(race, born, family, father, mother);
    }

    public SimulationPerson newPerson(Race race) {
        //TODO
        return createPerson(race, null, null, null, null);
//        SimulationPerson person = new SimulationPerson();
//        ModelPerson model = getModel(race, null, null, null, null);
//        person.setModel(model);
//        person.setConf(getConf(race, false));
//        person.initialize();
//        return person;
    }

    public SimulationPerson newPerson(Race race, LocalDate currentDate, int estimatedAge) {
        //TODO
        LocalDate born = currentDate.minusYears(varyAboutAge(estimatedAge));
        return createPerson(race, born, null, null, null);
//        SimulationPerson person = new SimulationPerson();
//        ModelPerson model = getModel(race, born, null, null, null);
//        person.setModel(model);
//        person.setConf(getConf(race, false));
//        person.initialize();
//        return person;
    }

    public SimulationPerson newPlebs(Race race, LocalDate currentDate, int estimatedAge) {
        //TODO
        LocalDate born = currentDate.minusYears(varyAboutAge(estimatedAge));
        return createPerson(race, born, null, null, null);
//        SimulationPerson person = new SimulationPerson();
//        person.setModel(getModel(race, born, null, null, null));
//        person.setConf(getConf(race, true));
//        person.initialize();
//        return person;
    }

    private ModelPerson getModel(Race race, LocalDate born, Long family, Long father, Long mother) {
        Sex sex = rand.nextBoolean() ? Sex.FEMALE : Sex.MALE;
        return ModelPerson.builder()
                .setBorn(born)
                .setRace(race)
                .setSex(sex)
                .setName(engine.getNamingProvider().get(race).getName(sex))
// TODO                .setMiddleName()
                .setFamilyId(family)
                .setFatherId(father)
                .setMotherId(mother)
                .build();
    }

    private ConfigurationPerson getConf(Race race, boolean plebs) {
        int lifespan = race.getProfile().getLifespan();
        return ConfigurationPerson.builder()
                .setPlebs(plebs)
                .setPlebsSurname(plebs ? race.getProfile().getNamingStrategy().getSurname() : null)
                .setHornyAge(varyAboutAge(lifespan, race.getProfile().getHornyAge(), 10))
                .setStableAge(varyAboutAge(lifespan, race.getProfile().getStableAge(), 16))
                .setMarriageAge(varyAboutAge(lifespan, race.getProfile().getMarriageAge(), 14))
                .setMourningTime(varyAboutAge(100, race.getProfile().getMourningTime(), 12))
                .setHorny(varyAboutStat(race.getProfile().getHorny()))
                .setLoyal(varyAboutStat(race.getProfile().getLoyal()))
                .setHomo(varyAboutStat(race.getProfile().getHomo()))
                .setInterracial(varyAboutStat(race.getProfile().getInterracial()))
                .setAttachment(varyAboutStat(race.getProfile().getAttachment()))
                .setAmorous(varyAboutStat(race.getProfile().getAmorous()))
                .setPoliamoric(varyAboutStat(race.getProfile().getPoliamoric()))
                .setJealous(varyAboutStat(race.getProfile().getJealous()))
                .setImpulsive(varyAboutStat(race.getProfile().getImpulsive()))
                .setTemperament(varyAboutStat(race.getProfile().getTemperament()))
                .setProud(varyAboutStat(race.getProfile().getProud()))
                .setParanoid(varyAboutStat(race.getProfile().getParanoid()))
                .setAmbition(varyAboutStat(race.getProfile().getAmbition()))
                .setDivorcable(varyAboutStat(race.getProfile().getDivorcable()))
                .setRevengous(varyAboutStat(race.getProfile().getRevengous()))
                .setHonor(varyAboutStat(race.getProfile().getHonor()))
                .setDiplomatic(varyAboutStat(race.getProfile().getDiplomatic()))
                .setManipulative(varyAboutStat(race.getProfile().getManipulative()))
                .setCunning(varyAboutStat(race.getProfile().getCunning()))
                .setMoral(varyAboutStat(race.getProfile().getMoral()))
                .setCharisma(varyAboutStat(race.getProfile().getCharisma()))
                .setIntellect(varyAboutStat(race.getProfile().getIntellect()))
                .setBeauty(varyAboutStat(race.getProfile().getBeauty()))
                .setFertility(varyAboutStat(race.getProfile().getFertility()))
                .setHealth(varyAboutStat(race.getProfile().getHealth()))
                .setInfluence(varyAboutStat(0.5))
//                .setVisibility(varyAboutStat(race.getProfile().))
                .build();
    }

    private Double varyAboutStat(Double value) {
        return Math.min(value * rand.nextDouble(0.4,1.6), 0.95);
    }

    private Integer varyAboutAge(int lifespan, Integer value, Integer minValue) {
        int newValue = rand.nextInt((int) (value - 0.025 * lifespan), (int) (value + 0.025 * lifespan));
        return Math.max(minValue, newValue);
    }

    private long varyAboutAge(int estimatedAge) {
        return rand.nextInt(estimatedAge - 4, estimatedAge + 4);
    }

}
