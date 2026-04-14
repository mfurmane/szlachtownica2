package priv.mfurmane.szlachtownica.engine;

import jakarta.annotation.PostConstruct;
import org.locationtech.jts.geom.Polygon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import priv.mfurmane.szlachtownica.engine.events.EventFactory;
import priv.mfurmane.szlachtownica.engine.registry.*;
import priv.mfurmane.szlachtownica.model.*;
import priv.mfurmane.szlachtownica.model.main.ModelLake;
import priv.mfurmane.szlachtownica.model.main.ModelMountains;
import priv.mfurmane.szlachtownica.model.main.ModelRiver;
import priv.mfurmane.szlachtownica.model.main.ModelSeaPart;
import priv.mfurmane.szlachtownica.model.naming.NamingProvider;
import priv.mfurmane.szlachtownica.model.naming.PlaceNameProvider;
import priv.mfurmane.szlachtownica.model.simulation.SimulationPerson;
import priv.mfurmane.szlachtownica.model.simulation.goal.GoalEngine;
import priv.mfurmane.szlachtownica.model.simulation.terrain.ProductionType;
import priv.mfurmane.szlachtownica.model.simulation.terrain.TerrainResource;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Component
public class MainEngine {
    private static MainEngine instance;

    public MainEngine() {
        instance = this;
    }

    public static MainEngine getInstance() {
        return instance;
    }

    @Autowired
    private EventManager eventManager;
    @Autowired
    private EventFactory eventFactory;
    @Autowired
    private PersonFactory personFactory;
    @Autowired
    private ProvinceFactory provinceFactory;
    @Autowired
    private PlaceFactory placeFactory;
//    @Autowired
//    private FamilyFactory familyFactory;
    @Autowired
    private PredefinedDataFiller dataFiller;
    @Autowired
    private PersonRegistry personRegistry;
    @Autowired
    private FamilyRegistry familyRegistry;
    @Autowired
    private PlaceRegistry placeRegistry;
    @Autowired
    private RiverRegistry riverRegistry;
    @Autowired
    private LakeRegistry lakeRegistry;
    @Autowired
    private SeaPartRegistry seaPartRegistry;
    @Autowired
    private MountainsRegistry mountainsRegistry;
    @Autowired
    private RegionRegistry regionRegistry;
    @Autowired
    private SubProvinceRegistry subProvinceRegistry;
    @Autowired
    private ProvinceRegistry provinceRegistry;
    @Autowired
    private ProvinceInitializer provinceInitializer;
    @Autowired
    private PlaceInitializer cityInitializer;
    @Autowired
    private NatureInitializer natureInitializer;
    @Autowired
    private GoalEngine goalEngine;
    @Autowired
    private NamingProvider namingProvider;
    @Autowired
    private PlaceNameProvider placeNameProvider;

    @PostConstruct
    public void inject() {
        eventManager.setEngine(this);
        eventFactory.setEngine(this);
        personFactory.setEngine(this);
//        familyFactory.setEngine(this);
        dataFiller.setEngine(this);
        goalEngine.setEngine(this);
        namingProvider.setEngine(this);
        provinceFactory.setEngine(this);
        placeFactory.setEngine(this);
        placeNameProvider.setEngine(this);
        personRegistry.setEngine(this);
        familyRegistry.setEngine(this);
        placeRegistry.setEngine(this);
        regionRegistry.setEngine(this);
        subProvinceRegistry.setEngine(this);
        provinceRegistry.setEngine(this);
        provinceInitializer.setEngine(this);
        cityInitializer.setEngine(this);
        riverRegistry.setEngine(this);
        lakeRegistry.setEngine(this);
        seaPartRegistry.setEngine(this);

        for (int i = 0; i < 10; i++) {
            SimulationPerson simulationPerson = personFactory.newPerson(Race.HUMAN);
            System.out.println("###################################################");
            System.out.println(simulationPerson.getModel().getName());
            simulationPerson.getGoals().entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).forEach((goal) -> System.out.println(goal.getValue() + ": " + goal.getKey().name()));

        }

        ProductionType.WOOD.initializeAsMaterial();
        ProductionType.BRICKS.initializeAsMaterial();
        TerrainResource.ROCK.initializeAsMaterial();
        TerrainResource.LIMESTONE.initializeAsMaterial();
        TerrainResource.GRANITE.initializeAsMaterial();
        TerrainResource.BASALT.initializeAsMaterial();
        TerrainResource.SLATE.initializeAsMaterial();
        TerrainResource.ALABASTER.initializeAsMaterial();
        TerrainResource.MARBLE.initializeAsMaterial();
        TerrainResource.OBSIDIAN.initializeAsMaterial();
        TerrainResource.OLD_BONES.initializeAsMaterial();
        TerrainResource.DRAGON_BONES.initializeAsMaterial();

//        provinceInitializer.readProvinces();
        cityInitializer.initializePlaces();
        List<ModelSeaPart> seaPoligons = natureInitializer.initializeSea();
        List<ModelRiver> rivers = natureInitializer.initializeRivers();
        List<ModelLake> lakes = natureInitializer.initializeLakes();
        List<ModelMountains> mountains = natureInitializer.initializeMountains();
        provinceInitializer.initializeProvinces(seaPoligons, rivers, lakes, mountains);

    }

    public RiverRegistry getRiverRegistry() {
        return riverRegistry;
    }

    public LakeRegistry getLakeRegistry() {
        return lakeRegistry;
    }

    public SeaPartRegistry getSeaPartRegistry() {
        return seaPartRegistry;
    }

    public ProvinceInitializer getProvinceInitializer() {
        return provinceInitializer;
    }

    public PlaceInitializer getCityInitializer() {
        return cityInitializer;
    }

    public NatureInitializer getNatureInitializer() {
        return natureInitializer;
    }

    public PlaceNameProvider getPlaceNameProvider() {
        return placeNameProvider;
    }

    public PlaceFactory getPlaceFactory() {
        return placeFactory;
    }

    public ProvinceFactory getProvinceFactory() {
        return provinceFactory;
    }

    public NamingProvider getNamingProvider() {
        return namingProvider;
    }

    public GoalEngine getGoalEngine() {
        return goalEngine;
    }

    public PersonRegistry getPersonRegistry() {
        return personRegistry;
    }

    public RegionRegistry getRegionRegistry() {
        return regionRegistry;
    }

    public EventManager getEventManager() {
        return eventManager;
    }

    public EventFactory getEventFactory() {
        return eventFactory;
    }

    public FamilyRegistry getFamilyRegistry() {
        return familyRegistry;
    }

    public SubProvinceRegistry getSubProvinceRegistry() {
        return subProvinceRegistry;
    }

    public ProvinceRegistry getProvinceRegistry() {
        return provinceRegistry;
    }

    public PersonFactory getPersonFactory() {
        return personFactory;
    }

//    public FamilyFactory getFamilyFactory() {
//        return familyFactory;
//    }

    public PredefinedDataFiller getDataFiller() {
        return dataFiller;
    }

    public PlaceRegistry getPlaceRegistry() {
        return placeRegistry;
    }

    public MountainsRegistry getMountainsRegistry() {
        return mountainsRegistry;
    }
}
