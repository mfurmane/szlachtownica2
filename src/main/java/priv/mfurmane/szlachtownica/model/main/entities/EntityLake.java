package priv.mfurmane.szlachtownica.model.main.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.locationtech.jts.geom.Polygon;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "lakes")
public class EntityLake {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JdbcTypeCode(SqlTypes.GEOMETRY)
    @Column(name = "geom", columnDefinition = "geometry(Polygon, 4326)") // <- PostGIS
    private Polygon area; //not null

    @Column(name = "name")
    private String name;

    public Long getId() {
        return id;
    }

    public EntityLake setId(Long id) {
        this.id = id;
        return this;
    }

    public Polygon getArea() {
        return area;
    }

    public EntityLake setArea(Polygon area) {
        this.area = area;
        return this;
    }

    public String getName() {
        return name;
    }

    public EntityLake setName(String name) {
        this.name = name;
        return this;
    }
}
