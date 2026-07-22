# Raport spójności — przejście I (domeny kotwiczne)

Weryfikacja spójności lore (z pominięciem „Starej wizji"). Metoda: parsowanie
obu arkuszy `.ods` (własny parser ODF → tekst), skan słownictwa, wyrywkowe
czytanie. Źródła traktowane jako autorytatywne wg wskazań autora: **arkusze**
(skrótowy zbiór wszystkiego), **Kroniki Bastionu Mądrości** (oś czasu).

Legenda: ✅ spójne · ❌ sprzeczność · ⚠️ niejednoznaczne / do weryfikacji ·
🕳️ luka (świadomy brak).

---

## 1. Panteon — ✅ spójny

Arkusz **Kościoły** ↔ **Bogowie itp**: pełna zgodność.
- 13 bogów Corellii: Armina, Birban, Eledin, Iliera, Irmus, Kalneter, Laenira,
  Manara, Pirenus, Serbena, Taika, Verena, Wisterus.
- Pierwotni Bogowie (Coacoaitla/Rogata Pani … Yuaxomoatla/Królowa Lodów) —
  zgodni, z „Pradawnym imieniem" w arkuszu.
- Zewnętrzni Bogowie (Pustka): Azrathun, Zug-Caiona, Cai'chaguth, Yuthogora,
  Zegorath, Izgarthul, Aik'Thara, Sla'chargatha — komplet.
- **Arcykapłani 1320** zgadzają się co do osoby między arkuszem a prozą (Armina→Irna,
  Birban→Adela, Eledin→Rumen, Iliera→Aelnira, Manara→czarnowłosa gnomka,
  Serbena→Haernal, Taika→Saersena Asi Noelar, Verena→Kajus, Wisterus→Golvar).
- 🕳️ **Arcykapłani 1743** — puści w obu źródłach (spójna, świadoma luka do uzupełnienia).

## 2. Kosmologia / wymiary — ✅ lore spójne, ❌ rozjazd kod↔lore

Arkusz **Wymiary** ↔ **Bogowie itp**: 11 wymiarów zgodnych (Corellia, Wymiar
duchowy, Nereneth, Taelia, Otchłań, Ghalagaar, Caithaloon, Czeluść, Pustka,
Wymiar materialny), z magią/praktyką/mieszkańcami. Spójne.

❌ **Kod `EnchantType` nie pokrywa się z listą wymiarów:**
| Wymiar (lore) | `EnchantType` (kod) |
|---|---|
| Corellia | `CORELLIA` ✅ |
| Nereneth | `NERENETH` ✅ |
| Otchłań | `ABYSS` ✅ |
| Caithaloon | `CAITHALOON` ✅ |
| Taelia | `TAELIA` ✅ |
| Pustka | `VOID` ✅ |
| Wymiar duchowy | `VEIL`? (do potwierdzenia) ⚠️ |
| Ghalagaar | — (brak) ❌ |
| Czeluść | — (brak) ❌ |
| Wymiar materialny | `NONE`? ⚠️ |
| — | `LIMBO` (brak wymiaru-odpowiednika) ❌ |

Do decyzji: czy `VEIL`=Wymiar duchowy, `NONE`=materialny, oraz **co to `LIMBO`**
(nie ma go w Wymiarach), a Ghalagaar/Czeluść nie mają swoich `EnchantType`.

## 3. Oś czasu — warstwy epok

Nie ma jednej „teraźniejszości" — świat ma kilka warstw czasowych, i to jest
spójne, o ile świadome:
- **Osadnictwo** (kod, `PlacesConfiguration`): 265–1195 (miasta starsze od królestwa).
- **Monarchia** (arkusz **Władcy**): 1151 (Żelazna Riana) → 1739 (Fallon II Ostatni),
  dynastie Tagarytów (od 1194) i Holzerów (od 1507), bezkrólewie 1497–1507.
- **Kroniki narracyjne**: 1320–1408+ (Tom 1 „Zelderińska Żmija" 1320).
- **Dwie migawki bogów**: 1320 i 1743 (różnica 423 lata — potwierdza ją
  arcykapłanka Taiki „starsza o 423 lata"). ✅ wewnętrznie spójne.
- **Obłęd Irmusa / gra Anniversary**: ≈1743 (koniec monarchii = Fallon II *Ostatni*
  od 1739). ✅ ładny zbieg: dynastia gaśnie wraz z upadkiem świata.

### Miasta: kod ↔ Kroniki
- ✅ **Verdalon** — kod „zniszczone 1193" = zniszczone przez Merinę II Zajadłą
  (panowanie 1189–1194).
- ✅ **Wornimore** — kod „zbudowane 1188" = miasta Fallona Zdobywcy (1183–1189).
- ✅ **Gilgamore** — kod 1086, stolica; Kroniki: zalążek królestwa, rywal Lertavore.
- ❌ **Lertavore** — kod „zbudowane **1195**", ale Kroniki: „starsze od Saravery,
  stolica państewka, **podbite jeszcze przez Żelazną Rianę**" (≤1165). Rok w kodzie
  jest o ~30 lat za późny.
- ⚠️ **Egerenna** — kod „zbudowane **812**", ale Kroniki wśród „miast późniejszych"
  (obok Jirdenal 1455). Sprzeczna kolejność — do ustalenia.
- ⚠️ **Ergondol** — kod 894/zniszczone 1302; Kroniki: zniszczone, by pokonać
  czarowników renegatów (bez daty). Founding 894 vs „miasto późniejsze" — jak Egerenna.

### ⚠️ Irmus a Naderia
Kroniki: „Naderia rozpieprzona przez Irmusa" (miasto z ery Tagary, 1194–1226) oraz
Tom 14 „**W Rękach Irmusa — 1381**". Ale kanoniczny **obłęd Irmusa to 1743**.
Do rozstrzygnięcia: czy Irmus niszczy Naderię jako *przytomny* bóg ~1381 (dziwne
dla boga uzdrowienia), czy to zdarzenie należy do 1743, czy „Irmus" oznacza tu
kogoś/coś innego (wcielenie, kult).

> **Aktualizacja (odzyskany dokument „Szlachtownica 2.0"):** mechanizm obłędu jest
> teraz udokumentowany — Irmus próbuje poznać tajemnicę śmierci **z pominięciem
> Kalnetera**, zagląda w Pustkę i napotyka **spojrzenie Izgarthula** (1743), co
> niszczy jego umysł (zgodne z `Bogowie itp.md:1` i :665 „Istota, którą dostrzegł
> Irmus, gdy zajrzał w Pustkę"). To domyka *przyczynę* 1743. Otwarta pozostaje
> tylko relacja do „1381 / Naderia" — najpewniej metafora tytułu tomu albo osobne,
> wcześniejsze zdarzenie, nie sam obłęd.

## 4. Języki / geografia — ✅ spójne

Arkusz **Języki** wewnętrznie spójny i zgodny z kosmologią oraz geografią:
Aldaharski → Aldahar + **Zaviles, Larazza** (zgadza się z wpływami Kompanii
Południowo-Aldaharskiej), Irwitański → Irwitan + **Tantanor** (miasto nieumarłych
z Irwitanu), Pierwotny → Nereneth + domena Serbeny (Bogowie: „domena Serbeny
graniczy z Nereneth"). Powiązanie Repenvore → najazd Mestilii → na gruzach
Tantanor (Kroniki) spójne z Mestilskim „w nielicznych miejscach w Irwitanie".

## 5. Kod ↔ lore: kalibracja highmapa — ⚠️

Arkusz **Metryczka** to legenda highmapa: 0–2000 m (biel = 2000 m = 255/65535),
pasma **Niziny / Wyżyny (400) / Góry (900) / Góry whui (1400)**. Worldgen używa
`highmapHeightScale = 3000` — warto zejść do ~2000, żeby wysokości zgadzały się
z kanonem arkusza (i ewentualnie odwzorować progi pasm terenu).

---

---

# Przejście II

## 6. Rasy — ✅ kod spójny, ⚠️ dryf nazw w linii elfów

- ✅ **Kod `enum Race` (7: HUMAN, ELF, DWARF, HALFLING, GNOME, PEURA, BLOSSOMITE)**
  to spójny **podzbiór** rosteru z `TEST RASY` (Ludzie, Elfy, Krasnoludy,
  Niziołki, Gnomy, Peura, Blossomici). Reszta ras (Nagi, Cyklopy, Minotaury,
  Ogry, Centaury, Selkie, Gobliny, Harpie, Dergowie, Kalowie, Torakka, +gałęzie
  elfów) jest lore-only — sim genealogii obejmuje tylko rasy „szlacheckie".
- ✅/⚠️ **Taksonomia elfów — nazewnictwo częściowo rozstrzygnięte.** Bogowie używają
  formalnej: kael'sear (=alfy), quana'sear (=elfy), teial'sear (=tulfy, król
  Oberon), zalea'sear (=zelerowie), **dira'sear (=Dergowie)**. `TEST RASY` używa
  pospolitej (Alfy, Tulfy, Zelerowie, Dergowie) — brak tabeli mapującej w samych
  plikach (`*'sear` = 0 trafień w TEST RASY), warto ją dodać.
  - ✅ **ROZSTRZYGNIĘTE (autor):** „drowy" to deprecjonowany błąd → **Dergowie**
    (dira'sear). Do posprzątania 3 wystąpienia: `Bogowie:717`, `Anniversary:622,632`
    (zob. `Ustalenia audytu.md`).
  - ⚠️ **sorcas** (z Taelii) występuje w `TEST RASY`/Anniversary, brak w Bogowie —
    do wpisania w taksonomię. Rasy przecinają się z `Bestiariusz.md` i arkuszami
    bestiariuszowymi → domknąć w przejściu III.
- ℹ️ **Kalowie i Blossomici są pozaświatowi** (arkusz Planety: „Planeta kalów",
  „Planeta blossomitów" — rasy przybyłe). Kotwica: „w **672** pojawia się
  pierwsze miasto kalów" (bardzo wczesna data, przed monarchią).
- ℹ️ **Peura** = „dotknięci przez Nereneth" (biologicznie sprzężeni z drzewami) —
  rozwinięci w `TEST RASY`/kodzie, ale **nieobecni w Bogowie** (drobna luka opisu).
- 🕳️ `TEST RASY` to w większości **staby** (sam szablon „Czy świat by się zmienił,
  gdyby tej rasy nie było?"); treść mają tylko Elfy, Krasnoludy, Gnomy, Peura,
  Kalowie. To dokument w toku, nie sprzeczność.
- Genetyka: arkusz **Allele** (23 pary cech, np. Łagodność–Krwiożerczość) to
  system dziedziczenia osobowości — spójny z polami charakteru w `RaceProfile`
  i `ModelPerson` (kod). Nie zweryfikowano jeszcze mapowania 1:1 alleli↔pola.

## 7. Leksykon — ✅ spójny (glosariusz)

`Leksykon.md` to zhiperlinkowany słownik in-world (`[termin]` pod UI). Model
rzeczywistości (Nereneth w centrum; Otchłań/Corellia/Materialny graniczą i
zlewają; Wymiar Duchowy przenika wszystko; Pustka poza) oraz lista **8 Zewnętrznych
Bogów** — zgodne z Bogowie/Kościoły/mapą kosmologii. ⚠️ Drobny niuans granulacji:
Leksykon mówi o **5 wymiarach rdzeniowych + Pustka**, arkusz Wymiary wylicza **11**
(z podrealmami Taelia/Ghalagaar/Caithaloon/Czeluść). Do ujednolicenia opisu
(rdzeń vs pełna lista), nie sprzeczność.

### Ustalenie autora (z logu)
- **Fallon II Ostatni** znosi monarchię z własnej inicjatywy → władza do **Rady
  przedstawicieli grup** (kościoły, akademie); jest królem w czasach obłędu Irmusa.
  Zob. `Ustalenia audytu.md`. Domyka „dlaczego Ostatni".

---

---

# Przejście III — Bestiariusz ↔ rasy ↔ wymiary

## 8. Bestiariusz jako klucz spójności — ✅

`Bestiariusz.md` jest zorganizowany **wg typów = wymiarów pochodzenia**, a te
pokrywają się 1:1 z kolumną „Mieszkańcy" arkusza Wymiary i z Bogowie:
Śmiertelnicy=materialny, Demony=Otchłań, Bestie=Ghalagaar, Potwory=Caithaloon,
Cor-osi'ale=Corellia, Duchy=Wymiar duchowy, Pradawni=Nereneth, Przybysze=Pustka,
Abominacje=Czeluść, Tai'Alain=Taelia, Irmusi'ale=(skażona Corellia). To spina
kosmologię, rasy i mechanikę w jedno.

### ✅ Skonsolidowana taksonomia elfów (pass-II flaga ZAMKNIĘTA)
Bestiariusz dostarcza brakującej tabeli — każda gałąź Viare'sear żyje w wymiarze
odpowiadającym jej skażeniu:

| formalna (Bogowie) | pospolita | typ / wymiar (Bestiariusz) |
|---|---|---|
| kael'sear | Alfy | Pradawni — Nereneth |
| quana'sear | Elfy | Śmiertelnicy — materialny |
| teial'sear | Tulfy | Tai'Alain — Taelia |
| zalea'sear | Zelerowie | Bestie — Ghalagaar |
| dira'sear | **Dergowie** (dawniej „drowy") | Potwory — Caithaloon |

Pozostałe rasy-„potwory" też mają czysty wymiar: Gobliny, Harpie → Bestie
(Ghalagaar); Wendigo → Potwory (Caithaloon); sorcas → Tai'Alain (Taelia).
Rekomendacja: przenieść tę tabelę do Bogowie/Leksykonu jako jedno źródło prawdy.

### Pochodzenie ras — ✅ spójne (bestiariusz rozstrzyga)
- **sorcas** (Tai'Alain, Taelia) przekształcili niziołki → **Peura**.
- **Kalowie**: planeta Kalarnia → wrzuceni do Otchłani straszną magią →
  Saravera (~672); ocalali dzięki ofierze czczonej jako półbóg. Spina
  Kalara / Verena (bóstwo kalów) / arkusz Planety.
- **Aldahar**: ludzie zesłani przez magów-elfów z Ernizjum na drugą stronę globu.
- **Blossomici**: z „Zielonej Planety" przez Nereneth; przybyli z Peurą ~300 lat
  temu po wojnie z Leredią.

### Do ruszenia
- ⚠️ **Pisownia „Blossomite"**: `Blosomita` (Bestiariusz) / `Blossomici`
  (TEST RASY) / `BLOSSOMITE` (kod) — ujednolicić.
- ⚠️ **Datowanie względne**: elfy pamiętają założenie Saravery „5,5 wieku temu"
  (≈1701), wojna z Leredią „~300 lat temu" (≈1400) → „teraz" bestiariusza ≈ 1700
  (era obłędu Irmusa). Zweryfikować z arkuszem **Kalendarz** (daty bezwzględne).
- ⚠️ **Peura — podwójne pochodzenie**: „sorcas (Taelia) przekształcili niziołki"
  (Bestiariusz) vs „dotknięci przez Nereneth / sprzężeni z drzewami" (TEST RASY).
  sorcas są z Taelii, nie Nereneth — drobne tarcie do domknięcia.
- 🕳️ Staby: Torakka, Cyklop, zdublowane wpisy Kal/Torakka (szablon, jak w TEST RASY).
- ✅ Sprzątanie „drowy"→Dergowie (3 miejsca) — zob. `Ustalenia audytu.md`.

---

---

# Przejście IV — Kalendarz / oś czasu

## 9. Kalendarz jako system — ✅ spójny

Arkusz **Kalendarz** to **zegar świata, nie kronika** (brak dat rocznych — te
żyją w Kronikach i Władcach). Ustala:
- ✅ **Epokę**: „Nowy Rok = **założenie Ernizjum**" (1-Jan ernizyjski). Potwierdza,
  że wszystkie lata (1151, 1320, 1743…) liczy się **od powstania Ernizjum**.
- **Półkula południowa**: ernizyjski styczeń = ziemski lipiec (pory roku odwrócone) —
  spójne z położeniem Saravery (~40–46°S) z worldgenu.
- **Kalendarze kulturowe** (geopolityka): Ernizyjski (standard Saravery), **Aldaharski**
  (16 miesięcy po 21–23 dni), **Pradawny** („w Nereneth nie ma pór roku"). Mapują się
  na kultury z arkusza Języki / narody świata (Ernizjum, Aldahar, Nereneth).
- Rok ~364 dni: okresy ~26-dniowe, 4 pory roku (wiosna/lato ~89, jesień 87, zima 84 dni).

### ✅ Święta ↔ bogowie (spójne)
- Birbańskie święto miłości (Birban) · Eledińsko-Kalneterskie święto zmarłych
  (Eledin+Kalneter — **para jak w Bogowie**) · Irmusiańsko-Ilierskie święto nowego
  życia (Irmus+Iliera) · Manarskie święto handlu (Manara) · Pirenusiańskie święto
  ogniska (Pirenus) · Początek sezonu rolniczego. Wszystkie zgodne z domenami bogów.

## 10. Model osi czasu — trzy komplementarne warstwy

Nie ma jednej „master-listy" dat; oś czasu to trzy zgodne warstwy:
- **Kroniki** = wydarzenia (tomy 1320–1408+), Twój główny wyznacznik;
- **Kalendarz** = zegar (miesiące/tygodnie/święta, epoka = Ernizjum);
- **Władcy** = reżimy (1151–1739, dynastie).
Uzupełniają je daty w kodzie (miasta 265–1195) i w Bogowie (1597, 1671, 1743).

### Do rozstrzygnięcia (przeniesione/nowe)
- ⚠️ **Royal Debug ≠ kanon.** To **wygenerowane dane symulacji** genealogii (imiona
  typu „Moczynosek"), a jego daty rozjeżdżają się z Władcami (np. Tagara I:
  ur. 1191 w debugu vs ur. 1168 w kanonie Władców). Nie traktować jako źródła dat.
- ⚠️ **Rok „teraźniejszości".** Bestiariusz pisany ok. **1700** (Saravera zał. 1151
  „5,5 wieku temu"), a obłęd Irmusa / gra Anniversary = **1743**. Warto ustalić jeden
  kanoniczny „rok teraz" dokumentów in-world (albo świadomie różnicować daty pisania).
- ⚠️ (z pass I, wciąż otwarte) **Lertavore** kod 1195 vs podbite ≤1165; **Irmus a
  Naderia** 1381 vs obłęd 1743.

---

---

# Przejście V — Geopolityka

## 11. Geopolityka — ✅ bardzo spójna, rozwiązuje flagi

`Geopolityka.md` to datowana historia narodów i kosmosu. Cross-checki:
- ✅ **Ernizjum założone w ROKU 0**, stolica Armatan — domyka epokę (Kalendarz:
  „Nowy Rok = założenie Ernizjum"). Wszystkie daty liczone od Ernizjum.
- ✅ **„Teraz ≈ 1700" ROZWIĄZANE (flaga z pass IV):** najnowsze wydarzenie to
  durrenburdzki silnik parowy **1693**; Aldahar pada ~**1628** po wojnie z
  Białą Gwiazdą. Czyli rozwinięta „teraźniejszość" dokumentów ≈ 1700, a gra
  (obłęd Irmusa **1743**) to nadchodzący kryzys. Bestiariusz ~1700 ↔ geopolityka
  1693 ↔ gra 1743 = spójny ciąg, nie sprzeczność.
- ✅ **Kalowie 672** (pierwsi kalowie) / państwo 697 / imperializm 812 — zgodne z
  bestiariuszem i TEST RASY.
- ✅ **Ziemie Białej Gwiazdy**: „na biegunie północnym Pustka sięga świata, na
  czele Armii stoi apostoł" — dokładnie jak Bogowie (apostoł Zug-Caiony).
- ✅ **Aldahar**: zał. 794 → Kompania Południowoaldaharska 1423 → okręty w
  Saraverze **1454** → Imperium pada ~1628 (Biała Gwiazda). Spina bestiariusz
  (Aldahar = zesłani ludzie) i wcześniejsze ustalenia (podbój → handel).
  Uwaga: wpływy aldaharskie w Saraverze (Zaviles/Larazza) to zjawisko **post-1454**.
- ✅ **Irwitan** (zał. 428, nieumarli z głodu, wędrówka na północ po upadku
  **Mestilii 1253–1447**) — zgodne z Bogowie i z „Repenvore→Tantanor" z Kronik.
- ✅ **Cesarstwo Tuexucańskie** (nagi, arcykapłani Pierwotnych: Tuetuezaltla,
  Xuxucoatla, Yocaquecua) i **Pocisearna** (nagi wodne, Xohuepoca/Quatlacoqua/Taika)
  — spójne z bestiariuszem (Naga Tuexuca/Poci) i Kościołami (Pierwotni).
- 🎯 **Szczelina Życia** (~8500 przed Ernizjum: Nereneth muska materię, pierwsze
  alfy w **Serea Quearna**) = źródło nazwy „Świat Szczelin" i pochodzenia elfów
  (alfy → Serea Quearna → elfy → część zakłada Ernizjum).

### Do ruszenia / TODO autora
- 🕳️/⚠️ **Mityczny założyciel Ernizjum — TODO autora.** W tekście jest wprost:
  „(Przepisać tak, żeby mieli mitycznego półboskiego założyciela)". Obecny kanon:
  Ernizjum założone przez elfy z Serea Quearna, rządzone przez Cesarzową (następczyni
  wybierana + namaszczenie bogów — niedynastycznie). To już **pasuje do „przyciąga,
  nie anektuje"** (Sojusz Ernizyjski rósł handlem, zatrzymał się na barierach), ale
  planowany mityczny założyciel nie jest jeszcze wpisany.
- 🕳️ **Sojusz Ernizyjski — daty placeholder** „xxx-xxxx" do uzupełnienia.
- ℹ️ **„Towarzysze Namiestnicy"** (Dominium Kalarskie) — sprawdzić, czy „Towarzysze"
  to ten sam byt/tytuł co plik `Towarzysze.md` (przejście VI: postaci).

---

---

# Przejście VI — Organizacje / Pakt

## 12. System Paktu (patroni) — ✅ spójny z kosmologią

Arkusz **Pakt** = patroni pogrupowani **wg wymiarów**, każdy z domeną i drabinką
zdolności (Łaska/Więź/Gniew/Wejrzenie/Nóż/Forma Wybrańca):
- **Caithaloon**: Ziz/Kraken/Behemot (Powietrze/Woda/Ziemia) — ofiara: Życie.
- **Otchłań (Demony)**: Ojciec Czartów, Ognisty Smok, Matka Lilimów — ofiara:
  Rytualne/Brutalne Morderstwo.
- **Wymiar Duchowy (Duchy)**: Geniusze Ognia/Powietrza/Wody/Ziemi — ofiara: Artefakt/Mana.
- **Nereneth (Natura)**: Pierwotni Bogowie jako patroni — ofiara: Krew/Życie.
- **Pustka**: Zewnętrzni Bogowie (Cai'chaguth, Yuthogora, Sla'chargatha, Zegorath…)
  — ofiara: Brutalne Morderstwo / **cudza Dusza**.
- ✅ **Koszt ofiary rośnie z „ciemnością" wymiaru** (Życie → Krew → Morderstwo →
  Dusza) — dokładnie jak moralny koszt patronów w Anniversary.

### Do ruszenia
- ⚠️ **13. Pierwotny bóg — „Pradziad Leszy" (Zemsta)** jest w arkuszu Pakt, ale
  **Bogowie itp wymienia tylko 12** Pierwotnych. Dodać do Bogowie/Kościołów lub
  wyjaśnić status.
- ℹ️ Artefakt edycji: w komórce „Królowa Lodów" wpadło „-Michał Furmanek" — usunąć.

## 13. Organizacje — ✅ spójna sieć

`Organizacje.md` (JAWNE/TAJNE + system klas). Kluczowe byty spinają się z resztą:
- ✅ **Zakon Gorejącego Słońca** (Organizacje + Kroniki) — jego wielki mistrz kończy
  bezkrólewie i **zakłada dynastię Holzerów w 1507** (arkusz Władcy). Spójne.
- ✅ **Bastion Mądrości** — instytucja-autor **Kronik** („Kroniki Bastionu Mądrości").
- ✅ **Ametystowy Pakt** — inspirowany Tomem 23 Kronik („Ametystowe Kłamstwo”),
  ametyst = kamień **Kalnetera** (jego symbol: „kruk z ametystowymi oczami”). Spójne.
- ✅ **Dzieci Taiki** — rada magów z **Egerenny**, elita **Wędrownych Sztukmistrzów**
  (Kroniki: fala wędrownych magików, kościół Taiki). Spójne.
- ℹ️ Sekcja klas/podklas (Barbarzyńca…Zwiadowca, w tym Technomanta/Mechanik dla
  Durrenburga, Kultysta dla Pustki) = mechanika gry → domknąć z arkuszami Umki/Profesje.

### Nota osi czasu (potwierdzenie autora)
- ✅ **Zelderińska Żmija = powieść z epoki Eola Szalonego.** Eol II Szalony rządził
  **1312–1346** (Władcy), a Kroniki datują „Zelderińską Żmiję" na **1320–1323** —
  mieści się w jego panowaniu. Kroniki ↔ Władcy ↔ powieść zgodne.

---

---

# Przejście VII — Geografia / Rejony ↔ kod

## 14. Prowincje — ✅ pełna zgodność, zbieżność klimatu

Arkusz **Geografia** = master-tabela prowincji (dynastia → stolica → prowincja →
miasta → rody → avg wysokość + klimat/wilgotność). **Wszystkie 13 prowincji
zgadza się z kodem** (`ProvinceInitializer`):

| Prowincja | Stolica | avg wys. | klimat / wilgotność |
|---|---|---:|---|
| Merinia | Gilgamore (stolica krajowa) | 1000 | klimat 3 |
| Nowa Corellia (świątynna) | Lertavore | 800 | — |
| Alstederia | Sartama (delta) | 150 | — |
| Międzygórze | Uvarra (+Tantanor) | wys. | góry |
| Viroelann | Wornimore | 1200 | klimat 4 |
| Carasera | Caravista | — | — |
| Druantia | Ergondol/Astaria | 400 | — |
| Larazza | Jirdenal/Korsana | 100 | klimat 5 |
| **Orvanor** | Xalivore | **1700** | **klimat 2–3, wilg 4–5 (mokro i zimno)** |
| Pirena | Pernagol | 600 | klimat 3/2, wilg ~2 (sucho) |
| Zaviles | Zelderin | 200 | klimat 4 |
| Saraidan | Egerenna | — | — |
| Zielona Rubież | Durnatel | 500 | klimat 2–4, wilg 4–5 (mokro) |

- ✅ **Zbieżność klimatu z 4 źródeł**: Geografia ↔ autorska `Humidity`/`Climate`
  (pass I) ↔ audyt temperatur (pass IV) ↔ intencja worldgenu. Orvanor
  zimny+mokry, Zielona Rubież mokra, Pirena/Zaviles sucho-cieplejsze. Pełny consensus.
- ✅ **Nowa Corellia = „prowincja świątynna"** — spójne z `EnchantType.CORELLIA`
  (wymiar boski) i stolicą Lertavore (`LOCAL_CAPITAL` w kodzie).
- ✅ **Rody per prowincja** (Tagar→Merinia, Holzer→Nowa Corellia, Elfogrzmot…) —
  łączą się z arkuszem Nazwiska i Royal Debug (→ pass VIII: postaci).

## 15. Rzeki i model temperatury — ✅

- ✅ **Rzeki graniczne**: Elsenora (Saravera–Ernizjum–Leredia), Zeldera
  (Saravera–Kalara; Zelderin od niej), Trebonar (Durnatel), Twilord (płd. granica
  Zielonej Rubieży → Leredia). **Wszystkie mają pliki `.points` w kodzie.**
- ✅ **Morze Długie** łączy Saraverę/Ernizjum/Kalarę/Aldahar + port Durrenburg.
- ✅ **Kanoniczny model temperatury** (arkusz Geografia): 0 m = 26,5°C; 39°S =
  22,8°C. Zgodny z kalibracją worldgenu (`tempNorthC≈25`). Można dostroić worldgen
  do dokładnych wartości arkusza + użyć kanonicznych wysokości prowincji.

## 16. Rejony (game-map) — ✅

Arkusz **Rejony** (1001 wierszy) to podział świata na rejony (Irvitan, Lodowiec
Irwitański, Kalarskie Kolonie 1-19…), nie tylko Saraverę. Prowincja↔rejon jest
~1:1 z wyjątkami z Anniversary: „Merinia + Nowa Corellia = jeden rejon",
„Durnatel (Zielona Rubież) = dwa rejony". Spójne (rejon = warstwa mapy gry).

### Do ruszenia
- ⚠️ (wciąż) **Lertavore** kod 1195 vs stolica starej Nowej Corellii podbita ≤1165.
- ✅ **Kalibracja worldgenu WPIĘTA** (branch worldgenu): model temp (tempNorthC=22.2,
  tempSouthC=17.6, lapse 6.5) + highmapHeightScale=2000. Zweryfikowane renderem
  (masyw zimny). Follow-up: kotwica wysokości per prowincja (working3.png to szkic).

---

---

# Przejście VIII — Profesje / Umki

## 17. System klas/umiejętności — ⚠️ w trakcie refaktoru (najwięcej rozbieżności)

Zgodnie z przewidywaniem — to najmniej ustabilizowana domena. Rozbieżności są tu
głównie **wewnętrzne** (równoległe wersje), nie sprzeczne z resztą świata:

- ⚠️ **Równoległe wersje (refaktor „worki zamiast domen" z Anniversary):**
  `Profesje` vs `Profesje - nowe` (oba ~1000 wierszy), `Umki` vs `Szerokie Umki`.
  „nowe"/„Szerokie" to wersja bucketowa. **Rekomendacja: wybrać kanoniczną,
  drugą zarchiwizować** — inaczej każda zmiana musi być robiona dwa razy.
- ⚠️ **Roster klas/podklas jest ogromny (~100+) i miesza epoki:** przetrwałe ze
  Starej wizji (`Czempion`, `Czarnoksiężnik`), obecne z Organizacji (`Technomanta`,
  `Mechanik`, `Kamikaze`, `Flagelant`, `Kultysta`), i lore'owe nowe
  (`Apostoł Białej Gwiazdy`, `Badacz Wymiarów`, `Druid Śmierci`, `Cmentarnik`).
  Warto uzgodnić jedną kanoniczną listę klas między `Organizacje.md` a arkuszem Profesje.
- ✅ **Zależności (relacje bóg↔klasa)** spójne z resztą: multiklasy i łączone
  wyznania (Kalneter + Eledin/Irmus/Taika/Laenira/Armina/Wisterus; Eledin →
  paladyni/wojownicy/barbarzyńcy) — zgodne z „kapłanami wielofunkcyjnymi" z
  Anniversary i parami bogów z Bogowie itp.
- ✅ **Klasy lore'owe pasują do kosmologii:** Apostoł Białej Gwiazdy (Pustka),
  Badacz Wymiarów, Demonolog (Otchłań), Druid Śmierci — spójne z wymiarami.
- ℹ️ **Klasy są game-side** — nie ma ich w kodzie symulacji genealogii (kod = sim
  rodów, nie mechanika walki RPG). Spójne (kod = podzbiór).
- 🕳️ Arkusze mają markery WIP (np. „MODYFIKATOR MODYFIKATORA … WYJEBAĆ") — do sprzątnięcia.

---

# Przejście IX — Postaci / szlachta / feudalizm

## 18. Władcy ↔ Kroniki — ✅ wzorcowa zgodność (Kroniki = oś czasu)

Arkusz **Władcy** (26 monarchów, 1151→1739+) to tabelaryczny zapis dokładnie tej
samej historii, którą **Kroniki** opowiadają narracyjnie. Zbieżność jest pełna:

- **Żelazna Riana** (1151–1165) → zabita przez **Urga I Kowala** („bo rozłupał jej
  czaszkę młotem", Władcy) = Kroniki: „posługująca się imieniem zabójczyni Urga…
  zagryza Fallona… jej ludzie zabijają kapłana i maga". ✅
- **Tagara I Budownicza** (1194) — Kroniki: kapłanka Iliery prowadzi powstanie
  przeciw Merinie II, nie chce korony, w końcu przyjmuje; buduje kraj, zakłada
  **Zakon Gorejącego Słońca** (paladyni-stróże) i **dynastię Tagarytów**. ✅
- **Garvon I Bezpłodny** (do 1497, bez potomka) → **bezkrólewie 1497–1507** →
  Wielki Mistrz Zakonu Gorejącego Słońca **Garvon Holzer** kończy walki książąt,
  opuszcza zakon, zakłada **dynastię Holzerów**. Nota bezkrólewia we Władcach jest
  słowo-w-słowo streszczeniem sceny z Kronik. ✅
- **Fallon II Ostatni** (1739–, ur. 1710) — epitet „Ostatni" potwierdza **ustalenie
  autora**: Fallon II znosi monarchię na rzecz Rady, jest królem w czasach szaleństwa
  Irmusa (1743; w 1743 ma 33 lata). Ostatni monarcha = domknięcie linii. ✅

## 19. Szlachta ↔ Władcy ↔ kod — ✅ spójny system

- **Rody królewskie osadzone w miastach**: `Gilgamore → ród Tagar` (dyn. od 1194)
  = dynastia Tagarytów; `Lertavore → ród Holzer` (od 1183, królewski od 1507) =
  dynastia Holzerów. Feudalizm potwierdza zakres: „Tagar 1194-1497" = pełne
  panowanie Tagarytów (Tagara I → koniec Garvona I → bezkrólewie). ✅
- **Etymologia szlachty** (Kroniki l. 39): Fallon Zdobywca nagradza zasługi,
  Tagara dokłada „szlachetność serca" → grupa nazwana **szlachtą**; od Erwina
  Kupca zwani **książętami**. Zgadza się z kolumnami nadania w arkuszu Szlachta
  (**Riana 1151 / Fallon 1183 / Merina 1194** — daty panowań nadających). ✅
- **Trasa podboju Fallona** (Kroniki): Gilgamore/Lertavore → bród → Xalivore →
  Pernagol → Caravista → Sartama → Astaria — wszystkie to miasta z arkusza
  Szlachta. ✅ Kolonie północy: Kompania Południowoaldaharska zakłada **Jirdenal**,
  potem dominuje **Zelderin** — zgodne z rodami Astager/Jirdenal i Sarrazin/Zelderin
  (start dyn. 1195) oraz z motywem „czerwonych żagli" (plik `Barwy Czerwieni`).
- **Cecha rodu ↔ kod `CharacterTrait`**: kolumna „Cecha" (MODESTY, ALTRUISM,
  COURAGE, EGOISM…) to **dokładnie** enum `CharacterTrait.java` — 31 cech 1:1,
  każdy ród ma odrębną. ✅
- **Nazwiska** (pula per rasa): krasnoludzko/niziołkowe nazwiska składane
  (Moczy+dupa, Męczy+buła) generują komiczne rody z arkusza Szlachta
  (Moczydupa, Męczybuła). ✅ Kod używa tej samej mechaniki (`ElvenNameGenerator`
  dla elfów; enum `CharacterTrait` w obu).

## 20. Towarzysze / Royal Debug — ✅ warstwy odrębne, niesprzeczne

- **`Towarzysze.md`** = postacie-towarzysze do gry, osadzone w **epoce powieści**
  (narratorka Aelervesa działa „kilkaset lat po wydarzeniach książek"). To nie
  rody szlacheckie z arkuszy, lecz bohaterowie osobowi — inna warstwa, brak
  sprzeczności. Odwołań do kanonicznych rodów/miast mało i spójne.
- **Royal Debug** = zrzut symulatora genealogii (`#LEGAL`, pary rodziców, daty).
  Używa kanonicznych rodów (Tagar, Szybcioszek, Gillear), ale **własny seed**:
  Tagara I ur. 1191 (sim) vs 1168 (kanon, Władcy). Potwierdza: **sim ≠ kanon** —
  Royal Debug to wyjście generatora, nie źródło prawdy osi czasu. ✅

### Domknięcie flagi z przejścia III (Lertavore)

Flaga „Lertavore: kod 1195 vs Kroniki ≤1165" — **przeszacowana**. Fallon I Zdobywca
panuje **1183–1189** (Władcy), a Kroniki (l. 122) mówią, że Lertavore istniało już
za jego podbojów; ród Holzer datowany od **1183** (Szlachta/Feudalizm). Realny
rozjazd to tylko **kod = 1195 vs lore ≈ 1183–1189** (dryf ~jednego pokolenia,
`PlacesConfiguration`), nie 30-letnia sprzeczność. Do drobnego wyrównania w kodzie.

### Do ruszenia

- ⚠️ **Wornimore — ród:** Feudalizm podaje linię **Seldan** (1154–1186) jako
  panującą, arkusz Szlachta jako „ważny" ród **Raevill** (Elfy, 687). Prawdopodobnie
  starożytny ród elfów (Raevill) + ludzkie nadanie książęce (Seldan) — do potwierdzenia
  autora, czy współistnieją, czy to rozjazd.
- 🕳️ **`Towarzysze.md`** ma miejscami surowe notatki robocze (język roboczy,
  „XD") — do redakcji, ale merytorycznie spójne.

---

## 21. Odzyskany dokument „Szlachtownica 2.0 — notatki projektowe" — ✅ źródło, nie sprzeczność

Autor dostarczył brakujący plik (tylko jako PDF → `lore/Szlachtownica 2.0 - notatki
projektowe.md`). To **dokument-źródło**: roboczy zlew projektowy, z którego wyrosła
część obecnego kanonu. Weryfikacja: elementy z niego **już istnieją** spójnie w
kanonie i kodzie, więc nie wnosi sprzeczności, a raczej *domyka przyczyny*:

- **Panteon Pustki**: `Verena` (bogini pragmatyzmu/„skurwysyństwa") i `Izgarthul`
  (Patrzący z Pustki) — **już w `Bogowie itp.md`** (417, 662). ✅
- **Obłęd Irmusa 1743**: udokumentowana przyczyna (spojrzenie Izgarthula przy próbie
  poznania śmierci z pominięciem Kalnetera) — patrz aktualizacja flagi Irmus/Naderia. ✅
- **Zug-Caiona** (upadek Aldaharu) — spójne z ustaleniem autora z wcześniejszej sesji. ✅
- **Geografia/rody w kodzie**: `Korsana` (zniszczona ~1140 → migracja → Jirdenal),
  ród `Sarrambert` z siedzibą `Amarastus`, `Alabaster` k. Tantanoru — **Korsana i
  Sarrambert już są w kodzie** (`PlacesConfiguration`, `FamiliesConfiguration`;
  Sarrambert z wasalstwem 1186→Tagar 1194→∅ 1497→Holzer 1507 — zgodne z osią rodów). ✅
- **Klątwa Zavilesu/Zelderinu** (~1305–1322, kapłan Vereny → kultysta Izgarthula,
  „owoce z Caithaloonu", epidemia CURSED) — tło powieści z jesieni 1320 (panowanie
  Eola II Szalonego 1312–1346 ✅). Materiał do przyszłego pass o `Zelderińska Żmija`.
- **Warstwa silnika** (BreedingStrategy, State, pełne reguły przejść RegionType /
  TerrainType, taksonomia terenu) — spójna z enumami w kodzie; do wykorzystania przy
  pass X (rzemiosło/teren) i przy ewentualnym refaktorze inicjalizatorów.

### Do ruszenia (nowe z dokumentu)

- ✅ **`Alabaster` — nie osada, lecz złoże (ustalenie autora).** Alabaster to **złoża
  alabastru w pobliżu Tantanoru**, z którego zbudowano samo Tantanor. W kodzie już jest
  jako `TerrainResource.ALABASTER` — poprawnie. Detal geologiczno-budowlany (region wokół
  Tantanoru powinien mieć alabaster w surowcach), nie brakujące miejsce.
- ℹ️ **`Amarastus` — siedziba rodu, nie miasto (ustalenie autora, potwierdzone 3×).**
  To **pałacyk/twierdza głowy rodu Sarrambert**, nie osada. Korroboracja: (1) odzyskany
  doc „predefiniowana siedziba Amarastus dla Sarrambertów"; (2) ustalenie autora; (3)
  **`Lochołaz` l. 143: „Amarastus – Pałac Vincenta Amadeusa Claribela von Amarastus"**.
  Późniejsza gałąź wampirza (von Amarastus) rezyduje w tym pałacu (patrz sekcja 26).
  Nie należy do listy miast.
- ℹ️ **„Zaktualizować daty startu prowincji"** — nota TODO autora; spina się z flagą
  dryfu dat (Lertavore 1195 itd.). Kandydat do przeglądu `PlacesConfiguration`.

---

# Przejście X — Rzemiosło / materiały / ekwipunek / ekonomia

## 22. Taksonomia materiałów — ✅ arkusz ↔ kod ↔ lore

- **Arkusz Materiały ↔ kod `TerrainResource` / `MaterialStats`.** 106 surowców w
  kategoriach STONE / ORE / GEM / FLUID / FOOD (`ResourceCategory`, każda z własną
  skalą wielkości złóż `ResourceDeposit`). Wymyślone metale — **Leranit, Barnizyt,
  Kasalit, Vealitium, Keltainum, Garandal, Eleryt, Perianit, Kuparin, Celenit,
  Turanit, Irintal** — są **i w arkuszu Materiały, i w enumie** kodu. ✅
- **`MaterialStats` = kolumny arkusza:** twardość / waga / wytrzymałość (endurance) /
  trwałość (durability) / komfort / prestiż / wartość + `ProductionType` + lista
  `TerrainResource`. Model kodu odwzorowuje strukturę arkusza. ✅
- **Materiały żyją w świecie** (nie są czysto mechaniczne): Anniversary l. 332 używa
  ich w epitetach gnomów (**Murandytowy Rapier, Perianitowy Automaton, Obsydianowa
  Tarcza**), a l. 596 w mechanice rozpoznawania („topór z **irmunu**", „**gilgamorska**
  zbroja"). Materiał = i statystyki, i element kultury nazewniczej. ✅

## 23. Handlarze Saqra — ✅ spójni z kosmologią (Taelia / Tai'Alain)

- Design (`Handlarze Saqra.md`): tajemniczy kupiec + **żeńska wersja**, wymiana
  **unikat-za-unikat**, magiczna waluta z magicznych przedmiotów/części stworzeń,
  znamię przywołania. Archetyp „diabelskiego sklepiku".
- **Bestiariusz** l. 1642: `Saqra` typowana jako **Tai'Alain** (byty Taelii — wymiaru
  dzikiej, chaotycznej magii). **Biblioteka Szczelin** l. 571: **Patriarcha Saqra /
  Matriarchini Saqra** = dokładnie para kupiecka (męska + żeńska) z design doca. ✅
- Spójne z backbone'em wymiarów: kupiec spoza praw materialnych = byt Taelii. Nie
  „diabeł" dosłownie, lecz istota chaotycznej magii — pasuje do bezwartościowo-cennych
  unikatów („zbroja, która się nie brudzi", „flet grający ciszę").

## 24. Profesje rzemieślnicze — ✅ spójne (game-side)

`Ekwipunek.md` opisuje rzemiosła (Alchemia, Bednarstwo, Garbarstwo, Introligatorstwo,
Inżynieria, Jubilerstwo, …) + system zarządzania ekwipunkiem (widok drużynowy,
pojemniki dedykowane, tragarze). Spójne z arkuszem Rzemiosło i systemem profesji
(pass VIII). Warstwa gry — jak klasy, nie ma jej w kodzie sim genealogii (kod = podzbiór).

### Do ruszenia

- 🕳️ **Brak glosariusza materiałów wymyślonych.** Leranit, Barnizyt, Kasalit,
  Vealitium, Diabelium (Sertelat/Murandyt) i in. istnieją jako **mechanika (arkusz +
  enum) + rozproszone użycia**, ale nie mają nigdzie opisu lore (czym są, skąd, jakie
  właściwości narracyjne). Tonalnie spójne (metale „diabelskie", rudy Pustki:
  `VOID_ORE`, metale niebiańskie: `MOON_METAL`/`SUM_METAL`/`NIGHT_METAL`, `SOUL_STONE`),
  ale warto by dorobić krótki leksykon materiałów, spinając je z wymiarami/kosmologią.
- ℹ️ **`ProductionType` / `ExploitationType` / taksonomia terenu** z odzyskanego
  „Szlachtownica 2.0" (TerrainType/Shape/Characteristic/Enchant) — spójna z enumami
  kodu (`SoilType`, `TerrainResource`, `ProductionType`, `RegionType`); pełna weryfikacja
  reguł przejść `RegionType` (setki reguł) to osobne, mechaniczne zadanie, nie lore.

---

## Domeny na przejście XII

- **`Zelderińska Żmija.md`** — ukończone (sekcja 27). Audyt spójności całości domknięty.

---

# Przejście XI — Rośliny / składniki + drobne pliki

## 25. Rośliny / składniki — ✅ biogeografia spójna z geografią i kosmologią

- **Arkusz Rośliny**: kolumny `real`/`mine` (rzeczywista vs wymyślona), `Wymiar`
  (domyślnie **Fizyczny + Nereneth** = wymiar natury), `Pochodzenie` (skąd gatunek).
  Origina: **Saravera (54), Leredia (42), Ernizjum (35), Kalara (31), Aldahar (27),
  Irwitan (5), Caithaloon (3)** — wszystkie to **kanoniczne miejsca** (Geopolityka:
  Kalara = imperium podbojów „kalów", Leredia itd.). Biogeografia = geografia świata. ✅
- **Rośliny z Caithaloonu** (`Czarne Drzewo`, `Czarna Viruka`) — wymiar i pochodzenie
  Caithaloon. To domyka motyw **„owoce z Caithaloonu"** z klątwy Zelderinu (odzyskany
  doc / sekcja 21): kultysta hoduje właśnie rośliny wymiaru Caithaloon. ✅
- **Arkusz Składniki**: rozbicie każdej rośliny na części (owoce/kwiaty/liście/kora/
  łyko/nasiona/drewno) z flagami użycia (Pożywienie / Przyprawa / Zielarstwo / Alchemia
  / Rzemiosło / Zaklinanie) — system alchemii/zielarstwa z `Ekwipunek.md`. ✅
- **Grzyby** (dół arkusza): wymyślone modelowane na realnych (Phallus, Omphalotus,
  Microstoma…) — ta sama metoda `real → mine` co przy roślinach i materiałach.

## 26. Drobne pliki — ✅ spójne, domykają wątki

- **`Gilgamore.md`** — klucz lokacji stolicy Tagarytów (pass IX). Dzielnice/obiekty:
  **Podarek Kalnetera** i **Świątynia Kalnetera** (bóg śmierci), **Siedziba licza**
  (nieśmiertelny władca umarłych — postać-legenda, którą autor rozwijał), **Grobowiec
  Królów**, **Zakon Gorejącego Słońca** (zakon Tagary!), świątynie panteonu (Iliera,
  Eledin, Armina, Laenira, Wisterus, Taika), Rezydencja **Canderelii … von Amoladora**.
  Spójne z pass IX (stolica + Zakon Gorejącego Słońca) i pantheonem. Gilgamore =
  sakralne serce (dar boga śmierci), stąd siedziba licza. ✅
- **`Barwy Czerwieni.md`** — ⚠️ **KOREKTA pass IX:** to NIE żagle Kompanii, lecz
  **polityka frakcji wampirzych** („czerwień" = krew/nieumarli). Zawartość: zakony
  nieumarłych z siedzibą w **Tantanorze** (Pochmurni Krzyżowcy, Kapituła Wieczności,
  Rada Szkarłatu, Perłowa Rota), **Rada Cieni** w Lazurowej Radzie w Gilgamore, Liga
  Żywych Inaczej (koegzystencja). Postacie wampirze złożone są z **kolumn wampirzych
  arkusza Szlachta**: „Silvius Flavius Alistair von Lunacarmesi" = Silvius+Flavius+
  Alistair (wszystkie w arkuszu). ✅ **Tantanor** jako kolebka zakonów nieumarłych
  spina się z ustaleniem autora („W Tantanorze narodził się zakon") i „Siedzibą licza"
  w Gilgamore. `von Amarastus` (Vincent) = wampirza gałąź w Pałacu Amarastus (sekcja 21).
- **`Biblioteka Szczelin.md`** — katalog ksiąg in-world wg domen wiedzy, z nakładem
  (Popularny → Unikat) i systemem **Ekspertyzy** (uczenie się tylko z ksiąg,
  niechronologicznie). Spójny z kulturą akademicką (Bastion Mądrości, akademie magów).
  Zawiera **Patriarcha/Matriarchini Saqra** (patrz pass X). Autorzy z rasowymi imionami
  (Caelia Uro Serean itd.) — spójne z systemem nazewnictwa.

### Do ruszenia

- ✅ Flaga pass IX o „Barwy Czerwieni = żagle Kompanii" — **błędna, skorygowana** (to
  frakcje wampirze). Powiązanie Kompanii z czerwonymi żaglami pozostaje prawdziwe
  (Kroniki), ale to nie ten plik.
- ℹ️ **`Barwy Czerwieni` i część `Gilgamore`/`Biblioteka`** mają wiele placeholderów
  („X – lider/ka…", puste „Długość:") — to szkice do rozpisania, nie braki spójności.

---

# Przejście XII — Zelderińska Żmija (powieść)

## 27. Powieść jako kanon w akcji — ✅ wzorcowa spójność, domyka wątki

`Zelderińska Żmija` (1320–1323, epoka Eola II Szalonego) to nie osobny tekst, lecz
**kanon zdramatyzowany**. Napisana część (Tom 1 + początek Tomu 2, dalej notatki
fabularne) potwierdza i domyka niemal każdą wcześniejszą flagę:

- **Oś czasu (Władcy) — dokładna.** Eol II rządzi od 9 lat, ma 31 lat (1312–1346,
  ur. 1289 ✅); książę Ranal ma 4 lata (przyszły Ranal I, ur. 1316 ✅); „historia
  Saravery sięga 170 lat", Tagara I = 7. władczyni, dynastia wydała Erwina I
  Niezłomnego, Merinę III, Erwina II — 1:1 z arkuszem. Fallon I Zdobywca „~130 lat
  temu" (≈1191; kanon 1183–1189 ✅).
- **Rody i herby.** Książę **Melvin Sarrazin** z Zelderinu/Zavilesu; herb **ognisty
  wąż przebity czarnym ostrzem z rubinem** = dosłownie nota herbowa z arkusza Szlachta
  (Zelderin/Sarrazin). Dar „Kieł Falaka" (obsydian, płonie ogniem Otchłani). ✅
- **Kosmologia — verbatim.** Wymiar materialny bez strażników → Panteon z Corellii;
  domena Serbeny = Corellia∩Nereneth; Ghalagaar = Nereneth∩Otchłań → **bestie**
  (gobliny); **cor-osi'ale**. Laergana (szamanka) wykłada **Zewnętrznych Bogów z
  Pustki** (Zug-Caiona / Martwa Gwiazda o Białym Świetle), którzy „wykiełkowali"
  **Caithaloon** w Nereneth i **Czeluść** w Otchłani; nieumarli Zoraza = dusze
  uwięzione przez Białą Gwiazdę. Zgodne z Bogowie, odzyskanym doc i ustaleniami autora.
- **Klątwa Zelderinu domknięta.** Zoraz **Serrena** (wuj Collera, kapłan Vereny →
  sługa **Izgarthula**) sprowadza skrzynie **czarnej viruki** (owoc Caithaloonu),
  otwiera portal do Caithaloonu — dokładnie „owoce z Caithaloonu / epidemia CURSED"
  z odzyskanego dokumentu (sekcja 21). Abalan **Coller** = „Abalan", siostrzeniec
  doradcy (recovered doc). ✅
- **Pochodzenie ras — pokazane.** Uwięziony Maelius spotyka **dira'sear** (l. 1472):
  elfy-pokrewni (potomkowie **kael'sear**), którzy zostali w Nereneth, gdy „nastała
  ciemność" Caithaloonu, i zostali przez nią odmienieni. Notatki (l. 1564): „dergi,
  alfi rodowód". To **dira'sear = Dergowie** z ustaleń autora — origin rasy
  **zdramatyzowany w powieści** i spójny z kosmologią. Morfologia elfia `X'sear`
  (kael'sear, quana'sear, dira'sear, cor-osi'ale) + osobny „pradawny" (mowa pradawnych)
  vs „otchłanny" (mowa demonów).
- **Geografia/klimat = model temperatury.** Zaviles/Zelderin gorące i suche (susza),
  Orvanor/Xalivore zimne i mokre, zima X–IV (spójne z kotwicą wysokości Orvanoru 1700 m
  i modelem temperatury). Rzeki (Berenora, Zeldara→Zelderin, Alsteda, Inortus), promy
  przy Grani Królów / Krwawym Masywie, jezioro Gilgam — kanoniczna mapa w akcji.
- **Ernizjum.** Cesarzowa **Ilasaera III Ori Trianlateon**; imperium >1000 lat, czas
  liczony od powstania Ernizjum, doktryna „nie prowadzimy wojen, o ile nas nie
  zaatakują". Spójne z epoką (rok 0 = założenie Ernizjum) i motywem „wiecznego pokoju".

### Domknięcie flagi z pass IX (Wornimore — ród)

**Rozstrzygnięte przez powieść.** L. 1477: **ród Raevil(l)** to starożytny ród elfów
z Viroelann, który zjednoczył okoliczne rasy ~500 lat przed Tagarą; po sojuszu z
Fallonem I został jego namiestnikiem, a siedziba **Wornimore pozostała niezdobyta**.
Rodzina wyprowadziła się, oddając pałac **Lidze Grymuaru** (magowie, „Alabastrowa
Loża"). Zatem **Raevill = kanoniczny ród Wornimore** (arkusz Szlachta miał rację);
„Seldan (1154–1186)" z Feudalizmu to najpewniej wcześniejsza/ludzka linia namiestnicza,
nie sprzeczność. Finał Tomu 1 = **bariera wokół Wornimore** (nota autora l. 43 i 1570).

### Do ruszenia

- ℹ️ **Stan tekstu:** napisany Tom 1 + początek Tomu 2; dalej **notatki fabularne**
  (Plany na Saraverę / Caithaloon / zakończenie). Spójność pełna; brakuje domknięcia
  narracyjnego (śmierć Zoraza, powrót/kwarantanna drużyny, bariera Wornimore).
- ℹ️ Drobne robocze markery autora na początku pliku (uwagi „Ważne", „Więcej scen…") —
  notatki warsztatowe, nie treść kanonu.

---

## Podsumowanie audytu (przejścia I–XII) — ✅ świat wyjątkowo spójny

Po przejściu wszystkich domen: **lore jest wybitnie spójne**, a spoiwem są
**wymiary/kosmologia** — łączą bogów, rasy, bestiariusz, materiały, rośliny, patronów
Paktu, geopolitykę i fabułę powieści w jeden system. Twarde sprzeczności są nieliczne
i w większości **domknięte** w trakcie audytu (Irmus/obłęd 1743 = Izgarthul; Lertavore
= dryf ~pokolenia w kodzie; Wornimore/Raevill; drowy→Dergowie). Pozostałe punkty to
**kod↔lore drobne dryfy dat** (`PlacesConfiguration`) i **luki opisowe** (glosariusz
materiałów, placeholdery w plikach-szkicach), nie sprzeczności kanonu.
