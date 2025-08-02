package priv.mfurmane.szlachtownica.model.simulation;

import priv.mfurmane.szlachtownica.model.config.ConfigurationFamily;
import priv.mfurmane.szlachtownica.model.main.ModelFamily;

import java.util.ArrayList;
import java.util.List;

public class SimulationFamily {
    private ModelFamily model;
    private ConfigurationFamily conf;

    private final List<Long> aliveMembers = new ArrayList<>();
    private final List<Long> deadMembers = new ArrayList<>();
}
