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

    private static List<WordCore> areas = List.of(
            //NEUTRAL
            new WordCore("wzgórz", WordGender.NEUTRAL, PluralType.NON_MASC_PERSONAL, NounForms.createE("wzgórz"), List.of(AdjectiveForms.createOwy("wzgórz"))),
            new WordCore("sioł", WordGender.NEUTRAL, PluralType.NON_MASC_PERSONAL, NounForms.createO("sioł"), List.of(new AdjectiveForms("sielski", "sielska", "sielskie", "sielscy", "sielskie"))),
            //FEMININE
            new WordCore("brzoz", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("brzoz"), List.of(AdjectiveForms.createOwy("brzoz"))),
            new WordCore("wierzb", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("wierzb"), List.of(AdjectiveForms.createOwy("wierzb"))),
            new WordCore("rzek", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAi("rzek"), List.of(AdjectiveForms.createCzny("rze"))),
            new WordCore("sosn", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("sosn"), List.of(AdjectiveForms.createOwy("sosn"))),
            new WordCore("topol", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAe("topol"), List.of(AdjectiveForms.createOwy("topol"), AdjectiveForms.createNy("topol"))),
            new WordCore("żab", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("żab"), List.of(AdjectiveForms.createI("żab"))),
            new WordCore("skał", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("skał"), List.of(AdjectiveForms.createNy("skal"))),
            new WordCore("sarn", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("sarn"), List.of(AdjectiveForms.createNi("sar"))),
            new WordCore("kun", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("kun"), List.of(AdjectiveForms.createNi("ku"))),
            new WordCore("krow", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("krow"), List.of(AdjectiveForms.createI("krow"))),
            new WordCore("koz", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("koz"), List.of(AdjectiveForms.createI("koz"))),
            new WordCore("owc", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAe("owc"), List.of(AdjectiveForms.createCzy("ow"))),
            new WordCore("kaczk", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAi("kaczk"), List.of(AdjectiveForms.createY("kacz"))),
            new WordCore("samic", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAe("samic"), List.of(AdjectiveForms.createY("samicz"))),
            new WordCore("ryb", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("ryb"), List.of(AdjectiveForms.createI("ryb"))),
            //SAME ADJECTIVE AS MALE
            new WordCore("handlark", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAi("handlark"), List.of(AdjectiveForms.createKi("handlars"))),
            new WordCore("rybaczk", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAi ("rybaczk"), List.of(AdjectiveForms.createKi("rybac"))),
            new WordCore("myśliw", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAe ("myśliw"), List.of(AdjectiveForms.createKi("myśliws"))),
            new WordCore("paster", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAi ("pasterk"), List.of(AdjectiveForms.createKi("pasters"))),
            new WordCore("kowalk", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAi ("kowalk"), List.of(AdjectiveForms.createKi("kowals"))),
            new WordCore("młynark", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAi ("młynark"), List.of(AdjectiveForms.createKi("młynars"))),
            new WordCore("elfk", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAi("elfk"), List.of(AdjectiveForms.createI("elf"), AdjectiveForms.createI("elfick"))),
            new WordCore("krasnoludk", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAi("krasnoludk"), List.of(AdjectiveForms.createI("krasnoludz"), AdjectiveForms.createKiCy("krasnoludz"))),

            //MASCULINE NON MASC
            new WordCore("smo", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createK("smo"), List.of(AdjectiveForms.createCzy("smo"))),
            new WordCore("las", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createEmptyY("las"), List.of(new AdjectiveForms("leśny", "leśna", "leśne", "leśni", "leśne"))),
            new WordCore("wil", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createK("wil"), List.of(AdjectiveForms.createCzy("wil"))),
            new WordCore("korze", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createNie("korze"), List.of(AdjectiveForms.createNy("korzen"), AdjectiveForms.createOwy("korzeni"))),
            new WordCore("poto", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createK("poto"), List.of(AdjectiveForms.createCzny("poto"), AdjectiveForms.createOwy("potok"), AdjectiveForms.createNy("potocza"))),
            new WordCore("lis", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createEmptyY("lis"), List.of(AdjectiveForms.createI("lis"), AdjectiveForms.createNy("lisia"))),
            new WordCore("niedźwied", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createZZie("niedźwied"), List.of(AdjectiveForms.createI("niedźwiedz"))),
            new WordCore("jele", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createNie("jele"), List.of(AdjectiveForms.createI("jelen"))),
            new WordCore("dzik", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createEmptyI("dzik"), List.of(AdjectiveForms.createI("dzik"), AdjectiveForms.createCzy("dzi"))),
            new WordCore("zając", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createEmptyE("zając"), List.of(AdjectiveForms.createY("zajęcz"))),
            new WordCore("bóbr", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, new NounForms("bóbr",   "bobry"), List.of(AdjectiveForms.createY("bobrz"), AdjectiveForms.createOwy("bobr"))),
            new WordCore("bober", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createEmptyY("bober"), List.of(AdjectiveForms.createOwy("bober"))),
            new WordCore("ko", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createNie("ko"), List.of(AdjectiveForms.createNski("ko"))),
            new WordCore("ko", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createEmptyY("kot"), List.of(AdjectiveForms.createCi("ko"))),
            new WordCore("kogu", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createEmptyY("kogut"), List.of(AdjectiveForms.createCi("kogu"))),
            new WordCore("sam", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, new NounForms("samiec", "samce"), List.of(AdjectiveForms.createCzy("sam"))),
            new WordCore("elf", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createEmptyY("elf"), List.of(AdjectiveForms.createI("elf"), AdjectiveForms.createI("elfick"))),
            new WordCore("krasnolud", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createEmptyY("krasnolud"), List.of(AdjectiveForms.createI("krasnoludz"), AdjectiveForms.createKiCy("krasnoludz"))),
            new WordCore("dąb", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, new NounForms("dąb", "dęby"), List.of(AdjectiveForms.createOwy("dęb"))),
            //MASCULINE MASC
//            new WordCore("dra", WordGender.MASCULINE, PluralType.MASC_PERSONAL, NounForms.createNie("dra"), List.of(AdjectiveForms.createNski("dra"), AdjectiveForms.createNny("dra"))),
            new WordCore("kupi", WordGender.MASCULINE, PluralType.MASC_PERSONAL, NounForms.createIec("kup"), List.of(AdjectiveForms.createNski("dra"), AdjectiveForms.createEcki("kupi"))),
            new WordCore("człowiek", WordGender.MASCULINE, PluralType.MASC_PERSONAL, new NounForms("człowiek", "ludzie"), List.of(AdjectiveForms.createKi("ludz"), AdjectiveForms.createCzy("człowie"))),
            new WordCore("rybak", WordGender.MASCULINE, PluralType.MASC_PERSONAL, NounForms.createKCy ("ryba"), List.of(AdjectiveForms.createKi("rybac"))),
            new WordCore("myśliw", WordGender.MASCULINE, PluralType.MASC_PERSONAL, NounForms.createYi ("myśliw"), List.of(AdjectiveForms.createKi("myśliws"))),
            new WordCore("paster", WordGender.MASCULINE, PluralType.MASC_PERSONAL, NounForms.createEmptyE ("pasterz"), List.of(AdjectiveForms.createKi("pasters"))),
            new WordCore("kowal", WordGender.MASCULINE, PluralType.MASC_PERSONAL, NounForms.createEmptyE ("kowal"), List.of(AdjectiveForms.createKi("kowals"))),
            new WordCore("młyna", WordGender.MASCULINE, PluralType.MASC_PERSONAL, NounForms.createEmptyE ("młynarz"), List.of(AdjectiveForms.createKi("młynars"))),
            new WordCore("wędrow", WordGender.MASCULINE, PluralType.MASC_PERSONAL, NounForms.createEmptyE ("młynarz"), List.of(AdjectiveForms.createKi("młynars"))),

            //NON ADJECTIVE
            new WordCore("uroczysk", WordGender.NEUTRAL, PluralType.NON_MASC_PERSONAL, NounForms.createO("uroczysk"), List.of()),
            new WordCore("trakt", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createEmptyY("trakt"), List.of()),
            new WordCore("siedlisk", WordGender.NEUTRAL, PluralType.NON_MASC_PERSONAL, NounForms.createO("siedlisk"), List.of()),
            new WordCore("siedliszcz", WordGender.NEUTRAL, PluralType.NON_MASC_PERSONAL, NounForms.createE("siedliszcz"), List.of()),
            //NON PLURAL, NON ADJECTIVE
            new WordCore("wieś", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, new NounForms("wieś", null), List.of()),
            new WordCore("wola", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, new NounForms("wola", null), List.of()),
            new WordCore("wólka", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, new NounForms("wólka", null), List.of())
    );

