package exigentech.treasure.hunt.core.navigation;

/**
 * Interface for allowable directions. Using an interface rather than a concrete class allows us to
 * extend the functionality to include new directions (e.g. NW, SW, etc.) without modifying the
 * existing code.
 */
public interface Direction  {

  String describe();

  Direction opposite();
}
