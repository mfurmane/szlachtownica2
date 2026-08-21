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

## 5. Historia i odbijanie alternatyw ✅ (zapis stanu, BEZ determinizmu)

Wymóg autora: **przeglądać stan na mapie w dowolnym momencie historii, w każdą stronę**, oraz
**cofnąć się do daty i wygenerować od niej alternatywną ścieżkę** (świeże losowania → inny
wynik).

**Determinizm RNG odrzucony (ustalenie autora) — bo kłóci się z celem.** Rozplątanie trzech
rzeczy, które łatwo pomylić:
1. **oglądanie już rozegranej przeszłości** — cofasz się do 1300 i widzisz, co *się stało*;
2. **świeże losowania na forku** — alt-linia musi wypaść inaczej;
3. **zdefiniowana kolejność zdarzeń z tej samej daty** — spójność, nie losowość kopca.

Determinizm RNG byłby potrzebny **tylko** dla realizacji pkt 1 metodą „replay z ziarna" — a ta
metoda jest **sprzeczna** z pkt 2 (to samo ziarno → to samo). Dlatego:

- **Realizujemy pkt 1 przez ZAPIS STANU, nie replay-z-ziarna.** Keyframe'y (snapshoty) co
  interwał (np. co rok) + zapis zmian między nimi. `stan(data)` = najbliższy keyframe (+ ew.
  odtworzenie **zapisanych** zmian, nie przeliczanie od nowa). Skok w przeszłość = wczytanie.
- **Fork = klon stanu z daty + dalsze losowania ze ŚWIEŻYM RNG** → nowa linia czasu. O to
  właśnie chodzi w „resecie od daty".
- **RNG:** jedno źródło, **ziarno świeże per uruchomienie/fork** (z entropii). Zero seedowania
  pod odtwarzalność.
- **„Szyny" niezależne od losowań:** zdarzenia, które *muszą* zajść (narodziny postaci
  historycznych, wojny) to **zaplanowane zdarzenia z datą** w kolejce — odpalają się mimo
  różnych losowań. Sweepy stochastyczne wypełniają emergentną resztę i to ona różni forki.
- **Tie-break w `EventManager`** (numer rejestracji) zostaje — ale to **pkt 3** (zdefiniowana
  kolejność same-date), **nie** determinizm losowań.

Koszt tej drogi to tylko miejsce na zapisany stan; przy LOD z pkt 4 (agregaty + zdarzenia
nazwanych) keyframe/rok + log zmian wystarcza. To nie „pauza + wyłączenie + wznowienie" —
**generujemy raz i swobodnie przeglądamy/odbijamy**.

**Otwarte (szyny vs emergencja):** co, gdy fork podkopie warunki wstępne szyny (postać ma się
urodzić w 1400, ale jej rodzice zginęli w alt-linii w 1380)? Odpalać szynę na siłę czy pozwolić
„nie zajść"? Do rozstrzygnięcia przy listach — nie blokuje szkieletu.

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
  - tie-break dla zdefiniowanej kolejności same-date (pkt 5.3), **nie** pod determinizm losowań.
- **Do zrobienia (po listach autora):** ujednolicić `Calendar`/`LocalDate`; baza
  `RecurringEvent` (self-reschedule) dla sweepów; cienki driver + endpointy sterowania;
  warstwa snapshot/keyframe (zapis stanu, świeży RNG na forku); rozdział worldgen→snapshot→sim;
  wypełnić `registerEventsFor` (odtwarzanie zdarzeń z danych).
