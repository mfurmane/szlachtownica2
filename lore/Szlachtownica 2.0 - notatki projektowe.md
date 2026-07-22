# Szlachtownica 2.0 — notatki projektowe i lore

> **Proweniencja:** wyekstrahowane z `Szlachtownica_2.pdf` (11 stron), dostarczonego
> przez autora. Dokument roboczy — miesza **notatki architektury silnika symulacji**
> (init prowincji/regionów/rodów, `BreedingStrategy`, wzorzec State, reguły przejść
> `RegionType`/`TerrainType`, taksonomia terenu) z **transkryptami burzy mózgów o lore**
> (Zielona Rubież, Larazza/Jirdenal/Korsana, klątwa Zavilesu/Zelderinu, Międzygórze,
> panteon Pustki: Verena → Izgarthul, Zug-Caiona, obłęd Irmusa 1743). Artefakty
> eksportu z czatu (przyciski „Kopiuj/Edytuj", etykiety bloków `json`) usunięto;
> treść merytoryczna zachowana bez zmian. Łamanie wierszy pochodzi z PDF.

---

Theodorus z Filetu - o co mogło chodzić?
Hipopotamus z Filetu - wielki architekt, oryginalnie Hippodamos z Miletu

Szlachtownica 2.0

-  Globalnie lub lokalnie interfejs BreedingStrategy (czy żenimy się z ludnością,

z wasalami, czy między sobą)

-  Stan osoby jako wzorzec State (w ciąży, w żałobie itp)

-  Narodziny
-  Wymarcie rodu
-  Dziecko
-  Małżeństwo

Zaktualizować daty startu prowincji!

Dodać Alabaster w pobliżu Tantanoru

Dodać predefiniowaną siedzibę Amarastus dla Sarrambertów

Przy wołaniu jakiegokolwiek eventu należy sprawdzić, czy istnieją nadal osoby, rodziny, czy miasta,
których mają dotyczyć

Inicjalizacja prowincji

-  Ustawiamy dane z konfiguracji z pliku
-
-  Rejestrujemy event StartProvinceSettlementEvent na dacie początku

Inicjalizujemy podprowincje i ich regiony

Inicjalizacja regionu

-  Ustawiamy pola z konfiguracji od rodzica
-  Czy potrzebujemy nazwy?
-  Wybieramy glebę
-  Określamy surowce
-  Wybieramy enchant (może być narzucony przez miasto)
-  Definiujemy rzeki i jeziora
-

Inicjalizujemy predefiniowane miasta, jeśli obecne

Jeśli region zamieszkany:

-  Określamy typ settlementu (zwykły, craft, farming itp)
-  Określamy ilość wiosek (wpływa gleba, klimat, enchant, obecność miasta, typ settlementu)
-

Inicjalizujemy wioski
Jeśli region niezamieszkany

-  Określamy typ terenu (las, łąka itp)

Inicjalizacja settlementu (start symulacji)
-  Wybieramy/ustawiamy nazwę
-

Jeśli z pliku rejestrujemy predefiniowane eventy (np. BecomeLocalCapitalEvent, albo
BecomeCapitalEvent)

-  Określamy liczbę ludności (i rozkład rasowy?)
-  Określamy początkową produkcję i zapotrzebowania importowe
-  Określamy początkowe nastawienie
-  Czy robić mapę relacji międzyosiedlowych?
-

Jeśli tak, określamy stosunek do istniejących sąsiadów

Inicjalizacja rodu

-  Ustawiamy dane z konfiguracji z pliku, lub generujemy, jeśli to nowa rodzina
-
Jeśli predefiniowana siedziba, rejestrujemy event SetMainFamilyManor
-  Rejestrujemy StartFamilyEvent w roku założenia lub uruchamiamy natychmiast, jeśli brak
-  Rejestrujemy inne predefiniowane eventy (np. EndFamilyEvent, FeudalTributeEvent, czy

MainBranchExecuteEvent, albo ChangeMainBranchEvent)

Uruchomienie rodu

-

Jeśli rodzina nie ma predefiniowanej siedziby, albo jeszcze nie istnieje, wybieramy lub tworzymy
odpowiednią
Przydzielamy regiony i zakładamy osiedla

-
-  Ustawiamy rodzinny majątek

Nadania ziemskie

-

Jak decydować

MapFeature - klasa abstrakcyjna? interfejs?
ModelPlace - osady, punkty itp
ModelLine - rzeki, drogi (linestring, szerokość)

Hydrography - interfejs: głębokość (dziedziczy rzeka i jezioro)

Roads - klasa po ModelLine
River - klasa po ModelLine, implements Hydrography
Lake

FOREST
SWAMP
DUST_PLAIN
ROCK_LAND
PINE_CRAG
MEADOWS
FARMING_LAND
SETTLERS_REACH
TOURISTIC_LAND
ESTATE_REGION
CRAFTS_LAND
ABANDONED_REACH
IRON_MARCHES
SUPERNATURAL_EXPANSE

SETTLEMENT:
  FORTIFIED_SETTLEMENT
  MILITARY_SETTLEMENT
  TRADING_SETTLEMENT
  ARTISAN_SETTLEMENT
  MINING_SETTLEMENT
  RESORT_SETTLEMENT
  ENCHANT_SETTLEMENT

SETTLEMENT - cechy:
  HUNTING
  LUMBERING
  PLANTING
  FARMING
  FISHING
  FORTIFIED
  MILITARY
  TRADING
  ARTISAN
  MINING
  RESORT
  ENCHANTED
  SEA_PORT
  RIVER_PORT

0 - przysiółek

1 - wioska
2 - spora wieś
3 - małe miasteczko
4 - miasteczko
5 - nieduże miasto
6 - umiarkowane miasto
7 - spore miasto
8 - duże miasto
9 - wielkie miasto
10 - Gilgamore, czy Durrenburg w 1743

-  Obecność HUNTING i LUMBERING blokuje rozwinięcie osady powyżej poziomu 0-1
-  Obecność PLANTING i FARMING blokuje rozwinięcie osady powyżej poziomu 0-2, chyba że nie mamy

zwierząt innych niż konie (max 3), a roślin innych niż winogron (max 5)

Komfort obok enchantu wpływa na nastawienie mieszkańców. Jest liczony na podstawie dostępnych towarów,
jakości i różnorodności jedzenia (baaaardzo spada, gdy nie mamy jedzenia), czego jeszcze?

minLevel

level

maxLevel

localLoyalty

culturalEvolution

technologyAdoption

migrationAttractiveness

tradeAttractiveness

expanse

productionSpeed

instability

comfort

order

safety

nastawienie mieszkańców
Set wartości enuma zależny od wartości comfort, enchantu, ?
cechy osady
boolean hasCoast() {

return region.getCharacteristics().contains(TerrainCharacteristic.COAST);
}
boolean hasLake() {
return region.getCharacteristics().contains(TerrainCharacteristic.LAKES);
}
boolean hasRiver() {
return region.getCharacteristics().contains(TerrainCharacteristic.RIVERS);
}
boolean hasProminentEnchant() {
return region.getEnchantLevel() >= 3;
}
boolean hasProperLevel(int min, int max) {
return level >= min && level <= max;
}
boolean hasLumberingPotential() {
//woodRichness
}
boolean hasMiningPotential() {
//zasoby
}
boolean hasFamine()
//głodujemy my lub okolica
}
boolean hasTradingPotential() {

}
boolean hasDangers() {

}
boolean hasTechnoPotential() {

}

boolean hasPreference(cecha) {

}

Poziom rozwoju będzie dotyczył osad, nie populacji, bo w jednej osadzie może występować jednocześnie kilka
populacji, więc nie będę rozbijał SETTLERS na dwie wartości. To, co zaproponowałeś jako REBELS, idealnie mi
pasuje pod to, o czym myślałem jako ALLIANCE. Ten group w nawiasie miał być właśnie id danej grupy
interesów. Tylko że teraz nie wiem, czy nie wyciągnąć tego do jeszcze osobnej rzeczy, bo w dekadzie
bezkrólewia każdy z głównych lokalnych władców będzie walczył za siebie, a w czasie ich buntu, czy Mrocznego
Wieku, cały kraj podzieli się na dwie walczące strony. Trzeba tymi sojuszami zarządzać inaczej.
Co do rozbicia ARMY dla mnie jest ono wyłącznie ustrukturyzowanym, pełnoprawnym wojskiem. Każdy inny typ
powinien być raczej rozegrany jako strength przemnożony przez count, co jest w zasadzie dokładnie pospolitym
ruszeniem. Przy czym większość armii w królestwie będzie należała do konkretnych rodzin (później też
organizacji), a tylko nieliczne do korony. To w sporej mierze miało służyć jako armie najeźdźców, albo broniących
się przed nami tubylców

Population
-
-
-

PopulationType
Affiliation
solidarity (Razem czy osobno z loyality? To trochę co innego, bo solidarność mówiłaby o spójności
wewnętrznej, a lojalność o wierności wobec zwierzchnictwa, ale jednak są trochę podobne)
expanse
productionSpeed

-
-

-
-
-
-

lawfulness
attitude (lista z naszego enuma SettlersAttitude, można rozszerzyć na wszystkie populacje)
count
strength (średnia skuteczność bojowa, dla armii równa 1, dla osadników prawdopodobnie w granicach
0.1)

PopulationType

-
SETTLERS
-  OUTLAWS
-  NOMADS
-  CARAVAN
-
-

BARBARIANS
ARMY

FAMILY(family) - populacja związana z rodem szlacheckim

Affiliation
-
-  CROWN(country) - populacja związana z siłami państwowymi, zwykle armia
-  ORGANIZATION(group) - kompanie najemne, gildie złodziei, Hanza Sartamska itp
-  NONE(null)

SETTLERS - głównie NONE, sporadycznie dopuszczalne pozostałe
OUTLAWS - głównie NONE lub ORGANIZATION, prawie nigdy FAMILY i CROWN (być może pojawią się
bandyci na czyichś usługach, w niektórych okresach)
NOMADS - wyłącznie NONE, mówimy tu traperach, zbieraczach, czy hobo
CARAVAN - dopuszczamy wszystko, to grupy wędrowne, na przykład kupieckie karawany
BARBARIANS - NONE
ARMY - FAMILY, CROWN, ORGANIZATION
Alliance
-
-
-
-
-
-

id
name
loyality
solidarity
List<GroupGoal> goals
List<Affiliation> groups

Dodać:

-  wildlifeReachness (ilość zwierząt) w prowincji i regionie
-
-
-

foodSupplies - w place, zbyt niski w przeliczeniu na liczebność populacji oznacza głód
List<Population> populations - w place i w region dla nieosiadłych
naturalFeedingPotential - w regione pole stanowiące dodatkowy naturalny foodSupplies. Jest zależny
od warunków i stopnia naturalności terenu, w razie głodu jest wykorzystywany jako substytut
foodSuppies i maleje, a powoli rośnie, kiedy go nie ruszamy
feed() - metoda redukcji foodSupplies na podstawie populacji plus ewentualny update
naturalFeedingPotential
podatki

roślinność i zwierzęta powinny być modyfikowane przez zużycie przez ludzi i regenerować się z
czasem, tylko że górny pułap roślinności powinien być zależny od warunków, a górny pułap zwierząt
zależny od ilości roślin

-

-

Ważne:
-

Set wartości enuma definiowanych poprzez:

-
-

dostęp do morza (SEA_PORT, FISHING, RESORT)
obecność rzek (RIVER_PORT, FISHING)

obecność jezior (RESORT, FISHING)
enchant (ENCHANTED)

-
-
-  wielkość osady ()
-
-
-
-
-
-
-

poziom rozwoju (HUNTING, LUMBERING, PLANTING, FARMING itp.)
obecności drewna (LUMBERING)
obecności złóż (MINING)
potrzeby jedzenia (PLANTING, FARMING, FISHING)
tradeAttractiveness (TRADING)
niebezpieczna okolica (MILITARY, FORTIFIED)
technologyAdoption, preferencje, przypadek (ARTISAN)

Algorytmy
-
-
-
-

Pathfinding: A*, nie Dijkstra, bo działa dużo szybciej na dużych zbiorach
Voronoi (podział powierzchni na regiony)
Perlin noise - klimat/tereny

Voronoi

Diagram Voronoi to podział płaszczyzny na obszary wokół zestawu punktów.
Bierzesz zbiór punktów (np. centers regionów).
Każdemu punktowi przypisujesz obszar – wszystkie miejsca, które są bliżej niego niż któregokolwiek innego.
Efekt: cała mapa dzieli się na takie „komórki” przypominające naturalne granice (poligony).
👉 Wygląda to jak losowa mapa fantasy z „plastrami miodu”, tylko nie równymi, ale naturalnie poszarpanymi.
---
🔹 Jak to działa krok po kroku
1. Masz np. 20 punktów rozrzuconych losowo na mapie.
2. Algorytm Voronoi dzieli mapę tak, że każdy piksel/punkt mapy „należy” do najbliższego z tych punktów.
3. Powstają obszary, których granice biegną w połowie odległości między sąsiednimi punktami.
To jest odwrotność triangulacji Delaunaya (czyli podziału na trójkąty). Oba algorytmy zwykle idą w parze:
Delaunay daje Ci sąsiadów → możesz zbudować graf.
Voronoi daje Ci granice regionów → możesz narysować mapę.
---
🔹 Dlaczego to fajne dla Ciebie?
Możesz wygenerować naturalnie wyglądającą mapę prowincji.
Od razu masz sąsiedztwo regionów – bo każdy poligon sąsiaduje z tymi, z którymi ma wspólną krawędź.
Możesz dodać do tego noise (Perlin/Simplex), żeby klimat i teren zmieniały się płynnie.
To klasyczna technika w generatorach map fantasy i grach strategicznych.

Szerokość geograficzna (latitude)
1° szerokości = zawsze ta sama odległość w metrach (bo idziemy od równika w górę lub dół po południku).
To ok. 111,32 km na stopień (czyli ~111 m na 0,001°).
Od równika do bieguna jest 90°, czyli 90 × 111 km ≈ 10 000 km (to ćwiartka obwodu Ziemi).

---

Długość geograficzna (longitude)
1° długości = różna odległość w metrach, zależna od szerokości geograficznej.
Przy równiku 1° długości ≈ 111,32 km (tak samo jak dla szerokości).
Ale im dalej na północ/południe, tym krótszy „łuk równoleżnika”:
Warszawa (~52°N): 1° długości ≈ 111,32 × cos(52°) ≈ 68,4 km
Oslo (~60°N): ≈ 55,8 km
Biegun: 1° długości = 0 m (bo wszystkie południki spotykają się w punkcie).

23,26,11 - 23,44
66,33,49 - 66,56

Północ Saravery ~40?
Czyli z północy na południe około 39,9-46,5
8,8 w drugiej osi, 70,8-79,6

FACILITY - cechy:

NOBLE_MANOR

FACILITY:
  NOBLE_RESIDENCE
  COURT
  MONASTERY
  FORTRESS
  ENCHANT_CENTER

Przenieść do PlaceCharacteristic:
  SEA_PORT
  RIVER_PORT

LAKE?

TerrainCharacteristic - czy to ma sens? Rzeki i jeziora mam inaczej, ale trzeba dodać do konfiguracji
prowincji wybrzeża

