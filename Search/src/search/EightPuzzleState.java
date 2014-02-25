package search;

import java.util.ArrayList;

import rp13.search.problem.puzzle.EightPuzzle;
import rp13.search.problem.puzzle.EightPuzzle.PuzzleMove;
import search.interfaces.Node;

public class EightPuzzleState extends Node<EightPuzzleState, PuzzleMove> {
	EightPuzzle puzzle;

	public EightPuzzleState(EightPuzzle puzzle) {
		this(puzzle, null, null);
	}

	public EightPuzzleState(EightPuzzle puzzle, EightPuzzleState parent, PuzzleMove action) {
		this.puzzle = puzzle;
		this.parent = parent;
		this.action = action;
	}

	@Override
	public ArrayList<EightPuzzleState> explore() {
		ArrayList<EightPuzzleState> successors = new ArrayList<EightPuzzleState>();

		for (PuzzleMove move : PuzzleMove.values()) {
			if (puzzle.isPossibleMove(move)) {
				EightPuzzle newPuzzle = new EightPuzzle(puzzle);
				newPuzzle.makeMove(move);

				successors.add(new EightPuzzleState(newPuzzle, this, move));
			}
		}

		return successors;
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof EightPuzzleState) {
			return puzzle.equals(((EightPuzzleState) other).puzzle) && action.equals(((EightPuzzleState)other).parent);
		}

		return false;
	}
	
	@Override
	public boolean stateEquals(Object other) {
		if (other instanceof EightPuzzleState) {
			return puzzle.equals(((EightPuzzleState) other).puzzle);
		}

		return false;
	}

	@Override
	public int heuristic(EightPuzzleState goal) {
		return 0;
	}

	@Override
	public int totalPathCost() {
		return 0;
	}
	
	public String toString() {
		return puzzle.toString();
	}

}
