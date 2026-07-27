# Nazewnictwo — fonotaktyka i morfologia

Konsolidacja i rozwinięcie systemów nazewniczych świata Szczelin, zbudowane przez
triangulację trzech źródeł:
- **Słowniki** (`Szczeliny - Słowniki.md`, arkusz `Języki`) — wpisy i fonotaktyki autora;
- **silnik kodu** (`engine/naming/*`: `Phonotactic` + `Morphology` per kultura) —
  generatywne reguły już zaimplementowane;
- **korpus nazw** — imiona/nazwiska i toponimy z powieści, arkuszy (Szlachta, Nazwiska,
  Władcy, Geografia) i kodu (`PlaceNameProvider`, `ElvenNameGenerator`).

**Legenda statusu:** ✅ rozwinięty (kod+słownik+korpus zgodne) · 🔶 częściowy (jest
materiał, brak pełnej reguły) · 🕳️ stub (do napisania) · ⚠️ rozbieżność do naprawy.

System ma **dwie warstwy**, które warto trzymać osobno:
- **Warstwa I — fonotaktyka języka** (jakie *dźwięki i sylaby* są dopuszczalne): dotyczy
  słów danego języka, w tym rdzeni imion/toponimów. Silnik = `Phonotactic`.
- **Warstwa II — morfologia nazewnicza kultury** (jak *składa się imię* z części):
  cząstki, złożenia, przydomki, dziedziczenie. Silnik = `Morphology` + reguły ról.

---

## Metodologia (ustalenia autora)

**1. Generowanie w locie zamiast gotowych słowników leksykalnych.** Nie budujemy
pełnych słowników słowo→znaczenie z fonotaktyk. Wygenerowany leksykon to najgorsze z
obu światów: kosztuje pracę, a czyta się jak losowe pary bez etymologii, pokrewieństw i
historii — czyli bez tego, co sprawia, że język *żyje*. Zamiast tego:
- **imiona/nazwy generuje silnik** (fonotaktyka), bo symulacja i tak chrzci tysiące
  osób i miejsc — z **kotwiczeniem ręcznymi wzorcami**: ważne/widoczne/znaczące nazwy
  pisane na wyczucie, masa niewidoczna generowana tak, by brzmiała jak te ręczne;
