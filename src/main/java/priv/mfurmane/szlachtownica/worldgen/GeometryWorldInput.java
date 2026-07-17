package priv.mfurmane.szlachtownica.worldgen;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Envelope;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.prep.PreparedGeometry;
import org.locationtech.jts.geom.prep.PreparedGeometryFactory;
import priv.mfurmane.szlachtownica.engine.utils.HighMapUtils;
import priv.mfurmane.szlachtownica.model.main.ModelMountains;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter: buduje {@link WorldGenContext} z geometrii świata. To jedyny kawałek
 * worldgen dotykający JTS i modelu — rdzeń (etapy) pozostaje czysty.
 *
 * Maska lądu = przynależność punktu do którejkolwiek prowincji (prepared.contains
 * z tanim prefiltrem po obwiedni). Szczyty = centroidy pasm gór w metryce,
 * z promieniem wyprowadzonym z powierzchni pasma.
 */
public class GeometryWorldInput {

    private static final GeometryFactory GF = new GeometryFactory();

    /**
     * @param areasMetric obszary prowincji w układzie metrycznym (EPSG:32743)
     * @param mountains   pasma gór (Polygon w EPSG:4326 + wysokość w metrach)
     * @param config      parametry (m.in. metersPerPixel — dolna granica rozmiaru piksela)
     * @param maxDim      górny limit dłuższego boku rastra w px (kontrola kosztu)
     */
    public static WorldGenContext fromProvinces(List<Geometry> areasMetric,
                                                List<ModelMountains> mountains,
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
        ctx.landMask = (x, y) -> {
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
        };

        if (mountains != null) {
            for (ModelMountains m : mountains) {
                if (m.getArea() == null || m.getHeight() == null) {
                    continue;
                }
                Geometry areaMetric = HighMapUtils.mapToMetric(m.getArea());
                Point centroid = areaMetric.getCentroid();
                double equivRadius = Math.sqrt(Math.max(areaMetric.getArea(), 1.0) / Math.PI);
                double radius = Math.max(cell * 3, equivRadius) * 1.4;
                ctx.peaks.add(new WorldGenContext.Peak(centroid.getX(), centroid.getY(), m.getHeight(), radius));
            }
        }
        return ctx;
    }
}
