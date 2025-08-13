package priv.mfurmane.szlachtownica.model.simulation.terrain;

public interface Material {

    MaterialStats getStats();
    void setStats(MaterialStats stats);

    void initializeAsMaterial();

}
