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
            new WordCore("wzgórz", WordGender.NEUTRAL, PluralType.NON_MASC_PERSONAL, NounForms.createE("wzgórz"), List.of(AdjectiveForms.createOwy("wzgórz")), "Wzgórzyca;Wzgórek"),
            new WordCore("sioł", WordGender.NEUTRAL, PluralType.NON_MASC_PERSONAL, NounForms.createO("sioł"), List.of(new AdjectiveForms("sielski", "sielska", "sielskie", "sielscy", "sielskie")), "Sielanka"),
            new WordCore("grodzisk", WordGender.NEUTRAL, PluralType.NON_MASC_PERSONAL, NounForms.createO("grodzisk"), List.of(AdjectiveForms.createOwy("grodzisk")), "Grodzisk"),
            new WordCore("błot", WordGender.NEUTRAL, PluralType.NON_MASC_PERSONAL, NounForms.createO("błot"), List.of(AdjectiveForms.createYI("błotn"), AdjectiveForms.createSty("błotni")), "Zabłocie;Błotnica;Podbłocie"),
            new WordCore("pol", WordGender.NEUTRAL, PluralType.NON_MASC_PERSONAL, NounForms.createE("pol"), List.of(AdjectiveForms.createYI("poln")), "Zapole;Podpole;Zapolice"),
            new WordCore("zacisz", WordGender.NEUTRAL, PluralType.NON_MASC_PERSONAL, NounForms.createE("zacisz"), List.of(AdjectiveForms.createYI("zaciszn")), "Zacisze"),
            new WordCore("słońc", WordGender.NEUTRAL, PluralType.NON_MASC_PERSONAL, NounForms.createE("słońc"), List.of(AdjectiveForms.createYI("słoneczn")), ""),
            new WordCore("zarośl", WordGender.NEUTRAL, PluralType.NON_MASC_PERSONAL, NounForms.createE("zarośl"), List.of(new AdjectiveForms("zarośnięty", "zarośnięta", "zarośnięte", "zarośnięci", "zarośnięte"), new AdjectiveForms("zarosły", "zarosła", "zarosłe", "zarośli", "zarosłe")), "Zarośle"),
            //FEMININE
            new WordCore("brzoz", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("brzoz"), List.of(AdjectiveForms.createOwy("brzoz")), "Zabrzozie;Podbrzozie;Brzózki;Brzozów;Brzozówka"),
            new WordCore("wierzb", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("wierzb"), List.of(AdjectiveForms.createOwy("wierzb")), "Wierzbie;Zawierzbie;Wierzbica;Wierzbianka"),
            new WordCore("rzek", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAi("rzek"), List.of(AdjectiveForms.createCzny("rze")), "Zarzecze;Nadrzecze;Podrzecze;Międzyrzecze"),
            new WordCore("sosn", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("sosn"), List.of(AdjectiveForms.createOwy("sosn")), "Sośnica;Sosnowica;Sosnówka;Podsośnie;Zasośnie"),
            new WordCore("topol", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAe("topol"), List.of(AdjectiveForms.createOwy("topol"), AdjectiveForms.createNy("topol")), "Topolno;Topolany;Topólka"),
            new WordCore("żab", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("żab"), List.of(AdjectiveForms.createI("żab")), "Żabno;Żabnica;Żabice;Żabianka;Zażabie"),
            new WordCore("skał", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("skał"), List.of(AdjectiveForms.createNy("skal")), "Zaskale;Podskale;Skałka"),
            new WordCore("sarn", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("sarn"), List.of(AdjectiveForms.createNi("sar")), "Sarnów;Sarnowa;Sarnówka"),
            new WordCore("kun", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("kun"), List.of(AdjectiveForms.createNi("ku")), "Kunów;Kunice"),
            new WordCore("krow", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("krow"), List.of(AdjectiveForms.createI("krow")), "Krowica;Krowianki"),
            new WordCore("koz", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("koz"), List.of(AdjectiveForms.createI("koz")), "Kozy;Koźle;Kozice;Koźlanka"),
            new WordCore("owc", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAe("owc"), List.of(AdjectiveForms.createCzy("ow")), "Owczary;Owczarnia"),
            new WordCore("kaczk", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAi("kaczk"), List.of(AdjectiveForms.createYY("kacz")), "Kaczory;Kaczanica"),
            new WordCore("samic", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAe("samic"), List.of(AdjectiveForms.createYY("samicz")), "Samiczki;Samica"),
            new WordCore("ryb", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("ryb"), List.of(AdjectiveForms.createI("ryb")), "Rybno;Rybnica;Zarybie;Międzyrybie"),
            new WordCore("stodoł", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("stodoł"), List.of(AdjectiveForms.createNy("stodol")), "Stodoły;Zastodole"),
            new WordCore("obor", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("obor"), List.of(AdjectiveForms.createNy("obor")), "Obory;Oborki;Oborniki"),
            new WordCore("kuźni", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAe("kuźni"), List.of(AdjectiveForms.createNy("kuźnia")), "Kuźnice;Kuźniczka;Kuźnica"),
            new WordCore("karczm", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("karczm"), List.of(AdjectiveForms.createNy("karczem")), "Karczmiska;Zakarczmie;Karczmianka"),
            new WordCore("przysta", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createNie("przysta"), List.of(AdjectiveForms.createOwy("przystani")), "Przystań"),
            new WordCore("staroś", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createCi("staroś"), List.of(AdjectiveForms.createRy("sta")), "Starość;Starzyzna"),
            new WordCore("nowoś", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createCi("nowoś"), List.of(AdjectiveForms.createYI("now")), "Nowość;Nowina"),
            new WordCore("wielko", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createCi("wielkoś"), List.of(AdjectiveForms.createKi("wiel")), "Wielkość"),
            new WordCore("łąk", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAi("łąk"), List.of(AdjectiveForms.createOwy("łąk"), AdjectiveForms.createNy("łącz")), "Łąka;Łęki;Łączki;Podłęże"),
            new WordCore("gór", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("gór"), List.of(AdjectiveForms.createKi("górs"), AdjectiveForms.createNy("gór")), "Zagórze;Podgórze;Górki;Nagórze;Górzyca"),
            new WordCore("wysokoś", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createCi("wysokoś"), List.of(AdjectiveForms.createKi("wyso"), AdjectiveForms.createOwy("wysokości")), ""),
            new WordCore("głębi", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAe("głębi"), List.of(AdjectiveForms.createKi("głębo"), AdjectiveForms.createOwy("głębin")), "Głębia;Zagłębie"),
            new WordCore("płycizn", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("płycizn"), List.of(AdjectiveForms.createKi("płyt")), "Płycizna"),
            new WordCore("wod", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("wod"), List.of(AdjectiveForms.createYI("wodn")), "Zawodzie;Zawada;Podwodzie"),
            new WordCore("dup", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("dup"), List.of(AdjectiveForms.createYI("dupn"), AdjectiveForms.createYI("dupczan"), AdjectiveForms.createSty("dupia")), "Zadupie;Dupice;Dupianka;Dupczyki"),
            new WordCore("kurw", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("kurw"), List.of(AdjectiveForms.createI("kurw"), AdjectiveForms.createYI("kurwiczn"), AdjectiveForms.createSty("wykurwi"), AdjectiveForms.createOny("skurwi")), "Zakurwie;Kurwice"),
