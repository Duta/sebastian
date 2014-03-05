package part1.jumbledstring.search;

import part1.jumbledstring.CharacterSwap;
import part1.jumbledstring.JumbledString;
import search.BreadthFirstFrontier;
import search.interfaces.Frontier;

/**
 * Performs BFS on the jumbled string problem.
 */
public class JumbledStringBreadthFirst extends JumbledStringSearch {
    public static void main(String[] args) {
        new JumbledStringBreadthFirst().search();
    }

    @Override
    protected Frontier<JumbledString, JumbledStringNode, CharacterSwap> createFrontier(JumbledStringNode goal) {
        return new BreadthFirstFrontier<JumbledString, JumbledStringNode, CharacterSwap>();
    }
}