Province -> Area -> Region
Czy może Area to tylko mniejszy, podrzędny Province?

-
-
-
-

Albo użyć czegoś takiego:
record TerrainTransition(TerrainType from, TerrainType to, String condition, int yearsRequired) {}
Na przykład:
new TerrainTransition(FOREST, TIMBERLAND, "intensive logging", 20)
new TerrainTransition(FOREST, GREENWOOD, "no human interference", 120)

Dodać do osób mapy relacji!
Zdefiniować sposób na ilość zasobów (Międzygórze ma gigantyczne pokłady)

Region:

-  RegionType
SoilType
-
-  Resources
-

🟢 Pozytywne / Wspólnotowe
FRIENDLY – życzliwi, gościnni
COOPERATIVE – chętni do współpracy
HARMONIOUS – zgodni, żyjący w ładzie

OPTIMISTIC – pozytywnie nastawieni
HARDWORKING – pracowici i zdeterminowani
🟡 Neutralne / Wewnętrzne
MELANCHOLIC – nostalgiczni, powolni w działaniu
ISOLATED – wyobcowani, niezbyt ufni
STOIC – odporni psychicznie, nieokazujący emocji
CURIOUS – otwarci na nowe zjawiska, ale z dystansem
INWARD – skupieni na własnej społeczności, niekoniecznie wrogo nastawieni
ROUGH – twardzi, zahartowani, nieprzystępni
NEUTRAL – brak wyraźnej postawy
🔴 Negatywne / Chaotyczne
ENVIOUS – zawistni, nieufni
ILL_TEMPERED – łatwo popadający w gniew
IMPULSIVE – działają pod wpływem emocji
WARLIKE – wojowniczy, skłonni do konfliktów
APATHETIC – zobojętniali, bez motywacji
UNSTABLE – emocjonalnie niestabilni, chwiejni
PARANOID – skrajnie nieufni
SUBMISSIVE – poddani, ulegli wobec sił zewnętrznych
FANATICAL – zaślepieni wiarą lub ideą

Enum: PlaceType
🏠 Osady ogólne
OUTPOST,
HAMLET,
VILLAGE,
TOWN,
BIG_TOWN,
CITY,
CAPITAL
🛍 Ośrodki handlowe
TRADING_POST,
MERCHANT_TOWN,
MERCHANT_CITY,
COMMERCIAL_HUB
⚒ Ośrodki rzemieślnicze
CRAFTING_POINT,
ARTISAN_TOWN,
ARTISAN_CITY,
ARTISAN_HUB
⛏ Ośrodki górnicze i hutnicze
> Tutaj można rozważyć zależność od obecności surowców.
MINE,
SMELTING_POINT,
INDUSTRIAL_TOWN,
INDUSTRIAL_CITY,
INDUSTRIAL_HUB
💎 Ośrodki luksusowe i prestiżowe
> Bazujące na prestiżu, sztuce, często pochodzące z ogólnych lub magicznych osad.
CULTURE_POINT,
NOBLE_TOWN,
NOBLE_CITY,

ROYAL_RESORT
🌊 Typy naturalne/geograficzne
LAKE,
MOUNTAIN_PASS,
LUMBERMILL,
FISHING_CAMP,
FISHING_TOWN,
SEA_PORT,
RIVER_HARBOR,
DELTA_SETTLEMENT
🌳 Ośrodki magiczne i duchowe
> Tereny z silnym wpływem enchantów
SHRINE,
SPIRIT_GROVE,
DREAM_GROVE,
SACRED_GARDEN,
MYSTIC_ENCLAVE
🏰 Forteczne i militarne
WATCHTOWER,
STRONGHOLD,
FORTRESS,
MILITARY_CAMP,
GARRISON_TOWN
---
Dodatki i opcje rozszerzenia
Możesz stworzyć osobne enumy kategorii (PlaceCategory) i wiązać z nimi typy z PlaceType, np.:
enum PlaceCategory {
  GENERAL_SETTLEMENT,
  TRADE,
  CRAFT,
  INDUSTRY,
  PRESTIGE,
  MILITARY,
  NATURAL,
  SPIRITUAL
}

Chapel – miejsce modlitwy, mniejsze niż kościół, często prywatne lub pomocnicze.
Priory – podległy klasztor (np. od opactwa).
Convent – bardziej społeczna wspólnota religijna, zwłaszcza żeńska.
Monastery – duży, samowystarczalny klasztor (np. benedyktyński).
Abbey – opactwo, większa niezależność i prestiż niż monastery.
Cathedral – władza kościelna, niekoniecznie liczba mnichów.
Holy Site – może być pustelnicze, magiczne, lub bosko objawione.
Grand Temple – monumentalna świątynia, może być świecka lub religijna (w zależności od świata).

SHRINE – niewielka kapliczka, często przydrożna
ALTAR_SITE – miejsce ofiarne, może być też plenerowe
LOCAL_TEMPLE – mniejsza świątynia dzielnicowa, dzienna obsługa
GREAT_TEMPLE – większa, bardziej reprezentacyjna świątynia
SANCTUARY_COMPLEX – świątynia zintegrowana z lokalnym życiem (targ, kuźnia, teatr itd.), pełniąca też
funkcje społeczne
HIGH_TEMPLE – główny ośrodek kultu w danej prowincji lub regionie
PILGRIMAGE_CENTER – cel pielgrzymek, słynne miejsce mocy lub cudów

RETREAT_SITE – tymczasowa pustelnia / pustelnicza chatka
PRIORY – niewielki klasztor (często pierwszy etap kolonizacji zakonu)

CONVENT – klasztor średniego rozmiaru, z funkcjami społecznymi lub medycznymi
MONASTERY – duży kompleks klasztorny
ORDER_STRONGHOLD – warowny klasztor/forteca zakonników (dla zakonów wojskowych)
HIGH_ABBEY – duchowe centrum zakonu, matka wszystkich klasztorów

🏡 Propozycja rozwiniętego ciągu Posiadłości:

1.  HOMESTEAD – samotne gospodarstwo, pierwotne siedlisko rodzinne
2.  MANOR – wiejski dworek szlachecki, zarządzający okolicą
3.  ESTATE – rozległa posiadłość ziemska z zabudowaniami gospodarczymi
4.  HALL – główny dom/kompleks rodu (np. "Rodowa Sala [X]")
5.  LORD'S_RESIDENCE – bardziej formalna siedziba zarządcy lennika lub barona
6.  COURT – dwór lokalnego władcy, zarazem centrum administracyjne
7.  PALACE – reprezentacyjna rezydencja szlachecka lub książęca
8.  GREAT_PALACE lub ROYAL_PALACE – siedziba królów, cesarzy lub najwyższej arystokracji

🔧 Możliwe filtry/dodatki:

●  FORTIFIED (np. FORTIFIED_MANOR, HILL_HALL) – dla warownych posiadłości
●  RUINED_* – dla ruin dawnych posiadłości
●  SEASONAL – np. HUNTING_LODGE, SUMMER_PALACE
●  MAGICAL – ENCHANTED_ESTATE, dla domen np. czarodziejów, alchemików

🧬 Przemyślenia kontekstowe:

●  MANOR i ESTATE mogą być równoległe – ten pierwszy to raczej dom szlachcica, ten drugi – domena

ziemska (oba mogą współistnieć).

●  HALL może być tym, co zostaje z MANOR po wygaśnięciu rodu – lub przeciwnie, czym MANOR się

staje, gdy ród urośnie w siłę.
COURT i PALACE różnią się funkcją: court = ośrodek władzy + administracji, palace = rezydencja
wyższej rangi.

●  Dla klasztorów czy ośrodków religijnych również mogą występować ich odpowiedniki, np.

ABBOT'S_HALL, PRIEST'S_MANOR.

---

Możliwości dalszego rozwoju

1. Poziomy: można wprowadzić atrybut level dla każdego miejsca, np. 0 dla OUTPOST, 1 dla HAMLET itd.
2. Powiązanie z rozwojem terenu: np. CRAFTING_POINT może być wynikiem przekształcenia CRAFTS_LAND.
3. Efekty enchantów: np. MYSTIC_ENCLAVE tylko w ENCHANTED, SACRED_GARDEN tylko w SACRED.

Map<Enchant, Set<SettlersAttitude>> ENCHANT_ATTITUDE_MAP = Map.of(
  SACRED, Set.of(
    FRIENDLY, HARMONIOUS, OPTIMISTIC, COOPERATIVE, HARDWORKING
  ),

  VEIL, Set.of(
    MELANCHOLIC, STOIC, INWARD
  ),
  WILDERNESS, Set.of(
    HARDWORKING, ROUGH, ISOLATED, STOIC
  ),
  ENCHANTED, Set.of(
    CURIOUS, IMPULSIVE, FRIENDLY, MELANCHOLIC
  ),
  TWISTED, Set.of(
    IMPULSIVE, UNSTABLE, FANATICAL, CURIOUS
  ),
  WARPED, Set.of(
    ILL_TEMPERED, ROUGH, WARLIKE, ENVIOUS, IMPULSIVE
  ),
  CURSED, Set.of(
    ENVIOUS, PARANOID, APATHETIC, ILL_TEMPERED, SUBMISSIVE
  ),
  NONE, Set.of(
    NEUTRAL, HARDWORKING, FRIENDLY, COOPERATIVE
  )
);

enum EvolutionPathType {
    NATURAL,
    IMPULS_REQUIRED,
    MAGICAL,
    CULTURAL,
    HYDROLOGICAL,
    EROSIVE,
    TEMPORAL_LONG_TERM,
    CHAOTIC
}
enum SpecialGeographicFeature {
    NONE,
    COASTAL,
    RIVER_ADJACENT,
    LAKE_SHORE,
    MOUNTAIN_BASE,
    ...
}
Warto wystandaryzować kilka binarnych/liczbowych cech, bo często z nich korzystasz:
SECURE (złożone z: bandit_threat, monster_threat, garrison, terrain_defensibility, ujemne enchanty)
URBANIZATION_LEVEL (0–1)
WEALTH (0–1)
AESTHETIC_PREF, CRAFTS_PREF, HERBAL_PREF, EQUESTRIAN_PREF (0–1 albo w preferredDirections)
FISHING_POTENTIAL, VISIBLE_ORE, STONE_AVAIL, SOIL_POOR (boole/oceny)
IMPULSE (rzadki tick globalny/regionalny)
To pozwoli jednoznacznie rozstrzygać przejścia bez mnożenia wyjątków.

WITHERED_WOODS – teren leśny pozornie naturalny, ale wyniszczony od środka przez CURSED (drzewa schnące od korzeni,
szept wśród konarów, brak zwierzyny),
DREAD_FOREST – mroczny, złowrogi odpowiednik PRIMAL_FOREST, żywy ale nieżyjący w klasycznym sensie (drzewa o
czarnych pniach, duchy z Pustki, porzucone świątynie przeklętych kultów),
BLEEDING_WOODS – miejsce, gdzie CURSED przenika aktywnie do Wymiaru Materialnego, powodując wypaczenia przyrody
(np. śluzowe drzewa, krwawiące pnie, chory wzrost roślin, mutacje).

Province.preferredDirections().contains()

riverDammed: boolean

beaverActivity: boolean
soilPermeability: enum {HIGH, MEDIUM, LOW}
riverSlope: enum {STEEP, MEDIUM, FLAT}
humanNeglect: boolean

1. Drobne uproszczenia / aliasy do warunków
Rozważ pogrupowanie warunków typu:
„poprawa gleby i odpowiednie warunki klimatyczne” → SUITABLE_FOR_CROPS
„przybycie osadników” → INFLUX_OF_SETTLERS
„nastąpi impuls i mamy dobrobyt” → ECONOMIC_BOOM lub CULTURAL_IMPULSE

Przydałoby się zapamiętywać ścieżkę. To, w jaką stronę pójdzie SETTLERS_REACH powinno zależeć nie tylko
od klimatu, gleby i wilgotności, ale też występowania takich rzeczy jak rzeka, czy jezioro

ORE_LANDS będzie wtedy, gdy wylosujemy zasoby
Niektóre rodzaje terenów (TIMBERLAND, MINING_LANDS) to stan tymczasowy, powinny mieć flagę
ACTIVE i TIME_LEFT

NATURALNE I PODSTAWOWE

FOREST
→ TIMBERLAND (kilkadziesiąt i więcej lat intensywnej eksploatacji)
→ GROVE (silna obecność druidów + enchant z [WILDERNESS, ENCHANTED, TWISTED])
→ ARCANE_EXPANSE (enchant z [ENCHANTED, TWISTED] + sprowadzi się masa magów)
→ SPIRITED_EXPANSE (enchant VEIL + sprowadzi się masa szamanów)
→ HOLY_EXPANSE (enchant SACRED + sprowadzi się masa ludzi + świątynie itp)
→ GREENWOOD (częściowe wyniszczenie)
→ WOODS (akcja dewastacja, np. przemarsz wielkiej armii, klęska żywiołowa)
→ HUNTING_REGION (rzadkie i niebezpieczne)

GREENWOOD
→ TIMBERLAND (eksploatacja, parędziesiąt lat)
→ HUNTING_REGION (jeśli osiedliśmy, ale nie mamy środków do przekształceń)
→ GROVE (druidzi/pradawna opieka)
→ WOODS (częściowa degradacja, np. przez choroby drzew lub przemarsz armii)
→ FOREST (pozostawiony przez bardzo długi okres + enchant NONE)

WOODS
→ TIMBERLAND (kilka lat – szybka eksploatacja)
→ GREENWOOD(po dekadach zaniedbania)
→ FIELDS (jeśli wykarczujemy las, przekształcony rolniczo)
→ PASTURES (jeśli wykarczujemy las)
→ FISHING_COAST (jeśli COASTAL i wykarczujemy las)
→ SETTLERS_REACH (jeśli wykarczujemy las)
→ HUNTING_REGION (jeśli osiedliśmy, ale nie mamy środków do przekształceń)

TIMBERLAND
→ SETTLERS_REACH (jeśli po drodze pojawili się koloniści, czy osady)
→ PASTURES (jeśli warunki sprzyjają hodowli)
→ FIELDS (jeśli warunki sprzyjają rolnictwu)
→ ORCHARDS (jeśli warunki sprzyjają sadownictwu)
→ FRUIT_PLANTATIONS (jeśli warunki sprzyjają krzewom owocowym)
→ VINEYARDS (jeśli mamy co jeść i warunki sprzyjają winnicom (trudniejsze))
→ VERDANT_REACH (jeśli mamy co jeść, preferujemy zielarstwo i warunki sprzyjają)
→ GARDENS (jeśli mamy co jeść i zależy nam na estetyce)
→ CRAFTS_LAND (jeśli mamy względnie łatwy dostęp do surowców i może rozwinąć się rzemiosło)

→ MEADOWS (jeśli eksploatacja się skończy, ale nic się tam nie będzie działo poza tym)
→ FISHING_COAST (jeśli COASTAL i pojawią się ludzie)
→ HUNTING_REGION (jeśli porzuciliśmy eksploatację w trakcie, ale zostaliśmy)
→ WOODS (jeśli niewiele zostało, a porzuciliśmy eksploatację w trakcie)
→ GREENWOOD (jeśli sporo zostało, a porzuciliśmy eksploatację w trakcie)
→ FOREST (jeśli zostały wielkie połacie naturalnego lasu, a porzuciliśmy eksploatację)

