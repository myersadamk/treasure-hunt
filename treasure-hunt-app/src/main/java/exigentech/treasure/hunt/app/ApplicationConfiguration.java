package exigentech.treasure.hunt.app;

import exigentech.treasure.hunt.core.DirectLineStrategy;
import exigentech.treasure.hunt.core.HuntingStrategy;
import exigentech.treasure.hunt.core.NaiveStrategy;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

  @Bean
  public TreasureHunter treasureHunter(HuntingStrategy strategy) {
    return new TreasureHunter(strategy);
  }

  @Bean
//  @ConditionalOnExpression(value = "${strategy}=naive")
  @ConditionalOnProperty(name = "strategy", havingValue = "direct", matchIfMissing = true)
  public HuntingStrategy singleLineStrategy() {

//    System.out.println(strategy);
    System.out.println("direct!");
    return new DirectLineStrategy();
  }
//
  @Bean
  @ConditionalOnProperty(name = "strategy", havingValue = "naive")
  public HuntingStrategy naiveStrategy() {
    System.out.println("naive!");
    return new NaiveStrategy();
  }
}
