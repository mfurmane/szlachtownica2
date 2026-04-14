package priv.mfurmane.szlachtownica.model.main;

import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.Polygon;
import priv.mfurmane.szlachtownica.model.main.entities.EntityLake;
import priv.mfurmane.szlachtownica.model.main.entities.EntityMountains;

public class ModelMountains extends MapFeature {
    private String name;
    private Double height = 1000;
    private Polygon area;
    private LineString line;

    public ModelMountains(String name) {
        this.name = name;
    }

    public Double getHeight() {
        return height;
    }

    public ModelMountains setHeight(Double height) {
        this.height = height;
        return this;
    }

    public LineString getLine() {
        return line;
    }

    public ModelMountains setLine(LineString line) {
        this.line = line;
        return this;
    }

    public Polygon getArea() {
        return area;
    }

    public ModelMountains setArea(Polygon area) {
        this.area = area;
        return this;
    }

    public String getName() {
        return name;
    }

    public ModelMountains setName(String name) {
        this.name = name;
        return this;
    }

    public EntityMountains toEntity() {
        EntityMountains mountains = new EntityMountains();
        mountains.setId(id);
        mountains.setName(name);
        mountains.setArea(area);
        mountains.setLine(line);
        return mountains;
    }
}
