package priv.mfurmane.szlachtownica.model.simulation.terrain;

import java.util.Random;

public enum EnchantType {

    //TODO change values
    NONE(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0),
    WILDERNESS(1.2, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0), //teren dotknięty Nereneth
    CURSED(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0), //teren z pewnym wpływem Caithaloonu lub Pustki
    TWISTED(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0), //teren dotknięty dziką magią
    WARPED(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0), //teren dotknięty przez otchłań
    VEIL(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0),  //teren przebijania wymiaru duchowego
    ENCHANTED(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0), //ziemie generalnie magiczne
    SACRED(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0); //ziemie dotknięte Corellią

    private final Double fertility;
    private final Double efficiency;
    private final Double plantingEasiness;
    private final Double farmingEasiness;
    private final Double health;
    private final Double windOfChange;
    private final Double expansion;
    private final Double attitude;
    private final Double stability;
    private final Double tendency; //czy tendencja zmian jest pozytywna, negatywna, czy losowa itp

    EnchantType(Double fertility, Double efficiency, Double plantingEasiness, Double farmingEasiness, Double health, Double windOfChange, Double expansion, Double attitude, Double stability, Double tendency) {
        this.fertility = fertility;
        this.efficiency = efficiency;
        this.plantingEasiness = plantingEasiness;
        this.farmingEasiness = farmingEasiness;
        this.health = health;
        this.windOfChange = windOfChange;
        this.expansion = expansion;
        this.attitude = attitude;
        this.stability = stability;
        this.tendency = tendency;
    }

    private Double handleValue(Double value, Integer enchantmentLevel) {
        if (Math.abs(0-value) < 0.001) {
            return new Random().nextDouble() + 0.5;
        }
        return Math.pow(value, enchantmentLevel);
    }

    public Double getFertility(Integer enchantmentLevel) {
        return handleValue(fertility, enchantmentLevel);
    }

    public Double getEfficiency(Integer enchantmentLevel) {
        return handleValue(efficiency, enchantmentLevel);
    }

    public Double getPlantingEasiness(Integer enchantmentLevel) {
        return handleValue(plantingEasiness, enchantmentLevel);
    }

    public Double getFarmingEasiness(Integer enchantmentLevel) {
        return handleValue(farmingEasiness, enchantmentLevel);
    }

    public Double getHealth(Integer enchantmentLevel) {
        return handleValue(health, enchantmentLevel);
    }

    public Double getWindOfChange(Integer enchantmentLevel) {
        return handleValue(windOfChange, enchantmentLevel);
    }

    public Double getExpansion(Integer enchantmentLevel) {
        return handleValue(expansion, enchantmentLevel);
    }

    public Double getAttitude(Integer enchantmentLevel) {
        return handleValue(attitude, enchantmentLevel);
    }

    public Double getStability(Integer enchantmentLevel) {
        return handleValue(stability, enchantmentLevel);
    }

    public Double getTendency(Integer enchantmentLevel) {
        return handleValue(tendency, enchantmentLevel);
    }
}
