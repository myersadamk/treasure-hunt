package exigentech.treasure.hunt.core.navigation;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

final class TransportModeTest {

  @Nested
  final class FromString {

    @ParameterizedTest
    @CsvSource({
        "WALK, WALK, Walk, walk",
        "RUN, RUN, Run, run",
        "HORSE_TROT, HORSE_TROT, Horse Trot, horse trot",
        "HORSE_GALLOP, HORSE_GALLOP, Horse Gallop, horse gallop",
        "ELEPHANT_RIDE, ELEPHANT_RIDE, Elephant Ride, elephant ride",
    })
    void enumFromString(String enumName, String s1, String s2, String s3) {
      final TransportMode expected = TransportMode.valueOf(enumName);
      Stream.of(s1, s2, s3)
          .map(TransportMode::parse)
          .forEach(
              actual -> assertThat(actual, is(expected))
          );
    }

    @Nested
    final class Validation {

      @Test
      void nullValue() {
        assertThrows(IllegalArgumentException.class, () -> TransportMode.parse(null));
      }

      @ParameterizedTest
      @ValueSource(strings = {"  ", "", " Walk", "dinosaur ride"})
      void invalidValue(String input) {
        assertThrows(IllegalArgumentException.class, () -> TransportMode.parse(input));
      }
    }
  }
}