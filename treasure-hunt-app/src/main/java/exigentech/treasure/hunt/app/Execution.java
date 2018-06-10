package exigentech.treasure.hunt.app;


import exigentech.treasure.hunt.core.HuntingStrategy;
import exigentech.treasure.hunt.core.Step;
import java.util.List;
import org.immutables.value.Value.Immutable;

@Immutable
public interface Execution {

  HuntingStrategy strategy();

  List<Step> steps();


  default void perform() {
    System.out.println(strategy());
  }

//  @Check
//  void validate() {
//    checkArgument(interactive() ^ filePath().isPresent(),
//        "Please do not provide a file path when specifying interactive mode!");
//  }
}
