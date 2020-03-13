package quiz.wordProcessor;

public class LineProcessor extends BaseProcessor {
  private int index;

  public LineProcessor(TextProcessorSettings settings) {
    super(settings);
  }

  @Override
  void handleCharacter(ProcessedCharacter character) {
    int lineNumber = index / getSettings().getWidth() + 1;
    character.setLine(lineNumber);
    index++;
    addToCharacters(character);
  }
}
