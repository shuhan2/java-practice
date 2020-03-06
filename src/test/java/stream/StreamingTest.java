package stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

class StreamingTest {

  @Test
  void should_be_able_to_turn_collection_into_stream() {
    List<String> words = Arrays.asList("a", "quick", "brown", "fox", "jumps", "over");

    // TODO: please modify the following code to pass the test
    // <--start
    try (Stream<String> wordStream = null)
    // --end-->
    {
      assertIterableEquals(
          words,
          wordStream.collect(Collectors.toList())
      );
    }
  }

  @Test
  void should_be_able_to_turn_array_into_stream() {
    String[] words = {"a", "quick", "brown", "fox", "jumps", "over"};

    // TODO: please modify the following code to pass the test
    // <--start
    try (Stream<String> wordStream = null)
    // --end-->
    {
      assertArrayEquals(
          words,
          wordStream.toArray(String[]::new)
      );
    }
  }

  @Test
  void should_be_able_to_generate_empty_stream() {
    // TODO: please modify the following code to pass the test
    // <--start
    try (Stream<String> emptyWordStream = null)
    // --end-->
    {
      assertEquals(0, emptyWordStream.count());
    }
  }

  @SuppressWarnings("ConstantConditions")
  @Test
  void should_be_able_to_generate_infinite_stream_with_same_items() {
    // TODO: please modify the following code to pass the test
    // <--start
    try (Stream<String> infiniteEchos = null)
    // --end-->
    {
      assertEquals("Echo", infiniteEchos.skip(10000).findFirst().get());
    }
  }

  @SuppressWarnings("ConstantConditions")
  @Test
  void should_be_able_to_generate_infinite_stream_of_sequence() {
    // TODO: please modify the following code to pass the test
    // <--start
    try (Stream<Integer> infiniteSequence = null)
    // --end-->
    {
      assertEquals(10000, infiniteSequence.skip(10000).findFirst().get().intValue());
    }
  }

  @SuppressWarnings({"unused", "ConstantConditions"})
  @Test
  void should_be_able_to_filter_stream() {
    Stream<String> wordStream = Stream.of("a", "quick", "brown", "fox", "jumps", "over");

    // TODO: please write code to filter word whose length is greater than 4
    // <--start
    try (Stream<String> filtered = null)
    // --end-->
    {
      assertArrayEquals(new String[]{"quick", "brown", "jumps"}, filtered.toArray(String[]::new));
    }
  }

  @SuppressWarnings({"unused", "ConstantConditions"})
  @Test
  void should_be_able_to_map_stream() {
    Stream<String> wordStream = Stream.of("a", "quick", "brown", "fox", "jumps", "over");

    // TODO: please write code to make the word all upper case
    // <--start
    try (Stream<String> filtered = null)
    // --end-->
    {
      assertArrayEquals(
          new String[]{"A", "QUICK", "BROWN", "FOX", "JUMPS", "OVER"},
          filtered.toArray(String[]::new));
    }
  }

  @SuppressWarnings({"unused", "ConstantConditions"})
  @Test
  void should_be_able_to_map_item_to_a_new_type() {
    Stream<String> nameStream = Stream.of("Naruto", "Kisuke", "Tomoya");

    // TODO: please modify the following code to pass the test
    // <--start
    try (Stream<AnimeCharacter> characters = null)
    // --end-->
    {
      AnimeCharacter[] actual = characters.toArray(AnimeCharacter[]::new);
      assertArrayEquals(
          new AnimeCharacter[]{
              new AnimeCharacter("Naruto"),
              new AnimeCharacter("Kisuke"),
              new AnimeCharacter("Tomoya")
          },
          actual);
    }
  }

  @SuppressWarnings({"unused", "ConstantConditions"})
  @Test
  void should_flatten_stream_of_streams() {
    Stream<Stream<Character>> nameStream = Stream
        .of("Naruto", "Kisuke", "Tomoya")
        .map(StreamingTest::letters);

    // TODO: please modify the following code to pass the test
    // <--start
    try (Stream<Character> flatted = null)
    // --end-->
    {
      assertArrayEquals(
          new Character[]{
              'N', 'a', 'r', 'u', 't', 'o', 'K', 'i', 's', 'u', 'k',
              'e', 'T', 'o', 'm', 'o', 'y', 'a'
          },
          flatted.toArray(Character[]::new));
    }
  }

  @SuppressWarnings({"unused", "ConstantConditions"})
  @Test
  void should_create_sequence_of_specified_size() {
    Stream<Integer> infiniteSequence = Stream.iterate(0, i -> i + 1);

    // TODO: please modify the following code to pass the test
    // <--start
    try (Stream<Integer> finiteStream = null)
    // --end-->
    {
      assertArrayEquals(
          new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9},
          finiteStream.toArray(Integer[]::new)
      );
    }
  }

  @SuppressWarnings({"unused", "ConstantConditions"})
  @Test
  void should_concat_streams() {
    Stream<Character> helloStream = Stream.of('H', 'e', 'l', 'l', 'o');
    Stream<Character> worldStream = Stream.of('W', 'o', 'r', 'l', 'd');

    // TODO: please modify the following code to pass the test
    // <--start
    try (Stream<Character> concatStream = null)
    // --end-->
    {
      assertArrayEquals(
          letters("HelloWorld").toArray(Character[]::new),
          concatStream.toArray(Character[]::new)
      );
    }
  }

  @SuppressWarnings({"unused", "SpellCheckingInspection"})
  @Test
  void should_get_unique_items() {
    Stream<Character> characterStream = letters("aquickbrownfox");

    // TODO: please modify the following code to pass the test
    // <--start
    try (Stream<Character> distinct = null)
    // --end-->
    {
      Character[] characters = distinct.sorted().toArray(Character[]::new);

      assertArrayEquals(
          new Character[]{'a', 'b', 'c', 'f', 'i', 'k', 'n', 'o', 'q', 'r', 'u', 'w', 'x'},
          characters
      );
    }
  }

  @Test
  void should_hook_stream_generation() {
    ValueHolder<Integer> holder = new ValueHolder<>();
    holder.setValue(0);

    Stream<Integer> hookStream = Stream
        .iterate(0, i -> i + 1)
        .limit(20)
        .filter(v -> v % 2 == 0)
        .peek(v -> holder.setValue(holder.getValue() + v));

    hookStream.forEach(i -> {
    });

    // TODO: please modify the following code to pass the test
    // <--start
    final int expected = Integer.MAX_VALUE;
    // --end-->

    assertEquals(expected, holder.getValue().intValue());
  }


  private static Stream<Character> letters(String text) {
    List<Character> characters = new ArrayList<>();
    for (int i = 0; i < text.length(); ++i) {
      characters.add(text.charAt(i));
    }
    return characters.stream();
  }

}