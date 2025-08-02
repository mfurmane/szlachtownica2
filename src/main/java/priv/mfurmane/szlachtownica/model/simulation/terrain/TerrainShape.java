package priv.mfurmane.szlachtownica.model.simulation.terrain;

public enum TerrainShape {
    FLATLANDS(new SoilType[]{SoilType.FERTILE, SoilType.BLACK, SoilType.LOAMY, SoilType.ALLUVIAL, SoilType.HUMUS_RICH, SoilType.LOESS, SoilType.CLAY, SoilType.SANDY, SoilType.PEAT, SoilType.TANGLED, SoilType.PERMAFROST}),
    HIGHLANDS(new SoilType[]{SoilType.LOAMY, SoilType.HUMUS_RICH, SoilType.CLAY, SoilType.LIMESTONE, SoilType.CHALKY, SoilType.GRAVELLY, SoilType.SHALE_TERRAIN, SoilType.ROCKY, SoilType.ACIDIC, SoilType.TANGLED, SoilType.PERMAFROST}),
    HILLS(new SoilType[]{SoilType.LOAMY, SoilType.CLAY, SoilType.HUMUS_RICH, SoilType.ROCKY, SoilType.GRAVELLY, SoilType.LIMESTONE, SoilType.TANGLED, SoilType.SHALE_TERRAIN, SoilType.VOLCANIC, SoilType.FERTILE, SoilType.PERMAFROST}),
    MOUNTAINS(new SoilType[]{SoilType.ROCKY, SoilType.BARE_CRAG, SoilType.GRAVELLY, SoilType.SHALE_TERRAIN, SoilType.HARDPAN, SoilType.LIMESTONE, SoilType.CHALKY, SoilType.VOLCANIC, SoilType.BASALTIC, SoilType.PERMAFROST, SoilType.ASHEN}),
    VALLEY(new SoilType[]{SoilType.ALLUVIAL, SoilType.FERTILE, SoilType.LOAMY, SoilType.HUMUS_RICH, SoilType.PEAT, SoilType.CLAY, SoilType.SANDY, SoilType.ASHEN, SoilType.SALINE, SoilType.PERMAFROST, SoilType.TANGLED}),
    CANYONS(new SoilType[]{SoilType.ROCKY, SoilType.SHALE_TERRAIN, SoilType.BARE_CRAG, SoilType.GRAVELLY, SoilType.SANDY, SoilType.HARDPAN, SoilType.CHALKY, SoilType.LIMESTONE, SoilType.ASHEN, SoilType.PERMAFROST}),
    WETBASIN(new SoilType[]{SoilType.PEAT, SoilType.CLAY, SoilType.HUMUS_RICH, SoilType.SALINE, SoilType.ACIDIC, SoilType.LOAMY, SoilType.ALLUVIAL, SoilType.TANGLED, SoilType.PERMAFROST}),
    PLATEAUS(new SoilType[]{SoilType.VOLCANIC, SoilType.BASALTIC, SoilType.LOAMY, SoilType.ROCKY, SoilType.GRAVELLY, SoilType.ACIDIC, SoilType.HARDPAN, SoilType.ASHEN, SoilType.BARE_CRAG, SoilType.SALINE, SoilType.PERMAFROST}),
    BADLANDS(new SoilType[]{SoilType.ASHEN, SoilType.HARDPAN, SoilType.SANDY, SoilType.GRAVELLY, SoilType.ROCKY, SoilType.CLAY, SoilType.SHALE_TERRAIN, SoilType.BARE_CRAG, SoilType.ACIDIC, SoilType.SALINE, SoilType.PERMAFROST});
    private final SoilType[] resources;

    public SoilType[] getSoilTypes() {
        return resources;
    }

    TerrainShape(SoilType[] resources) {
        this.resources = resources;

    }
}
