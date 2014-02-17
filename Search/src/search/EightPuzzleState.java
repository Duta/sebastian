package search;

import java.util.ArrayList;

import rp13.search.problem.puzzle.EightPuzzle;
import rp13.search.problem.puzzle.EightPuzzle.PuzzleMove;
import search.interfaces.State;

public class EightPuzzleState extends State<EightPuzzleState> {
	EightPuzzle puzzle;

	public EightPuzzleState(EightPuzzle puzzle) {
		this(puzzle, null);
	}

	public EightPuzzleState(EightPuzzle puzzle, EightPuzzleState parent) {
		this.puzzle = puzzle;
		this.parent = parent;
	}

	@Override
	public ArrayList<EightPuzzleState> explore() {
		ArrayList<EightPuzzleState> successors = new ArrayList<EightPuzzleState>();

		for (PuzzleMove move : PuzzleMove.values()) {
			if (puzzle.isPossibleMove(move)) {
				EightPuzzle newPuzzle = new EightPuzzle(puzzle);
				newPuzzle.makeMove(move);

				successors.add(new EightPuzzleState(newPuzzle, this));
			}
		}

		return successors;
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof EightPuzzleState) {
			/*if (parent == null) {
				return puzzle.equals(((EightPuzzleState) other).puzzle)
					&& ((EightPuzzleState) other).parent == null;
			} else {
				return puzzle.equals(((EightPuzzleState) other).puzzle)
					&& parent.equals(((EightPuzzleState) other).parent);
			}*/
			
			return puzzle.equals(((EightPuzzleState) other).puzzle);
		}

		return false;
	}

	@Override
	public int heuristic(EightPuzzleState goal) {
		return 0;
	}

	public String toString() {
		return puzzle.toString();
	}

}
