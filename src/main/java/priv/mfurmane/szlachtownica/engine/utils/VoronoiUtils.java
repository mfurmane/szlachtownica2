package priv.mfurmane.szlachtownica.engine.utils;

import org.locationtech.jts.geom.*;
import org.locationtech.jts.triangulate.VoronoiDiagramBuilder;
import org.osgeo.proj4j.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VoronoiUtils {
    //TODO do przejrzenia, ogarnięcia i dostosowania

    // GeometryFactory — możesz ustawić PrecisionModel/SRID jeśli chcesz
    private static final GeometryFactory GF = new GeometryFactory();

    // Reprojektuje listę JTS Coordinate (zakładamy x=lon, y=lat) z src->dst (np. "epsg:4326" -> "epsg:3857")
    public static List<Coordinate> reprojectCoords(List<Coordinate> points, String srcCode, String dstCode) {
        CRSFactory crsFactory = new CRSFactory();
        CoordinateReferenceSystem src = crsFactory.createFromName(srcCode);
        CoordinateReferenceSystem dst = crsFactory.createFromName(dstCode);
        CoordinateTransformFactory ctFactory = new CoordinateTransformFactory();
        CoordinateTransform transform = ctFactory.createTransform(src, dst);

        List<Coordinate> out = new ArrayList<>(points.size());
        for (Coordinate c : points) {
            ProjCoordinate in = new ProjCoordinate(c.x, c.y); // lon, lat
            ProjCoordinate outProj = new ProjCoordinate();
            transform.transform(in, outProj);
            out.add(new Coordinate(outProj.x, outProj.y));
        }
        return out;
    }

    // Buduje Voronoi na punktach (już w CRS metrycznym). clipBuffer — wartość (w jednostkach CRS, np. metry) do rozszerzenia obwiedni.
    public static Geometry buildVoronoi(List<Coordinate> projectedPoints, double clipBuffer) {
        Coordinate[] arr = projectedPoints.toArray(new Coordinate[0]);
        MultiPoint mp = GF.createMultiPointFromCoords(arr);

        VoronoiDiagramBuilder vdb = new VoronoiDiagramBuilder();
        vdb.setSites(mp);

        Envelope env = mp.getEnvelopeInternal();
        env.expandBy(clipBuffer); // rozszerzamy, żeby zapewnić, że regiony będą kompletne
        vdb.setClipEnvelope(env);

        Geometry diagram = vdb.getDiagram(GF);
        return diagram; // geometry w CRS metrycznym (np. EPSG:3857)
    }

    // Reprojektuje dowolną geometrię (klon) używając Proj4J: srcCode -> dstCode
    public static Geometry reprojectGeometry(Geometry geom, String srcCode, String dstCode) {
        CRSFactory crsFactory = new CRSFactory();
        CoordinateReferenceSystem src = crsFactory.createFromName(srcCode);
        CoordinateReferenceSystem dst = crsFactory.createFromName(dstCode);
        CoordinateTransformFactory ctFactory = new CoordinateTransformFactory();
        CoordinateTransform transform = ctFactory.createTransform(src, dst);

        Geometry clone = (Geometry) geom.clone();
        clone.apply(new CoordinateFilter() {
            @Override
            public void filter(Coordinate c) {
                ProjCoordinate in = new ProjCoordinate(c.x, c.y);
                ProjCoordinate out = new ProjCoordinate();
                transform.transform(in, out);
                c.x = out.x;
                c.y = out.y;
            }
        });
        return clone;
    }

    // helper: wybór lokalnego UTM (opcjonalnie) na podstawie średniej długości geograficznej
    public static String utmForLongitudeLatitude(double lon, double lat) {
        int zone = (int)Math.floor((lon + 180.0) / 6.0) + 1;
        int epsg = (lat >= 0) ? 32600 + zone : 32700 + zone; // 326xx = UTM northern, 327xx = southern
        return "epsg:" + epsg;
    }

    public static void main(String[] args) {
        // przykładowe punkty w EPSG:4326 (x=lon,y=lat)
        List<Coordinate> points4326 = Arrays.asList(
                new Coordinate(19.9, 50.0),
                new Coordinate(21.0, 52.2),
                new Coordinate(18.6, 54.4)
        );

        // --- Opcja A: proste — Web Mercator (EPSG:3857)
        List<Coordinate> projected = reprojectCoords(points4326, "epsg:4326", "epsg:3857");

        // zrób Voronoi; buffer w metrach (np. 100 km = 100000 m) — dopasuj do skali
        Geometry voronoiMeters = buildVoronoi(projected, 100_000.0);

        // przetransformuj poligony z powrotem do EPSG:4326
        Geometry voronoi4326 = reprojectGeometry(voronoiMeters, "epsg:3857", "epsg:4326");

        System.out.println("Voronoi (WGS84): " + voronoi4326);
        // Możesz iterować po polygonach: ((GeometryCollection)voronoi4326).getGeometryN(i)
    }

}