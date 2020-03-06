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

class StreamingTest1 {

  @Test
  void should_be_able_to_turn_collection_into_stream() {
    List<String> words = Arrays.asList("a", "quick", "brown", "fox", "jumps", "over");

    // TODO: please modify the following code to pass the test
    // <--start
    Stream<String> wordStream = words.stream();
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
    Stream<String> wordStream = Arrays.stream(words);
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
    Stream<String> emptyWordStream = Stream.empty();
    // --end-->
    {
      assertEquals(0, emptyWordStream.count());
    }
  }

  @Test
  void should_be_able_to_generate_infinite_stream_with_same_items() {
    // TODO: please modify the following code to pass the test
    // <--start
    Stream<String> infiniteEchos =  Stream.generate(() -> "Echo");
    // --end-->
    {
      assertEquals("Echo", infiniteEchos.skip(10000).findFirst().get());
    }
  }

  @Test
  void should_be_able_to_generate_infinite_stream_of_sequence() {
    // TODO: please modify the following code to pass the test
    // <--start
    Stream<Integer> infiniteSequence =  Stream.iterate(0, i -> ++i);
    // --end-->
    {
      assertEquals(10000, infiniteSequence.skip(10000).findFirst().get().intValue());
    }
  }

  @SuppressWarnings("unused")
  @Test
  void should_be_able_to_filter_stream() {
    Stream<String> wordStream = Stream.of("a", "quick", "brown", "fox", "jumps", "over");

    // TODO: please write code to filter word whose length is greater than 4
    // <--start
    Stream<String> filtered = wordStream.filter(word -> word.length() > 4);
    // --end-->
    {
      assertArrayEquals(new String[]{"quick", "brown", "jumps"}, filtered.toArray(String[]::new));
    }
  }

  @SuppressWarnings({"unused"})
  @Test
  void should_be_able_to_map_stream() {
    Stream<String> wordStream = Stream.of("a", "quick", "brown", "fox", "jumps", "over");

    // TODO: please write code to filter word whose length is greater than 4
    // <--start
    Stream<String> filtered = wordStream.map(String::toUpperCase);
    // --end-->
    {
      assertArrayEquals(
          new String[]{"A", "QUICK", "BROWN", "FOX", "JUMPS", "OVER"},
          filtered.toArray(String[]::new));
    }
  }

  @SuppressWarnings({"unused"})
  @Test
  void should_be_able_to_map_item_to_a_new_type() {
    Stream<String> nameStream = Stream.of("Naruto", "Kisuke", "Tomoya");

    // TODO: please modify the following code to pass the test
    // <--start
    Stream<AnimeCharacter> characters = nameStream.map(AnimeCharacter::new);
    // --end-->
    {
      AnimeCharacter[] actual = characters.toArray(AnimeCharacter[]::new);
      assertArrayEquals(
          new AnimeCharacter[] {
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
        .map(StreamingTest1::letters);

    // TODO: please modify the following code to pass the test
    // <--start
    Stream<Character> flatted = nameStream.flatMap(name -> name);
    // --end-->
    {
      assertArrayEquals(
          new Character[] {
              'N', 'a', 'r', 'u', 't', 'o', 'K', 'i', 's', 'u', 'k',
              'e', 'T', 'o', 'm', 'o', 'y', 'a'
          },
          flatted.toArray(Character[]::new));
    }
  }

  @SuppressWarnings({"unused"})
  @Test
  void should_create_sequence_of_specified_size() {
    Stream<Integer> infiniteSequence = Stream.iterate(0, i -> i + 1);

    // TODO: please modify the following code to pass the test
    // <--start
    Stream<Integer> finiteStream = infiniteSequence.limit(10);
    // --end-->
    {
      assertArrayEquals(
          new Integer[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9},
          finiteStream.toArray(Integer[]::new)
      );
    }
  }

  @SuppressWarnings({"unused"})
  @Test
  void should_concat_streams() {
    Stream<Character> helloStream = Stream.of('H', 'e', 'l', 'l', 'o');
    Stream<Character> worldStream = Stream.of('W', 'o', 'r', 'l', 'd');

    // TODO: please modify the following code to pass the test
    // <--start
    Stream<Character> concatStream = Stream.concat(helloStream, worldStream);
    // --end-->
    {
      assertArrayEquals(
          letters("HelloWorld").toArray(Character[]::new),
          concatStream.toArray(Character[]::new)
      );
    }
  }

  @SuppressWarnings({"SpellCheckingInspection", "unused"})
  @Test
  void should_get_unique_items() {
    Stream<Character> characterStream = letters("aquickbrownfox");

    // TODO: please modify the following code to pass the test
    // <--start
    Stream<Character> distinct = characterStream.distinct();
    // --end-->
    {
      Character[] characters = distinct.sorted().toArray(Character[]::new);

      assertArrayEquals(
          new Character[] {'a', 'b', 'c', 'f', 'i', 'k', 'n', 'o', 'q', 'r', 'u', 'w', 'x'},
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

    hookStream.forEach(i -> {});

    // TODO: please modify the following code to pass the test
    // <--start
    final int expected = 90;
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