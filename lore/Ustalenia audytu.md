# Ustalenia audytu — rozstrzygnięcia i nowy kanon

Log decyzji autora zapadających w trakcie weryfikacji spójności. Uzupełnia
`Raport spójności.md` (findings) o *rozstrzygnięcia* (decyzje/kanon).

---

## Rozstrzygnięcia

### Fallon II Ostatni — koniec monarchii, początek Rady
- Fallon II ma poglądy **demokratyczne**. Z jego inicjatywy **zniesiona zostaje
  monarchia** — stąd przydomek „Ostatni".
- **Powód osobisty:** Fallon II jest gejem i bardzo nie chce być zmuszany do
  płodzenia potomstwa (dziedzica) — to leży u podstaw jego niechęci do
  dynastycznej monarchii.
- Władza NIE przechodzi w ręce losowych osób wybieranych przez społeczeństwo,
  lecz **Rady złożonej z przedstawicieli grup** (kościoły, akademie magiczne itp.).
- Jest po prostu królem **w czasach obłędu Irmusa** (≈1739–1743+).
- Wniosek dla osi czasu: ustrój po ~1743 to **Rada stanów/instytucji**, nie
  republika ludowa. Ładnie domyka „Fallon II *Ostatni* od 1739" z arkusza Władcy
  i spina się z erą gry Anniversary (chaos po obłędzie Irmusa).
- Status flag z raportu: zbieg „ostatni monarcha ≈ obłęd Irmusa" = ✅ zamierzony.

### Nazewnictwo: „drowy" → Dergowie (dira'sear)
- Nazwa **„drowy" jest deprecjonowana — to błąd.** „Drow" jest zbyt zrośnięte z
  Forgotten Realms (konkretne skojarzenia, m.in. wygląd). Właściwa nazwa
  pospolita to **Dergowie** (dergi) — daje pole na liczne mutacje (np. mace itp.).
- Nazwa formalna (elfia) pozostaje **dira'sear**. Mapowanie:
  dira'sear = Dergowie (dawniej błędnie „drowy").
- **Sprzątanie** — 3 pozostałe wystąpienia „drowy" do zamiany na Dergowie
  (wszystkie już z dopiskiem „dira'sear" i znakiem zapytania):
  - `Bogowie itp.md:717`
  - `Anniversary Concept.md:622`
  - `Anniversary Concept.md:632`
  Zero w kodzie. (Fałszywe trafienia grepa „Wędrowiec/wędrowny" — od Taiki — pominięte.)

---

## Do rozstrzygnięcia przez autora (otwarte)

### Wornimore — który ród panuje? (pass IX)
- Feudalizm: linia **Seldan** (Ludzie, 1154–1186) jako panująca w Wornimore.
- Szlachta: „ważny" ród **Raevill** (Elfy, 687).
- Hipoteza audytu: starożytny ród elfów (Raevill) + późniejsze ludzkie nadanie
  książęce (Seldan) współistnieją. **Do potwierdzenia:** współistnienie czy rozjazd?

### Lertavore — rok założenia w kodzie (pass IX, domknięcie flagi pass III)
- Kanon (Szlachta/Feudalizm + Kroniki): ród Holzer / miasto od **~1183–1189**
  (podbój Fallona I Zdobywcy 1183–1189).
- Kod `PlacesConfiguration`: Lertavore zbudowane **1195**.
- Rozjazd zawężony do **~jednego pokolenia** (nie 30 lat, jak sugerowała pierwotna
  flaga „≤1165"). Drobne wyrównanie kodu 1195 → ~1185, jeśli autor zechce.
