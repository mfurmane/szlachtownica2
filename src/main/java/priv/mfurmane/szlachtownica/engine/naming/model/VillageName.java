package priv.mfurmane.szlachtownica.engine.naming.model;

/**
 * Wynik generatora nazw: gotowa nazwa oraz opcjonalny obiekt, który nazwa
 * zapowiada (landmark == null, gdy nazwa nie implikuje żadnej budowli).
 */
public record VillageName(String name, Landmark landmark) {
}
