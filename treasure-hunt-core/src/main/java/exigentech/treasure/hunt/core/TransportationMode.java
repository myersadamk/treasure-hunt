package exigentech.treasure.hunt.core;

import java.util.concurrent.TimeUnit;

public enum TransportationMode {

  WALK(3), RUN(6), HORSE_TROT(4), HORSE_GALLOP(15), ELEPHANT_RIDE(6);

  private final int milesPerHour;

  static TransportationMode parse(String input) {
    return valueOf(input.toUpperCase().replace(' ', '_'));
  }

  int getMilesPerHour() {
    return milesPerHour;
  }

  TransportationMode(int milesPerHour) {
    this.milesPerHour = milesPerHour;
  }
}
