package exigentech.treasure.hunt.app.input;

import com.google.common.collect.ImmutableList;
import exigentech.treasure.hunt.core.Step;
import exigentech.treasure.hunt.core.navigation.CardinalDirection;
import exigentech.treasure.hunt.core.navigation.TransportMode;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class FileStepSource implements StepSource {

  private final Path path;

  public FileStepSource(String path) {
    this.path = Paths.get(path);
  }

  @Override
  public List<Step> parseFile() {
    final ImmutableList.Builder builder = ImmutableList.builder();
    try (final BufferedReader reader = Files.newBufferedReader(path)) {
      String line;
      while ((line = reader.readLine()) != null) {
        final String[] args = line.split(", ");
        if (args.length != 3) {
          System.err.println("Unparseable instruction: " + line);
          continue;
        }
        final Duration totalDuration = parseDuration(args[1]);
        if (Duration.ZERO.equals(totalDuration)) {
          continue;
        }
        builder.add(Step.of(
            CardinalDirection.parse(args[2]), totalDuration, TransportMode.parse(args[0]))
        );
      }
      return builder.build();
    } catch (final FileNotFoundException exception) {
      throw new InputException(
          "Could not read steps (file not found: " + path.toString() + ").", exception
      );
    } catch (final IOException exception) {
      throw new InputException(
          "Could not read steps (exception thrown reading " + path.toString() + ").", exception
      );
    }
  }

  private static Duration parseDuration(final String input) {

    final List<Duration> durations = Stream.of(input.split(", "))
        .map(FileStepSource::parseDurationArticle)
        .collect(Collectors.toList());
//            Collector.of(
//                () -> Duration.ZERO,
//                // For sequential accumulations
//                (result, article) -> result = result.plus(article),
//                // For parallel accumulations (unused)
//                (result1, result2) -> result1 = result1.plus(result2)
//            );
    Duration totalDuration = Duration.ZERO;
    for (final Duration duration : durations) {
      totalDuration = totalDuration.plus(duration);
    }
    return totalDuration;
//    return Stream.of(input.split(", "))
//        .map(FileStepSource::parseDurationArticle)
//        .collect(
//            Collector.of(
//                () -> Duration.ZERO,
//                // For sequential accumulations
//                (result, article) -> result = result.plus(article),
//                // For parallel accumulations (unused)
//                (result1, result2) -> result1 = result1.plus(result2)
//            )
//        );
  }

  private static Duration parseDurationArticle(final String input) {
    final TemporalUnit unit;
    if (input.contains("sec")) {
      unit = ChronoUnit.SECONDS;
    } else if (input.contains("min")) {
      unit = ChronoUnit.MINUTES;
    } else if (input.contains("hour")) {
      unit = ChronoUnit.HOURS;
    } else {
      throw new InputException("Could not determine duration from " + input);
    }
    return Duration.of(Integer.valueOf(input.substring(0, input.indexOf(" "))), unit);
  }
}
