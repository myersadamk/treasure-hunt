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

final class StepTest {

  @ParameterizedTest
  @EnumSource(CardinalDirection.class)
  void allDirectionsAreSupported(final CardinalDirection direction) {
    assertThat(
        Step.of(direction, Duration.ofHours(1), RUN),
        is(Step.of(direction, RUN.getMPH()))
    );
  }

  @ParameterizedTest
  @EnumSource(TransportMode.class)
  void allTransportModes_SixHours(final TransportMode mode) {
    final int hours = 6;
    final Duration sixHourDuration = Duration.ofHours(hours);

    assertThat(
        Step.of(EAST, sixHourDuration, mode),
        is(Step.of(EAST, hours * mode.getMPH()))
    );
  }

  @Nested
  final class ParameterValidation {

    @Test
    void nullDirection() {
      assertThrows(IllegalArgumentException.class, () -> Step.of(null, Duration.ofMinutes(1), RUN));
    }

    @Test
    void nullTransportMode() {
      assertThrows(IllegalArgumentException.class,
          () -> Step.of(NORTH, Duration.ofMinutes(1), null));
    }

    @Test
    void zeroDuration() {
      assertThrows(IllegalArgumentException.class, () -> Step.of(NORTH, Duration.ZERO, RUN));
    }

    @Test
    void negativeDistance() {
      assertThrows(IllegalArgumentException.class, () -> Step.of(SOUTH, -1));
    }

    @Test
    void zeroDistance() {
      assertThrows(IllegalArgumentException.class, () -> Step.of(SOUTH, 0));
    }
  }
}