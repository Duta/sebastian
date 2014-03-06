package part1.eightpuzzle.search;

import part1.eightpuzzle.EightPuzzle;
import part1.eightpuzzle.PuzzleMove;
import search.AStarFrontier;
import search.interfaces.Frontier;

/**
 * Performs A* search on the 8-puzzle problem.
 */
public class EightPuzzleAStar
        extends EightPuzzleSearch {
    /**
     * Runs A* search on the 8-puzzle problem.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        new EightPuzzleAStar().search();
    }

    /**
     * Creates and returns a frontier
     * for A* search of an 8-puzzle.
     *
     * @param goal the goal node
     * @return a new frontier which implements A*
     */
    @Override
    protected Frontier<EightPuzzle, EightPuzzleNode, PuzzleMove>
    createFrontier(EightPuzzleNode goal) {
        return new AStarFrontier<EightPuzzle, EightPuzzleNode, PuzzleMove>(goal);
    }
}
