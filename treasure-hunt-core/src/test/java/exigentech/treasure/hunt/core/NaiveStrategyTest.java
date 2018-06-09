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
    assertThat(new NaiveStrategy(List.of()).getSteps(), is(List.of()));
  }

  @Test
  void redundantSteps() {
    final List<Step> confusedSteps = List.of(
        Step.of(NORTH, 10),
        Step.of(NORTH, 2),
        Step.of(SOUTH, 4),
        Step.of(NORTH, 4)
    );

    assertThat(new NaiveStrategy(confusedSteps).getSteps(), is(confusedSteps));
  }

  @Test
  void nullList() {
    assertThrows(IllegalArgumentException.class, () -> new NaiveStrategy(null));
  }
}