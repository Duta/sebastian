package part1.jumbledstring.search;

import part1.jumbledstring.CharacterSwap;
import part1.jumbledstring.JumbledString;
import search.AStarFrontier;
import search.interfaces.Frontier;

/**
 * Performs A* search on the jumbled string problem.
 */
public class JumbledStringAStar
		extends JumbledStringSearch {
    /**
     * Runs A* search on the jumbled string problem.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        new JumbledStringAStar().search();
    }

    /**
     * Creates and returns a frontier
     * for A* search of an 8-puzzle.
     *
     * @param goal the goal node
     * @return a new frontier which implements A*
     */
    @Override
    protected Frontier<JumbledString, JumbledStringNode, CharacterSwap>
    createFrontier(JumbledStringNode goal) {
        return new AStarFrontier<JumbledString, JumbledStringNode, CharacterSwap>(goal);
    }
}
