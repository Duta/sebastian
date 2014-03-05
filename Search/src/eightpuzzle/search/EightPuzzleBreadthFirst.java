package eightpuzzle.search;

import eightpuzzle.PuzzleMove;
import search.BreadthFirstFrontier;
import search.interfaces.Frontier;

public class EightPuzzleBreadthFirst extends EightPuzzleSearch {
	public static void main(String[] args) {
		new EightPuzzleBreadthFirst().search();
	}

	@Override
	protected Frontier<EightPuzzleNode, PuzzleMove> createFrontier(EightPuzzleNode goal) {
		return new BreadthFirstFrontier<EightPuzzleNode, PuzzleMove>();
	}
}
