package priv.mfurmane.szlachtownica.worldgen;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Envelope;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.geom.prep.PreparedGeometry;
import org.locationtech.jts.geom.prep.PreparedGeometryFactory;
import org.locationtech.jts.linearref.LengthIndexedLine;
import priv.mfurmane.szlachtownica.engine.utils.GeoUtils;
import priv.mfurmane.szlachtownica.engine.utils.HighMapUtils;
import priv.mfurmane.szlachtownica.model.main.ModelLake;
import priv.mfurmane.szlachtownica.model.main.ModelMountains;
import priv.mfurmane.szlachtownica.model.main.ModelRegion;
import priv.mfurmane.szlachtownica.model.main.ModelRiver;
import priv.mfurmane.szlachtownica.model.main.ModelSeaPart;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Adapter: buduje {@link WorldGenContext} z geometrii świata. To jedyny kawałek
 * worldgen dotykający JTS i modelu — rdzeń (etapy) pozostaje czysty.
 *
 * Maska lądu = przynależność punktu do którejkolwiek prowincji (prepared.contains
 * z tanim prefiltrem po obwiedni). Maska morza = przynależność do rzeczywistego
 * akwenu ({@link ModelSeaPart}); dzięki temu „poza prowincjami" nie znaczy „morze"
 * — lądy za południową granicą, za rzeką na zachodzie itd. zostają lądem.
 *
 * Szczyty gór próbkuje się WZDŁUŻ linii grzbietu ({@link ModelMountains#getLine()}),
 * a nie tylko w centroidzie — pasmo powstaje z nachodzących garbów o wysokości
 * pasma (etap wysokości bierze maksimum, nie sumę), więc grzbiet ma realny
 * kształt zamiast jednego kopca. Gdy brak wysokości — fallback z configu.
 */
public class GeometryWorldInput {

    private static final GeometryFactory GF = new GeometryFactory();

    /** Zgodność wsteczna: bez akwenów (morze = poza lądem). */
    public static WorldGenContext fromProvinces(List<Geometry> areasMetric,
                                                List<ModelMountains> mountains,
                                                WorldGenConfig config, int maxDim) {
        return fromProvinces(areasMetric, mountains, null, config, maxDim);
    }

    /** Zgodność wsteczna: bez rzek/jezior/regionów. */
    public static WorldGenContext fromProvinces(List<Geometry> areasMetric,
                                                List<ModelMountains> mountains,
                                                List<ModelSeaPart> seaParts,
                                                WorldGenConfig config, int maxDim) {
        return fromProvinces(areasMetric, mountains, seaParts, null, null, null, config, maxDim);
    }

    /**
     * @param areasMetric obszary prowincji w układzie metrycznym (EPSG:32743)
     * @param mountains   pasma gór (Polygon + LineString grzbietu w EPSG:4326 + wysokość)
     * @param seaParts    rzeczywiste akweny morskie (Polygon w EPSG:4326); null = brak
     * @param config      parametry (m.in. metersPerPixel — dolna granica rozmiaru piksela)
     * @param maxDim      górny limit dłuższego boku rastra w px (kontrola kosztu)
     */
    public static WorldGenContext fromProvinces(List<Geometry> areasMetric,
                                                List<ModelMountains> mountains,
                                                List<ModelSeaPart> seaParts,
                                                List<ModelRiver> rivers,
                                                List<ModelLake> lakes,
                                                List<ModelRegion> regions,
                                                WorldGenConfig config, int maxDim) {
        Envelope env = HighMapUtils.getEnvelope(areasMetric);
        double span = Math.max(env.getWidth(), env.getHeight());
        double cell = Math.max(config.metersPerPixel, span / Math.max(1, maxDim));
        int w = Math.max(1, (int) Math.ceil(env.getWidth() / cell));
        int h = Math.max(1, (int) Math.ceil(env.getHeight() / cell));

        WorldGenContext ctx = new WorldGenContext(config);
        ctx.width = w;
        ctx.height = h;
        ctx.cellSize = cell;
        ctx.minX = env.getMinX();
        ctx.minY = env.getMinY();

        // Prepared per prowincja + obwiednia do taniego prefiltru.
        List<Envelope> envs = new ArrayList<>();
        List<PreparedGeometry> preps = new ArrayList<>();
        for (Geometry g : areasMetric) {
            envs.add(g.getEnvelopeInternal());
            preps.add(PreparedGeometryFactory.prepare(g));
        }
        ctx.landMask = (x, y) -> contains(preps, envs, x, y);

        // Maska morza z rzeczywistych akwenów (jeśli podane).
        if (seaParts != null && !seaParts.isEmpty()) {
            List<Envelope> seaEnvs = new ArrayList<>();
            List<PreparedGeometry> seaPreps = new ArrayList<>();
            for (ModelSeaPart s : seaParts) {
                if (s.getArea() == null) {
                    continue;
                }
                Geometry g = HighMapUtils.mapToMetric(s.getArea());
                seaEnvs.add(g.getEnvelopeInternal());
                seaPreps.add(PreparedGeometryFactory.prepare(g));
            }
            if (!seaPreps.isEmpty()) {
                ctx.seaMask = (x, y) -> contains(seaPreps, seaEnvs, x, y);
            }
        }

        // Highmap jako makro-relief (hybryda). Zakotwiczony w tym samym bboxie
        // geograficznym co prowincje (GeoUtils LON/LAT), przetransformowanym do
        // metryki — dzięki temu highmap i geometria pokrywają się bez własnej
        // konwencji. Brak ścieżki/pliku => czysto proceduralnie (heightBias null).
        if (config.highmapPath != null && !config.highmapPath.isBlank()) {
            try {
                HighmapBias bias = HighmapBias.load(new File(config.highmapPath), config.highmapMaxDim);
                Polygon bboxGeo = GF.createPolygon(new Coordinate[]{
                        new Coordinate(GeoUtils.LON_MIN, GeoUtils.LAT_MIN),
                        new Coordinate(GeoUtils.LON_MAX, GeoUtils.LAT_MIN),
                        new Coordinate(GeoUtils.LON_MAX, GeoUtils.LAT_MAX),
                        new Coordinate(GeoUtils.LON_MIN, GeoUtils.LAT_MAX),
                        new Coordinate(GeoUtils.LON_MIN, GeoUtils.LAT_MIN)
                });
                Envelope hm = HighMapUtils.mapToMetric(bboxGeo).getEnvelopeInternal();
                double hmMinX = hm.getMinX(), hmMaxY = hm.getMaxY();
                double hmW = hm.getWidth(), hmH = hm.getHeight();
                ctx.heightBias = (x, y) -> bias.sample(
                        (x - hmMinX) / hmW,   // u: zachód→wschód
                        (hmMaxY - y) / hmH);  // v: północ (góra obrazu) → południe
            } catch (IOException ex) {
                System.out.println("[worldgen] highmap pominięty (" + config.highmapPath + "): " + ex);
            }
        }

        if (mountains != null) {
            for (ModelMountains m : mountains) {
                if (m.getArea() == null) {
                    continue;
                }
                Geometry areaMetric = HighMapUtils.mapToMetric(m.getArea());
                double areaM = Math.max(areaMetric.getArea(), 1.0);
                double height = m.getHeight() != null ? m.getHeight() : config.defaultMountainHeight;

                Geometry lineMetric = m.getLine() != null ? HighMapUtils.mapToMetric(m.getLine()) : null;
                double len = lineMetric != null ? lineMetric.getLength() : 0;

                if (lineMetric != null && len > cell) {
                    // Pół-szerokość pasma ≈ pole / długość / 2 (prostokątne przybliżenie),
                    // z lekkim rozmyciem, by garby wzdłuż linii nachodziły na siebie.
                    double halfWidth = Math.max(cell * 2, areaM / len / 2.0 * 1.3);
                    int samples = (int) Math.max(2, Math.min(80, Math.ceil(len / Math.max(cell, halfWidth))));
                    LengthIndexedLine idx = new LengthIndexedLine(lineMetric);
                    for (int s = 0; s <= samples; s++) {
                        Coordinate c = idx.extractPoint(len * s / samples);
                        ctx.peaks.add(new WorldGenContext.Peak(c.x, c.y, height, halfWidth));
                    }
                } else {
                    // Brak linii grzbietu — pojedynczy garb w centroidzie.
                    Point centroid = areaMetric.getCentroid();
                    double equivRadius = Math.sqrt(areaM / Math.PI);
                    double radius = Math.max(cell * 3, equivRadius) * 1.4;
                    ctx.peaks.add(new WorldGenContext.Peak(centroid.getX(), centroid.getY(), height, radius));
                }
            }
        }

        // Predefiniowane rzeki → łamane world-metric (do wypalenia w DEM).
        if (rivers != null) {
            List<double[][]> paths = new ArrayList<>();
            for (ModelRiver r : rivers) {
                if (r.getLine() == null) {
                    continue;
                }
                paths.add(toXY(HighMapUtils.mapToMetric(r.getLine()).getCoordinates()));
            }
            if (!paths.isEmpty()) {
                ctx.riverPaths = paths;
            }
        }
        // Predefiniowane jeziora → pierścienie zewnętrzne world-metric.
        if (lakes != null) {
            List<double[][]> rings = new ArrayList<>();
            for (ModelLake l : lakes) {
                if (l.getArea() == null) {
                    continue;
                }
                Geometry m = HighMapUtils.mapToMetric(l.getArea());
                rings.add(toXY(((Polygon) m).getExteriorRing().getCoordinates()));
            }
            if (!rings.isEmpty()) {
                ctx.lakePolys = rings;
            }
        }

        // Pole wilgotności z regionów (opad → hydrologia). Poziom 0..1 z Humidity.
        // Punkt poza regionami => -1 (hydrologia weźmie neutralną).
        if (regions != null && !regions.isEmpty()) {
            List<Envelope> hEnvs = new ArrayList<>();
            List<PreparedGeometry> hPreps = new ArrayList<>();
            List<Double> hLevels = new ArrayList<>();
            for (ModelRegion r : regions) {
                if (r.getArea() == null || r.getHumidity() == null) {
                    continue;
                }
                Geometry g = HighMapUtils.mapToMetric(r.getArea());
                hEnvs.add(g.getEnvelopeInternal());
                hPreps.add(PreparedGeometryFactory.prepare(g));
                hLevels.add(r.getHumidity().getLevel());
            }
            if (!hPreps.isEmpty()) {
                ctx.humidity = (x, y) -> {
                    Point p = null;
                    for (int k = 0; k < hPreps.size(); k++) {
                        if (!hEnvs.get(k).contains(x, y)) {
                            continue;
                        }
                        if (p == null) {
                            p = GF.createPoint(new Coordinate(x, y));
                        }
                        if (hPreps.get(k).contains(p)) {
                            return hLevels.get(k);
                        }
                    }
                    return -1;
                };
            }
        }
        return ctx;
    }

    private static double[][] toXY(Coordinate[] cs) {
        double[][] xy = new double[cs.length][2];
        for (int k = 0; k < cs.length; k++) {
            xy[k][0] = cs[k].x;
            xy[k][1] = cs[k].y;
        }
        return xy;
    }

    private static boolean contains(List<PreparedGeometry> preps, List<Envelope> envs, double x, double y) {
        Point p = null;
        for (int k = 0; k < preps.size(); k++) {
            if (!envs.get(k).contains(x, y)) {
                continue;
            }
            if (p == null) {
                p = GF.createPoint(new Coordinate(x, y));
            }
            if (preps.get(k).contains(p)) {
                return true;
            }
        }
        return false;
    }
}