//            new WordCore("wod", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("wod"), List.of(AdjectiveForms.createY("wodn")), ""),
            //NO PLURAL
            new WordCore("małoś", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, new NounForms("małość", null), List.of(new AdjectiveForms("mały", "mała", "małe", "mali", "małe"), AdjectiveForms.createYI("małostkow")), "Maleństwo"),
            new WordCore("ziele", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, new NounForms("zieleń", null), List.of(AdjectiveForms.createYI("zielon")), "Zielonki;Zielenica"),
            new WordCore("suchoś", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, new NounForms("suchość", null), List.of(AdjectiveForms.createChy("su")), "Susza;Suchoty;Suchotnica;Suszanka"),
            new WordCore("mokroś", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, new NounForms("mokrość", null), List.of(AdjectiveForms.createRy("mok"), AdjectiveForms.createOny("przemocz")), "Mokrzyska;Moczanka;Moczyce;Moczary"),
            new WordCore("susz", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, new NounForms("susza", null), List.of(AdjectiveForms.createOny("przesusz")), ""),
            new WordCore("cisz", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, new NounForms("cisza", null), List.of(AdjectiveForms.createChy("ci")), "Cisza;Zacisze;Ciszówka;Cichawica"),
            new WordCore("dzicz", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, new NounForms("dzicz", null), List.of(AdjectiveForms.createKi("dzi")), "Dzicz;Dzikość;Dziczyzna;Zadzicze"),
            new WordCore("gęstwa", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, new NounForms("gęstwa", null), List.of(AdjectiveForms.createSty("gę")), "Gęstwina"),
            new WordCore("rzadkość", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, new NounForms("rzadkość", null), List.of(AdjectiveForms.createKi("rzad")), "Rzadkość"),
            new WordCore("dum", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, new NounForms("duma", null), List.of(AdjectiveForms.createNy("dum")), "Dumka;Duma;Dumki"),
            //SAME ADJECTIVE AS MALE
            new WordCore("handlark", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAi("handlark"), List.of(AdjectiveForms.createKi("handlars")), "Handelek;Handlowisko;Handlownica"),
            new WordCore("rybaczk", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAi ("rybaczk"), List.of(AdjectiveForms.createKi("rybac")), "Rybianka;Rybaczka;Rybownica"),
            new WordCore("myśliw", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAe ("myśliw"), List.of(AdjectiveForms.createKi("myśliws")), ""),
            new WordCore("paster", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAi ("pasterk"), List.of(AdjectiveForms.createKi("pasters")), ""),
            new WordCore("kowalk", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAi ("kowalk"), List.of(AdjectiveForms.createKi("kowals")), "Kowalica;Kowalnica;Kowalówka"),
            new WordCore("młynark", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAi ("młynark"), List.of(AdjectiveForms.createKi("młynars")), "Młynianka;Młynówka"),
            new WordCore("elfk", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAi("elfk"), List.of(AdjectiveForms.createI("elf"), AdjectiveForms.createKi("elfic")), ""),
            new WordCore("krasnoludk", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAi("krasnoludk"), List.of(AdjectiveForms.createKiCy("krasnoludz")), ""),

            //MASCULINE NON MASC
            new WordCore("smo", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createK("smo"), List.of(AdjectiveForms.createCzy("smo")), "Smoczanka;Smoczyce;Smokownica"),
            new WordCore("las", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createEmptyY("las"), List.of(AdjectiveForms.createYI("leśn")), "Zalesie;Podlesie;Lasek;Laski;Leśnica;Lesianka;Leśniczówka;Laskownica"),
            new WordCore("wil", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createK("wil"), List.of(AdjectiveForms.createCzy("wil")), "Wilków;Wilcze;Wilkowice;Wilczyca;Wilkownica;Wilczanka;Zawilcze"),
            new WordCore("korze", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createNie("korze"), List.of(AdjectiveForms.createNy("korzen"), AdjectiveForms.createOwy("korzeni")), "Korzeniów;"),
            new WordCore("poto", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createK("poto"), List.of(AdjectiveForms.createCzny("poto"), AdjectiveForms.createOwy("potok")), "Potok;Zapotocze;Potoczek"),
            new WordCore("lis", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createEmptyY("lis"), List.of(AdjectiveForms.createI("lis")), "Lisów;Lisie;Liski"),
            new WordCore("niedźwied", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createZZie("niedźwied"), List.of(AdjectiveForms.createI("niedźwiedz")), "Niedźwiedź;Niedźwiada;Niedźwiedzie"),
            new WordCore("jele", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createNie("jele"), List.of(AdjectiveForms.createI("jelen")), "Jeleń;Jelenie;Jeleniów"),
            new WordCore("dzik", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createEmptyI("dzik"), List.of(AdjectiveForms.createCzy("dzi")), "Dzików;Dzikowo;Dzikowiec"),
            new WordCore("zając", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createEmptyE("zając"), List.of(AdjectiveForms.createYY("zajęcz")), "Zajączki;Zajączków;Zajączkowo"),
            new WordCore("bóbr", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, new NounForms("bóbr",   "bobry"), List.of(AdjectiveForms.createYY("bobrz"), AdjectiveForms.createOwy("bobr")), "Bobrowniki;Bobrownica;Bobrowo;Bobrzanka"),
            new WordCore("bober", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createEmptyY("bober"), List.of(AdjectiveForms.createOwy("bober")), ""),
            new WordCore("ko", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createNie("ko"), List.of(AdjectiveForms.createNski("ko")), "Końskie;Koniuszki"),
            new WordCore("ko", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createEmptyY("kot"), List.of(AdjectiveForms.createCi("ko")), "Kotów;Kocie;Kotki"),
            new WordCore("kogu", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createEmptyY("kogut"), List.of(AdjectiveForms.createCi("kogu")), ""),
            new WordCore("sam", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, new NounForms("samiec", "samce"), List.of(AdjectiveForms.createCzy("sam")), ""),
            new WordCore("elf", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createEmptyY("elf"), List.of(AdjectiveForms.createI("elf"), AdjectiveForms.createKi("elfic")), "Elfowo;Elfów"),
            new WordCore("krasnolud", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createEmptyY("krasnolud"), List.of(AdjectiveForms.createKiCy("krasnoludz")), "Krasnoludki;Krasnoludów"),
            new WordCore("dąb", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, new NounForms("dąb", "dęby"), List.of(AdjectiveForms.createOwy("dęb")), "Dąbrowa;Dąbie;Dębina;Dębno;Dębica"),
            new WordCore("młyn", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createEmptyY("młyn"), List.of(AdjectiveForms.createKi("młyńs")), "Młyny;Zamłynie;Młynary"),
            new WordCore("dwór", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, new NounForms("dwór", "dwory"), List.of(AdjectiveForms.createKi("dwors")), "Zadworze;Dwory;Poddworze"),
            new WordCore("gród", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, new NounForms("gród", "grody"), List.of(AdjectiveForms.createKi("grodz")), "Podgrodzie;Nowogród;Grodziec;Grodzisko"),
            new WordCore("kamie", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createNie("kamie"), List.of(AdjectiveForms.createNy("kamien")), "Kamień;Kamienica;Podkamień"),
            new WordCore("pias", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createEk("pias"), List.of(AdjectiveForms.createOwy("piask"), AdjectiveForms.createSty("piaszczy")), "Piaski;Piaseczno"),
            new WordCore("dół", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, new NounForms("dół", "doły"), List.of(AdjectiveForms.createNy("dol")), "Zadole;Podole;Nadole;Doły"),
            new WordCore("niż", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createEmptyE("niż"), List.of(AdjectiveForms.createKi("nis"), AdjectiveForms.createOwy("niż")), ""),
            new WordCore("wiatr", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createEmptyY("wiatr"), List.of(AdjectiveForms.createYI("wietrzn"), AdjectiveForms.createOwy("wiatr")), "Wiatrowo;Wietrzno"),
            new WordCore("mrok", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createEmptyI("mrok"), List.of(AdjectiveForms.createYI("mroczn")), "Mroczków;Mroki"),
            new WordCore("cie", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createNie("cie"), List.of(AdjectiveForms.createSty("cieni")), ""),
            new WordCore("osi", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, new NounForms("osioł", "osły"), List.of(AdjectiveForms.createIorA("ośl")), ""),
            new WordCore("płomie", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createNie("płomie"), List.of(AdjectiveForms.createYI("płomienn")), ""),
            //MASCULINE MASC
