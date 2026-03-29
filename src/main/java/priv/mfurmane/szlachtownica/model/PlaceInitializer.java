package priv.mfurmane.szlachtownica.model;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Component;
import priv.mfurmane.szlachtownica.engine.MainEngine;
import priv.mfurmane.szlachtownica.engine.registry.PlaceRegistry;
import priv.mfurmane.szlachtownica.model.config.ConfigurationPlace;
import priv.mfurmane.szlachtownica.model.main.ModelPlace;
import priv.mfurmane.szlachtownica.model.simulation.SimulationPlace;
import priv.mfurmane.szlachtownica.model.simulation.SimulationProvince;
import priv.mfurmane.szlachtownica.model.simulation.terrain.Material;
import priv.mfurmane.szlachtownica.model.simulation.terrain.PlaceType;

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
        SimulationPlace gilgamore = initializeGilgamore(1086, 9999);
        SimulationPlace lertavore = initializeLertavore(1195, 9999);
        SimulationPlace egerenna = initializeEgerenna(812, 9999);
        SimulationPlace verdalon = initializeVerdalon(936, 1193);
        SimulationPlace astaria = initializeAstaria(265, 9999);
        SimulationPlace ergondol = initializeErgondol(894, 1302);
        SimulationPlace sartama = initializeSartama(974, 9999);
        SimulationPlace wornimore = initializeWornimore(1188, 9999);
        SimulationPlace korsana = initializeKorsana(1202, 1451);
        SimulationPlace zelderin = initializeZelderin(1219, 9999);
        SimulationPlace caravista = initializeCaravista(1197, 9999);
        SimulationPlace uvarra = initializeUvarra(578, 9999);
        SimulationPlace jirdenal = initializeJirdenal(1455, 9999);
        SimulationPlace xalivore = initializeXalivore(1184, 9999);
        SimulationPlace pernagol = initializePernagol(1204, 9999);
        SimulationPlace isvellin = initializeIsvellin(1194, 9999);
        SimulationPlace repenvore = initializeRepenvore(1186, 1442);
        SimulationPlace tantanor = initializeTantanor(1595, 9999);
        SimulationPlace durnatel = initializeDurnatel(9999365, 9999);
        SimulationPlace vizarna = initializeVizarna(1273, 9999);
        SimulationPlace hasirel = initializeHasirel(1199, 9999);
        SimulationPlace naderia = initializeNaderia(1206, 1743);
        SimulationPlace quvelmore = initializeQuvelmore(1221, 1640);
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

    }

    private SimulationPlace initializeGilgamore(Integer builtYear, Integer destroyedYear) {
        SimulationPlace city = new SimulationPlace();
        ConfigurationPlace conf = ConfigurationPlace.builder()
//                .setPreferedMaterial(Material.)
                .build();
        Coordinate coordinate = new Coordinate(76.93604806408545, -43.32118181818182);
        ModelPlace model = new ModelPlace();
        model.setType(PlaceType.SETTLEMENT)
                .setId(1L)
                .setName("Gilgamore")
                .setLocation( gf.createPoint(coordinate));
        city.setConf(conf);
        city.setModel(model);
        MainEngine.getInstance().getPlaceRegistry().register(city);
        return city;
    }

    private SimulationPlace initializeLertavore(Integer builtYear, Integer destroyedYear) {
        SimulationPlace city = new SimulationPlace();
        return city;
    }

    private SimulationPlace initializeEgerenna(Integer builtYear, Integer destroyedYear) {
        SimulationPlace city = new SimulationPlace();
        return city;
    }

    private SimulationPlace initializeVerdalon(Integer builtYear, Integer destroyedYear) {
        SimulationPlace city = new SimulationPlace();
        return city;
    }

    private SimulationPlace initializeAstaria(Integer builtYear, Integer destroyedYear) {
        SimulationPlace city = new SimulationPlace();
        return city;
    }

    private SimulationPlace initializeErgondol(Integer builtYear, Integer destroyedYear) {
        SimulationPlace city = new SimulationPlace();
        return city;
    }

    private SimulationPlace initializeSartama(Integer builtYear, Integer destroyedYear) {
        SimulationPlace city = new SimulationPlace();
        return city;
    }

    private SimulationPlace initializeWornimore(Integer builtYear, Integer destroyedYear) {
        SimulationPlace city = new SimulationPlace();
        return city;
    }

    private SimulationPlace initializeKorsana(Integer builtYear, Integer destroyedYear) {
        SimulationPlace city = new SimulationPlace();
        return city;
    }

    private SimulationPlace initializeZelderin(Integer builtYear, Integer destroyedYear) {
        SimulationPlace city = new SimulationPlace();
        return city;
    }

    private SimulationPlace initializeCaravista(Integer builtYear, Integer destroyedYear) {
        SimulationPlace city = new SimulationPlace();
        return city;
    }

    private SimulationPlace initializeUvarra(Integer builtYear, Integer destroyedYear) {
        SimulationPlace city = new SimulationPlace();
        return city;
    }

    private SimulationPlace initializeJirdenal(Integer builtYear, Integer destroyedYear) {
        SimulationPlace city = new SimulationPlace();
        return city;
    }

    private SimulationPlace initializeXalivore(Integer builtYear, Integer destroyedYear) {
        SimulationPlace city = new SimulationPlace();
        return city;
    }

    private SimulationPlace initializePernagol(Integer builtYear, Integer destroyedYear) {
        SimulationPlace city = new SimulationPlace();
        return city;
    }

    private SimulationPlace initializeIsvellin(Integer builtYear, Integer destroyedYear) {
        SimulationPlace city = new SimulationPlace();
        return city;
    }

    private SimulationPlace initializeRepenvore(Integer builtYear, Integer destroyedYear) {
        SimulationPlace city = new SimulationPlace();
        return city;
    }

    private SimulationPlace initializeTantanor(Integer builtYear, Integer destroyedYear) {
        SimulationPlace city = new SimulationPlace();
        return city;
    }

    private SimulationPlace initializeDurnatel(Integer builtYear, Integer destroyedYear) {
        SimulationPlace city = new SimulationPlace();
        return city;
    }

    private SimulationPlace initializeVizarna(Integer builtYear, Integer destroyedYear) {
        SimulationPlace city = new SimulationPlace();
        return city;
    }

    private SimulationPlace initializeHasirel(Integer builtYear, Integer destroyedYear) {
        SimulationPlace city = new SimulationPlace();
        return city;
    }

    private SimulationPlace initializeNaderia(Integer builtYear, Integer destroyedYear) {
        SimulationPlace city = new SimulationPlace();
        return city;
    }

    private SimulationPlace initializeQuvelmore(Integer builtYear, Integer destroyedYear) {
        SimulationPlace city = new SimulationPlace();
        return city;
    }

    private SimulationPlace initializeResidel() {
        SimulationPlace city = new SimulationPlace();
        return city;
    }

    private SimulationPlace initializeQurena() {
        SimulationPlace city = new SimulationPlace();
        return city;
    }

    private SimulationPlace initializeSardena() {
        SimulationPlace city = new SimulationPlace();
        return city;
    }

    private SimulationPlace initializeMirnadel() {
        SimulationPlace city = new SimulationPlace();
        return city;
    }

    private SimulationPlace initializeNadarvel() {
        SimulationPlace city = new SimulationPlace();
        return city;
    }

    private SimulationPlace initializeVangodar() {
        SimulationPlace city = new SimulationPlace();
        return city;
    }

    private SimulationPlace initializeLutnaran() {
        SimulationPlace city = new SimulationPlace();
        return city;
    }

    private SimulationPlace initializeFelarna() {
        SimulationPlace city = new SimulationPlace();
        return city;
    }

    private SimulationPlace initializeTirnugal() {
        SimulationPlace city = new SimulationPlace();
        return city;
    }

    private SimulationPlace initializeNonusta() {
        SimulationPlace city = new SimulationPlace();
        return city;
    }

    private SimulationPlace initializeAnverna() {
        SimulationPlace city = new SimulationPlace();
        return city;
    }

    private SimulationPlace initializeGolveran() {
        SimulationPlace city = new SimulationPlace();
        return city;
    }

    private SimulationPlace initializeOstivore() {
        SimulationPlace city = new SimulationPlace();
        return city;
    }
}
