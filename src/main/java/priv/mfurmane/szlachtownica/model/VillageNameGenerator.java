package priv.mfurmane.szlachtownica.model;

import org.apache.commons.text.WordUtils;
import priv.mfurmane.szlachtownica.engine.naming.model.*;
import priv.mfurmane.szlachtownica.engine.naming.saravera.SaraveraPhonotactic;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class VillageNameGenerator {

    public static final Map<Integer, Double> villageSylables = Map.of(1, 8.0, 2, 2.0);
    public static final ThreadLocalRandom rng = ThreadLocalRandom.current();

    // TODO Trzeba to przepuścić przez Klaudię, bo jak się okazuje kurewsko niepoprawne bywają formy przymiotników dla liczby mnogiej
    // prompt: Przejrzyj mi proszę klasę VillageNameGenerator pod kątem poprawności tworzonych form w języku polskim. WordCore na ten moment zawiera trzon słowa (póki co nie używany, base), formy rzeczownika i listę form przymiotnika. Zrobiłem pierwsze kroki w stornę nazw w rodzaju żabianka, zażabie, zadupie, wierzbianka, ale poddałem się, gdy się zorientowałem, że będzie to dużo trudniejsze, niż zakładałem i może wymagać czegoś podobnego do przymiotników. Temu miał służyć parametr singleForm. Wprowadź proszę takie zmiany w te definicje, żeby formy były zgodne ze słownikiem języka polskiego
    private static List<WordCore> areas = List.of(
            //NEUTRAL
            new WordCore("wzgórz", WordGender.NEUTRAL, PluralType.NON_MASC_PERSONAL, NounForms.createE("wzgórz"), List.of(AdjectiveForms.createOwy("wzgórz")), "wzgórzy"),
            new WordCore("sioł", WordGender.NEUTRAL, PluralType.NON_MASC_PERSONAL, NounForms.createO("sioł"), List.of(new AdjectiveForms("sielski", "sielska", "sielskie", "sielscy", "sielskie")), ""),
            new WordCore("grodzisk", WordGender.NEUTRAL, PluralType.NON_MASC_PERSONAL, NounForms.createO("grodzisk"), List.of(AdjectiveForms.createOwy("grodzisk")), ""),
            new WordCore("błot", WordGender.NEUTRAL, PluralType.NON_MASC_PERSONAL, NounForms.createO("błot"), List.of(AdjectiveForms.createYY("błotn"), AdjectiveForms.createYY("błotnist")), ""),
            new WordCore("pol", WordGender.NEUTRAL, PluralType.NON_MASC_PERSONAL, NounForms.createE("pol"), List.of(AdjectiveForms.createYY("poln")), ""),
            new WordCore("zacisz", WordGender.NEUTRAL, PluralType.NON_MASC_PERSONAL, NounForms.createE("zacisz"), List.of(AdjectiveForms.createYY("zaciszn")), ""),
            new WordCore("słońc", WordGender.NEUTRAL, PluralType.NON_MASC_PERSONAL, NounForms.createE("słońc"), List.of(AdjectiveForms.createYY("słoneczn")), ""),
            new WordCore("zarośl", WordGender.NEUTRAL, PluralType.NON_MASC_PERSONAL, NounForms.createE("zarośl"), List.of(AdjectiveForms.createYY("zarośnięt"), AdjectiveForms.createYY("zarosł")), ""),
            //FEMININE
            new WordCore("brzoz", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("brzoz"), List.of(AdjectiveForms.createOwy("brzoz")), ""),
            new WordCore("wierzb", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("wierzb"), List.of(AdjectiveForms.createOwy("wierzb")), ""),
            new WordCore("rzek", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAi("rzek"), List.of(AdjectiveForms.createCzny("rze")), ""),
            new WordCore("sosn", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("sosn"), List.of(AdjectiveForms.createOwy("sosn")), ""),
            new WordCore("topol", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAe("topol"), List.of(AdjectiveForms.createOwy("topol"), AdjectiveForms.createNy("topol")), ""),
            new WordCore("żab", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("żab"), List.of(AdjectiveForms.createI("żab")), ""),
            new WordCore("skał", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("skał"), List.of(AdjectiveForms.createNy("skal")), ""),
            new WordCore("sarn", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("sarn"), List.of(AdjectiveForms.createNi("sar")), ""),
            new WordCore("kun", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("kun"), List.of(AdjectiveForms.createNi("ku")), ""),
            new WordCore("krow", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("krow"), List.of(AdjectiveForms.createI("krow")), ""),
            new WordCore("koz", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("koz"), List.of(AdjectiveForms.createI("koz")), ""),
            new WordCore("owc", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAe("owc"), List.of(AdjectiveForms.createCzy("ow")), ""),
            new WordCore("kaczk", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAi("kaczk"), List.of(AdjectiveForms.createYY("kacz")), ""),
            new WordCore("samic", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAe("samic"), List.of(AdjectiveForms.createYY("samicz")), ""),
            new WordCore("ryb", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("ryb"), List.of(AdjectiveForms.createI("ryb")), ""),
            new WordCore("stodoł", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("stodoł"), List.of(AdjectiveForms.createNy("stodol")), ""),
            new WordCore("obor", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("obor"), List.of(AdjectiveForms.createNy("obor")), ""),
            new WordCore("kuźni", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAe("kuźni"), List.of(AdjectiveForms.createNy("kuźnia")), ""),
            new WordCore("karczm", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("karczm"), List.of(AdjectiveForms.createNy("karczem")), ""),
            new WordCore("przysta", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createNie("przysta"), List.of(AdjectiveForms.createNy("przystan")), ""),
            new WordCore("staroś", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createCi("staroś"), List.of(AdjectiveForms.createYY("star")), ""),
            new WordCore("nowoś", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createCi("nowoś"), List.of(AdjectiveForms.createYY("now")), ""),
            new WordCore("wielko", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createCi("wielkoś"), List.of(AdjectiveForms.createI("wielk")), ""),
            new WordCore("łąk", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAi("łąk"), List.of(AdjectiveForms.createOwy("łąk"), AdjectiveForms.createNy("łącz")), ""),
            new WordCore("gór", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("gór"), List.of(AdjectiveForms.createKi("górs"), AdjectiveForms.createNy("gór")), ""),
            new WordCore("wysokoś", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createCi("wysokoś"), List.of(AdjectiveForms.createKi("wyso"), AdjectiveForms.createOwy("wysokości")), ""),
            new WordCore("głębi", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAe("głębi"), List.of(AdjectiveForms.createKi("głębo"), AdjectiveForms.createOwy("głębin")), ""),
            new WordCore("płycizn", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("płycizn"), List.of(AdjectiveForms.createKi("płyt")), ""),
            new WordCore("wod", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("wod"), List.of(AdjectiveForms.createYY("wodn")), ""),
            new WordCore("dup", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("dup"), List.of(AdjectiveForms.createYY("dupn"), AdjectiveForms.createYY("dupczan"), AdjectiveForms.createYY("dupiast")), ""),
            new WordCore("kurw", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("kurw"), List.of(AdjectiveForms.createI("kurw"), AdjectiveForms.createYY("kurwiczn"), AdjectiveForms.createYY("wykurwist"), AdjectiveForms.createYY("skurwion")), ""),
//            new WordCore("wod", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("wod"), List.of(AdjectiveForms.createY("wodn")), ""),
            //NO PLURAL
            new WordCore("małoś", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, new NounForms("małość", null), List.of(new AdjectiveForms("mały", "mała", "małe", "mali", "małe"), AdjectiveForms.createYI("małostkow")), ""),
            new WordCore("ziele", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, new NounForms("zieleń", null), List.of(AdjectiveForms.createYY("zielon")), ""),
            new WordCore("suchoś", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, new NounForms("suchość", null), List.of(AdjectiveForms.createYY("such")), ""),
            new WordCore("mokroś", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, new NounForms("mokrość", null), List.of(AdjectiveForms.createYY("mokry"), AdjectiveForms.createNy("przemoczo")), ""),
            new WordCore("susz", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, new NounForms("susza", null), List.of(AdjectiveForms.createNy("przesuszo")), ""),
            new WordCore("cisz", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, new NounForms("cisza", null), List.of(AdjectiveForms.createYY("cich")), ""),
            new WordCore("dzicz", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, new NounForms("dzicz", null), List.of(AdjectiveForms.createI("dzik")), ""),
            new WordCore("gęstwa", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, new NounForms("gęstwa", null), List.of(AdjectiveForms.createYY("gęst")), ""),
            new WordCore("rzadkość", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, new NounForms("rzadkość", null), List.of(AdjectiveForms.createI("rzadk")), ""),
            new WordCore("dum", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, new NounForms("duma", null), List.of(AdjectiveForms.createNy("dum")), ""),
            //SAME ADJECTIVE AS MALE
            new WordCore("handlark", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAi("handlark"), List.of(AdjectiveForms.createKi("handlars")), ""),
            new WordCore("rybaczk", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAi ("rybaczk"), List.of(AdjectiveForms.createKi("rybac")), ""),
            new WordCore("myśliw", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAe ("myśliw"), List.of(AdjectiveForms.createKi("myśliws")), ""),
            new WordCore("paster", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAi ("pasterk"), List.of(AdjectiveForms.createKi("pasters")), ""),
            new WordCore("kowalk", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAi ("kowalk"), List.of(AdjectiveForms.createKi("kowals")), ""),
            new WordCore("młynark", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAi ("młynark"), List.of(AdjectiveForms.createKi("młynars")), ""),
            new WordCore("elfk", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAi("elfk"), List.of(AdjectiveForms.createI("elf"), AdjectiveForms.createI("elfick")), ""),
            new WordCore("krasnoludk", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAi("krasnoludk"), List.of(AdjectiveForms.createI("krasnoludz"), AdjectiveForms.createKiCy("krasnoludz")), ""),

            //MASCULINE NON MASC
            new WordCore("smo", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createK("smo"), List.of(AdjectiveForms.createCzy("smo")), ""),
            new WordCore("las", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createEmptyY("las"), List.of(AdjectiveForms.createYY("leśn")), ""),    // new AdjectiveForms("leśny", "leśna", "leśne", "leśni", "leśne")
            new WordCore("wil", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createK("wil"), List.of(AdjectiveForms.createCzy("wil")), ""),
            new WordCore("korze", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createNie("korze"), List.of(AdjectiveForms.createNy("korzen"), AdjectiveForms.createOwy("korzeni")), ""),
            new WordCore("poto", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createK("poto"), List.of(AdjectiveForms.createCzny("poto"), AdjectiveForms.createOwy("potok"), AdjectiveForms.createNy("potocza")), ""),
            new WordCore("lis", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createEmptyY("lis"), List.of(AdjectiveForms.createI("lis"), AdjectiveForms.createNy("lisia")), ""),
            new WordCore("niedźwied", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createZZie("niedźwied"), List.of(AdjectiveForms.createI("niedźwiedz")), ""),
            new WordCore("jele", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createNie("jele"), List.of(AdjectiveForms.createI("jelen")), ""),
            new WordCore("dzik", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createEmptyI("dzik"), List.of(AdjectiveForms.createCzy("dzi")), ""),
            new WordCore("zając", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createEmptyE("zając"), List.of(AdjectiveForms.createYY("zajęcz")), ""),
            new WordCore("bóbr", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, new NounForms("bóbr",   "bobry"), List.of(AdjectiveForms.createYY("bobrz"), AdjectiveForms.createOwy("bobr")), ""),
            new WordCore("bober", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createEmptyY("bober"), List.of(AdjectiveForms.createOwy("bober")), ""),
            new WordCore("ko", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createNie("ko"), List.of(AdjectiveForms.createNski("ko")), ""),
            new WordCore("ko", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createEmptyY("kot"), List.of(AdjectiveForms.createCi("ko")), ""),
            new WordCore("kogu", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createEmptyY("kogut"), List.of(AdjectiveForms.createCi("kogu")), ""),
            new WordCore("sam", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, new NounForms("samiec", "samce"), List.of(AdjectiveForms.createCzy("sam")), ""),
            new WordCore("elf", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createEmptyY("elf"), List.of(AdjectiveForms.createI("elf"), AdjectiveForms.createI("elfick")), ""),
            new WordCore("krasnolud", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createEmptyY("krasnolud"), List.of(AdjectiveForms.createI("krasnoludz"), AdjectiveForms.createKiCy("krasnoludz")), ""),
            new WordCore("dąb", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, new NounForms("dąb", "dęby"), List.of(AdjectiveForms.createOwy("dęb")), ""),
            new WordCore("młyn", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createEmptyY("młyn"), List.of(AdjectiveForms.createKi("młyńs")), ""),
            new WordCore("dwór", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, new NounForms("dwór", "dwory"), List.of(AdjectiveForms.createKi("dwors")), ""),
            new WordCore("gród", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, new NounForms("gród", "grody"), List.of(AdjectiveForms.createKi("grodz")), ""),
            new WordCore("kamie", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createNie("kamie"), List.of(AdjectiveForms.createNy("kamien")), ""),
            new WordCore("pias", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createEk("pias"), List.of(AdjectiveForms.createOwy("piask"), AdjectiveForms.createYY("piaszczyst")), ""),
            new WordCore("dół", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, new NounForms("dół", "doły"), List.of(AdjectiveForms.createNy("dol")), ""),
            new WordCore("niż", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createEmptyE("niż"), List.of(AdjectiveForms.createKi("nis"), AdjectiveForms.createOwy("niż")), ""),
            new WordCore("wiatr", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createEmptyY("wiatr"), List.of(AdjectiveForms.createYY("wietrzn"), AdjectiveForms.createOwy("wiatr")), ""),
            new WordCore("mrok", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createEmptyI("mrok"), List.of(AdjectiveForms.createYY("mroczn")), ""),
            new WordCore("cie", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createNie("cie"), List.of(AdjectiveForms.createYY("cienist")), ""),
            new WordCore("osi", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, new NounForms("osioł", "osły"), List.of(AdjectiveForms.createIorA("ośl")), ""),
            new WordCore("płomie", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createNie("płomie"), List.of(AdjectiveForms.createYY("płomienn")), ""),
            //MASCULINE MASC
//            new WordCore("dra", WordGender.MASCULINE, PluralType.MASC_PERSONAL, NounForms.createNie("dra"), List.of(AdjectiveForms.createNski("dra"), AdjectiveForms.createNny("dra")), ""),
            new WordCore("kupi", WordGender.MASCULINE, PluralType.MASC_PERSONAL, NounForms.createIec("kup"), List.of(AdjectiveForms.createNski("dra"), AdjectiveForms.createEcki("kupi")), ""),
            new WordCore("człowiek", WordGender.MASCULINE, PluralType.MASC_PERSONAL, new NounForms("człowiek", "ludzie"), List.of(AdjectiveForms.createKi("ludz"), AdjectiveForms.createCzy("człowie")), ""),
            new WordCore("rybak", WordGender.MASCULINE, PluralType.MASC_PERSONAL, NounForms.createKCy ("ryba"), List.of(AdjectiveForms.createKi("rybac")), ""),
            new WordCore("myśliw", WordGender.MASCULINE, PluralType.MASC_PERSONAL, NounForms.createYi ("myśliw"), List.of(AdjectiveForms.createKi("myśliws")), ""),
            new WordCore("paster", WordGender.MASCULINE, PluralType.MASC_PERSONAL, NounForms.createEmptyE ("pasterz"), List.of(AdjectiveForms.createKi("pasters")), ""),
            new WordCore("kowal", WordGender.MASCULINE, PluralType.MASC_PERSONAL, NounForms.createEmptyE ("kowal"), List.of(AdjectiveForms.createKi("kowals")), ""),
            new WordCore("młyna", WordGender.MASCULINE, PluralType.MASC_PERSONAL, NounForms.createEmptyE ("młynarz"), List.of(AdjectiveForms.createKi("młynars")), ""),
            new WordCore("wędrow", WordGender.MASCULINE, PluralType.MASC_PERSONAL, NounForms.createEmptyE ("młynarz"), List.of(AdjectiveForms.createKi("młynars")), ""),

            //NON ADJECTIVE
            new WordCore("uroczysk", WordGender.NEUTRAL, PluralType.NON_MASC_PERSONAL, NounForms.createO("uroczysk"), List.of(), ""),
            new WordCore("trakt", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createEmptyY("trakt"), List.of(), ""),
            new WordCore("siedlisk", WordGender.NEUTRAL, PluralType.NON_MASC_PERSONAL, NounForms.createO("siedlisk"), List.of(), ""),
            new WordCore("siedliszcz", WordGender.NEUTRAL, PluralType.NON_MASC_PERSONAL, NounForms.createE("siedliszcz"), List.of(), ""),
            new WordCore("wież", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAe("wież"), List.of(), ""),
            new WordCore("wieżyc", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAe("wieżyc"), List.of(), ""),
            new WordCore("most", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createEmptyY("most"), List.of(), ""),
            new WordCore("chat", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("chat"), List.of(), ""),
            new WordCore("doł", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createEk("doł"), List.of(), ""),
            //NON PLURAL, NON ADJECTIVE
            new WordCore("wieś", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, new NounForms("wieś", null), List.of(), ""),
            new WordCore("wola", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, new NounForms("wola", null), List.of(), ""),
            new WordCore("wólka", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, new NounForms("wólka", null), List.of(), "")
    );

    private static List<String> singleEndings = List.of("ce", "anka", "za-e");

//    private static String[] single = {"Dupice", "Dupianka", "Zadupie", "Zarowie"};

    private static SaraveraPhonotactic phonotactic = new SaraveraPhonotactic();

    public static String generate() {
//        if (rng.nextDouble() < 0.3) {
//            return phonotactic.generateCapitalizedWord(WordType.CITY, villageSylables);
//        }

        return folkName();
    }

    private static WordCore getAdjectiveCore() {
        for (int i = 0; i < 100; i++) {
            WordCore wordCore = areas.get(rng.nextInt(areas.size()));
            if (!wordCore.getAdjectiveForms().isEmpty()) {
                return wordCore;
            }
        }
        throw new IllegalStateException("Brak właściwych przymiotników dla nazw wiosek");
    }

    private static String folkName() {
        WordCore nameCore = areas.get(rng.nextInt(areas.size()));
        if (rng.nextDouble() > 0.75) {
            String ending = singleEndings.get(rng.nextInt(singleEndings.size()));
            if (ending.contains("-")) {
                String[] split = ending.split("-");
                return WordUtils.capitalizeFully(split[0] + nameCore.getBase() + split[1]);
            } else {
                return WordUtils.capitalizeFully(nameCore.getBase() + ending);
            }
        }
        WordCore adjCore = getAdjectiveCore();
        WordCount count = rng.nextDouble() < 0.2 && nameCore.hasPlural() ? WordCount.PLURAL : WordCount.SINGULAR;
        boolean adjFirst = rng.nextDouble() > 0.25;
        String nameCoreFormatted = nameCore.getNoun(count);
        String adjective = adjCore.getAdjective(count, nameCore.getSelfGender(), nameCore.getPluralType(), rng.nextInt(adjCore.getAdjectiveForms().size()));
        if (adjFirst) {
            return WordUtils.capitalizeFully(adjective + " " + nameCoreFormatted);
        } else {
            return WordUtils.capitalizeFully(nameCoreFormatted + " " + adjective);
        }
    }

    /* TODO potencjalne pomysły
Bór
Gaj
Krzak
Krzew
Drzewo
Strumień
Potok
Staw
Jezioro
Bagno
Mokradło
Trzęsawisko
Źródło
Bród
Próg

Pan
Chłop

Wędrowiec
Strażnik
Kmiec
Sołtys



    * */

}
