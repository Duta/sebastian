package eightpuzzle;

import util.Util;

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
		return Util.randomChoice(values());
	}
}
