package exigentech.treasure.hunt.core;

import static exigentech.treasure.hunt.core.navigation.CardinalDirection.EAST;
import static exigentech.treasure.hunt.core.navigation.CardinalDirection.NORTH;
import static exigentech.treasure.hunt.core.navigation.CardinalDirection.SOUTH;
import static exigentech.treasure.hunt.core.navigation.TransportMode.RUN;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

import exigentech.treasure.hunt.core.navigation.CardinalDirection;
import exigentech.treasure.hunt.core.navigation.TransportMode;
import java.time.Duration;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

final class DistanceTest {

  @ParameterizedTest
  @EnumSource(CardinalDirection.class)
  void allDirectionsAreSupported(final CardinalDirection direction) {
    assertThat(
        Distance.calculate(direction, Duration.ofHours(1), RUN),
        is(Distance.calculate(direction, RUN.getMPH()))
    );
  }

  @ParameterizedTest
  @EnumSource(TransportMode.class)
  void allTransportModes_SixHours(final TransportMode mode) {
    final int hours = 6;
    final Duration sixHourDuration = Duration.ofHours(hours);

    assertThat(
        Distance.calculate(EAST, sixHourDuration, mode),
        is(Distance.calculate(EAST, hours * mode.getMPH()))
    );
  }

  @Test
  void secondsDuration() {
    final Duration twentyMinutes = Duration.ofMinutes(20);
    assertThat(
        Distance.calculate(EAST, twentyMinutes, TransportMode.WALK),
        is(Distance.calculate(EAST, 1))
    );
  }


  @Nested
  final class ParameterValidation {

    @Test
    void nullDirection() {
      assertThrows(IllegalArgumentException.class, () -> Distance
          .calculate(null, Duration.ofMinutes(1), RUN));
    }

    @Test
    void nullTransportMode() {
      assertThrows(IllegalArgumentException.class,
          () -> Distance.calculate(NORTH, Duration.ofMinutes(1), null));
    }

    @Test
    void zeroDuration() {
      assertThrows(IllegalArgumentException.class, () -> Distance.calculate(NORTH, Duration.ZERO, RUN));
    }

    @Test
    void negativeDistance() {
      assertThrows(IllegalArgumentException.class, () -> Distance.calculate(SOUTH, -1));
    }

    @Test
    void zeroDistance() {
      assertThrows(IllegalArgumentException.class, () -> Distance.calculate(SOUTH, 0));
    }
  }
}