package optional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OptionalTest {
  @Test
  void should_throws_if_get_value_of_empty_optional() {

    Optional<String> empty = Optional.empty();
    Class errorType = NoSuchElementException.class;

    assertThrows(errorType, empty::get);
  }

  @Test
  void should_provide_a_default_value_using_or_else() {
    Optional<String> empty = Optional.empty();
    Optional<String> nonEmpty = Optional.of("great");

    String emptyResult = getValue(empty, "default value");
    String nonEmptyResult = getValue(nonEmpty, "default value");

    assertEquals("default value", emptyResult);
    assertEquals("great", nonEmptyResult);
  }

  @Test
  void should_be_able_to_throw_for_empty_optional() {
    Optional<String> empty = Optional.empty();

    Runnable emptyRunnable = () -> {
      if (!empty.isPresent()) {
        throw new IllegalStateException();
      }
    };
    // --end-->

    assertThrows(IllegalStateException.class, emptyRunnable::run);
  }

  @Test
  void should_process_value_if_present() {
    Optional<String> optional = Optional.of("word");
    List<String> result = new ArrayList<>();

    Consumer<Optional<String>> optionalConsumer = item -> item.ifPresent(s -> result.add(s.toUpperCase()));

    optionalConsumer.accept(optional);

    assertEquals("WORD", result.get(0));
  }

  @Test
  void should_map_value_if_present() {
    Optional<String> optional = Optional.of("word");
    Optional<String> empty = Optional.empty();

    List<String> result = new ArrayList<>();


    Function<Optional<String>, Optional<Boolean>> mapping = stringOptional -> stringOptional.map(item -> result.add(item.toUpperCase()));
    // --end-->

    Optional<Boolean> mappingResult = mapping.apply(optional);
    Optional<Boolean> emptyMappingResult = mapping.apply(empty);

    assertTrue(mappingResult.orElseThrow(IllegalStateException::new));
    assertEquals("WORD", result.get(0));
    assertFalse(emptyMappingResult.isPresent());
  }

  @Test
  void should_flat_map_optional_value_do_chain_method() {
    Stream<YieldOptional> emptyStream = Stream.of(1, 2, 3)
        .filter(i -> i > 4)
        .map(i -> new YieldOptional());
    Stream<YieldOptional> nonEmptyStream = Stream.of(1, 2, 3)
        .filter(i -> i > 1)
        .map(i -> new YieldOptional());

    Function<Stream<YieldOptional>, Optional<String>> findFirstAndGet = yieldOptionalStream -> yieldOptionalStream.findFirst().flatMap(YieldOptional::get);

    Optional<String> emptyStreamResult = findFirstAndGet.apply(emptyStream);
    Optional<String> nonEmptyStreamResult = findFirstAndGet.apply(nonEmptyStream);

    assertFalse(emptyStreamResult.isPresent());
    assertTrue(nonEmptyStreamResult.isPresent());
    assertEquals("Hello", nonEmptyStreamResult.get());
  }

  private static <T> T getValue(Optional<T> optional, T defaultValue) {

    return optional.orElseGet(() -> defaultValue);
  }

  class YieldOptional {
    Optional<String> get() {
      return Optional.of("Hello");
    }
  }

}