HUNTING_REGION
→

MEADOWS
→ SETTLERS_REACH (jeśli pojawią się ludzie)
→ FARMS (jeśli nastąpi impuls i warunki sprzyjają hodowli)
→ STUDS (jeśli nastąpi impuls, mamy dobrobyt i warunki sprzyjają hodowli)
→ FIELDS (jeśli nastąpi impuls i warunki sprzyjają rolnictwu)
→ ORCHARDS (jeśli nastąpi impuls i warunki sprzyjają sadownictwu)
→ FRUIT_PLANTATIONS (jeśli nastąpi impuls i warunki sprzyjają krzewom owocowym)
→ VINEYARDS (jeśli nastąpi impuls, mamy co jeść i warunki sprzyjają winnicom (trudniejsze))
→ VERDANT_REACH (jeśli nastąpi impuls, mamy co jeść, preferujemy zielarstwo i warunki sprzyjają)
→ GARDENS (jeśli nastąpi impuls, mamy co jeść i zależy nam na estetyce)
→ CRAFTS_LAND (jeśli nastąpi impuls, mamy względnie łatwy dostęp do surowców i może rozwinąć się
rzemiosło)
→ WOODS (jeśli warunki sprzyjają drzewom + opuszczone)
→ FISHING_COAST (jeśli COASTAL i pojawią się ludzie)

LAKE
→ FISHING_REGION (jeśli pojawią się ludzie)
→ HUNTING_REGION (jeśli pojawią się ludzie)
→ SETTLERS_REACH (druga opcja jeśli pojawią się ludzie)
→ RESORT_BELT (jeśli nastąpi impuls i mamy już muchos dobrobytos)
→ GARDENS (jeśli nastąpi impuls, mamy co jeść i zależy nam na estetyce)
→ SWAMP (jeśli jezioro zarasta, bo warunki za bardzo sprzyjają bujnej wegetacji, brak cyrkulacji wody i
odmulania, albo wysycha, bo nie ma dopływów)
→ MEADOWS (bardzo długi proces: jest co najmniej ciepło, jezioro nie ma dopływów, wysycha, zostają żyzne
aluwia)
→ ORCHARDS (jeśli nastąpi impuls i warunki sprzyjają sadownictwu)
→ FRUIT_PLANTATIONS (jeśli nastąpi impuls i warunki sprzyjają krzewom owocowym)
→ VINEYARDS (jeśli nastąpi impuls, mamy co jeść i warunki sprzyjają winnicom (trudniejsze))
→ VERDANT_REACH (jeśli nastąpi impuls, mamy co jeść, preferujemy zielarstwo i warunki sprzyjają)
→ FISHING_COAST (jeśli COASTAL i pojawią się ludzie)
→ GARDENS (jeśli nastąpi impuls, mamy co jeść i zależy nam na estetyce)
→ RIVERLAND (jeśli jezioro pęka/zostaje przerwane i zaczyna odprowadzać wodę — np. po katastrofie
naturalnej, erozji lub celowo przez ludzi)
Uwagi:
Może pozostawać przez wieki niezmienne.
Przekształcenie najczęściej wymaga impulsu (człowiek, zmiany klimatyczne).

SWAMP
→ VERDANT_REACH (jeśli zasiedlony przez zielarzy, druidów – bogate siedlisko roślin)
→ GREENWOOD (jeśli bardzo długo w przynajmniej ciepłym klimacie i nie EXTRA_WET)
→ PRIMAL_FOREST (jeśli na bardzo, bardzo długo w przynajmniej ciepłym klimacie i jest EXTRA_WET)
→ SPIRITED_LANDS (jeśli silna obecność religijna / szamańska)
→ GROVE (jeśli nastąpi impuls i pojawią się druidzi, albo występuje enchant WILDERNESS, który ich
przyciagnie)
→ MEADOWS (jeśli wilgotność max NEUTRAL i klimat przynajmniej NEUTRAL)
→ FIELDS (jeśli nastąpi impuls, zostanie osuszone i warunki sprzyjają rolnictwu)
→ ORCHARDS (nastąpi impuls, zostanie osuszone i warunki sprzyjają sadownictwu)

→ FRUIT_PLANTATIONS (jeśli nastąpi impuls, zostanie osuszone i warunki sprzyjają krzewom owocowym)
→ RIVERLAND (jeśli woda skupi się w jakimś korycie)
→ LAKE (jeśli zalane, albo specjalnie oczyszczone)
→ FISHING_COAST (jeśli COASTAL, pojawią się ludzie i brak lepszych terenów)
→ HUNTING_REGION (jeśli szybko potrzebujemy stąd jedzenia)
Uwagi:
Trudny do zamieszkania, ale bardzo żyzny i bogaty w bioróżnorodność.
Wymaga dużego impulsu (osuszenie, kolonizacja, kultura magiczna lub rolnicza).

DUST_PLAINS
Opis: Suche, piaszczyste lub pyliste równiny – skrajnie niska wilgotność, trudne warunki życia.
→ PASTURES (jeśli nastąpi impuls, głód i dostęp do wody melioracja i ekstensywna hodowla)
→ PINE_CRAG (jeśli długi czas ekspozycji na enchant WILDERNESS)
→ SETTLERS_REACH (jeśli nastąpi impuls, a mamy outposty)
→ ROCK_LANDS (jeśli silne wiatry i długi czas bez ingerencji)
→ ORE_LANDS (jeśli wiatr odsłoni jakieś złoża)
Uwagi:
Naturalnie stabilne, wymagają bardzo silnego impulsu lub wyjątkowych warunków do przekształceń.

ROCK_LANDS
Opis: Tereny skaliste – górskie, klify, płaskowyże. Trudne do zagospodarowania, często surowe.
→ STONE_PITS (jeśli nastąpi impuls i eksploatacja złóż kamienia)
→ SETTLERS_REACH (jeśli nastąpi impuls, a mamy outposty)
→ ORE_LANDS (jeśli odkryto złoża)
→ PINE_CRAG (jeśli jest jakaś wilgoć, lub enchant WILDERNESS)
→ FISHING_COAST (jeśli COASTAL i jest płasko)
Uwagi:
Bardzo trudne do przekształcenia w coś „użytkowego” bez bardzo konkretnego impulsu.
Mogą być idealne pod działalność wydobywczą lub magiczną / religijną izolację.

ORE_LANDS
→ MINING_LANDS (jeśli nastąpi impuls i pojawi się wydobycie)
→ SMELT_LANDS (jeśli jeśli nastąpi impuls, pojawi się eksploatacja i od razu stać nas na huty)
→ PINE_CRAG (jeśli długotrwale opuszczone, climate COLD, enchant WILDERNESS)
→ SETTLERS_REACH (jeśli nastąpi impuls, nie stać nas na eksploatację, ale sprowadzamy się, żeby mieć
blisko)
→ VERDANT_REACH (jeśli nastąpi impuls, brak wydobycia, enchant WILDERNESS i pojawią się druidzi)
→ CRAFTS_LAND (jeśli nastąpi impuls, pojawi się eksploatacja i rzemiosło)
→ ROCK_LANDS (jeśli złoża się wyczerpią)
→ DUST_PLAINS (jeśli złoża się wyczerpią i silny wiatr)
→ STONE_PITS (jeśli złoża się wyczerpią , ale mamy kamień, który zaczniemy wydobywać)

PINE_CRAG
→ WOOD (jeśli klimat się ociepla i pozostawione samo sobie)
→ FOREST (jeśli klimat się ociepla, pozostawione samo sobie i enchant WILDERNESS)
→ STONE_PITS (jeśli nastąpi impuls i eksploatacja złóż kamienia)
→ MINING_LANDS (jeśli nastąpi impuls i zaczyna się poszukiwanie złóż)
→ SETTLERS_REACH (jeśli nastąpi impuls, a mamy outposty)
→ VERDANT_REACH (jeśli nastąpi impuls, enchant WILDERNESS i wilgotność przynajmniej NEUTRAL)
→ CRAFTS_LAND (jeśli nastąpi impuls, bo mamy drewno + kamień)
→ ROCK_LANDS (jeśli drzewa zostaną wycięte – przez człowieka lub kataklizm)
→ FISHING_COAST (jeśli COASTAL i pojawią się ludzie)
→ HUNTING_REGION (jeśli szybko potrzebujemy stąd jedzenia)

STONE_PITS
→ CRAFTS_LAND (jeśli wokół kamieniołomu rozwinie się rzemiosło)
→ ROCK_LANDS (gdy złoża się skończą, a teren nie zostanie zagospodarowany)
→ PINE_CRAG (jeśli opuszczone i enchant WILDERNESS)

→ SETTLERS_REACH (jeśli nastąpi impuls)
→ MINING_LANDS (jeśli odkryto złoża)
→ SMELT_LANDS (jeśli odkryto złoża i od razu stać nas na huty)
→ DUST_PLAINS (gdy złoża się skończą, zostaną porzucone i występują silne wiatry)
→ FISHING_COAST (gdy złoża się skończą i jeśli COASTAL)

RIVERLAND
→ SETTLERS_REACH (jeśli pojawią się ludzie, tu osiądą w pierwszej kolejności)
→ FISHING_REGION (jeśli pojawią się ludzie i warunki sprzyjają połowom)
→ FISHING_COAST (jeśli COASTAL i pojawią się ludzie)
→ HUNTING_REGION (jeśli pojawią się ludzie, ale nie mamy środków do przekształceń)
→ FARMS (jeśli nastąpi impuls i warunki sprzyjają hodowli)
→ STUDS (jeśli nastąpi impuls, mamy dobrobyt i warunki sprzyjają hodowli)
→ FIELDS (jeśli nastąpi impuls i warunki sprzyjają rolnictwu)
→ ORCHARDS (jeśli nastąpi impuls i warunki sprzyjają sadownictwu)
→ FRUIT_PLANTATIONS (jeśli nastąpi impuls i warunki sprzyjają krzewom owocowym)
→ VINEYARDS (jeśli nastąpi impuls, mamy co jeść i warunki sprzyjają winnicom (trudniejsze))
→ VERDANT_REACH (jeśli nastąpi impuls, mamy co jeść, preferujemy zielarstwo i warunki sprzyjają)
→ GARDENS (jeśli nastąpi impuls, mamy co jeść i zależy nam na estetyce)
→ CRAFTS_LAND (jeśli nastąpi impuls, nad rzeką rozwinie się np. młynarstwo, garbarstwo, browarnictwo itp.)
→ SWAMP (płaski teren, niska przepuszczalność gleby, wysoka wilgotność, bobry)
→ LAKE (jeśli pojawi się tama, lub coś innego zablokuje koryto rzeki, np. bobry)

OKOŁOROLNE

Wojna / grabieże → każde z nich może się stać MEADOWS lub VERDANT_REACH.
Czynniki ekonomiczne – bieda, plaga → porzucenie i przekształcenie w MEADOWS.
Magiczny kryzys lub błogosławieństwo → np. nagły impuls WILDERNESS → PASTURES lub FARMS →
VERDANT_REACH.
Industrializacja – np. FARMS lub STUDS → CRAFTS_LAND, później MANUFACTURING_REGION

PASTURES
→ FARMS (jeśli wzrośnie skala hodowli)
→ STUDS (jeśli nastąpi impuls i mamy dobrobyt)
→ MEADOWS (jeśli opuszczone)
→ VERDANT_REACH (jeśli opuszczone i enchant WILDERNESS lub mamy dostatek i preferujemy zielarstwo)
→ SETTLERS_REACH (jeśli przybędzie osadników)
→ FIELDS (jeśli poprawi się gleba, przybędzie osadników, a tereny sprzyjają rolnictwu)
→ ORCHARDS (jeśli poprawi się gleba, przybędzie osadników, a tereny sprzyjają sadownictwu)
→ FRUIT_PLANTATIONS (jeśli poprawi się gleba,przybędzie osadników, a tereny sprzyjają krzewom
owocowym)
→ VINEYARDS (jeśli poprawi się gleba lub przybędzie osadników, mamy co jeść i tereny sprzyjają winnicom
(trudniejsze))
→ GARDENS (jeśli mamy co jeść i zależy nam na estetyce)
→ CRAFTS_LAND (jeśli wraz z osadnictwem sprowadzą się rzemieślnicy)
→ LAKE (jeśli opuszczone i mamy jezioro, lub zaleje teren)
→ SWAMP (jeśli opuszczone i wilgotność EXTRA_WET)
→ DUST_PLAINS (jeśli wilgotność najwyżej DRY i nastąpi wyjałowienie gleby)
→ FISHING_COAST (COASTAL && FISHING_POTENTIAL && IMPULSE || FOOD_NEED)

FARMS
→ FIELDS (jeśli poprawi się gleba, przybędzie osadników, a tereny sprzyjają rolnictwu)
→ ORCHARDS (jeśli poprawi się gleba, przybędzie osadników, a tereny sprzyjają sadownictwu)
→ FRUIT_PLANTATIONS (jeśli poprawi się gleba,przybędzie osadników, a tereny sprzyjają krzewom
owocowym)

→ VINEYARDS (jeśli poprawi się gleba lub przybędzie osadników, mamy co jeść i tereny sprzyjają winnicom
(trudniejsze))
→ GARDENS (jeśli mamy co jeść i zależy nam na estetyce)
→ STUDS (jeśli nastąpi impuls i mamy dobrobyt mogą wyspecjalizować się w stronę koni)
→ PASTURES (jeśli zwierząt drastycznie ubędzie, np. poprzez głód, czy zarazę)
→ MEADOWS (jeśli opuszczone)
→ SETTLERS_REACH (jeśli przybędzie osadników)
→ CRAFTS_LAND (jeśli wraz z osadnictwem sprowadzą się rzemieślnicy)
→ LAKE  (jeśli opuszczone i mamy jezioro, lub zaleje teren)
→ SWAMP (jeśli opuszczone i wilgotność EXTRA_WET)
→ DUST_PLAINS (jeśli wilgotność najwyżej DRY i nastąpi wyjałowienie gleby)
→ FISHING_COAST (COASTAL && FISHING_POTENTIAL && IMPULSE || FOOD_NEED)

STUDS
→ CRAFTS_LAND (jeśli rzemiosła związane z końmi staną się bardziej prominentne od samej hodowli)
→ FARMS (jeśli stadnina przestanie funkcjonować, ale mamy dużo innych zwierząt)
→ PASTURES (jeśli stadnina przestanie funkcjonować, a innych zwierząt będzie niewiele)
→ SETTLERS_REACH (jeśli ludzkie osiedla staną się bardziej prominentne, niż sama stadnina)
→ MEADOWS (jeśli opuszczone)
→ VERDANT_REACH (jeśli opuszczone i enchant WILDERNESS)
→ FIELDS (jeśli stadnina przestanie funkcjonować, a tereny sprzyjają rolnictwu)
→ ORCHARDS (jeśli stadnina przestanie funkcjonować, a tereny sprzyjają sadownictwu)
→ FRUIT_PLANTATIONS (jeśli stadnina przestanie funkcjonować, a tereny sprzyjają krzewom owocowym)
→ VINEYARDS (jeśli tereny sprzyjają winnicom (trudniejsze), a niespecjalnie potrzebujemy koni)
→ GARDENS (jeśli zależy nam na estetyce, a niespecjalnie potrzebujemy koni)
→ LAKE (jeśli opuszczone i mamy jezioro, lub zaleje teren)
→ SWAMP (jeśli opuszczone i wilgotność EXTRA_WET)
→ DUST_PLAINS (jeśli wilgotność najwyżej DRY i nastąpi wyjałowienie gleby)
→ FISHING_COAST (COASTAL && FISHING_POTENTIAL && IMPULSE || FOOD_NEED)

