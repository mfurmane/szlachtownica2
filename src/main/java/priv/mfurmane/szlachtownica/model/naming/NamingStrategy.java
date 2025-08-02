package priv.mfurmane.szlachtownica.model.naming;

import priv.mfurmane.szlachtownica.model.Sex;
import priv.mfurmane.szlachtownica.model.config.ConfigurationPerson;

import java.util.Random;

public class NamingStrategy {
    private final Random rand = new Random();
    private final String[] maleNames;
    private final String[] femaleNames;
    private final String[] surnames;

    public NamingStrategy(String[] maleNames, String[] femaleNames, String[] surnames) {
        this.maleNames = maleNames;
        this.femaleNames = femaleNames;
        this.surnames = surnames;
    }

    public String getName(Sex sex) {
        return switch (sex) {
            case MALE -> maleNames[rand.nextInt(maleNames.length)];
            case FEMALE -> femaleNames[rand.nextInt(femaleNames.length)];
            case DICK_KNOWS -> surnames[rand.nextInt(surnames.length)];
        };
    }

    public String getSurname() {
        return surnames[rand.nextInt(surnames.length)];
    }
}
