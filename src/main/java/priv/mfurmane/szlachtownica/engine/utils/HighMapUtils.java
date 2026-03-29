package priv.mfurmane.szlachtownica.engine.utils;


import org.geotools.geometry.jts.JTS;
import org.geotools.referencing.CRS;
import org.locationtech.jts.geom.Envelope;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.prep.PreparedGeometry;
import org.locationtech.jts.geom.prep.PreparedGeometryFactory;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.TransformException;

import java.util.List;

public class HighMapUtils {

    public static final String GEOGRAPHIC = "EPSG:4326";
    public static final String METRIC = "EPSG:32743";
    public static final Integer metersPerPixel = 100;

    public static MathTransform getTransform(String input, String output) {
        try {
            CoordinateReferenceSystem sourceCRS = CRS.decode(input, true);
            CoordinateReferenceSystem targetCRS = CRS.decode(output, true);
            return CRS.findMathTransform(sourceCRS, targetCRS);
        } catch (FactoryException e) {
            throw new RuntimeException(e);
        }
    }

    public static Geometry mapToMetric(Geometry input) {
        try {
            return JTS.transform(input, getTransform(GEOGRAPHIC, METRIC));
        } catch (TransformException e) {
            throw new RuntimeException(e);
        }
    }

    public static Geometry mapToGeometric(Geometry input) {
        try {
            return JTS.transform(input, getTransform(METRIC, GEOGRAPHIC));
        } catch (TransformException e) {
            throw new RuntimeException(e);
        }
    }

    public static Envelope getEnvelope(List<Geometry> geometries) {
//        Wyliczenie rozpiętości terenu
        Envelope env = null;
        for (Geometry g : geometries) {
            if (env == null) {
                env = new Envelope(g.getEnvelopeInternal());
            } else {
                env.expandToInclude(g.getEnvelopeInternal());
            }
        }
//        double widthMeters = env.getWidth(); // rozpiętość E–W w metrach
//        double heightMeters = env.getHeight(); // rozpiętość N–S w metrach
        System.out.println("### " + env.getWidth() + " " + env.getHeight() + "\n" + env.getMinX() + " " + env.getMaxX() + " " + env.getMinY() + " " + env.getMaxY());
        return env;
    }

    public static float[][] getEmptyMap(Envelope env) {
        int widthPixels = (int) Math.ceil(env.getWidth() / metersPerPixel) + 20;
        int heightPixels = (int) Math.ceil(env.getHeight() / metersPerPixel) + 20;
        float[][] highMap = new float[heightPixels][widthPixels];
        return highMap;
    }

    public static void main(String[] args) {

//        Inicjalizacja highMapy
//
//        PreparedGeometry prep = PreparedGeometryFactory.prepare(poly32743);
//        if (prep.contains(p)) { ... }
//gdzie p jest środkiem pixela


    }


}