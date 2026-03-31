package priv.mfurmane.szlachtownica.model.main.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.Polygon;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rivers")
public class EntityRiver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JdbcTypeCode(SqlTypes.GEOMETRY)
    @Column(name = "geom", columnDefinition = "geometry(LineString, 4326)") // <- PostGIS
    private LineString line; //not null

    @Column(name = "name")
    private String name;

    public Long getId() {
        return id;
    }

    public EntityRiver setId(Long id) {
        this.id = id;
        return this;
    }

    public LineString getLine() {
        return line;
    }

    public EntityRiver setLine(LineString line) {
        this.line = line;
        return this;
    }

    public String getName() {
        return name;
    }

    public EntityRiver setName(String name) {
        this.name = name;
        return this;
    }
}
