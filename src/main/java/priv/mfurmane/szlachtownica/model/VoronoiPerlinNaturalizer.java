package priv.mfurmane.szlachtownica.model;

import org.locationtech.jts.geom.*;

import java.util.*;

/**
 * Zamienia proste, "geometryczne" krawędzie diagramu Voronoi na naturalnie
 * wyglądające granice. Trzy zabiegi działające razem:
 *
 * 1. JITTER WIERZCHOŁKÓW — potrójne styki komórek (wierzchołki Voronoi) są
 *    przesuwane o deterministyczny wektor zależny od pozycji. To rozbija
 *    regularny szkielet diagramu, który inaczej prześwituje spod falowania.
 *    Wierzchołki leżące na zewnętrznej granicy obszaru pozostają nieruchome,
 *    dzięki czemu kontur prowincji nie zmienia kształtu.
 *
 * 2. FRAKTALNA PERTURBACJA KRAWĘDZI — każda wspólna (wewnętrzna) krawędź jest
 *    zaginana wielooktawowym szumem (fBm), co daje meandry w wielu skalach
 *    naraz zamiast pojedynczego gładkiego łuku. Okno sin(pi*t) zeruje wychylenie
 *    na końcach, więc krawędzie schodzą się w (przesuniętych) wierzchołkach bez
 *    rozejść i nakładek.
 *
 * 3. Amplituda jest BEZWZGLĘDNA (ułamek rozmiaru mapy), a nie proporcjonalna do
 *    długości krawędzi — dzięki temu krótkie i długie granice falują spójnie.
 *
 * Każda krawędź i każdy wierzchołek są transformowane dokładnie raz i współdzielone
 * przez sąsiednie komórki, więc podział pozostaje szczelny (bez luk i nakładek).
 */
public class VoronoiPerlinNaturalizer {

    private static final int OCTAVES = 5;              // liczba oktaw fBm (detal fraktalny)
    // Przesunięcie potrójnych styków trzymamy małe: większy jitter rozjeżdżałby
    // POLA sąsiednich komórek (jeden róg = wspólny), a zależy nam na podobnych
    // rozmiarach. Nierówność kształtu bierze się głównie z falowania krawędzi.
    private static final double VERTEX_JITTER_FACTOR = 0.35;

    private final GeometryFactory gf;
    private final double amplitude;   // maks. bezwzględne wychylenie krawędzi (w jednostkach mapy)
    private final int segments;       // liczba punktów pośrednich wstawianych na krawędź
    private final double frequency;   // bazowa częstotliwość falowania wzdłuż krawędzi
    private final long seed;

    public VoronoiPerlinNaturalizer(GeometryFactory gf, int segments, double amplitude, double frequency, long seed) {
        this.gf = gf;
        this.segments = segments;
        this.amplitude = amplitude;
        this.frequency = frequency;
        this.seed = seed;
    }

