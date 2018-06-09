package exigentech.treasure.hunt.core;

import static com.google.common.base.Preconditions.checkArgument;

import exigentech.treasure.hunt.core.navigation.Direction;
import exigentech.treasure.hunt.core.navigation.TransportMode;
import java.time.Duration;
import org.immutables.value.Value;
import org.immutables.value.Value.Style.ImplementationVisibility;

@Value.Immutable(builder = false)
@Value.Style(allParameters = true, visibility = ImplementationVisibility.PRIVATE)
public interface Step {

  Direction getDirection();

  double getDistance();

  static Step of(Direction direction, Duration duration, TransportMode mode) {
    checkArgument(direction != null);
    checkArgument(!Duration.ZERO.equals(duration), "Duration must be positive");

    return ImmutableStep.of(direction, calculateDistance(duration, mode));
  }

  static Step of(Direction direction, double distance) {
    checkArgument(direction != null);
    checkArgument(distance > 0);

    return ImmutableStep.of(direction, distance);
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

    return mode.getMPH() * duration.toHours();
  }
}
