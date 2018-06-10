package exigentech.treasure.hunt.core;


import static com.google.common.base.Preconditions.checkArgument;
import static exigentech.treasure.hunt.core.navigation.CardinalDirection.EAST;
import static exigentech.treasure.hunt.core.navigation.CardinalDirection.NORTH;
import static java.util.stream.Collectors.groupingBy;

import exigentech.treasure.hunt.core.navigation.Direction;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

/**
 * {@linkplain HuntingStrategy} that simplifies a List of {@linkplain Step}s into one that can be
 * followed in a straight line.
 */
@Component
public final class DirectLineStrategy implements HuntingStrategy {

  private final Map<Direction, List<Step>> allStepsByDirection;
  private List<Step> steps = null;

  public DirectLineStrategy(List<Step> steps) {
    checkArgument(steps != null);
    allStepsByDirection = steps.stream().collect(groupingBy(Step::getDirection));
  }

  /**
   * {@inheritDoc}
   * <p />
   * Depending on the outcome of the net of all of the combined input steps, there are three
   * possible outputs from this method:
   * <ul>
   *   <li>An empty list if the steps effectively negate each other, e.g. "N 10, S 10."</li>
   *   <li>One step if the treasure only requires moving in one direction, e.g. "N 10, S 2."</li>
   *   <li>Two steps if the treasure requires moving in two directions, e.g. "N 10, E 10."</li>
   * </ul>
   *
   * @return Non-null, possibly empty list containing at most two steps to follow to reach the
   *  treasure.
   */
  @Override
  public List<Step> getSteps() {
    if (allStepsByDirection.isEmpty()) {
      return List.of();
    }

    if (steps != null) {
      return steps;
    }
    steps = new ArrayList<>(2);

    combineOpposingSteps(NORTH);
    combineOpposingSteps(EAST);
    return steps;
  }

  private void combineOpposingSteps(Direction direction) {
    final double distance = sumDistance(direction) - sumDistance(direction.opposite());
    if (distance == 0) {
      return;
    }

    // Style Note
    //
    // This is an instance where I introduce variables only to give more clarity to what is going
    // on; it's sufficient to inline these @ line 50. Given I know some cringe at ternaries, this
    // technique sometimes helps. If nothing else, this is an illustration of one of the things I've
    // picked up from reading/coding over the years. I am personally not averse to ternaries.
    //
    // https://martinfowler.com/books/refactoring.html
    // https://refactoring.com/catalog/extractVariable.html
    //
    final Direction absoluteDirection = distance > 0 ? direction : direction.opposite();
    final double absoluteDistance = Math.abs(distance);

    steps.add(Step.of(absoluteDirection, absoluteDistance));
  }

  private double sumDistance(final Direction direction) {
    return allStepsByDirection
        .getOrDefault(direction, List.of())
        .stream()
        .mapToDouble(Step::getDistance)
        .sum();
  }
}
