package priv.mfurmane.szlachtownica.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wololo.jts2geojson.GeoJSONWriter;
import priv.mfurmane.szlachtownica.api.dto.Place;
import priv.mfurmane.szlachtownica.api.dto.Province;
import priv.mfurmane.szlachtownica.api.dto.Region;
import priv.mfurmane.szlachtownica.api.dto.SubProvince;
import priv.mfurmane.szlachtownica.engine.registry.PlaceRegistry;
import priv.mfurmane.szlachtownica.engine.registry.ProvinceRegistry;
import priv.mfurmane.szlachtownica.model.main.ModelSubProvince;
import priv.mfurmane.szlachtownica.model.simulation.SimulationPlace;
import priv.mfurmane.szlachtownica.model.simulation.SimulationProvince;
import priv.mfurmane.szlachtownica.model.simulation.SimulationSubProvince;

import java.util.List;

@Service
public class ProvinceService {
    @Autowired
    private ProvinceRegistry provinceRegistry;
    @Autowired
    private PlaceRegistry placeRegistry;

    private final GeoJSONWriter writer = new GeoJSONWriter();

    public List<Province> getAll() {
        List<SimulationPlace> allPlaces = placeRegistry.getAll();
        return provinceRegistry.getAll().stream()
                .map(sp -> mapProvince(sp, allPlaces))
                .toList();
    }

    private Province mapProvince(SimulationProvince sp, List<SimulationPlace> allPlaces) {
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
                    allPlaces.stream()
                        .filter(place -> place.getModel().getLocation() != null && reg.getArea()
                        .contains(place.getModel().getLocation()))
                        .map(this::mapPlace).forEach(region.getPlaces()::add);
                    region.setArea(writer.write(reg.getArea()));
                    result.getRegions().add(region);
                });
            }
            province.getSubProvinces().add(result);
        });
        return province;
    }

    private Place mapPlace(SimulationPlace place) {
        Place newPlace = new Place();
        newPlace.setId(place.getModel().getId());
        newPlace.setName(place.getModel().getName());
        newPlace.setType(place.getModel().getType());
        newPlace.setPlaceCharacteristics(List.copyOf(place.getModel().getPlaceCharacteristics()));
        newPlace.setLocation(writer.write(place.getModel().getLocation()));
        return newPlace;
    }

}
