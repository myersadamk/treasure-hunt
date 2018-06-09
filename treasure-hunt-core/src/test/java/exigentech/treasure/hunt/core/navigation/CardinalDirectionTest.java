package exigentech.treasure.hunt.core.navigation;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.NoSuchElementException;
import java.util.stream.Stream;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

final class CardinalDirectionTest {

  @Nested
  final class FromString {

    @ParameterizedTest
    @CsvSource({
        "NORTH, n, N, North, north",
        "SOUTH, s, S, South, south",
        "EAST, e, E, East, east",
        "WEST, w, W, West, west"
    })
    void enumFromString(String enumName, String s1, String s2, String s3, String s4) {
      final CardinalDirection expected = CardinalDirection.valueOf(enumName);
      Stream.of(s1, s2, s3, s4)
          .map(CardinalDirection::parse)
          .forEach(
              actual -> assertThat(actual, is(expected))
          );
    }

    @Nested
    final class Validation {

      @Test
      void nullValue() {
        assertThrows(IllegalArgumentException.class, () -> CardinalDirection.parse(null));
      }

      @ParameterizedTest
      @ValueSource(strings = {"  ", ""})
      void blankOrEmptyValue(String input) {
        assertThrows(IllegalArgumentException.class, () -> CardinalDirection.parse(input));
      }

      @ParameterizedTest
      @ValueSource(strings = {" north", "over there"})
      void invalidValue(String input) {
        assertThrows(NoSuchElementException.class, () -> CardinalDirection.parse(input));
      }
    }
  }
}