package priv.mfurmane.szlachtownica.model.main.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.locationtech.jts.geom.Polygon;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sea_parts")
public class EntitySeaPart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JdbcTypeCode(SqlTypes.GEOMETRY)
    @Column(name = "geom", columnDefinition = "geometry(Polygon, 4326)") // <- PostGIS
    private Polygon area; //not null

    public Long getId() {
        return id;
    }

    public EntitySeaPart setId(Long id) {
        this.id = id;
        return this;
    }

    public Polygon getArea() {
        return area;
    }

    public EntitySeaPart setArea(Polygon area) {
        this.area = area;
        return this;
    }
}
