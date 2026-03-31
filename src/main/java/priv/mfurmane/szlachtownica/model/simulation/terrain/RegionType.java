package priv.mfurmane.szlachtownica.model.simulation.terrain;

import java.util.List;

public enum RegionType {
    FOREST(false),
    SWAMP(false),
    DUST_PLAIN(false),
    ROCK_LAND(false),
    PINE_CRAG(false),
    MEADOWS(false),
    FARMING_LAND(true),
    SETTLERS_REACH(true),
    TOURISTIC_LAND(true),
    ESTATE_REGION(true),
    CRAFTS_LAND(true),
    ABANDONED_REACH(false),
    IRON_MARCHES(true),
    SUPERNATURAL_EXPANSE(true);

    public static List<RegionType> getNatural() {
        return List.of(FOREST, SWAMP, DUST_PLAIN, ROCK_LAND, PINE_CRAG, MEADOWS);
    }

    public static List<RegionType> getSettled() {
        return List.of(FARMING_LAND, SETTLERS_REACH, CRAFTS_LAND, SUPERNATURAL_EXPANSE);
    }

    public static List<RegionType> getEmpty() {
        return List.of(FOREST, SWAMP, DUST_PLAIN, ROCK_LAND, PINE_CRAG, MEADOWS, ABANDONED_REACH);
    }

    public static List<RegionType> getAllSettled() {
        return List.of(FARMING_LAND, SETTLERS_REACH, TOURISTIC_LAND, ESTATE_REGION, CRAFTS_LAND, IRON_MARCHES, SUPERNATURAL_EXPANSE);
    }

    private boolean settled;

    private RegionType(boolean settled) {
        this.settled = settled;
    }

//    FOREST, // puszcza bez enchantu
//    GREENWOODS, //porządny las
//    WOODS, //trochę bieda las
//    SWAMP,
//    DUST_PLAINS, //suchy teren
//    ROCK_LANDS, //rejon skalisty
//    ORE_LANDS,
//    PINE_CRAG, //rejon skalisty z iglakami

//    STONE_PITS, // - typ eksploatacji

//    MEADOWS,

//    LAKE, // - cecha, nie typ terenu
//    RIVERLAND, // - cecha, nie typ terenu

//    PASTURES, // - typ eksploatacji

//    FIELDS,
//    ORCHARDS,
//    FRUIT_PLANTATIONS,
//    VINEYARDS,
//    VERDANT_REACH, //ogrody ziołowe i uprawa
//    GARDENS,
//    FARMS,
//    STUDS,
//    SETTLERS_REACH, //teren zasiedlany

//    DREAM_GROVE, //magiczne ogrody // magiczność
//    SHELTER_LANDS, //bezpieczny, spokojny region // spokój i bezpieczeństwo
//    HEARTH_LANDS, //przytulnie, domowo, cozy // przytulność
//    BLOOMING_REACH, //kwitnący estetyczny region mieszkalny // estetyka roślinna
//    COMFORT_VALE, //rejon wysokiego komfortu // komfort

//    RESORT_BELT, //rejon kurortów // kurorty
//    PAVILIONS_LANDS, //zagłębie domków letnich itp // dworki szlacheckie

//    GOLDEN_MARCH, //rejon sprzyjający handlowi //handlowość
//    MERCHANTS_REACH, //rejon skupiony na handlu //handlowość
//    TRADE_HEAVEN, //rejon, do którego przyjeżdża się z daleka na zakupy //handlowość
//    TIMBERLAND, //forest i wildwoods muszą przejść ten etap, żeby zostać czymkolwiek innym // typ eksploatacji
//    CRAFTS_LAND,
//    MANUFACTURING_REGION,
//    ABANDONED_REACH, //opuszczone miasta i wsie
//    FISHING_COAST, //rybactwo morskie
//    FISHING_REGION, //rybactwo rzeczne
//    HUNTING_REGION,
//    MINING_LANDS, //wydobycie na ore landsach
//    SMELT_LANDS, //huty
//    IRON_MARCHES, //rejon wysoko uprzemysłowiony
//    ARCANE_REACH, //skupisko magów
//    ARCANE_EXPANSE, //zagłębie magów
//    PILGRIMS_SPAN,  //ściąga pielgrzymów
//    HOLY_EXPANSE,
//    SPIRITED_LANDS, //ściąga wyznawców szamanizmu
//    SPIRITED_EXPANSE,
//    GROVE, //rejon zaopiekowany przez druidów
//    ANCIENT_GROVE //bardzo stary gaj druidów
}
