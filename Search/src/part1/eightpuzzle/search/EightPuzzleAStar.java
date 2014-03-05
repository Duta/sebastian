package part1.eightpuzzle.search;

import part1.eightpuzzle.PuzzleMove;
import search.AStarFrontier;
import search.interfaces.Frontier;

/**
 * Performs A* search on the 8-puzzle problem.
 */
public class EightPuzzleAStar extends EightPuzzleSearch {
	public static void main(String[] args) {
		new EightPuzzleAStar().search();
	}

	@Override
	protected Frontier<EightPuzzleNode, PuzzleMove> createFrontier(EightPuzzleNode goal) {
		return new AStarFrontier<EightPuzzleNode, PuzzleMove>(goal);
	}
}
