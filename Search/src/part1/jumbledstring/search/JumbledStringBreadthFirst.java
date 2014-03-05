package part1.jumbledstring.search;

import part1.jumbledstring.CharacterSwap;
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
    protected Frontier<JumbledStringNode, CharacterSwap> createFrontier(JumbledStringNode goal) {
        return new BreadthFirstFrontier<JumbledStringNode, CharacterSwap>();
    }
}
