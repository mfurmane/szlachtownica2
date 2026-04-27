package priv.mfurmane.szlachtownica.model.config;

import priv.mfurmane.szlachtownica.model.RaceProfile;
import priv.mfurmane.szlachtownica.model.simulation.terrain.Material;

public class ConfigurationPlace {

  private final Material preferedMaterial;
  private final Integer builtYear;
  private final Integer destroyedYear;

  private ConfigurationPlace(Builder builder) {
    this.preferedMaterial = builder.preferedMaterial;
    this.builtYear = builder.builtYear;
    this.destroyedYear = builder.destroyedYear;
  }

  public static Builder builder() {
    return new Builder();
  }

  public Material getPreferedMaterial() {
    return preferedMaterial;
  }

  public Integer getBuiltYear() {
    return builtYear;
  }

  public Integer getDestroyedYear() {
    return destroyedYear;
  }

  public static class Builder {
    private Material preferedMaterial;
    private Integer builtYear;
    private Integer destroyedYear;

    public Builder setPreferedMaterial(Material preferedMaterial) {
      this.preferedMaterial = preferedMaterial;
      return this;
    }

    public Builder setBuiltYear(Integer builtYear) {
      this.builtYear = builtYear;
      return this;
    }

    public Builder setDestroyedYear(Integer destroyedYear) {
      this.destroyedYear = destroyedYear;
      return this;
    }

    public ConfigurationPlace build() {
      return new ConfigurationPlace(this);
    }

  }

}
