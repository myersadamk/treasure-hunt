package exigentech.treasure.hunt.app;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

public final class Application {
  public static void main(String[] args) throws Exception {
    ConfigurableApplicationContext context = SpringApplication.run(TreasureHunter.class, args);
    context.close();
  }
}
