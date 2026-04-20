package priv.mfurmane.szlachtownica.model.main;

import org.locationtech.jts.geom.LineString;
import priv.mfurmane.szlachtownica.model.main.entities.EntityLake;
import priv.mfurmane.szlachtownica.model.main.entities.EntityRiver;
import priv.mfurmane.szlachtownica.model.simulation.terrain.Material;

import java.util.HashMap;
import java.util.Map;

public class ModelRiver extends MapFeature {
    private String name;
    private Integer width = 1;
    private Double depth = 0.4;
    private LineString line;

    public ModelRiver(String name) {
        this.name = name;
    }

    public Integer getWidth() {
        return width;
    }

    public ModelRiver setWidth(Integer width) {
        this.width = width;
        return this;
    }

    public Double getDepth() {
        return depth;
    }

    public ModelRiver setDepth(Double depth) {
        this.depth = depth;
        return this;
    }

    public LineString getLine() {
        return line;
    }

    public ModelRiver setLine(LineString line) {
        this.line = line;
        return this;
    }

    public String getName() {
        return name;
    }

    public ModelRiver setName(String name) {
        this.name = name;
        return this;
    }

    public EntityRiver toEntity() {
        EntityRiver river = new EntityRiver();
        river.setId(id);
        river.setName(name);
        river.setLine(line);
        return river;
    }

    public boolean isVeryBig() {
        return false;
    }
}
