package priv.mfurmane.szlachtownica.model.initializers;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.springframework.stereotype.Component;
import priv.mfurmane.szlachtownica.engine.MainEngine;
import priv.mfurmane.szlachtownica.engine.naming.ernizjum.ErnizjumPhonotactic;
import priv.mfurmane.szlachtownica.engine.naming.model.*;
import priv.mfurmane.szlachtownica.engine.naming.nereneth.NerenethPhonotactic;
import priv.mfurmane.szlachtownica.model.VillageNameGenerator;
import priv.mfurmane.szlachtownica.model.config.ConfigurationPlace;
import priv.mfurmane.szlachtownica.model.main.ModelPlace;
import priv.mfurmane.szlachtownica.model.simulation.SimulationPlace;
import priv.mfurmane.szlachtownica.model.simulation.terrain.PlaceState;
import priv.mfurmane.szlachtownica.model.simulation.terrain.PlaceType;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class PlaceInitializer {
    public static final Map<Integer, Double> citySyllablesCountMap = Map.of(1, 2.5, 2, 7.0, 3, 0.5);
    public static final Map<Integer, Double> nerenethSyllablesCountMap = Map.of(2, 2.5, 3, 7.0, 4, 0.5);
    private final PlacesConfiguration placesConfiguration = new PlacesConfiguration(this);
    private MainEngine engine;
    public static final GeometryFactory gf = new GeometryFactory();
    Set<String> usedNames = new HashSet<>();

    private final Phonotactic ernizjumPhonotactic = new ErnizjumPhonotactic();
    private final Phonotactic nerenethPhonotactic = new NerenethPhonotactic();

    private final ObjectMapper mapper = new ObjectMapper();

    public PlaceInitializer() {
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    }

    public void setEngine(MainEngine mainEngine) {
        this.engine = mainEngine;
    }

    public void initializePlaces() {
        SimulationPlace gilgamore = placesConfiguration.initializeGilgamore();
        SimulationPlace lertavore = placesConfiguration.initializeLertavore();
        SimulationPlace egerenna = placesConfiguration.initializeEgerenna();
        SimulationPlace verdalon = placesConfiguration.initializeVerdalon();
        SimulationPlace astaria = placesConfiguration.initializeAstaria();
        SimulationPlace ergondol = placesConfiguration.initializeErgondol();
        SimulationPlace sartama = placesConfiguration.initializeSartama();
        SimulationPlace wornimore = placesConfiguration.initializeWornimore();
        SimulationPlace korsana = placesConfiguration.initializeKorsana();
        SimulationPlace zelderin = placesConfiguration.initializeZelderin();
        SimulationPlace caravista = placesConfiguration.initializeCaravista();
        SimulationPlace uvarra = placesConfiguration.initializeUvarra();
        SimulationPlace jirdenal = placesConfiguration.initializeJirdenal();
        SimulationPlace xalivore = placesConfiguration.initializeXalivore();
        SimulationPlace pernagol = placesConfiguration.initializePernagol();
        SimulationPlace isvellin = placesConfiguration.initializeIsvellin();
        SimulationPlace tantanor = placesConfiguration.initializeTantanor();
        SimulationPlace durnatel = placesConfiguration.initializeDurnatel();
        SimulationPlace repenvore = placesConfiguration.initializeRepenvore();
        SimulationPlace vizarna = placesConfiguration.initializeVizarna();
        SimulationPlace hasirel = placesConfiguration.initializeHasirel();
        SimulationPlace naderia = placesConfiguration.initializeNaderia();
        SimulationPlace quvelmore = placesConfiguration.initializeQuvelmore();
        SimulationPlace bellenau = placesConfiguration.initializeBellenau();

        SimulationPlace residel = placesConfiguration.initializeResidel();
        SimulationPlace qurena = placesConfiguration.initializeQurena();
        SimulationPlace sardena = placesConfiguration.initializeSardena();
        SimulationPlace mirnadel = placesConfiguration.initializeMirnadel();
        SimulationPlace nadarvel = placesConfiguration.initializeNadarvel();
        SimulationPlace vangodar = placesConfiguration.initializeVangodar();
        SimulationPlace lutnaran = placesConfiguration.initializeLutnaran();
        SimulationPlace felarna = placesConfiguration.initializeFelarna();
        SimulationPlace tirnugal = placesConfiguration.initializeTirnugal();
        SimulationPlace nonusta = placesConfiguration.initializeNonusta();
        SimulationPlace anverna = placesConfiguration.initializeAnverna();
        SimulationPlace golveran = placesConfiguration.initializeGolveran();
        SimulationPlace ostivore = placesConfiguration.initializeOstivore();

        List<SimulationPlace> cities = new ArrayList<>(List.of(gilgamore, lertavore, egerenna, verdalon, astaria, ergondol, sartama, wornimore, korsana, zelderin, caravista, uvarra, jirdenal, xalivore, pernagol, isvellin,
                tantanor, durnatel, repenvore, vizarna, hasirel, naderia, quvelmore, bellenau, residel, qurena, sardena, mirnadel, nadarvel, vangodar, lutnaran, felarna, tirnugal, nonusta, anverna, golveran, ostivore));

        cities.forEach(city -> {
            int before = usedNames.size();
            usedNames.add(city.getModel().getName());
            System.out.println("Before " + city.getModel().getName() + " was " + before + ", after was " + usedNames.size());
        });

    }

    public SimulationPlace initializeUnnamedSettlement(Coordinate coordinate, boolean town, int builtYear) {
        return initializeNamedSettlement(determineName(town), coordinate, builtYear);
    }

    public SimulationPlace initializeNamedSettlement(String name, Coordinate coordinate, int builtYear) {
        SimulationPlace settlement = new SimulationPlace();
        ConfigurationPlace conf = ConfigurationPlace.builder()
                .setBuiltYear(builtYear)
                .setDestroyedYear(null)
                .build();
        ModelPlace model = new ModelPlace();
        model.setType(PlaceType.SETTLEMENT)
                .setId(null)
                .setName(name)
                .setState(PlaceState.ALRIGHT)
                .setLocation( gf.createPoint(coordinate));
        return PlacesConfiguration.mergeAndRegister(settlement, conf, model);
    }

    private String determineName(boolean town) {
        if (town) {
            return generateTownName();
        }
        return generateVillageName();
    }

    private String generateVillageName() {

//        for (int i = 0; i < 100; i++) {
//            String generated = nerenethPhonotactic.generateCapitalizedWord(
//                    WordType.CITY,
////                    citySyllablesCountMap
//                    nerenethSyllablesCountMap
//            );
//            if (usedNames.add(generated)) {
//                return generated;
//            }
//        }

        return VillageNameGenerator.generate();
    }

    public static void main(String[] args) {
        PlaceInitializer initializer = new PlaceInitializer();

        for (int i = 0; i < 500; i++) {
//            System.out.println(ElvenNameGenerator.getMaleName() + " " + ElvenNameGenerator.getInnername(CharacterTrait.getRandom()) + " " + ElvenNameGenerator.getSurname());
//            System.out.println(ElvenNameGenerator.getFemaleName() + " " + ElvenNameGenerator.getInnername(CharacterTrait.getRandom()) + " " + ElvenNameGenerator.getSurname());
//            System.out.println(DwarvenNameGenerator.getMaleName() + " " + DwarvenNameGenerator.getMaleFather(CharacterTrait.getRandom()) + " " + DwarvenNameGenerator.getSurname());
//            System.out.println(DwarvenNameGenerator.getFemaleName() + " " + DwarvenNameGenerator.getFemaleFather(CharacterTrait.getRandom()) + " " + DwarvenNameGenerator.getSurname());

            System.out.println(initializer.determineName(false));
        }
    }

    private String generateTownName() {
        for (int i = 0; i < 100; i++) {
            String generated = ernizjumPhonotactic.generateCapitalizedWord(
                    WordType.CITY,
                    citySyllablesCountMap
            );
            if (usedNames.add(generated)) {
                return generated;
            }
        }
        throw new IllegalStateException("Cannot generate unique town name");
    }
}
