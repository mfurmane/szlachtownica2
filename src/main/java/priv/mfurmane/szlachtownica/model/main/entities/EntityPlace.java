package priv.mfurmane.szlachtownica.model.main.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;
import priv.mfurmane.szlachtownica.model.main.Populatable;
import priv.mfurmane.szlachtownica.model.simulation.terrain.PlaceType;

@Entity
@Table(name = "places")
public class EntityPlace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JdbcTypeCode(SqlTypes.GEOMETRY)
    @Column(name = "location", columnDefinition = "geometry(Point, 4326)") // <- PostGIS
    private Point location;

    @JdbcTypeCode(SqlTypes.GEOMETRY)
    @Column(name = "area", columnDefinition = "geometry(Polygon, 4326)") // <- PostGIS
    private Polygon area;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private PlaceType type;

    @Column(name = "name")
    private String name;

//    private final Populatable populatable = new Populatable();
  //  private Integer populationLimit;

    public String getName() {
        return name;
    }

    public EntityPlace setName(String name) {
        this.name = name;
        return this;
    }

    public Polygon getArea() {
        return area;
    }

    public EntityPlace setArea(Polygon area) {
        this.area = area;
        return this;
    }

    public Point getLocation() {
        return location;
    }

    public EntityPlace setLocation(Point location) {
        this.location = location;
        return this;
    }

    public PlaceType getType() {
        return type;
    }

    public EntityPlace setType(PlaceType type) {
        this.type = type;
        return this;
    }

}
