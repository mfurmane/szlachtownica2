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

## Domeny na przejście III

- **Postaci / szlachta**: arkusze Szlachta, Władcy, Royal Debug, Nazwiska ↔
  `Towarzysze.md` (970 KB) ↔ Kroniki (bohaterowie tomów), `Zelderińska Żmija.md`.
- **Organizacje**: `Organizacje.md`, `Organizacje i siedziby…` ↔ wzmianki
  (Zakon Gorejącego Słońca, Bastion Mądrości, Ametystowy Pakt) ↔ arkusz Pakt.
- **Bestiariusz**: `Bestiariusz.md` ↔ arkusze Bestiariusz / Bestiariusz poziomowy.
- **Pełen Kalendarz** (arkusz, 480 wierszy) ↔ Kroniki (kompletne zestawienie dat,
  m.in. Kalowie 672, Jirdenal 1455, tomy 1320–1408).
- **Geografia/Rejony** (arkusze Geografia, Rejony) ↔ prowincje/regiony w kodzie ↔
  Anniversary („Merinia i Nowa Corellia = jeden rejon", „Durnatel = dwa rejony").
- **Profesje/Umki** ↔ przebudowa umiejętności z Anniversary (worki zamiast domen).
