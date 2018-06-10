package exigentech.treasure.hunt.app;

import exigentech.treasure.hunt.app.input.FileStepSource;
import exigentech.treasure.hunt.core.HuntingStrategy;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"exigentech.treasure.hunt"})
public class Application {

  @Autowired
  public static BeanFactory factory;

  public static void main(String[] args){
    final BeanFactory factory = SpringApplication.run(Application.class, args).getBeanFactory();

    final HuntingStrategy strategy = factory.getBean(HuntingStrategy.class, factory.getBean(FileStepSource.class));
    System.out.println(factory.getBean(HuntingStrategy.class, factory.getBean(FileStepSource.class).parseFile())
        .getSteps());
  }
}
