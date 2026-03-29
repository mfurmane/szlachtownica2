package priv.mfurmane.szlachtownica.model;

import org.locationtech.jts.geom.*;
import org.locationtech.jts.triangulate.VoronoiDiagramBuilder;

import java.util.*;

public class VoronoiNaturalizer {

    private final GeometryFactory gf;
    private final double amplitude;   // maksymalne przesunięcie krawędzi
    private final int segments;       // liczba segmentów w krawędzi

    public VoronoiNaturalizer(GeometryFactory gf, int segments, double amplitude) {
        this.gf = gf;
        this.segments = segments;
        this.amplitude = amplitude;
    }

    /** Główny punkt wejścia: punkty -> MultiPolygon z "falistymi" krawędziami */
    public List<Polygon> generateNaturalVoronoi(Collection<Coordinate> points, Envelope clipEnvelope) {
//    public MultiPolygon generateNaturalVoronoi(Collection<Coordinate> points, Envelope clipEnvelope) {
        VoronoiDiagramBuilder vdb = new VoronoiDiagramBuilder();
        vdb.setSites(points);
        vdb.setClipEnvelope(clipEnvelope);
        Geometry voronoi = vdb.getDiagram(gf);

        // Konwertujemy Geometry -> List<Polygon>
        List<Polygon> polygons = new ArrayList<>();
        for (int i = 0; i < voronoi.getNumGeometries(); i++) {
            polygons.add((Polygon) voronoi.getGeometryN(i));
        }

        // Tworzymy mapę krawędzi -> poligony które ją dzielą
        Map<String, Coordinate[]> edgeMap = new HashMap<>();
        Map<String, List<Polygon>> polygonMap = new HashMap<>();

        for (Polygon p : polygons) {
            addEdgesToMap(p.getExteriorRing(), p, edgeMap, polygonMap);
            for (int j = 0; j < p.getNumInteriorRing(); j++) {
                addEdgesToMap(p.getInteriorRingN(j), p, edgeMap, polygonMap);
            }
        }

        // Przekształcamy każdą krawędź tylko raz
        Map<String, Coordinate[]> transformedEdges = new HashMap<>();
        for (Map.Entry<String, Coordinate[]> entry : edgeMap.entrySet()) {
            transformedEdges.put(entry.getKey(), perturbEdge(entry.getValue()[0], entry.getValue()[1]));
        }

        // Budujemy nowe poligony
        List<Polygon> newPolygons = new ArrayList<>();
        for (Polygon p : polygons) {
            List<Coordinate> newCoords = new ArrayList<>();
            // zewnętrzny pierścień
            collectTransformedCoordinates(p.getExteriorRing(), transformedEdges, newCoords);
            // zamknięcie pierścienia
            newCoords.add(newCoords.get(0));
            LinearRing shell = gf.createLinearRing(newCoords.toArray(new Coordinate[0]));
            Polygon newPoly = gf.createPolygon(shell, null);
            // poprawiamy topologię w razie self-intersections
            if (!newPoly.isValid()) {
                newPoly = (Polygon) newPoly.buffer(0);
            }
            newPolygons.add(newPoly);
        }

//        return gf.createMultiPolygon(newPolygons.toArray(new Polygon[0]));
        return newPolygons;
    }

    /** Dodaje krawędzie z pierścienia do map */
    private void addEdgesToMap(LineString ring, Polygon p, Map<String, Coordinate[]> edgeMap, Map<String, List<Polygon>> polygonMap) {
        for (int i = 0; i < ring.getNumPoints() - 1; i++) {
            Coordinate c1 = ring.getCoordinateN(i);
            Coordinate c2 = ring.getCoordinateN(i + 1);
            String key = makeEdgeKey(c1, c2);
            edgeMap.putIfAbsent(key, new Coordinate[]{c1, c2});
            polygonMap.computeIfAbsent(key, k -> new ArrayList<>()).add(p);
        }
    }

    /** Generuje spójny klucz krawędzi niezależnie od kierunku */
    private String makeEdgeKey(Coordinate c1, Coordinate c2) {
        if (c1.compareTo(c2) <= 0) {
            return c1.toString() + "_" + c2.toString();
        } else {
            return c2.toString() + "_" + c1.toString();
        }
    }

