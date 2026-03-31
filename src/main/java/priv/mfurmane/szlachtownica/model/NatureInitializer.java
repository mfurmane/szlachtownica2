package priv.mfurmane.szlachtownica.model;

import org.eclipse.emf.ecore.resource.Resource;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Polygon;
import org.springframework.stereotype.Component;
import priv.mfurmane.szlachtownica.engine.MainEngine;
import priv.mfurmane.szlachtownica.engine.utils.GeoUtils;
import priv.mfurmane.szlachtownica.model.config.ConfigurationProvince;
import priv.mfurmane.szlachtownica.model.main.ModelLake;
import priv.mfurmane.szlachtownica.model.main.ModelProvince;
import priv.mfurmane.szlachtownica.model.main.ModelRiver;
import priv.mfurmane.szlachtownica.model.main.ModelSeaPart;
import priv.mfurmane.szlachtownica.model.main.repositories.LakeRepository;
import priv.mfurmane.szlachtownica.model.main.repositories.RiverRepository;
import priv.mfurmane.szlachtownica.model.main.repositories.SeaPartRepository;
import priv.mfurmane.szlachtownica.model.simulation.SimulationProvince;

import java.util.ArrayList;
import java.util.List;

@Component
public class NatureInitializer {
    private MainEngine engine;
    public static final GeometryFactory gf = new GeometryFactory();
    private LakeRepository lakeRepository;
    private RiverRepository riverRepository;
    private SeaPartRepository seaPartRepository;

    public void setEngine(MainEngine mainEngine) {
        this.engine = mainEngine;
    }

    public List<ModelLake> initializeLakes() {
        List<ModelLake> lakes = new ArrayList<>();
        lakes.add(initializeLake("dibert"));
        lakes.add(initializeLake("gilgam"));
        lakes.add(initializeLake("rozlewisko"));
        lakes.add(initializeLake("vostin"));
        return lakes;
    }

    public List<ModelSeaPart> initializeSea() {
        List<ModelSeaPart> seaParts = new ArrayList<>();

        return seaParts;
    }

    public List<ModelRiver> initializeRivers() {
        List<ModelRiver> rivers = new ArrayList<>();
        rivers.add(initializeRiver("alsteda"));
        rivers.add(initializeRiver("alsteda_1"));
        rivers.add(initializeRiver("alsteda_2"));
        rivers.add(initializeRiver("alsteda_3"));
        rivers.add(initializeRiver("altanor"));
        rivers.add(initializeRiver("berenora"));
        rivers.add(initializeRiver("berkosa"));
        rivers.add(initializeRiver("cambeling"));
        rivers.add(initializeRiver("doplyw"));
        rivers.add(initializeRiver("elsenora"));
        rivers.add(initializeRiver("emroy"));
        rivers.add(initializeRiver("inortus"));
        rivers.add(initializeRiver("lenera"));
        rivers.add(initializeRiver("orauda"));
        rivers.add(initializeRiver("sanna"));
        rivers.add(initializeRiver("tellera"));
        rivers.add(initializeRiver("tenera"));
        rivers.add(initializeRiver("trebonar"));
        rivers.add(initializeRiver("twilord"));
        rivers.add(initializeRiver("villera"));
        rivers.add(initializeRiver("wasanov"));
        rivers.add(initializeRiver("zeldera"));
        return rivers;
    }

    private ModelLake initializeLake(String name) {
        ModelLake lake = new ModelLake(name);
        lake.setArea(GeoUtils.readLake(name));
        lakeRepository.save(lake.toEntity());
        engine.getLakeRegistry().register(lake);
        return lake;
    }

    private ModelRiver initializeRiver(String name) {
        ModelRiver river = new ModelRiver(name);
        river.setLine(GeoUtils.readRiver(name));
        riverRepository.save(river.toEntity());
        engine.getRiverRegistry().register(river);
        return river;
    }
}
