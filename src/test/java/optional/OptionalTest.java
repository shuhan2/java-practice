package optional;

import java.util.ArrayList;
import java.util.List;
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
    // TODO: please create an empty optional and specify the concrete exception type.
    // <--start
    Optional<String> empty = Optional.empty();
    Class errorType = null;
    // --end-->

    assertThrows(NullPointerException.class, empty::get);
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

  @SuppressWarnings({"ConstantConditions", "unused"})
  @Test
  void should_be_able_to_throw_for_empty_optional() {
    Optional<String> empty = Optional.empty();

    // TODO: In the `Runnable` object. Please throw IllegalStateException when `empty` is not present.
    // <--start
    Runnable emptyRunnable = null;
    // --end-->

    assertThrows(IllegalStateException.class, emptyRunnable::run);
  }

  @SuppressWarnings({"MismatchedQueryAndUpdateOfCollection", "ConstantConditions"})
  @Test
  void should_process_value_if_present() {
    Optional<String> optional = Optional.of("word");
    List<String> result = new ArrayList<>();

    // TODO: please add the upper-cased value to result if `optional` is present in `Consumer<Optional<String>>`
    // TODO: implementation.
    // <--start
    Consumer<Optional<String>> optionalConsumer = null;
    // --end-->

    optionalConsumer.accept(optional);

    assertEquals("WORD", result.get(0));
  }

  @SuppressWarnings({"ConstantConditions", "MismatchedQueryAndUpdateOfCollection"})
  @Test
  void should_map_value_if_present() {
    Optional<String> optional = Optional.of("word");
    Optional<String> empty = Optional.empty();

    List<String> result = new ArrayList<>();

    // TODO: The `Function<Optional<String>, Optional<Boolean>>` will map `Optional<String>` to `Optional<Boolean>`
    // TODO: please add the upper-cased value to `result` list if optional is present. Then return the boolean
    // TODO: mapping result of `result.add`.
    // <--start
    Function<Optional<String>, Optional<Boolean>> mapping = null;
    // --end-->

    Optional<Boolean> mappingResult = mapping.apply(optional);
    Optional<Boolean> emptyMappingResult = mapping.apply(empty);

    assertTrue(mappingResult.orElseThrow(IllegalStateException::new));
    assertEquals("WORD", result.get(0));
    assertFalse(emptyMappingResult.isPresent());
  }

  @SuppressWarnings("ConstantConditions")
  @Test
  void should_flat_map_optional_value_do_chain_method() {
    Stream<YieldOptional> emptyStream = Stream.of(1, 2, 3)
        .filter(i -> i > 4)
        .map(i -> new YieldOptional());
    Stream<YieldOptional> nonEmptyStream = Stream.of(1, 2, 3)
        .filter(i -> i > 1)
        .map(i -> new YieldOptional());

    // TODO: The `findFirstAndGet` interface will find first item in stream. If it can be found, map it with
    // TODO: `YieldOptional.get` method. Otherwise, returns empty Optional.
    // <--start
    Function<Stream<YieldOptional>, Optional<String>> findFirstAndGet = null;
    // --end-->

    Optional<String> emptyStreamResult = findFirstAndGet.apply(emptyStream);
    Optional<String> nonEmptyStreamResult = findFirstAndGet.apply(nonEmptyStream);

    assertFalse(emptyStreamResult.isPresent());
    assertTrue(nonEmptyStreamResult.isPresent());
    assertEquals("Hello", nonEmptyStreamResult.get());
  }

  private static <T> T getValue(Optional<T> optional, T defaultValue) {
    // TODO: please implement the following method to pass the test
    // <--start
    throw new NotImplementedException();
    // --end-->
  }

  class YieldOptional {
    Optional<String> get() {
      return Optional.of("Hello");
    }
  }

}
