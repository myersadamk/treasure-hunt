package exigentech.treasure.hunt.app;

import exigentech.treasure.hunt.app.input.FileStepSource;
import exigentech.treasure.hunt.core.DirectLineStrategy;
import exigentech.treasure.hunt.core.HuntingStrategy;
import exigentech.treasure.hunt.core.NaiveStrategy;
import exigentech.treasure.hunt.core.Step;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

@Configuration
public class ApplicationConfiguration {

  @Bean
  @Lazy
  public TreasureHunter treasureHunter(HuntingStrategy strategy) {
    return new TreasureHunter(strategy);
  }

  @Bean
  @ConditionalOnProperty(name = "strategy", havingValue = "direct", matchIfMissing = true)
  public HuntingStrategy singleLineStrategy(List<Step> steps) {
    return new DirectLineStrategy(steps);
  }

  @Bean
  @ConditionalOnProperty(name = "strategy", havingValue = "naive")
  public HuntingStrategy naiveStrategy(List<Step> steps) {
    return new NaiveStrategy(steps);
  }

  @Bean
  public List<Step> getInputSteps(FileStepSource source) {
    return source.parseFile();
  }

  @Bean
  public FileStepSource fileStepSource(@Value("${path}") String path) {
    return new FileStepSource(path);
  }
}
