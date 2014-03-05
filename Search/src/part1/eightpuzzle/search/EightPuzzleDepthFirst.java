package part1.eightpuzzle.search;

import part1.eightpuzzle.PuzzleMove;
import search.DepthFirstFrontier;
import search.interfaces.Frontier;

/**
 * Performs DFS on the 8-puzzle problem.
 */
public class EightPuzzleDepthFirst extends EightPuzzleSearch {
	public static void main(String[] args) {
		new EightPuzzleDepthFirst().search();
	}

	@Override
	protected Frontier<EightPuzzleNode, PuzzleMove> createFrontier(EightPuzzleNode goal) {
		return new DepthFirstFrontier<EightPuzzleNode, PuzzleMove>();
	}
}
