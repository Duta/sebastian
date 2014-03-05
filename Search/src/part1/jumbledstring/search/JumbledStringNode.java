package part1.jumbledstring.search;

import part1.jumbledstring.CharacterSwap;
import part1.jumbledstring.JumbledString;
import search.interfaces.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a node in the jumbled string problem.
 * Used for searching the jumbled string problem.
 */
public class JumbledStringNode extends Node<JumbledString, JumbledStringNode, CharacterSwap> {
    public JumbledStringNode(JumbledString jumbledString) {
        super(jumbledString);
    }

    public JumbledStringNode(JumbledString jumbledString,
            JumbledStringNode parent, CharacterSwap action) {
        super(jumbledString, parent, action);
    }

    @Override
    public CharacterSwap[] allActions() {
        List<CharacterSwap> actions = new ArrayList<CharacterSwap>();
        int length = state.length();
        for(int i = 0; i < length - 1; i++) {
            for(int j = i + 1; j < length; j++) {
                actions.add(new CharacterSwap(i, j));
            }
        }
        return actions.toArray(new CharacterSwap[actions.size()]);
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof JumbledStringNode
            && state.equals(((JumbledStringNode)other).state);
    }

    @Override
    public int heuristic(JumbledStringNode goal) {
        // Calculates the hamming distance between this and the goal
        int hammingDistance = 0;
        for(int i = 0; i < state.length(); i++) {
            if(state.character(i) != goal.state.character(i)) {
                hammingDistance++;
            }
        }
        return hammingDistance;
    }

    @Override
    public JumbledStringNode applyAction(CharacterSwap action) {
        return new JumbledStringNode(state.makeSwap(action), this, action);
    }
}
