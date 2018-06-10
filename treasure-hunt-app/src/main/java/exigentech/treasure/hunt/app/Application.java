package exigentech.treasure.hunt.app;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

  @Autowired
  public static BeanFactory factory;

  public static void main(String[] args){
    SpringApplication.run(Application.class, args)
        .getBeanFactory()
        .getBean(TreasureHunter.class)
        .describeJourney();
  }
}
