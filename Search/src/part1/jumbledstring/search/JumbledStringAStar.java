package part1.jumbledstring.search;

import part1.jumbledstring.CharacterSwap;
import search.AStarFrontier;
import search.interfaces.Frontier;

/**
 * Performs A* search on the jumbled string problem.
 */
public class JumbledStringAStar extends JumbledStringSearch {
    public static void main(String[] args) {
        new JumbledStringAStar().search();
    }

    @Override
    protected Frontier<JumbledStringNode, CharacterSwap> createFrontier(JumbledStringNode goal) {
        return new AStarFrontier<JumbledStringNode, CharacterSwap>(goal);
    }
}
