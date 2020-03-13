package quiz.wordProcessor;

public class GroupProcessor extends BaseProcessor {
  private int groupNumber = 0;
  private boolean isSpace = false;

  public GroupProcessor(TextProcessorSettings settings) {
    super(settings);
  }

  @Override
  void handleCharacter(ProcessedCharacter character) {
    if (groupNumber == 0 || (isSpace !=character.isWhitespace())) {
      groupNumber ++;
    }
    isSpace = character.isWhitespace();
    character.setGroupId(groupNumber);
    addToCharacters(character);
  }
}
