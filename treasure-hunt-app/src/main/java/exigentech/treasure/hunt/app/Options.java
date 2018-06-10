package exigentech.treasure.hunt.app;

import static java.util.function.UnaryOperator.identity;

import java.util.Set;
import java.util.function.UnaryOperator;

enum Options {
  INTERACTIVE(false, null, "-i", "--i", "---interactive");

  String validate(final String argument) {
    if (requiresArgument) {
      validationFunction.apply(argument);
    }
    return argument;
  }
  private final boolean requiresArgument;
  private final UnaryOperator<String> validationFunction;
  private final Set<String> flags;

  Options(boolean requiresArgument, UnaryOperator<String> validationFunction, String... flags) {
    this.requiresArgument = requiresArgument;
    this.validationFunction = validationFunction;
    this.flags = Set.of(flags);
  }
}
