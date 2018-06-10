package exigentech.treasure.hunt.core;


import static com.google.common.base.Preconditions.checkArgument;
import static exigentech.treasure.hunt.core.navigation.CardinalDirection.EAST;
import static exigentech.treasure.hunt.core.navigation.CardinalDirection.NORTH;
import static java.util.stream.Collectors.groupingBy;

import com.google.common.collect.ImmutableList;
import exigentech.treasure.hunt.core.navigation.Direction;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * {@linkplain HuntingStrategy} that follows a List of {@linkplain Step}s literally, even if there
 * are redundancies.
 */
public final class NaiveStrategy implements HuntingStrategy {

  /**
   * {@inheritDoc}
   * @return Non-null, possibly empty list containing a naive set of steps to follow to reach the
   *  treasure.
   */
  @Override
  public List<Step> getSteps(List<Step> steps) {
    checkArgument(steps != null);
    return steps;
  }
}
