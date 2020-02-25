package generics;


import java.util.Optional;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GenericTest {

  @Test
  void should_auto_resolve_generic_method() {
    final String[] words = {"Hello", "Good", "Morning"};

    // TODO: please call getMiddle method for string
    final String middle = getMiddle(words);

    assertEquals("Good", middle);
  }

  @Test
  void should_specify_a_type_restriction_on_typed_parameters() {
    int minimumInteger = min(new Integer[]{1, 2, 3});
    double minimumReal = min(new Double[]{1.2, 2.2, -1d});

    assertEquals(1, minimumInteger);
    assertEquals(-1d, minimumReal, 1.0E-05);
  }

  @Test
  void should_not_know_generic_type_parameters_at_runtime() {
    KeyValuePair<String, Integer> pair = new KeyValuePair<>("name", 2);
    KeyValuePair<Integer, String> pairWithDifferentTypeParameter = new KeyValuePair<>(2, "name");

    // TODO: please modify the code to pass the test
    final Optional<Boolean> expected = Optional.of(true);

    assertEquals(expected.get(), pair.getClass().equals(pairWithDifferentTypeParameter.getClass()));
  }

  @Test
  void should_be_careful_of_raw_type_generic() {
    Pair<Manager> managerPair = new Pair<>();
    Pair rawPair = managerPair;
    rawPair.setFirst(new Employee());

    boolean willThrow = false;
    try {
      Manager first = managerPair.getFirst();
    } catch (ClassCastException error) {
      willThrow = true;
    }

    // TODO: please modify the following code to pass the test
    final Optional<Boolean> expected = Optional.of(true);

    assertEquals(expected.get(), willThrow);
  }

  @Test
  void should_swap() {
    Pair<String> pair = new Pair<>("Hello", "World");

    swap(pair);

    assertEquals("World", pair.getFirst());
    assertEquals("Hello", pair.getSecond());
  }

  @Disabled
  @Test
  void should_consider_the_inconvertible_type() {
    KeyValuePair[] keyValuePairs = new KeyValuePair[10];
    keyValuePairs[0] = new KeyValuePair<>("Name", 10);
    keyValuePairs[1] = new KeyValuePair<>(10, "Name");
    KeyValuePair<String, Integer> keyValuePair = keyValuePairs[1];
    assertEquals(10, keyValuePair.getKey());
    assertEquals("Name", keyValuePair.getValue());
  }

  private static <T> T getMiddle(T[] args) {
    return args[args.length / 2];
  }

  // TODO: please implement the following code to pass the test. It should be generic after all.
  // The method should only accept `Number` and the number should implement `Comparable<T>`
  private static <T extends Number & Comparable<T>> T min(T[] values) {
    if (values == null || values.length == 0) {
      return null;
    }
    T minNumber = values[0];
    for (T number: values) {
      if (number.compareTo(minNumber) < 0) {
        minNumber = number;
      }
    }
    return minNumber;
  }


  // TODO: please implement following method to pass the test. But you cannot change the signature
  private static void swap(Pair<?> pair) {
    helper(pair);
  }

  // TODO: You can add additional method within the range if you like
  private static<T> void helper(Pair<T> pair) {
    T temp = pair.getFirst();
    pair.setFirst(pair.getSecond());
    pair.setSecond(temp);
  }

}