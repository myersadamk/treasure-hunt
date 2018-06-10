package exigentech.treasure.hunt.core;


import static com.google.common.base.Preconditions.checkArgument;

import com.google.common.collect.ImmutableList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * {@linkplain HuntingStrategy} that follows a List of {@linkplain Step}s literally, even if there
 * are redundancies.
 */
@Component
public final class NaiveStrategy implements HuntingStrategy {

  private final List<Step> steps;

  public NaiveStrategy(List<Step> steps) {
    checkArgument(steps != null);
    this.steps = ImmutableList.copyOf(steps);
  }

  /**
   * {@inheritDoc}
   * @return Non-null, possibly empty list containing a naive set of steps to follow to reach the
   *  treasure.
   */
  @Override
  public List<Step> getSteps() {
    return steps;
  }
}
