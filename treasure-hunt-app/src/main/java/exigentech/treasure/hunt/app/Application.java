package exigentech.treasure.hunt.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages={"exigentech.treasure.hunt"})
public class Application {
  public static void main(String[] args) throws Exception {
    final ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
//    context.getEnvironment().getPropertySources().addFirst(new SimpleCommandLinePropertySource(args));
    context.close();
  }
}
