package exigentech.treasure.hunt.core;


import static exigentech.treasure.hunt.core.navigation.Direction.E;
import static exigentech.treasure.hunt.core.navigation.Direction.N;
import static exigentech.treasure.hunt.core.navigation.Direction.NE;
import static exigentech.treasure.hunt.core.navigation.Direction.NW;
import static exigentech.treasure.hunt.core.navigation.Direction.S;
import static exigentech.treasure.hunt.core.navigation.Direction.SE;
import static exigentech.treasure.hunt.core.navigation.Direction.SW;
import static exigentech.treasure.hunt.core.navigation.Direction.W;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

final class DirectStrategyTest {

  @Test
  void noSteps() {
    assertThat(new DirectStrategy(List.of()).getDistances(), is(empty()));
  }

  @Test
  void nullList() {
    assertThrows(IllegalArgumentException.class, () -> new DirectStrategy(null));
  }

  @ParameterizedTest
  @StepSource(steps = {"N:2 S:2", "W:4 E:4", "N:10 E:11 S:10 W:11"})
  void neutralizedSteps(List<Distance> distances) {
    assertThat(applyDirectLineStrategy(distances), is(empty()));
  }

  @ParameterizedTest
  @StepSource(steps = {"N:10", "E:13", "S:21", "W:90"})
  void singleStep(List<Distance> distances) {
    assertThat(applyDirectLineStrategy(distances), is(distances));
  }

  @ParameterizedTest
  @StepSource(steps = {"N:12 N:14"})
  void multipleStepsInSameDirection(List<Distance> distances) {
    assertThat(applyDirectLineStrategy(distances), is(List.of(Distance.calculate(N, 26))));
  }

  @ParameterizedTest
  @StepSource(steps = {"S:22 N:24"})
  void opposingVerticalSteps_North(List<Distance> distances) {
    assertThat(applyDirectLineStrategy(distances), is(List.of(Distance.calculate(N, 2))));
  }

  @ParameterizedTest
  @StepSource(steps = {"S:12 N:2"})
  void opposingVerticalSteps_South(List<Distance> distances) {
    assertThat(applyDirectLineStrategy(distances), is(List.of(Distance.calculate(S, 10))));
  }

  @ParameterizedTest
  @StepSource(steps = {"E:22 W:24"})
  void opposingHorizontalSteps_West(List<Distance> distances) {
    assertThat(applyDirectLineStrategy(distances), is(List.of(Distance.calculate(W, 2))));
  }

  @ParameterizedTest
  @StepSource(steps = {"E:12 W:4"})
  void opposingHorizontalSteps_East(List<Distance> distances) {
    assertThat(applyDirectLineStrategy(distances), is(List.of(Distance.calculate(E, 8))));
  }

  @ParameterizedTest
  @StepSource(steps = {"E:12 W:4 N:4"})
  void opposingSteps_NorthEast(List<Distance> distances) {
    assertThat(
        applyDirectLineStrategy(distances),
        containsInAnyOrder(Distance.calculate(E, 8), Distance.calculate(N, 4))
    );
  }

  @ParameterizedTest
  @StepSource(steps = {"N:2 E:4 S:14 W:9"})
  void opposingSteps_SouthWest(List<Distance> distances) {
    assertThat(
        applyDirectLineStrategy(distances),
        containsInAnyOrder(Distance.calculate(S, 12), Distance.calculate(W, 5))
    );
  }

  @Test
  void singleStep_NorthEast() {
    assertThat(
        applyDirectLineStrategy(List.of(Distance.calculate(NE, 2))),
        is(List.of(Distance.calculate(N, 2), Distance.calculate(E, 2)))
    );
  }

  @Test
  void singleStep_NorthWest() {
    assertThat(
        applyDirectLineStrategy(List.of(Distance.calculate(NW, 2))),
        is(List.of(Distance.calculate(N, 2), Distance.calculate(W, 2)))
    );
  }

  @Test
  void singleStep_SouthEast() {
    assertThat(
        applyDirectLineStrategy(List.of(Distance.calculate(SE, 2))),
        is(List.of(Distance.calculate(S, 2), Distance.calculate(E, 2)))
    );
  }

  @Test
  void singleStep_SouthWest() {
    assertThat(
        applyDirectLineStrategy(List.of(Distance.calculate(SW, 2))),
        is(List.of(Distance.calculate(S, 2), Distance.calculate(W, 2)))
    );
  }

  @ParameterizedTest
  @StepSource(steps = {
      "NE:2 SW:2", "W:2 NE:4 W:2 S:4",
      "NW:2 SE:2", "E:2 NW:4 E:2 S:4",
      "SE:2 NW:2", "W:2 SE:4 W:2 N:4",
      "SW:2 NE:2", "E:2 SW:4 E:2 N:4"
  })
  void neutralizingSteps_Diagonals(List<Distance> steps) {
    assertThat(applyDirectLineStrategy(steps), is(List.of()));
  }

  private static List<Distance> applyDirectLineStrategy(List<Distance> distances) {
    return new DirectStrategy(distances).getDistances();
  }
}