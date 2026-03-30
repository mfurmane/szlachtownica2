package priv.mfurmane.szlachtownica.model.main;

import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.Polygon;

public class ModelLake extends MapFeature {
    private Integer width = 1;
    private Double depth = 0.4;
    private Polygon polygon;

    public Integer getWidth() {
        return width;
    }

    public ModelLake setWidth(Integer width) {
        this.width = width;
        return this;
    }

    public Double getDepth() {
        return depth;
    }

    public ModelLake setDepth(Double depth) {
        this.depth = depth;
        return this;
    }

    public Polygon getPolygon() {
        return polygon;
    }

    public ModelLake setPolygon(Polygon polygon) {
        this.polygon = polygon;
        return this;
    }
}
