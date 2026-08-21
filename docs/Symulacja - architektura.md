# Symulacja — architektura (plan roboczy)

Notatka projektowa silnika symulacji. **Plan, nie kanon** — spisany, żeby listy procesów
(mortalność, płodność, migracje, budynki…) miały ramę, w którą się wpinają. Ustalenia
autora oznaczone ✅.

## 1. Rozdział: worldgen vs symulacja ✅

- **Worldgen = jednorazowy producent stanu początkowego** (teren, prowincje, startowe
  osady/populacje, pierwsi notable'owie). Docelowo za endpointem: `generuj → utrwal
  snapshot → oddaj symulacji`.
- **Symulacja = konsument, który ten stan ewoluuje.**
- **Rozmawiają przez wspólny model domenowy** (`SimulationProvince`, `Population`,
  `SimulationPerson`…), nie przez wołanie się nawzajem. Zmiana schematu osad/populacji
  dotyka obu stron, ale trzymają się kontraktu-danych → zostają rozdzielne.

## 2. Czas ✅

- **`java.util.Calendar`**, kalendarz ziemski. Świat ma odwrócone półkule — styczeń zimny
  na południu, gorący na północy (to kwestia klimatu regionu, nie kalendarza).
- **Jeden zegar** („teraz") w silniku; ujednolicić istniejący rozjazd `Calendar`
  (w `CalendarEvent`) vs `LocalDate` (w `GoalEngine`/`PersonFactory`) → wszędzie `Calendar`.

## 3. Model hybrydowy: przemiatanie stochastyczne + zdarzenia zaplanowane ✅

**Kluczowa korekta:** większość procesów **nie jest cykliczna w sensie „znam datę"** —
to **losowania szansy per okres** (stary model: `if (rand < deathChance(person))`, potem
„napalony? wierny? okazja?" → seks → szansa na ciążę…). Szansa zależy od zmiennego stanu
(wiek, głód, zdrowie), więc **nie wolno** z góry ustalać daty śmierci — trzeba **re-rollować
co okres**. Dlatego:

- **Przemiatania stochastyczne (sweeps)** — trzon. Co okres (tydzień/miesiąc/rok) przechodzą
  po encjach i losują zajście zdarzenia wg bieżącej szansy. Tu żyją: mortalność, kojarzenie/
  płodność, migracje/osadnictwo, audyt budynków itd.
- **Zdarzenia zaplanowane (kolejka)** — dla **zobowiązanych przyszłych skutków**, których
  data jest znana. Przykład wzorcowy: miesięczny sweep kojarzenia **wylosuje** zapłodnienie
  (stochastycznie) → **na sukces planuje** `KidEvent` ~9 mies. później (deterministycznie).
  Tu też: wygaszenie celu na datę `until` (masz w `GoalEngine`), zaplanowany ślub/wojna.

**Wielorytm rozwiązany bez `week % N`:** sweep tygodniowy/miesięczny/roczny to **zdarzenia
same-rescheduling** (po `act()` planują się +tydzień/+miesiąc/+rok) w **tej samej kolejce**,
co zdarzenia jednorazowe. Silnik nie robi najmniejszego kroku — **skacze od zdarzenia do
zdarzenia**. Rozmiar kroku przestaje istnieć.

## 4. Poziom szczegółu (LOD) ✅ kierunek

- **Nazwani (szlachta, istotni)** → szczegółowe zdarzenia życiowe per osoba (kolejka + sweep).
- **Chłopstwo / ekonomia / demografia** → **agregat per osada/prowincja**, ruszany sweepem
  (miesięcznym/rocznym), nie per-głowa. Populacja jako liczby i rozkłady.

Dzięki temu zmiana struktury osad/populacji dotyka warstwy agregatowej, nie kolejki szlachty.

## 5. Historia i odbijanie alternatyw ✅ (event sourcing)

Wymóg autora: **przeglądać stan na mapie w dowolnym momencie historii, w każdą stronę**, oraz
**cofnąć się do daty i wygenerować od niej alternatywną ścieżkę**. To determinuje szkielet:

- **Ziarno (seed) + snapshot początkowy + append-only log zdarzeń/decyzji** = pełna,
  odtwarzalna linia czasu.
- **Keyframe'y (snapshoty stanu) co interwał** (np. co rok) — żeby skok do przeszłości nie
  odgrywał od zera: `stan(data) = najbliższy wcześniejszy keyframe + replay do daty`.
- **Odbicie alternatywy = fork** od keyframe'u/daty: kopiujemy stan z tego punktu i jedziemy
  dalej (inne ziarno lub dalsze losowania) jako **nowa linia czasu**.
- **Warunki konieczne determinizmu:** (a) **RNG z ziarna, odtwarzalny** (najlepiej per-strumień,
  np. per prowincja/rok, żeby fork nie rozjeżdżał niepowiązanych losowań); (b) **stabilny
  porządek zdarzeń** o tej samej dacie — zrobione w `EventManager` (tie-break po numerze
  rejestracji).

To nie jest „pauza + wyłączenie aplikacji + wznowienie" — to **generujemy raz i swobodnie
przeglądamy/odbijamy**.

## 6. Sterowanie ✅

Minimalne: **uruchom / pauza / reset od daty** (reset = fork z pkt 5). Front czyta stan na
dowolną datę. Szkic endpointów (spina się z worldgenem na żądanie):
- `POST /sim/run` (do daty / N kroków), `POST /sim/pause`, `POST /sim/reset?from=DATA`.
- `GET /world/at?date=DATA` — stan do renderu mapy (keyframe + replay pod spodem).

## 7. Szablon dla „pełnych list" procesów

Dla każdego procesu opisz cztery rzeczy — to wystarcza, by go wpiąć:

| Proces | Rytm | Zakres | Typ | Skutki (zdarzenia) |
|---|---|---|---|---|
| Śmierć | tydzień/miesiąc? | per osoba (nazwani) / agregat (chłopi) | sweep (roll `deathChance`) | `DeathEvent`, zwolnienie budynku |
| Kojarzenie/seks | miesiąc | per osoba / para | sweep (roll: napalenie·wierność·okazja) | na sukces → plan `KidEvent` +9 mies. |
| Ciąża→poród | — | per osoba | **zaplanowane** | `KidEvent` na datę; ryzyko śmierci połogowej (roll w act) |
| Osadnictwo | miesiąc | agregat per osada | sweep (nadwyżka → gdzie osadzić) | zmiana populacji, ew. nowa osada |
| Audyt budynków | rok | per budynek mieszkalny | sweep (kto się urodził/umarł) | korekty stanu |
| … | … | … | … | … |

- **Rytm:** tydzień / miesiąc / rok / jednorazowe.
- **Zakres:** per-nazwany / per-agregat (osada/prowincja).
- **Typ:** sweep (losowanie szansy co okres) czy zaplanowane (znana data).
- **Skutki:** jakie zdarzenia/zmiany stanu produkuje (co ląduje w kolejce).

## 8. Stan kodu i usterki

- **Jest szkielet DES:** `CalendarEvent` (`act()`+`time`), zdarzenia `Death/Marriage/Kid/
  Insemination/FamilyEnd`, `EventManager`, `EventFactory`, `GoalEngine` (planuje na datę).
- **Naprawione w `EventManager` (ten branch):**
  - odwrócony warunek `getTime().after(date)` (palił zdarzenia z przyszłości) → wymagalność
    `time <= now`;
  - `ArrayList` + skan liniowy → **`PriorityQueue`** po (czas, sekwencja), z obsługą kaskad
    tego samego dnia (zdarzenie z `act()` może dorzucić kolejne);
  - stabilny tie-break pod replay (pkt 5).
- **Do zrobienia (po listach autora):** ujednolicić `Calendar`/`LocalDate`; baza
  `RecurringEvent` (self-reschedule) dla sweepów; cienki driver + endpointy sterowania;
  warstwa snapshot/keyframe + seedowany RNG; rozdział worldgen→snapshot→sim; wypełnić
  `registerEventsFor` (odtwarzanie zdarzeń z danych).
