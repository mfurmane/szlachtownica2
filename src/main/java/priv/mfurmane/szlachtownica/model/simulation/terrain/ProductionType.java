        package priv.mfurmane.szlachtownica.model.simulation.terrain;

public enum ProductionType {
    //            🌿 Kultura Elfów (~0)
    DYES(0),
    WOOD(0),
    STONE(0),
    CLAY(0),
    SAND(0),
    RAW_LEATHER(0),
    MILK(0),
    EGGS(0),
    MEAT(0),
    FISH(0),
    MUSHROOMS(0),
    HERBS(0),
    BONES(0),
    FLOWERS(0),
    WOOL(0),
    RESIN(0),
    CEREALS(0),
    YARN(0),
    FIBERS(0),
    BUSH_FRUITS(0),
    TREE_FRUITS(0),
    RICE(0),
    CORN(0),
    SCROLLS(0),
    SCROLL_CASES(0),
    VELLUM(0),
    MAGIC_INGREDIENTS(0),
    RUNES(0),
    CHARMS(0),
    TOOLS(0),
    CLOTHES(0),
    WINE(0),
    MEAD(0),
    BEER(0),
// ⚒️ Feudalna / Dwarven Industrial (578 ~1100)
    COAL(0),
    GLASS(0),
    BRICKS(0),
    TANNED_LEATHER(0),
    FABRIC(0),
    METALS(0),
    METAL_TRINKETS(0),
    BOOKS(0),
    LEATHER_GOODS(0),
    PLANKS(0),
    THREAD(0),
    FURNITURE(0),
    BARRELS(0),
    JEWELLERY(0),
    INSTRUMENTS(0),
    PAINTINGS(0),
    POTS(0),
    WINDOWS(0),
    MIRRORS(0),
    SADDLES(0),
    WAGONS(0),
    //            🔥 Renesans (~1600)
    DISTILLATE(0),
    RARE_SPICES(0),
    FINE_WINE(0),
    SUGAR_PLANTS(0),
    TOBACCO_LEAVES(0),
    COFFEE_BEANS(0),
    TEA_LEAVES(0),
    ALCHEMICAL_COMPONENTS(0),
    ELIXIRS(0),
    WEAPONS(0),
    ARMORS(0),
    //            ⚙️ Wczesny Przemysł (~1720),
    SULPHUR(0),
    PHOSPHATES(0),
    GUANO(0),
    CLOCKS(0),
    QUALITY_CLOTHES(0),
    TOYS(0),
    CHARCOAL(0),
    BITUMEN(0),
    SOAP(0),
    CANDLES(0);

    private final int availableFrom;

    public int getAvailableFrom() {
        return availableFrom;
    }

    ProductionType(int availableFrom) {
        this.availableFrom = availableFrom;
    }
}
