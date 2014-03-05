package part1.eightpuzzle.search;

import part1.eightpuzzle.EightPuzzle;
import part1.eightpuzzle.PuzzleMove;
import search.interfaces.Node;

/**
 * Represents a node in the 8-puzzle problem.
 * Used for searching the 8-puzzle problem.
 */
public class EightPuzzleNode extends Node<EightPuzzle, EightPuzzleNode, PuzzleMove> {
    public EightPuzzleNode(EightPuzzle puzzle) {
        super(puzzle);
	}

    public EightPuzzleNode(EightPuzzle puzzle, EightPuzzleNode parent, PuzzleMove action) {
        super(puzzle, parent, action);
	}

	@Override
	public PuzzleMove[] allActions() {
        return PuzzleMove.values();
	}

	@Override
	public boolean equals(Object other) {
		return other instanceof EightPuzzleNode
            && state.equals(((EightPuzzleNode) other).state);
	}

    @Override
	public int heuristic(EightPuzzleNode goal) {
        int numDifferent = 0;
        for(int x = 0; x < EightPuzzle.SIZE; x++) {
            for(int y = 0; y < EightPuzzle.SIZE; y++) {
                if(state.getValue(x, y) != goal.state.getValue(x, y)) {
                    numDifferent++;
                }
            }
        }
        return numDifferent;
    }

	@Override
	public EightPuzzleNode applyAction(PuzzleMove action) {
        return state.isPossibleMove(action)
            ? new EightPuzzleNode(state.makeMove(action), this, action)
            : this;
    }
}
