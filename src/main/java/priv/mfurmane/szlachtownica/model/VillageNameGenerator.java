package priv.mfurmane.szlachtownica.model;

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
            new WordCore("żab", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("żab"), List.of(AdjectiveForms.createI("żab"))),
            new WordCore("skał", WordGender.FEMININE, PluralType.NON_MASC_PERSONAL, NounForms.createAy("skał"), List.of(AdjectiveForms.createNy("skal"))),
            //MASCULINE NON MASC
            new WordCore("smo", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createK("smo"), List.of(AdjectiveForms.createCzy("smo"))),
            new WordCore("las", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createEmptyY("las"), List.of(new AdjectiveForms("leśny", "leśna", "leśne", "leśni", "leśne"))),
            new WordCore("wil", WordGender.MASCULINE, PluralType.NON_MASC_PERSONAL, NounForms.createK("wil"), List.of(AdjectiveForms.createCzy("wil"))),
            //MASCULINE MASC
            new WordCore("dra", WordGender.MASCULINE, PluralType.MASC_PERSONAL, NounForms.createNie("dra"), List.of(AdjectiveForms.createNski("dra"), AdjectiveForms.createNny("dra"))),
            new WordCore("kupi", WordGender.MASCULINE, PluralType.MASC_PERSONAL, NounForms.createEc("kupi"), List.of(AdjectiveForms.createNski("dra"), AdjectiveForms.createEcki("kupi"))),

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
        return adjective + " " + nameCoreFormatted;
    }

    /* TODO potencjalne pomysły
Dolina
Góra
Górka
Wzgórze
Pagórek
Równina
Nizina
Wyżyna
Brzeg
Przełęcz
Jar
Wąwóz
Pustkowie

Polana
Łąka
Pole
Zakole
Dukt
Ścieżka
Droga
Gościniec
Rozdroże
Osada
Kolonia
Przysiółek
Zagroda
Stanica

Bór
Gaj
Zarośle
Krzak
Krzew
Drzewo
Dąb
Brzoza
Sosna
Topola
Wierzba
Rzeka
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

Lis
Niedźwiedź
Jeleń
Sarna
Dzik
Zając
Kuna
Bóbr
Żubr
Krowa
Koza
Owca
Koń
Pies
Kot
Kogut
Kaczka

Człowiek
Chłop
Pan
Kmiec
Sołtys
Rybak
Myśliwy
Pasterz
Kupiec
Kowal
Młynarz
Wędrowiec
Strażnik

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
