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
- ⬜ **Adapter geometrii** dla Etapu 1: zbudować `WorldGenContext` z prowincji —
  maska lądu z unii obszarów (`PreparedGeometry.contains`), szczyty z
  `ModelMountains` (pozycja + `height`), georeferencja z `HighMapUtils`.
- ⬜ Wyjęcie worldgen z `MainEngine.inject` + przełącznik bake/play.
- ⬜ Etapy 2–12 w powyższej kolejności (Etap 2 geologia następny).