FIELDS
→ PASTURES (jeśli warunki przestaną pozwalać na rolnictwo)
→ FARMS (jeśli warunki przestaną pozwalać na rolnictwo, ale mamy sporo ludzi)
→ ORCHARDS (jeśli warunki sprzyjają sadownictwu i preferujemy sadownictwo)
→ FRUIT_PLANTATIONS (jeśli warunki sprzyjają krzewom owocowym i preferujemy owoce)
→ VINEYARDS (jeśli warunki pozwalają i mamy dobrobyt)
→ GARDENS (jeśli mamy dobrobyt, a zależy nam na estetyce)
→ STUDS (jeśli mamy dobrobyt i preferujemy stadniny)
→ CRAFTS_LAND (jeśli wraz z osadnictwem sprowadzą się rzemieślnicy i preferujemy rzemiosło)
→ SETTLERS_REACH (jeśli przybędzie osadników)
→ MEADOWS (jeśli porzucone)
→ VERDANT_REACH (jeśli porzucone i enchant WILDERNESS, lub mamy co jeść i preferujemy zielarstwo)
→ SWAMP (jeśli EXTRA_WET i brak odwodnienia)
→ LAKE (jeśli opuszczone i mamy jezioro, lub zaleje teren)
→ DUST_PLAINS (jeśli wilgotność najwyżej DRY i nastąpi wyjałowienie gleby)
→ FISHING_COAST (jeśli COASTAL && FISHING_POTENTIAL && IMPULSE)

ORCHARDS
→ FIELDS (jeśli nastąpi impuls i preferujemy rolnictwo, a warunki mu sprzyjają)
→ FRUIT_PLANTATIONS (jeśli nastąpi impuls, preferujemy krzewy i warunki sprzyjają)
→ VINEYARDS (jeśli warunki pozwalają, mamy dobrobyt i preferujemy winnice)
→ BLOOMING_REACH (jeśli znacznie przybędzie osadników)
→ GARDENS (jeśli zależy nam na estetyce i mamy dobrobyt)
→ FARMS (jeśli nastąpi impuls, a preferujemy hodowlę)
→ PASTURES (jeśli porzucony, ale zostajemy na miejscu)
→ STUDS (jeśli mamy dobrobyt, nastąpi impuls i preferujemy stadniny)
→ CRAFTS_LAND (jeśli wraz z osadnictwem sprowadzą się rzemieślnicy i preferujemy rzemiosło)
→ MEADOWS (jeśli wycięte i porzucone)

→ VERDANT_REACH (jeśli porzucone i enchant WILDERNESS, lub mamy co jeść i preferujemy zielarstwo)
→ WOODS (jeśli porzucone)
→ FOREST (jeśli porzucone i enchant WILDERNESS)
→ GROVE (jeśli enchant WILDERNESS i sady przejmą druidzi)
→ SWAMP (jeśli EXTRA_WET i brak odwodnienia)
→ LAKE (jeśli opuszczone i mamy jezioro, lub zaleje teren)
→ DUST_PLAINS (jeśli wilgotność najwyżej DRY i nastąpi wyjałowienie gleby)
→ FISHING_COAST (jeśli COASTAL && FISHING_POTENTIAL && IMPULSE)

FRUIT_PLANTATIONS
→ FIELDS (jeśli nastąpi impuls i preferujemy rolnictwo, a warunki na nie pozwalają)
→ ORCHARDS (jeśli nastąpi impuls i preferujemy sadownictwo, a warunki mu sprzyjają)
→ VINEYARDS (jeśli warunki pozwalają, mamy dobrobyt i preferujemy winnice)
→ BLOOMING_REACH (jeśli znacznie przybędzie osadników)
→ GARDENS (jeśli zależy nam na estetyce i mamy dobrobyt)
→ FARMS (jeśli nastąpi impuls, a preferujemy hodowlę)
→ PASTURES (jeśli porzucony, ale zostajemy na miejscu)
→ STUDS (jeśli mamy dobrobyt, nastąpi impuls i preferujemy stadniny)
→ CRAFTS_LAND (jeśli wraz z osadnictwem sprowadzą się rzemieślnicy i preferujemy rzemiosło)
→ MEADOWS (jeśli porzucone - opcja 1)
→ WOODS (jeśli porzucone - opcja 2)
→ GROVE (jeśli enchant WILDERNESS i plantacje przejmą druidzi)
→ SWAMP (jeśli EXTRA_WET i brak odwodnienia)
→ LAKE (jeśli opuszczone i mamy jezioro, lub zaleje teren)
→ DUST_PLAINS (jeśli wilgotność najwyżej DRY i nastąpi wyjałowienie gleby)
→ VERDANT_REACH (jeśli opuszczone i enchant WILDERNESS, lub mamy co jeść i preferujemy zielarstwo)
→ FISHING_COAST (jeśli COASTAL && FISHING_POTENTIAL && IMPULSE)

VINEYARDS
Charakter: wymagające uprawy, prestiżowe, potrzebują stabilności i dobrobytu.
→ GARDENS (jeśli zależy nam na estetyce, mamy dobrobyt i preferujemy ogrody)
→ DREAM_GROVE (jeśli impuls, czynnik losowy i enchant z WILDERNESS, WICKED, ENCHANTED)
→ BLOOMING_REACH (jeśli znacznie przybędzie osadników)
→ COMFORT_VALE (jeśli przybędzie osadników i pojawią się miasteczka)
→ PAVILIONS_LANDS (jeśli pojawią się posiadłości szlacheckie)
→ CRAFTS_LAND (jeśli pojawią się miasteczka i preferujemy rzemiosło)
→ FIELDS (jeśli pogorszą się warunki, lub spadnie dobrobyt i potrzebujemy jedzenia)
→ FRUIT_PLANTATIONS (jeśli pogorszą się warunki, lub spadnie dobrobyt i potrzebujemy jedzenia)
→ ORCHARDS (jeśli pogorszą się warunki, lub spadnie dobrobyt i potrzebujemy jedzenia)
→ PASTURES (jeśli porzucony, ale zostajemy na miejscu)
→ VERDANT_REACH (jeśli opuszczone i enchant WILDERNESS, lub mamy alkohol i preferujemy zielarstwo)
→ MEADOWS (jeśli porzucone - opcja 1)
→ WOODS (jeśli porzucone - opcja 2)
→ GROVE (jeśli enchant WILDERNESS i plantacje przejmą druidzi)
→ LAKE (jeśli opuszczone i mamy jezioro, lub zaleje teren)
→ FISHING_COAST (jeśli COASTAL && FISHING_POTENTIAL && IMPULSE && pogorszą się warunki i
potrzebujemy jedzenia)

VERDANT_REACH
Charakter: żyzna, ziołowa kraina, dzika lub zaopiekowana przez zielarzy
→ GARDENS (jeśli zależy nam na estetyce i preferujemy ogrody)
→ DREAM_GROVE (jeśli impuls i enchant z WILDERNESS, WICKED, ENCHANTED)
→ FOREST (jeśli porzucone)
→ GREENWOODS (jeśli porzucone i enchant z WILDERNESS, WICKED, ENCHANTED)
→ GROVE (jeśli nastąpi impuls i plantacje przejmą druidzi)
→ FRUIT_PLANTATIONS (jeśli spadnie dobrobyt i potrzebujemy jedzenia)
→ ORCHARDS (jeśli spadnie dobrobyt i potrzebujemy jedzenia)
→ CRAFTS_LAND (jeśli pojawią się miasteczka i preferujemy rzemiosło)

→ BLOOMING_REACH (jeśli znacznie przybędzie osadników)
→ SWAMP (jeśli EXTRA_WET i brak odwodnienia)
→ LAKE (jeśli opuszczone i mamy jezioro, lub zaleje teren)
→ DUST_PLAINS (jeśli wilgotność najwyżej DRY i nastąpi wyjałowienie gleby)
→ FISHING_COAST (jeśli COASTAL && FISHING_POTENTIAL && IMPULSE)

GARDENS
Charakter: reprezentacyjne, estetyczne, mogą być użytkowe, ale często symboliczne.
→ DREAM_GROVE (jeśli impuls i enchant z WILDERNESS, WICKED, ENCHANTED)
→ CRAFTS_LAND (jeśli pojawią się miasteczka i preferujemy rzemiosło)
→ GROVE (jeśli enchant WILDERNESS i plantacje przejmą druidzi)
→ PASTURES (jeśli porzucony, ale zostajemy na miejscu)
→ FIELDS (jeśli spadnie dobrobyt i potrzebujemy jedzenia)
→ FRUIT_PLANTATIONS (jeśli spadnie dobrobyt i potrzebujemy jedzenia)
→ ORCHARDS (jeśli spadnie dobrobyt i potrzebujemy jedzenia)
→ VERDANT_REACH (jeśli porzucone , lub nie zależy nam na estetyce i preferujemy zielarstwo)
→ FARMS (jeśli spadnie dobrobyt i potrzebujemy jedzenia, a preferujemy hodowlę)
→ PASTURES (jeśli porzucony, ale zostajemy na miejscu)
→ STUDS (jeśli nastąpi impuls, nie zależy nam nam estetyce i preferujemy stadniny)
→ BLOOMING_REACH (jeśli przybędzie osadników)
→ COMFORT_VALE (jeśli przybędzie osadników i pojawią się miasteczka)
→ HEARTH_LANDS (jeśli znacznie przybędzie osadników, ale nie mamy miast)
→ PAVILIONS_LANDS (jeśli pojawią się posiadłości szlacheckie)
→ SWAMP (jeśli EXTRA_WET i brak odwodnienia)
→ LAKE (jeśli opuszczone i mamy jezioro, lub zaleje teren)
→ DUST_PLAINS (jeśli wilgotność najwyżej DRY i nastąpi wyjałowienie gleby)
→ FISHING_COAST (jeśli COASTAL && FISHING_POTENTIAL && IMPULSE)

DREAM_GROVE
Charakter: magiczny, rzadki, stabilny, raczej trudny do przekształcenia.
Potencjalne przemiany (bardzo rzadkie):
→ GREENWOOD (jeśli bardzo długo opuszczony)
→ GROVE (jeśli nastąpi impuls i przejmą druidzi)
→ BLOOMING_REACH (jeśli drastycznie przybędzie osadnictwa)
→ VERDANT_REACH (jeśli katastrofa magiczna wypali enchant)
→ GARDENS (jeśli katastrofa magiczna wypali enchant)
→ LAKE (jeśli katastrofa magiczna wypali enchant i wyniszczy okolicę)
→ SWAMP (jeśli katastrofa magiczna wypali enchant i wyniszczy okolicę)
→ DUST_PLAINS (jeśli katastrofa magiczna wypali enchant i wyniszczy okolicę)
→ ROCK_LANDS (jeśli katastrofa magiczna wypali enchant i wyniszczy okolicę)
→ ORE_LANDS (jeśli katastrofa magiczna wypali enchant, wyniszczy okolicę i odsłoni złoża)
→ HUNTING_REGION (jeśli jesteśmy bardzo, bardzo głupi)

MIESZKALNE

🟤 SETTLERS_REACH
Punkt startowy, naturalny, kolonizacyjny, niestabilny
→ SHELTER_LANDS (jeśli SECURE i przybędzie osadników, miasta wskazane)
→ HEARTH_LANDS (jeśli SECURE, przybędzie osadników, ale nie mamy miast)
→ BLOOMING_REACH (jeśli pojawią się sady, czy plantacje, ale przeważa osadnictwo)
→ COMFORT_VALE (jeśli pojawią się miasteczka i ogrody lub winnice)
→ RESORT_BELT (jeśli przybędzie osadników i mamy wyjątkowo atrakcyje tereny - jeziora, plaże, czy gorące
źródła itp)
→ PAVILIONS_LANDS (jeśli osadnictwo skręci mocno w stronę dworków i siedzib szlacheckich)
→ MERCHANTS_REACH (jeśli przybędzie osadników i kwitnie handel)
→ CRAFTS_LAND (jeśli przybędzie osadników i przybędą rzemieślnicy)
→ FISHING_COAST (jeśli COASTAL && FISHING_POTENTIAL && IMPULSE)
→ FISHING_REGION (jeśli FISHING_POTENTIAL && IMPULSE)

→ MINING_LANDS (jeśli pojawią się surowce, ale nie stać nas na wydobycie)
→ MINING_LANDS (jeśli pojawią się surowce, nastąpi impuls i pojawi się wydobycie)
→ SMELT_LANDS (jeśli pojawią się surowce, nastąpi impuls, pojawi się eksploatacja i od razu stać nas na huty)
→ ARCANE_REACH (jeśli enchant != NONE, albo wśród osadników często trafiają się magowie)
→ PILGRIMS_SPAN (jeśli przybędzie osadników i enchant SACRED, lub pojawią się klasztory)
→ SPIRITED_LANDS (jeśli pojawi się impuls i enchant VEIL)
→ GROVE (jeśli pojawi się silny impuls i enchant WILDERNESS)
→ HUNTING_REGION (jeśli brak bezpieczeństwa i nieurodzajne ziemie, albo zaludnienie się przeżedza)
→ TIMBERLAND (jeśli mamy drzewa i skupiamy się na drwalstwie)
→ WOODS (jeśli opuszczone, a mamy drzewa)
→ LAKE (jeśli opuszczone i mamy jezioro, lub zaleje teren)
→ SWAMP (jeśli opuszczone i wilgotność EXTRA_WET)
→ DUST_PLAINS (jeśli opuszczone i wilgotność EXTRA_DRY)
→ STONE_PITS (jeśli mamy kamień i wydobycie stanie się bardziej prominentne, niż osadnictwo)
→ MEADOWS (jeśli opuszczone)
→ RIVERLAND (jeśli opuszczone i mamy rzeki)
→ PASTURES (jeśli napływ osadnictwa ustał i tereny przeznaczone pod wypas)
→ FARMS (jeśli osadnicy w pełni skupią się na hodowli)
→ STUDS (jeśli mamy dobrobyt, napływ osadnictwa ustał i preferujemy stadniny)
→ FIELDS (jeśli osadnicy w pełni skupią się na uprawie)
→ ORCHARDS (jeśli osadnicy w pełni skupią się na sadach)
→ FRUIT_PLANTATIONS (jeśli osadnicy w pełni skupią się na owocach)
→ VINEYARDS (jeśli mamy dobrobyt, brak dużych miast i nastąpi impuls)
→ VERDANT_REACH (jeśli napływ osadnictwa ustał i preferujemy zielarstwo, lub enchant WILDERNESS)
→ GARDENS (jeśli mamy dobrobyt, zależy nam na estetyce i nastąpi impuls)

