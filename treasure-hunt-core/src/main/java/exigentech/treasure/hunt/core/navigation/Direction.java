package exigentech.treasure.hunt.core.navigation;

/**
 * Interface for allowable directions. Using an interface rather than a concrete class allows us to
 * extend the functionality to include new directions (e.g. Up, Down, etc.) without modifying the
 * existing code. Admittedly superfluous for this application.
 */
public interface Direction  {

  String describe();

  Direction opposite();
}
