package part1.jumbledstring;

import util.ArrayUtil;

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
        ArrayUtil.swap(characters, swap.getIndexA(), swap.getIndexB());
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
