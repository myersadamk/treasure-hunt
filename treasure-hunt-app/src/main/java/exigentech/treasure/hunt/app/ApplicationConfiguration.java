package exigentech.treasure.hunt.app;

import exigentech.treasure.hunt.app.input.FileStepSource;
import exigentech.treasure.hunt.core.DirectLineStrategy;
import exigentech.treasure.hunt.core.Distance;
import exigentech.treasure.hunt.core.HuntingStrategy;
import exigentech.treasure.hunt.core.NaiveStrategy;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class ApplicationConfiguration {

  @Bean
  @Lazy
  public TreasureHunter treasureHunter(HuntingStrategy strategy) {
    return new TreasureHunter(strategy);
  }

  @Bean
  @ConditionalOnProperty(name = "strategy", havingValue = "direct", matchIfMissing = true)
  public HuntingStrategy singleLineStrategy(List<Distance> distances) {
    return new DirectLineStrategy(distances);
  }

  @Bean
  @ConditionalOnProperty(name = "strategy", havingValue = "naive")
  public HuntingStrategy naiveStrategy(List<Distance> distances) {
    return new NaiveStrategy(distances);
  }

  @Bean
  public List<Distance> getInputSteps(FileStepSource source) {
    return source.parseFile();
  }

  @Bean
  public FileStepSource fileStepSource(@Value("${path}") String path) {
    return new FileStepSource(path);
  }
}
