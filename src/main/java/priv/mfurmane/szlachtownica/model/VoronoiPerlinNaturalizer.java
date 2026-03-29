package priv.mfurmane.szlachtownica.model;

import org.locationtech.jts.geom.*;
import org.locationtech.jts.triangulate.VoronoiDiagramBuilder;

import java.util.*;

public class VoronoiPerlinNaturalizer {

    private final GeometryFactory gf;
    private final double amplitude;
    private final int segments;
    private final double noiseScale;
    private final long seed;

    public VoronoiPerlinNaturalizer(GeometryFactory gf, int segments, double amplitude, double noiseScale, long seed) {
        this.gf = gf;
        this.segments = segments;
        this.amplitude = amplitude;
        this.noiseScale = noiseScale;
        this.seed = seed;
    }

//    public MultiPolygon generateNaturalVoronoi(Collection<Coordinate> points, Envelope clipEnvelope) {
//        VoronoiDiagramBuilder vdb = new VoronoiDiagramBuilder();
//        vdb.setSites(points);
//        vdb.setClipEnvelope(clipEnvelope);
//        Geometry voronoi = vdb.getDiagram(gf);
//
//        List<Polygon> polygons = new ArrayList<>();
//        for (int i = 0; i < voronoi.getNumGeometries(); i++) {
//            polygons.add((Polygon) voronoi.getGeometryN(i));
//        }
//
//        Map<String, Coordinate[]> edgeMap = new HashMap<>();
//        for (Polygon p : polygons) {
//            addEdgesToMap(p.getExteriorRing(), edgeMap);
//            for (int j = 0; j < p.getNumInteriorRing(); j++) {
//                addEdgesToMap(p.getInteriorRingN(j), edgeMap);
//            }
//        }
//
//        // Transformacja każdej krawędzi tylko raz
//        Map<String, Coordinate[]> transformedEdges = new HashMap<>();
//        for (Map.Entry<String, Coordinate[]> entry : edgeMap.entrySet()) {
//            String key = entry.getKey();
//            Coordinate c1 = entry.getValue()[0];
//            Coordinate c2 = entry.getValue()[1];
//            transformedEdges.put(key, perturbEdge(c1, c2, key.hashCode()));
//        }
//
//        List<Polygon> newPolygons = new ArrayList<>();
//        for (Polygon p : polygons) {
//            List<Coordinate> newCoords = new ArrayList<>();
//
//            collectTransformedCoordinates(p.getExteriorRing(), transformedEdges, newCoords);
//
//            newCoords.add(newCoords.get(0));
//            LinearRing shell = gf.createLinearRing(newCoords.toArray(new Coordinate[0]));
//            Polygon newPoly = gf.createPolygon(shell, null);
//
//            if (!newPoly.isValid()) {
//                newPoly = (Polygon) newPoly.buffer(0);
//            }
//
//            newPolygons.add(newPoly);
//        }
//
//        return gf.createMultiPolygon(newPolygons.toArray(new Polygon[0]));
//    }

    private void addEdgesToMap(LineString ring, Map<String, List<Coordinate[]>> edgeMap) {
        for (int i = 0; i < ring.getNumPoints() - 1; i++) {
            Coordinate c1 = ring.getCoordinateN(i);
            Coordinate c2 = ring.getCoordinateN(i + 1);
            String key = makeEdgeKey(c1, c2);

            edgeMap.computeIfAbsent(key, k -> new ArrayList<>())
                    .add(new Coordinate[]{c1, c2});
        }
    }

    private String makeEdgeKey(Coordinate c1, Coordinate c2) {
        if (c1.compareTo(c2) <= 0) {
            return c1.toString() + "_" + c2.toString();
        } else {
            return c2.toString() + "_" + c1.toString();
        }
    }

    // 🔥 GŁÓWNA RÓŻNICA – Perlin/value noise
    private Coordinate[] perturbEdge(Coordinate c1, Coordinate c2, int edgeSeed) {
        Coordinate[] coords = new Coordinate[segments + 2];
        coords[0] = c1;
        coords[segments + 1] = c2;

        // wektor prostopadły do krawędzi (żeby odchylenia były "na boki")
        double dx = c2.x - c1.x;
        double dy = c2.y - c1.y;
        double length = Math.sqrt(dx * dx + dy * dy);

        double nx = -dy / length;
        double ny = dx / length;

        for (int i = 1; i <= segments; i++) {
            double t = (double) i / (segments + 1);

            double baseX = c1.x + t * dx;
            double baseY = c1.y + t * dy;

            // próbkujemy noise wzdłuż krawędzi
            double noise = noise1D(t * noiseScale, edgeSeed);

            double offset = noise * amplitude * length;

            double x = baseX + nx * offset;
            double y = baseY + ny * offset;

            coords[i] = new Coordinate(x, y);
        }

        return coords;
    }

//    private void collectTransformedCoordinates(LineString ring, Map<String, Coordinate[]> transformedEdges, List<Coordinate> out) {
//        for (int i = 0; i < ring.getNumPoints() - 1; i++) {
//            Coordinate c1 = ring.getCoordinateN(i);
//            Coordinate c2 = ring.getCoordinateN(i + 1);
//            String key = makeEdgeKey(c1, c2);
//
//            Coordinate[] transformed = transformedEdges.get(key);
//
//            if (!transformed[0].equals2D(c1)) {
//                for (int j = transformed.length - 1; j >= 0; j--) {
//                    out.add(transformed[j]);
//                }
//            } else {
//                Collections.addAll(out, transformed);
//            }
//        }
//    }

