# Przepływ inicjalizacji świata: `PlaceInitializer` i `ProvinceInitializer`

> Stan na branch `claude/natural-province-borders`. Numery linii orientacyjne —
> trzymaj się nazw metod, bo linie się przesuwają.

## 1. Punkt wejścia i kolejność

- `PlaceInitializer.initializePlaces()` — tworzy **37 nazwanych miast startowych**
  (hardkodowane, przez `PlacesConfiguration.initialize<Miasto>()`), rejestruje je
  w `placeRegistry`, zbiera nazwy do `usedNames`.
- `ProvinceInitializer.initializeProvinces(seaShapes, rivers, lakes, mountains)`
  — wołane z `MainEngine.inject`. **Musi iść po** `initializePlaces`, bo sięga po
  miasta przez `initialCities` → `placeRegistry.get(cityId)`.

## 2. Hierarchia geometryczna

**Prowincja → SubProwincja → Region.** Każdy poziom to podział Voronoi rodzica,
przez ten sam pipeline `generateSubGeometries`: losowe punkty → Voronoi → Lloyd
×6 → naturalizacja krawędzi → `removeOverlaps` (dla `!smallDist`).

1. **13 prowincji** (`initializeProvinces`) — z konfiguracji; kształt czytany z
   pliku (`initializeProvince` → `GeoUtils.readProvince`). Zapis encji + rejestracja.
2. Dla każdej prowincji:
   - **Chmura ~`subProvinces*80` kandydatów na jeziora** (`generateSubGeometries(..., smallDist=true)`).
   - **Jeziora wewnętrzne** (`lakesRichness` szt.) — losowe kandydujące poligony
     bez miasta w środku, `ModelLake("brak_nazwy")`.
   - **SubProwincje** — `generateSubGeometries(area, subProvinces.size(), cityCoordinates, false)`,
     zapis `EntitySubProvince`, `initializeSubProvinces(...)` → `ModelSubProvince`
     z listą `ModelRegion`.
   - **Regiony** — `initializeRegions` → `generateSubGeometries(sub.area, regionsCount, ...)`,
     zapis `EntityRegion`, ustawienie `area` + `id`.

## 3. Co się wypełnia w regionach

Dla każdego regionu (pętla w `initializeProvinces`):
- **Miasto**: jeśli region zawiera lokalizację miasta → `SETTLERS_REACH`, miasto
  do `region.places`, profil stołeczny.
- **Cechy terenu**: góry (najwyższa nakładająca się), morze (`coast=true`),
  jeziora (`lake=true`), rzeki (`river=true`) → `mapFeatures` + flagi.
- **Profil** (tylko regiony bez miasta): `terrainShape`, `enchant`, `humidity`,
  `climate`, `woodRichness` — losowane z rozkładów prowincji.
- **Sąsiedztwo** (`determineNeighbourhood`): graf połączeń — `BORDER` (koszt 0),
  `BRIDGE`/`FERRYBOAT` przez rzeki, `RIVER`/`LAKE`/`SEA` z kosztami; obustronnie,
  z dystansami.

**Typ regionu ustalany w 3 przejściach:**
1. miasta → `SETTLERS_REACH`;
2. `settleBestRegions` — sort po `compareTo`, dla najlepszych `regionsToSettle`:
   `SUPERNATURAL_EXPANSE` / `FARMING_LAND` / z profilu osadników; woła
   `createPlacesForRegion` (**pusta!**);
3. `fillParameterBasedRegionTypes` — od końca: `ROCK_LAND`/`DUST_PLAIN` dla
   najgorszej gleby.

## 4. Wyjście
Metryczna otoczka + `MapPrinterUtils.print` + zapis `dem.png`.

---

## 5. Luki i „do zrobienia" (priorytetowo)

