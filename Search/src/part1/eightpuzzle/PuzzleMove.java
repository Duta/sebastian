package part1.eightpuzzle;

import util.ArrayUtil;

/**
 * Represents an action in the 8-puzzle problem.
 */
public enum PuzzleMove {
	UP(-3), DOWN(3), LEFT(-1), RIGHT(1);

	private final int move;

	private PuzzleMove(int move) {
		this.move = move;
	}
	
	public int getMove() {
		return move;
	}

	public static PuzzleMove randomMove() {
		return ArrayUtil.randomChoice(values());
	}
}
