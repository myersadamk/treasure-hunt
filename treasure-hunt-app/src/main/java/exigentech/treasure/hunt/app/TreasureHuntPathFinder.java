package exigentech.treasure.hunt.app;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.shell.Bootstrap;

public final class TreasureHuntPathFinder {

  public static void main(final String[] args) throws IOException {
    Bootstrap.main(args);
//    run(
//        ImmutableExecution.builder()
//            .interactive(arguments.contains("--i"))
//            .filePath(getFilePath(arguments))
//            .build()
//    );
  }

  private static void run(Execution execution) {
//    try (Scanner)
  }

  private static Optional<String> getFilePath(List<String> arguments) {
    final int argumentIndex = arguments.indexOf("--file") + 1;
    if (argumentIndex == 0) {
      return Optional.empty();
    }
    if (arguments.size() <= argumentIndex) {
      System.err.println("Usage");
      System.exit(0);
    }
    return Optional.of(arguments.get(argumentIndex));
  }
}