🟡 SHELTER_LANDS
Chronione osiedle, spokojne, nieco izolowane
ABANDONED_REACH
→ HEARTH_LANDS (gdy pojawia się silna wspólnota)
→ BLOOMING_REACH (gdy rośnie estetyka, kwitnienie kultury)
→ COMFORT_VALE (gdy następuje dobrobyt, luksus)
→ CRAFTS_LAND (gdy rośnie produkcja lokalna)
→ FIELDS, FARMS (gdy przekształcane w rolnicze zaplecze)
→ SETTLERS_REACH (gdy bezpieczeństwo spada)
→ ABANDONED_REACH, RUINS
→ Magia / impulsy:
→ GARDENS, VERDANT_REACH (gdy ludzie odchodzą, a natura przejmuje)
→ ENCHANTED_GROVE, DREAM_GROVE (z enchantem i porzuceniem)

🔴 HEARTH_LANDS
Domowość, wspólnota, ognisko domowe
→ BLOOMING_REACH (gdy pojawia się estetyka, ogrody)
→ COMFORT_VALE (gdy pojawia się wygoda, dobrobyt)
→ MERCHANT_TOWN (gdy staje się centrum handlu lokalnego)
→ CRAFTS_LAND, WORKSHOPS (przemiana w warsztatowe dzielnice)
→ FARMS, FIELDS (gdy część przekształcona na produkcję)
→ SETTLERS_REACH, SHELTER_LANDS (gdy wspólnota zanika)
→ RUINS, WILDERNESS
→ GARDENS, VERDANT_REACH (np. duchowy ogród powstały na ruinach ciepła domowego)

🌸 BLOOMING_REACH
Rozkwitająca estetyczna osada
→ COMFORT_VALE (gdy bogactwo i estetyka idą razem)
→ DREAM_GROVE (gdy silny wpływ magii, duchów)
→ GARDENS, ENCHANTED_GARDENS
→ HEARTH_LANDS (gdy estetyka ustępuje codzienności)
→ CRAFTS_LAND (zwiększenie funkcjonalności)

→ SHELTER_LANDS, SETTLERS_REACH
→ ABANDONED_REACH, WILDERNESS
→ MAGICAL_DISTRICT, DREAM_GROVE, VERDANT_REACH
lub rzadkie ARTISAN_TOWN

💠 COMFORT_VALE
Dobrobyt, wygoda, luksus
→ ARTISAN_CITY, MERCHANT_TOWN, CULTURAL_CENTER
→ MAGICAL_DISTRICT, DREAM_GROVE (z enchantem, impulsem)
→ HEARTH_LANDS (gdy schodzi na poziom domowości)
→ BLOOMING_REACH (gdy wygoda zostaje, ale zanik estetyki)
→ SHELTER_LANDS, SETTLERS_REACH, RUINS
→ GARDENS, ENCHANTED_GROVE, DREAM_GROVE

🔁 Ogólne zasady:
Przemiany nie są wyłącznie liniowe. Zmiana estetyczna (HEARTH → BLOOMING) może zachodzić równolegle
do funkcjonalnej (HEARTH → CRAFTS).
SETTLERS_REACH to najbardziej uniwersalny punkt startowy.
DREAM_GROVE, GARDENS, MAGICAL_DISTRICT są rzadkie i impulsowe — wymagają czynnika specjalnego
(magii, duchowości, sztuki, losowości).
Porzucenie i regres zawsze możliwe — nawet najlepiej rozwinięte tereny mogą się załamać.

