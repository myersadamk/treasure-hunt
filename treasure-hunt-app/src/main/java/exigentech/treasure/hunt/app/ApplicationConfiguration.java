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
  @ConditionalOnProperty(name = "strategy", havingValue = "directline", matchIfMissing = true)
  public HuntingStrategy singleLineStrategy() {
    return new DirectLineStrategy(null);
  }

  @Bean
  @ConditionalOnProperty(name = "strategy", havingValue = "naive")
  public HuntingStrategy naiveStrategy() {
    return new NaiveStrategy(null);
  }
}
