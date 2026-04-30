package priv.mfurmane.szlachtownica.engine.naming.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class WeightMapBuilder {

    private final Map<String, Double> map = new LinkedHashMap<>();

    public WeightMapBuilder add(double weight, String... items) {
        for (String item : items) {
            map.put(item, weight);
        }
        return this;
    }

    public Map<String, Double> build() {
        return map;
    }
}
