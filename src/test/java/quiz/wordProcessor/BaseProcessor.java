package quiz.wordProcessor;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseProcessor {

  private final TextProcessorSettings settings;
  private List<ProcessedCharacter> characters  = new ArrayList<>();;

  public BaseProcessor(TextProcessorSettings settings) {
    this.settings = settings;
  }


  public List<ProcessedCharacter> getCharacters() {
    return characters;
  }

  public TextProcessorSettings getSettings() {
    return settings;
  }

  abstract void handleCharacter(ProcessedCharacter character);

  public void addToCharacters(ProcessedCharacter character) {
    characters.add(character);
  }
}
