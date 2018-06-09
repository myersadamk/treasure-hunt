package exigentech.treasure.hunt.core;

import java.math.BigDecimal;

enum Direction {

  NORTH, SOUTH, EAST, WEST;

  static Direction parse(String input) {
    return valueOf(input.toUpperCase());
  }

  double applyCoordinateSignage(double distance) {
    switch (this) {
      case SOUTH:
      case WEST:
        return BigDecimal.valueOf(distance).negate().doubleValue();
    }
    throw new IllegalStateException("Unrecognized direction: " + this.name());
  }

  Direction opposite() {
    switch (this) {
      case NORTH:
        return SOUTH;
      case SOUTH:
        return NORTH;
      case EAST:
        return WEST;
      case WEST:
        return EAST;
    }
    throw new IllegalStateException("Unrecognized direction: " + this.name());
  }
}
