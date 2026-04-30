package priv.mfurmane.szlachtownica.engine.naming.model;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class WeightedPicker {

    public static <T> T pick(Map<T, Double> weights) {
        double total = weights.values().stream().mapToDouble(Double::doubleValue).sum();
        double r = ThreadLocalRandom.current().nextDouble() * total;
        double cumulative = 0.0;
        for (var entry : weights.entrySet()) {
            cumulative += entry.getValue();
            if (r <= cumulative) {
                return entry.getKey();
            }
        }
        throw new IllegalStateException("Weights broken");
    }

//    public static <T> T pickWeighted(Map<T, Double> weights, Random rng) {
//        double total = 0.0;
//
//        for (double w : weights.values()) {
//            total += w;
//        }
//
//        double r = rng.nextDouble() * total;
//
//        double cumulative = 0.0;
//
//        for (var entry : weights.entrySet()) {
//            cumulative += entry.getValue();
//            if (r <= cumulative) {
//                return entry.getKey();
//            }
//        }
//
//        throw new IllegalStateException("Empty weight map");
//    }
}
