package priv.mfurmane.szlachtownica.model.main;

public class RegionConnection {
    private ModelRegion target;
    private Double distance;
    private Double travelDifficulty;
    private RegionConnectionType connectionType;

    public RegionConnection(ModelRegion target, Double distance, RegionConnectionType connectionType, Double travelDifficulty) {
        this.target = target;
        this.distance = distance;
        this.connectionType = connectionType;
    }

    public Double getTravelDifficulty() {
        return travelDifficulty;
    }

    public void setTravelDifficulty(Double travelDifficulty) {
        this.travelDifficulty = travelDifficulty;
    }

    public ModelRegion getTarget() {
        return target;
    }

    public void setTarget(ModelRegion target) {
        this.target = target;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public RegionConnectionType getConnectionType() {
        return connectionType;
    }

    public void setConnectionType(RegionConnectionType connectionType) {
        this.connectionType = connectionType;
    }
}