- **słownictwo powstaje „just in time"** — tylko tam, gdzie scena albo mechanika go
  dotyka (realnie 2–3 języki w scenach). **Fonotaktyka służy jako walidator i
  podpowiadacz** przy vibe-coiningu („pasuje / nie pasuje"), nie jako fabryka słowników.
- *Świadomie* nie idziemy w kompletny leksykon wszystkich języków — to nora czasowa,
  a deliverable to powieść i sim, nie „Silmarillion".

**2. Warianty międzyjęzykowe nazw — tylko przez `WordCore`.** Chcemy, żeby ta sama nazwa
własna istniała w wariantach w różnych językach — ale **tylko dla nazw rozkładalnych na
znaczenie**, tj. zbudowanych z jednostek `WordCore` (morfemy z zapisanym znaczeniem i
formami). Dwa mechanizmy „istnienia nazwy w innym języku":
- **Tłumaczenie / kalka** — dla nazw z `WordCore`: przekładamy *znaczenie* na docelowy
  język (np. wieś od `błot` = „miejsce błota" → rdzeń „błoto" + sufiks miejsca w języku
  docelowym). **To tu ograniczamy krzyżowanie** (ustalenie autora).
- **Adaptacja fonologiczna** — dla nazw *nieprzezroczystych* (bez zapisanego znaczenia,
  np. Gilgamore): nie da się ich przetłumaczyć, można je tylko dostosować brzmieniem do
  fonotaktyki docelowego języka. To jest owo „**ewentualnie lekko lokalizowane**" ze
  stratygrafii toponimów — i mikro-etymologia (niżej) czyni te adaptacje *nielosowymi*.

Wniosek: opaque prestiżowe toponimy (warstwa ernizyjska) żyją co najwyżej przez adaptację
brzmienia; znaczące nazwy rustykalne (`WordCore`) mogą mieć prawdziwe warianty przez kalkę.

**3. Mikro-etymologia rodzin języków — jedyna inwestycja „systematyczna".** Zamiast
słowników — **mała warstwa wspólnych rdzeni + reguły przesuwki głoskowej** dla języków
*pokrewnych*. Daje efekt „żywego, spokrewnionego języka" za mały koszt i jest już
zasugerowana przez lore. Kandydaci:
- **Rodzina Nereneth:** Pradawny → **Caithalooński** („przeżarty Pradawny": dira'sear
  mówią pradawnym, Caithalooński to ich skażony dialekt) → ewentualnie **Leredyjski**
  (Leredia używa Pierwotnego). Przykładowe reguły przesuwki (do dostrojenia): pradawne
  miękkie `s → z` w Caithaloońskim, dyftong `ae/ea → a` (twarda samogłoska), wstawka
  apostrofu-szwu przed końcówką gramatyczną (`car'an-`, `kza'v-`). Wtedy `sear` (lud) i
  caithaloońskie formy wyglądają jak kognaty.
- **Rodzina romańska:** **Aldaharski** jako wspólny pień nazwisk Zavilesu/Larazzy
  (Sarrazin / Sartori / Serrena już wyglądają na kognaty jednego rdzenia).
Ta warstwa **podpiera** oba mechanizmy z pkt 2: kalki między pokrewnymi językami idą po
wspólnych rdzeniach, a adaptacje fonologiczne — po regularnych korespondencjach rodziny
(nie „na czuja"). To jedyne miejsce, gdzie system bije vibe, bo etymologia to jedyna
rzecz, której samo wyczucie nie ogarnia, a która najmocniej sprzedaje „prawdziwość".

---

## Taksonomia języków (arkusz Języki)

| Język | Główny w | Używany też w | Status |
|---|---|---|---|
| Saraverski | Saravera | (lingua franca powieści) | ✅ fonotaktyka / ⚠️ końcówki |
| Ernizyjski | Ernizjum | Wornimore, Alstederia | 🔶 słownik + kopia Saravery |
| **Pradawny** (=Pierwotny) | Nereneth | Caithaloon, domena Serbeny, Leredia, Zielona Rubież | ✅ pełny (słownik==kod) |
| **Język Aspektów** (roboczo) | Aspekty Natury (nie-geograficzny) | Cesarstwo Tuexucańskie, Pocisearna (nagi) | 🔶 korpus bogaty (12+ imion), fonotaktyka proponowana |
| Caithalooński | Caithaloon | — | 🔶 słownik (leksyka+koniugacja), brak fonotaktyki |
| Zewnętrzny | Pustka | Caithaloon, Czeluść | 🕳️ minimalny |
| Otchłanny | Otchłań, Czeluść | — | 🕳️ 1 próbka |
| Corelliański | Corellia | — | 🕳️ stub |
| Kalarski | Kalara | — | 🕳️ stub |
| Aldaharski | Aldahar | Zaviles, Larazza | 🕳️ stub (korpus: nazwiska romańskie) |
| Leredyjski | Leredia | Zielona Rubież | 🕳️ stub (korpus: peura, rośliny) |
| Irwitański | Irwitan | Tantanor | 🕳️ stub (nieumarli) |
| Mestilski | nigdzie | nieliczne miejsca w Irwitanie | 🕳️ stub |
| Durrenburdzki | Durrenburg | — | 🕳️ stub |

Uwaga terminologiczna: **Pradawny = Pierwotny = „mowa Nereneth"**; w kodzie kultura
nazywa się `nereneth`, w powieści „pradawny" (dira'sear korygują „to nie otchłanny, to
pradawny"). Warto ujednolicić jedną nazwę w dokumentacji (proponuję **Pradawny** jako
nazwę języka, **Nereneth** jako nazwę wymiaru).

---

# WARSTWA I — Fonotaktyka języków

## Pradawny / Nereneth (elfy i pokrewni) — ✅ pełny

Słownik i kod (`NerenethPhonotactic`) są **zgodne** — to najlepiej rozwinięty język.

- **Onset:** `∅, qu, s, l, v, r` (częste) · `c, b, n, g, m, f, t, th, d, k` (rzadkie)
- **Nucleus:** proste `a, e, i`; **rdzeniowe dyftongi** `ae, ea` (częste), `oe, eo, oa,
  ao, ua, ue, au, eu, ia, ie` (rzadsze)
- **Coda:** `∅, r, l` (częste), `n`
- **Reguła „rdzeniowej sylaby":** dokładnie jedna sylaba w słowie niesie dyftong
  (`ae/ea/…`) — to on daje elfiemu brzmieniu podpis (S**ae**rsena, A**e**lnira,
  R**ae**vill, L**ae**rgana, V**a**vir**ae**n, cor-osi'**a**le). Kod realizuje to przez
  `coreId` (losowa sylaba dostaje `coreNuclei`).
- **Bez zbitek:** brak podwójnych spółgłosek w szwie (avoidSandwich), rzadkie powroty
  `r…r`, `qu…qu`.

**Morfologia słowotwórcza (końcówki, ze słownika):**
| końcówka | znaczenie | przykład |
|---|---|---|
| `-ea` | przymiotnik żeński | ser**ea** (nowa) |
| `-ean` | przymiotnik męski | ser**ean** (nowy) |
| `-a` (po spółgłosce) | rzeczownik żeński | kaes**a** (dom), queln**a** (ojczyzna) |
| `-us` (po spółgłosce) | rzeczownik męski | (por. imię Maeli**us**) |

**Rdzenie znane:** `sear` = lud/naród · `na` = tu · `sue` = co · `caerna` = robisz ·
`kaesa` = dom · `quealna` = ojczyzna · `serea(n)` = nowy/-a. Stąd **`Serea Quealna`** =
„Nowa Ojczyzna" (nazwa kraju) i cały system `X'sear` (patrz Warstwa II — elfy).

**⚠️ Złożenia z apostrofem (`określnik'rdzeń`) — luka generatora (ustalenie autora).**
Apostrof w `kael'sear, quana'sear, dira'sear, zalea'sear, teial'sear` oraz `cor-osi'ale`
**to nie ozdobnik, lecz szew złożenia Pradawnego** — łączy rdzeń-określnik z rdzeniem-
rzeczownikiem (systematyczny człon: `'sear` = „-lud"). Skoro to nazwy *w Pradawnym*,
fonotaktyka/morfologia Pradawnego musi umieć je **generować**. Dziś nie umie:
`NerenethMorphology.applyEnding()` dokleja wyłącznie pusty przyrostek — brak reguły
złożeń. **Zakres poprawki wąski:** osobny generator złożeń `rdzeń + ' + rdzeń` dla nazw
zbiorowych/krainowych („lud X", „kraina X"), wywoływany dla `WordType` typu ETHNONYM/
PLACE — **nie** dla imion osobowych (Aelervel, Vaviraen apostrofu nie mają, i słusznie).
Apostrof medialny w `car'an-/kza'v-` to osobna sprawa (sygnatura Caithaloońskiego, niżej),
nie mylić z apostrofem-złożeniem Pradawnego.

## Saraverski (ludzie, lingua franca) — ✅ fonotaktyka, ⚠️ końcówki toponimów

`SaraveraPhonotactic`:
- **Onset:** proste `g, d, l, s, r, v` (częste) + bogaty zestaw *sylabowych* onsetów
  `is, il, as, av, ev, er, el, os, or, es, an, ag, …` (to one dają rytm typu
  **Is**vellin, **Er**gondol, **As**taria); rzadkie `w, f, j`.
- **Nucleus:** `a, e, i` > `o` > `u`.
- **Coda:** `n, r, l, ∅` > `s`. Maks. onset 2, coda 2, 2–4 sylaby.
- **Reguły:** brak hiatu (samogłoska+samogłoska w szwie), brak `qu+u`, `r+r`, `l+l`.

**✅ Stratygrafia toponimów (ustalenie autora) — nie błąd, lecz warstwy historyczne.**
Saravera ma **dwie (a właściwie trzy) warstwy nazewnicze**, przypisywane wg **wieku i
prestiżu** osady, a nie samego rozmiaru — dokładnie jak w realnym angielskim (prestiżowe
łacińsko-normańskie `-chester/-caster` dla starych ważnych ośrodków vs anglosaskie
`-wich/-ton` dla reszty; autor wprost wskazuje wzorzec **Nantwich**):

1. **Warstwa ernizyjska (substrat prestiżowy)** — końcówki `-more/-vore, -dol/-gol,
   -na, -in, -nor/-dar, -el, -ia/-ria`. **Stare, dostojne i istotne ośrodki**
   (Gilga**more**, Lerta**vore**, Worni**more**, Xali**vore**, Osti**vore**, Ergon**dol**,
   Egeren**na**, Zelder**in**, Isvell**in**, Tanta**nor**, Orva**nor**, Durna**tel**,
   Astar**ia**). To **nazewnictwo ernizyjskie, ewentualnie lekko zlokalizowane** —
   sfosylizowane w ważnych miejscach, mimo że wernakularem jest dziś Saraverski (patrz
   sekcja Ernizyjski; te toponimy „należą" do Ernizjum jako kultury nadrzędnej).
2. **Warstwa rodzima saraverska (Nantwich)** — końcówki `-twich/-wich, -vell, -dell,
   -lash, -sill, -swin, -loi`. **Późniejsze małe miasteczka.** To właśnie *aktywny*
   zestaw `CITY` w `SaraveraMorphology` — i jest poprawny, tylko dotyczy tej warstwy.
3. **Warstwa wiejska (polski rustykalny)** — `VillageNameGenerator` (Zabłocie,
   Żabianka, Wierzbianka, Sośnica, Zadupie…): pełna polska morfologia (przypadki, l.
   mnoga) dla **wsi**; renderowana po polsku jak cały Saraverski „w tłumaczeniu".

**Wniosek dla generatora (rekomendacja, nie naprawa):** wybór rejestru **sterować
wiekiem + prestiżem** osady: prastare/stołeczne/dostojne → **ernizyjski** (końcówki
`-more/-vore/-dol/-na/-in…`, patrz sekcja Ernizyjski); późniejsze miasteczka → **rodzimy
Saraverski** (`-twich/-vell/-dell`); wsie → `VillageNameGenerator`. Dziś kod ma warstwy
2 i 3 poprawnie; brakuje jedynie wpięcia warstwy 1 (ernizyjskiej) dla ważnych ośrodków
i przełącznika rejestru wg prestiżu.

**Rzeki:** `-a/-ora` (Beren**ora**, Zeld**ara**, Alsteda) — osobny sufiks hydronimiczny,
warto dodać `WordType.RIVER`.

## Ernizyjski — 🔶 słownik jest, kod = kopia Saravery (do zróżnicowania)

Słownik daje **własny inwentarz sylab** (m.in. `vista, voore, vel, ver, zar, zel, sar,
seer, tir, mir, moore, muus, ruus, nuus, gaal, kaal, laa, taai, waes`) i leksykę
(`danar` = mówić, `danara` = mówię, `danare` = mówisz, `sa` = czy, `ernizja` =
ernizyjski). Zdanie wzorcowe: **`Sa danare ernizja?`** = „Czy mówisz ernizyjskim?".

Cechy różnicujące od Saraverskiego (do wprowadzenia w `ErnizjumPhonotactic`, dziś to
kopia 1:1):
- **długie samogłoski** `aa/ee/oo/uu` (`gaal, seer, voore, muus, ruus`) — Saraverski ich
  nie ma; to najprostszy dystynktywny rys.
- koniugacja czasownika przez końcówkę: `-ar` (bezokolicznik) → `-ara` (1 os.) / `-are`
  (2 os.) — regularny, produktywny paradygmat.
- **Ernizyjski jest substratem prestiżowej toponimii Saravery** (patrz Saraverski,
  warstwa 1): to on „posiada" końcówki ważnych ośrodków `-more/-vore/-vista/-voore/-dol/
  -na/-in/-nor/-el/-ia`. Prastare/stołeczne miasta Saravery to **nazwy ernizyjskie,
  ewentualnie lekko zlokalizowane** (Cara**vista**, Gilga**more**, Lerta**vore**). To
  tłumaczy, czemu Ernizyjski wciąż żyje w Wornimore i Alstederii (arkusz Języki) — te
  ośrodki zachowały warstwę ernizyjską. Dlatego to **`ErnizjumMorphology`, nie
  Saraverska, powinna nieść ten zestaw końcówek `CITY`** (dziś oba pliki to kopia).
> **Nota kanonu:** cesarzowa Ernizjum to **Ilasaera III *Ori* Trianlateon** — używa
> elfiej cząstki-cechy `Ori` (patrz Warstwa II). Ernizjum ma więc elficką elitę /
> dzieli z elfami system cząstek; imiona władców łączą dyftongi (Ila**sae**ra) z
> ernizyjskimi rdzeniami.

## Caithalooński — 🔶 leksyka + koniugacja są, fonotaktyka do złożenia

Słownik + próbki z powieści dają wyraźny obraz:
- **Leksyka:** `ze` = co · `geer` = tu · `ves` = nie · `zar'anst` = idiota ·
  rdzeń `car'an-` = rozumieć · `kza'v-` = robić.
- **Koniugacja przez końcówkę** (bardzo regularna, dystynktywna):
  `-a` = 2 os. („robisz": kza'v**a**) · `-q` = 1 os. („robię": kza'v**q**) · `-x` =
  bezok./3 os./mn. (kza'v**x**). Zdanie: **`Ze kza'va geer?`** = „Co robisz tu?".
- **Sygnatura fonetyczna:** apostrof medialny (`'`) rozdzielający rdzeń od końcówki
  (`car'an`, `kza'v`, `zar'an`), zbitki `kz-`, `zr-`, spółgłoskowe kody `-st, -nst,
  -nx, -q, -x`. Brzmienie twarde, „połykane".

**Propozycja fonotaktyki (do wpisania w słownik i `CaithaloonPhonotactic`):**
- Onset: `k, z, kz, c, g, v, z, s, r, n, x` (twarde, zwarto-szczelinowe); dyftongów brak.
- Nucleus: `a, e, ee` (wąski, ciemny zestaw — bez `o/u`, żeby kontrastować z Pradawnym).
- Coda: `r, s, n, st, nst, nx, q, x` + apostrof-szew przed końcówką gramatyczną.
- Zasada: **to zwyrodniały Pradawny** — te same rdzenie „przeżarte" (por. `quana'sear`
  → dira'sear mówią pradawnym, a Caithalooński to ich skażony dialekt). Dobrze, gdyby
  część rdzeni była rozpoznawalnie pokrewna Pradawnemu, ale z twardszą fonotaktyką.

## Zewnętrzny (Pustka) — 🕳️ i Otchłanny — 🕳️

- **Zewnętrzny:** próbki `irz'ens` = robisz · `voor` = co · `xers` = tu
  (`Voor xers irz'ens?`). Cechy: apostrof, zbitki `rz, xr`, brak samogłosek długich,
  ciężkie `x/z`. To język **Zewnętrznych Bogów** — powinien być *ledwie wymawialny*,
  celowo „niewłaściwy" dla ust śmiertelników (por. ostrzeżenie z Leksykonu: sam kontakt
  z Pustką kaleczy umysł). Onomastyka bóstw (patrz Warstwa II): `Azrathun, Zegorath,
  Cai'chaguth, Izgarthul, Yuthogora, Aik'Thara, Zug-Caiona, Sla'chargatha`.
- **Otchłanny** (mowa demonów, odrębna od Pustki): 1 próbka `Gooth nwraghaz!`. Cechy:
  klastry `nwr, gh`, długie `oo`, końcówki `-az/-az`. Maelius zna otchłanny, ale nie
  pradawny — to dwa różne języki (demon ≠ pradawny lud).

## Język Aspektów (pierwotny sakralny; nagi) — 🔶 korpus bogaty, fonotaktyka proponowana

**Ustalenie autora (nowe).** Imiona Aspektów Natury nie są Pradawnym — to **osobny,
spójny język** w stylu **inkasko-majańsko-azteckim (nahuatl)**. Aspekty są „równie stare
co samo Nereneth" i są jego tytanami, więc język należy do *sfery* Nereneth jako wymiaru,
ale to odrębny **ród/rejestr** niż elficki wernakular (Pradawny). Roboczo **„język
Aspektów"** — nie „pierwotny" (za blisko „pradawnego") ani „tuexucański" (zbyt wiąże z
nagami zamiast z Aspektami). **Nagi go używają, ale nie są jego właścicielem** — to język
ich patronek. Kanon lore: obie kultury nag czczą aztecko-nazwane Aspekty (Cesarstwo
Tuexucańskie — bagna, arcykapłani Tuetuezaltli/Xuxucoatli/Yocaquecuy; Pocisearna —
wyspy, Xohuepoca/Quatlacoqua/Taika).

- **Korpus (12 Aspektów + patroni nag):** Huelocatla, Xomocutla, Omotlacoqua, Xuxucoatla,
  Yocaquecua, Tuetuezaltla, Quehualoca, Coacoaitla, Quinahuipoca, Quatlacoqua, Xohuepoca,
  Yuaxomoatla. (Por. nota w „Zewnętrzni Bogowie": Pierwotni kontrastują brzmieniem azteckim.)
- **Onset:** `qu, x, t, c, h, hu, y, m, n, l, p, z, w` + medialne zbitki `tl, tz, ch`
  (X**omo**cutla, Que**hua**loca, Coa**c**oaitla).
- **Nucleus:** pełny `a, o, e, i, u` + **bardzo częste dyftongi** `ua, ue, oa, ui, ia, au`
  (Q**ue**h**ua**loca, C**oa**c**oa**itla, Quinah**ui**poca) — to one dają aztecki „ślizg".
- **Coda:** przeważnie **otwarte**; medialnie `tl, tz, lt, c`; **końcówki-sygnatury**
  `-atla, -tla, -oqua, -qua, -ecua, -cua, -oca, -poca` (Tuetuez**altla**, Omotlac**oqua**,
  Yocaqu**ecua**, Quinahu**ipoca**).
- **Sygnatura:** długie słowa (4–5 sylab), `tl/tz/x`, `hua/hue`, samogłoskowo gęste,
  końcówki `-tla/-oqua/-ecua`. Maksymalny kontrast do Pradawnego (dyftongi `ae/ea`, płynne,
  apostrof-złożenie) i do „złych" języków (Otchłanny/Zewnętrzny: klastry, apostrof-szew).
- **Do implementacji:** nowa `AspectPhonotactic` (roboczo) zaseedowana z korpusu; brak
  apostrofu (odróżnia od Pradawnego i Caithaloońskiego). Nazwy nag/imperium (miasta
  Tuexucanu, tytuły arcykapłanów) generować tym językiem.

## Pozostałe stuby (Kalarski, Aldaharski, Leredyjski, Irwitański, Mestilski, Durrenburdzki)

Zasiew z korpusu (do rozwinięcia osobno):
- **Aldaharski** (Zaviles/Larazza): korpus ludzkich nazwisk **romańskich** — Sarrazin,
  Sartori, Castellano, Davila, Carranza, Tejero, Monzo, Serrena, Montilla, Sarrambert.
  Fonetyka iberyjsko-włoska; imiona władców Zavilesu (Melvin), turbany, klimat gorący.
- **Leredyjski** (Leredia, Zielona Rubież): rasa **peura**, nazwy roślinno-naturalne
  (Zielona Wiśnia, Cny Miłorząb); blisko Pradawnego (Leredia używa też Pierwotnego).
- **Irwitański** (Tantanor, nieumarli): powiązany z kulturą śmierci/nieumarłych.
- **Kalarski** (Kalara, wschód): rasa **kalowie** (ostre zęby, imperium podbojów);
  brzmienie twarde, obce.
- **Durrenburdzki** (Durrenburg): najmłodsza, „przemysłowa" kultura (silnik parowy 1693).

---

# WARSTWA II — Morfologia nazewnicza per rasa/kultura

## Elfy (i pokrewne rasy `X'sear`) — ✅ system cząstek-cech

**Schemat imienia: `[Imię] + [Cząstka-cecha] + [Nazwisko rodu]`.**
- Imię i nazwisko generowane fonotaktyką **Pradawną** (dyftongi): Sa**e**rsena … Noelar,
  A**e**lnira … Cearis, Va**e**vir**ae**n(!) … Erianor, Ma**e**lius … Lardeal.
- **Cząstka środkowa = dominująca cecha charakteru** danej osoby, w formie „elfiej".
  To dokładnie te same cechy, co enum `CharacterTrait` (31) i kolumna „Cecha" w arkuszu
  Szlachta. Kod: `ElvenNameGenerator.internames` (mapa `CharacterTrait → cząstka`).

**Pełna tabela 31 cząstek (cecha → cząstka):**

| Cząstka | Cecha | | Cząstka | Cecha | | Cząstka | Cecha |
|---|---|---|---|---|---|---|---|
| Ino | CHILDHOOD (dziecko)* | | Isa | HONESTY | | Sae | MYSTICISM |
| Aio | MODESTY | | Ivo | PASSION | | San | IMPATIENCE |
| Ana | ALTRUISM | | Kel | RUTHLESSNESS | | Sel | RESPONSIBILITY |
| Ani | OPTIMISM | | Lia | TRUST | | Sia | IRRESPONSIBILITY |
| Asi | GENTLENESS | | Min | DEPRESSION | | Sin | IMPULSIVITY |
| Ave | COURAGE | | Nia | SERIOUSNESS | | Uro | REALNESS |
| Ban | EGOISM | | Ori | CAUTION | | Vae | PERSEVERANCE |
| Coe | SUSPICION | | | | | Vel | DECEPTIVENESS |
| Del | CALMNESS | | | | | Ven | PLAYFULNESS |
| Doe | COWARDICE | | | | | Via | VIRTUE |
| Don | EARTHLINESS | | | | | Vir | BLOODTHIRSTINESS |
| Eli | PRIDE | | | | | | |
| Esi | ROMANTICISM | | | | | | |

\* **`Ino`** = cząstka dziecięca — nosi ją każdy młody elf **przed uznaniem za dorosłego
(≈50 lat)**, niezależnie od charakteru. Dopiero dorosłość przynosi cząstkę-cechę.

**Reguły produktywne (kanon z powieści):**
- Cząstkę **można ukrywać/zmieniać.** Maelius naprawdę jest `Vel` (DECEPTIVENESS —
  oszust), ale przedstawia się jako `Ori` (CAUTION) albo `Ino` (dziecko), by dodać sobie
  powagi lub uśpić czujność. To fabularne narzędzie, nie błąd danych.
- Cząstka bywa **odczytywana jako charakterystyka** przy poznaniu („Asi oznacza
  łagodność" — Adalia).
- Weryfikacja korpusem: Sa**e**rsena **Asi** (łagodna arcykapłanka) · Aelnira **Sin**
  (IMPULSIVITY — ognista wyznawczyni Iliery) · Laergana **Del** (CALMNESS — spokojna
  szamanka) · Leana **Aio** (MODESTY) · Caelia **Uro** (REALNESS) · Viraela **Kel**
  (RUTHLESSNESS — wiwisekcjonistka). Wszystkie pasują do charakteru. ✅

**Rasy `X'sear`** (formalne, elfie): kael'sear (=alfy), quana'sear (=elfy), teial'sear
(=tulfy), zalea'sear (=zelerowie), **dira'sear (=Dergowie)** — dosł. „X-lud" (`sear` =
lud). Analogicznie **cor-osi'ale** (istoty Corellii). To ta sama morfologia rdzeń+`'sear`.

## Krasnoludy — dwa rejestry

1. **Rdzenne klanowe (prawdziwie krasnoludzkie):** twarda fonotaktyka
   (`DwarvenPhonotactic`: onset/coda `g, d, r, w` + `s, m, n, v`; nucleus `a, i` > `o,
   e, u, y`). Generuje szorstkie rdzenie. Toponimia z `-gol, -dol, -lon, -lin, -vore,
   -rin, -moore, -ta`.
2. **Przydomkowe w Saraverskim (Common):** złożenia opisowe po polsku — **Runiczna
   Piącha, Zakuty Łeb, Krwawy Czerep, Elfogrzmot, Męczybuła, Moczydupa, Szubienicznik**.
   Arkusz Nazwiska ma je jako składane: kolumny *„Krasnoludy – przód / – tył"*
   (Moczy+dupa, Męczy+buła, Świszczy+pała). To imiona „w tłumaczeniu" — jak inni je
   słyszą, nie ich własna mowa.

## Niziołki — zdrobnienia

Polski system zdrobnień, sufiksy `-ek/-oszek/-uszek/-iczek`, motywy przytulne/kulinarne:
**Szybcioszek, Bolinosek, Burczybrzuszek, Smoliszek, Ośmiorniczek, Naleśniczek,
Korniszonek, Pierniczek, Zauszek, Palniczek, Kłapouszek, Moczynosek.** (Arkusz Nazwiska,
kolumna „Niziołki".)

## Gnomy — trójstopniowy system życia

1. **Nazwisko rodu:** złożenie z apostrofem, brzmienie „mechaniczne" — **Gas'trogall,
   Ser'gevall, Bru'eldell, Gra'bendell, Brug'denall, Der'gonall, Sar'trogall.**
   (Onset drugiego członu: `-gall/-vall/-dell/-nall`.)
2. **Imię dziecięce (do 30 lat):** proste, cecha/kolor — *Brązowy Loczek, Niebieskie
   Oczko.*
3. **Imię dorosłe (od 30 lat):** oparte na **największym dziele twórcy**, długie i
   opisowe, z partykułą rodu: `El` = „syn rodu", `Al` = „córka rodu", `Ol` = „twórca
   rodu" (sygnał celibatu na rzecz twórczości, dla obu płci). Przykład kanoniczny:
   **„Oszałamiająca i Niezawodna Mikstura Ratująca Życie i Reperująca Samopoczucie
   El Sar'trogall"** (zdrobniale „Oszołom"). Por. z Anniversary: „Gromowa Kusza El
   Gra'bendell", „Perianitowy Automaton Ol Gas'trogall".

## Ludzie — style regionalne nazwisk

- **Rdzeń/Gilgamore:** mieszane; imiona władców germanizująco-fantasy (Riana, Merina,
  Fallon, Tagara, Erwin, Eol, Ranal, Garvon), nazwiska różne (Holzer — germańskie;
  Tagar; Mondenero; Coller; Tejero; Monzo).
- **Zaviles/Larazza (wpływ Aldaharu):** nazwiska **romańskie** (Sarrazin, Sartori,
  Castellano, Davila, Carranza, Montilla, Serrena, Sarrambert) — patrz Aldaharski.
- Imiona osobowe w Saraverskim (Adalia, Ezala, Arden, Golvar, Kajus, Rumen, Kern).

## Peura — epitety roślinne

Nazwy z natury/roślin (Zielona Wiśnia, Cny Miłorząb), region Leredia/Zielona Rubież.

## Nagi — dwie kultury, dwa rejestry nazewnicze

Nagi (rasa stworzona z węży przez Matkę Węży — patrz `Planety i pochodzenie ras.md`)
posługują się **językiem Aspektów** (patrz Warstwa I), ale dwie kultury robią to inaczej:

1. **Cesarstwo Tuexucańskie** (bagna na północ od Kalary; teokracja arcykapłanów
   Tuetuezaltli/Xuxucoatli/Yocaquecuy; piramidy schodkowe, kult śmierci). **Rejestr
   czysto aztecki** — toponimy, tytuły i imiona z języka Aspektów (końcówki `-tla/-oqua/
   -poca`). Sama nazwa „Tuexucan" pasuje do sygnatury (`x`, `-can`).
2. **Pocisearna** („Tutaj ludu Poci"; wyspiarskie królestwo na zachód od Aldaharu; kult
   Xohuepoci/Quatlacoquy/Taiki i geniuszy wody). **Rejestr mieszany/dwujęzyczny:** religia
   aztecka, ale **sama nazwa krainy jest Pradawna** — `Poci` + `sear` (lud) + `-na` (tu) =
   „Tu, lud Poci", ta sama morfologia co `Serea Quealna` i `X'sear`. To onomastyczny ślad
   **asymilacji z alfami/elfami** (Pocisearna leży blisko sfery elfiej — Aldahar/Serea
   Quealna). Wniosek: wodne nagi nazywają rzeczy świeckie/krainowe po Pradawnemu, a
   sakralne (patronki, kapłani) po azteckiemu — bilingwizm sakralno-świecki.

Wskazówka generatywna: Tuexucan → wszystko z `AspectPhonotactic`; Pocisearna → sakralne z
`AspectPhonotactic`, świeckie/krainowe przez Pradawny + złożenie `'sear`/`-na`.

## Zewnętrzni Bogowie — onomastyka grozy

8 imion (Leksykon): **Azrathun, Zegorath, Cai'chaguth, Izgarthul, Yuthogora, Aik'Thara,
Zug-Caiona, Sla'chargatha.** Sygnatura: apostrof (`Cai'chaguth, Aik'Thara,
Sla'chargatha`), zbitki `thr/chg/rg/thg`, końcówki `-th/-thun/-gorath/-thul/-thara`,
łączenie z tytułem-epitetem („Martwa Gwiazda o Białym Świetle"). Mają być **trudne do
wymówienia** — to celowe (kontakt z Pustką kaleczy). Pierwotni Bogowie kontrastują
brzmieniem azteckim (Coacoaitla, Yuaxomoatla) — to **język Aspektów**, patrz Warstwa I.

---

# Rozbieżności i rekomendacje (do decyzji autora)

1. **✅ Saravera — stratygrafia toponimów (nie błąd).** Aktywne `twich/vell/dell/lash/
   sill/swin/loi` to poprawna **warstwa rodzima (Nantwich)** dla późniejszych miasteczek.
   Brakuje jedynie: (a) **wpięcia warstwy ernizyjskiej** (`-more/-vore/-dol/-na/-in…`,
   patrz pkt 2) dla starych/dostojnych/ważnych ośrodków, (b) **przełącznika rejestru wg
   wieku+prestiżu** osady (prestiż→ernizyjski, miasteczko→rodzimy, wieś→`VillageNameGenerator`).
2. **🔶 Ernizjum ≠ Saravera + posiada prestiżowe końcówki.** `ErnizjumPhonotactic` to
   dziś kopia 1:1 Saravery. Zróżnicować (długie `aa/ee/oo/uu`, paradygmat `-ar/-ara/
   -are`) i **przenieść zestaw końcówek ważnych miast** (`-more/-vore/-vista/-voore/-dol/
   -na/-in/-nor/-el/-ia`) do `ErnizjumMorphology` — to substrat prestiżowej toponimii
   Saravery (Gilgamore, Lertavore, Caravista).
3. **🕳️ Caithaloon i Corellia — morfologia zakomentowana** (niezaimplementowana).
   Słownik Caithalooński (koniugacja `-a/-q/-x`, apostrof-szew) daje gotowy spec —
   patrz Warstwa I. Corelliański: brak jakiegokolwiek materiału (do zaprojektowania;
   sugestia: „świetlisty", płynny, dużo `l/n/vokali`, kontrast do Pradawnego).
4. **🕳️ Puste fonotaktyki w słowniku** (`Kalarski, Aldaharski, Durrenburdzki,
   Leredyjski, Irwitański, Mestilski, Otchłanny, Corelliański, Caithalooński` mają
   puste `Onset/Nucleus/Coda`). Wypełnić wg zasiewów z Warstwy I.
5. **Ujednolicić nazwę** Pradawny/Pierwotny/Nereneth (proponuję: język = *Pradawny*,
   wymiar = *Nereneth*).
6. **Dodać `WordType.RIVER`** (hydronimy `-ora/-ara`) i rejestr gnomich/krasnoludzkich
   końcówek osobno od saraverskich.
7. **🆕 Apostrof-złożenie w Pradawnym (`X'sear`).** Dorobić generator złożeń
   `rdzeń + ' + rdzeń` w `NerenethMorphology` dla nazw zbiorowych/krainowych (ETHNONYM/
   PLACE); dziś `applyEnding` daje tylko pusty przyrostek. Nie ruszać imion osobowych.
8. **🆕 Język Aspektów — nowa `AspectPhonotactic`.** Zaseedować z 12 imion Aspektów
   (nahuatl: `tl/tz/x/hua`, końcówki `-atla/-oqua/-ecua`), dopisać do słownika jako osobny
   język. Nazwać go na stałe (roboczo „język Aspektów"). Wpiąć jako rejestr nazewniczy nag
   (Tuexucan — czysto; Pocisearna — sakralnie, świeckie po Pradawnemu).

## Kolejność wdrożenia (proponowana)

1. Wypełnienie pustych fonotaktyk słownika (pkt 4) tekstem z tego dokumentu — czysta
   dokumentacja, zero decyzji twórczych.
2. Warstwa ernizyjska toponimii (pkt 1+2): przenieść końcówki prestiżowe do
   `ErnizjumMorphology`, dodać przełącznik rejestru wg wieku/prestiżu osady
   (prestiż→ernizyjski, miasteczko→rodzimy Saraverski/Nantwich, wieś→`VillageNameGenerator`).
3. Zróżnicowanie fonotaktyki Ernizjum (długie samogłoski) + implementacja Caithaloon (pkt 3).
4. Projekt Corelliańskiego i pozostałych stubów — osobna sesja twórcza.

---

# Wstępne fonotaktyki języków-stubów (na bazie korpusu nazw)

Rozwinięcie sekcji „Pozostałe stuby" z Warstwy I. **Wersje WSTĘPNE** — wyprowadzone z
istniejących nazw (powieść, arkusze Szlachta/Nazwiska/Geografia, Leksykon, notatki Next
Steps). Przy każdym języku podaję **korpus**, **pewność** i propozycję `Onset/Nucleus/
Coda`. To materiał do akceptacji/strojenia przez autora, nie kanon.

## Aldaharski — 🔶 pewność WYSOKA (bogaty korpus)

Język Aldaharu; wernakular szlachty Zavilesu i Larazzy (arkusz Języki). Charakter
**romański (iberyjsko-włoski)** — najlepiej udokumentowany ze stubów.
- **Korpus (nazwiska):** Sarrazin, Sartori, Castellano, Davila, Carranza, Tejero, Monzo,
  Serrena, Montilla, Sarrambert, Bonnet, Paquin, Molla, Navarra, Gaumont, Mondenero,
  Morten, Coller. **Imiona:** Melvin, Abalan, Zoraz, Vincent Amadeus Claribel, Silvius
  Flavius Alistair. **Toponimy:** Zelderin, Zaviles, Larazza, Jirdenal, Vizarna, Korsana.
- **Onset:** `b, c, d, f, g, l, m, n, p, r, s, t, v` + klastry `br, cr, tr, cl, gr` +
  geminaty `rr, ll` (Sa**rr**azin, Mo**ll**a).
- **Nucleus:** `a, e, i, o` (pełny zestaw, `o` częste — Monz**o**, Sartor**i**) + dyftongi
  `ia, io, ai, au` (rzadkie).
- **Coda:** samogłoskowe zakończenia dominują (`-o, -a, -e, -i`); spółgłoskowe `n, r, s,
  z, t, l` (Sarrazi**n**, Colle**r**, Monz**o**). Akcent paroksytoniczny.
- **Sygnatura:** dużo otwartych sylab CV, `-o/-a` na końcu, geminaty, brak twardych zbitek.

## Corelliański — 🔶 pewność ŚREDNIA (derywacja z Panteonu)

Brak nazw wprost „corelliańskich", ale **bogowie są bytami Corellii** — ich imiona to
najlepszy dostępny sondaż (por. `cor-osi'ale` = istoty Corellii).
- **Korpus (Panteon):** Armina, Birban, Eledin, Iliera, Irmus, Kalneter, Laenira, Manara,
  Pirenus, Serbena, Taika, Verena, Wisterus.
- **Onset:** `l, r, n, m, s, v, t, k, b, p` (miękkie, bez twardych klastrów); `∅`.
- **Nucleus:** `a, e, i, o` + lekkie dyftongi `ae, ia, ei` (L**ae**nira).
- **Coda:** otwarte lub `n, r, s` (Armin**a**, Eledi**n**, Manar**a**, Wisteru**s**).
- **Sygnatura:** świetlisty, melodyjny, 2–3 sylaby, końcówki `-a/-us/-in/-er/-a`. Kontrast
  do Pradawnego: mniej dyftongów, więcej pełnych samogłosek, spokojny rytm.

## Durrenburdzki — 🔶 pewność ŚREDNIA (germański + industrialny)

Durrenburg = najmłodsza, „przemysłowa" kultura (silnik parowy 1693). Charakter **germański**.
- **Korpus:** Durrenburg, Durnatel, (por. Durren-, -burg). Wpływ na Saraverę: „Durrenburdzki".
- **Onset:** `d, b, g, t, k, r, n, s, h` + klastry `br, gr, dr, tr, st, str`.
- **Nucleus:** `u, e, a, o` (ciemniejszy, „twardy" zestaw; mało `i`).
- **Coda:** `r, n, g, rg, rk, rn, t` (Durrenbu**rg**, Durnat**el**). Zbitki spółgłoskowe OK.
- **Sygnatura:** twarde, industrialne, `-burg/-berg/-natel`, bliskie krasnoludzkiemu, ale
  bardziej „ludzko-germańskie".

## Kalarski — 🕳️ pewność NISKA (imperium wschodu, kalowie)

Kalara, wschód; rasa **kalowie** (ostre zęby, imperium podbojów, „Towarzysze Namiestnicy").
- **Korpus:** Kalara, kalowie, `kalaruna` (nazwa języka, którą Maelius próbuje w powieści).
- **Onset:** `k, g, t, d, r, n, kh, tr, kr` (twarde, tylnojęzykowe).
- **Nucleus:** `a, u, o` (tylne, ciemne; mało `e/i`).
- **Coda:** `r, n, k, t` + `-una, -ar, -an`.
- **Sygnatura:** twarda, „obca", dużo `a/u`, tylnojęzykowe zwarte; brzmienie zaborcze.

## Leredyjski — 🕳️ pewność NISKA (natura, peura, blisko Pradawnego)

Leredia, Zielona Rubież; rasa **peura**; region używa też Pierwotnego (blisko Nereneth).
- **Korpus:** Leredia, `lerediar` (nazwa języka z powieści), peura. Nazwy peura po polsku
  (Zielona Wiśnia, Cny Miłorząb) — warstwa „w tłumaczeniu".
- **Onset:** `l, r, n, m, v, p, s` (płynne, miękkie); `∅`.
- **Nucleus:** `a, e, i` + dyftongi `ia, ie, ea` (L**e**r**e**dia).
- **Coda:** `r, n, l`, często otwarte.
- **Sygnatura:** liryczna, l/r-płynna, blisko Pradawnego, ale prostsza (mniej `ae`);
  końcówki `-ia, -iar, -edia`.

## Irwitański — 🕳️ pewność NISKA (nieumarli, Tantanor)

Irwitan (dom wolnych nieumarłych), Tantanor (na gruzach Repenvore po najeździe Mestilii).
- **Korpus:** Irwitan, Tantanor. (Repenvore = saraverski, nie irwitański.)
- **Onset:** `t, n, r, w, v, s, ir` (por. **Ir**witan).
- **Nucleus:** `a, i, o`.
- **Coda:** `n, r, t, l` (Irwita**n**, Tantano**r**).
- **Sygnatura:** cicha, dostojna, „starodawna"; dużo `n/r/t`, końcówki `-tan, -nor, -an`;
  pasuje do kultury śmierci/pamięci.

## Mestilski — 🕳️ pewność NISKA (język niemal martwy)

Mestilia (najeźdźcy 1439, zpacyfikowani 1443); przetrwał w nielicznych miejscach Irwitanu.
- **Korpus:** Mestilia, `mestilee` (nazwa języka z powieści).
- **Onset:** `m, s, t, l, r, n`.
- **Nucleus:** `e, i, a` + długie `ee` (mestil**ee**).
- **Coda:** `l, s, n`, często otwarte.
- **Sygnatura:** szeleszcząca, `s/t/l`-owa, długie `ee`; końcówki `-ee, -ia, -il`.

## Otchłanny (mowa demonów) — 🕳️ pewność NISKA (1 próbka)

Odrębny od Pustki/Zewnętrznego i od Caithaloońskiego. Maelius go zna (ale nie pradawnego).
- **Korpus:** `Gooth nwraghaz!` (jedyna próbka). Nazwy demonów/lewiatanów: Sorderon (ziz).
- **Onset:** `g, n, nw, gr, thr, zr, dr` (ciężkie klastry).
- **Nucleus:** `oo, a, e` (długie, tylne, ciemne).
- **Coda:** `th, z, gh, az, ath` (Goo**th**, nwragha**z**).
- **Sygnatura:** gardłowa, klastrowa, długie `oo`; kontrast do Caithaloońskiego (ten ma
  apostrof-szew i koniugację `-a/-q/-x`), do Zewnętrznego (Pustka: `irz'ens, voor, xers`)
  i do Pradawnego (dyftongi). Trzy „złe" języki są rozróżnialne fonetycznie — dobrze.

---

**Nota o pewności:** Aldaharski jest gotowy do implementacji (duży korpus). Corelliański
i Durrenburdzki są rozsądnie ugruntowane. Kalarski, Leredyjski, Irwitański, Mestilski i
Otchłanny to **kierunkowe szkice** z ubogiego korpusu — traktować jako punkt wyjścia do
autorskiej decyzji, nie gotowy kanon. Wszystkie do wpisania w puste pola
`Szczeliny - Słowniki.md`, gdy autor je zaakceptuje.
