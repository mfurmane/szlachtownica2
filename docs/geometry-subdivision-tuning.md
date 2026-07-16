# Strojenie podziału geometrii na kawałki

> Dotyczy pipeline'u `generateSubGeometries` (podział prowincji → subprowincje →
> regiony) i naturalizacji granic. Branch `claude/natural-province-borders`.

## Pliki

- `model/initializers/ProvinceInitializer.java` — pipeline podziału:
  `generateSubGeometries`, `voronoiRaw`, `naturalize`, `generatePoints`,
  `removeOverlaps`, `determineSegmentsCount`.
- `model/VoronoiPerlinNaturalizer.java` — kształt granic (falowanie krawędzi,
  jitter wierzchołków).
- `App.java` — włączenie robustnego OverlayNG (patrz niżej).

## Cel projektowy
**Podobne pola wszystkich komórek** (przez relaksację Lloyda) **przy
nieregularnych kształtach i poszarpanych krawędziach** (przez naturalizację).

---

## Parametry (gdzie / ile / co robi)

### 1. Relaksacja Lloyda — równość PÓL
`ProvinceInitializer.generateSubGeometries`, zmienna `lloydIterations` (obecnie **6**).
- Centroidalne Voronoi wyrównuje pola komórek. Więcej iteracji = bardziej równe
  pola (i bardziej „heksagonalne" kształty, ale to maskuje naturalizacja).
- `0` = surowe, nierówne komórki; `6+` = wyrównane rozmiary.
- **Chcesz równiejszych pól** → podnieś (np. 8). **Chcesz większej różnorodności
  rozmiarów** → obniż (2–3).

### 2. Naturalizacja krawędzi — „poszarpanie"
`ProvinceInitializer.naturalize(...)`:
```java
double amplitude = area.getEnvelopeInternal().getWidth() * 0.03; // maks. wychylenie
new VoronoiPerlinNaturalizer(gf, determineSegmentsCount(area), amplitude, 7.0, 10);
//                                     segments=24                  amplitude  freq  seed
```
- **`amplitude` (× 0.03)** — bezwzględna siła falowania (ułamek szerokości mapy).
  Falowanie jest zerośrednie, więc **nie zmienia pola** komórki. Większe =
  bardziej poszarpane. Za duże → ryzyko nakładek (łapie je `removeOverlaps`).
  Rozsądny zakres `0.02`–`0.05`.
- **`frequency` (7.0)** — gęstość meandrów wzdłuż krawędzi. Wyżej = więcej,
  gęstszych ząbków.
- **`segments` (`determineSegmentsCount` → 24)** — liczba punktów na krawędź =
  gładkość krzywej. Za mało → kanciaste; więcej → gładsze, ale cięższe.

### 3. Charakter krawędzi i wierzchołków
`VoronoiPerlinNaturalizer` (stałe u góry klasy):
- **`OCTAVES` (5)** — oktawy szumu fBm. Więcej = bogatszy, drobniejszy detal
  fraktalny (bardziej „naturalne"/poszarpane). Mniej = łagodniejsze fale.
- **`VERTEX_JITTER_FACTOR` (0.35)** — przesunięcie potrójnych styków. **Uwaga:**
  większy jitter **rozjeżdża POLA** sąsiednich komórek (wspólny róg), więc trzymaj
  mały, jeśli zależy Ci na równych rozmiarach. `0` = wierzchołki nieruchome
  (czysto falowane krawędzie); `0.3–0.5` = lekki nieregularny szkielet.
- `perturbEdge`: `maxOffset = 0.4 * length` — twardy ogranicznik wychylenia na
  krótkich krawędziach (żeby się nie zapętlały / nie wchodziły w sąsiada). Zwykle
  nie ruszać; obniż, jeśli widać samoprzecięcia.

### 4. Gęstość / rozkład punktów
`ProvinceInitializer.generateSubGeometries`:
```java
double minDist = Math.sqrt(areaSize / partsCount) * 0.5; // min. odstęp punktów
double minDistCap = env.getHeight() * 0.03;              // górny limit odstępu
minDist = Math.min(minDist, minDistCap);
```
- Steruje minimalnym odstępem losowanych punktów (przed Lloydem). Większy odstęp
  = równiejszy start, ale trudniej upakować (więcej retry). Zwykle bez zmian —
  Lloyd i tak wyrównuje.
- `generatePoints`: `maxAttempts = 10000` (limit prób losowania), po
  przekroczeniu → wyjątek → retry. `retries > 5` w `generateSubGeometries` =
  ile razy próbować od nowa z nowymi punktami.

### 5. Usuwanie nakładek — rozłączny podział
`ProvinceInitializer.removeOverlaps` (wołane w `generateSubGeometries` gdy
`!smallDist`).
- Sekwencyjnie odejmuje od każdego poligonu sumę już rozstrzygniętych → wynik
  ściśle rozłączny. Sporny fragment zostaje przy poligonie **wcześniejszym**.
- Stosowane tylko dla właściwych podziałów (subprowincje/regiony), **nie** dla
  chmury kandydatów na jeziora (`smallDist=true` — dużo poligonów, nakładki tam
  nie szkodzą).

### 6. Robustny silnik overlay (OverlayNG)
`App.java` (blok statyczny + `main`) oraz blok statyczny `ProvinceInitializer`:
```java
System.setProperty("jts.overlay", "ng");
```
- **Konieczne.** Domyślny w JTS 1.18 stary `OverlayOp` rzuca
  `TopologyException: non-noded intersection` na geometriach z niemal
  pokrywającymi się krawędziami (efekt naturalizacji). OverlayNG noduje odpornie.
- Musi być ustawione zanim JTS zainicjalizuje `GeometryOverlay` (stąd blok
  statyczny). Alternatywnie flaga JVM: `-Djts.overlay=ng`.

### 7. `smallDist` (parametr `generateSubGeometries`)
- `true` — chmura kandydatów na jeziora (`subProvinces*80` poligonów): bez
  `removeOverlaps`.
- `false` — właściwe podziały (subprowincje, regiony): z `removeOverlaps`.

---

## Szybka ściąga: „chcę X → zmień Y"

| Chcę… | Zmień |
|---|---|
| bardziej równe pola komórek | `lloydIterations` ↑ (np. 8) |
| większą różnorodność rozmiarów | `lloydIterations` ↓ (2–3) |
| mocniej poszarpane krawędzie | `amplitude` (× 0.03 → 0.045) i/lub `frequency` ↑ |
| łagodniejsze krawędzie | `amplitude` ↓, `frequency` ↓, `OCTAVES` ↓ |
| gęstsze/drobniejsze ząbki | `frequency` ↑, `OCTAVES` ↑ |
| gładsze krzywe granic | `determineSegmentsCount` ↑ (np. 32) |
| mniej „heksagonalny" szkielet | `VERTEX_JITTER_FACTOR` ↑ (kosztem równości pól) |
| naprawić samoprzecięcia/nakładki | `amplitude` ↓, `VERTEX_JITTER_FACTOR` ↓, sprawdź `removeOverlaps` |
| crash `non-noded intersection` | upewnij się, że `jts.overlay=ng` jest ustawione dość wcześnie |

## Uwaga o weryfikacji
Podgląd stylu granic robiony był offline (czysta Java, bez JTS) — patrz historia
brancza. Finalny wygląd oceniaj na froncie po realnym uruchomieniu; wsp.
zmienności pól komórek to dobra liczbowa kontrola równości rozmiarów.
