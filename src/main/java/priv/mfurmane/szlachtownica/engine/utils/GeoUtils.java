package priv.mfurmane.szlachtownica.engine.utils;

import org.locationtech.jts.geom.*;
import org.locationtech.jts.geom.impl.CoordinateArraySequence;

import java.io.*;
import java.util.*;

public class GeoUtils {
    public static final int WIDTH = 1499;
    public static final int HEIGHT = 1090;
    public static final double LON_MIN = 68.79;
    public static final double LON_MAX = 81.21;
    public static final double LAT_MIN = -46.5;
    public static final double LAT_MAX = -39.9;
    private static final GeometryFactory gf = new GeometryFactory(new PrecisionModel(), 4326);
    public static final String PROVINCES_PATH = "src/main/resources/provinces/areas/";
    public static final String RIVERS_PATH = "src/main/resources/rivers/";
    public static final String ROADS_PATH = "src/main/resources/roads/";
    private static final String LAKES_PATH = "src/main/resources/lakes/";

    /**
     * Mapuje piksel (x,y) do współrzędnych geograficznych (lat, lon) w stopniach.
     *
     * @param x pixel x (0..width-1)
     * @param y pixel y (0..height-1)
     * @return double[]{latitude, longitude}
     */
    public static Coordinate pixelToLatLon(int x, int y) {
        if (WIDTH <= 1 || HEIGHT <= 1) throw new IllegalArgumentException("width/height must be > 1");
        double lon = LON_MIN + (double) x / (WIDTH - 1) * (LON_MAX - LON_MIN);
        double lat = LAT_MAX - (double) y / (HEIGHT - 1) * (LAT_MAX - LAT_MIN);
        return new Coordinate(lat, lon);
    }

    /**
     * Mapuje współrzędne geograficzne (lat, lon) do pikseli (x,y).
     * Wyniki mogą wyjść poza obraz (np. -10 lub width+5) jeśli wsp. są poza bbox.
     *
     * @return int[]{x, y}
     */
    public static int[] latLonToPixel(double lat, double lon, int width, int height,
                                      double minLon, double maxLon,
                                      double minLat, double maxLat) {
        if (width <= 1 || height <= 1) throw new IllegalArgumentException("width/height must be > 1");

        double fx = (lon - minLon) / (maxLon - minLon);
        double fy = (maxLat - lat) / (maxLat - minLat); // odwrotność osi y

        int x = (int) Math.round(fx * (width - 1));
        int y = (int) Math.round(fy * (height - 1));
        return new int[]{x, y};
    }

    public static Polygon readProvince(String arg) {
        return readPolygon(arg, PROVINCES_PATH);
    }

    public static Polygon readLake(String arg) {
        return readPolygon(arg, LAKES_PATH);
    }

    public static LineString readRiver(String arg) {
        return readLine(arg, RIVERS_PATH);
    }

    public static LineString readRoad(String arg) {
        return readLine(arg, ROADS_PATH);
    }

    private static Polygon readPolygon(String arg, String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path + arg.toLowerCase() + ".points"))) {
            List<Coordinate> coords = br.lines()
                    .map(line -> line.split(","))
                    .map(parts -> pixelToLatLon(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])))
                    .toList();
            List<Coordinate> used = new ArrayList<>();
            List<Polygon> polygons = new ArrayList<>();
            coords.forEach(value -> {
                if (used.contains(value)) {
                    used.add(value);
                    LinearRing linearRing = gf.createLinearRing(used.toArray(new Coordinate[0]));
                    polygons.add(new Polygon(linearRing, null, gf));
                    used.clear();
                } else {
                    used.add(value);
                }
            });
            return polygons.get(0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static LineString readLine(String arg, String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path + arg.toLowerCase() + ".points"))) {
            List<Coordinate> coords = br.lines()
                    .map(line -> line.split(","))
                    .map(parts -> pixelToLatLon(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])))
                    .toList();
            CoordinateSequence points = new CoordinateArraySequence(coords.toArray(new Coordinate[0]));
            return new LineString(points, gf);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        GeoUtils utils = new GeoUtils();
        File file = new File("src/main/resources/provinces/");
        Arrays.stream(Objects.requireNonNull(file.listFiles())).forEach(file1 -> {
            if (!file1.isDirectory()) {
                Polygon polygon = readProvince(file1.getName().replace(".txt", ""));
                System.out.println(file1.getName().replace(".txt", "")+"\n"+polygon.toText());
            }
        });
    }
}