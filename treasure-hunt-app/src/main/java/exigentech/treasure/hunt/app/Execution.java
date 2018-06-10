package exigentech.treasure.hunt.app;



import exigentech.treasure.hunt.core.HuntingStrategy;
import java.util.Optional;
import org.immutables.value.Value.Default;
import org.immutables.value.Value.Immutable;

@Immutable
//@Style(visibility = PACKAGE)
public interface Execution {

  @Default
  default boolean interactive() {
    return false;
  }

  default Optional<String> filePath() {
    return Optional.empty();
  }

  HuntingStrategy strategy();

//  @Check
//  void validate() {
//    checkArgument(interactive() ^ filePath().isPresent(),
//        "Please do not provide a file path when specifying interactive mode!");
//  }
}
