package exigentech.treasure.hunt.core;


import static com.google.common.base.Preconditions.checkArgument;

import com.google.common.collect.ImmutableList;
import java.util.List;

/**
 * {@linkplain HuntingStrategy} that follows a List calculate {@linkplain Distance}s literally, even if there
 * are redundancies.
 */
public final class NaiveStrategy implements HuntingStrategy {

  private final List<Distance> distances;

  public NaiveStrategy(List<Distance> distances) {
    checkArgument(distances != null);
    this.distances = ImmutableList.copyOf(distances);
  }

  /**
   * {@inheritDoc}
   * @return Non-null, possibly empty list containing a naive set calculate distances to follow to reach the
   *  treasure.
   */
  @Override
  public List<Distance> getDistances() {
    return distances;
  }
}