    /** Przekształca krawędź na "falistą" */
    private Coordinate[] perturbEdge(Coordinate c1, Coordinate c2) {
        Coordinate[] coords = new Coordinate[segments + 2];
        coords[0] = c1;
        coords[segments + 1] = c2;
        for (int i = 1; i <= segments; i++) {
            double t = (double) i / (segments + 1);
            double x = c1.x + t * (c2.x - c1.x) + (Math.random() - 0.5) * amplitude;
            double y = c1.y + t * (c2.y - c1.y) + (Math.random() - 0.5) * amplitude;
            coords[i] = new Coordinate(x, y);
        }
        return coords;
    }

    /** Zbiera przekształcone krawędzie dla pierścienia */
    private void collectTransformedCoordinates(LineString ring, Map<String, Coordinate[]> transformedEdges, List<Coordinate> out) {
        for (int i = 0; i < ring.getNumPoints() - 1; i++) {
            Coordinate c1 = ring.getCoordinateN(i);
            Coordinate c2 = ring.getCoordinateN(i + 1);
            String key = makeEdgeKey(c1, c2);
            Coordinate[] transformed = transformedEdges.get(key);
            // dopasowujemy kierunek
            if (!transformed[0].equals2D(c1)) {
                // odwracamy kolejność
                for (int j = transformed.length - 1; j >= 0; j--) {
                    out.add(transformed[j]);
                }
            } else {
                Collections.addAll(out, transformed);
            }
        }
    }
//
//    public List<Polygon> naturalizeVoronoi(List<Polygon> polygons) {
//
//        Map<String, Coordinate[]> edgeMap = new HashMap<>();
//
//        // 1️⃣ Zbieramy wszystkie krawędzie
//        for (Polygon p : polygons) {
//            addEdgesToMap(p.getExteriorRing(), edgeMap);
//            for (int j = 0; j < p.getNumInteriorRing(); j++) {
//                addEdgesToMap(p.getInteriorRingN(j), edgeMap);
//            }
//        }
//
//        // 2️⃣ Transformujemy każdą krawędź tylko raz
//        Map<String, Coordinate[]> transformedEdges = new HashMap<>();
//        for (Map.Entry<String, Coordinate[]> entry : edgeMap.entrySet()) {
//            String key = entry.getKey();
//            Coordinate c1 = entry.getValue()[0];
//            Coordinate c2 = entry.getValue()[1];
//
//            transformedEdges.put(key, perturbEdge(c1, c2, key.hashCode()));
//        }
//
//        // 3️⃣ Budujemy nowe poligony
//        List<Polygon> result = new ArrayList<>();
//
//        for (Polygon p : polygons) {
//            LinearRing shell = rebuildRing(p.getExteriorRing(), transformedEdges);
//
//            LinearRing[] holes = new LinearRing[p.getNumInteriorRing()];
//            for (int i = 0; i < holes.length; i++) {
//                holes[i] = rebuildRing(p.getInteriorRingN(i), transformedEdges);
//            }
//
//            Polygon newPoly = gf.createPolygon(shell, holes);
//
//            if (!newPoly.isValid()) {
//                newPoly = (Polygon) newPoly.buffer(0);
//            }
//
//            result.add(newPoly);
//        }
//
//        return result;
//    }
//
//    private LinearRing rebuildRing(LineString ring, Map<String, Coordinate[]> transformedEdges) {
//        List<Coordinate> coords = new ArrayList<>();
//
//        for (int i = 0; i < ring.getNumPoints() - 1; i++) {
//            Coordinate c1 = ring.getCoordinateN(i);
//            Coordinate c2 = ring.getCoordinateN(i + 1);
//
//            String key = makeEdgeKey(c1, c2);
//            Coordinate[] edge = transformedEdges.get(key);
//
//            // dopasowanie kierunku
//            if (!edge[0].equals2D(c1)) {
//                for (int j = edge.length - 1; j >= 0; j--) {
//                    coords.add(edge[j]);
//                }
//            } else {
//                Collections.addAll(coords, edge);
//            }
//        }
//
//        // zamknięcie pierścienia
//        coords.add(coords.get(0));
//
//        return gf.createLinearRing(coords.toArray(new Coordinate[0]));
//    }
}
