package quiz.wordProcessor;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

class TextProcessor {

  private final TextProcessorSettings settings;

  TextProcessor(int width) {
    this(width, null);
  }

  TextProcessor(int width, char[] whitespaces) {
    if (width < 10 || width > 80) {
      throw new IllegalArgumentException("Width out of range.");
    }

    settings = new TextProcessorSettings(width, getWhitespaces(whitespaces));
  }

  private char[] getWhitespaces(char[] whitespaces) {
    return whitespaces == null ?
        new char[]{' '} :
        whitespaces;
  }

  String process(String text) {
    if (text == null) {
      throw new IllegalArgumentException();
    }
    List<ProcessedCharacter> processedCharacters = text.chars()
        .mapToObj(c -> (char) c)
        .map(c -> new ProcessedCharacter(c, settings.isWhitespace(c)))
        .collect(Collectors.toList());

    return convertToString(processedCharacters(processedCharacters, new GroupProcessor(settings), new LineProcessor(settings)));
  }

  private List<ProcessedCharacter> processedCharacters(List<ProcessedCharacter> characters, BaseProcessor... processors) {
    Arrays.stream(processors).forEach(singleProcessor -> {
      characters.forEach(singleProcessor::handleCharacter);
    });

    return characters;
  }

  private String convertToString(List<ProcessedCharacter> processedCharacters) {
    Map<Integer, List<ProcessedCharacter>> characters = processedCharacters.stream()
        .collect(Collectors.groupingBy(ProcessedCharacter::getGroupId));
    StringBuilder builder = new StringBuilder();

    characters.forEach((key, value) -> {

      String stringValue = value.stream()
          .map(ProcessedCharacter::getValue)
          .collect(StringBuilder::new, StringBuilder::append,
                   StringBuilder::append).toString();

      String lineValue = value.stream()
          .map(ProcessedCharacter::getLine)
          .distinct()
          .sorted()
          .map(Objects::toString)
          .collect(Collectors.joining(","));

      builder.append(stringValue).append("(").append(lineValue).append(");");
    });
    return builder.toString();
  }
}

