package priv.mfurmane.szlachtownica.model.config;

import priv.mfurmane.szlachtownica.model.RaceProfile;
import priv.mfurmane.szlachtownica.model.simulation.terrain.Material;

public class ConfigurationPlace {

  private final Material preferedMaterial;

  private ConfigurationPlace(Builder builder) {
    this.preferedMaterial = builder.preferedMaterial;
  }

  public static Builder builder() {
    return new Builder();
  }

  public Material getPreferedMaterial() {
    return preferedMaterial;
  }

  public static class Builder {
    private Material preferedMaterial;

    public Builder setPreferedMaterial(Material preferedMaterial) {
      this.preferedMaterial = preferedMaterial;
      return this;
    }

    public ConfigurationPlace build() {
      return new ConfigurationPlace(this);
    }

  }

}
