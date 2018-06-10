package exigentech.treasure.hunt.app;

import exigentech.treasure.hunt.core.HuntingStrategy;
import java.text.DecimalFormat;
import org.springframework.beans.factory.annotation.Autowired;

public class TreasureHunter {
  private final HuntingStrategy strategy;

  static DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.##");

  public TreasureHunter(@Autowired HuntingStrategy strategy) {
    this.strategy = strategy;
  }

  public void describeJourney() {
    strategy.getDistances().forEach(d -> System.out.println(
        DECIMAL_FORMAT.format(d.getDistance()) +  " miles to the " + d.getDirection().describe())
    );
  }
}
