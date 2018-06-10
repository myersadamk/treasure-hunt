package exigentech.treasure.hunt.core;

import java.util.List;

/**
 * Defines a strategy for treasure hunting.
 */
public interface HuntingStrategy {

  /**
   * Gets the steps to follow to find a treasure according to this treasure-hunting strategy.
   */
  List<Distance> getDistances();
}
