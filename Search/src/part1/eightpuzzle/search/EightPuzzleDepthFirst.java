package part1.eightpuzzle.search;

import part1.eightpuzzle.EightPuzzle;
import part1.eightpuzzle.PuzzleMove;
import search.DepthFirstFrontier;
import search.interfaces.Frontier;

/**
 * Performs DFS on the 8-puzzle problem.
 */
public class EightPuzzleDepthFirst extends EightPuzzleSearch {
    /**
     * Runs DFS on the 8-puzzle.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
		new EightPuzzleDepthFirst().search();
	}

    /**
     * Creates and returns a frontier
     * for DFS of an 8-puzzle.
     *
     * @param goal the goal node
     * @return a new frontier which implements DFS
     */
    @Override
	protected Frontier<EightPuzzle, EightPuzzleNode, PuzzleMove> createFrontier(EightPuzzleNode goal) {
		return new DepthFirstFrontier<EightPuzzle, EightPuzzleNode, PuzzleMove>();
	}
}
