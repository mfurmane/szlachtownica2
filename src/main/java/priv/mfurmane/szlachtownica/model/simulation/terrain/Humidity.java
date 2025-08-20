package priv.mfurmane.szlachtownica.model.simulation.terrain;

public enum Humidity {
    EXTRA_DRY(0.6, new SoilType[]{SoilType.SANDY, SoilType.SALINE, SoilType.ROCKY, SoilType.BARE_CRAG, SoilType.ASHEN}),
    DRY(0.8, new SoilType[]{SoilType.FERTILE, SoilType.LOAMY, SoilType.LOESS, SoilType.SANDY, SoilType.SALINE, SoilType.ROCKY, SoilType.GRAVELLY, SoilType.LIMESTONE, SoilType.CHALKY, SoilType.SHALE_TERRAIN, SoilType.PERMAFROST, SoilType.BARE_CRAG, SoilType.ASHEN}),
    NEUTRAL(1.0, new SoilType[]{SoilType.FERTILE, SoilType.BLACK, SoilType.LOAMY, SoilType.ALLUVIAL, SoilType.VOLCANIC, SoilType.HUMUS_RICH, SoilType.LOESS, SoilType.BASALTIC, SoilType.SANDY, SoilType.CLAY, SoilType.ROCKY, SoilType.GRAVELLY, SoilType.HARDPAN, SoilType.LIMESTONE, SoilType.CHALKY, SoilType.SHALE_TERRAIN, SoilType.PERMAFROST, SoilType.BARE_CRAG}),
    WET(1.2, new SoilType[]{SoilType.FERTILE, SoilType.LOAMY, SoilType.ALLUVIAL, SoilType.VOLCANIC, SoilType.HUMUS_RICH, SoilType.BASALTIC, SoilType.CLAY, SoilType.SALINE, SoilType.ACIDIC, SoilType.ROCKY, SoilType.HARDPAN, SoilType.PEAT, SoilType.LIMESTONE, SoilType.SHALE_TERRAIN, SoilType.TANGLED, SoilType.PERMAFROST}),
    EXTRA_WET(1.0, new SoilType[]{SoilType.ALLUVIAL, SoilType.HUMUS_RICH, SoilType.CLAY, SoilType.ACIDIC, SoilType.PEAT, SoilType.TANGLED});

    private final SoilType[] soilTypes;
    private final Double fertility;

    Humidity(Double fertility, SoilType[] soilTypes) {
        this.soilTypes = soilTypes;
        this.fertility = fertility;
    }

    public Double getFertility() {
        return fertility;
    }

    public SoilType[] getSoilTypes() {
        return soilTypes;
    }
}
