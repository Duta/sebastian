package jumbledstring.search;

import jumbledstring.CharacterSwap;
import jumbledstring.JumbledString;
import search.interfaces.Node;

import java.util.ArrayList;
import java.util.List;

public class JumbledStringNode extends Node<JumbledStringNode, CharacterSwap> {
    private final JumbledString jumbledString;

    public JumbledStringNode(JumbledString jumbledString) {
        this(jumbledString, null, null);
    }

    public JumbledStringNode(JumbledString jumbledString,
            JumbledStringNode parent,
            CharacterSwap action) {
        this.jumbledString = jumbledString;
        this.parent = parent;
        this.action = action;
    }

    @Override
    public List<JumbledStringNode> explore() {
        List<JumbledStringNode> successors = new ArrayList<JumbledStringNode>();
        int length = jumbledString.length();
        for(int i = 0; i < length - 1; i++) {
            for(int j = i + 1; j < length; j++) {
                CharacterSwap swap = new CharacterSwap(i, j);
                JumbledString newState = jumbledString.makeSwap(swap);
                successors.add(new JumbledStringNode(newState, this, swap));
            }
        }
        return successors;
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof JumbledStringNode
            && jumbledString.equals(((JumbledStringNode)other).jumbledString);
    }

    @Override
    public int heuristic(JumbledStringNode goal) {
        // Calculates the hamming distance between this and the goal
        if(jumbledString.length() != goal.jumbledString.length()) {
            // TODO: Change to sebastian internal exception
            throw new RuntimeException(
                    "The two nodes must have strings of the same length");
        }
        int hammingDistance = 0;
        for(int i = 0; i < jumbledString.length(); i++) {
            if(jumbledString.character(i) != goal.jumbledString.character(i)) {
                hammingDistance++;
            }
        }
        return hammingDistance;
    }

    @Override
    public String toString() {
        return jumbledString.toString();
    }

    public JumbledString getJumbledString() {
        return jumbledString;
    }
}
