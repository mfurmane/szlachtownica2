package priv.mfurmane.szlachtownica.engine.matrimonial;

import priv.mfurmane.szlachtownica.model.simulation.SimulationPerson;

public interface MatrimonialPhase {

    void setNextPhase(SimulationPerson person);
    void fuck(SimulationPerson person);
    void findMarriage(SimulationPerson person);

}
