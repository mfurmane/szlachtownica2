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
| Nowa Corellia (świątynna) | Lertavore | 80 | — |
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
- ℹ️ **Kalibracja worldgenu** wg arkuszy: wysokości prowincji (Orvanor 1700 …
  Nowa Corellia 80) i model temp (0 m=26,5°C, 39°S=22,8°C) — do wpięcia.

---

## Domeny na przejście VIII

- **Postaci / szlachta**: arkusze Szlachta, Władcy, Nazwiska (Royal Debug = tylko
  sim, nie kanon) ↔ `Towarzysze.md` (970 KB) ↔ bohaterowie Kronik, `Zelderińska Żmija.md`.
- **Organizacje**: `Organizacje.md`, `Organizacje i siedziby…` ↔ wzmianki
  (Zakon Gorejącego Słońca, Bastion Mądrości, Ametystowy Pakt) ↔ arkusz Pakt.
- **Geografia/Rejony** (arkusze Geografia, Rejony) ↔ prowincje/regiony w kodzie ↔
  Anniversary („Merinia i Nowa Corellia = jeden rejon", „Durnatel = dwa rejony").
- **Profesje/Umki** ↔ przebudowa umiejętności z Anniversary (worki zamiast domen).
