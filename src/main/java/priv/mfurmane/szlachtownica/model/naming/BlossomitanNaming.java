package priv.mfurmane.szlachtownica.model.naming;

import priv.mfurmane.szlachtownica.model.Sex;

public class BlossomitanNaming extends NamingStrategy {

    private static final String[] blossomPart = {"Bu", "Bo", "Ba", "Be", "Du", "Do", "Da", "De", "Gu", "Go", "Ga", "Ge", "Mu", "Mo", "Ma", "Me"};

    public BlossomitanNaming() {
        super(new String[0],new String[0],new String[0]);
    }

    public String getName() {
        //TODO klejenie z rodowodu
        return "Bobo";
    }

}
