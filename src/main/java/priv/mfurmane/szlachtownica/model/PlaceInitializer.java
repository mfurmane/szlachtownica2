package priv.mfurmane.szlachtownica.model;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.springframework.stereotype.Component;
import priv.mfurmane.szlachtownica.engine.MainEngine;
import priv.mfurmane.szlachtownica.model.config.ConfigurationPlace;
import priv.mfurmane.szlachtownica.model.config.ConfigurationSubProvince;
import priv.mfurmane.szlachtownica.model.main.ModelPlace;
import priv.mfurmane.szlachtownica.model.simulation.SimulationPlace;
import priv.mfurmane.szlachtownica.model.simulation.SimulationProvince;
import priv.mfurmane.szlachtownica.model.simulation.terrain.PlaceCharacteristic;
import priv.mfurmane.szlachtownica.model.simulation.terrain.PlaceState;
import priv.mfurmane.szlachtownica.model.simulation.terrain.PlaceType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class PlaceInitializer {
    private MainEngine engine;
    public static final GeometryFactory gf = new GeometryFactory();

//    private final ObjectMapper mapper = new ObjectMapper();

//    public CityInitializer() {
//        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
//    }

    public void setEngine(MainEngine mainEngine) {
        this.engine = mainEngine;
    }

    public void initializePlaces() {
        SimulationPlace gilgamore = initializeGilgamore();
        SimulationPlace lertavore = initializeLertavore();
        SimulationPlace egerenna = initializeEgerenna();
        SimulationPlace verdalon = initializeVerdalon();
        SimulationPlace astaria = initializeAstaria();
        SimulationPlace ergondol = initializeErgondol();
        SimulationPlace sartama = initializeSartama();
        SimulationPlace wornimore = initializeWornimore();
        SimulationPlace korsana = initializeKorsana();
        SimulationPlace zelderin = initializeZelderin();
        SimulationPlace caravista = initializeCaravista();
        SimulationPlace uvarra = initializeUvarra();
        SimulationPlace jirdenal = initializeJirdenal();
        SimulationPlace xalivore = initializeXalivore();
        SimulationPlace pernagol = initializePernagol();
        SimulationPlace isvellin = initializeIsvellin();
        SimulationPlace tantanor = initializeTantanor();
        SimulationPlace durnatel = initializeDurnatel();
        SimulationPlace repenvore = initializeRepenvore();
        SimulationPlace vizarna = initializeVizarna();
        SimulationPlace hasirel = initializeHasirel();
        SimulationPlace naderia = initializeNaderia();
        SimulationPlace quvelmore = initializeQuvelmore();
        SimulationPlace bellenau = initializeBellenau();

        SimulationPlace residel = initializeResidel();
        SimulationPlace qurena = initializeQurena();
        SimulationPlace sardena = initializeSardena();
        SimulationPlace mirnadel = initializeMirnadel();
        SimulationPlace nadarvel = initializeNadarvel();
        SimulationPlace vangodar = initializeVangodar();
        SimulationPlace lutnaran = initializeLutnaran();
        SimulationPlace felarna = initializeFelarna();
        SimulationPlace tirnugal = initializeTirnugal();
        SimulationPlace nonusta = initializeNonusta();
        SimulationPlace anverna = initializeAnverna();
        SimulationPlace golveran = initializeGolveran();
        SimulationPlace ostivore = initializeOstivore();

        List<SimulationPlace> cities = new ArrayList<>(List.of(gilgamore, lertavore, egerenna, verdalon, astaria, ergondol, sartama, wornimore, korsana, zelderin, caravista, uvarra, jirdenal, xalivore, pernagol, isvellin,
                tantanor, durnatel, repenvore, vizarna, hasirel, naderia, quvelmore, bellenau, residel, qurena, sardena, mirnadel, nadarvel, vangodar, lutnaran, felarna, tirnugal, nonusta, anverna, golveran, ostivore));

        String[] names = {"Dupna Wólka"};
        Arrays.stream(names).forEach(name -> {
            cities.add(initializeNamedCity(name, null, new Coordinate(0, 0)));
        });

        cities.forEach(city -> {
            System.out.println(city.getModel().getName());
        });

    }

    private SimulationPlace initializeGilgamore() {
        SimulationPlace city = new SimulationPlace();
        ConfigurationPlace conf = ConfigurationPlace.builder()
                .setBuiltYear(1086)
                .setDestroyedYear(9999)
//                .setPreferedMaterial(Material.)
                .build();
        Coordinate coordinate = new Coordinate(76.93604806408545, -43.32118181818182);
        ModelPlace model = new ModelPlace();
        model.setType(PlaceType.SETTLEMENT)
                .setId(1L)
                .setName("Gilgamore")
                .setState(PlaceState.ALRIGHT)
                .setLocation( gf.createPoint(coordinate));
        model.getPlaceCharacteristics().add(PlaceCharacteristic.CAPITAL);
        model.getPlaceCharacteristics().add(PlaceCharacteristic.LOCAL_CAPITAL);
        model.getPlaceCharacteristics().add(PlaceCharacteristic.FORTIFIED);
        city.setConf(conf);
        city.setModel(model);
        MainEngine.getInstance().getPlaceRegistry().register(city);
        return city;
    }

    private SimulationPlace initializeLertavore() {
        SimulationPlace city = new SimulationPlace();
        ConfigurationPlace conf = ConfigurationPlace.builder()
                .setBuiltYear(1195)
                .setDestroyedYear(9999)
//                .setPreferedMaterial(Material.)
                .build();
        Coordinate coordinate = new Coordinate(0, 0);
        ModelPlace model = new ModelPlace();
        model.setType(PlaceType.SETTLEMENT)
                .setId(2L)
                .setName("Lertavore")
                .setState(PlaceState.ALRIGHT)
                .setLocation( gf.createPoint(coordinate));
        model.getPlaceCharacteristics().add(PlaceCharacteristic.LOCAL_CAPITAL);
        model.getPlaceCharacteristics().add(PlaceCharacteristic.FORTIFIED);
        city.setConf(conf);
        city.setModel(model);
        MainEngine.getInstance().getPlaceRegistry().register(city);
        return city;
    }

    private SimulationPlace initializeEgerenna() {
        SimulationPlace city = new SimulationPlace();
        ConfigurationPlace conf = ConfigurationPlace.builder()
                .setBuiltYear(812)
                .setDestroyedYear(9999)
//                .setPreferedMaterial(Material.)
                .build();
        Coordinate coordinate = new Coordinate(0, 0);
        ModelPlace model = new ModelPlace();
        model.setType(PlaceType.SETTLEMENT)
                .setId(3L)
                .setName("Egerenna")
                .setState(PlaceState.ALRIGHT)
                .setLocation( gf.createPoint(coordinate));
        model.getPlaceCharacteristics().add(PlaceCharacteristic.LOCAL_CAPITAL);
        city.setConf(conf);
        city.setModel(model);
        MainEngine.getInstance().getPlaceRegistry().register(city);
        return city;
    }

    private SimulationPlace initializeVerdalon() {
        SimulationPlace city = new SimulationPlace();
        ConfigurationPlace conf = ConfigurationPlace.builder()
                .setBuiltYear(936)
                .setDestroyedYear(1193)
//                .setPreferedMaterial(Material.)
                .build();
        Coordinate coordinate = new Coordinate(0, 0);
        ModelPlace model = new ModelPlace();
        model.setType(PlaceType.SETTLEMENT)
                .setId(4L)
                .setName("Verdalon")
                .setState(PlaceState.ALRIGHT)
                .setLocation( gf.createPoint(coordinate));
        model.getPlaceCharacteristics().add(PlaceCharacteristic.TRADING);
        city.setConf(conf);
        city.setModel(model);
        MainEngine.getInstance().getPlaceRegistry().register(city);
        return city;
    }

    private SimulationPlace initializeAstaria() {
        SimulationPlace city = new SimulationPlace();
        ConfigurationPlace conf = ConfigurationPlace.builder()
                .setBuiltYear(265)
                .setDestroyedYear(9999)
//                .setPreferedMaterial(Material.)
                .build();
        Coordinate coordinate = new Coordinate(0, 0);
        ModelPlace model = new ModelPlace();
        model.setType(PlaceType.SETTLEMENT)
                .setId(5L)
                .setName("Astaria")
                .setState(PlaceState.ALRIGHT)
                .setLocation( gf.createPoint(coordinate));
        model.getPlaceCharacteristics().add(PlaceCharacteristic.LOCAL_CAPITAL);
        city.setConf(conf);
        city.setModel(model);
        MainEngine.getInstance().getPlaceRegistry().register(city);
        return city;
    }

    private SimulationPlace initializeErgondol() {
        SimulationPlace city = new SimulationPlace();
        ConfigurationPlace conf = ConfigurationPlace.builder()
                .setBuiltYear(894)
                .setDestroyedYear(1302)
//                .setPreferedMaterial(Material.)
                .build();
        Coordinate coordinate = new Coordinate(0, 0);
        ModelPlace model = new ModelPlace();
        model.setType(PlaceType.SETTLEMENT)
                .setId(6L)
                .setName("Ergondol")
                .setState(PlaceState.ALRIGHT)
                .setLocation( gf.createPoint(coordinate));
        model.getPlaceCharacteristics().add(PlaceCharacteristic.LOCAL_CAPITAL);
        city.setConf(conf);
        city.setModel(model);
        MainEngine.getInstance().getPlaceRegistry().register(city);
        return city;
    }

    private SimulationPlace initializeSartama() {
        SimulationPlace city = new SimulationPlace();
        ConfigurationPlace conf = ConfigurationPlace.builder()
                .setBuiltYear(974)
                .setDestroyedYear(9999)
//                .setPreferedMaterial(Material.)
                .build();
        Coordinate coordinate = new Coordinate(0, 0);
        ModelPlace model = new ModelPlace();
        model.setType(PlaceType.SETTLEMENT)
                .setId(7L)
                .setName("Sartama")
                .setState(PlaceState.ALRIGHT)
                .setLocation( gf.createPoint(coordinate));
        model.getPlaceCharacteristics().add(PlaceCharacteristic.LOCAL_CAPITAL);
        model.getPlaceCharacteristics().add(PlaceCharacteristic.TRADING);
        model.getPlaceCharacteristics().add(PlaceCharacteristic.SEA_PORT);
        model.getPlaceCharacteristics().add(PlaceCharacteristic.FORTIFIED);
        city.setConf(conf);
        city.setModel(model);
        MainEngine.getInstance().getPlaceRegistry().register(city);
        return city;
    }

    private SimulationPlace initializeWornimore() {
        SimulationPlace city = new SimulationPlace();
        ConfigurationPlace conf = ConfigurationPlace.builder()
                .setBuiltYear(1188)
                .setDestroyedYear(9999)
//                .setPreferedMaterial(Material.)
                .build();
        Coordinate coordinate = new Coordinate(0, 0);
        ModelPlace model = new ModelPlace();
        model.setType(PlaceType.SETTLEMENT)
                .setId(8L)
                .setName("Wornimore")
                .setState(PlaceState.ALRIGHT)
                .setLocation( gf.createPoint(coordinate));
        model.getPlaceCharacteristics().add(PlaceCharacteristic.LOCAL_CAPITAL);
        model.getPlaceCharacteristics().add(PlaceCharacteristic.FORTIFIED);
        model.getPlaceCharacteristics().add(PlaceCharacteristic.ENCHANTED);
        city.setConf(conf);
        city.setModel(model);
        MainEngine.getInstance().getPlaceRegistry().register(city);
        return city;
    }

    private SimulationPlace initializeKorsana() {
        SimulationPlace city = new SimulationPlace();
        ConfigurationPlace conf = ConfigurationPlace.builder()
                .setBuiltYear(1202)
                .setDestroyedYear(1451)
//                .setPreferedMaterial(Material.)
                .build();
        Coordinate coordinate = new Coordinate(0, 0);
        ModelPlace model = new ModelPlace();
        model.setType(PlaceType.SETTLEMENT)
                .setId(9L)
                .setName("Korsana")
                .setState(PlaceState.ALRIGHT)
                .setLocation( gf.createPoint(coordinate));
        model.getPlaceCharacteristics().add(PlaceCharacteristic.LOCAL_CAPITAL);
        model.getPlaceCharacteristics().add(PlaceCharacteristic.FORTIFIED);
        city.setConf(conf);
        city.setModel(model);
        MainEngine.getInstance().getPlaceRegistry().register(city);
        return city;
    }

    private SimulationPlace initializeZelderin() {
        SimulationPlace city = new SimulationPlace();
        ConfigurationPlace conf = ConfigurationPlace.builder()
                .setBuiltYear(1219)
                .setDestroyedYear(9999)
//                .setPreferedMaterial(Material.)
                .build();
        Coordinate coordinate = new Coordinate(0, 0);
        ModelPlace model = new ModelPlace();
        model.setType(PlaceType.SETTLEMENT)
                .setId(10L)
                .setName("Zelderin")
                .setState(PlaceState.ALRIGHT)
                .setLocation( gf.createPoint(coordinate));
        model.getPlaceCharacteristics().add(PlaceCharacteristic.LOCAL_CAPITAL);
        model.getPlaceCharacteristics().add(PlaceCharacteristic.SEA_PORT);
        model.getPlaceCharacteristics().add(PlaceCharacteristic.FORTIFIED);
        city.setConf(conf);
        city.setModel(model);
        MainEngine.getInstance().getPlaceRegistry().register(city);
        return city;
    }

    private SimulationPlace initializeCaravista() {
        SimulationPlace city = new SimulationPlace();
        ConfigurationPlace conf = ConfigurationPlace.builder()
                .setBuiltYear(1197)
                .setDestroyedYear(9999)
//                .setPreferedMaterial(Material.)
                .build();
        Coordinate coordinate = new Coordinate(0, 0);
        ModelPlace model = new ModelPlace();
        model.setType(PlaceType.SETTLEMENT)
                .setId(11L)
                .setName("Caravista")
                .setState(PlaceState.ALRIGHT)
                .setLocation( gf.createPoint(coordinate));
        model.getPlaceCharacteristics().add(PlaceCharacteristic.LOCAL_CAPITAL);
        model.getPlaceCharacteristics().add(PlaceCharacteristic.RIVER_PORT);
        model.getPlaceCharacteristics().add(PlaceCharacteristic.FORTIFIED);
        city.setConf(conf);
        city.setModel(model);
        MainEngine.getInstance().getPlaceRegistry().register(city);
        return city;
    }

    private SimulationPlace initializeUvarra() {
        SimulationPlace city = new SimulationPlace();
        ConfigurationPlace conf = ConfigurationPlace.builder()
                .setBuiltYear(578)
                .setDestroyedYear(9999)
//                .setPreferedMaterial(Material.)
                .build();
        Coordinate coordinate = new Coordinate(0, 0);
        ModelPlace model = new ModelPlace();
        model.setType(PlaceType.SETTLEMENT)
                .setId(12L)
                .setName("Uvarra")
                .setState(PlaceState.ALRIGHT)
                .setLocation( gf.createPoint(coordinate));
        model.getPlaceCharacteristics().add(PlaceCharacteristic.LOCAL_CAPITAL);
        model.getPlaceCharacteristics().add(PlaceCharacteristic.FORTIFIED);
        model.getPlaceCharacteristics().add(PlaceCharacteristic.ARTISAN);
        model.getPlaceCharacteristics().add(PlaceCharacteristic.MINING);
        city.setConf(conf);
        city.setModel(model);
        MainEngine.getInstance().getPlaceRegistry().register(city);
        return city;
    }

    private SimulationPlace initializeJirdenal() {
        SimulationPlace city = new SimulationPlace();
        ConfigurationPlace conf = ConfigurationPlace.builder()
                .setBuiltYear(1455)
                .setDestroyedYear(9999)
//                .setPreferedMaterial(Material.)
                .build();
        Coordinate coordinate = new Coordinate(0, 0);
        ModelPlace model = new ModelPlace();
        model.setType(PlaceType.SETTLEMENT)
                .setId(13L)
                .setName("Jirdenal")
                .setState(PlaceState.ALRIGHT)
                .setLocation( gf.createPoint(coordinate));
        model.getPlaceCharacteristics().add(PlaceCharacteristic.LOCAL_CAPITAL);
        model.getPlaceCharacteristics().add(PlaceCharacteristic.SEA_PORT);
        model.getPlaceCharacteristics().add(PlaceCharacteristic.TRADING);
        model.getPlaceCharacteristics().add(PlaceCharacteristic.FISHING);
        city.setConf(conf);
        city.setModel(model);
        MainEngine.getInstance().getPlaceRegistry().register(city);
        return city;
    }

    private SimulationPlace initializeXalivore() {
        SimulationPlace city = new SimulationPlace();
        ConfigurationPlace conf = ConfigurationPlace.builder()
                .setBuiltYear(1184)
                .setDestroyedYear(9999)
//                .setPreferedMaterial(Material.)
                .build();
        Coordinate coordinate = new Coordinate(0, 0);
        ModelPlace model = new ModelPlace();
        model.setType(PlaceType.SETTLEMENT)
                .setId(14L)
                .setName("Xalivore")
                .setState(PlaceState.ALRIGHT)
                .setLocation( gf.createPoint(coordinate));
        model.getPlaceCharacteristics().add(PlaceCharacteristic.LOCAL_CAPITAL);
        model.getPlaceCharacteristics().add(PlaceCharacteristic.ARTISAN);
        model.getPlaceCharacteristics().add(PlaceCharacteristic.FORTIFIED);
        city.setConf(conf);
        city.setModel(model);
        MainEngine.getInstance().getPlaceRegistry().register(city);
        return city;
    }

    private SimulationPlace initializePernagol() {
        SimulationPlace city = new SimulationPlace();
        ConfigurationPlace conf = ConfigurationPlace.builder()
                .setBuiltYear(1204)
                .setDestroyedYear(9999)
//                .setPreferedMaterial(Material.)
                .build();
        Coordinate coordinate = new Coordinate(0, 0);
        ModelPlace model = new ModelPlace();
        model.setType(PlaceType.SETTLEMENT)
                .setId(15L)
                .setName("Pernagol")
                .setState(PlaceState.ALRIGHT)
                .setLocation( gf.createPoint(coordinate));
        model.getPlaceCharacteristics().add(PlaceCharacteristic.LOCAL_CAPITAL);
        model.getPlaceCharacteristics().add(PlaceCharacteristic.FORTIFIED);
        model.getPlaceCharacteristics().add(PlaceCharacteristic.ARTISAN);
        city.setConf(conf);
        city.setModel(model);
        MainEngine.getInstance().getPlaceRegistry().register(city);
        return city;
    }

    private SimulationPlace initializeIsvellin() {
        SimulationPlace city = new SimulationPlace();
        ConfigurationPlace conf = ConfigurationPlace.builder()
                .setBuiltYear(1194)
                .setDestroyedYear(9999)
//                .setPreferedMaterial(Material.)
                .build();
        Coordinate coordinate = new Coordinate(0, 0);
        ModelPlace model = new ModelPlace();
        model.setType(PlaceType.SETTLEMENT)
                .setId(16L)
                .setName("Isvellin")
                .setState(PlaceState.ALRIGHT)
                .setLocation( gf.createPoint(coordinate));
        model.getPlaceCharacteristics().add(PlaceCharacteristic.TRADING);
        city.setConf(conf);
        city.setModel(model);
        MainEngine.getInstance().getPlaceRegistry().register(city);
        return city;
    }

    private SimulationPlace initializeRepenvore() {
        SimulationPlace city = new SimulationPlace();
        ConfigurationPlace conf = ConfigurationPlace.builder()
                .setBuiltYear(1186)
                .setDestroyedYear(1442)
//                .setPreferedMaterial(Material.)
                .build();
        Coordinate coordinate = new Coordinate(0, 0);
        ModelPlace model = new ModelPlace();
        model.setType(PlaceType.SETTLEMENT)
                .setId(17L)
                .setName("Repenvore")
                .setState(PlaceState.ALRIGHT)
                .setLocation( gf.createPoint(coordinate));
        city.setConf(conf);
        city.setModel(model);
        MainEngine.getInstance().getPlaceRegistry().register(city);
        return city;
    }

    private SimulationPlace initializeTantanor() {
        SimulationPlace city = new SimulationPlace();
        ConfigurationPlace conf = ConfigurationPlace.builder()
                .setBuiltYear(1595)
                .setDestroyedYear(9999)
//                .setPreferedMaterial(Material.)
                .build();
        Coordinate coordinate = new Coordinate(0, 0);
        ModelPlace model = new ModelPlace();
        model.setType(PlaceType.SETTLEMENT)
                .setId(18L)
                .setName("Tantanor")
                .setState(PlaceState.ALRIGHT)
                .setLocation( gf.createPoint(coordinate));
        model.getPlaceCharacteristics().add(PlaceCharacteristic.FORTIFIED);
        city.setConf(conf);
        city.setModel(model);
        MainEngine.getInstance().getPlaceRegistry().register(city);
        return city;
    }

    private SimulationPlace initializeDurnatel() {
        SimulationPlace city = new SimulationPlace();
        ConfigurationPlace conf = ConfigurationPlace.builder()
                .setBuiltYear(-365)
                .setDestroyedYear(9999)
//                .setPreferedMaterial(Material.)
                .build();
        Coordinate coordinate = new Coordinate(0, 0);
        ModelPlace model = new ModelPlace();
        model.setType(PlaceType.SETTLEMENT)
                .setId(19L)
                .setName("Durnatel")
                .setState(PlaceState.ALRIGHT)
                .setLocation( gf.createPoint(coordinate));
        model.getPlaceCharacteristics().add(PlaceCharacteristic.LOCAL_CAPITAL);
        city.setConf(conf);
        city.setModel(model);
        MainEngine.getInstance().getPlaceRegistry().register(city);
        return city;
    }

    private SimulationPlace initializeVizarna() {
        SimulationPlace city = new SimulationPlace();
        ConfigurationPlace conf = ConfigurationPlace.builder()
                .setBuiltYear(1273)
                .setDestroyedYear(9999)
//                .setPreferedMaterial(Material.)
                .build();
        Coordinate coordinate = new Coordinate(0, 0);
        ModelPlace model = new ModelPlace();
        model.setType(PlaceType.SETTLEMENT)
                .setId(20L)
                .setName("Vizarna")
                .setState(PlaceState.ALRIGHT)
                .setLocation( gf.createPoint(coordinate));
//        model.getPlaceCharacteristics().add(PlaceCharacteristic.FORTIFIED);
        city.setConf(conf);
        city.setModel(model);
        MainEngine.getInstance().getPlaceRegistry().register(city);
        return city;
    }

    private SimulationPlace initializeHasirel() {
        SimulationPlace city = new SimulationPlace();
        ConfigurationPlace conf = ConfigurationPlace.builder()
                .setBuiltYear(1199)
                .setDestroyedYear(9999)
//                .setPreferedMaterial(Material.)
                .build();
        Coordinate coordinate = new Coordinate(0, 0);
        ModelPlace model = new ModelPlace();
        model.setType(PlaceType.SETTLEMENT)
                .setId(21L)
                .setName("Hasirel")
                .setState(PlaceState.ALRIGHT)
                .setLocation( gf.createPoint(coordinate));
        model.getPlaceCharacteristics().add(PlaceCharacteristic.FORTIFIED);
        city.setConf(conf);
        city.setModel(model);
        MainEngine.getInstance().getPlaceRegistry().register(city);
        return city;
    }

    private SimulationPlace initializeNaderia() {
        SimulationPlace city = new SimulationPlace();
        ConfigurationPlace conf = ConfigurationPlace.builder()
                .setBuiltYear(1206)
                .setDestroyedYear(1743)
//                .setPreferedMaterial(Material.)
                .build();
        Coordinate coordinate = new Coordinate(0, 0);
        ModelPlace model = new ModelPlace();
        model.setType(PlaceType.SETTLEMENT)
                .setId(22L)
                .setName("Naderia")
                .setState(PlaceState.ALRIGHT)
                .setLocation( gf.createPoint(coordinate));
        model.getPlaceCharacteristics().add(PlaceCharacteristic.FORTIFIED);
        city.setConf(conf);
        city.setModel(model);
        MainEngine.getInstance().getPlaceRegistry().register(city);
        return city;
    }

    private SimulationPlace initializeQuvelmore() {
        SimulationPlace city = new SimulationPlace();
        ConfigurationPlace conf = ConfigurationPlace.builder()
                .setBuiltYear(1221)
                .setDestroyedYear(1640)
//                .setPreferedMaterial(Material.)
                .build();
        Coordinate coordinate = new Coordinate(0, 0);
        ModelPlace model = new ModelPlace();
        model.setType(PlaceType.SETTLEMENT)
                .setId(23L)
                .setName("Quvelmore")
                .setState(PlaceState.ALRIGHT)
                .setLocation( gf.createPoint(coordinate));
        model.getPlaceCharacteristics().add(PlaceCharacteristic.FORTIFIED);
        city.setConf(conf);
        city.setModel(model);
        MainEngine.getInstance().getPlaceRegistry().register(city);
        return city;
    }

    private SimulationPlace initializeNamedCity(String name, Long id, Coordinate coordinate) {
        SimulationPlace city = new SimulationPlace();
        ConfigurationPlace conf = ConfigurationPlace.builder()
                .setBuiltYear(determineBuiltYearAfter(1194))
                .setDestroyedYear(null)
                .build();
        ModelPlace model = new ModelPlace();
        model.setType(PlaceType.SETTLEMENT)
                .setId(id)
                .setName(name)
                .setState(PlaceState.ALRIGHT)
                .setLocation( gf.createPoint(coordinate));
        city.setConf(conf);
        city.setModel(model);
        MainEngine.getInstance().getPlaceRegistry().register(city);
        return city;
    }

    private SimulationPlace initializeResidel() {
        SimulationPlace city = new SimulationPlace();
        ConfigurationPlace conf = ConfigurationPlace.builder()
                .setBuiltYear(determineBuiltYearAfter(1194))
                .setDestroyedYear(null)
//                .setPreferedMaterial(Material.)
                .build();
        Coordinate coordinate = new Coordinate(0, 0);
        ModelPlace model = new ModelPlace();
        model.setType(PlaceType.SETTLEMENT)
                .setId(24L)
                .setName("Residel")
                .setState(PlaceState.ALRIGHT)
                .setLocation( gf.createPoint(coordinate));
        model.getPlaceCharacteristics().add(PlaceCharacteristic.FORTIFIED);
        city.setConf(conf);
        city.setModel(model);
        MainEngine.getInstance().getPlaceRegistry().register(city);
        return city;
    }

    private SimulationPlace initializeQurena() {
        SimulationPlace city = new SimulationPlace();
        ConfigurationPlace conf = ConfigurationPlace.builder()
                .setBuiltYear(determineBuiltYearAfter(1194))
                .setDestroyedYear(null)
//                .setPreferedMaterial(Material.)
                .build();
        Coordinate coordinate = new Coordinate(0, 0);
        ModelPlace model = new ModelPlace();
        model.setType(PlaceType.SETTLEMENT)
                .setId(25L)
                .setName("Qurena")
                .setState(PlaceState.ALRIGHT)
                .setLocation( gf.createPoint(coordinate));
        model.getPlaceCharacteristics().add(PlaceCharacteristic.FORTIFIED);
        city.setConf(conf);
        city.setModel(model);
        MainEngine.getInstance().getPlaceRegistry().register(city);
        return city;
    }

    private SimulationPlace initializeSardena() {
        SimulationPlace city = new SimulationPlace();
        ConfigurationPlace conf = ConfigurationPlace.builder()
                .setBuiltYear(determineBuiltYearAfter(1194))
                .setDestroyedYear(null)
//                .setPreferedMaterial(Material.)
                .build();
        Coordinate coordinate = new Coordinate(0, 0);
        ModelPlace model = new ModelPlace();
        model.setType(PlaceType.SETTLEMENT)
                .setId(26L)
                .setName("Sardena")
                .setState(PlaceState.ALRIGHT)
                .setLocation( gf.createPoint(coordinate));
        model.getPlaceCharacteristics().add(PlaceCharacteristic.FORTIFIED);
        city.setConf(conf);
        city.setModel(model);
        MainEngine.getInstance().getPlaceRegistry().register(city);
        return city;
    }

    private SimulationPlace initializeMirnadel() {
        SimulationPlace city = new SimulationPlace();
        ConfigurationPlace conf = ConfigurationPlace.builder()
                .setBuiltYear(determineBuiltYearAfter(1194))
                .setDestroyedYear(null)
//                .setPreferedMaterial(Material.)
                .build();
        Coordinate coordinate = new Coordinate(0, 0);
        ModelPlace model = new ModelPlace();
        model.setType(PlaceType.SETTLEMENT)
                .setId(27L)
                .setName("Mirnadel")
                .setState(PlaceState.ALRIGHT)
                .setLocation( gf.createPoint(coordinate));
        model.getPlaceCharacteristics().add(PlaceCharacteristic.FORTIFIED);
        city.setConf(conf);
        city.setModel(model);
        MainEngine.getInstance().getPlaceRegistry().register(city);
        return city;
    }

    private SimulationPlace initializeNadarvel() {
        SimulationPlace city = new SimulationPlace();
        ConfigurationPlace conf = ConfigurationPlace.builder()
                .setBuiltYear(determineBuiltYearAfter(1194))
                .setDestroyedYear(null)
//                .setPreferedMaterial(Material.)
                .build();
        Coordinate coordinate = new Coordinate(0, 0);
        ModelPlace model = new ModelPlace();
        model.setType(PlaceType.SETTLEMENT)
                .setId(28L)
                .setName("Nadarvel")
                .setState(PlaceState.ALRIGHT)
                .setLocation( gf.createPoint(coordinate));
        model.getPlaceCharacteristics().add(PlaceCharacteristic.FORTIFIED);
        city.setConf(conf);
        city.setModel(model);
        MainEngine.getInstance().getPlaceRegistry().register(city);
        return city;
    }

    private SimulationPlace initializeVangodar() {
        SimulationPlace city = new SimulationPlace();
        ConfigurationPlace conf = ConfigurationPlace.builder()
                .setBuiltYear(determineBuiltYearAfter(1194))
                .setDestroyedYear(null)
//                .setPreferedMaterial(Material.)
                .build();
        Coordinate coordinate = new Coordinate(0, 0);
        ModelPlace model = new ModelPlace();
        model.setType(PlaceType.SETTLEMENT)
                .setId(29L)
                .setName("Vangodar")
                .setState(PlaceState.ALRIGHT)
                .setLocation( gf.createPoint(coordinate));
        model.getPlaceCharacteristics().add(PlaceCharacteristic.FORTIFIED);
        city.setConf(conf);
        city.setModel(model);
        MainEngine.getInstance().getPlaceRegistry().register(city);
        return city;
    }

    private SimulationPlace initializeLutnaran() {
        SimulationPlace city = new SimulationPlace();
        ConfigurationPlace conf = ConfigurationPlace.builder()
                .setBuiltYear(determineBuiltYearAfter(1194))
                .setDestroyedYear(null)
//                .setPreferedMaterial(Material.)
                .build();
        Coordinate coordinate = new Coordinate(0, 0);
        ModelPlace model = new ModelPlace();
        model.setType(PlaceType.SETTLEMENT)
                .setId(30L)
                .setName("Lutnaran")
                .setState(PlaceState.ALRIGHT)
                .setLocation( gf.createPoint(coordinate));
        model.getPlaceCharacteristics().add(PlaceCharacteristic.FORTIFIED);
        city.setConf(conf);
        city.setModel(model);
        MainEngine.getInstance().getPlaceRegistry().register(city);
        return city;
    }

    private SimulationPlace initializeFelarna() {
        SimulationPlace city = new SimulationPlace();
        ConfigurationPlace conf = ConfigurationPlace.builder()
                .setBuiltYear(determineBuiltYearAfter(1194))
                .setDestroyedYear(null)
//                .setPreferedMaterial(Material.)
                .build();
        Coordinate coordinate = new Coordinate(0, 0);
        ModelPlace model = new ModelPlace();
        model.setType(PlaceType.SETTLEMENT)
                .setId(31L)
                .setName("Felarna")
                .setState(PlaceState.ALRIGHT)
                .setLocation( gf.createPoint(coordinate));
        model.getPlaceCharacteristics().add(PlaceCharacteristic.FORTIFIED);
        city.setConf(conf);
        city.setModel(model);
        MainEngine.getInstance().getPlaceRegistry().register(city);
        return city;
    }

    private SimulationPlace initializeTirnugal() {
        SimulationPlace city = new SimulationPlace();
        ConfigurationPlace conf = ConfigurationPlace.builder()
                .setBuiltYear(determineBuiltYearAfter(1194))
                .setDestroyedYear(null)
//                .setPreferedMaterial(Material.)
                .build();
        Coordinate coordinate = new Coordinate(0, 0);
        ModelPlace model = new ModelPlace();
        model.setType(PlaceType.SETTLEMENT)
                .setId(32L)
                .setName("Tirnugal")
                .setState(PlaceState.ALRIGHT)
                .setLocation( gf.createPoint(coordinate));
        model.getPlaceCharacteristics().add(PlaceCharacteristic.FORTIFIED);
        city.setConf(conf);
        city.setModel(model);
        MainEngine.getInstance().getPlaceRegistry().register(city);
        return city;
    }

    private SimulationPlace initializeNonusta() {
        SimulationPlace city = new SimulationPlace();
        ConfigurationPlace conf = ConfigurationPlace.builder()
                .setBuiltYear(determineBuiltYearAfter(1194))
                .setDestroyedYear(null)
//                .setPreferedMaterial(Material.)
                .build();
        Coordinate coordinate = new Coordinate(0, 0);
        ModelPlace model = new ModelPlace();
        model.setType(PlaceType.SETTLEMENT)
                .setId(33L)
                .setName("Nonusta")
                .setState(PlaceState.ALRIGHT)
                .setLocation( gf.createPoint(coordinate));
        model.getPlaceCharacteristics().add(PlaceCharacteristic.FORTIFIED);
        city.setConf(conf);
        city.setModel(model);
        MainEngine.getInstance().getPlaceRegistry().register(city);
        return city;
    }

    private SimulationPlace initializeAnverna() {
        SimulationPlace city = new SimulationPlace();
        ConfigurationPlace conf = ConfigurationPlace.builder()
                .setBuiltYear(determineBuiltYearAfter(1194))
                .setDestroyedYear(null)
//                .setPreferedMaterial(Material.)
                .build();
        Coordinate coordinate = new Coordinate(0, 0);
        ModelPlace model = new ModelPlace();
        model.setType(PlaceType.SETTLEMENT)
                .setId(34L)
                .setName("Anverna")
                .setState(PlaceState.ALRIGHT)
                .setLocation( gf.createPoint(coordinate));
        model.getPlaceCharacteristics().add(PlaceCharacteristic.FORTIFIED);
        city.setConf(conf);
        city.setModel(model);
        MainEngine.getInstance().getPlaceRegistry().register(city);
        return city;
    }

    private SimulationPlace initializeGolveran() {
        SimulationPlace city = new SimulationPlace();
        ConfigurationPlace conf = ConfigurationPlace.builder()
                .setBuiltYear(determineBuiltYearAfter(1194))
                .setDestroyedYear(null)
//                .setPreferedMaterial(Material.)
                .build();
        Coordinate coordinate = new Coordinate(0, 0);
        ModelPlace model = new ModelPlace();
        model.setType(PlaceType.SETTLEMENT)
                .setId(35L)
                .setName("Golveran")
                .setState(PlaceState.ALRIGHT)
                .setLocation( gf.createPoint(coordinate));
        model.getPlaceCharacteristics().add(PlaceCharacteristic.FORTIFIED);
        city.setConf(conf);
        city.setModel(model);
        MainEngine.getInstance().getPlaceRegistry().register(city);
        return city;
    }

    private SimulationPlace initializeOstivore() {
        SimulationPlace city = new SimulationPlace();
        ConfigurationPlace conf = ConfigurationPlace.builder()
                .setBuiltYear(determineBuiltYearAfter(1195))
                .setDestroyedYear(null)
//                .setPreferedMaterial(Material.)
                .build();
        Coordinate coordinate = new Coordinate(0, 0);
        ModelPlace model = new ModelPlace();
        model.setType(PlaceType.SETTLEMENT)
                .setId(36L)
                .setName("Ostivore")
                .setState(PlaceState.ALRIGHT)
                .setLocation( gf.createPoint(coordinate));
        model.getPlaceCharacteristics().add(PlaceCharacteristic.FORTIFIED);
        city.setConf(conf);
        city.setModel(model);
        MainEngine.getInstance().getPlaceRegistry().register(city);
        return city;
    }

    private SimulationPlace initializeBellenau() {
        SimulationPlace city = new SimulationPlace();
        ConfigurationPlace conf = ConfigurationPlace.builder()
                .setBuiltYear(determineBuiltYearAfter(1195))
                .setDestroyedYear(9999)
//                .setPreferedMaterial(Material.)
                .build();
        Coordinate coordinate = new Coordinate(0, 0);
        ModelPlace model = new ModelPlace();
        model.setType(PlaceType.SETTLEMENT)
                .setId(37L)
                .setName("Bellenau")
                .setState(PlaceState.ALRIGHT)
                .setLocation( gf.createPoint(coordinate));
        model.getPlaceCharacteristics().add(PlaceCharacteristic.FORTIFIED);
        city.setConf(conf);
        city.setModel(model);
        MainEngine.getInstance().getPlaceRegistry().register(city);
        return city;
    }

    private Integer determineBuiltYearAfter(int year) {
        double general = ThreadLocalRandom.current().nextDouble();
        int base = year;
        if (general >= 0.4 && general < 0.7) {
            base += 100;
        } else if (general < 0.9) {
            base += 200;
        } else if (general < 0.95) {
            base += 300;
        } else {
            base += 400;
        }
        return base + ThreadLocalRandom.current().nextInt(100);
    }
}
