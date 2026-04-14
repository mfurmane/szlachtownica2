package priv.mfurmane.szlachtownica.model.simulation.terrain;

public enum Climate {
    VERY_COLD(0.0, 0.7, new SoilType[]{SoilType.ROCKY, SoilType.PERMAFROST, SoilType.BARE_CRAG}),
    COLD(0.25, 0.9, new SoilType[]{SoilType.FERTILE, SoilType.LOAMY, SoilType.HUMUS_RICH, SoilType.CLAY, SoilType.ACIDIC, SoilType.ROCKY, SoilType.GRAVELLY, SoilType.HARDPAN, SoilType.PEAT, SoilType.LIMESTONE, SoilType.SHALE_TERRAIN, SoilType.BARE_CRAG}),
    NEUTRAL(0.5, 1.0, new SoilType[]{SoilType.FERTILE, SoilType.BLACK, SoilType.LOAMY, SoilType.ALLUVIAL, SoilType.VOLCANIC, SoilType.HUMUS_RICH, SoilType.LOESS, SoilType.SANDY, SoilType.CLAY, SoilType.ACIDIC, SoilType.ROCKY, SoilType.GRAVELLY, SoilType.HARDPAN, SoilType.PEAT, SoilType.LIMESTONE, SoilType.CHALKY, SoilType.SHALE_TERRAIN}),
    WARM(0.75, 1.1, new SoilType[]{SoilType.FERTILE, SoilType.BLACK, SoilType.LOAMY, SoilType.ALLUVIAL, SoilType.VOLCANIC, SoilType.HUMUS_RICH, SoilType.LOESS, SoilType.BASALTIC, SoilType.SANDY, SoilType.CLAY, SoilType.GRAVELLY, SoilType.LIMESTONE, SoilType.CHALKY, SoilType.SHALE_TERRAIN, SoilType.TANGLED, SoilType.BARE_CRAG, SoilType.ASHEN}),
    HOT(1.0, 1.2, new SoilType[]{SoilType.ALLUVIAL, SoilType.VOLCANIC, SoilType.BASALTIC, SoilType.SANDY, SoilType.TANGLED, SoilType.ASHEN});

    private final SoilType[] soilTypes;
    private final Double fertility;
    private final Double temperature;

    Climate(Double temperature, Double fertility, SoilType[] soilTypes) {
        this.temperature = temperature;
        this.soilTypes = soilTypes;
        this.fertility = fertility;
    }

    public Double getFertility() {
        return fertility;
    }

    public SoilType[] getSoilTypes() {
        return soilTypes;
    }

    public Double getTemperature() {
        return temperature;
    }
}
