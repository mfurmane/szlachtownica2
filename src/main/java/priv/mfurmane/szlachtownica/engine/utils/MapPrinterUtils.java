package priv.mfurmane.szlachtownica.engine.utils;


import org.locationtech.jts.geom.*;
import org.locationtech.jts.geom.prep.PreparedGeometry;
import priv.mfurmane.szlachtownica.model.simulation.SimulationProvince;

import java.util.List;
import java.util.Map;

import static priv.mfurmane.szlachtownica.engine.utils.HighMapUtils.metersPerPixel;

public class MapPrinterUtils {

    public static List<PreparedGeometry> preparedGeometryStream;
    public static float[][] emptyMap;
    public static Envelope env;
    private static double cellSizeX = metersPerPixel;
    private static double cellSizeY = metersPerPixel;

    static final float MIN_H = 0f;
    static final float MAX_H = 2000f; // albo wylicz z danych
    static final float USHORT_MAX = 65535f;

    public static void print(Map<PreparedGeometry, SimulationProvince> provinceMap) {
        System.out.println("Glup Shitto: " + emptyMap.length + " - " + emptyMap[0].length);
        System.out.println();
        for (int i = 0; i < emptyMap.length; i++) {
            for (int j = 0; j < emptyMap[i].length; j++) {
                emptyMap[i][j] = height(i, j, provinceMap);
//                System.out.println(i + "-" + j + ": " + emptyMap[i][j]);
            }
        }
    }

    private static float height(int i, int j, Map<PreparedGeometry, SimulationProvince> provinceMap) {
        GeometryFactory gf = new GeometryFactory();
        double x = env.getMinX() + (j + 0.5) * cellSizeX;
        double y = env.getMinY() + (i + 0.5) * cellSizeY;
        Point p = gf.createPoint(new Coordinate(x, y));
        for (PreparedGeometry geometry : provinceMap.keySet()) {
            if (geometry.contains(p)) {
                float hMeters = provinceMap.get(geometry).getConf().getAverageHeight();

                float norm = (hMeters - MIN_H) / (MAX_H - MIN_H);
                int gray16 = Math.round(norm * USHORT_MAX);

                return emptyMap[i][j] = gray16;
            }
        }
        return USHORT_MAX;
    }

//    private static float height(int i, int j, Map<PreparedGeometry, SimulationProvince> provinceMap) {
//        GeometryFactory gf = new GeometryFactory();
////        for (int row = 0; row < env.getHeight(); row++) {
////            for (int col = 0; col < env.getWidth(); col++) {
////                double x = env.getMinX() + (j + 0.5) * cellSizeX;
//        double x = env.getMinX() + (j + 0.5) * cellSizeX;
//        double y = env.getMinY() + (i + 0.5) * cellSizeY;
//        Point p = gf.createPoint(new Coordinate(x, y));
//        // szybkie wyszukiwanie kandydatów
////                @SuppressWarnings("unchecked")
////                List<SimulationProvince> candidates = index.query(p.getEnvelopeInternal());
//        for (PreparedGeometry geometry : provinceMap.keySet()) {
////                    PreparedGeometry prep = prepared.get(prov);
//            if (geometry.contains(p)) {
//                float h = provinceMap.get(geometry).getConf().getAverageHeight() * 10;
//                return emptyMap[i][j] = h;
//            }
//        }
////            }
////        }
//        return 255f;
//    }

}