package eightpuzzle;

import java.util.Arrays;

import util.SebastianInternalException;

/**
 * This class implements an eight puzzle state.
 */
public class EightPuzzle {
	public static final int
		BLANK = 0,
		SIZE = 3;

    private final int[] board;
	private final int blankPosition;

	private EightPuzzle() {
		this(new int[] {
			1, 2, 3,
			4, 5, 6,
			7, 8, BLANK
		});
	}
	
	private EightPuzzle(int[] board) { 
		this(board, findBlank(board));
	}
	
	private static int findBlank(int[] board) {
		for(int i = 0; i < board.length; i++) {
			if(board[i] == BLANK) {
				return i;
			}
		}
		throw new SebastianInternalException();
	}

	private EightPuzzle(int[] board, int blankPosition) {
		this.board = board;
		this.blankPosition = blankPosition;
	}

	public EightPuzzle randomMove() {
		PuzzleMove move;
		do {
			move = PuzzleMove.randomMove();
		} while(!isPossibleMove(move));
		return makeMove(move);
	}

	public boolean isPossibleMove(PuzzleMove move) {
		int newBlankPosition = getNewBlankPosition(move);
		// Check array bounds
		if (newBlankPosition < 0 || newBlankPosition >= board.length) {
			return false;
		}
		// Check logical bounds
		int currentRow = blankPosition / SIZE;
		int newRow = newBlankPosition / SIZE;
		return (currentRow == newRow)
			|| Math.abs(move.getMove()) > 1;
	}

	public EightPuzzle makeMove(PuzzleMove move) {
		int newBlankPosition = getNewBlankPosition(move);
		int[] newBoard = Arrays.copyOf(board, board.length);
		int temp = board[newBlankPosition];
		newBoard[newBlankPosition] = BLANK;
		newBoard[blankPosition] = temp;
		return new EightPuzzle(newBoard, newBlankPosition);
	}

    public int getValue(int x, int y) {
        return board[x + y*SIZE];
    }

    public boolean isBlank(int x, int y) {
        return getValue(x, y) == BLANK;
    }

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < board.length; i++) {
			sb.append("| ");
			sb.append(board[i] == BLANK ? "X" : board[i]);
			sb.append(" ");
			if ((i + 1) % SIZE == 0) {
				sb.append("|\n");
			}
		}
		return sb.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof EightPuzzle)) {
			return false;
		}
		EightPuzzle that = (EightPuzzle) obj;
		return this.blankPosition == that.blankPosition
			&& Arrays.equals(this.board, that.board);
	}

	public static EightPuzzle orderedEightPuzzle() {
		return new EightPuzzle();
	}

	public static EightPuzzle randomEightPuzzle() {
		return randomEightPuzzle(SIZE * SIZE * SIZE);
	}

	public static EightPuzzle randomEightPuzzle(int moves) {
		EightPuzzle puzzle = orderedEightPuzzle();
		for (int i = 0; i < moves; i++) {
			puzzle = puzzle.randomMove();
		}
		return puzzle;
	}
	
	private int getNewBlankPosition(PuzzleMove move) {
		return blankPosition + move.getMove();
	}
}
