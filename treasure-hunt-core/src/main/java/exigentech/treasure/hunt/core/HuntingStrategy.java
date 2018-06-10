package exigentech.treasure.hunt.core;

import java.util.List;
import org.springframework.stereotype.Service;

/**
 * Defines a strategy for treasure hunting.
 */
@Service
public interface HuntingStrategy {

  /**
   * Gets the steps to follow to find a treasure according to this treasure-hunting strategy.
   */
  List<Step> getSteps();
}
