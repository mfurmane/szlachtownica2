package priv.mfurmane.szlachtownica.engine.utils;

import org.geotools.geometry.jts.JTS;
import org.locationtech.jts.geom.*;
import org.locationtech.jts.geom.prep.PreparedGeometry;
import org.locationtech.jts.geom.prep.PreparedGeometryFactory;
import org.locationtech.jts.index.strtree.STRtree;
import org.opengis.referencing.operation.MathTransform;
import priv.mfurmane.szlachtownica.model.simulation.SimulationProvince;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Rasterizer {
    public static float[][] rasterizeProvinces(
            List<SimulationProvince> provinces,
            HashMap<SimulationProvince, Float> averageHeights,
            int width,
            int height,
            MathTransform transform // 4326 → 32743
    ) throws Exception {
        float[][] map = new float[height][width];
        // 1. Transformacja i przygotowanie geometrii + budowa indeksu
        Map<SimulationProvince, PreparedGeometry> prepared = new HashMap<>();
        STRtree index = new STRtree();
        Envelope env = null;
        for (SimulationProvince prov : provinces) {
            Geometry poly4326 = prov.getModel().getArea();
            Geometry poly32743 = JTS.transform(poly4326, transform);
            PreparedGeometry prep = PreparedGeometryFactory.prepare(poly32743);
            prepared.put(prov, prep);
            // dodaj do indeksu
            index.insert(poly32743.getEnvelopeInternal(), prov);
            if (env == null) {
                env = new Envelope(poly32743.getEnvelopeInternal());
            } else {
                env.expandToInclude(poly32743.getEnvelopeInternal());
            }
        }
        double minX = env.getMinX();
        double maxX = env.getMaxX();
        double minY = env.getMinY();
        double maxY = env.getMaxY();
        double cellSizeX = (maxX - minX) / width;
        double cellSizeY = (maxY - minY) / height;
        GeometryFactory gf = new GeometryFactory();
        // 2. Iteracja po pikselach
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                double x = minX + (col + 0.5) * cellSizeX;
                double y = maxY - (row + 0.5) * cellSizeY;
                Point p = gf.createPoint(new Coordinate(x, y));
                // szybkie wyszukiwanie kandydatów
                @SuppressWarnings("unchecked")
                List<SimulationProvince> candidates = index.query(p.getEnvelopeInternal());
                for (SimulationProvince prov : candidates) {
                    PreparedGeometry prep = prepared.get(prov);
                    if (prep.contains(p)) {
                        float h = averageHeights.get(prov);
                        map[row][col] = h;
                        break; // pierwszy pasujący poligon wystarczy
                    }
                }
            }
        }
        return map;
    }
}
