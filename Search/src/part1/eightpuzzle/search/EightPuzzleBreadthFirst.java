package part1.eightpuzzle.search;

import part1.eightpuzzle.PuzzleMove;
import search.BreadthFirstFrontier;
import search.interfaces.Frontier;

/**
 * Performs BFS on the 8-puzzle problem.
 */
public class EightPuzzleBreadthFirst extends EightPuzzleSearch {
	public static void main(String[] args) {
		new EightPuzzleBreadthFirst().search();
	}

	@Override
	protected Frontier<EightPuzzleNode, PuzzleMove> createFrontier(EightPuzzleNode goal) {
		return new BreadthFirstFrontier<EightPuzzleNode, PuzzleMove>();
	}
}
