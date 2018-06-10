package exigentech.treasure.hunt.app;

import exigentech.treasure.hunt.core.HuntingStrategy;
import org.springframework.beans.factory.annotation.Autowired;

//@SpringBootApplication
//@ComponentScan(value = "exigentech.treasure.hunt.app")
public class TreasureHunter {
  private final HuntingStrategy strategy;

  public TreasureHunter(@Autowired HuntingStrategy strategy) {
    this.strategy = strategy;
  }
}
