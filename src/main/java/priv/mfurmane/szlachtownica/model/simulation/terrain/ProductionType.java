        package priv.mfurmane.szlachtownica.model.simulation.terrain;

public enum ProductionType implements Material {
    WOOD(ImportNeed.BUILDING_MATERIALS, -700, 5),
    STONE(ImportNeed.BUILDING_MATERIALS, -700, 4),
    SAND(ImportNeed.BUILDING_MATERIALS, -700, 2),
    RAW_LEATHER(ImportNeed.CLOTHES, -700, 8),
    MEAT(ImportNeed.FOOD, -700, 6),
    BONES(ImportNeed.BUILDING_MATERIALS, -700, 2),
    FLOWERS(ImportNeed.LUXURIES, -700, 5),
    BUSH_FRUITS(ImportNeed.FOOD, -700, 4),
    TREE_FRUITS(ImportNeed.FOOD, -700, 5),
    MAGIC_INGREDIENTS(ImportNeed.MAGIC_STUFF, -700, 25),
    CLOTHES(ImportNeed.CLOTHES, -439, 10),
    MEAD(ImportNeed.LUXURIES, -389, 13),
    FISH(ImportNeed.FOOD, -316, 6),
    FIBERS(ImportNeed.CLOTHES, -262, 5),
    YARN(ImportNeed.CLOTHES, -261, 6),
    WINE(ImportNeed.LUXURIES, -257, 12),
    MUSHROOMS(ImportNeed.FOOD, -212, 4),
    EGGS(ImportNeed.FOOD, -58, 3),
    CLAY(ImportNeed.BUILDING_MATERIALS, -28, 3),
    WEAPONS(ImportNeed.SAFETY, -25, 30),
    HERBS(ImportNeed.MAGIC_STUFF, -13, 15),
    WOOL(ImportNeed.CLOTHES, 12, 7),
    CEREALS(ImportNeed.FOOD, 28, 4),
    CHARMS(ImportNeed.MAGIC_STUFF, 37, 35),
    DYES(ImportNeed.LUXURIES, 42, 12),
    MILK(ImportNeed.FOOD, 67, 3),
    SCROLLS(ImportNeed.MAGIC_STUFF, 138, 20),
    BEER(ImportNeed.LUXURIES, 148, 8),
    JEWELLERY(ImportNeed.LUXURIES, 149, 45),
    ELIXIRS(ImportNeed.MAGIC_STUFF, 229, 45),
    TOOLS(ImportNeed.TOOLS, 253, 10),
    RESIN(ImportNeed.BUILDING_MATERIALS, 263, 6),
    CHARCOAL(ImportNeed.MINING_RESOURCES, 276, 7),
    BOOKS(ImportNeed.LUXURIES, 283, 25),
    ARMORS(ImportNeed.SAFETY, 312, 40),
    ALCHEMICAL_COMPONENTS(ImportNeed.MAGIC_STUFF, 327, 35),
    RICE(ImportNeed.FOOD, 372, 5),
    CORN(ImportNeed.FOOD, 401, 4),
    METALS(ImportNeed.MINING_RESOURCES, 415, 15),
    FURNITURE(ImportNeed.BUILDING_MATERIALS, 461, 18),
    LEATHER_GOODS(ImportNeed.CLOTHES, 493, 15),
    BARRELS(ImportNeed.BUILDING_MATERIALS, 506, 12),
    WAGONS(ImportNeed.BUILDING_MATERIALS, 514, 30),
    SCROLL_CASES(ImportNeed.MAGIC_STUFF, 536, 15),
    COAL(ImportNeed.MINING_RESOURCES, 542, 6),
    VELLUM(ImportNeed.CLOTHES, 612, 18),
    FINE_WINE(ImportNeed.LUXURIES, 613, 30),
    RUNES(ImportNeed.MAGIC_STUFF, 617, 40),
    METAL_TRINKETS(ImportNeed.TOOLS, 634, 18),
    SADDLES(ImportNeed.LUXURIES, 635, 18),
    INSTRUMENTS(ImportNeed.LUXURIES, 643, 30),
    BRICKS(ImportNeed.BUILDING_MATERIALS, 728, 5),
    PLANKS(ImportNeed.CLOTHES, 739, 8),
    TANNED_LEATHER(ImportNeed.CLOTHES, 761, 12),
    THREAD(ImportNeed.CLOTHES, 876, 6),
    GUANO(ImportNeed.FERTILIZER, 894, 6),
    FABRIC(ImportNeed.CLOTHES, 917, 8),
    CANDLES(ImportNeed.LIVING_STUFF, 963, 8),
    RARE_SPICES(ImportNeed.LUXURIES, 1037, 45),
    WINDOWS(ImportNeed.BUILDING_MATERIALS, 1056, 15),
    TEA_LEAVES(ImportNeed.LUXURIES, 1113, 24),
    COFFEE_BEANS(ImportNeed.LUXURIES, 1121, 27),
    POTS(ImportNeed.LIVING_STUFF, 1182, 8),
    GLASS(ImportNeed.BUILDING_MATERIALS, 1193, 10),
    TOYS(ImportNeed.LUXURIES, 1227, 15),
    MIRRORS(ImportNeed.LUXURIES, 1238, 25),
    PAINTINGS(ImportNeed.LUXURIES, 1261, 30),
    TOBACCO_LEAVES(ImportNeed.LUXURIES, 1274, 25),
    DISTILLATE(ImportNeed.LUXURIES, 1292, 30),
    SOAP(ImportNeed.LIVING_STUFF, 1314, 12),
    QUALITY_CLOTHES(ImportNeed.CLOTHES, 1352, 20),
    SUGAR_PLANTS(ImportNeed.LUXURIES, 1467, 17),
    SULPHUR(ImportNeed.MINING_RESOURCES, 1531, 12),
    BITUMEN(ImportNeed.MINING_RESOURCES, 1676, 10),
    PHOSPHATES(ImportNeed.MINING_RESOURCES, 1692, 10),
    CLOCKS(ImportNeed.LUXURIES, 1703, 30);

    private final ImportNeed importCategory;
    private final int availableFrom;
    private final int value;

    public int getAvailableFrom() {
        return availableFrom;
    }

    public ImportNeed getImportCategory() {
        return importCategory;
    }

    public int getValue() {
        return value;
    }

    ProductionType(ImportNeed importCategory, int availableFrom, int value) {
        this.importCategory = importCategory;
        this.availableFrom = availableFrom;
        this.value = value;
    }
}
