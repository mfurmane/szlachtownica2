package priv.mfurmane.szlachtownica.model;

import org.locationtech.jts.geom.*;
import org.locationtech.jts.geom.prep.PreparedGeometry;
import org.locationtech.jts.geom.prep.PreparedGeometryFactory;
import org.locationtech.jts.triangulate.VoronoiDiagramBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import priv.mfurmane.szlachtownica.engine.MainEngine;
import priv.mfurmane.szlachtownica.engine.utils.GeoUtils;
import priv.mfurmane.szlachtownica.engine.utils.HighMapUtils;
import priv.mfurmane.szlachtownica.engine.utils.MapPrinterUtils;
import priv.mfurmane.szlachtownica.model.config.ConfigurationProvince;
import priv.mfurmane.szlachtownica.model.config.ConfigurationSubProvince;
import priv.mfurmane.szlachtownica.model.main.*;
import priv.mfurmane.szlachtownica.model.main.entities.EntityRegion;
import priv.mfurmane.szlachtownica.model.main.entities.EntitySubProvince;
import priv.mfurmane.szlachtownica.model.main.repositories.ProvinceRepository;
import priv.mfurmane.szlachtownica.model.main.repositories.RegionRepository;
import priv.mfurmane.szlachtownica.model.main.repositories.SubProvinceRepository;
import priv.mfurmane.szlachtownica.model.simulation.SimulationPlace;
import priv.mfurmane.szlachtownica.model.simulation.SimulationProvince;
import priv.mfurmane.szlachtownica.model.simulation.terrain.*;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class ProvinceInitializer {
    public static final GeometryFactory gf = new GeometryFactory();
    public static final Random rand = new Random();
    public static final int GREAT_FERTILITY_LEVEL = 2;
    private final ProvinceRepository provinceRepository;
    private final SubProvinceRepository subProvinceRepository;
    private final RegionRepository regionRepository;
    private MainEngine engine;

    @Autowired
    public ProvinceInitializer(ProvinceRepository provinceRepository, SubProvinceRepository subProvinceRepository, RegionRepository regionRepository) {
        this.provinceRepository = provinceRepository;
        this.subProvinceRepository = subProvinceRepository;
        this.regionRepository = regionRepository;
    }

    public void setEngine(MainEngine mainEngine) {
        this.engine = mainEngine;
    }

    public void initializeProvinces(List<ModelSeaPart> seaShapes, List<ModelRiver> rivers, List<ModelLake> lakes) {
        SimulationProvince merinia = initializeMerinia();
        SimulationProvince nowacorellia = initializeCorellia();
        SimulationProvince orvanor = initializeOrvanor();
        SimulationProvince pirena = initializePirena();
        SimulationProvince carasera = initializeCarasera();
        SimulationProvince viroelann = initializeViroelann();
        SimulationProvince alstederia = initializeAlstederia();
        SimulationProvince druantia = initializeDruantia();
        SimulationProvince saraidan = initializeSaraidan();
        SimulationProvince miedzygorze = initializeMiedzygorze();
        SimulationProvince larazza = initializeLarazza();
        SimulationProvince zaviles = initializeZaviles();
        SimulationProvince zielonarubiez = initializeZielonarubiez();
        List<SimulationProvince> provinces = List.of(
                merinia, nowacorellia, orvanor, pirena, carasera, viroelann, alstederia,
                druantia, saraidan, miedzygorze, larazza, zaviles, zielonarubiez);
        Map<PreparedGeometry, SimulationProvince> provinceMap = new HashMap<>();
        MapPrinterUtils.preparedGeometryStream = provinces.stream().map(val -> {
            Geometry geometry = HighMapUtils.mapToMetric(val.getModel().getArea());
            PreparedGeometry prep = PreparedGeometryFactory.prepare(geometry);
            provinceMap.put(prep, val);
            return prep;
        }).toList();
        provinces.forEach(province -> {
            int smallLakes = province.getConf().getLakesRichness();
            List<ModelLake> internalLakes = new ArrayList<>();
            List<SimulationPlace> cities = new ArrayList<>();
            List<Polygon> possibleLakes = generateSubGeometries(province.getModel().getArea(), province.getConf().getSubProvinces().size() * 80, new ArrayList<>(), true);
            List<Coordinate> cityCoordinates = new ArrayList<>();
            province.getConf().getInitialCities().forEach(cityId -> {
                SimulationPlace place = MainEngine.getInstance().getPlaceRegistry().get(cityId);
                if (place != null && place.getModel().getLocation() != null) {
                    cities.add(place);
                    cityCoordinates.add(place.getModel().getLocation().getCoordinate());
                }
            });
            for (int i = 0; i < smallLakes; i++) {
                ModelLake lake = new ModelLake("brak_nazwy");
                Polygon polygon = possibleLakes.get(rand.nextInt(possibleLakes.size() - 1));
                while (containsCity(cities, polygon)) {
                    polygon = possibleLakes.get(rand.nextInt(possibleLakes.size() - 1));
                }
                lake.setArea(polygon);
                internalLakes.add(lake);
            }
            List<ModelLake> allLakes = new ArrayList<>();
            allLakes.addAll(lakes);
            allLakes.addAll(internalLakes);
            List<Polygon> polygons = generateSubGeometries(province.getModel().getArea(), province.getConf().getSubProvinces().size(), cityCoordinates, false);
            for (int i = 0; i < province.getConf().getSubProvinces().size(); i++) {
                ConfigurationSubProvince subProvince = province.getConf().getSubProvinces().get(i);
                EntitySubProvince sub = new EntitySubProvince();
                sub.setArea(polygons.get(i));
                sub.setProvince(province.getModel().toEntity());
                sub = subProvinceRepository.save(sub);
                ModelSubProvince model = province.getModel().initializeSubProvinces(subProvince, province.getConf(), sub);
                initializeRegions(sub, model, subProvince);
                List<ModelRegion> regions = model.getRegions();
                int regionsToSettle = subProvince.getInitiallyOccupied();
                for (ModelRegion region : regions) {
                    for (SimulationPlace city : cities) {
                        Point location = city.getModel().getLocation();
                        if (region.getArea().contains(location)) {
                            region.setType(RegionType.SETTLERS_REACH);
                            region.getPlaces().add(city.getModel());
                            regionsToSettle--;
                            region.setGeneralProfile(province.getConf().getCapitalProfile());
                            //TODO trzeba jednak przestawić subprowincje tak, żeby pierwsze zawierały miasta, bo wybierałem je pod kątem klimatu itp
                            //      chyba, że to się ogarnęło samo przez punkty podawane do generateSubGeometries
                        }
                    }
                    seaShapes.forEach(shape -> {
                        if (shape.getArea().intersects(region.getArea()) && shape.getArea().intersection(region.getArea()).getDimension() != 0) {
                            region.setCoast(true);
                        }
                    });
                    allLakes.forEach(lake -> {
                        if (lake.getArea().intersects(region.getArea()) && lake.getArea().intersection(region.getArea()).getDimension() != 0) {
                            region.setLake(true);
                        }
                    });
                    rivers.forEach(river -> {
                        if (river.getLine().intersects(region.getArea()) && river.getLine().intersection(region.getArea()).getDimension() != 0) {
                            region.setRiver(true);
                        }
                    });
                    //uzależnić typy, klimat i resztę od rzeczy
                    if (region.getPlaces().size() == 0) {
                        region.setGeneralProfile(new ModelRegion.GeneralProfile(
                                        determineHumidity(region, model),
                                        determineClimate(region, model),
                                        determineTerrainShape(region, model),
                                        determineEnchant(region, model)))
                                .setWoodRichness(determineWoodRichness(region, model, province.getConf().getWoodRichness()));
                    }
                }
                settleBestRegions(regions, regionsToSettle, province.getConf().getInitialSettlersProfile());
                fillParameterBasedRegionTypes(regions, subProvince.getRegionsCount() - subProvince.getInitiallyOccupied());
//                determineOtherRegions(regions, subProvince.getRegionsCount() - subProvince.getInitiallyOccupied(), province.getConf().getInitialNaturalProfile());

// TODO Powiązać odpowiednie numerowo regiony z miastami i poligonami na mapie

//                province.getModel().getSubProvinces().get(0).getModel().getRegions().get(0).getPlaces().add(cityId);
//                sub.setArea(model.getArea());
//                subProvinceRepository.save(sub);
                //TODO czy usuwać, czy zostawić?
//                internalLakes.forEach(lake -> {
//                    model.setArea(removeLakes(lake, model));
//
//                });
            }
        });
        List<Geometry> mapped = provinces.stream().map(val -> HighMapUtils.mapToMetric(val.getModel().getArea())).toList();
        MapPrinterUtils.env = HighMapUtils.getEnvelope(mapped);
        MapPrinterUtils.emptyMap = HighMapUtils.getEmptyMap(MapPrinterUtils.env);
        MapPrinterUtils.print(provinceMap);

        try {
            ImageIO.write(GeoUtils.getBufferedImage(), "png", new File("dem.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Integer determineWoodRichness(ModelRegion region, ModelSubProvince model, int woodRichness) {
        if (region.getPlaces().size() > 0) {
            //przycięte solidnie przez miasto
        }
        int base = woodRichness;
        switch (region.getType()) {
            case FOREST -> {
                base = (int) (1.3 * base);
            }
            case PINE_CRAG -> {
                base = (int) (0.6 * base);
            }
            case ABANDONED_REACH -> {
                base = (int) (0.3 * base);
            }
            case DUST_PLAIN, IRON_MARCHES -> {
                base = 0;
            }
        }
        switch (region.getEnchant()) {
            case NERENETH -> base = (int) (base * 1.2 * );
        }
        return woodRichness;
    }

    private EnchantType determineEnchant(ModelRegion region, ModelSubProvince model) {
        if (region.getPlaces().size() > 0) {
            //raczej NONE
        }
        return null;
    }

    private TerrainShape determineTerrainShape(ModelRegion region, ModelSubProvince model) {
        if (region.getPlaces().size() > 0) {
            //najlepsze dostępne
        }
        return null;
    }

    private Climate determineClimate(ModelRegion region, ModelSubProvince model) {
        if (region.getPlaces().size() > 0) {
            //najlepsze dostępne
        }
        return null;
    }

    private Humidity determineHumidity(ModelRegion region, ModelSubProvince model) {
        if (region.getPlaces().size() > 0) {
            //najlepsze dostępne
        }
        return null;
    }

    private void settleBestRegions(List<ModelRegion> regions, int regionsToSettle, Map<RegionType, Double> initialSettlersProfile) {
        regions.sort(ModelRegion::compareTo);
        for (int i = 0; i < regionsToSettle; i++) {
            ModelRegion region = regions.get(i);
            if (region.getType().equals(RegionType.SETTLERS_REACH)) {
                i--;
            } else {
                if (region.getEnchantmentLevel() > 3) {
                    region.setType(RegionType.SUPERNATURAL_EXPANSE);
                } else if (region.getFertility() > GREAT_FERTILITY_LEVEL) {
                    region.setType(RegionType.FARMING_LAND);
                } else {
                    region.setType(chooseType(initialSettlersProfile, true));
                }
            }
            createPlacesForRegion(region);
        }
    }

    private void createPlacesForRegion(ModelRegion region) {
        //TODO na podstawie typu i innych warunków stworzyć miasteczka, wsie, plantacje itp
    }

    private void fillParameterBasedRegionTypes(List<ModelRegion> regions, int notDetermined) {
        for (int i = regions.size() - 1; i >= notDetermined; i--) {
            ModelRegion region = regions.get(i);
            /*
            * BARE_CRAG
                SALINE
                ASHEN
                PERMAFROST
            * */
            if (region.getSoilType().equals(SoilType.PERMAFROST)) {
                region.setType(RegionType.ROCK_LAND);
            } else if (region.getSoilType().getFertility() == 0) {
                //region.getTerrainShape().equals(TerrainShape.BADLANDS) ||
                region.setType(RegionType.ROCK_LAND);
                region.setType(RegionType.DUST_PLAIN);
            }
            //TODO rozpatrzeć wszystkie opcje i możliwości
//            region.setType();
        }
    }

    private void determineOtherRegions(List<ModelRegion> regions, int notDetermined, Map<RegionType, Double> initialNaturalProfile) {
        //TODO nie jestem pewny, czy > czy >=
        for (int i = regions.size() - 1; i >= notDetermined; i--) {
            ModelRegion region = regions.get(i);
            region.setType(chooseType(initialNaturalProfile, false));
        }
    }

    private static RegionType chooseType(Map<RegionType, Double> regions, boolean settled) {
        if (new Random().nextDouble() < 0.07)
            return randomFromCategory(settled);
        return randomFromMap(regions).orElse(randomFromCategory(settled));
    }

    private static RegionType randomFromCategory(boolean settled) {
        List<RegionType> values;
        if (settled) {
            values = RegionType.getSettled();
        } else {
            values = RegionType.getNatural();
        }
        return values.get(ThreadLocalRandom.current().nextInt(values.size()));
    }

    private static <T> Optional<T> randomFromMap(Map<T, Double> distribution) {
        double totalWeight = distribution.values().stream().mapToDouble(Double::doubleValue).sum();
        double roll = new Random().nextDouble() * totalWeight;
        double cumulative = 0.0;
        for (Map.Entry<T, Double> entry : distribution.entrySet()) {
            cumulative += entry.getValue();
            if (roll <= cumulative) {
                return Optional.of(entry.getKey());
            }
        }
        return Optional.empty();
    }

    private boolean containsCity(List<SimulationPlace> cities, Polygon polygon) {
        for (SimulationPlace city : cities) {
            if (polygon.contains(city.getModel().getLocation())) {
                return true;
            }
        }
        return false;
    }

//    private static Geometry removeLakes(ModelLake lake, ModelSubProvince model) {
//        return model.getArea().difference(lake.getArea());
//    }

    private void initializeRegions(EntitySubProvince sub, ModelSubProvince model, ConfigurationSubProvince conf) {
        List<Polygon> polygons = generateSubGeometries(sub.getArea(), conf.getRegionsCount(), new ArrayList<>(), false);
        for (int i = 0; i < polygons.size(); i++) {
            Polygon polygon = polygons.get(i);
            ModelRegion modelRegion = model.getRegions().get(i);
            EntityRegion entity = new EntityRegion(modelRegion);
            entity.setArea(polygon);
            entity.setSubProvince(sub);
            entity = regionRepository.save(entity);
            modelRegion.setId(entity.getId());
            modelRegion.setArea(polygon);
//            if ()
        }
    }

    private List<Polygon> generateSubGeometries(Polygon area, int partsCount, List<Coordinate> points, boolean smallDist) {
//        List<Coordinate> points = new ArrayList<>();
        Envelope env = area.getEnvelopeInternal();
        int attempts = 0;
        int maxAttempts = 10000;
        double minDist = smallDist ? env.getHeight() * 0.05 / 20 : env.getHeight() * 0.05;
        //province.getConf().getSubProvinces().size()

        while (points.size() < partsCount && attempts < maxAttempts) {
            attempts++;
            double x = env.getMinX() + rand.nextDouble() * env.getWidth();
            double y = env.getMinY() + rand.nextDouble() * env.getHeight();
            Coordinate coordinate = new Coordinate(x, y);
            Point p = gf.createPoint(coordinate);
            boolean tooClose = points.stream()
                    .anyMatch(c -> c.distance(coordinate) < minDist);
            Geometry safeArea = area.buffer(-minDist);
            if (!tooClose && safeArea.contains(p)) {
                points.add(coordinate);
            }
        }
        if (attempts >= maxAttempts) {
            throw new RuntimeException("Nie udało się wygenerować punktów");
        }
        List<Polygon> subGeoms = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            subGeoms = voronoiThisShit(partsCount, points, area);
            points = subGeoms.stream()
                    .map(p -> p.getCentroid().getCoordinate())
                    .toList();
        }
//        Envelope clip = new Envelope(0, 100, 0, 100);
//        VoronoiPerlinNaturalizer naturalizer = new VoronoiPerlinNaturalizer(gf, 24, 0.002, 1.0, 10); //new VoronoiNaturalizer(gf, 5, 2.0); // 5 segmentów, max przesunięcie 2
//        return naturalizer.generateNaturalVoronoi(points, clip);
//        return naturalizer.naturalizeVoronoi(subGeoms);
        return subGeoms;

//        result.get
//        for (int i = 0; i < province.getModel().getSubProvinces().size(); i++) {
//            SimulationSubProvince s = province.getModel().getSubProvinces().get(i);
//            s.getModel().setArea(subGeoms.get(i));
//        }
    }

    private static List<Polygon> voronoiThisShit(int partsSize, List<Coordinate> points, Polygon area) {
        VoronoiDiagramBuilder voronoi = new VoronoiDiagramBuilder();
        voronoi.setTolerance(0.0001);
        voronoi.setSites(points);
// opcjonalne (lekko ogranicza rozmiar)
//        voronoi.setClipEnvelope(area.getEnvelopeInternal());
        Geometry diagram = voronoi.getDiagram(gf);

        List<Polygon> subGeoms = new ArrayList<>();
        for (int i = 0; i < diagram.getNumGeometries(); i++) {
            Geometry cell = diagram.getGeometryN(i);
            Geometry clipped = cell.intersection(area);
            if (clipped.isEmpty()) continue;
            if (clipped instanceof Polygon p) {
                subGeoms.add(p);
            } else if (clipped instanceof MultiPolygon mp) {
                // wybierz największy kawałek
                Polygon biggest = null;
                double maxArea = -1;
                for (int j = 0; j < mp.getNumGeometries(); j++) {
                    Polygon candidate = (Polygon) mp.getGeometryN(j);
                    double areaSize = candidate.getArea();
                    if (areaSize > maxArea) {
                        maxArea = areaSize;
                        biggest = candidate;
                    }
                }
                if (biggest != null) {
                    subGeoms.add(biggest);
                }
//                for (int j = 0; j < mp.getNumGeometries(); j++) {
//                    subGeoms.add((Polygon) mp.getGeometryN(j));
//                }
            }
        }
        double amplitude = area.getEnvelopeInternal().getWidth() * 0.015;
        VoronoiPerlinNaturalizer naturalizer = new VoronoiPerlinNaturalizer(gf, 10, 0.1, 2.0, 10); //new VoronoiNaturalizer(gf, 5, 2.0); // 5 segmentów, max przesunięcie 2
//        return naturalizer.generateNaturalVoronoi(points, clip);
//        return naturalizer.naturalizeVoronoi(subGeoms);
        subGeoms = naturalizer.naturalizeVoronoi(subGeoms);
        if (subGeoms.size() != partsSize) {
            throw new IllegalStateException("Mismatch: " + subGeoms.size() + " vs " + partsSize);
        }
        return subGeoms;
    }

    private SimulationProvince initializeProvince(ConfigurationProvince configuration, ModelProvince model) {
        SimulationProvince province = engine.getProvinceFactory().newProvince();
        province.setConf(configuration);
        province.setModel(model);
        province.getModel().setArea(GeoUtils.readProvince(province.getModel().getName()));
//        province.getConf().getSubProvinces().forEach(subProvince -> {
//            EntitySubProvince sub = new EntitySubProvince();
//            sub.setProvince(province.getModel().toEntity());
//            sub = subProvinceRepository.save(sub);
//            province.getModel().initializeSubProvinces(subProvince, province.getConf(), sub);
//        });
        provinceRepository.save(province.getModel().toEntity());
        engine.getProvinceRegistry().register(province);
        return province;
    }

    private SimulationProvince initializeMerinia() {
        List<ConfigurationSubProvince> subProvinces = new ArrayList<>();
        subProvinces.add(new ConfigurationSubProvince(26, 14, Climate.NEUTRAL, Humidity.WET, false));
        subProvinces.add(new ConfigurationSubProvince(27, 11, Climate.NEUTRAL, Humidity.NEUTRAL, false));
        subProvinces.add(new ConfigurationSubProvince(27, 6, Climate.NEUTRAL, Humidity.NEUTRAL, false));
        subProvinces.add(new ConfigurationSubProvince(28, 4, Climate.NEUTRAL, Humidity.WET, false));
        subProvinces.add(new ConfigurationSubProvince(28, 2, Climate.NEUTRAL, Humidity.NEUTRAL, false));
        subProvinces.add(new ConfigurationSubProvince(27, 0, Climate.NEUTRAL, Humidity.NEUTRAL, false));
        subProvinces.add(new ConfigurationSubProvince(26, 0, Climate.NEUTRAL, Humidity.DRY, false));
        Map<TerrainShape, Double> terrainProfile = new HashMap<>();
        terrainProfile.put(TerrainShape.FLATLANDS, 0.65);
        terrainProfile.put(TerrainShape.HILLS, 0.3);
        terrainProfile.put(TerrainShape.WETBASIN, 0.05);
        Map<RegionType, Double> naturalProfile = new HashMap<>();
        naturalProfile.put(RegionType.FOREST, 0.6);
        naturalProfile.put(RegionType.MEADOWS, 0.2);
        naturalProfile.put(RegionType.PINE_CRAG, 0.1);
        naturalProfile.put(RegionType.SWAMP, 0.10);
        Map<RegionType, Double> settlersProfile = new HashMap<>();
        settlersProfile.put(RegionType.FARMING_LAND, 0.4);
        settlersProfile.put(RegionType.SETTLERS_REACH, 0.6);
        Map<EnchantType, Double> enchantDistribution = new HashMap<>();
        enchantDistribution.put(EnchantType.NONE, 0.85);
        enchantDistribution.put(EnchantType.NERENETH, 0.06);
        enchantDistribution.put(EnchantType.GHALAGAAR, 0.06);
        enchantDistribution.put(EnchantType.VOID, 0.01);
        enchantDistribution.put(EnchantType.CAITHALOON, 0.01);
        enchantDistribution.put(EnchantType.TAELIA, 0.01);
        enchantDistribution.put(EnchantType.LIMBO, 0.0);
        enchantDistribution.put(EnchantType.ABYSS, 0.0);
        enchantDistribution.put(EnchantType.VEIL, 0.02);
//        enchantDistribution.put(EnchantType.ENCHANTED, 0.02);
        enchantDistribution.put(EnchantType.CORELLIA, 0.03);
        ModelRegion.GeneralProfile capitalProfile = new ModelRegion.GeneralProfile(Humidity.WET, Climate.NEUTRAL, TerrainShape.FLATLANDS, EnchantType.NONE);
        ConfigurationProvince configuration = ConfigurationProvince.builder()
                .setSubProvinces(subProvinces)
                .setTerrainProfile(terrainProfile)
                .setInitialNaturalProfile(naturalProfile)
                .setInitialSettlersProfile(settlersProfile)
                .setEnchantDistribution(enchantDistribution)
                .setInitialCities(List.of(1L))
                .setSimulationStart(1072)
//                .setAverageHeight(400)
                .setAverageHeight(600)
                .setGrowthPotential(0.75)
                .setInstability(0.2)
                .setLakesRichness(7)
                .setResourceRichness(2)
                .setRiversRichness(13)
                .setWoodRichness(10)
                .setCapitalProfile(capitalProfile)
                .build();
        ModelProvince model = ModelProvince.builder()
                .setId(1L)
                .setName("Merinia")
                .setPreferredDirections(List.of(RegionType.SETTLERS_REACH, RegionType.FARMING_LAND, RegionType.ESTATE_REGION, RegionType.CRAFTS_LAND))
//                .setAreas() ??
//                .setRegions() ??
                .build();
        return initializeProvince(configuration, model);
    }

    private SimulationProvince initializeCorellia() {
        List<ConfigurationSubProvince> subProvinces = new ArrayList<>();
        subProvinces.add(new ConfigurationSubProvince(25, 17, Climate.WARM, Humidity.WET, false));
        subProvinces.add(new ConfigurationSubProvince(26, 8, Climate.WARM, Humidity.NEUTRAL, false));
        subProvinces.add(new ConfigurationSubProvince(27, 4, Climate.WARM, Humidity.WET, false));
        subProvinces.add(new ConfigurationSubProvince(26, 3, Climate.WARM, Humidity.WET, false));
        subProvinces.add(new ConfigurationSubProvince(29, 0, Climate.WARM, Humidity.NEUTRAL, false));
        subProvinces.add(new ConfigurationSubProvince(24, 0, Climate.WARM, Humidity.NEUTRAL, false));
        subProvinces.add(new ConfigurationSubProvince(25, 0, Climate.WARM, Humidity.NEUTRAL, false));
        subProvinces.add(new ConfigurationSubProvince(30, 0, Climate.WARM, Humidity.NEUTRAL, false));
        subProvinces.add(new ConfigurationSubProvince(23, 0, Climate.WARM, Humidity.NEUTRAL, false));
        Map<TerrainShape, Double> terrainProfile = new HashMap<>();
        terrainProfile.put(TerrainShape.FLATLANDS, 0.4);
        terrainProfile.put(TerrainShape.HILLS, 0.3);
        terrainProfile.put(TerrainShape.HIGHLANDS, 0.13);
        terrainProfile.put(TerrainShape.MOUNTAINS, 0.11);
        terrainProfile.put(TerrainShape.VALLEY, 0.1);
        terrainProfile.put(TerrainShape.CANYONS, 0.02);
        terrainProfile.put(TerrainShape.PLATEAUS, 0.03);
        Map<RegionType, Double> naturalProfile = new HashMap<>();
        naturalProfile.put(RegionType.FOREST, 0.50);
        naturalProfile.put(RegionType.MEADOWS, 0.2);
        naturalProfile.put(RegionType.PINE_CRAG, 0.1);
        naturalProfile.put(RegionType.SWAMP, 0.2);
        Map<RegionType, Double> settlersProfile = new HashMap<>();
        settlersProfile.put(RegionType.FARMING_LAND, 0.7);
        settlersProfile.put(RegionType.SETTLERS_REACH, 0.3);
        Map<EnchantType, Double> enchantDistribution = new HashMap<>();
        enchantDistribution.put(EnchantType.NONE, 0.8);
        enchantDistribution.put(EnchantType.NERENETH, 0.06);
        enchantDistribution.put(EnchantType.GHALAGAAR, 0.06);
        enchantDistribution.put(EnchantType.VOID, 0.005);
        enchantDistribution.put(EnchantType.CAITHALOON, 0.005);
        enchantDistribution.put(EnchantType.TAELIA, 0.02);
        enchantDistribution.put(EnchantType.LIMBO, 0.005);
        enchantDistribution.put(EnchantType.ABYSS, 0.005);
        enchantDistribution.put(EnchantType.VEIL, 0.03);
//        enchantDistribution.put(EnchantType.ENCHANTED, 0.02);
        enchantDistribution.put(EnchantType.CORELLIA, 0.06);
        ModelRegion.GeneralProfile capitalProfile = new ModelRegion.GeneralProfile(Humidity.WET, Climate.WARM, TerrainShape.FLATLANDS, EnchantType.CORELLIA);
        ConfigurationProvince configuration = ConfigurationProvince.builder()
                .setSubProvinces(subProvinces)
                .setTerrainProfile(terrainProfile)
                .setInitialNaturalProfile(naturalProfile)
                .setInitialSettlersProfile(settlersProfile)
                .setEnchantDistribution(enchantDistribution)
                .setInitialCities(List.of(2L))
                .setSimulationStart(942)
//                .setAverageHeight(300)
                .setAverageHeight(400)
                .setGrowthPotential(0.75)
                .setInstability(0.15)
                .setLakesRichness(9)
                .setResourceRichness(3)
                .setRiversRichness(10)
                .setWoodRichness(16)
                .setCapitalProfile(capitalProfile)
                .build();
        ModelProvince model = ModelProvince.builder()
                .setId(2L)
                .setName("Nowa Corellia")
                .setPreferredDirections(List.of(RegionType.SETTLERS_REACH, RegionType.ESTATE_REGION, RegionType.TOURISTIC_LAND, RegionType.SUPERNATURAL_EXPANSE))
//                .setAreas() ??
//                .setRegions() ??
                .build();
        return initializeProvince(configuration, model);
    }

    private SimulationProvince initializeOrvanor() {
        List<ConfigurationSubProvince> subProvinces = new ArrayList<>();
        subProvinces.add(new ConfigurationSubProvince(25, 15, Climate.NEUTRAL, Humidity.WET, false));
        subProvinces.add(new ConfigurationSubProvince(26, 6, Climate.COLD, Humidity.WET, false));
        subProvinces.add(new ConfigurationSubProvince(26, 4, Climate.COLD, Humidity.WET, false));
        subProvinces.add(new ConfigurationSubProvince(27, 3, Climate.COLD, Humidity.EXTRA_WET, false));
        subProvinces.add(new ConfigurationSubProvince(29, 0, Climate.VERY_COLD, Humidity.EXTRA_WET, false));
        Map<TerrainShape, Double> terrainProfile = new HashMap<>();
        terrainProfile.put(TerrainShape.WETBASIN, 0.4);
        terrainProfile.put(TerrainShape.VALLEY, 0.2);
        terrainProfile.put(TerrainShape.MOUNTAINS, 0.05);
        terrainProfile.put(TerrainShape.HILLS, 0.15);
        terrainProfile.put(TerrainShape.HIGHLANDS, 0.15);
        terrainProfile.put(TerrainShape.PLATEAUS, 0.05);
        Map<RegionType, Double> naturalProfile = new HashMap<>();
        naturalProfile.put(RegionType.FOREST, 0.25);
        naturalProfile.put(RegionType.MEADOWS, 0.15);
        naturalProfile.put(RegionType.PINE_CRAG, 0.3);
        naturalProfile.put(RegionType.SWAMP, 0.3);
        Map<RegionType, Double> settlersProfile = new HashMap<>();
        settlersProfile.put(RegionType.FARMING_LAND, 0.25);
        settlersProfile.put(RegionType.SETTLERS_REACH, 0.75);
        Map<EnchantType, Double> enchantDistribution = new HashMap<>();
        enchantDistribution.put(EnchantType.NONE, 0.9);
        enchantDistribution.put(EnchantType.NERENETH, 0.005);
        enchantDistribution.put(EnchantType.GHALAGAAR, 0.005);
        enchantDistribution.put(EnchantType.VOID, 0.02);
        enchantDistribution.put(EnchantType.CAITHALOON, 0.02);
        enchantDistribution.put(EnchantType.TAELIA, 0.01);
        enchantDistribution.put(EnchantType.LIMBO, 0.005);
        enchantDistribution.put(EnchantType.ABYSS, 0.005);
        enchantDistribution.put(EnchantType.VEIL, 0.02);
//        enchantDistribution.put(EnchantType.ENCHANTED, 0.02);
        enchantDistribution.put(EnchantType.CORELLIA, 0.02);
        ModelRegion.GeneralProfile capitalProfile = new ModelRegion.GeneralProfile(Humidity.WET, Climate.NEUTRAL, TerrainShape.VALLEY, EnchantType.NONE);
        ConfigurationProvince configuration = ConfigurationProvince.builder()
                .setSubProvinces(subProvinces)
                .setTerrainProfile(terrainProfile)
                .setInitialNaturalProfile(naturalProfile)
                .setInitialSettlersProfile(settlersProfile)
                .setEnchantDistribution(enchantDistribution)
                .setInitialCities(List.of(14L))
                .setSimulationStart(1027)
                .setAverageHeight(700)
                .setGrowthPotential(0.65)
                .setInstability(0.1)
                .setLakesRichness(13)
                .setResourceRichness(9)
                .setRiversRichness(8)
                .setWoodRichness(10)
                .setCapitalProfile(capitalProfile)
                .build();
        ModelProvince model = ModelProvince.builder()
                .setId(12L)
                .setName("Orvanor")
                .setPreferredDirections(List.of(RegionType.CRAFTS_LAND, RegionType.SETTLERS_REACH, RegionType.FARMING_LAND))
//                .setAreas() ??
//                .setRegions() ??
                .build();
        return initializeProvince(configuration, model);
    }

    private SimulationProvince initializePirena() {
        List<ConfigurationSubProvince> subProvinces = new ArrayList<>();
        subProvinces.add(new ConfigurationSubProvince(26, 12, Climate.NEUTRAL, Humidity.WET, false));
        subProvinces.add(new ConfigurationSubProvince(23, 7, Climate.NEUTRAL, Humidity.NEUTRAL, false));
        subProvinces.add(new ConfigurationSubProvince(25, 5, Climate.NEUTRAL, Humidity.NEUTRAL, false));
        subProvinces.add(new ConfigurationSubProvince(26, 5, Climate.NEUTRAL, Humidity.DRY, false));
        subProvinces.add(new ConfigurationSubProvince(27, 0, Climate.NEUTRAL, Humidity.EXTRA_DRY, false));
        subProvinces.add(new ConfigurationSubProvince(27, 0, Climate.COLD, Humidity.DRY, false));
        Map<TerrainShape, Double> terrainProfile = new HashMap<>();
        terrainProfile.put(TerrainShape.FLATLANDS, 0.3);
        terrainProfile.put(TerrainShape.VALLEY, 0.2);
        terrainProfile.put(TerrainShape.MOUNTAINS, 0.05);
        terrainProfile.put(TerrainShape.HILLS, 0.3);
        terrainProfile.put(TerrainShape.HIGHLANDS, 0.1);
        terrainProfile.put(TerrainShape.PLATEAUS, 0.05);
        Map<RegionType, Double> naturalProfile = new HashMap<>();
        naturalProfile.put(RegionType.FOREST, 0.4);
        naturalProfile.put(RegionType.MEADOWS, 0.3);
        naturalProfile.put(RegionType.PINE_CRAG, 0.2);
        naturalProfile.put(RegionType.ROCK_LAND, 0.1);
        Map<RegionType, Double> settlersProfile = new HashMap<>();
        settlersProfile.put(RegionType.FARMING_LAND, 0.8);
        settlersProfile.put(RegionType.SETTLERS_REACH, 0.2);
        Map<EnchantType, Double> enchantDistribution = new HashMap<>();
        enchantDistribution.put(EnchantType.NONE, 0.92);
        enchantDistribution.put(EnchantType.NERENETH, 0.01);
        enchantDistribution.put(EnchantType.GHALAGAAR, 0.01);
        enchantDistribution.put(EnchantType.VOID, 0.01);
        enchantDistribution.put(EnchantType.CAITHALOON, 0.01);
        enchantDistribution.put(EnchantType.TAELIA, 0.01);
        enchantDistribution.put(EnchantType.LIMBO, 0.01);
        enchantDistribution.put(EnchantType.ABYSS, 0.01);
        enchantDistribution.put(EnchantType.VEIL, 0.01);
//        enchantDistribution.put(EnchantType.ENCHANTED, 0.01);
        enchantDistribution.put(EnchantType.CORELLIA, 0.02);
        ModelRegion.GeneralProfile capitalProfile = new ModelRegion.GeneralProfile(Humidity.WET, Climate.NEUTRAL, TerrainShape.HILLS, EnchantType.NONE);
        ConfigurationProvince configuration = ConfigurationProvince.builder()
                .setSubProvinces(subProvinces)
                .setTerrainProfile(terrainProfile)
                .setInitialNaturalProfile(naturalProfile)
                .setInitialSettlersProfile(settlersProfile)
                .setEnchantDistribution(enchantDistribution)
                .setInitialCities(List.of(15L))
                .setSimulationStart(1094)
                .setAverageHeight(500)
                .setGrowthPotential(0.85)
                .setInstability(0.15)
                .setLakesRichness(3)
                .setResourceRichness(8)
                .setRiversRichness(5)
                .setWoodRichness(8)
                .setCapitalProfile(capitalProfile)
                .build();
        ModelProvince model = ModelProvince.builder()
                .setId(10L)
                .setName("Pirena")
                .setPreferredDirections(List.of(RegionType.SETTLERS_REACH, RegionType.CRAFTS_LAND, RegionType.FARMING_LAND))
//                .setAreas() ??
//                .setRegions() ??
                .build();
        return initializeProvince(configuration, model);
    }

    private SimulationProvince initializeCarasera() {
        List<ConfigurationSubProvince> subProvinces = new ArrayList<>();
        subProvinces.add(new ConfigurationSubProvince(29, 10, Climate.NEUTRAL, Humidity.NEUTRAL, false));
        subProvinces.add(new ConfigurationSubProvince(25, 7, Climate.NEUTRAL, Humidity.WET, false));
        subProvinces.add(new ConfigurationSubProvince(27, 5, Climate.NEUTRAL, Humidity.NEUTRAL, false));
        subProvinces.add(new ConfigurationSubProvince(26, 3, Climate.NEUTRAL, Humidity.NEUTRAL, false));
        subProvinces.add(new ConfigurationSubProvince(26, 0, Climate.NEUTRAL, Humidity.DRY, false));
        subProvinces.add(new ConfigurationSubProvince(25, 0, Climate.NEUTRAL, Humidity.DRY, false));
        subProvinces.add(new ConfigurationSubProvince(33, 0, Climate.NEUTRAL, Humidity.EXTRA_DRY, false));
        Map<TerrainShape, Double> terrainProfile = new HashMap<>();
        terrainProfile.put(TerrainShape.FLATLANDS, 0.3);
        terrainProfile.put(TerrainShape.HIGHLANDS, 0.23);
        terrainProfile.put(TerrainShape.HILLS, 0.4);
        terrainProfile.put(TerrainShape.MOUNTAINS, 0.05);
        terrainProfile.put(TerrainShape.CANYONS, 0.02);
        Map<RegionType, Double> naturalProfile = new HashMap<>();
        naturalProfile.put(RegionType.FOREST, 0.58);
        naturalProfile.put(RegionType.MEADOWS, 0.25);
        naturalProfile.put(RegionType.DUST_PLAIN, 0.2);
        naturalProfile.put(RegionType.PINE_CRAG, 0.05);
        Map<RegionType, Double> settlersProfile = new HashMap<>();
        settlersProfile.put(RegionType.FARMING_LAND, 0.2);
        settlersProfile.put(RegionType.SETTLERS_REACH, 0.8);
        Map<EnchantType, Double> enchantDistribution = new HashMap<>();
        enchantDistribution.put(EnchantType.NONE, 0.86);
        enchantDistribution.put(EnchantType.NERENETH, 0.02);
        enchantDistribution.put(EnchantType.GHALAGAAR, 0.02);
        enchantDistribution.put(EnchantType.VOID, 0.02);
        enchantDistribution.put(EnchantType.CAITHALOON, 0.02);
        enchantDistribution.put(EnchantType.TAELIA, 0.02);
        enchantDistribution.put(EnchantType.LIMBO, 0.02);
        enchantDistribution.put(EnchantType.ABYSS, 0.02);
        enchantDistribution.put(EnchantType.VEIL, 0.02);
//        enchantDistribution.put(EnchantType.ENCHANTED, 0.02);
        enchantDistribution.put(EnchantType.CORELLIA, 0.02);
        ModelRegion.GeneralProfile capitalProfile = new ModelRegion.GeneralProfile(Humidity.NEUTRAL, Climate.NEUTRAL, TerrainShape.FLATLANDS, EnchantType.NONE);
        ConfigurationProvince configuration = ConfigurationProvince.builder()
                .setSubProvinces(subProvinces)
                .setTerrainProfile(terrainProfile)
                .setInitialNaturalProfile(naturalProfile)
                .setInitialSettlersProfile(settlersProfile)
                .setEnchantDistribution(enchantDistribution)
                .setInitialCities(List.of(11L))
                .setSimulationStart(1045)
//                .setAverageHeight(350)
                .setAverageHeight(300)
                .setGrowthPotential(0.65)
                .setInstability(0.2)
                .setLakesRichness(2)
                .setResourceRichness(8)
                .setRiversRichness(6)
                .setWoodRichness(11)
                .setCapitalProfile(capitalProfile)
                .build();
        ModelProvince model = ModelProvince.builder()
                .setId(9L)
                .setName("Carasera")
                .setPreferredDirections(List.of(RegionType.CRAFTS_LAND, RegionType.SETTLERS_REACH))
//                .setAreas() ??
//                .setRegions() ??
                .build();
        return initializeProvince(configuration, model);
    }

    private SimulationProvince initializeViroelann() {
        List<ConfigurationSubProvince> subProvinces = new ArrayList<>();
        subProvinces.add(new ConfigurationSubProvince(26, 18, Climate.WARM, Humidity.NEUTRAL, false));
        subProvinces.add(new ConfigurationSubProvince(27, 11, Climate.WARM, Humidity.WET, false));
        subProvinces.add(new ConfigurationSubProvince(24, 8, Climate.WARM, Humidity.NEUTRAL, true));
        subProvinces.add(new ConfigurationSubProvince(29, 5, Climate.WARM, Humidity.NEUTRAL, false));
        subProvinces.add(new ConfigurationSubProvince(29, 4, Climate.WARM, Humidity.WET, false));
        subProvinces.add(new ConfigurationSubProvince(26, 2, Climate.WARM, Humidity.WET, false));
        subProvinces.add(new ConfigurationSubProvince(26, 1, Climate.WARM, Humidity.NEUTRAL, false));
        subProvinces.add(new ConfigurationSubProvince(22, 1, Climate.WARM, Humidity.NEUTRAL, false));
        subProvinces.add(new ConfigurationSubProvince(25, 1, Climate.WARM, Humidity.NEUTRAL, false));
        subProvinces.add(new ConfigurationSubProvince(28, 0, Climate.WARM, Humidity.NEUTRAL, false));
        subProvinces.add(new ConfigurationSubProvince(26, 0, Climate.WARM, Humidity.DRY, false));
        subProvinces.add(new ConfigurationSubProvince(24, 0, Climate.WARM, Humidity.DRY, false));
        Map<TerrainShape, Double> terrainProfile = new HashMap<>();
        terrainProfile.put(TerrainShape.FLATLANDS, 0.3);
        terrainProfile.put(TerrainShape.HILLS, 0.13);
        terrainProfile.put(TerrainShape.HIGHLANDS, 0.13);
        terrainProfile.put(TerrainShape.MOUNTAINS, 0.03);
        terrainProfile.put(TerrainShape.PLATEAUS, 0.07);
        terrainProfile.put(TerrainShape.CANYONS, 0.03);
        terrainProfile.put(TerrainShape.VALLEY, 0.3);
        terrainProfile.put(TerrainShape.BADLANDS, 0.01);
        Map<RegionType, Double> naturalProfile = new HashMap<>();
        naturalProfile.put(RegionType.FOREST, 0.55);
        naturalProfile.put(RegionType.MEADOWS, 0.4);
        naturalProfile.put(RegionType.PINE_CRAG, 0.05);
        Map<RegionType, Double> settlersProfile = new HashMap<>();
        settlersProfile.put(RegionType.FARMING_LAND, 0.5);
        settlersProfile.put(RegionType.SETTLERS_REACH, 0.5);
        Map<EnchantType, Double> enchantDistribution = new HashMap<>();
        enchantDistribution.put(EnchantType.NONE, 0.83);
        enchantDistribution.put(EnchantType.NERENETH, 0.06);
        enchantDistribution.put(EnchantType.GHALAGAAR, 0.06);
        enchantDistribution.put(EnchantType.VOID, 0.01);
        enchantDistribution.put(EnchantType.CAITHALOON, 0.01);
        enchantDistribution.put(EnchantType.TAELIA, 0.01);
        enchantDistribution.put(EnchantType.LIMBO, 0.0);
        enchantDistribution.put(EnchantType.ABYSS, 0.0);
        enchantDistribution.put(EnchantType.VEIL, 0.02);
//        enchantDistribution.put(EnchantType.ENCHANTED, 0.04);
        enchantDistribution.put(EnchantType.CORELLIA, 0.03);
        ModelRegion.GeneralProfile capitalProfile = new ModelRegion.GeneralProfile(Humidity.NEUTRAL, Climate.WARM, TerrainShape.FLATLANDS, EnchantType.TAELIA);
        ConfigurationProvince configuration = ConfigurationProvince.builder()
                .setSubProvinces(subProvinces)
                .setTerrainProfile(terrainProfile)
                .setInitialNaturalProfile(naturalProfile)
                .setInitialSettlersProfile(settlersProfile)
                .setEnchantDistribution(enchantDistribution)
                .setInitialCities(List.of(8L))
                .setSimulationStart(1196)
//                .setAverageHeight(180)
                .setAverageHeight(500)
                .setGrowthPotential(0.85)
                .setInstability(0.1)
                .setLakesRichness(12)
                .setResourceRichness(6)
                .setRiversRichness(16)
                .setWoodRichness(12)
                .setCapitalProfile(capitalProfile)
                .build();
        ModelProvince model = ModelProvince.builder()
                .setId(6L)
                .setName("Viroelann")
                .setPreferredDirections(List.of(RegionType.ESTATE_REGION, RegionType.SUPERNATURAL_EXPANSE, RegionType.FARMING_LAND, RegionType.SETTLERS_REACH))
//                .setAreas() ??
//                .setRegions() ??
                .build();
        return initializeProvince(configuration, model);
    }

    private SimulationProvince initializeAlstederia() {
        List<ConfigurationSubProvince> subProvinces = new ArrayList<>();
        subProvinces.add(new ConfigurationSubProvince(25, 17, Climate.HOT, Humidity.WET, true));
        subProvinces.add(new ConfigurationSubProvince(26, 9, Climate.WARM, Humidity.NEUTRAL, true));
        subProvinces.add(new ConfigurationSubProvince(27, 6, Climate.HOT, Humidity.WET, true));
        subProvinces.add(new ConfigurationSubProvince(26, 4, Climate.WARM, Humidity.WET, true));
        subProvinces.add(new ConfigurationSubProvince(29, 2, Climate.HOT, Humidity.NEUTRAL, false));
        subProvinces.add(new ConfigurationSubProvince(24, 2, Climate.HOT, Humidity.NEUTRAL, false));
        subProvinces.add(new ConfigurationSubProvince(25, 1, Climate.HOT, Humidity.NEUTRAL, false));
        subProvinces.add(new ConfigurationSubProvince(30, 1, Climate.HOT, Humidity.NEUTRAL, false));
        subProvinces.add(new ConfigurationSubProvince(23, 1, Climate.HOT, Humidity.NEUTRAL, false));
        Map<TerrainShape, Double> terrainProfile = new HashMap<>();
        terrainProfile.put(TerrainShape.FLATLANDS, 0.54);
        terrainProfile.put(TerrainShape.VALLEY, 0.25);
        terrainProfile.put(TerrainShape.HILLS, 0.1);
        terrainProfile.put(TerrainShape.BADLANDS, 0.01);
        terrainProfile.put(TerrainShape.WETBASIN, 0.1);
        Map<RegionType, Double> naturalProfile = new HashMap<>();
        naturalProfile.put(RegionType.FOREST, 0.55);
        naturalProfile.put(RegionType.MEADOWS, 0.2);
        naturalProfile.put(RegionType.PINE_CRAG, 0.1);
        naturalProfile.put(RegionType.SWAMP, 0.15);
        Map<RegionType, Double> settlersProfile = new HashMap<>();
        settlersProfile.put(RegionType.FARMING_LAND, 0.3);
        settlersProfile.put(RegionType.SETTLERS_REACH, 0.7);
        Map<EnchantType, Double> enchantDistribution = new HashMap<>();
        enchantDistribution.put(EnchantType.NONE, 0.88);
        enchantDistribution.put(EnchantType.NERENETH, 0.03);
        enchantDistribution.put(EnchantType.GHALAGAAR, 0.03);
        enchantDistribution.put(EnchantType.VOID, 0.01);
        enchantDistribution.put(EnchantType.CAITHALOON, 0.01);
        enchantDistribution.put(EnchantType.TAELIA, 0.02);
        enchantDistribution.put(EnchantType.LIMBO, 0.015);
        enchantDistribution.put(EnchantType.ABYSS, 0.015);
        enchantDistribution.put(EnchantType.VEIL, 0.01);
//        enchantDistribution.put(EnchantType.ENCHANTED, 0.02);
        enchantDistribution.put(EnchantType.CORELLIA, 0.015);
        ModelRegion.GeneralProfile capitalProfile = new ModelRegion.GeneralProfile(Humidity.WET, Climate.HOT, TerrainShape.FLATLANDS, EnchantType.NONE);
        ConfigurationProvince configuration = ConfigurationProvince.builder()
                .setSubProvinces(subProvinces)
                .setTerrainProfile(terrainProfile)
                .setInitialNaturalProfile(naturalProfile)
                .setInitialSettlersProfile(settlersProfile)
                .setEnchantDistribution(enchantDistribution)
                .setInitialCities(List.of(7L))
                .setSimulationStart(968)
//                .setAverageHeight(50)
                .setAverageHeight(200)
                .setGrowthPotential(0.9)
                .setInstability(0.15)
                .setLakesRichness(13)
                .setResourceRichness(4)
                .setRiversRichness(13)
                .setWoodRichness(14)
                .setCapitalProfile(capitalProfile)
                .build();
        ModelProvince model = ModelProvince.builder()
                .setId(5L)
                .setName("Alstederia")
                .setPreferredDirections(List.of(RegionType.TOURISTIC_LAND, RegionType.FARMING_LAND, RegionType.SETTLERS_REACH))
//                .setAreas() ??
//                .setRegions() ??
                .build();
        return initializeProvince(configuration, model);
    }

    private SimulationProvince initializeDruantia() {
        List<ConfigurationSubProvince> subProvinces = new ArrayList<>();
        subProvinces.add(new ConfigurationSubProvince(26, 9, Climate.WARM, Humidity.WET, false));
        subProvinces.add(new ConfigurationSubProvince(25, 8, Climate.WARM, Humidity.WET, false));
        subProvinces.add(new ConfigurationSubProvince(27, 6, Climate.WARM, Humidity.NEUTRAL, false));
        subProvinces.add(new ConfigurationSubProvince(28, 5, Climate.WARM, Humidity.NEUTRAL, false));
        subProvinces.add(new ConfigurationSubProvince(28, 5, Climate.WARM, Humidity.WET, false));
        subProvinces.add(new ConfigurationSubProvince(27, 4, Climate.WARM, Humidity.WET, false));
        subProvinces.add(new ConfigurationSubProvince(27, 2, Climate.WARM, Humidity.NEUTRAL, false));
        subProvinces.add(new ConfigurationSubProvince(28, 0, Climate.WARM, Humidity.NEUTRAL, false));
        subProvinces.add(new ConfigurationSubProvince(27, 0, Climate.WARM, Humidity.WET, false));
        subProvinces.add(new ConfigurationSubProvince(26, 0, Climate.WARM, Humidity.NEUTRAL, false));
        subProvinces.add(new ConfigurationSubProvince(24, 0, Climate.WARM, Humidity.DRY, false));
        subProvinces.add(new ConfigurationSubProvince(22, 0, Climate.WARM, Humidity.DRY, false));
        Map<TerrainShape, Double> terrainProfile = new HashMap<>();
        terrainProfile.put(TerrainShape.FLATLANDS, 0.4);
        terrainProfile.put(TerrainShape.VALLEY, 0.2);
        terrainProfile.put(TerrainShape.HILLS, 0.2);
        terrainProfile.put(TerrainShape.HIGHLANDS, 0.19);
        terrainProfile.put(TerrainShape.BADLANDS, 0.01);
        Map<RegionType, Double> naturalProfile = new HashMap<>();
        naturalProfile.put(RegionType.FOREST, 0.65);
        naturalProfile.put(RegionType.MEADOWS, 0.2);
        naturalProfile.put(RegionType.SWAMP, 0.15);
        Map<RegionType, Double> settlersProfile = new HashMap<>();
        settlersProfile.put(RegionType.FARMING_LAND, 0.3);
        settlersProfile.put(RegionType.SETTLERS_REACH, 0.6);
        settlersProfile.put(RegionType.ABANDONED_REACH, 0.1);
        Map<EnchantType, Double> enchantDistribution = new HashMap<>();
        enchantDistribution.put(EnchantType.NONE, 0.82);
        enchantDistribution.put(EnchantType.NERENETH, 0.7);
        enchantDistribution.put(EnchantType.GHALAGAAR, 0.7);
        enchantDistribution.put(EnchantType.VOID, 0.01);
        enchantDistribution.put(EnchantType.CAITHALOON, 0.01);
        enchantDistribution.put(EnchantType.TAELIA, 0.03);
        enchantDistribution.put(EnchantType.LIMBO, 0.01);
        enchantDistribution.put(EnchantType.ABYSS, 0.01);
        enchantDistribution.put(EnchantType.VEIL, 0.02);
//        enchantDistribution.put(EnchantType.ENCHANTED, 0.03);
        enchantDistribution.put(EnchantType.CORELLIA, 0.01);
        ModelRegion.GeneralProfile capitalProfile = new ModelRegion.GeneralProfile(Humidity.WET, Climate.WARM, TerrainShape.FLATLANDS, EnchantType.NONE);
        ConfigurationProvince configuration = ConfigurationProvince.builder()
                .setSubProvinces(subProvinces)
                .setTerrainProfile(terrainProfile)
                .setInitialNaturalProfile(naturalProfile)
                .setInitialSettlersProfile(settlersProfile)
                .setEnchantDistribution(enchantDistribution)
                .setInitialCities(List.of(5L, 6L))
                .setSimulationStart(265)
//                .setAverageHeight(150)
                .setAverageHeight(400)
                .setGrowthPotential(0.85)
                .setInstability(0.15)
                .setLakesRichness(12)
                .setResourceRichness(6)
                .setRiversRichness(16)
                .setWoodRichness(17)
                .setCapitalProfile(capitalProfile)
                .build();
        ModelProvince model = ModelProvince.builder()
                .setId(4L)
                .setName("Druantia")
                .setPreferredDirections(List.of(RegionType.TOURISTIC_LAND, RegionType.SUPERNATURAL_EXPANSE, RegionType.SETTLERS_REACH, RegionType.FARMING_LAND))
//                .setAreas() ??
//                .setRegions() ??
                .build();
        return initializeProvince(configuration, model);
    }

    private SimulationProvince initializeSaraidan() {
        List<ConfigurationSubProvince> subProvinces = new ArrayList<>();
        subProvinces.add(new ConfigurationSubProvince(39, 14, Climate.WARM, Humidity.WET, false));
        subProvinces.add(new ConfigurationSubProvince(36, 12, Climate.WARM, Humidity.WET, false));
        subProvinces.add(new ConfigurationSubProvince(31, 5, Climate.WARM, Humidity.EXTRA_WET, false));
        subProvinces.add(new ConfigurationSubProvince(29, 4, Climate.WARM, Humidity.WET, false));
        subProvinces.add(new ConfigurationSubProvince(29, 4, Climate.WARM, Humidity.WET, false));
        subProvinces.add(new ConfigurationSubProvince(35, 4, Climate.NEUTRAL, Humidity.WET, false));
        subProvinces.add(new ConfigurationSubProvince(35, 3, Climate.NEUTRAL, Humidity.NEUTRAL, false));
        subProvinces.add(new ConfigurationSubProvince(33, 3, Climate.WARM, Humidity.WET, false));
        subProvinces.add(new ConfigurationSubProvince(32, 2, Climate.NEUTRAL, Humidity.NEUTRAL, false));
        subProvinces.add(new ConfigurationSubProvince(28, 3, Climate.WARM, Humidity.WET, false));
        subProvinces.add(new ConfigurationSubProvince(29, 2, Climate.NEUTRAL, Humidity.NEUTRAL, false));
        subProvinces.add(new ConfigurationSubProvince(36, 2, Climate.WARM, Humidity.EXTRA_WET, false));
        subProvinces.add(new ConfigurationSubProvince(34, 0, Climate.WARM, Humidity.EXTRA_WET, false));
        subProvinces.add(new ConfigurationSubProvince(33, 0, Climate.WARM, Humidity.EXTRA_WET, false));
        subProvinces.add(new ConfigurationSubProvince(30, 0, Climate.WARM, Humidity.NEUTRAL, false));
        Map<TerrainShape, Double> terrainProfile = new HashMap<>();
        terrainProfile.put(TerrainShape.FLATLANDS, 0.4);
        terrainProfile.put(TerrainShape.VALLEY, 0.4);
        terrainProfile.put(TerrainShape.HILLS, 0.1);
        terrainProfile.put(TerrainShape.WETBASIN, 0.1);
        Map<RegionType, Double> naturalProfile = new HashMap<>();
        naturalProfile.put(RegionType.FOREST, 0.7);
        naturalProfile.put(RegionType.MEADOWS, 0.1);
        naturalProfile.put(RegionType.PINE_CRAG, 0.1);
        naturalProfile.put(RegionType.SWAMP, 0.1);
        Map<RegionType, Double> settlersProfile = new HashMap<>();
        settlersProfile.put(RegionType.FARMING_LAND, 0.6);
        settlersProfile.put(RegionType.SETTLERS_REACH, 0.4);
        Map<EnchantType, Double> enchantDistribution = new HashMap<>();
        enchantDistribution.put(EnchantType.NONE, 0.78);
        enchantDistribution.put(EnchantType.NERENETH, 0.12);
        enchantDistribution.put(EnchantType.GHALAGAAR, 0.12);
        enchantDistribution.put(EnchantType.VOID, 0.02);
        enchantDistribution.put(EnchantType.CAITHALOON, 0.02);
        enchantDistribution.put(EnchantType.TAELIA, 0.04);
        enchantDistribution.put(EnchantType.LIMBO, 0.0);
        enchantDistribution.put(EnchantType.ABYSS, 0.0);
        enchantDistribution.put(EnchantType.VEIL, 0.01);
//        enchantDistribution.put(EnchantType.ENCHANTED, 0.02);
        enchantDistribution.put(EnchantType.CORELLIA, 0.01);
        ModelRegion.GeneralProfile capitalProfile = new ModelRegion.GeneralProfile(Humidity.WET, Climate.WARM, TerrainShape.FLATLANDS, EnchantType.NERENETH);
        ConfigurationProvince configuration = ConfigurationProvince.builder()
                .setSubProvinces(subProvinces)
                .setTerrainProfile(terrainProfile)
                .setInitialNaturalProfile(naturalProfile)
                .setInitialSettlersProfile(settlersProfile)
                .setEnchantDistribution(enchantDistribution)
                .setInitialCities(List.of(3L, 4L))
                .setSimulationStart(798)
//                .setAverageHeight(100)
                .setAverageHeight(500)
                .setGrowthPotential(0.6)
                .setInstability(0.1)
                .setLakesRichness(18)
                .setResourceRichness(5)
                .setRiversRichness(20)
                .setWoodRichness(20)
                .setCapitalProfile(capitalProfile)
                .build();
        ModelProvince model = ModelProvince.builder()
                .setId(3L)
                .setName("Saraidan")
                .setPreferredDirections(List.of(RegionType.SUPERNATURAL_EXPANSE, RegionType.FARMING_LAND, RegionType.SETTLERS_REACH))
//                .setAreas() ??
//                .setRegions() ??
                .build();
        return initializeProvince(configuration, model);
    }

    private SimulationProvince initializeMiedzygorze() {
        List<ConfigurationSubProvince> subProvinces = new ArrayList<>();
        subProvinces.add(new ConfigurationSubProvince(29, 12, Climate.COLD, Humidity.NEUTRAL, false));
        subProvinces.add(new ConfigurationSubProvince(31, 7, Climate.COLD, Humidity.NEUTRAL, false));
        subProvinces.add(new ConfigurationSubProvince(27, 5, Climate.COLD, Humidity.DRY, false));
        subProvinces.add(new ConfigurationSubProvince(28, 4, Climate.COLD, Humidity.DRY, false));
        subProvinces.add(new ConfigurationSubProvince(28, 4, Climate.COLD, Humidity.NEUTRAL, false));
        subProvinces.add(new ConfigurationSubProvince(27, 3, Climate.COLD, Humidity.DRY, false));
        subProvinces.add(new ConfigurationSubProvince(29, 3, Climate.COLD, Humidity.DRY, false));
        subProvinces.add(new ConfigurationSubProvince(29, 0, Climate.COLD, Humidity.DRY, false));
        subProvinces.add(new ConfigurationSubProvince(31, 0, Climate.COLD, Humidity.DRY, false));
        subProvinces.add(new ConfigurationSubProvince(28, 0, Climate.COLD, Humidity.DRY, false));
        subProvinces.add(new ConfigurationSubProvince(31, 0, Climate.COLD, Humidity.EXTRA_DRY, false));
        Map<TerrainShape, Double> terrainProfile = new HashMap<>();
        terrainProfile.put(TerrainShape.HIGHLANDS, 0.35);
        terrainProfile.put(TerrainShape.MOUNTAINS, 0.2);
        terrainProfile.put(TerrainShape.VALLEY, 0.05);
        terrainProfile.put(TerrainShape.PLATEAUS, 0.2);
        terrainProfile.put(TerrainShape.CANYONS, 0.1);
        terrainProfile.put(TerrainShape.BADLANDS, 0.1);
        Map<RegionType, Double> naturalProfile = new HashMap<>();
        naturalProfile.put(RegionType.FOREST, 0.1);
        naturalProfile.put(RegionType.PINE_CRAG, 0.25);
        naturalProfile.put(RegionType.DUST_PLAIN, 0.05);
        naturalProfile.put(RegionType.ROCK_LAND, 0.6);
        Map<RegionType, Double> settlersProfile = new HashMap<>();
        settlersProfile.put(RegionType.IRON_MARCHES, 0.3);
        settlersProfile.put(RegionType.CRAFTS_LAND, 0.5);
        settlersProfile.put(RegionType.SETTLERS_REACH, 0.2);
        Map<EnchantType, Double> enchantDistribution = new HashMap<>();
        enchantDistribution.put(EnchantType.NONE, 0.85);
        enchantDistribution.put(EnchantType.NERENETH, 0.06);
        enchantDistribution.put(EnchantType.GHALAGAAR, 0.06);
        enchantDistribution.put(EnchantType.VOID, 0.01);
        enchantDistribution.put(EnchantType.CAITHALOON, 0.01);
        enchantDistribution.put(EnchantType.TAELIA, 0.01);
        enchantDistribution.put(EnchantType.LIMBO, 0.0);
        enchantDistribution.put(EnchantType.ABYSS, 0.0);
        enchantDistribution.put(EnchantType.VEIL, 0.02);
//        enchantDistribution.put(EnchantType.ENCHANTED, 0.02);
        enchantDistribution.put(EnchantType.CORELLIA, 0.03);
        ModelRegion.GeneralProfile capitalProfile = new ModelRegion.GeneralProfile(Humidity.NEUTRAL, Climate.COLD, TerrainShape.MOUNTAINS, EnchantType.NONE);
        ConfigurationProvince configuration = ConfigurationProvince.builder()
                .setSubProvinces(subProvinces)
                .setTerrainProfile(terrainProfile)
                .setInitialNaturalProfile(naturalProfile)
                .setInitialSettlersProfile(settlersProfile)
                .setEnchantDistribution(enchantDistribution)
                .setInitialCities(List.of(12L))
                .setSimulationStart(575)
                .setAverageHeight(1000)
//                .setAverageHeight(900)
                .setGrowthPotential(0.7)
                .setInstability(0.25)
                .setLakesRichness(1)
                .setResourceRichness(30)
                .setRiversRichness(3)
                .setWoodRichness(3)
                .setCapitalProfile(capitalProfile)
                .build();
        ModelProvince model = ModelProvince.builder()
                .setId(11L)
                .setName("Międzygórze")
                .setPreferredDirections(List.of(RegionType.IRON_MARCHES, RegionType.CRAFTS_LAND, RegionType.SETTLERS_REACH))
//                .setAreas() ??
//                .setRegions() ??
                .build();
        return initializeProvince(configuration, model);
    }

    private SimulationProvince initializeLarazza() {
        List<ConfigurationSubProvince> subProvinces = new ArrayList<>();
        subProvinces.add(new ConfigurationSubProvince(24, 7, Climate.HOT, Humidity.NEUTRAL, true));
        subProvinces.add(new ConfigurationSubProvince(26, 6, Climate.HOT, Humidity.NEUTRAL, true));
        subProvinces.add(new ConfigurationSubProvince(27, 5, Climate.HOT, Humidity.WET, true));
        subProvinces.add(new ConfigurationSubProvince(25, 3, Climate.HOT, Humidity.WET, true));
        subProvinces.add(new ConfigurationSubProvince(25, 0, Climate.HOT, Humidity.NEUTRAL, true));
        subProvinces.add(new ConfigurationSubProvince(27, 0, Climate.HOT, Humidity.NEUTRAL, false));
        subProvinces.add(new ConfigurationSubProvince(26, 0, Climate.HOT, Humidity.DRY, false));
        subProvinces.add(new ConfigurationSubProvince(26, 0, Climate.HOT, Humidity.DRY, false));
        subProvinces.add(new ConfigurationSubProvince(25, 0, Climate.HOT, Humidity.EXTRA_WET, false));
        Map<TerrainShape, Double> terrainProfile = new HashMap<>();
        terrainProfile.put(TerrainShape.FLATLANDS, 0.65);
        terrainProfile.put(TerrainShape.HILLS, 0.1);
        terrainProfile.put(TerrainShape.HIGHLANDS, 0.1);
        terrainProfile.put(TerrainShape.MOUNTAINS, 0.05);
        terrainProfile.put(TerrainShape.BADLANDS, 0.1);
        Map<RegionType, Double> naturalProfile = new HashMap<>();
        naturalProfile.put(RegionType.FOREST, 0.5);
        naturalProfile.put(RegionType.MEADOWS, 0.25);
        naturalProfile.put(RegionType.ROCK_LAND, 0.1);
        naturalProfile.put(RegionType.SWAMP, 0.05);
        naturalProfile.put(RegionType.DUST_PLAIN, 0.1);
        Map<RegionType, Double> settlersProfile = new HashMap<>();
        settlersProfile.put(RegionType.FARMING_LAND, 0.7);
        settlersProfile.put(RegionType.SETTLERS_REACH, 0.3);
        Map<EnchantType, Double> enchantDistribution = new HashMap<>();
        enchantDistribution.put(EnchantType.NONE, 0.9);
        enchantDistribution.put(EnchantType.NERENETH, 0.01);
        enchantDistribution.put(EnchantType.GHALAGAAR, 0.01);
        enchantDistribution.put(EnchantType.VOID, 0.01);
        enchantDistribution.put(EnchantType.CAITHALOON, 0.01);
        enchantDistribution.put(EnchantType.TAELIA, 0.01);
        enchantDistribution.put(EnchantType.LIMBO, 0.04);
        enchantDistribution.put(EnchantType.ABYSS, 0.04);
        enchantDistribution.put(EnchantType.VEIL, 0.01);
//        enchantDistribution.put(EnchantType.ENCHANTED, 0.02);
        enchantDistribution.put(EnchantType.CORELLIA, 0.0);
        ModelRegion.GeneralProfile capitalProfile = new ModelRegion.GeneralProfile(Humidity.NEUTRAL, Climate.HOT, TerrainShape.FLATLANDS, EnchantType.NONE);
        ConfigurationProvince configuration = ConfigurationProvince.builder()
                .setSubProvinces(subProvinces)
                .setTerrainProfile(terrainProfile)
                .setInitialNaturalProfile(naturalProfile)
                .setInitialSettlersProfile(settlersProfile)
                .setEnchantDistribution(enchantDistribution)
                .setInitialCities(List.of(9L))
                .setSimulationStart(1200)
                .setAverageHeight(200)
//                .setAverageHeight(70)
                .setGrowthPotential(0.75)
                .setInstability(0.25)
                .setLakesRichness(3)
                .setResourceRichness(8)
                .setRiversRichness(8)
                .setWoodRichness(13)
                .setCapitalProfile(capitalProfile)
                .build();
        ModelProvince model = ModelProvince.builder()
                .setId(7L)
                .setName("Larazza")
                .setPreferredDirections(List.of(RegionType.FARMING_LAND, RegionType.TOURISTIC_LAND, RegionType.SETTLERS_REACH, RegionType.SUPERNATURAL_EXPANSE))
//                .setAreas() ??
//                .setRegions() ??
                .build();
        return initializeProvince(configuration, model);
    }

    private SimulationProvince initializeZaviles() {
        List<ConfigurationSubProvince> subProvinces = new ArrayList<>();
        subProvinces.add(new ConfigurationSubProvince(26, 7, Climate.WARM, Humidity.WET, true));
        subProvinces.add(new ConfigurationSubProvince(27, 5, Climate.WARM, Humidity.NEUTRAL, true));
        subProvinces.add(new ConfigurationSubProvince(24, 2, Climate.WARM, Humidity.NEUTRAL, true));
        subProvinces.add(new ConfigurationSubProvince(29, 0, Climate.WARM, Humidity.DRY, true));
        subProvinces.add(new ConfigurationSubProvince(30, 0, Climate.WARM, Humidity.DRY, false));
        subProvinces.add(new ConfigurationSubProvince(26, 0, Climate.HOT, Humidity.DRY, false));
        subProvinces.add(new ConfigurationSubProvince(26, 0, Climate.HOT, Humidity.EXTRA_DRY, false));
        Map<TerrainShape, Double> terrainProfile = new HashMap<>();
        terrainProfile.put(TerrainShape.FLATLANDS, 0.5);
        terrainProfile.put(TerrainShape.HILLS, 0.1);
        terrainProfile.put(TerrainShape.HIGHLANDS, 0.1);
        terrainProfile.put(TerrainShape.MOUNTAINS, 0.1);
        terrainProfile.put(TerrainShape.BADLANDS, 0.2);
        Map<RegionType, Double> naturalProfile = new HashMap<>();
        naturalProfile.put(RegionType.FOREST, 0.1);
        naturalProfile.put(RegionType.PINE_CRAG, 0.1);
        naturalProfile.put(RegionType.DUST_PLAIN, 0.5);
        naturalProfile.put(RegionType.ROCK_LAND, 0.3);
        Map<RegionType, Double> settlersProfile = new HashMap<>();
        settlersProfile.put(RegionType.FARMING_LAND, 0.2);
        settlersProfile.put(RegionType.SETTLERS_REACH, 0.8);
        Map<EnchantType, Double> enchantDistribution = new HashMap<>();
        enchantDistribution.put(EnchantType.NONE, 0.9);
        enchantDistribution.put(EnchantType.NERENETH, 0.0);
        enchantDistribution.put(EnchantType.GHALAGAAR, 0.0);
        enchantDistribution.put(EnchantType.VOID, 0.03);
        enchantDistribution.put(EnchantType.CAITHALOON, 0.03);
        enchantDistribution.put(EnchantType.TAELIA, 0.01);
        enchantDistribution.put(EnchantType.LIMBO, 0.04);
        enchantDistribution.put(EnchantType.ABYSS, 0.04);
        enchantDistribution.put(EnchantType.VEIL, 0.01);
//        enchantDistribution.put(EnchantType.ENCHANTED, 0.015);
        enchantDistribution.put(EnchantType.CORELLIA, 0.005);
        ModelRegion.GeneralProfile capitalProfile = new ModelRegion.GeneralProfile(Humidity.WET, Climate.WARM, TerrainShape.FLATLANDS, EnchantType.NONE);
        ConfigurationProvince configuration = ConfigurationProvince.builder()
                .setSubProvinces(subProvinces)
                .setTerrainProfile(terrainProfile)
                .setInitialNaturalProfile(naturalProfile)
                .setInitialSettlersProfile(settlersProfile)
                .setEnchantDistribution(enchantDistribution)
                .setInitialCities(List.of(10L))
                .setSimulationStart(1189)
//                .setAverageHeight(150)
                .setAverageHeight(400)
                .setGrowthPotential(0.5)
                .setInstability(0.25)
                .setLakesRichness(1)
                .setResourceRichness(12)
                .setRiversRichness(2)
                .setWoodRichness(3)
                .setCapitalProfile(capitalProfile)
                .build();
        ModelProvince model = ModelProvince.builder()
                .setId(8L)
                .setName("Zaviles")
                .setPreferredDirections(List.of(RegionType.CRAFTS_LAND, RegionType.SETTLERS_REACH))
//                .setAreas() ??
//                .setRegions() ??
                .build();
        return initializeProvince(configuration, model);
    }

    private SimulationProvince initializeZielonarubiez() {
        List<ConfigurationSubProvince> subProvinces = new ArrayList<>();
        subProvinces.add(new ConfigurationSubProvince(26, 14, Climate.WARM, Humidity.WET, false));
        subProvinces.add(new ConfigurationSubProvince(26, 9, Climate.WARM, Humidity.WET, false));
        subProvinces.add(new ConfigurationSubProvince(26, 5, Climate.NEUTRAL, Humidity.WET, false));
        subProvinces.add(new ConfigurationSubProvince(26, 4, Climate.NEUTRAL, Humidity.WET, false));
        subProvinces.add(new ConfigurationSubProvince(26, 3, Climate.WARM, Humidity.WET, false));
        subProvinces.add(new ConfigurationSubProvince(26, 3, Climate.WARM, Humidity.WET, false));
        subProvinces.add(new ConfigurationSubProvince(26, 2, Climate.NEUTRAL, Humidity.WET, false));
        subProvinces.add(new ConfigurationSubProvince(26, 2, Climate.NEUTRAL, Humidity.WET, false));
        subProvinces.add(new ConfigurationSubProvince(26, 2, Climate.WARM, Humidity.WET, false));
        subProvinces.add(new ConfigurationSubProvince(26, 1, Climate.NEUTRAL, Humidity.WET, false));
        subProvinces.add(new ConfigurationSubProvince(26, 1, Climate.NEUTRAL, Humidity.WET, false));
        subProvinces.add(new ConfigurationSubProvince(26, 1, Climate.WARM, Humidity.WET, false));
        subProvinces.add(new ConfigurationSubProvince(26, 1, Climate.NEUTRAL, Humidity.WET, false));
        subProvinces.add(new ConfigurationSubProvince(26, 1, Climate.WARM, Humidity.WET, false));
        subProvinces.add(new ConfigurationSubProvince(26, 0, Climate.NEUTRAL, Humidity.WET, false));
        subProvinces.add(new ConfigurationSubProvince(26, 0, Climate.WARM, Humidity.WET, false));
        subProvinces.add(new ConfigurationSubProvince(26, 0, Climate.NEUTRAL, Humidity.WET, false));
        subProvinces.add(new ConfigurationSubProvince(26, 0, Climate.WARM, Humidity.WET, false));
        subProvinces.add(new ConfigurationSubProvince(26, 0, Climate.NEUTRAL, Humidity.WET, false));
        subProvinces.add(new ConfigurationSubProvince(26, 0, Climate.NEUTRAL, Humidity.WET, false));
        subProvinces.add(new ConfigurationSubProvince(26, 0, Climate.WARM, Humidity.WET, false));
        subProvinces.add(new ConfigurationSubProvince(26, 0, Climate.NEUTRAL, Humidity.WET, false));
        subProvinces.add(new ConfigurationSubProvince(26, 0, Climate.NEUTRAL, Humidity.WET, false));
        subProvinces.add(new ConfigurationSubProvince(26, 0, Climate.NEUTRAL, Humidity.WET, false));
        subProvinces.add(new ConfigurationSubProvince(26, 0, Climate.NEUTRAL, Humidity.WET, false));
        subProvinces.add(new ConfigurationSubProvince(26, 0, Climate.NEUTRAL, Humidity.WET, false));
        Map<TerrainShape, Double> terrainProfile = new HashMap<>();
        terrainProfile.put(TerrainShape.FLATLANDS, 0.4);
        terrainProfile.put(TerrainShape.HIGHLANDS, 0.05);
        terrainProfile.put(TerrainShape.HILLS, 0.15);
        terrainProfile.put(TerrainShape.VALLEY, 0.08);
        terrainProfile.put(TerrainShape.MOUNTAINS, 0.02);
        terrainProfile.put(TerrainShape.WETBASIN, 0.3);
        Map<RegionType, Double> naturalProfile = new HashMap<>();
        naturalProfile.put(RegionType.FOREST, 0.6);
        naturalProfile.put(RegionType.MEADOWS, 0.2);
        naturalProfile.put(RegionType.SWAMP, 0.2);
        Map<RegionType, Double> settlersProfile = new HashMap<>();
        settlersProfile.put(RegionType.FARMING_LAND, 0.7);
        settlersProfile.put(RegionType.SETTLERS_REACH, 0.3);
        Map<EnchantType, Double> enchantDistribution = new HashMap<>();
        enchantDistribution.put(EnchantType.NONE, 0.75);
        enchantDistribution.put(EnchantType.NERENETH, 0.15);
        enchantDistribution.put(EnchantType.GHALAGAAR, 0.15);
        enchantDistribution.put(EnchantType.VOID, 0.005);
        enchantDistribution.put(EnchantType.CAITHALOON, 0.005);
        enchantDistribution.put(EnchantType.TAELIA, 0.05);
        enchantDistribution.put(EnchantType.LIMBO, 0.005);
        enchantDistribution.put(EnchantType.ABYSS, 0.005);
        enchantDistribution.put(EnchantType.VEIL, 0.03);
//        enchantDistribution.put(EnchantType.ENCHANTED, 0.01);
        enchantDistribution.put(EnchantType.CORELLIA, 0.0);
        ModelRegion.GeneralProfile capitalProfile = new ModelRegion.GeneralProfile(Humidity.WET, Climate.WARM, TerrainShape.FLATLANDS, EnchantType.NERENETH);
        ConfigurationProvince configuration = ConfigurationProvince.builder()
                .setSubProvinces(subProvinces)
                .setTerrainProfile(terrainProfile)
                .setInitialNaturalProfile(naturalProfile)
                .setInitialSettlersProfile(settlersProfile)
                .setEnchantDistribution(enchantDistribution)
                .setInitialCities(List.of(19L))
                .setSimulationStart(1268)
//                .setAverageHeight(400)
                .setAverageHeight(600)
                .setGrowthPotential(0.5)
                .setInstability(0.1)
                .setLakesRichness(14)
                .setResourceRichness(10)
                .setRiversRichness(18)
                .setWoodRichness(30)
                .setCapitalProfile(capitalProfile)
                .build();
        ModelProvince model = ModelProvince.builder()
                .setId(13L)
                .setName("Zielona Rubież")
                .setPreferredDirections(List.of(RegionType.SUPERNATURAL_EXPANSE, RegionType.TOURISTIC_LAND, RegionType.SETTLERS_REACH, RegionType.FARMING_LAND))
//                .setAreas() ??
//                .setRegions() ??
                .build();
        return initializeProvince(configuration, model);
    }
}
