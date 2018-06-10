package exigentech.treasure.hunt.core.navigation;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.EnumSet;
import java.util.NoSuchElementException;

public enum CardinalDirection implements Direction {

  NORTH, SOUTH, EAST, WEST;

  public static CardinalDirection parse(String input) {
    checkArgument(input != null);
    checkArgument(!input.trim().isEmpty());

    return EnumSet.allOf(CardinalDirection.class).stream()
        .filter(e -> e.name().startsWith(input.substring(0, 1).toUpperCase()))
        .findFirst()
        .orElseThrow(NoSuchElementException::new);
  }

  @Override
  public String describe() {
    return name().toLowerCase();
  }

  @Override
  public CardinalDirection opposite() {
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
