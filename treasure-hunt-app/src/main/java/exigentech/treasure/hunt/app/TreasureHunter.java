package exigentech.treasure.hunt.app;

import exigentech.treasure.hunt.core.Distance;
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
    final StringBuffer buffer = new StringBuffer();
    strategy.getDistances().forEach(d -> appendDescription(d, buffer));
    System.out.println(buffer.toString());
  }

  private static void appendDescription(final Distance distance, final StringBuffer buffer) {
    buffer.append(DECIMAL_FORMAT.format(distance.getDistance()))
        .append(" miles to the ")
        .append(distance.getDirection().describe());
  }
}
