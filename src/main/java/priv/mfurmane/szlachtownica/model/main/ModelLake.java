package priv.mfurmane.szlachtownica.model.main;

import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.Polygon;
import priv.mfurmane.szlachtownica.model.main.entities.EntityLake;
import priv.mfurmane.szlachtownica.model.main.entities.EntityRiver;

public class ModelLake extends MapFeature {
    private String name;
    private Double depth = 0.4;
    private Polygon area;

    public ModelLake(String name) {
        this.name = name;
    }

    public Double getDepth() {
        return depth;
    }

    public ModelLake setDepth(Double depth) {
        this.depth = depth;
        return this;
    }

    public Polygon getArea() {
        return area;
    }

    public ModelLake setArea(Polygon area) {
        this.area = area;
        return this;
    }

    public String getName() {
        return name;
    }

    public ModelLake setName(String name) {
        this.name = name;
        return this;
    }

    public EntityLake toEntity() {
        EntityLake lake = new EntityLake();
        lake.setId(id);
        lake.setName(name);
        lake.setArea(area);
        return lake;
    }
}
