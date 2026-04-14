package priv.mfurmane.szlachtownica.model.main.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.Polygon;

@Entity
@Table(name = "lakes")
public class EntityMountains {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JdbcTypeCode(SqlTypes.GEOMETRY)
    @Column(name = "geom", columnDefinition = "geometry(Polygon, 4326)") // <- PostGIS
    private Polygon area; //not null

    @Column(name = "name")
    private String name;

    @JdbcTypeCode(SqlTypes.GEOMETRY)
    @Column(name = "line", columnDefinition = "geometry(LineString, 4326)") // <- PostGIS
    private LineString line;

    public Long getId() {
        return id;
    }

    public EntityMountains setId(Long id) {
        this.id = id;
        return this;
    }

    public Polygon getArea() {
        return area;
    }

    public EntityMountains setArea(Polygon area) {
        this.area = area;
        return this;
    }

    public String getName() {
        return name;
    }

    public EntityMountains setName(String name) {
        this.name = name;
        return this;
    }

    public LineString getLine() {
        return line;
    }

    public EntityMountains setLine(LineString line) {
        this.line = line;
        return this;
    }
}
