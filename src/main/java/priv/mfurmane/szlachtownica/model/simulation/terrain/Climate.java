package priv.mfurmane.szlachtownica.model.simulation.terrain;

public enum Climate {
    VERY_COLD(new SoilType[]{SoilType.ROCKY, SoilType.PERMAFROST, SoilType.BARE_CRAG}),
    COLD(new SoilType[]{SoilType.FERTILE, SoilType.LOAMY, SoilType.HUMUS_RICH, SoilType.CLAY, SoilType.ACIDIC, SoilType.ROCKY, SoilType.GRAVELLY, SoilType.HARDPAN, SoilType.PEAT, SoilType.LIMESTONE, SoilType.SHALE_TERRAIN, SoilType.BARE_CRAG}),
    NEUTRAL(new SoilType[]{SoilType.FERTILE, SoilType.BLACK, SoilType.LOAMY, SoilType.ALLUVIAL, SoilType.VOLCANIC, SoilType.HUMUS_RICH, SoilType.LOESS, SoilType.SANDY, SoilType.CLAY, SoilType.ACIDIC, SoilType.ROCKY, SoilType.GRAVELLY, SoilType.HARDPAN, SoilType.PEAT, SoilType.LIMESTONE, SoilType.CHALKY, SoilType.SHALE_TERRAIN}),
    WARM(new SoilType[]{SoilType.FERTILE, SoilType.BLACK, SoilType.LOAMY, SoilType.ALLUVIAL, SoilType.VOLCANIC, SoilType.HUMUS_RICH, SoilType.LOESS, SoilType.BASALTIC, SoilType.SANDY, SoilType.CLAY, SoilType.GRAVELLY, SoilType.LIMESTONE, SoilType.CHALKY, SoilType.SHALE_TERRAIN, SoilType.TANGLED, SoilType.BARE_CRAG, SoilType.ASHEN}),
    HOT(new SoilType[]{SoilType.ALLUVIAL, SoilType.VOLCANIC, SoilType.BASALTIC, SoilType.SANDY, SoilType.TANGLED, SoilType.ASHEN});

    private final SoilType[] soilTypes;

    Climate(SoilType[] soilTypes) {
        this.soilTypes = soilTypes;
    }

    public SoilType[] getSoilTypes() {
        return soilTypes;
    }
}
