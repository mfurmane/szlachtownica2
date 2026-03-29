package priv.mfurmane.szlachtownica.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wololo.geojson.Geometry;
import org.wololo.jts2geojson.GeoJSONWriter;
import priv.mfurmane.szlachtownica.api.dto.Place;
import priv.mfurmane.szlachtownica.api.dto.Province;
import priv.mfurmane.szlachtownica.api.dto.Region;
import priv.mfurmane.szlachtownica.api.dto.SubProvince;
import priv.mfurmane.szlachtownica.engine.MainEngine;
import priv.mfurmane.szlachtownica.engine.registry.ProvinceRegistry;
import priv.mfurmane.szlachtownica.model.main.ModelRegion;
import priv.mfurmane.szlachtownica.model.main.ModelSubProvince;
import priv.mfurmane.szlachtownica.model.simulation.SimulationPlace;
import priv.mfurmane.szlachtownica.model.simulation.SimulationProvince;
import priv.mfurmane.szlachtownica.model.simulation.SimulationSubProvince;

import java.util.List;

@Service
public class ProvinceService {
    @Autowired
    private ProvinceRegistry registry;

    private final GeoJSONWriter writer = new GeoJSONWriter();

    public List<Province> getAll() {
        return registry.getAll().stream().map(this::mapProvince).toList();
    }

    private Province mapProvince(SimulationProvince sp) {
        Province province = new Province();
        province.setId(sp.getModel().getId());
        province.setName(sp.getModel().getName());
        province.setArea(writer.write(sp.getModel().getArea()));
        List<SimulationSubProvince> subProvinces = sp.getModel().getSubProvinces();
        subProvinces.forEach(sub -> {
            ModelSubProvince model = sub.getModel();
            SubProvince result = new SubProvince();
            result.setId(model.getId());
            if (model.getArea() != null) {
                result.setArea(writer.write(model.getArea()));
                model.getRegions().forEach(reg -> {
                    Region region = new Region(reg);
                    reg.getPlaces().forEach(placeId -> {
                        if (placeId == 1) {
//                            SimulationPlace place = MainEngine.getInstance().getPlaceRegistry().get(placeId);
//                            Place newPlace = new Place();
//                            newPlace.setName(place.getModel().getName());
//                            newPlace.setLocation(writer.write(place.getModel().getLocation()));
//                            region.getPlaces().add(newPlace);
                        }
                    });
                    region.setArea(writer.write(reg.getArea()));
                    result.getRegions().add(region);
                });
            }
            province.getSubProvinces().add(result);
        });
        return province;
    }

}