//            new WordCore("dra", WordGender.MASCULINE, PluralType.MASC_PERSONAL, NounForms.createNie("dra"), List.of(AdjectiveForms.createNski("dra"), AdjectiveForms.createNny("dra")), ""),
            new WordCore("kupi", WordGender.MASCULINE, PluralType.MASC_PERSONAL, NounForms.createIec("kup"), List.of(AdjectiveForms.createNski("dra"), AdjectiveForms.createEcki("kupi")), ""),
            new WordCore("człowiek", WordGender.MASCULINE, PluralType.MASC_PERSONAL, new NounForms("człowiek", "ludzie"), List.of(AdjectiveForms.createKi("ludz"), AdjectiveForms.createCzy("człowie")), ""),
            new WordCore("rybak", WordGender.MASCULINE, PluralType.MASC_PERSONAL, NounForms.createKCy ("ryba"), List.of(AdjectiveForms.createKi("rybac")), "Rybaki;Rybaków"),
            new WordCore("myśliw", WordGender.MASCULINE, PluralType.MASC_PERSONAL, NounForms.createYi ("myśliw"), List.of(AdjectiveForms.createKi("myśliws")), ""),
            new WordCore("paster", WordGender.MASCULINE, PluralType.MASC_PERSONAL, NounForms.createEmptyE ("pasterz"), List.of(AdjectiveForms.createKi("pasters")), "Pasterka"),
            new WordCore("kowal", WordGender.MASCULINE, PluralType.MASC_PERSONAL, NounForms.createEmptyE ("kowal"), List.of(AdjectiveForms.createKi("kowals")), "Kowale;Kowalewo;Kowala"),
            new WordCore("młyna", WordGender.MASCULINE, PluralType.MASC_PERSONAL, NounForms.createEmptyE ("młynarz"), List.of(AdjectiveForms.createKi("młynars")), ""),
            new WordCore("wędrow", WordGender.MASCULINE, PluralType.MASC_PERSONAL, new NounForms("wędrowiec", "wędrowcy"), List.of(AdjectiveForms.createNy("wędrow")), ""),

            //NON ADJECTIVE
            new WordCore("uroczysk", WordGender.NEUTRAL, PluralType.NON_MASC_PERSONAL, NounForms.createO("uroczysk"), List.of(), ""),
            new WordCore("trakt", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createEmptyY("trakt"), List.of(), ""),
            new WordCore("siedlisk", WordGender.NEUTRAL, PluralType.NON_MASC_PERSONAL, NounForms.createO("siedlisk"), List.of(), "Siedliska"),
            new WordCore("siedliszcz", WordGender.NEUTRAL, PluralType.NON_MASC_PERSONAL, NounForms.createE("siedliszcz"), List.of(), ""),
            new WordCore("wież", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAe("wież"), List.of(), ""),
            new WordCore("wieżyc", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAe("wieżyc"), List.of(), ""),
            new WordCore("most", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createEmptyY("most"), List.of(), "Mosty;Zamoście;Mościska"),
            new WordCore("chat", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("chat"), List.of(), ""),
            new WordCore("doł", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createEk("doł"), List.of(), ""),
            //NON PLURAL, NON ADJECTIVE
            new WordCore("wieś", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, new NounForms("wieś", null), List.of(), ""),
            new WordCore("wola", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, new NounForms("wola", null), List.of(), "Wola;Wólka;Wolica"),
            new WordCore("wólka", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, new NounForms("wólka", null), List.of(), "Wólka")
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

    private static String folkName() {
        WordCore nameCore = areas.get(rng.nextInt(areas.size()));
        // Ścieżka jednowyrazowa: gotowe formy ludowe (Żabianka, Zażabie...).
        // Trzymane per-WordCore w polu singleEnder, bo derywacja wymaga alternacji
        // rdzenia, których nie da się rzetelnie wyliczyć z samego tematu.
        List<String> singleForms = nameCore.getSingleForms();
        if (!singleForms.isEmpty() && rng.nextDouble() < 0.4) {
            return singleForms.get(rng.nextInt(singleForms.size()));
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
