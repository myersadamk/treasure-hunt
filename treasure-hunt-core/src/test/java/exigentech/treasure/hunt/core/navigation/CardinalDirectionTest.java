//package exigentech.treasure.hunt.core.navigation;
//
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.is;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//import java.util.NoSuchElementException;
//import java.util.stream.Stream;
//import org.junit.jupiter.api.Nested;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.CsvSource;
//import org.junit.jupiter.params.provider.ValueSource;
//
//final class CardinalDirectionTest {
//
//  @Nested
//  final class FromString {
//
//    @ParameterizedTest
//    @CsvSource({
//        "N, n",
//        "S, s",
//        "E, e",
//        "W, w"
//    })
//    void enumFromString(String enumName, String from) {
//      final CardinalDirection expected = CardinalDirection.valueOf(enumName);
//      assertThat(CardinalDirection.valueOf(expected), is(CardinalDirection.parse()))
//          .map(CardinalDirection::parse)
//          .forEach(
//              actual -> assertThat(actual, is(expected))
//          );
//    }
//
//    @Nested
//    final class Validation {
//
//      @Test
//      void nullValue() {
//        assertThrows(IllegalArgumentException.class, () -> CardinalDirection.parse(null));
//      }
//
//      @ParameterizedTest
//      @ValueSource(strings = {"  ", ""})
//      void blankOrEmptyValue(String input) {
//        assertThrows(IllegalArgumentException.class, () -> CardinalDirection.parse(input));
//      }
//
//      @ParameterizedTest
//      @ValueSource(strings = {" north", "over there"})
//      void invalidValue(String input) {
//        assertThrows(NoSuchElementException.class, () -> CardinalDirection.parse(input));
//      }
//    }
//  }
//}