### 🔴 Krytyczne — osady w regionach nie powstają
- `createPlacesForRegion` jest **pustą metodą z TODO**. Cała maszyneria
  `PlaceInitializer.initializeUnnamedSettlement` + `VillageNameGenerator` (nazwy,
  kategorie, landmarki, kontekst środowiska) **jest niepodpięta**. Poza 37
  hardkodowanymi miastami żadne wsie/miasteczka nie powstają.
- Naturalny most: `createPlacesForRegion` → `PlaceInitializer.initializeUnnamedSettlement(coord, town?, year)`,
  docelowo `VillageNameGenerator.generate(env)` z `EnvFeature` wyliczonym z flag
  regionu (rzeka/las/góry) + odczyt `landmark`.

### 🟠 Typy regionów niekompletne
- `fillParameterBasedRegionTypes` obsługuje tylko `PERMAFROST` i glebę żyzności 0;
  reszta TODO. Dodatkowo `setType(ROCK_LAND)` a zaraz `setType(DUST_PLAIN)` —
  pierwszy natychmiast nadpisywany (prawdopodobnie błąd).
- `determineOtherRegions` (wypełniłby „środek" z profilu naturalnego) jest
  **zakomentowane** w wywołaniu. Pas regionów między osadzonymi a „ogonem"
  prawdopodobnie zostaje z typem domyślnym.

### 🟠 Miasta a przydział subprowincji/regionów
- `cityCoordinates` podawane jako punkty startowe Voronoi subprowincji, ale
  **Lloyd ×6 je relaksuje**, a przy `initialPoints.size() != partsCount`
  `generateSubGeometries` i tak losuje od nowa (bo `voronoiRaw` rzuci „za mało
  poligonów" → retry). Efekt: miasta lądują w subprowincjach/regionach losowo
  (por. TODO w kodzie).
- `regionsToSettle--` może zejść poniżej zera przy kilku miastach w jednej
  subprowincji.

### 🟡 Drobne bugi / dług
- `possibleLakes.get(rand.nextInt(possibleLakes.size() - 1))` — off-by-one
  (ostatni element nigdy; `nextInt(0)` rzuci przy rozmiarze 1).
- `removeLakes` (wycięcie jeziora z regionu) — **zakomentowane**; jeziora
  nakładają się na regiony zamiast być wycięte.
- `determineClimate`/`determineHumidity`: gałąź `if (!region.getPlaces().isEmpty())`
  jest **pusta** — regiony-miasta nie dostają liczonego klimatu/wilgotności.
- `calculateDistance` dla geometrii innej niż `LineString` tylko `println` i
  zwraca dystans centroidów (TODO).
- Pętle cech (góry/morze/jezioro/rzeka) używają surowego `.intersection()` bez
  `try/catch` — po włączeniu OverlayNG powinno być OK, ale bez siatki
  bezpieczeństwa jak w `determineNeighbourhood`.
- **Martwy kod**: `voronoiThisShit` i stary zakomentowany `generateSubGeometries`.
- **Persystencja**: `EntityRegion` zapisywany **zanim** ustalone są typy/profile;
  jeśli mapowanie nie bierze ich leniwie z `ModelRegion`, późniejsze `setType`/
  profile mogą nie trafić do bazy (do zweryfikowania w encji).

### 🟡 Do zweryfikowania poza tymi plikami
- Skąd `ModelRegion` bierze `soilType`, `fertility`, `enchantmentLevel` (używane
  w `settleBestRegions`/`fill...`) — to `initializeSubProvinces` (nie czytane).
  Potwierdzić, że są ustawione **przed** przejściami typów.

---

## Podsumowanie
Geometria (prowincje/subprowincje/regiony), cechy terenu, profile klimatyczne i
graf sąsiedztwa **już powstają i są zapisywane**. Brakuje przede wszystkim:
1. **tworzenia osad w regionach** (`createPlacesForRegion` — tu wepnie się system nazw/landmarków),
2. **domknięcia typowania regionów** (środkowy pas + pełna tabela w `fill...`),
3. **deterministycznego przypisania miast do subprowincji**.
