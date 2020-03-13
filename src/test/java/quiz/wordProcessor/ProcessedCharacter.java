package quiz.wordProcessor;

class ProcessedCharacter {
    private final char value;
    private final boolean isWhitespace;
    private int line;
    private int groupId;

    public ProcessedCharacter(char value, boolean isWhitespace) {
        this.value = value;
        this.isWhitespace = isWhitespace;
    }

    public char getValue() {
        return value;
    }

    public boolean isWhitespace() {
        return isWhitespace;
    }

    public int getLine() {
        return line;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public void setLine(int lineNumber) {
        line = lineNumber;
    }
}