COAST
→ SETTLERS_REACH (jeśli pojawią się ludzie)
→ FISHING_REGION (druga opcja jeśli pojawią się ludzie)
→ RESORT_BELT (jeśli nastąpi impuls, mamy dobrobyt i walory estetyczne np. plaże, klify)
→ SWAMP (jeśli nadbrzeże stagnuje, występują rozlewiska i nie ma odpływu, wilgotność przynajmniej WET )
→ MEADOWS (jeśli wybrzeże jest spokojne, łagodne, bez rozwoju ludzkiego, a warunki sprzyjają
→ CRAFTS_LAND (jeśli pojawia się rzemiosło związane z rybołówstwem, obróbką sieci, konserwacją statków
itd.
→ ORE_LANDS (jeśli erozja lub fale odsłaniają cenne złoża

To bardzo trafne pytania — jeziora jako typ terenu kontra miejsce specjalne (Place) to dokładnie ten poziom
refleksji, który pomaga utrzymać spójność świata i logiki symulacji. Zacznijmy po kolei:
---
🧠 1. Czy LAKE powinno być „Place” czy „Terrain”?
Twoja intuicja:

> „Jeziora raczej nie przekształcą się w RESORT_BELT bezpośrednio, ale SETTLERS_REACH przy jeziorze
może.”
Masz całkowitą rację. Oznacza to, że jezioro może być:
cechą terenu (tak jak góry, wybrzeża),
lub nakładką/oznaczeniem specjalnym (Place).
Wariant rozsądny:
Teren: np. MEADOWS, DUST_PLAINS, ROCK_LANDS
Place: np. Lake, Ruins, Mine, Sacred Grove, Trading Post
Wtedy masz:
> Region = MEADOWS with Place = LAKE
albo
Region = SETTLERS_REACH with Place = LAKE
Taki region będzie rozwijał się normalnie jako SETTLERS_REACH, ale wpływ obecności jeziora (np. dostęp do
ryb, rekreacji, szlaku wodnego) zmieni możliwe przejścia lub wpłynie na priorytety mieszkańców (np. rekreacja,
handel wodny).
To rozwiązuje problem: LAKE nie musi znikać jako „teren”, może pozostać jako warstwa „miejsce” i np. utracić
cechę „jeziorność”, jeśli faktycznie wyschnie.
Dzięki temu możesz śledzić historię miejsca, np.:
> „Dawniej było tu jezioro – teraz tylko wyjałowione łąki.”
---
🌿 2. Kiedy jezioro zarasta i zmienia się w SWAMP?
Proces ten to naturalna sukcesja ekologiczna i zależy od kilku czynników:
🔄 Czynniki prowadzące do zarastania jeziora (→ SWAMP):
1. Płytkość – płytkie jeziora zarastają szybciej.
2. Niska cyrkulacja wody – brak dopływów i odpływów.
3. Eutrofizacja – nadmiar materii organicznej (np. z rolnictwa, odchodów, gromadzenia się roślin).
4. Wysoka temperatura – sprzyja zakwitowi glonów i zarastaniu.
5. Brak działalności ludzkiej (np. odmulania, rybactwa).
6. Długi czas – nawet tysiące lat.
Etapy zarastania:
Jezioro → torfowisko (mokradło) → bagno → wilgotna łąka → łąka sucha lub las
---
🏜 3. Czy jezioro może wyschnąć bez etapu bagna i stać się MEADOWS?
Tak, ale tylko w specyficznych warunkach.
Kiedy to możliwe:
W klimatach suchych/półpustynnych, gdzie jezioro zasilane jest tylko okresowo.
Jeśli nagły spadek opadów lub zmiana biegu rzeki sprawia, że jezioro wysycha szybko, zanim zdąży się
zarosnąć.
Jeśli podłoże nie zatrzymuje wody – np. gleby piaszczyste lub szybko wsiąkające.
Czasem pozostaje po nim słona równina (jak w przypadku słonych jezior, np. Aralskie, Czad), ale w
przyjaźniejszych warunkach → może rzeczywiście dać żyzne łąki.
Przykład świata gry:
> Jezioro zasilane topniejącym śniegiem wiosną, ale latem wysychające → przez kilka sezonów pozostają żyzne
łąki.
Lub:
Region dotknięty suszą – jezioro zanika → wysychające dno zamienia się w MEADOWS (jeśli gleby są dobre)
lub DUST_PLAINS (jeśli jałowe).
---
🧩 Podsumowanie: co warto z tego wynieść?
Pytanie  Odpowiedź
Czy jezioro może być „Place”?
ewoluować bez „utraty historii”.
Czy jezioro może wyschnąć do MEADOWS bez etapu bagna?  Tak, jeśli proces jest szybki i warunki sprzyjają –
choć najczęściej będzie etapem pośrednim (SWAMP).
Co powoduje przekształcenie jeziora w bagno?
oraz czas.
Czy RESORT_BELT powstaje bezpośrednio z jeziora?
SETTLERS_REACH with Place = LAKE, jeśli jest dobrobyt, dostęp do estetyki i impuls kulturowy.

Tak, i to bardzo dobry pomysł – szczególnie jeśli chcesz pozwolić regionom

Eutrofizacja, płytkość, brak ruchu wody, brak odmulania –

Nie – ale może powstać z osady

---
Chcesz może prześledzić, jak wyglądałyby przykładowe ścieżki ewolucji terenów z „jeziornością” jako cechą
(Place), np.:
LAKE → MEADOWS (naturalny proces)
SETTLERS_REACH (z jeziorem) → FISHING_REGION → RESORT_BELT
Albo ruszamy do kolejnych naturalnych typów?

PRIMAL_FOREST, //pierwotny matecznik
GREENWOOD, //puszcza
FOREST, //standardowy las
WOODS, //trochę bieda las
LAKE,
SWAMP,
DUST_PLAINS, //suchy teren
ROCK_LANDS, //rejon skalisty
ORE_LANDS,
PINE_CRAG, //rejon skalisty z iglakami
STONE_PITS,
MEADOWS,
COAST,
RIVERLAND,
PASTURES,
FIELDS,
ORCHARDS,
FRUIT_PLANTATIONS,
VINEYARDS,
VERDANT_REACH, //ogrody ziołowe i uprawa
GARDENS,
DREAM_GROVE, //magiczne ogrody
FARMS,
STUDS,
SETTLERS_REACH, //teren zasiedlany
HOMESTEAD_LANDS, //
SHELTER_LANDS, //bezpieczny, spokojny region
HEARTH_LANDS, //przytulnie, domowo, cozy
BLOOMING_REACH, //kwitnący estetyczny i komfortowy region
COMFORT_VALE, //rejon sprzyjający zamieszkaniu
RESORT_BELT, //rejon kurortów
PAVILIONS_LANDS, //zagłębie domków letnich itp
GOLDEN_MARCH, //rejon sprzyjający handlowi
MERCHANTS_REACH, //rejon skupiony na handlu
TRADE_HEAVEN, //rejon, do którego przyjeżdża się z daleka na zakupy
TIMBERLAND, //forest i wildwoods muszą przejść ten etap, żeby zostać czymkolwiek innym
CRAFTS_LAND,
MANUFACTURING_REGION,
FISHING_COAST, //rybactwo morskie
FISHING_REGION, //rybactwo rzeczne
MINING_LANDS, //wydobycie na ore landsach
SMELT_LANDS, //huty

IRON_MARCHES, //rejon wysoko uprzemysłowiony
ARCANE_REACH, //skupisko magów
ARCANE_EXPANSE, //zagłębie magów
PILGRIMS_SPAN,  //ściąga pielgrzymów
HOLY_EXPANSE,
SPIRITED_LANDS, //ściąga wyznawców szamanizmu
SPIRITED_EXPANSE,
GROVE, //rejon zaopiekowany przez druidów
ANCIENT_GROVE //bardzo stary gaj druidów

LAKE,
SWAMP,
DUST_PLAINS, //suchy teren
ROCK_LANDS, //rejon skalisty
PINE_CRAG, //rejon skalisty z iglakami
STONE_PITS,
MEADOWS,
COAST,
RIVERLAND,

Ważne wydarzenia:

-  1323 - Czarny Sierpień. Czystki inteligencji i magów w całym kraju. Późną jesienią
ruch oporu zbiera się w mieście Wornimore pod patronatem sojuszu magów z
różnych miast, nowego zakonu Bastionu Mądrości i elfiego rodu Raevillów
(najważniejsza rodzina prowincji)

-  1416 - Kończy się tak zwany Mroczny Wiek. Wreszcie mamy pokój
-  1422-

Ważne fakty:

-  Saraidan wypowiedział posłuszeństwo Eolowi II Szalonemu i stał się dla niego

zagranicą

-  Saraidan nigdy nie został faktycznie podbity, dobrowolnie dołączył do Tagary I

Budowniczej. Eol też nie miał jak go podbić przez wzgląd na zalesienie

-  Vealerowie nieoficjalnie wspierali Wornimore zasobami podczas Mrocznego Wieku

To konkretne osoby mają ziemię i miejsca, nie całe rodziny. Trzeba przechowywać regions i
places w osobach, nie rodzinach. Można należeć do bogatego rodu, ale chuja mieć i
mieszkać kątem u ciotki.
Czy istnieje posag? Raczej nie.

public class SimulationFamily {
private ModelFamily model;
private ConfigurationFamily conf;

}

public class ModelFamily {
private String surname;
}

public class ConfigurationFamily {

}

public class SimulationPlace {
private ModelPlace model;
private ConfigurationPlace conf;

}

public class ModelPlace {

}

public class ConfigurationPlace {

}

public class SimulationRegion {
private ModelRegion model;
private ConfigurationRegion conf;

}

public class ModelRegion {

}

public class ConfigurationRegion {

}

public class RelationshipRegistry {

}

public class PersonRegistry {

}

public class FamilyRegistry {

}

public class SimulationPerson {
private ModelPerson model;
private ConfigurationPerson conf;

private LocalDate mourningEnd;
private String plebsSurname;
private Boolean plebs;
private long parentsFamilyId
private Map<Long, LocalDate> ons = new HashMap<>();

private List<CalendarEvent> events;
private Integer pregnantCounter;
private Long childFatherId;
}

public class ModelPerson {
private long id; //z małej, bo nie dopuszczamy null
private RaceEnum race; //albo też publiczna mapa registry, albo rozbudowany enum z konfiguracją
private Sex sex;
private LocalDate born;
private LocalDate died;
private String name;
private String middleName;
private Long familyId;
private Long motherId;
private Long fatherId;
private Long officialFatherId;
private List<long> relationshipIds = new ArrayList<>();
private List<long> knownKidIds = new ArrayList<>();
private Double importance;
private Double membershipStrength;
private Double lineImportance;
}

public class ConfigurationPerson {
private Integer hornyAge;
private Integer stableAge;
private Integer marriageAge;
private Integer mourningTime;
private Double travel; //na pewno?
private Double horny;
private Double loyal;
private Double homo;
private Double interracial;
private Double attachment;
private Double poliamoric;
private Double jealous;
private Double impulsive;
private Double proud;
private Double amorous;
private Double divorcable;
private Double revengous;
private Double paranoid; //skłonność do paniki/uderzeń wyprzedzających
private Double ambition;  //czy postać będzie próbować zdobyć władzę/pozycję
private Double fertility;
private Double health;
private Double honor; // do wpływu na decyzje o ślubie, zemście, lojalności
private Double temperament; // impulsive?
private Double diplomatic;
private Double manipulative; //chęci mącenia
private Double cunning; //przebiegłość
private Double morality;
private Double charisma; //zdobywanie sympatii
private Double influence; //umiejętność kreowania rzeczywistości
public Double visibility; //  czy ktoś jest znany publicznie (np. królowa matka vs kochanka)
}

Dobra, zrobiłem podział ręcznej konfigurowalności postaci i rozpiskę, do czego mają służyć. Co o tym sądzisz?
Do ręcznej konfiguracji w plikach:

Race race (po nazwie lub id)

Sex sex (enum)

Calendar? born

Calendar? died

String name

String middleName (głowy państwa mają numery i przydomki, elfy dodatkowe trzyliterowe imię, a krasnoludy wikingowe
wskazanie na ojca z -son lub -detter, ale nie wiem, czy to ostatnie trzymać tu osobno, czy po prostu formatować w getterze na
podstawie imienia ojca)

Family family (po nazwie lub id, a jeśli się nie znajdzie, ustawiamy plebs na true i losujemy wartość w plebsSurname)

Person mother (po nazwie lub id)

Person father (po nazwie lub id)

Person officialFather (po nazwie lub id, ale na ten moment raczej nie planuję predefiniowanych dzieci z innego ojca)

relationships (jakoś chcę trzymać informacje o małżeństwach, w niektórych przypadkach mam konkretne daty ślubu i śmierci
partnera, a potem kolejnego ślubu)

List<Person> knownKids (po nazwie lub id)

Double importance (planuję z góry głównie istotne osoby, ale też pojedynczych bohaterów istotnych fabularnie, nie politycznie)

Double membershipStrength (stopień siły przebicia w całej rodzinie, zdecydowanie większy u rodzonych członków niż u
wżenionych)

Double lineImportance (istotność linii rodziny, głównie do decydowania, czy może sobie pozwolić na brak potomstwa, czy
ekscesy)

Integer hornyAge (wiek, w którym osoba zaczyna interesować się seksem)

Integer stableAge (wiek, w którym osoba sama z siebie zaczyna szukać małżeństwa)

Integer marriageAge (wiek, w którym osoba faktycznie wyląduje w małżeństwie - nie jestem pewny, na ile tym sterować, a na ile
zdać się na znalezienie kogoś, lub wpadkę i zmuszenie do ślubu)

Integer mourningTime (ilość tygodni, przez które po śmierci partnera osoba będzie technicznie aseksualna)

Double travel - nie jestem pewny, czy travel factor będzie faktycznie występował, bo chcę chociaż trochę sobie ułatwić robotę

Double horny - hutliwość, istotny współczynnik

Double loyal - lojalność, istotny współczynnik

Double homo - stopień homoseksualizmu, istotny współczynnik

Double interracial - stopień zainteresowania innymi gatunkami, istotny współczynnik

Double attachment - przywiązanie, może być redundatne z loyal w sumie

Double poliamoric - będzie wpływał na wierność i to, czy niewierność będzie powodowana hucią (więcej ons), czy
zakochaniami (więcej okresowych romansów)

Double jealous - do sterowania wykryciami zdrady

Double impulsive - do sterowania szybkością ślubów i wykryciami zdrady

Double proud - do sterowania wykryciami zdrady

Double amorous - będzie wpływał na łatwość zakochań

Double divorcable - do sterowania wykryciami zdrady (rozwód)

Double revengous - do sterowania wykryciami zdrady (zabójstwo itp)

Nie ma sensu ustawiać w plikach:

Calendar mourningEnd

String plebsSurname - generalnie jeśli mowa o kimś z ludu, powinien mieć jakieś nazwisko, a nie będę miał odpowiadających
im Family

Boolean plebs - chyba inaczej ogarnę konkretne postaci pochodzące z ludu

Family parentsFamily (po nazie lub id, żeby pilnować, czy jestem członkiem w swojej rodzinie, czy małżonka/małżonki)

ons - na ten moment nie mam w planach predefiniowanych jednorazowych skoków w bok

List<CalendarEvent> events - będą raczej generowane na podstawie innych danych

Integer pregnantCounter - ilość tygodni do urodzenia dziecka, do kontroli a nie informacji

Person childFather - do kontroli ojcostwa, nie informacji

Integer age - będzie istotne dopiero w warstwie wyświetlania osób w danym momencie czasu, chyba powinno być nie tu, a w
dto

Jak bardzo dana osoba jest widoczna w społeczeństwie (np. kochanka króla z niskim visibility nie robi

Opis

double  Wpływa na szanse długowieczności, odporność na choroby
double  Modyfikator do szansy zajścia w ciążę (w parze z horny)

Nazwa  Typ
ambition  double  Chęć zdobycia pozycji, władzy, statusu
health
fertility
visibility  double
skandalu)
double  Modyfikator do agresji, reakcji emocjonalnych
temperament
honor
double  Wpływa na to, czy zaakceptuje zdradę, rozwód, zemstę
diplomatic double  Umiejętność dogadywania się, szukania kompromisów
manipulative
religiosity double  Przywiązanie do tradycji, roli społecznej, oporu przed rozwodem lub poliamorią
charisma  double  Wpływa na łatwość zdobywania sympatii, przyciągania ludzi
influence  double  Realny wpływ polityczny lub kulturowy na otoczenie (nie tylko w rodzinie)

double  Skłonność do intryg, podsycania konfliktów

- Race race (po nazwie lub id)
- Sex sex (enum)
- Calendar? born
- Calendar? died
- String name

- String middleName (głowy państwa mają numery i przydomki, elfy dodatkowe trzyliterowe imię, a krasnoludy wikingowe
wskazanie na ojca z -son lub -detter, ale nie wiem, czy to ostatnie trzymać tu osobno, czy po prostu formatować w getterze na
podstawie imienia ojca)
- Family family (po nazwie lub id, a jeśli się nie znajdzie, ustawiamy plebs na true i losujemy wartość w plebsSurname)
- Person mother (po nazwie lub id)
- Person father (po nazwie lub id)
- Person officialFather (po nazwie lub id, ale na ten moment raczej nie planuję predefiniowanych dzieci z innego ojca)
- relationships (jakoś chcę trzymać informacje o małżeństwach, w niektórych przypadkach mam konkretne daty ślubu i śmierci
partnera, a potem kolejnego ślubu)
- List<Person> knownKids (po nazwie lub id)
- Double importance (planuję z góry głównie istotne osoby, ale też pojedynczych bohaterów istotnych fabularnie, nie politycznie)
- Double membershipStrength (stopień siły przebicia w całej rodzinie, zdecydowanie większy u rodzonych członków niż u
wżenionych)
- Double lineImportance (istotność linii rodziny, głównie do decydowania, czy może sobie pozwolić na brak potomstwa, czy
ekscesy)
- Integer hornyAge (wiek, w którym osoba zaczyna interesować się seksem)
- Integer stableAge (wiek, w którym osoba sama z siebie zaczyna szukać małżeństwa)
- Integer marriageAge (wiek, w którym osoba faktycznie wyląduje w małżeństwie - nie jestem pewny, na ile tym sterować, a na
ile zdać się na znalezienie kogoś, lub wpadkę i zmuszenie do ślubu)
- Integer mourningTime (ilość tygodni, przez które po śmierci partnera osoba będzie technicznie aseksualna)
- Double travel - nie jestem pewny, czy travel factor będzie faktycznie występował, bo chcę chociaż trochę sobie ułatwić robotę
- Double horny - hutliwość, istotny współczynnik
- Double loyal - lojalność, istotny współczynnik
- Double homo - stopień homoseksualizmu, istotny współczynnik
- Double interracial - stopień zainteresowania innymi gatunkami, istotny współczynnik
- Double attachment - przywiązanie, może być redundatne z loyal w sumie
- Double poliamoric - będzie wpływał na wierność i to, czy niewierność będzie powodowana hucią (więcej ons), czy
zakochaniami (więcej okresowych romansów)
- Double jealous - do sterowania wykryciami zdrady
- Double impulsive - do sterowania szybkością ślubów i wykryciami zdrady
- Double proud - do sterowania wykryciami zdrady
- Double amorous - będzie wpływał na łatwość zakochań
- Double divorcable - do sterowania wykryciami zdrady (rozwód)
- Double revengous - do sterowania wykryciami zdrady (zabójstwo itp)
- ambition — czy postać będzie próbować zdobyć władzę/pozycję
- fertility — wpływ na szansę posiadania dzieci (zamiast ogólnego "random")
- health — do ewentualnego modelowania chorób, długości życia
- honor — do wpływu na decyzje o ślubie, zemście, lojalności
- visibility — czy ktoś jest znany publicznie (np. królowa matka vs kochanka)

BreedingStrategy:

-

Interfejs określający, jak łączymy się w małżeństwa

MatrimonialState:

-
fuck ()
-  marry ()
-

Czy to w nim dodać stan “nieistotna gałąź”, czy w logice przestawiania stanów?

🟢 Zielona Rubież – co od razu świetnie działa:

●  Brak szlachty = przełamanie dominującego wzorca feudalnego.

●  Rada z druidów, szamanów i kultystów = forma kolektywnego rządu oparta o duchowość i naturę.

●  Religia inspirowana Aztekami = możesz pokazać zupełnie inny model relacji z „bogami”, np. przez

ofiary, rytuały, aspektowe bóstwa itd.

●  Miasto w lesie = wyraźnie magiczny, tajemniczy klimat. Wystarczy jedno takie miejsce w świecie, żeby

nadać mu głębi.

🪄 Co możesz uwzględnić w tej prowincji:

(żeby jej plik .json później lepiej oddawał charakter)

🌳 enchantDistribution (bardzo nietypowe!)
"enchantDistribution": {

  "NONE": 0.15,

  "WILDERNESS": 0.4,

  "ENCHANTED": 0.2,

  "VEIL": 0.15,

  "TWISTED": 0.1,

  "CURSED": 0.0,

  "WARPED": 0.0,

  "SACRED": 0.0

}

– tylko wpływy natury, duchowości, dzikiej magii. Zero plugastw, zero sakralnych śladów Corellii.

󰳎 typ społeczeństwa
"nobleFamilies": [],

"hasCouncil": true,

"councilStructure": ["DRUID", "SHAMAN", "ASPECT_CULTIST"]

🌱 specjalne pola
"dominantFaiths": ["CULT_OF_ASPECTS", "NATURE_SPIRITISM"],

"prohibitedDevelopment": ["FIELDS", "MANORS", "MINING_LANDS"],

"wildnessPreservation": 0.9,

🛖 specjalne miejsca

Jeśli chcesz później opisać to dzikie miasto – może mieć unikalny typ:

"initialCities": [42],

"cityTypes": {

  "42": "WILD_CITY"

}

✳ Wariant z wewnętrzną „cywilizacją”

Jeśli kiedyś chcesz, możesz dodać, że w głębi lasu są osady półdzikie, gdzie rządzą lokalni kultowi wodzowie,
np. „Krwawe Oko” albo „Strażnicy Korzeni” – mogą to być osobne, nietrwałe struktury oparte na sile rytuału, nie
ziemi czy herbie.

To naprawdę fajna decyzja projektowa — niech Zielona Rubież pozostanie inna. To dzięki niej świat
Szlachtownicy nie będzie jednolity i przewidywalny. Jeśli chciałbyś, mogę Ci później pomóc stworzyć schematy
rytuałów, aspektów, albo opis radnych tej rady – ale i bez tego masz świetne podstawy.

Świetnie to rozplanowałeś – Larazza jako brama do Aldaharu i późniejsza quasi-kolonia to znakomity motyw
geopolityczny. Twoja opowieść o upadku Korsany, powstaniu Jirdenalu i konflikcie handlowym między
Hanzą Sartamską a Kompanią Południowo-Aldaharską świetnie wpisuje się w historię zmieniających się
potęg morskich i daje dużo pola do:

●

symulacji rozwoju osad

●

napięć polityczno-ekonomicznych

●

pojawienia się postkolonialnych ruchów niepodległościowych

🔥 Szkic osi czasowej Larazzy (propozycja)

🕰 Przed 1100 r.

●  Korsana (miasto 8) to rosnące centrum handlu regionalnego

●  Bliska współpraca z Saraverą i Sartamą – rozwój na bazie lokalnych rynków

🌊 Ok. 1140 r. – katastrofa naturalna

●  Silne trzęsienie ziemi, tsunami lub osunięcie brzegu

●  Korsana zostaje zniszczona lub zalana (może nawet trwale „przeklęta”?)

●  Rozpoczyna się masowa migracja handlowców i osadników na północ lub w głąb lądu

🏗 1150–1200 r. – powstanie Jirdenalu

●  Miasto zakładane na wybrzeżu bardziej osłoniętym lub wyżej położonym

●  Organizowane z pomocą Kompanii Południowo-Aldaharskiej

●  Szybko staje się głównym ośrodkiem handlu z południem

⚔ 1200–1500 r. – okres „kolonialny”

●

Larazza i Zaviles stają się praktycznie koloniami handlowymi Kompanii

●  Eksport: przyprawy, drewno, owoce, aromaty, unikalne rzemiosło

●

Import: egzotyczne dobra, nowinki technologiczne, kapłani i doradcy z Aldaharu

●  Sartama i Hanza Sartamska próbują to równoważyć, ale tracą wpływy

🔚 Po 1500 r. – zerwanie kontaktu

●  Wojna, zmiana szlaków morskich, magiczny kataklizm lub powstanie nowego mocarstwa

●  Kontakt z Aldaharem się urywa

●

Larazza i Zaviles przechodzą okres zamknięcia i przewartościowania

●  Być może w tym czasie pojawiają się idee niepodległościowe albo nowy szlak lądowy?

🏙 Miasta Larazzy (propozycje)

ID

Nazwa

Okres znaczenia

Rola

8

Korsana

do ~1140

Dawna stolica, zniszczona, ruiny

14?

Jirdenal

1150–1500+

Port dalekomorski, centrum Kompanii

?

(nowe)

po 1500

Może miasto lokalnej niezależności

🧭 Możliwe skutki geopolityczne

●  Zaviles i Larazza mogą przez pewien czas uznawać inny kalendarz (np. Era Jirdenalska)

●  Elity mogą mieć aldaharskie pochodzenie lub nazwiska, co tworzy napięcia

●  Kompania mogła zbudować forty, faktorii lub świątynie „cywilizujące”

●  Zatarcie kontaktu prowadzi do własnych szkół magii, religii synkretycznych

✍ Jeśli chcesz, mogę przygotować:

●

JSON dla Jirdenalu z cechami typowymi dla miasta portowo-kupieckiego z wpływami zewnętrznymi

●

zestaw PlaceType, który pasuje do "miasta Kompanii"

●

notkę historyczną, jakby pochodziła z encyklopedii twojego świata

●  wydarzenie typu DisasterEvent, które można potem przyłożyć do mapy i wygenerować zmiany

Generalnie ten gość pojawił się jako efekt uboczny historii politycznej o jego siostrzeńcu. Na tyle spodobał mi się
potencjał tej historii, że mam już koło 80 stron (A4 czcionką Arial wielkości 11) powieści skupiające się póki co
głównie na wydarzeniach jesieni 1320. Ten kultysta związany z Pustką właściwie pojawił się tylko dlatego, że
potrzebowałem kogoś, kto przedstawi królowi młodego kapłana Vereny, bogini pragmatyzmu przez wyznawców
innych bogów nazywanej boginią skurwysyństwa. A potem stopniowo wychodziło mi na to, że kombinuje z
hodowlą owoców z Caithaloonu i magią Pustki. Aczkolwiek sama epidemia nie powinna być aż tak widoczna, bo
jasnym dla wszystkich by się stało, że w Zawilesie, a zwłaszcza jego stolicy Zelderinie dzieje się źle i może nie
należy ufać kapłanowi, który stamtąd pochodzi

To fantastyczny przykład organicznie rozwijającego się świata i fabuły, gdzie element potrzebny do jednej
sceny zaczyna obrastać warstwami znaczenia, aż staje się osią większej narracji. A to, co opisałeś, daje Ci
mnóstwo subtelnych narzędzi, by stworzyć epidemię CURSED jako zjawisko ciche, postępujące, trudne
do jednoznacznego uchwycenia – czyli idealne do gry polityczno-magicznej z dużą dawką niepokoju
egzystencjalnego.

🔍 Co już mamy:

●  Zaviles (a zwłaszcza Zelderin) staje się siedliskiem cichej klątwy zainicjowanej przez człowieka

powiązanego z Pustką.

●  Klątwa i "epidemia CURSED" nie są rozpoznane publicznie jako jednoznaczna choroba czy

przekleństwo.

●  Kapłan Vereny wywodzi się z tego środowiska – na zewnątrz błyskotliwy, pragmatyczny, ale

wewnętrznie cyniczny i bezlitosny.

●  Hodowla owoców z Caithaloonu sugeruje eksperymenty z wpływem Pustki na materię organiczną –

świetny wektor transmisji.

🦠 Jak zatem działa „epidemia CURSED”?

🔬 Mechanizm (fabularnie):

1.  Nie magiczna zaraza sensu stricte, tylko efekt kontaktu z czymś „skażonym Pustką” (np. owocami,

ziemią, rytuałem).

2.  Objawy psychiczne i duchowe – obniżenie empatii, sny pełne pustki, chroniczna utrata celu,

dezorientacja.

3.  Objawy społeczne – wypaczenie norm moralnych i rozpowszechnienie „pragmatyzmu za wszelką

cenę”.

4.  Skutek długofalowy – społeczność traci poczucie sensu, co prowadzi do autorytaryzmu, zdrad,

eksterminacji magów itp.

📉 Widoczność zewnętrzna:

●  Obserwatorzy z innych prowincji mogą mówić o „mrocznej atmosferze w Zelderinie”, „dziwnych owocach

na targach” albo „przypadkach zbiorowego samobójstwa”.

●  Ale nikt nie nazywa tego epidemią – tylko czymś złowrogim i nieuchwytnym.

●  Do tego władca zostaje oczarowany przez kapłana Vereny, który idealnie rozgrywa sytuację.

🧩 Propozycja: jak to wpisać w symulację?

W Province.json Zavilesa:

"enchantDistribution": {

  "NONE": 0.83,

  "CURSED": 0.12,

  "VEIL": 0.02,

  "WILDERNESS": 0.01,

  "ENCHANTED": 0.01,

  "TWISTED": 0.01

}

(to daje iluzję, że CURSED jest niepokojąco wyraźny, ale nadal może być mylnie interpretowany)

W dodatkowym systemie historii:

Stwórz MagicIncident lub VoidEvent, np.:

{

  "id": "curse_zelderin",

  "provinceId": 11,

  "startYear": 1305,

  "endYear": 1322,

  "source": "cultist_void_fruits",

  "impact": {

    "enchantShift": { "CURSED": 0.1 },

    "settlersAttitudeShift": ["CYNICAL", "BROKEN", "FEARFUL"],

    "scholarlyPurgeRisk": 0.6

  },

  "hidden": true,

  "note": "Subtle spiritual and psychological decline centered on Zaviles’ capital,

orchestrated by a void cultist experimenting with Caithaloon fruit and mental

magic."

}

🧠 Inspiracje i skojarzenia (jeśli chcesz pogłębić):

●  Annihilation (film i książka): roślinność i otoczenie przesiąknięte czymś obcym, niedefiniowalnym

●  Dishonored i plaga szczurów: nie tyle zaraza, co symbol dekadencji i moralnego rozkładu

●  Velen z Wiedźmina 3: przeklęte, ale nie do końca zrozumiałe miejsce

●

Lovecraft: oddziaływanie Pustki nie musi być konkretne – może być ontologiczną korupcją sensu
istnienia

To absolutnie widać – i działa to fantastycznie w Twoim świecie. Ten miks ontologicznej
grozy, politycznej degeneracji i niewypowiedzianej transgresji świetnie rezonuje z
Dishonored, Lovecraftem i bardziej złowrogimi aspektami D&D-owego „Far Realm” czy „The
Void Between Worlds”.

🔥 A skoro już to potwierdziłeś – kilka sugestii, które mogą Cię
zainspirować jeszcze głębiej:

🧠 Pustka jako fenomen metafizyczny

Pustka w Twoim świecie nie musi być tylko „źródłem złych bóstw” – może być:

●  błędem w istnieniu,

●  zaprzeczeniem boskiego ładu,

●  miejscem bez snu, bez czasu, bez tożsamości.

„To nie jest zło. To jest coś, co istniało wcześniej niż pojęcie dobra i zła – i chce
je znowu usunąć.”

🧪 Eksperymenty z owocami z Caithaloonu

Niech te owoce będą:

●  pozornie pyszne, piękne, dające wrażenie euforii;

●  w rzeczywistości wypaczają percepcję – dają błogosławieństwo pragmatyzmu bez

duszy, który idealnie pasuje do Vereny;

●  możliwe do przetwarzania w „pragmatyczne narkotyki”: syropy, wina, kadzidła

rytualne.

Można nimi wzmocnić czary kosztem uszkodzeń psychicznych albo przyspieszyć rozwój
upraw kosztem gleby i ducha ziemi.

👤 Kultysta z Zelderinu

Przykładowe imię: Tarshel Videmar, znany później w księgach zakazanych jako „Ten Który
Karmił Owoc”.

Profil:

●  wysoki rangą administrator kultu Vereny;

●

formalnie reformator rolnictwa i dyplomata;

●  potajemnie – agent kultu Pustki, badający wpływ negacji bytu na ekosystemy i

psychikę;

●

jego notatki przypominają szalone dzienniki naukowców z Dishonored lub
Bloodborne;

●  najbliżsi współpracownicy popełniają samobójstwa albo milkną na zawsze.

Znaki obecności:

●  świątynia Vereny w Zelderinie zaczyna przypominać laboratorium alchemiczne;

●  niektóre dzieci z okolicy rodzą się „zbyt spokojne”, „jakby bez duszy” – oszczędne

w mimice, niepodatne na ból;

●  ptaki nie śpiewają już nad sadami z owocami Caithaloonu.

📜 Fragment kroniki, np. z Saravery (możesz użyć jako
wstawki literackiej lub symulacyjnej):

„Jesień 1320 roku była długa i bezwietrzna, jakby niebo zapomniało oddychać.
Poselstwa z Zelderinu przybywały częściej niż zwykle, przynosząc ofiary z wina
i owoców – lecz ich posłańcy milczeli dłużej niż wypadało, patrzyli bez
mrugnięcia i mówili z przesadną uprzejmością. Stare zakonnice z Klasztoru
Gorejącego Słońca ostrzegły mnie, że w tych prezentach nie ma duszy.
Powiedziałem im, że to pragmatyzm nowej ery. Teraz, z perspektywy, myślę, że
to była cisza przed piekłem.”

🕯 A na koniec: możliwe konsekwencje

●  powstanie Zakonu Płomiennej Prawdy jako odpowiedzi – chcący wyplenić wpływ

Pustki ogniem i stali.

●

fale represji antymagicznych wywołane przez siostrzeńca Tarshela – „magowie byli
milczący, jak ci posłańcy...”

●  Zaviles w długofalowym upadku, ale nie poprzez wojny – tylko przez wypalenie

duchowe.

Jeśli chcesz:

●  mogę pomóc spisać „Kronikę Tarshela” – np. kilkanaście wpisów z jego dziennika;

●  opracować „kodeks symptomów CURSED” dla symulacji;

●  albo rozrysować zależności Tarshela, jego siostrzeńca, kapłanów Vereny i uczonych.

Jak chcesz to dalej rozwinąć – narracyjnie, systemowo czy mieszanie obu?

To, co budujesz, jest naprawdę imponujące – to już nie tylko tło dla powieści czy symulacji,
to pełnowymiarowa kosmologia, w której historia, mitologia i osobiste dramaty są
splecione z przerażającą precyzją. Poprawiona informacja o Abalanie i jego wuju jeszcze
bardziej pogłębia cały ten obraz.

Pozwól, że zrobię małe podsumowanie + zasugeruję możliwe rozwinięcia lub
uporządkowania relacji między postaciami i bytami:

📍 Kluczowe postaci i byty

Abalan Coller

●  Kapłan Wisterusa, boga hierarchii, feudalnego porządku i autorytetu.

●  Cyniczny, ambitny, bezwzględny – człowiek stworzony do robienia kariery w

instytucjach.

●  Siostrzeniec byłego kapłana Vereny, dziś kultysty Izgarthula.

●  W przyszłości odegra kluczową rolę w prześladowaniach uczonych i magów (np. jako

protektor czystki ideologicznej czy autorytarnych reform).

●  Może czerpać potajemną inspirację od wuja, nawet jeśli się od niego oficjalnie

dystansuje.

Wuj Abalana (kapłan Vereny → kultysta Izgarthula)

●  Dawniej: kapłan bogini Vereny, znanej jako bogini pragmatyzmu, prawa, kontroli

społecznej.

●  Obecnie: wyznawca Izgarthula – Patrzącego z Pustki.

●  Oficjalnie mędrzec lub urzędnik; potajemnie bada efekty Pustki, eksperymentuje z

przekraczaniem granic poznania.

●  Kryje się za maską rozsądku, jego przerażenie i fascynacja są podszyte chłodną

logiką.

●  Może być jednym z pierwszych, którzy sprowadzili „owoce z Caithaloonu” albo znają

ich sekret.

Izgarthul – Patrzący z Pustki, Szalony Obserwator

●  Zewnętrzny bóg Pustki.

●  Przedstawiany jako bezkształtne, wielookie coś, które nie mówi – tylko patrzy.

●  Spojrzenie Izgarthula = utrata tożsamości, zatracenie granic jaźni.

●

Inspiracja: Lovecraft, Far Realm, Dishonored, ale też Borges czy Ligotti.

●  Jego kult jest ukryty, elitarystyczny, przesiąknięty obłędem w maskach racjonalizmu.

Irmus (bóg współczucia i pomocy)

●  W 1743 zostanie dotknięty spojrzeniem Izgarthula.

●  Próba poznania tajemnicy śmierci z pominięciem Kalnetera zaprowadzi go na skraj

szaleństwa.

●  Motyw w stylu mitologii: "nawet bogowie nie powinni patrzeć".

●  Może stworzyć z tego herezję współczucia – fałszywe zbawienie, które otwiera

drogę Pustce pod postacią współodczuwania wszystkiego aż do rozpadu własnego
ja.

Kalneter (bóg śmierci i nieumarłych)

●  Strażnik cyklu życia-śmierci.

●  Blokuje duszom drogę do Pustki.

●  Nie broni jednak przed Izgarthulem, tylko przed Zug-Caioną – Martwą Gwiazdą o

Białym Świetle.

●  Może jawić się jako mroczny, ale sprawiedliwy – trzyma porządek świata przed

unicestwieniem.

Zug-Caiona – Martwa Gwiazda o Białym Świetle

●  Jeden z najbardziej niszczycielskich bytów Pustki.

●  Wielki, biały blask, który rozrywa dusze – nie poprzez cierpienie, lecz przez

nieludzki chłód egzystencji.

●  Odpowiada za przyszły upadek Aldaharu – może jako „gwiazda” zapowiedziana w

przepowiedniach, kometa zagłady albo rozprzestrzeniająca się infekcja
metafizyczna.

●  Jej wpływ = wyjaławianie świata z narracji, znaczeń i celów.

🔗 Proponowane powiązania i wątki

Motyw

Propozycja rozwinięcia

Kapłan Vereny →
kultysta Izgarthula

Opublikował kiedyś traktat o „transpragmatyzmie” – o granicach
rozumu; dziś jego kopie są zakazane

Relacja z Abalanem  Abalan może traktować wuja jako niezrozumianego geniusza,

którego kiedyś „naprawi”

Spojrzenie
Izgarthula

Czasem prowadzi do "oświecenia", ale bardziej do
ideologicznego obłędu (np. pragnienia by świat był pozbawiony
sprzeczności... przez usunięcie wszystkiego)

Owoc Caithaloonu

Spożyty przez kapłana może dać mu wizję „doskonałego
społeczeństwa, gdzie nikt nie cierpi – bo nikt nie istnieje”

Zug-Caiona

Jej pojawienie się w 1700+ to może być kosmiczne echo
grzechu Irmusa, który osłabił granicę między śmiercią a pustką

Chcesz może:

●

rozpisać więcej postaci lub ich ścieżki (np. Abalan jako sędzia inkwizycyjny?);

●  przygotować tablicę związków i wpływów (Pustka → Izgarthul → Wuj → Abalan →

represje);

●  albo przygotować dokument z systemową reprezentacją Pustki i jej aspektów (np.

CURSED jako enchant i rozkład duszy/dziedzictwa/kultury)?

Możemy to też przerobić na eventy do Twojej symulacji.

Świetnie zrobiłeś — Międzygórze jest naturalnym krokiem dalej po Zavilesie. Jeśli Zaviles
walczy o przeżycie wśród skał i kurzu, to Międzygórze już dawno pogodziło się z faktem, że

żywności z tej ziemi nie będzie. Ale za to daje wszystko inne, czego potrzebuje świat:
rudę, stal, kunszt i siłę.

Poniżej masz kronikarski opis tej prowincji, który możesz dowolnie rozwijać lub rozbić na
opisy dla subregionów czy frakcji:

🛡 Międzygórze – W kuźni gór rodzą się imperia

"Gdyby kamień miał serce, biłoby ono w Międzygórzu."
 — Z przysięgi rzemieślnika rodu Runicznej Pięści

Międzygórze to kraina z kamienia, rudy i pamięci. Położona wysoko, gdzie zimny wiatr huczy
w szczelinach między szczytami, a noce są czarne jak głębiny krasnoludzkich szybów, nie
daje nic za darmo. W zamian za trud — surowce. W zamian za honor — żelazo. W zamian
za krew — dzieła niezrównane.

Tutaj nie da się uprawiać ziemi — nie z braku chęci, lecz z jej nieobecności. Miejsca, gdzie
można wbić pług, są tak rzadkie, że traktowane są niemal jak świętość. To nie jest kraina dla
rolników. To kuźnia, arsenał i zbrojownia świata.

Około 65% terytorium pokrywają surowe góry, wyżyny i płaskowyże, z domieszką
złowieszczych kanionów i spękanych nizin. Ziemia twarda, nieprzyjazna, ale też
niesamowicie bogata: rudy metali szlachetnych, kamienie runiczne, a nawet — jak głoszą
legendy — szczątki pradawnych maszyn z Czasów Kuźni. Prowincja osiąga rekordowe 30
punktów zasobności, co czyni ją gospodarczym potworem.

Zdominowana przez krasnoludzki ród Runicznej Pięści z Uvarry, Międzygórze nie zna
pojęcia kompromisu. Kultura jest surowa, oparta na honorze, mistrzostwie
rzemieślniczym i żelaznych zasadach prawa, często zapisywanych w kamieniu, nie na
pergaminie. Każda społeczność posiada kuźnię, straż i kaplicę przodków — nawet jeśli
nie posiada rynków ani pól.

Religia koncentruje się wokół Starych Duchów Kamienia i Ognia Kuźni, choć w cieniu
tuneli szerzy się też kult Przełomu Żyły – zakazanej wiary mówiącej o „żywej rudzie”, którą
można karmić.

Szlaki handlowe z Międzygórza to arterie, którymi płyną metale, broń, precyzyjne
mechanizmy, a czasem nawet nielegalne artefakty. To one umożliwiają prowincji wymianę
zewnętrzną — głównie z państwami-miastami oraz enklawami krasnoludzkimi, takimi jak
Dolne Bastiony, Sartama czy górskie faktorie Larazzy.

Niepokojąco wysoka niestabilność (0.25) wskazuje, że choć ród Runicznej Pięści oficjalnie
włada całością, coś się burzy pod powierzchnią. Być może to głosy młodych, którzy nie chcą
żyć w cieniu przodków. A może to coś gorszego – coś, co żyje pod górami, gdzie pęka
kamień, a nieświęty VEIL osnuwa stare korytarze...

📊 Garść obserwacji mechaniczno-projektowych:

●  Zero rozwinięcia rolnictwa – brak nawet "FARMS", "FIELDS" czy "GARDENS" w

preferredDirections, co jest spójne z profilem (super!).

●  Postęp przemysłowy: bardzo jasno zarysowana ścieżka: MINING_LANDS →

SMELT_LANDS → IRON_MARCHES → CRAFTS_LAND →
MANUFACTURING_REGION.

●  Potencjał handlowy: MERCHANTS_REACH, GOLDEN_MARCH sugerują wzrost

znaczenia jako punktu wymiany dóbr, mimo braku żyzności.

●  Wysoka niestabilność: przy tak surowym środowisku i silnej strukturze klanowej

może oznaczać wewnętrzne napięcia, stare spory rodowe, konflikty z „niższymi
kastami” lub nawet obudzone zło z głębin.

Jeśli chcesz, mogę przygotować osobno:

●

listę potencjalnych miast lub twierdz Międzygórza,

●  zestawienie kluczowych klanów lub frakcji (ród Runicznej Pięści i ich

oponenci),

●  kilka legend, które można wrzucić w dialogi postaci, opisy przedmiotów, czy jako tło

dla wydarzeń w kampanii.

Świetnie – cały zestaw wygląda bardzo spójnie i różnorodnie. Poniżej krótkie podsumowanie
charakteru tych prowincji na podstawie danych, które dostarczyłeś, z uwzględnieniem ich
funkcji, klimatu, potencjału i możliwych problemów.

9. Carasera

Charakterystyka:
 Zrównoważona, umiarkowanie sucha prowincja z dominacją łąk, wzgórz i równin. Dobre
warunki do rolnictwa i sadownictwa.
 Kierunki rozwoju: głównie rolniczo-ogrodnicze (FARMS, FRUIT_PLANTATIONS,
VERDANT_REACH).
 Potencjał:

●  Drewno: średnie (14)

●  Surowce: niskie (6)

●  Stabilność: dość dobra (0.2)

●  Potencjał wzrostu: 0.65

 Uwagi: idealna do rozwoju klasycznej wsi szlacheckiej i osad chłopskich –
prawdopodobnie żywicielka innych prowincji.

10. Pirena

Charakterystyka:
 Chłodnawa, lesista prowincja z dominacją PINE_CRAG i lasów liściastych. Umiarkowanie
wilgotna.
 Kierunki rozwoju: rolnictwo, ogrodnictwo, winnice – klimat wskazuje raczej na
chłodniejsze winorośle (np. wysokogórskie).
 Potencjał:

●  Drewno: niskie-średnie (8)

●  Surowce: umiarkowane (8)

●  Brak miast startowych – potencjalnie dzika/prowincjonalna

●  Potencjał wzrostu: 0.65

 Uwagi: może być dobrym tłem dla chłopskich wspólnot, zielarzy, klasztorów.
Trochę "zielona samotnia".

11. Międzygórze

Charakterystyka:
 Skrajnie trudne warunki do życia – dominują skały, góry, złoża.
 Kierunki rozwoju: górnictwo, hutnictwo, ekspansja przemysłowa.
 Potencjał:

●  Drewno: bardzo niskie (3)

●  Surowce: bardzo wysokie (30)

●  Potencjał wzrostu: 0.75

●  Klimat: zimny i bardzo suchy

 Uwagi: idealna jako ojczyzna krasnoludów – surowa, twarda, bogata, ale mało
przyjazna. Rolnictwo praktycznie niemożliwe – wszystko trzeba importować.

12. Orvanor

Charakterystyka:
 Mokra, zimna i leśna prowincja, z jeziorami i bagnami. Przypomina dzikie północne
rubieże.
 Kierunki rozwoju: osady rzemieślnicze i magiczne, powiązania z duchowością (VEIL).
 Potencjał:

●  Drewno: umiarkowane (10)

●  Surowce: niskie-średnie (7)

●  Potencjał wzrostu: niski (0.5)

 Uwagi: może być miejscem mrocznych tajemnic, pustelni, duchów i prastarych
lasów.

13. Zielona Rubież

Charakterystyka:
 Olbrzymia, magicznie nasycona, leśna prowincja o ogromnym potencjale naturalnym.
 Kierunki rozwoju: mistyczno-leśne – sady, gaje, ogrody, regiony duchowe.
 Potencjał:

●  Drewno: bardzo wysokie (30)

●  Surowce: niskie (10)

●  Potencjał wzrostu: niski (0.5), ale bardzo stabilna (0.1)

 Uwagi: to zielone serce świata – idealne pod drużyny elfów, druidów, społeczności
zrównoważonych z naturą, ale też dla eksperymentalnych, duchowych rozwojów
ziem.

Jeśli chciałbyś, mogę też stworzyć skondensowaną tabelę porównawczą wszystkich
prowincji (np. id, name, key features, growthPotential, wood/resource

richness, itd.) albo zasugerować mapy sąsiedztw/konfliktów między prowincjami (np.
"Międzygórze eksportuje do Carasery i Pireny"). Daj znać!

📌 Główne komponenty terenu (propozycja
uporządkowania):

🟩 Typ terenu (TerrainType)

Przykłady: FOREST, MEADOWS, FIELDS, PASTURES, VINEYARDS, MINING_LANDS,
etc.
 To główna klasyfikacja — „co to jest?”.

🪨 Kształt terenu (TerrainShape)

Np. HILLS, PLAINS, PLATEAU, CANYONS, CLIFFS, MOUNTAINS
 Opisuje geofizyczną strukturę terenu.

🌊 Cechy fizyczne (TerrainCharacteristic)

Np. LAKES, RIVERS, COAST
 To nakładki fizyczne, które mogą się łączyć z typem i kształtem.

🪄 Natura magiczna (Enchant)

Np. WILDERNESS, VEIL, ENCHANTED, CURSED, etc.
 Opisuje wpływy pozamaterialne.

⛏ Rodzaj eksploatacji (ExploitationType)

Np. TIMBER, ORE, CLAY, HUNTING_MEAT, FISH, SALT
 Czego ludzie mogą się dorobić z danego terenu.

🌾 Rodzaj produkcji (ProductionType)

Np. CROPS, FRUITS, GRAPES, WINE, BEER, HORSES, HONEY, HERBS
 Czego ludzie mogą wytwarzać na danym terenie — może też wskazywać na specjalizację.

🏘 Poziom osadniczy (SettlementLevel)

Np. OUTPOST, HAMLET, VILLAGE, TOWN, BIG_TOWN, CITY, CAPITAL
 Dobrze, że to wydzieliłeś — pozwala to rozdzielić infrastrukturę od funkcji.

🧭 Rola regionu (RegionRole) (opcjonalne, ale bardzo pomocne)

Np. AGRICULTURAL, MINING, TRADE_HUB, MILITARY_OUTPOST,
RELIGIOUS_CENTER
 To może być wykorzystywane przez AI i symulację do preferencji rozwoju, zapotrzebowań
itp.

🔄 Co z HUNTING_REGION?

Masz absolutną rację — traktowanie HUNTING_REGION jako osobnego TerrainType
byłoby nieopłacalne. Lepsze opcje:

●  Zmienić na ExploitationType.HUNTING_MEAT (czyli to, co się pozyskuje).

●  Dodatkowo oznaczyć obecność łownej fauny tagiem, np.

TerrainFeature.HUNTING_GROUNDS, ale tylko jeśli potrzebujesz czegoś, co
wpływa na inne systemy (np. prawo do polowań, prestiż).

●  Można też podciągnąć to częściowo pod ResourcePresence, jeśli masz system

zasobów per prowincja.

💡 Przykład JSON-owego modelu terenu
{

  "terrainType": "MEADOWS",

  "terrainShape": "PLAINS",

  "characteristics": ["RIVER"],

  "enchant": "WILDERNESS",

  "exploitationTypes": ["HUNTING_MEAT", "FISH"],

  "productionTypes": ["CROPS", "HONEY"],

  "settlementLevel": "HAMLET",

  "regionRole": "AGRICULTURAL"

}

Jeśli chcesz, możemy to zamienić w klasę Java, enumy, format JSON schema czy format
wczytywania do edytora — cokolwiek będzie Ci wygodniejsze.

✅ Główne osie opisu terenów:

1. Typ terenu (TerrainType) – kluczowy stan miejsca

Np.: FOREST, FIELDS, PASTURES, TIMBERLAND, SETTLERS_REACH, CRAFTS_LAND,
GARDENS…

2. Kształt terenu (TerrainShape) – fizyczna forma:

Np.: HILLS, VALLEY, PLATEAU, CANYON, CLIFF, FLATLAND, RIDGE, BASIN

3. Cechy terenowe (TerrainCharacteristic) – stałe, trudne do zmiany:

Np.: LAKE, RIVER, COAST, MARSH, SPRING, SINKHOLE, HOT_SPRING

4. Enchant (magiczny wpływ) – nadprzyrodzona natura terenu:

Np.: WILDERNESS, CURSED, VEIL, ENCHANTED, SACRED, TWISTED, NONE

5. Poziom zasiedlenia (SettlementLevel) – nowa kategoria:

Np.: WILDERNESS, OUTPOST, HAMLET, VILLAGE, TOWN, CITY, CAPITAL

6. Rola regionu (RegionRole) – nowa propozycja, np. do celów AI i strategii:

Np.: AGRICULTURAL, INDUSTRIAL, MILITARY, RELIGIOUS, TRADING, CULTURAL

7. Typ eksploatacji (ExploitationType) – co fizycznie wydobywamy:

Np.: WOOD, STONE, ORE, FISH, CLAY, PEAT, SALT

8. Typ produkcji (ProductionType) – co powstaje w wyniku rozwoju:

Np.: CROPS, FRUITS, HORSES, GRAPES, WINE, BEER, CHEESE, CLOTHES

💡 Pomysł na HUNTING_REGION

Twoje przeczucie jest trafne — jako typ terenu, HUNTING_REGION komplikuje przemiany,
bo:

●  Nie ma z czego naturalnie w niego ewoluować ani do czego z niego wracać.

●  Odzwierciedla funkcję, nie stan ani typ terenu.

🔁 Alternatywy:

1.  Zamiana na ExploitationType: GAME_MEAT, FUR, LEATHER

2.  Tag funkcjonalny (np. funkcja pomocnicza): np. HuntingZone, RichInGame

3.  Moduł cech dodatkowych – np. AdditionalActivities = [HUNTING,

FISHING]

4.  Zupełne pominięcie – efekty polowań mogą być zasymulowane innymi surowcami

(MEAT) lub jako wydarzenia losowe.

📌 Co teraz?

Proponuję na tym etapie:

1.  Dokończyć pełną strukturę modelu typu LandTile/RegionTile.

2.  Rozplanować zestaw pól (w tym np. Set<ExploitationType>,

Set<ProductionType>, TerrainCharacteristic, Enchant).

3.  Dopiero potem (opcjonalnie) wrócić do przemian SETTLERS_REACH, MEADOWS,

TIMBERLAND itd.

Masz absolutną rację – to bardzo zdrowa i dojrzała refleksja, niezależnie od wspomagaczy
🍻😉

W skrócie: uproszczenie enumów do typów miejsc + rozróżnienie szczegółów przez poziom
(np. level) jest bardzo dobrym i praktycznym podejściem. Oto czemu:

---

✅ Zalety uproszczenia przez typ + poziom:

1. Elastyczność – możesz mieć np. ARTISAN_CENTER poziomu 1 jako wiejski warsztat,
poziomu 3 jako miasteczko rzemieślnicze, poziomu 5 jako metropolię rzemieślniczą. Bez
potrzeby wymyślania nowych enumów.

2. Prostsza logika kodu – możesz łatwo filtrować po typie (np. PlaceType == MONASTERY),
niezależnie od wielkości.

3. Lokalizacja/Internacjonalizacja – enum w kodzie to czysty identyfikator (np.
PlaceType.TRADING_CENTER), a nazwa wyświetlana graczowi będzie zależeć od poziomu
i kultury, np. w jednym regionie „Kramik”, w innym „Wielki Bazar”.

4. Spójność z innymi systemami – np. rozwój terenów może zakładać tylko typ
(ARTISAN_CENTER), a poziom (czyli skala osady) może wynikać z liczby mieszkańców,
zamożności, impulsu itp.

5. Łatwe balansowanie i rozwój – masz jedną listę typów, a np. rozwój z poziomu 1 do 2 to
po prostu level++, z ewentualną walidacją.

---

🔢 Przykładowe typy PlaceType:

enum PlaceType {
    SETTLEMENT,         // zwykła osada
    TRADING_CENTER,     // handlowa
    ARTISAN_CENTER,     // rzemieślnicza
    RELIGIOUS_CENTER,   // świątynia lub inny kultowy
    MONASTERY,          // klasztor / zakon
    MILITARY_SITE,      // warownia, garnizon
    NOBLE_RESIDENCE,    // siedziba szlachecka
    MINING_SITE,        // górnicza
    SPECIAL_SITE,       // np. ruiny, jezioro, cud, magiczne miejsce
    PORT,               // port morski lub rzeczny
    CAPITAL             // stolica (jeśli potrzebujesz jako osobny typ)
}

---

🔀 level może znaczyć coś innego dla każdego typu

PlaceType

Level 1 Level 3 Level 5

Miasteczko (Town)  Miasto (City)

Osada (Hamlet)

SETTLEMENT
TRADING_CENTER  Targ  Miasteczko kupieckie Węzeł handlowy
ARTISAN_CENTER  Wiejski warsztat
rzemieślnicze
RELIGIOUS_CENTER
MONASTERY Priory  Klasztor
MILITARY_SITE
NOBLE_RESIDENCE Dworek

Twierdza zakonników

Dwór rodowy  Pałac

Wieża strażnicza

Kapliczka

Świątynia

Garnizon

Cytadela

Miasteczko rzemieślnicze

Centrum

Sanktuarium narodowe

---

🧠 A jak chcesz wrócić do szczegółowych nazw…

…to możesz mieć zewnętrzny obiekt (np. PlaceNamingScheme) lub mapowanie, które po
PlaceType i level zwraca konkretną nazwę (np. do opisów, UI, narracji).

---

Podsumowując: przejście na PlaceType + level to strzał w dziesiątkę, a fakt że zauważyłeś
to sam – i to jeszcze w trakcie luźnego wieczoru – pokazuje, że projekt dojrzewa. Chcesz,
żebym pomógł ci teraz stworzyć konkretny enum i jego levelową strukturę?

