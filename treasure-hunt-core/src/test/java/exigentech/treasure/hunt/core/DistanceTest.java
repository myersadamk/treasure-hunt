package exigentech.treasure.hunt.core;

import static exigentech.treasure.hunt.core.navigation.Direction.E;
import static exigentech.treasure.hunt.core.navigation.Direction.N;
import static exigentech.treasure.hunt.core.navigation.Direction.S;
import static exigentech.treasure.hunt.core.navigation.TransportMode.RUN;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

import exigentech.treasure.hunt.core.navigation.Direction;
import exigentech.treasure.hunt.core.navigation.TransportMode;
import java.time.Duration;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

final class DistanceTest {

  @ParameterizedTest
  @EnumSource(Direction.class)
  void allDirectionsAreSupported(final Direction direction) {
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
        Distance.calculate(E, sixHourDuration, mode),
        is(Distance.calculate(E, hours * mode.getMPH()))
    );
  }

  @Test
  void secondsDuration() {
    final Duration twentyMinutes = Duration.ofMinutes(20);
    assertThat(
        Distance.calculate(E, twentyMinutes, TransportMode.WALK),
        is(Distance.calculate(E, 1))
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
          () -> Distance.calculate(N, Duration.ofMinutes(1), null));
    }

    @Test
    void zeroDuration() {
      assertThrows(IllegalArgumentException.class, () -> Distance.calculate(N, Duration.ZERO, RUN));
    }

    @Test
    void negativeDistance() {
      assertThrows(IllegalArgumentException.class, () -> Distance.calculate(S, -1));
    }

    @Test
    void zeroDistance() {
      assertThrows(IllegalArgumentException.class, () -> Distance.calculate(S, 0));
    }
  }
}