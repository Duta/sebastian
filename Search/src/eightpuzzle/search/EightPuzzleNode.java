package eightpuzzle.search;

import java.util.ArrayList;
import java.util.List;

import eightpuzzle.EightPuzzle;
import eightpuzzle.PuzzleMove;
import search.interfaces.Node;

public class EightPuzzleNode extends Node<EightPuzzleNode, PuzzleMove> {
	private EightPuzzle puzzle;

	public EightPuzzleNode(EightPuzzle puzzle) {
		this(puzzle, null, null);
	}

	public EightPuzzleNode(EightPuzzle puzzle, EightPuzzleNode parent, PuzzleMove action) {
		this.puzzle = puzzle;
		this.parent = parent;
		this.action = action;
	}

	@Override
	public List<EightPuzzleNode> explore() {
		List<EightPuzzleNode> successors = new ArrayList<EightPuzzleNode>();
		for (PuzzleMove move : PuzzleMove.values()) {
			if (puzzle.isPossibleMove(move)) {
				EightPuzzle successorState = puzzle.makeMove(move);
				successors.add(new EightPuzzleNode(successorState, this, move));
			}
		}
		return successors;
	}

	@Override
	public boolean equals(Object other) {
		return other instanceof EightPuzzleNode
				&& puzzle.equals(((EightPuzzleNode) other).puzzle);
	}

	@Override
	public int heuristic(EightPuzzleNode goal) {
		return 0;
	}

	public String toString() {
		return puzzle.toString();
	}

}
