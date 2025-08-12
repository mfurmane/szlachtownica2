package priv.mfurmane.szlachtownica.model.simulation.terrain;

import java.util.*;

public enum TerrainResource implements Material {
    ROCK(0.95, ResourceCategory.STONE), //kamień
    ALABASTER(0.3, ResourceCategory.STONE),
    SAND(0.95, ResourceCategory.STONE), //piasek
    CLAY(0.95, ResourceCategory.STONE), //glina
    LIMESTONE(0.95, ResourceCategory.STONE), //wapień
    CHALK(0.8, ResourceCategory.STONE), //kreda
    GRANITE(0.95, ResourceCategory.STONE), //twarda budowlana
    BASALT(0.75, ResourceCategory.STONE), //wulkaniczna ciemna
    SLATE(0.75, ResourceCategory.STONE), //łupek, cienki warstwowy
    MARBLE(0.3, ResourceCategory.STONE), //marmur
    SALT(0.75, ResourceCategory.STONE), //sól
    SULFUR(0.5, ResourceCategory.STONE), //siarka
    TIN(0.75, ResourceCategory.ORE), //cyna
    LEAD(0.7, ResourceCategory.ORE), //ołów
    ZINC(0.75, ResourceCategory.ORE), //cynk
    NICKEL(0.5, ResourceCategory.ORE), //do stali nierdzewnej
    CHROMIUM(0.5, ResourceCategory.ORE), //błyszczący, odporny
    TUNGSTEN(0.25, ResourceCategory.ORE), //wolfram, na narzędzia
    MERCURY(0.2, ResourceCategory.FLUID), //rtęć
    LERANIT(0.5, ResourceCategory.ORE), //leranit
    COPPER(0.75, ResourceCategory.ORE), //miedź
    OLD_BONES(0.5, ResourceCategory.STONE), //stare kości
    IRON(0.8, ResourceCategory.ORE), //żelazo
    COAL(0.8, ResourceCategory.STONE), //węgiel
    BARNIZYT(0.35, ResourceCategory.ORE), //barnizyt
    KASALIT(0.3, ResourceCategory.ORE), //kasalit
    VEALITIUM(0.3, ResourceCategory.ORE), //vealitium
    KELTAINUM(0.4, ResourceCategory.ORE), //keltainum
    GARANDAL(0.2, ResourceCategory.ORE), //garandal
    ELERYT(0.3, ResourceCategory.ORE), //eleryt
    PERIANIT(0.3, ResourceCategory.ORE), //perianit
    KUPARIN(0.25, ResourceCategory.ORE), //kuparin
    CELENIT(0.2, ResourceCategory.ORE), //celenit
    TURANIT(0.15, ResourceCategory.STONE), //turanit
    DRAGON_BONES(0.1, ResourceCategory.STONE), //smocze kości
    IRINTAL(0.06, ResourceCategory.ORE), //irintal
    OBSIDIAN(0.1, ResourceCategory.STONE), //obsydian
    GOLD(0.2, ResourceCategory.ORE), //złoto
    SILVER(0.25, ResourceCategory.ORE), //srebro
    PLATINUM(0.1, ResourceCategory.ORE), //platyna
    VOID_ORE(0.001, ResourceCategory.ORE), //bardzo, bardzo żadka, mało kto o niej wie, nikt normalny nie wydobywa
    STAR_SHARD(0.01, ResourceCategory.GEM), //odłamki dawno temu przybyłe z kosmosu
    SOUL_STONE(0.01, ResourceCategory.GEM), //dusznik, materiał jakoś powiązany z wymiarem duchów, na przykład soczewka z dusznika pozwala zerknąć do niego
    FLAME_STONE(0.03, ResourceCategory.GEM), //ognisty kamień, bardzo ciepły kamień, który świeci, jakby był w nim zaklęty płomień
    FROST_VEIN(0.03, ResourceCategory.GEM), //żyła lodu, bardzo zimny kamień bijący chłodnym, błękitno-białym blaskiem
    ECHO_SHARDS(0.01, ResourceCategory.GEM), //odłamki przeszłości, w niewyjaśniony sposób przechowują pamięć i przekazują wizje
    BLOODSTONE(0.03, ResourceCategory.GEM), //kwawy kamień, kopalna skamieniała krew
    MOON_METAL(0.03, ResourceCategory.ORE), //srebrzysty metal bijący delikatnym, księżycowym światłem
    SUM_METAL(0.03, ResourceCategory.ORE), //żółtawo-srebrzysty metal bijący delikatnym, słonecznym blaskiem
    NIGHT_METAL(0.03, ResourceCategory.ORE), //nocny chrom, bardzo twardy, idealnie czarny i błyszczący metal
    AGATE(0.1, ResourceCategory.GEM), //Agat
    AQUAMARINE(0.02, ResourceCategory.GEM), //Akwamaryn
    ALEXANDRITE(0.004, ResourceCategory.GEM), //Aleksandryt
    ALMANDINE(0.08, ResourceCategory.GEM), //Almandyn
    AMAZONITE(0.015, ResourceCategory.GEM), //Amazonit
    AMETHYST(0.025, ResourceCategory.GEM), //Ametyst
    BERYL(0.004, ResourceCategory.GEM), //Beryl
    AMBER(0.05, ResourceCategory.GEM), //Bursztyn
    CHALCEDONY(0.007, ResourceCategory.GEM), //Chalcedon
    CHRYSOBERYL(0.01, ResourceCategory.GEM), //Chryzoberyl
    CYMOPHANE(0.04, ResourceCategory.GEM), //Cymofan
    ZIRCON(0.08, ResourceCategory.GEM), //Cyrkon
    CUBIC_ZIRCONIA(0.06, ResourceCategory.GEM), //Cyrkonia
    CITRINE(0.1, ResourceCategory.GEM), //Cytryn
    BLACK_OPAL(0.005, ResourceCategory.GEM), //Czarny Opal
    BLACK_DIAMOND(0.001, ResourceCategory.GEM), //Czarny Diament
    RED_DIAMOND(0.002, ResourceCategory.GEM), //Czerwony Diament
    DEMANTOID(0.005, ResourceCategory.GEM), //Demantoid
    DIAMOND(0.003, ResourceCategory.GEM), //Diament
    DRAVITE(0.05, ResourceCategory.GEM), //Drawit
    PHENAKITE(0.009, ResourceCategory.GEM), //Fenakit
    FLUORITE(0.09, ResourceCategory.GEM), //Fluoryt
    GLAUCONITE(0.15, ResourceCategory.GEM), //Glaukonit
    GARNET(0.05, ResourceCategory.GEM), //Granat
    GRANDIDIERITE(0.003, ResourceCategory.GEM), //Grandidieryt
    GROSSULAR(0.08, ResourceCategory.GEM), //Grossular
    HELIODOR(0.015, ResourceCategory.GEM), //Heliodor
    INDICOLITE(0.1, ResourceCategory.GEM), //Indygolit
    JADEITE(0.03, ResourceCategory.GEM), //Jadeit
    JASPER(0.15, ResourceCategory.GEM), //Jaspis
    MOONSTONE(0.03, ResourceCategory.GEM), //Kamień Księżycowy
    LAPIS_LAZULI(0.09, ResourceCategory.GEM), //Lazuryt
    MALACHITE(0.02, ResourceCategory.GEM), //Malachit
    MORGANITE(0.01, ResourceCategory.GEM), //Morganit
    MORION(0.02, ResourceCategory.GEM), //Morion
    NEPHRITE(0.02, ResourceCategory.GEM), //Nefryt
    BLUE_DIAMOND(0.001, ResourceCategory.GEM), //Niebieski Diament
    BLUE_GARNET(0.002, ResourceCategory.GEM), //Niebieski Granat
    FIRE_OPAL(0.006, ResourceCategory.GEM), //Ognisty Opal
    PERIDOT(0.075, ResourceCategory.GEM), //Oliwin
    ONYX(0.04, ResourceCategory.GEM), //Onyks
    PEARL(0.015, ResourceCategory.GEM), //Perła
    PYROPE(0.03, ResourceCategory.GEM), //Pirop
    RUBELLITE(0.01, ResourceCategory.GEM), //Rubelit
    RUBY(0.0035, ResourceCategory.GEM), //Rubin
    SARCOLITE(0.015, ResourceCategory.GEM), //Sarkolit
    SERENDIBITE(0.004, ResourceCategory.GEM), //Serendibit
    SPESSARTINE(0.009, ResourceCategory.GEM), //Spessartyn
    SPINEL(0.01, ResourceCategory.GEM), //Spinel
    SAPPHIRE(0.0045, ResourceCategory.GEM), //Szafir
    EMERALD(0.0055, ResourceCategory.GEM), //Szmaragd
    TAAFFEITE(0.003, ResourceCategory.GEM), //Taaffeit
    TANZANITE(0.008, ResourceCategory.GEM), //Tanzanit
    TOPAZ(0.07, ResourceCategory.GEM), //Topaz
    TOURMALINE(0.015, ResourceCategory.GEM), //Turmalin
    VERDELITE(0.01, ResourceCategory.GEM); //Werdelit

    private final double availability;
    private final ResourceCategory category;
//    private final double quantityScale;

    TerrainResource(double availability, ResourceCategory category) {
        this.availability = availability;
        this.category = category;
    }

    public double getAvailability() {
        return availability;
    }

    public ResourceCategory getCategory() {
        return category;
    }

    public static void main(String[] args) {
        Arrays.stream(SoilType.values()).forEach(soilType -> {
            System.out.println(soilType+": " + Arrays.toString(soilType.getResources()));
        });
    }



}
