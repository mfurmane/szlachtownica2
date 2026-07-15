package priv.mfurmane.szlachtownica.model;

import org.apache.commons.text.WordUtils;
import priv.mfurmane.szlachtownica.engine.naming.model.*;
import priv.mfurmane.szlachtownica.engine.naming.saravera.SaraveraPhonotactic;

import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class VillageNameGenerator {

    public static final Map<Integer, Double> villageSylables = Map.of(1, 8.0, 2, 2.0);
    public static final ThreadLocalRandom rng = ThreadLocalRandom.current();

    // TODO Trzeba to przepuścić przez Klaudię, bo jak się okazuje kurewsko niepoprawne bywają formy przymiotników dla liczby mnogiej
    // prompt: Przejrzyj mi proszę klasę VillageNameGenerator pod kątem poprawności tworzonych form w języku polskim. WordCore na ten moment zawiera trzon słowa (póki co nie używany, base), formy rzeczownika i listę form przymiotnika. Zrobiłem pierwsze kroki w stornę nazw w rodzaju żabianka, zażabie, zadupie, wierzbianka, ale poddałem się, gdy się zorientowałem, że będzie to dużo trudniejsze, niż zakładałem i może wymagać czegoś podobnego do przymiotników. Temu miał służyć parametr singleForm. Wprowadź proszę takie zmiany w te definicje, żeby formy były zgodne ze słownikiem języka polskiego
    private static List<WordCore> areas = List.of(
            //NEUTRAL
            new WordCore("wzgórz", WordCategory.TERRAIN, WordGender.NEUTRAL, PluralType.NON_MASC_PERSONAL, NounForms.createE("wzgórz"), List.of(AdjectiveForms.createOwy("wzgórz")), "Wzgórzyca;Wzgórek"),
            new WordCore("sioł", WordCategory.STRUCTURE, WordGender.NEUTRAL, PluralType.NON_MASC_PERSONAL, NounForms.createO("sioł"), List.of(new AdjectiveForms("sielski", "sielska", "sielskie", "sielscy", "sielskie")), "Sielanka"),
            new WordCore("grodzisk", WordCategory.STRUCTURE, WordGender.NEUTRAL, PluralType.NON_MASC_PERSONAL, NounForms.createO("grodzisk"), List.of(AdjectiveForms.createOwy("grodzisk")), "Grodzisk"),
            new WordCore("błot", WordCategory.TERRAIN, WordGender.NEUTRAL, PluralType.NON_MASC_PERSONAL, NounForms.createO("błot"), List.of(AdjectiveForms.createYI("błotn"), AdjectiveForms.createSty("błotni")), "Zabłocie;Błotnica;Podbłocie"),
            new WordCore("pol", WordCategory.TERRAIN, WordGender.NEUTRAL, PluralType.NON_MASC_PERSONAL, NounForms.createE("pol"), List.of(AdjectiveForms.createYI("poln")), "Zapole;Podpole;Zapolice"),
            new WordCore("zacisz", WordCategory.TERRAIN, WordGender.NEUTRAL, PluralType.NON_MASC_PERSONAL, NounForms.createE("zacisz"), List.of(AdjectiveForms.createYI("zaciszn")), "Zacisze"),
            new WordCore("słońc", WordCategory.TERRAIN, WordGender.NEUTRAL, PluralType.NON_MASC_PERSONAL, NounForms.createE("słońc"), List.of(AdjectiveForms.createYI("słoneczn")), ""),
            new WordCore("zarośl", WordCategory.PLANT, WordGender.NEUTRAL, PluralType.NON_MASC_PERSONAL, NounForms.createE("zarośl"), List.of(new AdjectiveForms("zarośnięty", "zarośnięta", "zarośnięte", "zarośnięci", "zarośnięte"), new AdjectiveForms("zarosły", "zarosła", "zarosłe", "zarośli", "zarosłe")), "Zarośle"),
            //FEMININE
            new WordCore("brzoz", WordCategory.PLANT, WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("brzoz"), List.of(AdjectiveForms.createOwy("brzoz")), "Zabrzozie;Podbrzozie;Brzózki;Brzozów;Brzozówka"),
            new WordCore("wierzb", WordCategory.PLANT, WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("wierzb"), List.of(AdjectiveForms.createOwy("wierzb")), "Wierzbie;Zawierzbie;Wierzbica;Wierzbianka"),
            new WordCore("rzek", WordCategory.TERRAIN, WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAi("rzek"), List.of(AdjectiveForms.createCzny("rze")), "Zarzecze;Nadrzecze;Podrzecze;Międzyrzecze"),
            new WordCore("sosn", WordCategory.PLANT, WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("sosn"), List.of(AdjectiveForms.createOwy("sosn")), "Sośnica;Sosnowica;Sosnówka;Podsośnie;Zasośnie"),
            new WordCore("topol", WordCategory.PLANT, WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAe("topol"), List.of(AdjectiveForms.createOwy("topol"), AdjectiveForms.createNy("topol")), "Topolno;Topolany;Topólka"),
            new WordCore("żab", WordCategory.ANIMAL, WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("żab"), List.of(AdjectiveForms.createI("żab")), "Żabno;Żabnica;Żabice;Żabianka;Zażabie"),
            new WordCore("skał", WordCategory.TERRAIN, WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("skał"), List.of(AdjectiveForms.createNy("skal")), "Zaskale;Podskale;Skałka"),
            new WordCore("sarn", WordCategory.ANIMAL, WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("sarn"), List.of(AdjectiveForms.createNi("sar")), "Sarnów;Sarnowa;Sarnówka"),
            new WordCore("kun", WordCategory.ANIMAL, WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("kun"), List.of(AdjectiveForms.createNi("ku")), "Kunów;Kunice"),
            new WordCore("krow", WordCategory.ANIMAL, WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("krow"), List.of(AdjectiveForms.createI("krow")), "Krowica;Krowianki"),
            new WordCore("koz", WordCategory.ANIMAL, WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("koz"), List.of(AdjectiveForms.createI("koz")), "Kozy;Koźle;Kozice;Koźlanka"),
            new WordCore("owc", WordCategory.ANIMAL, WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAe("owc"), List.of(AdjectiveForms.createCzy("ow")), "Owczary;Owczarnia"),
            new WordCore("kaczk", WordCategory.ANIMAL, WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAi("kaczk"), List.of(AdjectiveForms.createYY("kacz")), "Kaczory;Kaczanica"),
            new WordCore("samic", WordCategory.ANIMAL, WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAe("samic"), List.of(AdjectiveForms.createYY("samicz")), "Samiczki;Samica"),
            new WordCore("ryb", WordCategory.ANIMAL, WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("ryb"), List.of(AdjectiveForms.createI("ryb")), "Rybno;Rybnica;Zarybie;Międzyrybie"),
            new WordCore("stodoł", WordCategory.STRUCTURE, WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("stodoł"), List.of(AdjectiveForms.createNy("stodol")), "Stodoły;Zastodole"),
            new WordCore("obor", WordCategory.STRUCTURE, WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("obor"), List.of(AdjectiveForms.createNy("obor")), "Obory;Oborki;Oborniki"),
            new WordCore("kuźni", WordCategory.STRUCTURE, WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAe("kuźni"), List.of(AdjectiveForms.createNy("kuźnia")), "Kuźnice;Kuźniczka;Kuźnica"),
            new WordCore("karczm", WordCategory.STRUCTURE, WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("karczm"), List.of(AdjectiveForms.createNy("karczem")), "Karczmiska;Zakarczmie;Karczmianka"),
            new WordCore("przysta", WordCategory.STRUCTURE, WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createNie("przysta"), List.of(AdjectiveForms.createOwy("przystani")), "Przystań"),
            new WordCore("staroś", WordCategory.QUALITY, WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createCi("staroś"), List.of(AdjectiveForms.createRy("sta")), "Starość;Starzyzna"),
            new WordCore("nowoś", WordCategory.QUALITY, WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createCi("nowoś"), List.of(AdjectiveForms.createYI("now")), "Nowość;Nowina"),
            new WordCore("wielko", WordCategory.QUALITY, WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createCi("wielkoś"), List.of(AdjectiveForms.createKi("wiel")), "Wielkość"),
            new WordCore("łąk", WordCategory.TERRAIN, WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAi("łąk"), List.of(AdjectiveForms.createOwy("łąk"), AdjectiveForms.createNy("łącz")), "Łąka;Łęki;Łączki;Podłęże"),
            new WordCore("gór", WordCategory.TERRAIN, WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("gór"), List.of(AdjectiveForms.createKi("górs"), AdjectiveForms.createNy("gór")), "Zagórze;Podgórze;Górki;Nagórze;Górzyca"),
            new WordCore("wysokoś", WordCategory.QUALITY, WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createCi("wysokoś"), List.of(AdjectiveForms.createKi("wyso"), AdjectiveForms.createOwy("wysokości")), ""),
            new WordCore("głębi", WordCategory.TERRAIN, WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAe("głębi"), List.of(AdjectiveForms.createKi("głębo"), AdjectiveForms.createOwy("głębin")), "Głębia;Zagłębie"),
            new WordCore("płycizn", WordCategory.TERRAIN, WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("płycizn"), List.of(AdjectiveForms.createKi("płyt")), "Płycizna"),
            new WordCore("wod", WordCategory.TERRAIN, WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("wod"), List.of(AdjectiveForms.createYI("wodn")), "Zawodzie;Zawada;Podwodzie"),
            new WordCore("dup", WordCategory.VULGAR, WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("dup"), List.of(AdjectiveForms.createYI("dupn"), AdjectiveForms.createYI("dupczan"), AdjectiveForms.createSty("dupia")), "Zadupie;Dupice;Dupianka;Dupczyki"),
            new WordCore("kurw", WordCategory.VULGAR, WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("kurw"), List.of(AdjectiveForms.createI("kurw"), AdjectiveForms.createYI("kurwiczn"), AdjectiveForms.createSty("wykurwi"), AdjectiveForms.createOny("skurwi")), "Zakurwie;Kurwice"),
//            new WordCore("wod", WordCategory.TERRAIN, WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("wod"), List.of(AdjectiveForms.createY("wodn")), ""),
            //NO PLURAL
            new WordCore("małoś", WordCategory.QUALITY, WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, new NounForms("małość", null), List.of(new AdjectiveForms("mały", "mała", "małe", "mali", "małe"), AdjectiveForms.createYI("małostkow")), "Maleństwo"),
            new WordCore("ziele", WordCategory.PLANT, WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, new NounForms("zieleń", null), List.of(AdjectiveForms.createYI("zielon")), "Zielonki;Zielenica"),
            new WordCore("suchoś", WordCategory.QUALITY, WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, new NounForms("suchość", null), List.of(AdjectiveForms.createChy("su")), "Susza;Suchoty;Suchotnica;Suszanka"),
            new WordCore("mokroś", WordCategory.QUALITY, WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, new NounForms("mokrość", null), List.of(AdjectiveForms.createRy("mok"), AdjectiveForms.createOny("przemocz")), "Mokrzyska;Moczanka;Moczyce;Moczary"),
            new WordCore("susz", WordCategory.QUALITY, WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, new NounForms("susza", null), List.of(AdjectiveForms.createOny("przesusz")), ""),
            new WordCore("cisz", WordCategory.QUALITY, WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, new NounForms("cisza", null), List.of(AdjectiveForms.createChy("ci")), "Cisza;Zacisze;Ciszówka;Cichawica"),
            new WordCore("dzicz", WordCategory.QUALITY, WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, new NounForms("dzicz", null), List.of(AdjectiveForms.createKi("dzi")), "Dzicz;Dzikość;Dziczyzna;Zadzicze"),
            new WordCore("gęstwa", WordCategory.PLANT, WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, new NounForms("gęstwa", null), List.of(AdjectiveForms.createSty("gę")), "Gęstwina"),
            new WordCore("rzadkość", WordCategory.QUALITY, WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, new NounForms("rzadkość", null), List.of(AdjectiveForms.createKi("rzad")), "Rzadkość"),
            new WordCore("dum", WordCategory.QUALITY, WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, new NounForms("duma", null), List.of(AdjectiveForms.createNy("dum")), "Dumka;Duma;Dumki"),
            //SAME ADJECTIVE AS MALE
            new WordCore("handlark", WordCategory.PROFESSION, WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAi("handlark"), List.of(AdjectiveForms.createKi("handlars")), "Handelek;Handlowisko;Handlownica"),
            new WordCore("rybaczk", WordCategory.PROFESSION, WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAi ("rybaczk"), List.of(AdjectiveForms.createKi("rybac")), "Rybianka;Rybaczka;Rybownica"),
            new WordCore("myśliw", WordCategory.PROFESSION, WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAe ("myśliw"), List.of(AdjectiveForms.createKi("myśliws")), ""),
            new WordCore("paster", WordCategory.PROFESSION, WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAi ("pasterk"), List.of(AdjectiveForms.createKi("pasters")), ""),
            new WordCore("kowalk", WordCategory.PROFESSION, WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAi ("kowalk"), List.of(AdjectiveForms.createKi("kowals")), "Kowalica;Kowalnica;Kowalówka"),
            new WordCore("młynark", WordCategory.PROFESSION, WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAi ("młynark"), List.of(AdjectiveForms.createKi("młynars")), "Młynianka;Młynówka"),
            new WordCore("elfk", WordCategory.BEING, WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAi("elfk"), List.of(AdjectiveForms.createI("elf"), AdjectiveForms.createKi("elfic")), ""),
            new WordCore("krasnoludk", WordCategory.BEING, WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAi("krasnoludk"), List.of(AdjectiveForms.createKiCy("krasnoludz")), ""),

            //MASCULINE NON MASC
            new WordCore("smo", WordCategory.BEING, WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createK("smo"), List.of(AdjectiveForms.createCzy("smo")), "Smoczanka;Smoczyce;Smokownica"),
            new WordCore("las", WordCategory.TERRAIN, WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createEmptyY("las"), List.of(AdjectiveForms.createYI("leśn")), "Zalesie;Podlesie;Lasek;Laski;Leśnica;Lesianka;Leśniczówka;Laskownica"),
            new WordCore("wil", WordCategory.ANIMAL, WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createK("wil"), List.of(AdjectiveForms.createCzy("wil")), "Wilków;Wilcze;Wilkowice;Wilczyca;Wilkownica;Wilczanka;Zawilcze"),
            new WordCore("korze", WordCategory.PLANT, WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createNie("korze"), List.of(AdjectiveForms.createNy("korzen"), AdjectiveForms.createOwy("korzeni")), "Korzeniów;"),
            new WordCore("poto", WordCategory.TERRAIN, WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createK("poto"), List.of(AdjectiveForms.createCzny("poto"), AdjectiveForms.createOwy("potok")), "Potok;Zapotocze;Potoczek"),
            new WordCore("lis", WordCategory.ANIMAL, WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createEmptyY("lis"), List.of(AdjectiveForms.createI("lis")), "Lisów;Lisie;Liski"),
            new WordCore("niedźwied", WordCategory.ANIMAL, WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createZZie("niedźwied"), List.of(AdjectiveForms.createI("niedźwiedz")), "Niedźwiedź;Niedźwiada;Niedźwiedzie"),
            new WordCore("jele", WordCategory.ANIMAL, WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createNie("jele"), List.of(AdjectiveForms.createI("jelen")), "Jeleń;Jelenie;Jeleniów"),
            new WordCore("dzik", WordCategory.ANIMAL, WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createEmptyI("dzik"), List.of(AdjectiveForms.createCzy("dzi")), "Dzików;Dzikowo;Dzikowiec"),
            new WordCore("zając", WordCategory.ANIMAL, WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createEmptyE("zając"), List.of(AdjectiveForms.createYY("zajęcz")), "Zajączki;Zajączków;Zajączkowo"),
            new WordCore("bóbr", WordCategory.ANIMAL, WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, new NounForms("bóbr",   "bobry"), List.of(AdjectiveForms.createYY("bobrz"), AdjectiveForms.createOwy("bobr")), "Bobrowniki;Bobrownica;Bobrowo;Bobrzanka"),
            new WordCore("bober", WordCategory.ANIMAL, WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createEmptyY("bober"), List.of(AdjectiveForms.createOwy("bober")), ""),
            new WordCore("ko", WordCategory.ANIMAL, WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createNie("ko"), List.of(AdjectiveForms.createNski("ko")), "Końskie;Koniuszki"),
            new WordCore("ko", WordCategory.ANIMAL, WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createEmptyY("kot"), List.of(AdjectiveForms.createCi("ko")), "Kotów;Kocie;Kotki"),
            new WordCore("kogu", WordCategory.ANIMAL, WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createEmptyY("kogut"), List.of(AdjectiveForms.createCi("kogu")), ""),
            new WordCore("sam", WordCategory.ANIMAL, WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, new NounForms("samiec", "samce"), List.of(AdjectiveForms.createCzy("sam")), ""),
            new WordCore("elf", WordCategory.BEING, WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createEmptyY("elf"), List.of(AdjectiveForms.createI("elf"), AdjectiveForms.createKi("elfic")), "Elfowo;Elfów"),
            new WordCore("krasnolud", WordCategory.BEING, WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createEmptyY("krasnolud"), List.of(AdjectiveForms.createKiCy("krasnoludz")), "Krasnoludki;Krasnoludów"),
            new WordCore("dąb", WordCategory.PLANT, WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, new NounForms("dąb", "dęby"), List.of(AdjectiveForms.createOwy("dęb")), "Dąbrowa;Dąbie;Dębina;Dębno;Dębica"),
            new WordCore("młyn", WordCategory.STRUCTURE, WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createEmptyY("młyn"), List.of(AdjectiveForms.createKi("młyńs")), "Młyny;Zamłynie;Młynary"),
            new WordCore("dwór", WordCategory.STRUCTURE, WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, new NounForms("dwór", "dwory"), List.of(AdjectiveForms.createKi("dwors")), "Zadworze;Dwory;Poddworze"),
            new WordCore("gród", WordCategory.STRUCTURE, WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, new NounForms("gród", "grody"), List.of(AdjectiveForms.createKi("grodz")), "Podgrodzie;Nowogród;Grodziec;Grodzisko"),
            new WordCore("kamie", WordCategory.TERRAIN, WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createNie("kamie"), List.of(AdjectiveForms.createNy("kamien")), "Kamień;Kamienica;Podkamień"),
            new WordCore("pias", WordCategory.TERRAIN, WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createEk("pias"), List.of(AdjectiveForms.createOwy("piask"), AdjectiveForms.createSty("piaszczy")), "Piaski;Piaseczno"),
            new WordCore("dół", WordCategory.TERRAIN, WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, new NounForms("dół", "doły"), List.of(AdjectiveForms.createNy("dol")), "Zadole;Podole;Nadole;Doły"),
            new WordCore("niż", WordCategory.TERRAIN, WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createEmptyE("niż"), List.of(AdjectiveForms.createKi("nis"), AdjectiveForms.createOwy("niż")), ""),
            new WordCore("wiatr", WordCategory.TERRAIN, WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createEmptyY("wiatr"), List.of(AdjectiveForms.createYI("wietrzn"), AdjectiveForms.createOwy("wiatr")), "Wiatrowo;Wietrzno"),
            new WordCore("mrok", WordCategory.TERRAIN, WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createEmptyI("mrok"), List.of(AdjectiveForms.createYI("mroczn")), "Mroczków;Mroki"),
            new WordCore("cie", WordCategory.TERRAIN, WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createNie("cie"), List.of(AdjectiveForms.createSty("cieni")), ""),
            new WordCore("osi", WordCategory.ANIMAL, WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, new NounForms("osioł", "osły"), List.of(AdjectiveForms.createIorA("ośl")), ""),
            new WordCore("płomie", WordCategory.TERRAIN, WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createNie("płomie"), List.of(AdjectiveForms.createYI("płomienn")), ""),
            //MASCULINE MASC
//            new WordCore("dra", WordCategory.TERRAIN, WordGender.MASCULINE, PluralType.MASC_PERSONAL, NounForms.createNie("dra"), List.of(AdjectiveForms.createNski("dra"), AdjectiveForms.createNny("dra")), ""),
            new WordCore("kupi", WordCategory.PROFESSION, WordGender.MASCULINE, PluralType.MASC_PERSONAL, NounForms.createIec("kup"), List.of(AdjectiveForms.createNski("dra"), AdjectiveForms.createEcki("kupi")), ""),
            new WordCore("człowiek", WordCategory.BEING, WordGender.MASCULINE, PluralType.MASC_PERSONAL, new NounForms("człowiek", "ludzie"), List.of(AdjectiveForms.createKi("ludz"), AdjectiveForms.createCzy("człowie")), ""),
            new WordCore("rybak", WordCategory.PROFESSION, WordGender.MASCULINE, PluralType.MASC_PERSONAL, NounForms.createKCy ("ryba"), List.of(AdjectiveForms.createKi("rybac")), "Rybaki;Rybaków"),
            new WordCore("myśliw", WordCategory.PROFESSION, WordGender.MASCULINE, PluralType.MASC_PERSONAL, NounForms.createYi ("myśliw"), List.of(AdjectiveForms.createKi("myśliws")), ""),
            new WordCore("paster", WordCategory.PROFESSION, WordGender.MASCULINE, PluralType.MASC_PERSONAL, NounForms.createEmptyE ("pasterz"), List.of(AdjectiveForms.createKi("pasters")), "Pasterka"),
            new WordCore("kowal", WordCategory.PROFESSION, WordGender.MASCULINE, PluralType.MASC_PERSONAL, NounForms.createEmptyE ("kowal"), List.of(AdjectiveForms.createKi("kowals")), "Kowale;Kowalewo;Kowala"),
            new WordCore("młyna", WordCategory.PROFESSION, WordGender.MASCULINE, PluralType.MASC_PERSONAL, NounForms.createEmptyE ("młynarz"), List.of(AdjectiveForms.createKi("młynars")), ""),
            new WordCore("wędrow", WordCategory.PROFESSION, WordGender.MASCULINE, PluralType.MASC_PERSONAL, new NounForms("wędrowiec", "wędrowcy"), List.of(AdjectiveForms.createNy("wędrow")), ""),

            //NON ADJECTIVE
            new WordCore("uroczysk", WordCategory.STRUCTURE, WordGender.NEUTRAL, PluralType.NON_MASC_PERSONAL, NounForms.createO("uroczysk"), List.of(), ""),
            new WordCore("trakt", WordCategory.STRUCTURE, WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createEmptyY("trakt"), List.of(), ""),
            new WordCore("siedlisk", WordCategory.STRUCTURE, WordGender.NEUTRAL, PluralType.NON_MASC_PERSONAL, NounForms.createO("siedlisk"), List.of(), "Siedliska"),
            new WordCore("siedliszcz", WordCategory.STRUCTURE, WordGender.NEUTRAL, PluralType.NON_MASC_PERSONAL, NounForms.createE("siedliszcz"), List.of(), ""),
            new WordCore("wież", WordCategory.STRUCTURE, WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAe("wież"), List.of(), ""),
            new WordCore("wieżyc", WordCategory.STRUCTURE, WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAe("wieżyc"), List.of(), ""),
            new WordCore("most", WordCategory.STRUCTURE, WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createEmptyY("most"), List.of(), "Mosty;Zamoście;Mościska"),
            new WordCore("chat", WordCategory.STRUCTURE, WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("chat"), List.of(), ""),
            new WordCore("doł", WordCategory.TERRAIN, WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createEk("doł"), List.of(), ""),
            //NON PLURAL, NON ADJECTIVE
            new WordCore("wieś", WordCategory.STRUCTURE, WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, new NounForms("wieś", null), List.of(), ""),
            new WordCore("wola", WordCategory.STRUCTURE, WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, new NounForms("wola", null), List.of(), "Wola;Wólka;Wolica"),
            new WordCore("wólka", WordCategory.STRUCTURE, WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, new NounForms("wólka", null), List.of(), "Wólka"),

            // ===================== NOWE SŁOWA =====================
            // --- TERREN: las/woda/ukształtowanie (kategoria domyślna TERRAIN) ---
            new WordCore("bór", WordCategory.TERRAIN, WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, new NounForms("bór", "bory"), List.of(AdjectiveForms.createOwy("bor")), "Bór;Borek;Borowo;Borowina"),
            new WordCore("gaj", WordCategory.TERRAIN, WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createEmptyE("gaj"), List.of(AdjectiveForms.createOwy("gaj")), "Gaj;Gaje;Gajów;Gajówka;Zagaje"),
            new WordCore("staw", WordCategory.TERRAIN, WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createEmptyY("staw"), List.of(AdjectiveForms.createOwy("staw")), "Stawy;Zastawie;Podstawie;Stawiszcze"),
            new WordCore("jezior", WordCategory.TERRAIN, WordGender.NEUTRAL, PluralType.NON_MASC_PERSONAL, NounForms.createO("jezior"), List.of(AdjectiveForms.createNy("jezior"), AdjectiveForms.createOwy("jezior")), "Jeziorko;Jeziorany;Zajezierze;Jeziorna"),
            new WordCore("strumie", WordCategory.TERRAIN, WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createNie("strumie"), List.of(AdjectiveForms.createNy("strumien")), "Strumień;Strumiany;Strumienno"),
            new WordCore("bagn", WordCategory.TERRAIN, WordGender.NEUTRAL, PluralType.NON_MASC_PERSONAL, NounForms.createO("bagn"), List.of(AdjectiveForms.createNy("bagien"), AdjectiveForms.createSty("bagni")), "Bagno;Bagienko;Zabagnie"),
            new WordCore("mokradł", WordCategory.TERRAIN, WordGender.NEUTRAL, PluralType.NON_MASC_PERSONAL, NounForms.createO("mokradł"), List.of(), "Mokradła;Zamokradle"),
            new WordCore("źródł", WordCategory.TERRAIN, WordGender.NEUTRAL, PluralType.NON_MASC_PERSONAL, NounForms.createO("źródł"), List.of(AdjectiveForms.createNy("źródla")), "Źródła;Zaźródle;Źródłowa"),
            new WordCore("bród", WordCategory.TERRAIN, WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, new NounForms("bród", "brody"), List.of(AdjectiveForms.createOwy("brod")), "Bród;Brody;Zabrodzie;Podbrodzie"),
            new WordCore("dolin", WordCategory.TERRAIN, WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("dolin"), List.of(AdjectiveForms.createNy("dolin"), AdjectiveForms.createOwy("dolin")), "Dolina;Doliny;Zadolinie"),
            new WordCore("polan", WordCategory.TERRAIN, WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("polan"), List.of(), "Polana;Polany;Polanka;Polanica"),
            new WordCore("przesiek", WordCategory.TERRAIN, WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAi("przesiek"), List.of(), "Przesieka;Przesieki"),
            new WordCore("wydm", WordCategory.TERRAIN, WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("wydm"), List.of(AdjectiveForms.createOwy("wydm")), "Wydmy;Wydminy"),
            new WordCore("mgł", WordCategory.TERRAIN, WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("mgł"), List.of(AdjectiveForms.createSty("mgli")), "Mglice;Zamgle"),
            new WordCore("przełęcz", WordCategory.TERRAIN, WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, new NounForms("przełęcz", "przełęcze"), List.of(AdjectiveForms.createOwy("przełęcz")), "Przełęcz;Przełęcze"),
            new WordCore("urwisk", WordCategory.TERRAIN, WordGender.NEUTRAL, PluralType.NON_MASC_PERSONAL, NounForms.createO("urwisk"), List.of(), "Urwisko;Urwiska"),
            new WordCore("wąwóz", WordCategory.TERRAIN, WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, new NounForms("wąwóz", "wąwozy"), List.of(), "Wąwóz;Wąwozy"),
            // --- ROŚLINY (PLANT) ---
            new WordCore("drzew", WordCategory.PLANT, WordGender.NEUTRAL, PluralType.NON_MASC_PERSONAL, NounForms.createO("drzew"), List.of(AdjectiveForms.createNy("drzew")), "Drzewce;Zadrzewie"),
            new WordCore("jodł", WordCategory.PLANT, WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("jodł"), List.of(AdjectiveForms.createOwy("jodł")), "Jodłowa;Jodłów;Jodłówka;Zajodle"),
            new WordCore("świerk", WordCategory.PLANT, WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createEmptyI("świerk"), List.of(AdjectiveForms.createOwy("świerk")), "Świerkle;Świerklany;Świerczyna;Świerkowa"),
            new WordCore("buk", WordCategory.PLANT, WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createEmptyI("buk"), List.of(AdjectiveForms.createOwy("buk")), "Buków;Bukowo;Bukowa;Bukowina"),
            new WordCore("lip", WordCategory.PLANT, WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("lip"), List.of(AdjectiveForms.createOwy("lip")), "Lipno;Lipnica;Lipiny;Lipowa;Lipówka;Zalipie"),
            new WordCore("olch", WordCategory.PLANT, WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("olch"), List.of(AdjectiveForms.createOwy("olch")), "Olchowa;Olchowiec;Olszyny;Olszany"),
            new WordCore("wiąz", WordCategory.PLANT, WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createEmptyY("wiąz"), List.of(AdjectiveForms.createOwy("wiąz")), "Wiązów;Wiązownica;Wiązowna"),
            new WordCore("jesion", WordCategory.PLANT, WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createEmptyY("jesion"), List.of(AdjectiveForms.createOwy("jesion")), "Jesionowa;Jesionka"),
            new WordCore("wrzos", WordCategory.PLANT, WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createEmptyY("wrzos"), List.of(AdjectiveForms.createOwy("wrzos")), "Wrzosowa;Wrzosowo"),
            new WordCore("malin", WordCategory.PLANT, WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("malin"), List.of(AdjectiveForms.createOwy("malin")), "Malinowo;Malinów;Malinówka;Maliniec"),
            new WordCore("jagod", WordCategory.PLANT, WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("jagod"), List.of(AdjectiveForms.createOwy("jagod")), "Jagodne;Jagodno;Jagodnik"),
            new WordCore("grzyb", WordCategory.PLANT, WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createEmptyY("grzyb"), List.of(AdjectiveForms.createOwy("grzyb")), "Grzybowo;Grzybów;Grzybno"),
            new WordCore("paproc", WordCategory.PLANT, WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, new NounForms("paproć", "paprocie"), List.of(), "Paprotnia;Paprocany;Paprotki"),
            new WordCore("traw", WordCategory.PLANT, WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("traw"), List.of(AdjectiveForms.createSty("trawia")), "Trawniki;Zatrawie"),
            // --- ZWIERZĘTA (ANIMAL) ---
            new WordCore("jastrz", WordCategory.ANIMAL, WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, new NounForms("jastrząb", "jastrzębie"), List.of(AdjectiveForms.createI("jastrzęb")), "Jastrzębie;Jastrząb;Jastrzębia"),
            new WordCore("sokoł", WordCategory.ANIMAL, WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, new NounForms("sokół", "sokoły"), List.of(AdjectiveForms.createIorA("sokol")), "Sokołów;Sokoły;Sokolniki"),
            new WordCore("orl", WordCategory.ANIMAL, WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, new NounForms("orzeł", "orły"), List.of(AdjectiveForms.createIorA("orl")), "Orłowo;Orły;Orlik;Orlica"),
            new WordCore("wron", WordCategory.ANIMAL, WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("wron"), List.of(AdjectiveForms.createNi("wro")), "Wronki;Wronów;Wronowo"),
            new WordCore("sow", WordCategory.ANIMAL, WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("sow"), List.of(AdjectiveForms.createI("sow")), "Sowia;Sowin;Sowiniec"),
            new WordCore("bocian", WordCategory.ANIMAL, WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createEmptyY("bocian"), List.of(AdjectiveForms.createI("bocian")), "Bocianów;Bociany;Bocianowo"),
            new WordCore("żuraw", WordCategory.ANIMAL, WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, new NounForms("żuraw", "żurawie"), List.of(AdjectiveForms.createI("żuraw")), "Żurawie;Żurawica;Żurawno;Żurawiec"),
            new WordCore("węż", WordCategory.ANIMAL, WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, new NounForms("wąż", "węże"), List.of(AdjectiveForms.createOwy("węż")), "Węże;Wężowa;Zawężie"),
            new WordCore("jeż", WordCategory.ANIMAL, WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createEmptyE("jeż"), List.of(AdjectiveForms.createOwy("jeż")), "Jeże;Jeżów;Jeżowo"),
            new WordCore("wiewiór", WordCategory.ANIMAL, WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAi("wiewiórk"), List.of(AdjectiveForms.createCzy("wiewiór")), "Wiewiórki;Wiewiórów"),
            new WordCore("borsuk", WordCategory.ANIMAL, WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createEmptyI("borsuk"), List.of(AdjectiveForms.createCzy("borsu")), "Borsuki;Borsuków"),
            new WordCore("łoś", WordCategory.ANIMAL, WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, new NounForms("łoś", "łosie"), List.of(AdjectiveForms.createI("łos")), "Łosie;Łosiów;Łosina"),
            new WordCore("tur", WordCategory.ANIMAL, WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createEmptyY("tur"), List.of(AdjectiveForms.createYY("turz")), "Turze;Turza;Turów;Turowo"),
            new WordCore("żubr", WordCategory.ANIMAL, WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createEmptyY("żubr"), List.of(AdjectiveForms.createYY("żubrz")), "Żubry;Żubrza;Żubrów;Żubrowo"),
            new WordCore("ryś", WordCategory.ANIMAL, WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, new NounForms("ryś", "rysie"), List.of(AdjectiveForms.createI("rys")), "Rysie;Rysiów;Rysin"),
            new WordCore("kruk", WordCategory.ANIMAL, WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createEmptyI("kruk"), List.of(AdjectiveForms.createCzy("kru")), "Kruki;Kruków;Krukowo"),
            new WordCore("gołęb", WordCategory.ANIMAL, WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, new NounForms("gołąb", "gołębie"), List.of(AdjectiveForms.createI("gołęb")), "Gołębie;Gołębiew;Gołębiów"),
            new WordCore("słowik", WordCategory.ANIMAL, WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createEmptyI("słowik"), List.of(AdjectiveForms.createCzy("słowi")), "Słowiki;Słowików;Słowikowo"),
            new WordCore("pszczoł", WordCategory.ANIMAL, WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("pszczoł"), List.of(AdjectiveForms.createIorA("pszczel")), "Pszczółki;Pszczew;Pszczelnik"),
            new WordCore("czapl", WordCategory.ANIMAL, WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAe("czapl"), List.of(AdjectiveForms.createIorA("czapl")), "Czaple;Czaplinek;Czaplin"),
            // --- ISTOTY (BEING) ---
            new WordCore("czart", WordCategory.BEING, WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createEmptyY("czart"), List.of(AdjectiveForms.createI("czar")), "Czartów;Czartowo;Czartoria"),
            new WordCore("diab", WordCategory.BEING, WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, new NounForms("diabeł", "diabły"), List.of(AdjectiveForms.createKi("diabels")), "Diablak;Diabłowo"),
            new WordCore("duch", WordCategory.BEING, WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createEmptyY("duch"), List.of(AdjectiveForms.createOwy("duch")), "Duchowo;Duchów;Duchnów"),
            new WordCore("olbrzym", WordCategory.BEING, WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createEmptyY("olbrzym"), List.of(AdjectiveForms.createI("olbrzym")), "Olbrzymowo"),
            new WordCore("wilkołak", WordCategory.BEING, WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createEmptyI("wilkołak"), List.of(AdjectiveForms.createCzy("wilkoła")), "Wilkołaki"),
            new WordCore("upiór", WordCategory.BEING, WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, new NounForms("upiór", "upiory"), List.of(AdjectiveForms.createNy("upior")), "Upiory"),
            new WordCore("widm", WordCategory.BEING, WordGender.NEUTRAL, PluralType.NON_MASC_PERSONAL, NounForms.createO("widm"), List.of(AdjectiveForms.createOwy("widm")), "Widma"),
            // --- ZAWODY / LUDZIE (PROFESSION, męskoosobowe) ---
            new WordCore("pan", WordCategory.PROFESSION, WordGender.MASCULINE, PluralType.MASC_PERSONAL, new NounForms("pan", "panowie"), List.of(AdjectiveForms.createNski("pa")), "Panów;Panowo;Pańków"),
            new WordCore("chłop", WordCategory.PROFESSION, WordGender.MASCULINE, PluralType.MASC_PERSONAL, new NounForms("chłop", "chłopi"), List.of(AdjectiveForms.createKi("chłops")), "Chłopy;Chłopice;Chłopków"),
            new WordCore("strażnik", WordCategory.PROFESSION, WordGender.MASCULINE, PluralType.MASC_PERSONAL, NounForms.createKCy("strażni"), List.of(AdjectiveForms.createCzy("strażni")), "Strażów;Strażnica"),
            new WordCore("sołtys", WordCategory.PROFESSION, WordGender.MASCULINE, PluralType.MASC_PERSONAL, new NounForms("sołtys", "sołtysi"), List.of(AdjectiveForms.createI("sołtys")), "Sołtysy;Sołtysów"),
            new WordCore("kmie", WordCategory.PROFESSION, WordGender.MASCULINE, PluralType.MASC_PERSONAL, new NounForms("kmieć", "kmiecie"), List.of(AdjectiveForms.createCy("kmie")), "Kmiecie;Kmiecin"),
            new WordCore("rzeźnik", WordCategory.PROFESSION, WordGender.MASCULINE, PluralType.MASC_PERSONAL, NounForms.createKCy("rzeźni"), List.of(AdjectiveForms.createKi("rzeźnic")), "Rzeźnia;Rzeźniki"),
            new WordCore("szewc", WordCategory.PROFESSION, WordGender.MASCULINE, PluralType.MASC_PERSONAL, new NounForms("szewc", "szewcy"), List.of(AdjectiveForms.createKi("szews")), "Szewce;Szewna"),
            new WordCore("garncar", WordCategory.PROFESSION, WordGender.MASCULINE, PluralType.MASC_PERSONAL, NounForms.createEmptyE("garncarz"), List.of(AdjectiveForms.createKi("garncars")), "Garncarze;Garncary"),
            new WordCore("tkacz", WordCategory.PROFESSION, WordGender.MASCULINE, PluralType.MASC_PERSONAL, NounForms.createEmptyE("tkacz"), List.of(AdjectiveForms.createKi("tkac")), "Tkacze"),
            new WordCore("smolarz", WordCategory.PROFESSION, WordGender.MASCULINE, PluralType.MASC_PERSONAL, NounForms.createEmptyE("smolarz"), List.of(AdjectiveForms.createKi("smolars")), "Smolary;Smolarnia;Smolarze"),
            new WordCore("zdun", WordCategory.PROFESSION, WordGender.MASCULINE, PluralType.MASC_PERSONAL, new NounForms("zdun", "zduni"), List.of(AdjectiveForms.createNski("zdu")), "Zduny;Zdunów;Zdunowo"),
            new WordCore("rycerz", WordCategory.PROFESSION, WordGender.MASCULINE, PluralType.MASC_PERSONAL, NounForms.createEmptyE("rycerz"), List.of(AdjectiveForms.createKi("rycers")), "Rycerka;Rycerzewo"),
            new WordCore("mnich", WordCategory.PROFESSION, WordGender.MASCULINE, PluralType.MASC_PERSONAL, new NounForms("mnich", "mnisi"), List.of(AdjectiveForms.createYY("mnisz")), "Mnichów;Mnichowo;Mniszki;Mniszek"),
            new WordCore("bartnik", WordCategory.PROFESSION, WordGender.MASCULINE, PluralType.MASC_PERSONAL, NounForms.createKCy("bartni"), List.of(AdjectiveForms.createCzy("bartni")), "Bartniki;Bartne;Bartodzieje"),
            // --- BUDOWLE / OSADY (STRUCTURE) ---
            new WordCore("zamek", WordCategory.STRUCTURE, WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createEk("zam"), List.of(AdjectiveForms.createOwy("zamk")), "Zamek;Zamczysko;Podzamcze;Zamkowa"),
            new WordCore("wał", WordCategory.STRUCTURE, WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createEmptyY("wał"), List.of(AdjectiveForms.createOwy("wał")), "Wały;Zawale;Podwale;Wałowa"),
            new WordCore("studni", WordCategory.STRUCTURE, WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAe("studni"), List.of(AdjectiveForms.createNy("studzien")), "Studnia;Studzianna;Studzianki;Studzienice"),
            new WordCore("warowni", WordCategory.STRUCTURE, WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAe("warowni"), List.of(AdjectiveForms.createNy("warow")), "Warownia;Warowna"),
            new WordCore("grobl", WordCategory.STRUCTURE, WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAe("grobl"), List.of(), "Grobla;Groble"),
            new WordCore("folwark", WordCategory.STRUCTURE, WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createEmptyI("folwark"), List.of(AdjectiveForms.createCzny("folwar")), "Folwark;Folwarki"),
            new WordCore("zagrod", WordCategory.STRUCTURE, WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("zagrod"), List.of(AdjectiveForms.createOwy("zagrod")), "Zagroda;Zagrody;Zagrodno"),
            new WordCore("bud", WordCategory.STRUCTURE, WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("bud"), List.of(), "Budy;Budki;Budziska"),
            new WordCore("szałas", WordCategory.STRUCTURE, WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createEmptyY("szałas"), List.of(), "Szałas;Szałasy"),
            new WordCore("rynek", WordCategory.STRUCTURE, WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createEk("ryn"), List.of(AdjectiveForms.createOwy("rynk")), "Rynek"),
            new WordCore("dzwonnic", WordCategory.STRUCTURE, WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAe("dzwonnic"), List.of(), "Dzwonnica;Dzwonowo"),
            // --- CECHY (QUALITY) ---
            new WordCore("pustk", WordCategory.QUALITY, WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAi("pustk"), List.of(AdjectiveForms.createSty("pu")), "Pustki;Pustkowo;Pustynia"),
            new WordCore("głusz", WordCategory.QUALITY, WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAe("głusz"), List.of(AdjectiveForms.createChy("głu")), "Głusza;Głuszyna;Głuchowo"),
            new WordCore("spokój", WordCategory.QUALITY, WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, new NounForms("spokój", null), List.of(AdjectiveForms.createNy("spokoj")), "Spokojna"),
            new WordCore("wolnoś", WordCategory.QUALITY, WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, new NounForms("wolność", null), List.of(AdjectiveForms.createYI("woln")), "Wolna;Wolne"),
            new WordCore("świętoś", WordCategory.QUALITY, WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, new NounForms("świętość", null), List.of(new AdjectiveForms("święty", "święta", "święte", "święci", "święte")), "Świętno;Święte;Świątniki")
    );

    // --- Reguły łączenia przymiotnik (modyfikator) + rzeczownik (głowa) ----
    // Rdzenie "miejscowe" (teren/budowle/wulgaryzmy) jako głowa przyjmują dowolny
    // przymiotnik — stąd zostają "Żabie Błoto", "Wilczy Gród", "Sarni Las".
    // Rdzenie "bytowe" (zwierzę, roślina, zawód, istota, cecha) jako głowa
    // przyjmują tylko przymiotniki terenowe i jakościowe — to eliminuje bezsensy
    // typu "Kuni Jeleń" czy "Zajęczy Lis", zostawiając np. "Leśny Jeleń".
    private static final Set<WordCategory> PLACE_MODIFIERS = Set.of(WordCategory.TERRAIN, WordCategory.QUALITY);
    private static final Set<WordCategory> ALL_MODIFIERS = EnumSet.allOf(WordCategory.class);
    private static final Map<WordCategory, Set<WordCategory>> ALLOWED_MODIFIERS = Map.of(
            WordCategory.ANIMAL, PLACE_MODIFIERS,
            WordCategory.PLANT, PLACE_MODIFIERS,
            WordCategory.PROFESSION, PLACE_MODIFIERS,
            WordCategory.BEING, PLACE_MODIFIERS,
            WordCategory.QUALITY, PLACE_MODIFIERS,
            WordCategory.TERRAIN, ALL_MODIFIERS,
            WordCategory.STRUCTURE, ALL_MODIFIERS,
            WordCategory.VULGAR, ALL_MODIFIERS
    );

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

    // Losuje rdzeń dostarczający przymiotnik, którego kategoria pasuje do rdzenia
    // będącego głową nazwy. Po wyczerpaniu prób oddaje dowolny (bezpiecznik).
    private static WordCore getCompatibleAdjectiveCore(WordCore nameCore) {
        Set<WordCategory> allowed = ALLOWED_MODIFIERS.getOrDefault(nameCore.getCategory(), ALL_MODIFIERS);
        for (int i = 0; i < 200; i++) {
            WordCore candidate = areas.get(rng.nextInt(areas.size()));
            if (!candidate.getAdjectiveForms().isEmpty() && allowed.contains(candidate.getCategory())) {
                return candidate;
            }
        }
        return getAdjectiveCore();
    }

    private static String folkName() {
        WordCore nameCore = areas.get(rng.nextInt(areas.size()));
        // Ścieżka jednowyrazowa: gotowe formy ludowe (Żabianka, Zażabie...).
        // Trzymane per-WordCore w polu singleEnder, bo derywacja wymaga alternacji
        // rdzenia, których nie da się rzetelnie wyliczyć z samego tematu.
        List<String> singleForms = nameCore.getSingleForms();
        if (!singleForms.isEmpty() && rng.nextDouble() < 0.4) {
            return singleForms.get(rng.nextInt(singleForms.size()));
        }
        WordCore adjCore = getCompatibleAdjectiveCore(nameCore);
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
