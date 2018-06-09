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
    assertThat(new DirectLineStrategy(List.of()).getSteps(), is(empty()));
  }

  @Test
  void nullList() {
    assertThrows(IllegalArgumentException.class, () -> new DirectLineStrategy(null));
  }

  @ParameterizedTest
  @StepSource(steps = {"N2 S2", "W4 E4", "N10 E11 S10 W11"})
  void neutralizedSteps(List<Step> steps) {
    assertThat(applyDirectLineStrategy(steps), is(empty()));
  }

  @ParameterizedTest
  @StepSource(steps = {"N10", "E13", "S21", "W90"})
  void singleStep(List<Step> steps) {
    assertThat(applyDirectLineStrategy(steps), is(steps));
  }

  @ParameterizedTest
  @StepSource(steps = {"N12 N14"})
  void multipleStepsInSameDirection(List<Step> steps) {
    assertThat(applyDirectLineStrategy(steps), is(List.of(Step.of(NORTH, 26))));
  }

  @ParameterizedTest
  @StepSource(steps = {"S22 N24"})
  void opposingVerticalSteps_North(List<Step> steps) {
    assertThat(applyDirectLineStrategy(steps), is(List.of(Step.of(NORTH, 2))));
  }

  @ParameterizedTest
  @StepSource(steps = {"S12 N2"})
  void opposingVerticalSteps_South(List<Step> steps) {
    assertThat(applyDirectLineStrategy(steps), is(List.of(Step.of(SOUTH, 10))));
  }

  @ParameterizedTest
  @StepSource(steps = {"E22 W24"})
  void opposingHorizontalSteps_West(List<Step> steps) {
    assertThat(applyDirectLineStrategy(steps), is(List.of(Step.of(WEST, 2))));
  }

  @ParameterizedTest
  @StepSource(steps = {"E12 W4"})
  void opposingHorizontalSteps_East(List<Step> steps) {
    assertThat(applyDirectLineStrategy(steps), is(List.of(Step.of(EAST, 8))));
  }

  @ParameterizedTest
  @StepSource(steps = {"E12 W4 N4"})
  void opposingSteps_NorthEast(List<Step> steps) {
    assertThat(
        applyDirectLineStrategy(steps),
        containsInAnyOrder(Step.of(EAST, 8), Step.of(NORTH, 4))
    );
  }

  @ParameterizedTest
  @StepSource(steps = {"N2 E4 S14 W9"})
  void opposingSteps_SouthWest(List<Step> steps) {
    assertThat(
        applyDirectLineStrategy(steps),
        containsInAnyOrder(Step.of(SOUTH, 12), Step.of(WEST, 5))
    );
  }

  private static List<Step> applyDirectLineStrategy(List<Step> steps) {
    return new DirectLineStrategy(steps).getSteps();
  }
}