package exigentech.treasure.hunt.core.navigation;

import static com.google.common.base.Preconditions.checkArgument;

public enum TransportMode {

  WALK(3), RUN(6), HORSE_TROT(4), HORSE_GALLOP(15), ELEPHANT_RIDE(6);

  private final int milesPerHour;

  public static TransportMode parse(String input) {
    checkArgument(input != null);
    checkArgument(!input.trim().isEmpty());

    return valueOf(input.toUpperCase().replace(' ', '_'));
  }

  public int getMPH() {
    return milesPerHour;
  }

  TransportMode(int milesPerHour) {
    this.milesPerHour = milesPerHour;
  }
}
