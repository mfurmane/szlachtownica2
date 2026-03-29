package priv.mfurmane.szlachtownica.model.main.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.locationtech.jts.geom.Polygon;
import priv.mfurmane.szlachtownica.engine.MainEngine;
import priv.mfurmane.szlachtownica.model.config.ConfigurationProvince;
import priv.mfurmane.szlachtownica.model.config.ConfigurationSubProvince;
import priv.mfurmane.szlachtownica.model.simulation.SimulationRegion;
import priv.mfurmane.szlachtownica.model.simulation.SimulationSubProvince;
import priv.mfurmane.szlachtownica.model.simulation.terrain.EnchantType;
import priv.mfurmane.szlachtownica.model.simulation.terrain.RegionType;
import priv.mfurmane.szlachtownica.model.simulation.terrain.TerrainShape;

import java.util.*;

@Entity
@Table(name = "provinces")
public class EntityProvince {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @JdbcTypeCode(SqlTypes.GEOMETRY)
    @Column(name = "geom", columnDefinition = "geometry(Polygon, 4326)") // <- PostGIS
    private Polygon area; //not null

    @OneToMany(mappedBy = "province")
    private final List<EntitySubProvince> subProvinces = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public EntityProvince setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public EntityProvince setName(String name) {
        this.name = name;
        return this;
    }

    public Polygon getArea() {
        return area;
    }

    public EntityProvince setArea(Polygon area) {
        this.area = area;
        return this;
    }

    public List<EntitySubProvince> getSubProvinces() {
        return subProvinces;
    }
}
