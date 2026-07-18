# Docelowy pipeline generowania świata (kolejność, żeby miało ręce i nogi)

> Kierunek: **raster**. Rozdzielczość `HighMapUtils.metersPerPixel = 100`,
> projekcja `EPSG:32743`, gotowy `float[][]` z `HighMapUtils.getEmptyMap(env)`.
> Regiony (komórki Voronoi) **próbkują** rastry, nie zastępują ich.

## Architektura: pieczenie (bake) vs symulacja
Worldgen jest **oddzielony** od runtime'u: pieczemy stan wyjściowy raz, a
symulacja działa na gotowym stanie.
- **Etapy jako pipeline**: `WorldGenStage.run(WorldGenContext)` — etapy są
  **czyste** (czytają/piszą stan w pamięci), a **orchestrator** (`WorldGenerator`)
  odpowiada za zapis.
- **Persystencja za flagą**: `WorldGenConfig.persist` (domyślnie **false**) —
  dopóki wyłączona, cały pipeline liczy się w pamięci i nic nie ląduje na dysku
  (FIXME: warstwa snapshotów jeszcze nie istnieje).
- **Dwa tryby** (docelowo): *bake* (przelicz + zapisz snapshot) i *play* (boot
  ładuje snapshot). Dziś generacja siedzi w `MainEngine.inject` — do wyjęcia.
- **Fazy**: A) bake całości raz + load; B) persystencja artefaktów pośrednich →
  re-run pojedynczego etapu; C) cienki REST nad etapami. Interfejs `WorldGenStage`
  jest już pod to przygotowany.
- Pakiet: `priv.mfurmane.szlachtownica.worldgen` (bez zależności od JTS w rdzeniu;
  geometrię wstrzykuje adapter: szczyty + maska lądu).

## Zasada
Prawie wszystko wynika z **ukształtowania terenu i geologii**; część zależności
jest **wzajemna** (wysokość ↔ erozja ↔ gleba; klimat ↔ wilgotność ↔ hydrologia).
Nie symulujemy tego w pełni — robimy **jeden przebieg „w przód" + 1–2 przebiegi
korekcyjne**. Kolejność niżej jest tak dobrana, żeby każdy etap miał już swoje
wejścia gotowe (a korekty łatały wzajemności).

---

## Pipeline (kolejność docelowa)

### Etap 0 — Osadnictwo przy wodzie (ZROBIONE, prowizorycznie)
`createPlacesForRegion` tworzy wsie z przewagą regionów przy wodzie, na obecnych
(ręcznych) flagach `river/lake/coast`. Gdy przyjdzie hydrologia z terenu, te same
flagi po prostu zmienią źródło. Bez straty pracy.

### Etap 1 — Baza wysokości / tektonika  →  `float[][] elevation`
- fBm (szum wieloskalowy) + **wypiętrzenie** ku pasmom (`ModelMountains`, ich
  `height`) + **opadanie** ku morzu (`seaShapes`/wybrzeże) + bias `averageHeight`
  prowincji.
- Wynik: rastrowy DEM. To fundament — wszystko dalej z niego czerpie.

### Etap 2 — Geologia / skała macierzysta  →  `bedrock grid`
- Typ podłoża z tektoniki/wysokości/szumu: wulkaniczne i twarde przy pasmach,
  osadowe/wapienne w nizinach, aluwialne w dolinach rzek (po Etapie 3).
- **Wzajemność z wysokością**: twarda skała wolniej eroduje → wpływa z powrotem
  na kształt (uwzględnić w przebiegu erozji, nie osobno).
- Po co przed glebą: **gleba dziedziczy po skale macierzystej** — to naprawia
  „gleba losowana / tylko z klimatu".

### Etap 3 — Erozja + hydrologia  →  `rivers`, `lakes`, DEM (skorygowany), `flowAccumulation`
- **Wypełnianie zagłębień** (priority-flood / Planchon–Darboux): zalane misy =
  **jeziora** (endoreiczne baseny).
