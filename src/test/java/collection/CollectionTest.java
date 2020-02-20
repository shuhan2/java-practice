package collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class CollectionTest {
  @Test
  void should_go_through_an_iterator() {
    List<String> collection = new ArrayList<>();
    collection.add("Hello");
    collection.add("World");
    collection.add("!");
    Iterator<String> iterator = collection.iterator();

    assertIterableEquals(Arrays.asList("Hello", "World", "!"), createList(iterator));
  }

  @SuppressWarnings({"unused", "UnnecessaryLocalVariable"})
  private static List<String> createList(Iterator<String> iterator) {
    List<String> list = new ArrayList<>();

    // allowed.
    // <--start
    while (iterator.hasNext()) {
      list.add(iterator.next());
    }

    // --end-->

    return list;
  }

  @Test
  void should_create_a_sequence_without_putting_all_items_into_memory() {
    Sequence sequence = new Sequence(4, 10);
    assertIterableEquals(Arrays.asList(4, 5, 6, 7, 8, 9), sequence);
  }

  @Test
  void should_predict_linked_list_operation_result() {
    LinkedList<String> staff = new LinkedList<>();

    staff.add("Amy");
    staff.add("Bob");
    staff.add("Carl");

    ListIterator<String> iterator = staff.listIterator();
    iterator.next();
    iterator.add("Juliet");
    iterator.previous();
    iterator.remove();

    // TODO: please modify the following code to pass the test
    // <--start
    final List<String> expected = Arrays.asList("I", "Don't", "Know");
    // --end-->

    assertIterableEquals(expected, staff);
  }

  @Test
  void should_generate_distinct_sequence_on_the_fly() {
    // NOTE: This test may execute for a while. But it is okay if your impl is correct.
    final int oneGagaChars = 1024 * 1024 * 1024;
    RandomCharacterIterable characters = new RandomCharacterIterable(
        oneGagaChars,
        new Character[]{'a', 'b'});

    List<Character> distinct = new DistinctIterable<>(characters).toList();
    distinct.sort(Character::compareTo);

    assertIterableEquals(Arrays.asList('a', 'b'), distinct);
  }

  @Test
  void should_reflects_back_to_original_list_for_sub_range() {
    List<Integer> integers = new ArrayList<>();
    for (int i = 0; i < 12; ++i) {
      integers.add(i);
    }

    List<Integer> subList = integers.subList(3, 10);
    subList.clear();

    // TODO: please modify the following code to pass the test
    // <--start
    final List<Integer> expected = Arrays.asList(0, 0, 0);
    // --end-->

    assertIterableEquals(expected, integers);
  }

}

/*
 * - Can you expect the order returned when iterating over a `HashSet<T>`?
 *   `PriorityQueue`, `HashMap`, `TreeMap`, `EnumMap`, `LinkedHashMap`
 * - What if an collection is modified while an iterator is still iterating?
 * - What are the differences between HashMap and HashSet?
 * - What is size(), and what capacity?
 */
