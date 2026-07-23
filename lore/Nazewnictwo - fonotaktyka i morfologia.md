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

## Taksonomia języków (arkusz Języki)

| Język | Główny w | Używany też w | Status |
|---|---|---|---|
| Saraverski | Saravera | (lingua franca powieści) | ✅ fonotaktyka / ⚠️ końcówki |
| Ernizyjski | Ernizjum | Wornimore, Alstederia | 🔶 słownik + kopia Saravery |
| **Pradawny** (=Pierwotny) | Nereneth | Caithaloon, domena Serbeny, Leredia, Zielona Rubież | ✅ pełny (słownik==kod) |
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

## Saraverski (ludzie, lingua franca) — ✅ fonotaktyka, ⚠️ końcówki toponimów

`SaraveraPhonotactic`:
- **Onset:** proste `g, d, l, s, r, v` (częste) + bogaty zestaw *sylabowych* onsetów
  `is, il, as, av, ev, er, el, os, or, es, an, ag, …` (to one dają rytm typu
  **Is**vellin, **Er**gondol, **As**taria); rzadkie `w, f, j`.
- **Nucleus:** `a, e, i` > `o` > `u`.
- **Coda:** `n, r, l, ∅` > `s`. Maks. onset 2, coda 2, 2–4 sylaby.
- **Reguły:** brak hiatu (samogłoska+samogłoska w szwie), brak `qu+u`, `r+r`, `l+l`.

**⚠️ Rozbieżność do naprawy — końcówki miast.** *Aktywne* końcówki `CITY` w kodzie to
`twich, vell, dell, lash, sill, swin, loi` — **nie pasują do kanonu**. Kanoniczne miasta
Saravery mają końcówki (zakomentowane w kodzie, obecne w korpusie):

`-more/-vore` (Gilga**more**, Lerta**vore**, Worni**more**, Xali**vore**, Osti**vore**,
Quvel**more**) · `-dol/-gol` (Ergon**dol**) · `-na` (Egeren**na**, Sarde**na**,
Vizar**na**, Anver**na**, Felar**na**) · `-in` (Zelder**in**, Isvell**in**) ·
`-nor/-tor/-dar` (Tanta**nor**, Orva**nor**, Vango**dar**) · `-el` (Durna**tel**,
Mirna**del**, Reside**l**, Nadarve**l**) · `-ia/-ria` (Astar**ia**, Meri**nia**).
**Rekomendacja:** przywrócić zakomentowaną listę (`more, vore, gol, dol, na, lon, ia,
rin, ta, ra, nal, lin, nor, tel, rel, del, el, ria, vel, al, gal`) jako `CITY`, a
`twich/vell/dell/lash` przenieść do rejestru, do którego faktycznie pasują (brzmią
gnomio/krasnoludzko, por. Gra'ben**dell**, Ser'ge**vall**).

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
- toponimia z `-vista/-voore/-moore` (por. Cara**vista** — miasto na styku wpływów).
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

## Zewnętrzni Bogowie — onomastyka grozy

8 imion (Leksykon): **Azrathun, Zegorath, Cai'chaguth, Izgarthul, Yuthogora, Aik'Thara,
Zug-Caiona, Sla'chargatha.** Sygnatura: apostrof (`Cai'chaguth, Aik'Thara,
Sla'chargatha`), zbitki `thr/chg/rg/thg`, końcówki `-th/-thun/-gorath/-thul/-thara`,
łączenie z tytułem-epitetem („Martwa Gwiazda o Białym Świetle"). Mają być **trudne do
wymówienia** — to celowe (kontakt z Pustką kaleczy). Pierwotni Bogowie kontrastują
brzmieniem azteckim (Coacoaitla, Yuaxomoatla).

---

# Rozbieżności i rekomendacje (do decyzji autora)

1. **⚠️ Saravera — końcówki miast.** Aktywne `twich/vell/dell/lash/sill/swin/loi` nie
   pasują do kanonu (`-more/-vore/-dol/-na/-in`). Przywrócić zakomentowaną listę jako
   `CITY`; „gnomie" końcówki (`-dell/-vall`) przenieść do gnomów.
2. **🔶 Ernizjum ≠ Saravera.** `ErnizjumPhonotactic` to dziś kopia 1:1. Zróżnicować
   minimalnie: **długie samogłoski** `aa/ee/oo/uu` i końcówki `-vista/-voore/-moore` ze
   słownika. Dodać paradygmat `-ar/-ara/-are`.
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

## Kolejność wdrożenia (proponowana)

1. Naprawa końcówek Saravery (pkt 1) — najszybszy zysk jakości, zero decyzji twórczych.
2. Wypełnienie pustych fonotaktyk słownika (pkt 4) tekstem z tego dokumentu — czysta
   dokumentacja, łatwa do zaakceptowania/poprawienia.
3. Zróżnicowanie Ernizjum (pkt 2) + implementacja Caithaloon (pkt 3) w kodzie.
4. Projekt Corelliańskiego i pozostałych stubów — osobna sesja twórcza.