- **Kierunek spływu** (D8) + **akumulacja przepływu**.
- Komórki z akumulacją > próg = **rzeki** (dendrytyczne).
- Erozja modyfikuje wysokość; depozycja w dolinach → **mady/aluwia** (wejście do
  gleby). Zależne od twardości skały z Etapu 2.
- Wynik zastępuje ręczne `rivers`/`lakes` podawane dziś do `initializeProvinces`.

### Etap 4 — Klimat (bazowo)  →  temperatura
- Baza z szerokości geograficznej + wysokości (chłodniej wyżej).
- **Korekta**: cień opadowy za pasmami, łagodzenie od morza. (Część korekty
  wymaga hydrologii/wiatru → patrz „przebiegi korekcyjne".)

### Etap 5 — Wilgotność  →  wilgotność
- Z klimatu + **odległości od wody** (rzeki/jeziora/morze z Etapu 3) +
  akumulacji/topograficznego indeksu wilgotności (TWI) + cienia opadowego.

### Etap 6 — Gleba  →  `SoilType`
- Z **geologii** (skała macierzysta, Etap 2) + **terenu** (nachylenie/pozycja:
  dolina→aluwia, stok→kamieniste/gravelly, misa→torf) + **hydrologii** (drenaż,
  mady, zabagnienie) + **klimatu/wilgotności** (bielicowanie, zasolenie).
- Zastępuje dzisiejsze losowanie gleby / wyprowadzanie tylko z klimatu i wilgoci.

### Etap 7 — `TerrainShape` (kategoria) z DEM
- Wyprowadzony z wysokości/nachylenia/pozycji: `VALLEY`/`WETBASIN` nisko-mokro,
  `MOUNTAINS`/`HIGHLANDS`/`PLATEAUS` wysoko, `FLATLANDS`/`HILLS` pośrednio.
- Zastępuje losowy `determineTerrainShape`.

### Etap 8 — Fertility (już liczona) na realnych wejściach
- `ModelRegion.fertility = soil × humidity × climate × terrainShape × enchant`
  (kod już to robi) — teraz z niewymyślonych wartości.

### Etap 9 — Typowanie regionów (`RegionType`)
- Z wysokości/gleby/wilgotności/hydrologii/enchantu. Domknąć `fill...`/
  `determineOtherRegions` (dziś częściowo losowe / zakomentowane).

### Etap 10 — Osadnictwo (rozbudowa Etapu 0)
- Przy wodzie **i** na dobrej glebie; liczba/typ z regionu. Fale osadnictwa w
  czasie (przedkrólewskie/ernizyjskie/szlacheckie/oddolne/aldaharskie) → wiąże
  się z nazwami, robione osobno.

### Etap 11 — Geometria punktowa osad
- Umiejscowienie osady **względem konkretnej rzeki/brzegu** (bród, ujście,
  zakole), nie losowy punkt wnętrza jak w Etapie 0.

### Etap 12 — Zmiany w czasie (geologia/gleba)
- Erozja/depozycja, migracja koryt, wyjaławianie/wzbogacanie gleby zależne od
  użytkowania i skały macierzystej, zabagnianie mis. Iteracyjnie na tick/rok —
  wykorzystuje geologię z Etapu 2 jako „powolną" warstwę.

---

## Wzajemne zależności — jak przerwać cykle
- **wysokość ↔ erozja ↔ gleba**: policz bazę (E1) → geologia (E2) → przebieg
  erozji modyfikujący DEM i tworzący mady (E3) → dopiero gleba (E6). Ewentualnie
  2 przebiegi erozji dla stabilizacji.
- **klimat ↔ wilgotność ↔ hydrologia**: hydrologia (E3) daje wodę → wilgotność
  (E5); klimat bazowy z geografii (E4); po hydrologii **przebieg korekcyjny**
  klimatu (cień opadowy, wpływ mórz) i ponowna wilgotność.
- Reguła: **w przód raz + 1–2 korekty**, zamiast pełnej pętli sprzężeń.

## Mapowanie na obecny kod
- **Zastąpić**: `determineTerrainShape` (losowy → z DEM), losowa gleba (→ z
  geologii/terenu/hydro), ręczne `rivers`/`lakes` w `initializeProvinces`
  (→ z hydrologii Etapu 3).
- **Dołożyć**: moduł rastrowego DEM (wypełnić `getEmptyMap`), geologię, hydrologię
  (sink-fill + flow), próbkowanie rastrów per region.
- **Przepiąć kolejność** w `initializeProvinces`: dziś *teren losowy → woda z ręki
  → typy*; docelowo *DEM → geologia → hydrologia → gleba → klimat/wilgotność →
  terrainShape → typy → osady*.
- `dem.png` / `MapPrinterUtils` już są — użyć do podglądu DEM/hydrologii.

## Status
- ✅ Etap 0 (osadnictwo przy wodzie, prowizoryczne flagi).
- ✅ Szkielet worldgen (`WorldGenStage`/`Context`/`Config`/`WorldGenerator`) +
  **Etap 1 rdzeń** (`ElevationStage`: fBm + wypiętrzenie + batymetria + rampa
  brzegowa). Czysta Java, skompilowany i zweryfikowany offline (podgląd DEM).
- ✅ **Adapter geometrii** (`GeometryWorldInput`): buduje `WorldGenContext` z
  prowincji (maska lądu z `PreparedGeometry.contains` + prefiltr obwiedni),
  georeferencja z `HighMapUtils`, limit rozmiaru rastra. Wpięte w
  `initializeProvinces` → podgląd `elevation.png` (persist=false).
  Uwaga: rdzeń zweryfikowany offline; adapter+wpięcie tylko przegląd (JTS).
- ✅ **Maska morza z realnych akwenów** (`ctx.seaMask` z `List<ModelSeaPart>`):
  „poza prowincjami" NIE znaczy już „morze". Morze jest tylko tam, gdzie leży
  faktyczny akwen (dziś: na północy); lądy za południowymi górami, za rzeką na
  zachodzie itd. pozostają lądem. To usuwa też ogromne fałszywe jeziora — gdy
  ląd ma realny brzeg do spływu, priority-flood przestaje więzić wodę w środku
  (weryfikacja offline: wyspa z brzegiem → 0% jezior; zamknięty prostokąt bez
  ujścia → misy się zalewają, co jest fizycznie poprawne).
- ✅ **Hybryda: autorski highmap jako makro-relief** (`HighmapBias` + `ctx.heightBias`):
  autorski, ręcznie rysowany highmap (grayscale PNG) dostarcza GRUBEGO kształtu
  lądu/gór/nizin, a procedura (fBm + erozja) dokłada realistyczny detal i rzeźbi
  doliny — więc niedbałe fragmenty highmapa i tak wychodzą wiarygodnie. Highmap
  zakotwiczony w tym samym bboxie geograficznym co prowincje (`GeoUtils` LON/LAT
  → metryka), wielkie PNG (rzędu 60 Mpix) wczytywane z subsamplingiem czytnika.
  Wysokość lądu = `highmapWeight·(nizina→szczyt z highmapa) + (1−w)·fBm + detal`.
  Zwarstwy ląd/morze pozostają autorytatywne z geometrii (prowincje + `seaMask`)
  — highmap steruje TYLKO wysokością wewnątrz lądu. Parametry: `highmapPath`,
  `highmapWeight` (0.85), `highmapLowland` (220 m), `highmapHeightScale` (3000 m),
  `highmapDetailMeters` (70), `highmapMaxDim`. Brak ścieżki => czysto
  proceduralnie. Zweryfikowane offline na realnym highmapie (working3.png):
  wynik oddaje morze na N, zatokę na NE, masyw i grzbiet na S/SE, dendrytyczne
  rzeki i nieliczne jeziora zaporowe u stóp gór.
- ✅ **Grzbiety gór próbkowane wzdłuż linii** (`ModelMountains.getLine()`):
  pasmo to gęsta linia równych szczytów (`height` z modelu lub fallback
  `defaultMountainHeight`), a etap wysokości bierze **maksimum** garbów, nie
  sumę — grzbiet ma realny kształt zamiast jednego kopca w centroidzie i nie
  wybija w absurdalne wysokości. Render rzek okrągłym pędzlem z łagodnym,
  ograniczonym progiem grubości (koniec kanciastych, „eskalujących" rzek).
- ⬜ Wyjęcie worldgen z `MainEngine.inject` + przełącznik bake/play (dziś DEM
  liczy się przy inicjalizacji jako podgląd).
- ⬜ Kalibracja: skala `ModelMountains.getHeight()` vs `baseHeight`; rozdzielczość
  docelowa (dziś podgląd ograniczony `maxDim`).
- ✅ **Etap 2 (geologia)** — `GeologyStage` + `BedrockType` (granit/bazalt/łupek
  metam./wapień/piaskowiec/iłowiec). Spójne formacje z niskoczęstotliwościowego
  fBm, wybór typu kontrolowany wysokością i domenami wulkanicznymi; `hardness`
  pod erozję. Render `bedrock.png`. Wspólny szum wyciągnięty do `Noise`.
  Zweryfikowane offline.
- ✅ **Etap 3 (hydrologia)** — `HydrologyStage`: priority-flood (wypełnianie
  zagłębień → jeziora + kierunki na płaskich), kierunek spływu = najstromszy
  spadek po wypełnionym DEM (dendrytyczny), akumulacja przepływu, rzeki wg progu
  powierzchni zlewni (`riverDrainageKm2`, niezależny od rozdzielczości). Render
  `hydrology.png`. Zweryfikowane offline.
  UWAGA: jakość dendrytów zależy od terenu — gładka, promienista wyspa daje
  krótkie równoległe zlewnie (poprawne fizycznie). Efektowne doliny/dendryty
  wymagają **Etapu 3b: erozji hydraulicznej** (wcinanie dolin w DEM przed
  routingiem) — rekomendowane następne.
- ✅ **Etap 3b (erozja hydrauliczna)** — `ErosionStage`: symulacja kropli
  (Musgrave/Beyer/Lague) wcina doliny w DEM, tempo modulowane twardością skały
  macierzystej (granit stawia opór, iłowiec ustępuje). Uruchamiana po geologii,
  przed hydrologią. Efekt: rzeki meandrują i rozgałęziają się dendrytycznie
  zamiast równoległych zlewni. Zweryfikowane offline.
- ⬜ Etapy 4–12 (Etap 4: klimat z kotwicami prowincji — Twoja "chwila prawdy").
- ⬜ **Dociąganie granic administracyjnych do naturalnych elementów** — po
  hydrologii (Etap 3/3b) dociągnąć granice subprowincji i regionów do rzek,
  grzbietów górskich, brzegów jezior i innych naturalnych linii podziału,
  zamiast czysto geometrycznych krawędzi Voronoi. Zależności: potrzebuje rzek/
  grzbietów z rastra (Etap 3) oraz podziału Voronoi z `ProvinceInitializer`
  (`generateSubGeometries`). Pomysł na realizację: dla każdej krawędzi między
  komórkami, jeśli w pobliżu biegnie rzeka/grzbiet, przesunąć (snap) krawędź do
  tej linii; ewentualnie użyć rzek jako "twardych" krawędzi już na etapie
  podziału (np. cięcie obszaru rzekami przed Voronoi). Po dociągnięciu odświeżyć
  sąsiedztwo (`determineNeighbourhood`) — granice rzeczne to naturalne mosty/
  promy. Uwaga: to spina worldgen (raster) z geometrią JTS (Voronoi), więc
  zrobić po wyjęciu worldgen z boota i ustaleniu wspólnej georeferencji.