//    private static String[] femaleFirstWords = {"Dupna", "Żabia"};
//    private static String[] femaleSecondWords = {"Wola", "Wólka"};
//    private static String[] maleFirstWords = {"Dupny", "Żabi"};
//    private static String[] maleSecondWords = {"Wola", "Wólka"};
//    private static String[] neutralFirstWords = {"Dupne", "Żabie", "Stare"};
//    private static String[] neutralSecondWords = {"Wzgórza", "Zarowie", "Pupy"};
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
        WordCore adjCore = getAdjectiveCore();
        WordCount count = rng.nextDouble() < 0.3 && nameCore.hasPlural() ? WordCount.PLURAL : WordCount.SINGULAR;
        String nameCoreFormatted = nameCore.getNoun(count);
        String adjective = adjCore.getAdjective(count, nameCore.getSelfGender(), nameCore.getPluralType(), rng.nextInt(adjCore.getAdjectiveForms().size()));
        return WordUtils.capitalizeFully(adjective + " " + nameCoreFormatted);
    }

    /* TODO potencjalne pomysły
Bór
Gaj
Zarośle
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
Woda
Bród
Próg

Pan
Chłop

Wędrowiec
Strażnik
Kmiec
Sołtys

Młyn
Dwór
Zamek
Gród
Grodzisko
Fort
Forteca
Wieża
Most
Chata
Stodoła
Obora
Kuźnia
Karczma
Targ
Port
Przystań







Stary
Nowy
Mały
Wielki
Zielony
Suchy
Mokry
Cichy
Dziki
Gęsty
Rzadki
Kamienny
Piaszczysty
Błotnisty
Leśny
Polny
Łąkowy
Górny
Dolny
Nizinny
Wysoki
Niski
Głęboki
Płytki
Zaciszny
Wietrzny
Słoneczny
Mroczny
Cienisty
Jasny
Zarosły
Kręty
Prosty
Szeroki
Wąski
Otwarty
Zamknięty
Rozległy
Ukryty
Zalesiony
Nagły
Stromy
Łagodny
Pusty
Żyzny
Urodzajny
Kamienisty
Bagienny
Równinny
Pagórkowaty

Spokojny
Cichy
Senny
Leniwy
Pracowity
Stary
Nowy
Zadbany
Opuszczony
Zamieszkały
Ludny
Pusty
Rolniczy
Gospodarczy
Zagrodowy
Wiejski
Swojski
Prosty
Skromny
Dostatni
Bogaty
Ubogi
Boczny
Biedny
Gościnny
Zamknięty
Otwarty
Rodzinny
Ciepły
Chłodny
Przyjazny
Obcy
Dawny
Historyczny
Tradycyjny
Zwyczajny
Znany
Zapomniany
Ukryty
Daleki
Bliski

Złoty
Srebrny
Żelazny
Miedziany
Kamienny
Szklany
Czarny
Biały
Czerwony
Szary
Popielaty
Krwawy
Święty
Przeklęty
Błogosławiony
Zaklęty
Magiczny
Dziwny
Obcy
Starożytny
Zapomniany
Zaginiony
Ukryty
Widmowy
Martwy
Żywy
Wieczny
Nietrwały
Złamany
Spalony
Zrujnowany
Porzucony
Zdradziecki
Mroczny
Jasny
Ponury
Groźny
Cichy
Nawiedzony
Zatracony

Dobry
Zły
Stary
Młody
Silny
Słaby
Dumny
Pokorny
Wesoły
Smutny
Uparty
Mądry
Głupi
Sprytny
Uczciwy
Zdradliwy
Twardy
Miękki
Odważny
Strachliwy
Cichy
Głośny
Zamyślony
Rozbity
Zgubiony
Odnaleziony
Zaginiony
Znany
Nieznany
Zapomniany

Zamknięty
Otwarty
Złamany
Zbudowany
Spalony
Zniszczony
Porzucony
Zgubiony
Odnaleziony
Zaginiony
Zapomniany
Nazwany
Ukryty
Odsłonięty
Zakopany
Wzniesiony
Zburzony
Zalany
Osuszony
Zasiany
Zebrany
Uprawiany
Zamieszkały
Opuszczony
Zarośnięty
Wypalony
Zatracony
Ocalony
Zatrzymany
Rozlany
Złączony
Rozdzielony
Zamglony
Zacieniony
Oświetlony
Przebity
Związany
Rozwiązany
Zamrożony
Ocieplony


    * */

}
