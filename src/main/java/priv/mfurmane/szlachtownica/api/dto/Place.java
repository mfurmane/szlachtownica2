package priv.mfurmane.szlachtownica.api.dto;

import org.wololo.geojson.Geometry;
import priv.mfurmane.szlachtownica.model.simulation.terrain.PlaceType;

import java.util.ArrayList;
import java.util.List;

public class Place {
    private Long id;
    private String name;
    private Geometry location;
    private Geometry area; // = {Polygon@12201} "POLYGON ((76.1 -44.13030303030303, 76.08344459279039 -44.08181818181818, 76.1 -44.02121212121212, 76.08344459279039 -43.96666666666667, 76.1 -43.906060606060606, 76.10827770360481 -43.845454545454544, 76.08344459279039 -43.77878787878788, 76.1 -43.74848484848485, 76.14138851802403 -43.62121212121212, 76.19105473965287 -43.56666666666666, 76.24899866488651 -43.52424242424242, 76.32349799732978 -43.487878787878785, 76.356608811749 -43.445454545454545, 76.42283044058745 -43.40909090909091, 76.44766355140187 -43.36666666666667, 76.53044058744993 -43.342424242424244, 76.58838451268358 -43.2939393939394, 76.64632843791722 -43.263636363636365, 76.7539385847797 -43.269696969696966, 76.85327102803738 -43.28787878787879, 76.93604806408545 -43.31818181818182, 77.01882510013351 -43.336363636363636, 77.10987983978639 -43.32424242424242, 77.19265687583444 -43.34848484848485, 77.25060080106809 -43.306060606060605, 77.31682242990654 -43.275757575757574, 77.31682242990654 -43.22727272727273, 77.3995994"
    private PlaceType type;

    public Long getId() {
        return id;
    }

    public Place setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Place setName(String name) {
        this.name = name;
        return this;
    }

    public Geometry getArea() {
        return area;
    }

    public Place setArea(Geometry area) {
        this.area = area;
        return this;
    }

    public Geometry getLocation() {
        return location;
    }

    public Place setLocation(Geometry location) {
        this.location = location;
        return this;
    }

    public PlaceType getType() {
        return type;
    }

    public Place setType(PlaceType type) {
        this.type = type;
        return this;
    }
}
