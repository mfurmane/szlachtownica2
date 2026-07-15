package priv.mfurmane.szlachtownica.engine.naming.model;

/**
 * Kategoria znaczeniowa rdzenia słowa. Służy do dobierania sensownych par
 * przymiotnik + rzeczownik przy tworzeniu nazw (np. żeby uniknąć "Kuni Jeleń"
 * czy "Zajęczy Lis" — zwierzęcy przymiotnik przy innym zwierzęciu).
 */
public enum WordCategory {
    ANIMAL,      // żaba, sarna, wilk, lis, bóbr...
    BEING,       // smok, elf, krasnolud, człowiek...
    PROFESSION,  // kupiec, rybak, kowal, młynarz...
    PLANT,       // brzoza, dąb, sosna, korzeń, zarośle...
    TERRAIN,     // las, góra, błoto, rzeka, wiatr, mrok... (naturalny krajobraz)
    STRUCTURE,   // gród, młyn, karczma, most, wieś... (twory ludzkie / osady)
    QUALITY,     // starość, dzicz, cisza, duma... (cechy abstrakcyjne)
    VULGAR       // dupa, kurwa...
}
