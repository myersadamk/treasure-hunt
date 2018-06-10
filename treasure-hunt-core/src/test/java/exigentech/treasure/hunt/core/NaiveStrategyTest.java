package exigentech.treasure.hunt.core;

import static exigentech.treasure.hunt.core.navigation.CardinalDirection.NORTH;
import static exigentech.treasure.hunt.core.navigation.CardinalDirection.SOUTH;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import org.junit.jupiter.api.Test;

final class NaiveStrategyTest {

  @Test
  void noSteps() {
    assertThat(new NaiveStrategy(List.of()).getDistances(), is(List.of()));
  }

  @Test
  void redundantSteps() {
    final List<Distance> confusedDistances = List.of(
        Distance.calculate(NORTH, 10),
        Distance.calculate(NORTH, 2),
        Distance.calculate(SOUTH, 4),
        Distance.calculate(NORTH, 4)
    );

    assertThat(new NaiveStrategy(confusedDistances).getDistances(), is(confusedDistances));
  }

  @Test
  void nullList() {
    assertThrows(IllegalArgumentException.class, () -> new NaiveStrategy(null));
  }
}