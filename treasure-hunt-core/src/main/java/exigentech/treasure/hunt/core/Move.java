package exigentech.treasure.hunt.core;

import java.math.BigDecimal;
import java.time.Duration;

public final class Move {

  private final Direction direction;
  private final double distance;

  public static Move calculate(Direction direction, TransportationMode mode, Duration duration) {
    return new Move(direction, calculateDistance(mode, duration));
  }

  static Move of(Direction direction, double distance) {
    return new Move(direction, distance);
  }

  public Direction getDirection() {
    return direction;
  }

  public double getDistance() {
    return getDistance();
  }

  public double getRelativeDistance() {
    switch (direction) {
      case SOUTH:
      case WEST:
        return BigDecimal.valueOf(distance).negate().doubleValue();
      default:
        return distance;
    }
  }

  private static double calculateDistance(final TransportationMode mode, final Duration duration) {
    return mode.getMilesPerHour() * duration.toHours();
  }

  private Move(Direction direction, double distance) {
    this.direction = direction;
    this.distance = distance;
  }
}
