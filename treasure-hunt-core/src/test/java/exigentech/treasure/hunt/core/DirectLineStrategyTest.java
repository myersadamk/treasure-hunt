package exigentech.treasure.hunt.core;


import static exigentech.treasure.hunt.core.navigation.CardinalDirection.EAST;
import static exigentech.treasure.hunt.core.navigation.CardinalDirection.NORTH;
import static exigentech.treasure.hunt.core.navigation.CardinalDirection.SOUTH;
import static exigentech.treasure.hunt.core.navigation.CardinalDirection.WEST;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

final class DirectLineStrategyTest {

  @Test
  void noSteps() {
    assertThat(new DirectLineStrategy(List.of()).getDistances(), is(empty()));
  }

  @Test
  void nullList() {
    assertThrows(IllegalArgumentException.class, () -> new DirectLineStrategy(null));
  }

  @ParameterizedTest
  @StepSource(steps = {"N2 S2", "W4 E4", "N10 E11 S10 W11"})
  void neutralizedSteps(List<Distance> distances) {
    assertThat(applyDirectLineStrategy(distances), is(empty()));
  }

  @ParameterizedTest
  @StepSource(steps = {"N10", "E13", "S21", "W90"})
  void singleStep(List<Distance> distances) {
    assertThat(applyDirectLineStrategy(distances), is(distances));
  }

  @ParameterizedTest
  @StepSource(steps = {"N12 N14"})
  void multipleStepsInSameDirection(List<Distance> distances) {
    assertThat(applyDirectLineStrategy(distances), is(List.of(Distance.calculate(NORTH, 26))));
  }

  @ParameterizedTest
  @StepSource(steps = {"S22 N24"})
  void opposingVerticalSteps_North(List<Distance> distances) {
    assertThat(applyDirectLineStrategy(distances), is(List.of(Distance.calculate(NORTH, 2))));
  }

  @ParameterizedTest
  @StepSource(steps = {"S12 N2"})
  void opposingVerticalSteps_South(List<Distance> distances) {
    assertThat(applyDirectLineStrategy(distances), is(List.of(Distance.calculate(SOUTH, 10))));
  }

  @ParameterizedTest
  @StepSource(steps = {"E22 W24"})
  void opposingHorizontalSteps_West(List<Distance> distances) {
    assertThat(applyDirectLineStrategy(distances), is(List.of(Distance.calculate(WEST, 2))));
  }

  @ParameterizedTest
  @StepSource(steps = {"E12 W4"})
  void opposingHorizontalSteps_East(List<Distance> distances) {
    assertThat(applyDirectLineStrategy(distances), is(List.of(Distance.calculate(EAST, 8))));
  }

  @ParameterizedTest
  @StepSource(steps = {"E12 W4 N4"})
  void opposingSteps_NorthEast(List<Distance> distances) {
    assertThat(
        applyDirectLineStrategy(distances),
        containsInAnyOrder(Distance.calculate(EAST, 8), Distance.calculate(NORTH, 4))
    );
  }

  @ParameterizedTest
  @StepSource(steps = {"N2 E4 S14 W9"})
  void opposingSteps_SouthWest(List<Distance> distances) {
    assertThat(
        applyDirectLineStrategy(distances),
        containsInAnyOrder(Distance.calculate(SOUTH, 12), Distance.calculate(WEST, 5))
    );
  }

  private static List<Distance> applyDirectLineStrategy(List<Distance> distances) {
    return new DirectLineStrategy(distances).getDistances();
  }
}