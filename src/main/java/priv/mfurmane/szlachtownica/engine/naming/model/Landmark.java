package priv.mfurmane.szlachtownica.engine.naming.model;

/**
 * Obiekt, który nazwa wioski "zapowiada" i który powinien faktycznie powstać
 * w wiosce (np. wioska nazwana "Turza Wieża" powinna mieć wieżę, "Młyn
 * Wilkołaczy" — młyn). Zwracany razem z nazwą; null oznacza brak obiektu.
 */
public enum Landmark {
    BRIDGE,     // most
    TOWER,      // wieża
    MILL,       // młyn
    CASTLE,     // zamek
    STRONGHOLD, // gród / grodzisko / warownia
    MANOR,      // dwór
    TAVERN,     // karczma
    HARBOR,     // przystań
    SMITHY,     // kuźnia
    GROVE,      // gaj
    WELL,       // studnia
    RAMPART,    // wał
    MARKET,     // rynek
    BARN,       // stodoła
    BELFRY,     // dzwonnica
    GRANGE      // folwark
}
