package priv.mfurmane.szlachtownica.model.main;

import org.locationtech.jts.geom.Polygon;
import priv.mfurmane.szlachtownica.model.main.entities.EntityLake;

public class ModelSeaPart extends MapFeature {
    private String name;
    private Polygon area;

    public String getName() {
        return name;
    }

    public ModelSeaPart setName(String name) {
        this.name = name;
        return this;
    }

    public Polygon getArea() {
        return area;
    }

    public ModelSeaPart setArea(Polygon area) {
        this.area = area;
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
