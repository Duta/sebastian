package eightpuzzle.search;

import eightpuzzle.PuzzleMove;
import search.DepthFirstFrontier;
import search.interfaces.Frontier;

public class EightPuzzleDepthFirst extends EightPuzzleSearch {
	public static void main(String[] args) {
		new EightPuzzleDepthFirst().search();
	}

	@Override
	protected Frontier<EightPuzzleNode, PuzzleMove> createFrontier(EightPuzzleNode goal) {
		return new DepthFirstFrontier<EightPuzzleNode, PuzzleMove>();
	}
}