    // =========================
    // 🎲 VALUE NOISE (Perlin-like)
    // =========================

    private double noise1D(double x, int edgeSeed) {
        int x0 = (int) Math.floor(x);
        int x1 = x0 + 1;

        double t = x - x0;

        double v0 = randomFromInt(x0 + edgeSeed);
        double v1 = randomFromInt(x1 + edgeSeed);

        double smoothT = fade(t);

        return lerp(v0, v1, smoothT);
    }

    private double randomFromInt(int x) {
        x = (x << 13) ^ x;
        return 1.0 - ((x * (x * x * 15731 + 789221) + 1376312589 + (int)seed) & 0x7fffffff) / 1073741824.0;
    }

    private double fade(double t) {
        return t * t * (3 - 2 * t); // smoothstep
    }

    private double lerp(double a, double b, double t) {
        return a + t * (b - a);
    }



    public List<Polygon> naturalizeVoronoi(List<Polygon> polygons) {
        Map<String, List<Coordinate[]>> edgeMap = new HashMap<>();
        // 1️⃣ Zbieramy wszystkie krawędzie
        for (Polygon p : polygons) {
            addEdgesToMap(p.getExteriorRing(), edgeMap);
            for (int j = 0; j < p.getNumInteriorRing(); j++) {
                addEdgesToMap(p.getInteriorRingN(j), edgeMap);
            }
        }
        // 2️⃣ Transformujemy każdą krawędź tylko raz
//        Map<String, Coordinate[]> transformedEdges = new HashMap<>();
//        for (Map.Entry<String, List<Coordinate[]>> entry : edgeMap.entrySet()) {
//            String key = entry.getKey();
//            Coordinate c1 = entry.getValue()[0];
//            Coordinate c2 = entry.getValue()[1];
//            transformedEdges.put(key, perturbEdge(c1, c2, key.hashCode()));
//        }
        Map<String, Coordinate[]> transformedEdges = new HashMap<>();

        for (Map.Entry<String, List<Coordinate[]>> entry : edgeMap.entrySet()) {
            String key = entry.getKey();
            List<Coordinate[]> edges = entry.getValue();
            Coordinate c1 = edges.get(0)[0];
            Coordinate c2 = edges.get(0)[1];
            if (edges.size() == 2) {
                // 🔥 WEWNĘTRZNA krawędź → deformujemy
                transformedEdges.put(key, perturbEdge(c1, c2, key.hashCode()));
            } else {
                // 🔥 ZEWNĘTRZNA → zostawiamy prostą
                transformedEdges.put(key, new Coordinate[]{c1, c2});
            }
        }
        // 3️⃣ Budujemy nowe poligony
        List<Polygon> result = new ArrayList<>();
        for (Polygon p : polygons) {
            LinearRing shell = rebuildRing(p.getExteriorRing(), transformedEdges);
            LinearRing[] holes = new LinearRing[p.getNumInteriorRing()];
            for (int i = 0; i < holes.length; i++) {
                holes[i] = rebuildRing(p.getInteriorRingN(i), transformedEdges);
            }
            Polygon newPoly = gf.createPolygon(shell, holes);
            if (!newPoly.isValid()) {
//                newPoly = (Polygon) newPoly.buffer(0);
                Geometry fixed = newPoly.buffer(0);
                if (fixed instanceof Polygon) {
                    newPoly = (Polygon) fixed;
                } else {//if (fixed instanceof MultiPolygon) {
                    // wybieramy największy kawałek (najczęściej to właściwy region)
                    newPoly = getLargestPolygon((MultiPolygon) fixed);
                } //else {
                    // fallback – rzadkie przypadki
                    //continue;
                //}
            }
            result.add(newPoly);
        }
        return result;
    }

    private Polygon getLargestPolygon(MultiPolygon mp) {
        Polygon largest = null;
        double maxArea = -1;
        for (int i = 0; i < mp.getNumGeometries(); i++) {
            Polygon p = (Polygon) mp.getGeometryN(i);
            double area = p.getArea();
            if (area > maxArea) {
                maxArea = area;
                largest = p;
            }
        }
        return largest;
    }

    private LinearRing rebuildRing(LineString ring, Map<String, Coordinate[]> transformedEdges) {
        List<Coordinate> coords = new ArrayList<>();
        for (int i = 0; i < ring.getNumPoints() - 1; i++) {
            Coordinate c1 = ring.getCoordinateN(i);
            Coordinate c2 = ring.getCoordinateN(i + 1);
            String key = makeEdgeKey(c1, c2);
            Coordinate[] edge = transformedEdges.get(key);
            // dopasowanie kierunku
            if (!edge[0].equals2D(c1)) {
                for (int j = edge.length - 1; j >= 0; j--) {
                    coords.add(edge[j]);
                }
            } else {
                Collections.addAll(coords, edge);
            }
        }
        // zamknięcie pierścienia
        coords.add(coords.get(0));
        return gf.createLinearRing(coords.toArray(new Coordinate[0]));
    }
}
