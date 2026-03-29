package priv.mfurmane.szlachtownica.model.main.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.locationtech.jts.geom.Polygon;
import priv.mfurmane.szlachtownica.engine.MainEngine;
import priv.mfurmane.szlachtownica.model.simulation.SimulationProvince;
import priv.mfurmane.szlachtownica.model.simulation.SimulationRegion;
import priv.mfurmane.szlachtownica.model.simulation.terrain.Climate;
import priv.mfurmane.szlachtownica.model.simulation.terrain.Humidity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sub_provinces")
public class EntitySubProvince {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JdbcTypeCode(SqlTypes.GEOMETRY)
    @Column(name = "geom", columnDefinition = "geometry(Polygon, 4326)") // <- PostGIS
    private Polygon area; //not null

    @ManyToOne
    @JoinColumn(name = "province_id")
    private EntityProvince province;

    @OneToMany(mappedBy = "subProvince")
    private final List<EntityRegion> regions = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public EntitySubProvince setId(Long id) {
        this.id = id;
        return this;
    }

    public Polygon getArea() {
        return area;
    }

    public EntitySubProvince setArea(Polygon area) {
        this.area = area;
        return this;
    }

    public EntityProvince getProvince() {
        return province;
    }

    public EntitySubProvince setProvince(EntityProvince province) {
        this.province = province;
        return this;
    }

    public List<EntityRegion> getRegions() {
        return regions;
    }
}
