package part1.jumbledstring.search;

import part1.jumbledstring.CharacterSwap;
import part1.jumbledstring.JumbledString;
import search.DepthFirstFrontier;
import search.interfaces.Frontier;

/**
 * Performs DFS on the jumbled string problem.
 */
public class JumbledStringDepthFirst
        extends JumbledStringSearch {
    public static void main(String[] args) {
        new JumbledStringDepthFirst().search();
    }

    @Override
    protected Frontier<JumbledString, JumbledStringNode, CharacterSwap>
    createFrontier(JumbledStringNode goal) {
        return new DepthFirstFrontier<JumbledString, JumbledStringNode, CharacterSwap>();
    }
}
