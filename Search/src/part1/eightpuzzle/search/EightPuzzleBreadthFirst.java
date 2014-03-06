package part1.eightpuzzle.search;

import part1.eightpuzzle.EightPuzzle;
import part1.eightpuzzle.PuzzleMove;
import search.BreadthFirstFrontier;
import search.interfaces.Frontier;

/**
 * Performs BFS on the 8-puzzle problem.
 */
public class EightPuzzleBreadthFirst
        extends EightPuzzleSearch {
    /**
     * Runs BFS on the 8-puzzle.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        new EightPuzzleBreadthFirst().search();
    }

    /**
     * Creates and returns a frontier
     * for BFS of an 8-puzzle.
     *
     * @param goal the goal node
     * @return a new frontier which implements BFS
     */
    @Override
    protected Frontier<EightPuzzle, EightPuzzleNode, PuzzleMove>
    createFrontier(EightPuzzleNode goal) {
        return new BreadthFirstFrontier<EightPuzzle, EightPuzzleNode, PuzzleMove>();
    }
}