    public List<Polygon> naturalizeVoronoi(List<Polygon> polygons) {
        // 1) Zbierz wszystkie krawędzie; krawędź wewnętrzna wystąpi 2x, zewnętrzna 1x.
        Map<String, List<Coordinate[]>> edgeMap = new HashMap<>();
        for (Polygon p : polygons) {
            addEdgesToMap(p.getExteriorRing(), edgeMap);
            for (int j = 0; j < p.getNumInteriorRing(); j++) {
                addEdgesToMap(p.getInteriorRingN(j), edgeMap);
            }
        }

        // 2) Wierzchołki na zewnętrznej granicy (należące do krawędzi występującej raz)
        //    zostawiamy nieruchome, żeby kontur obszaru się nie zmienił.
        Set<String> boundaryVerts = new HashSet<>();
        for (List<Coordinate[]> edges : edgeMap.values()) {
            if (edges.size() == 1) {
                boundaryVerts.add(vKey(edges.get(0)[0]));
                boundaryVerts.add(vKey(edges.get(0)[1]));
            }
        }

        // 3) Przesunięcie każdego unikalnego wierzchołka (spójne dla wszystkich komórek).
        Map<String, Coordinate> vertexShift = new HashMap<>();
        for (List<Coordinate[]> edges : edgeMap.values()) {
            for (Coordinate c : new Coordinate[]{edges.get(0)[0], edges.get(0)[1]}) {
                vertexShift.computeIfAbsent(vKey(c),
                        k -> boundaryVerts.contains(k) ? c : jitterVertex(c));
            }
        }

        // 4) Transformacja każdej krawędzi dokładnie raz.
        Map<String, Coordinate[]> transformed = new HashMap<>();
        Map<String, Coordinate> edgeStart = new HashMap<>(); // oryginalny początek — do ustalania kierunku
        for (Map.Entry<String, List<Coordinate[]>> entry : edgeMap.entrySet()) {
            String key = entry.getKey();
            Coordinate c1 = entry.getValue().get(0)[0];
            Coordinate c2 = entry.getValue().get(0)[1];
            edgeStart.put(key, c1);
            Coordinate s1 = vertexShift.get(vKey(c1));
            Coordinate s2 = vertexShift.get(vKey(c2));
            if (entry.getValue().size() == 2) {
                transformed.put(key, perturbEdge(s1, s2, key.hashCode())); // wewnętrzna → zaginamy
            } else {
                transformed.put(key, new Coordinate[]{s1, s2});            // zewnętrzna → prosta
            }
        }

        // 5) Odbuduj poligony z przetransformowanych krawędzi.
        List<Polygon> result = new ArrayList<>();
        for (Polygon p : polygons) {
            LinearRing shell = rebuildRing(p.getExteriorRing(), transformed, edgeStart);
            LinearRing[] holes = new LinearRing[p.getNumInteriorRing()];
            for (int i = 0; i < holes.length; i++) {
                holes[i] = rebuildRing(p.getInteriorRingN(i), transformed, edgeStart);
            }
            Polygon newPoly = gf.createPolygon(shell, holes);
            if (!newPoly.isValid()) {
                Geometry fixed = newPoly.buffer(0);
                newPoly = (fixed instanceof Polygon poly)
                        ? poly
                        : getLargestPolygon((MultiPolygon) fixed);
            }
            result.add(newPoly);
        }
        return result;
    }

    // Zagina krawędź między (przesuniętymi) wierzchołkami c1..c2 wielooktawowym szumem.
    private Coordinate[] perturbEdge(Coordinate c1, Coordinate c2, int edgeSeed) {
        double dx = c2.x - c1.x;
        double dy = c2.y - c1.y;
        double length = Math.sqrt(dx * dx + dy * dy);
        if (length == 0) {
            return new Coordinate[]{c1, c2};
        }
        double nx = -dy / length; // wektor prostopadły (jednostkowy)
        double ny = dx / length;

        // Ogranicznik: na krótkiej krawędzi zbyt duże wychylenie zapętliłoby ją
        // lub wepchnęło w sąsiednią komórkę (skąd biorą się nakładki regionów).
        // Tniemy wychylenie do ułamka długości krawędzi.
        double maxOffset = 0.4 * length;

        Coordinate[] coords = new Coordinate[segments + 2];
        coords[0] = c1;
        coords[segments + 1] = c2;
        for (int i = 1; i <= segments; i++) {
            double t = (double) i / (segments + 1);
            double baseX = c1.x + t * dx;
            double baseY = c1.y + t * dy;
            double window = Math.sin(Math.PI * t);          // 0 na końcach, 1 w środku
            double offset = window * amplitude * fbm(t * frequency, edgeSeed);
            offset = Math.max(-maxOffset, Math.min(maxOffset, offset));
            coords[i] = new Coordinate(baseX + nx * offset, baseY + ny * offset);
        }
        return coords;
    }

    // Wielooktawowy szum (fractional Brownian motion) w [-1, 1].
    private double fbm(double x, int edgeSeed) {
        double sum = 0, amp = 1, total = 0, freq = 1;
        for (int o = 0; o < OCTAVES; o++) {
            sum += amp * noise1D(x * freq, edgeSeed + o * 101);
            total += amp;
            amp *= 0.5;
            freq *= 2.0;
        }
        return sum / total;
    }

