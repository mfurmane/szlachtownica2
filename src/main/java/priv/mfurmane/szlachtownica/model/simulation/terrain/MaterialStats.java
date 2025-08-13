package priv.mfurmane.szlachtownica.model.simulation.terrain;

import java.util.ArrayList;
import java.util.List;

public class MaterialStats {
    private ProductionType productionType;
    private final List<TerrainResource> resources = new ArrayList<>();
    private Integer value;
    private Double endurance;
    private Double durability;
    private Double comfort;
    private Double prestige;

    public ProductionType getProductionType() {
        return productionType;
    }

    public MaterialStats setProductionType(ProductionType productionType) {
        this.productionType = productionType;
        return this;
    }

    public List<TerrainResource> getResources() {
        return resources;
    }

    public Integer getValue() {
        return value;
    }

    public MaterialStats setValue(Integer value) {
        this.value = value;
        return this;
    }

    public Double getEndurance() {
        return endurance;
    }

    public MaterialStats setEndurance(Double endurance) {
        this.endurance = endurance;
        return this;
    }

    public Double getDurability() {
        return durability;
    }

    public MaterialStats setDurability(Double durability) {
        this.durability = durability;
        return this;
    }

    public Double getComfort() {
        return comfort;
    }

    public MaterialStats setComfort(Double comfort) {
        this.comfort = comfort;
        return this;
    }

    public Double getPrestige() {
        return prestige;
    }

    public MaterialStats setPrestige(Double prestige) {
        this.prestige = prestige;
        return this;
    }
}
