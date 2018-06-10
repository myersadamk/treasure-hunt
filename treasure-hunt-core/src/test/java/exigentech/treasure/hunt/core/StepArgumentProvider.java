package exigentech.treasure.hunt.core;

import exigentech.treasure.hunt.core.navigation.CardinalDirection;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.support.AnnotationConsumer;

final class StepArgumentProvider implements ArgumentsProvider, AnnotationConsumer<StepSource> {

  public StepArgumentProvider() {
  }

  private String[] values;

  @Override
  public void accept(StepSource annotation) {
    values = annotation.steps();
  }

  @Override
  public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
    return Arrays.stream(values)
        .map(StepArgumentProvider::parseSteps)
        .map(Arguments::of);
  }

  private static List<Distance> parseSteps(final String input) {
    return Arrays.stream(input.split(" "))
        .map(StepArgumentProvider::parseStep).collect(Collectors.toList());
  }

  private static Distance parseStep(final String input) {
    return Distance.calculate(
        CardinalDirection.parse(input.substring(0, 1)), Double.valueOf(input.substring(1))
    );
  }
}
