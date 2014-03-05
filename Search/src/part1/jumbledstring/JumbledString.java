package part1.jumbledstring;

/**
 * Represents an immutable state of a jumbled string.
 */
public class JumbledString {
    private final String string;

    public JumbledString(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }

    public char character(int index) {
        return string.charAt(index);
    }

    public JumbledString makeSwap(CharacterSwap swap) {
        char[] characters = string.toCharArray();
        int indexA = swap.getIndexA();
        int indexB = swap.getIndexB();
        char temp = characters[indexA];
        characters[indexA] = characters[indexB];
        characters[indexB] = temp;
        return new JumbledString(new String(characters));
    }

    public int length() {
        return string.length();
    }

    @Override
    public String toString() {
        return string;
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof JumbledString
            && string.equals(((JumbledString)other).string);
    }
}