    // Deterministyczne przesunięcie wierzchołka zależne od jego pozycji.
    private Coordinate jitterVertex(Coordinate c) {
        double angle = (hash(c, 7) * 0.5 + 0.5) * 2 * Math.PI;
        double radius = (0.4 + 0.6 * (hash(c, 13) * 0.5 + 0.5)) * amplitude * VERTEX_JITTER_FACTOR;
        return new Coordinate(c.x + Math.cos(angle) * radius, c.y + Math.sin(angle) * radius);
    }

    private void addEdgesToMap(LineString ring, Map<String, List<Coordinate[]>> edgeMap) {
        for (int i = 0; i < ring.getNumPoints() - 1; i++) {
            Coordinate c1 = ring.getCoordinateN(i);
            Coordinate c2 = ring.getCoordinateN(i + 1);
            edgeMap.computeIfAbsent(makeEdgeKey(c1, c2), k -> new ArrayList<>())
                    .add(new Coordinate[]{c1, c2});
        }
    }

    private LinearRing rebuildRing(LineString ring, Map<String, Coordinate[]> transformed,
                                   Map<String, Coordinate> edgeStart) {
        List<Coordinate> coords = new ArrayList<>();
        for (int i = 0; i < ring.getNumPoints() - 1; i++) {
            Coordinate c1 = ring.getCoordinateN(i);
            Coordinate c2 = ring.getCoordinateN(i + 1);
            String key = makeEdgeKey(c1, c2);
            Coordinate[] edge = transformed.get(key);
            boolean forward = edgeStart.get(key).equals2D(c1);
            if (forward) {
                for (int j = 0; j < edge.length - 1; j++) coords.add(edge[j]);
            } else {
                for (int j = edge.length - 1; j >= 1; j--) coords.add(edge[j]);
            }
        }
        coords.add(coords.get(0)); // zamknięcie pierścienia
        return gf.createLinearRing(coords.toArray(new Coordinate[0]));
    }

    private Polygon getLargestPolygon(MultiPolygon mp) {
        Polygon largest = null;
        double maxArea = -1;
        for (int i = 0; i < mp.getNumGeometries(); i++) {
            Polygon p = (Polygon) mp.getGeometryN(i);
            if (p.getArea() > maxArea) {
                maxArea = p.getArea();
                largest = p;
            }
        }
        return largest;
    }

    private String makeEdgeKey(Coordinate c1, Coordinate c2) {
        return c1.compareTo(c2) <= 0
                ? vKey(c1) + "_" + vKey(c2)
                : vKey(c2) + "_" + vKey(c1);
    }

    private String vKey(Coordinate c) {
        return c.x + ":" + c.y;
    }

    // =========================
    // Szum wartościowy (value noise), interpolowany smoothstepem.
    // =========================

    private double noise1D(double x, int edgeSeed) {
        int x0 = (int) Math.floor(x);
        double t = x - x0;
        double v0 = randomFromInt(x0 + edgeSeed);
        double v1 = randomFromInt(x0 + 1 + edgeSeed);
        return lerp(v0, v1, fade(t));
    }

    private double randomFromInt(int x) {
        x = (x << 13) ^ x;
        return 1.0 - ((x * (x * x * 15731 + 789221) + 1376312589 + (int) seed) & 0x7fffffff) / 1073741824.0;
    }

    // Skalarny hash pozycji w [-1, 1] — deterministyczny dla danego wierzchołka.
    private double hash(Coordinate c, int salt) {
        int hx = Double.hashCode(c.x);
        int hy = Double.hashCode(c.y);
        return randomFromInt(hx * 73856093 ^ hy * 19349663 ^ salt * 83492791);
    }

    private double fade(double t) {
        return t * t * (3 - 2 * t); // smoothstep
    }

    private double lerp(double a, double b, double t) {
        return a + t * (b - a);
    }
}
