package exigentech.treasure.hunt.core;

import static com.google.common.base.Preconditions.checkArgument;

import exigentech.treasure.hunt.core.navigation.Direction;
import exigentech.treasure.hunt.core.navigation.TransportMode;
import java.time.Duration;
import org.immutables.value.Value;
import org.immutables.value.Value.Style.ImplementationVisibility;

@Value.Immutable(builder = false)
@Value.Style(allParameters = true, visibility = ImplementationVisibility.PACKAGE)
public interface Distance {

  Direction getDirection();

  double getDistance();

  static Distance calculate(Direction direction, Duration duration, TransportMode mode) {
    checkArgument(direction != null);
    checkArgument(!Duration.ZERO.equals(duration), "Duration must be positive");

    System.out.println(((double) mode.getMPH() / 60) * duration.toMinutes() + ", " + direction);
    return ImmutableDistance.of(direction, calculateDistance(duration, mode));
  }

  static Distance calculate(Direction direction, double distance) {
    checkArgument(direction != null);
    checkArgument(distance > 0);

    return ImmutableDistance.of(direction, distance);
  }

  // Style Note
  //
  // I used to be pretty adamant about using 'final' everywhere it could possibly be meaningful.
  // I've come to be more relaxed on this - it really only helps the developer writing the code (by
  // making it impossible to accidentally reassign the value). This is partially an adaptation to my
  // previous team, who didn't value the practice as much as my team at Cerner.
  //
  // I leave it off public/protected signatures, since it doesn't add any value to the consumer. In
  // fact, a 'final' qualifier on an interface/abstract method is superfluous; implementors have no
  // obligation to declare their variables as final when implementing the method.
  private static double calculateDistance(final Duration duration, final TransportMode mode) {
    checkArgument(duration != null);
    checkArgument(mode != null);

    return ((double) mode.getMPH() / 60) * duration.toMinutes();
  }
}
