package part1.eightpuzzle.search;

import part1.Part1SearchRunner;
import part1.eightpuzzle.EightPuzzle;
import part1.eightpuzzle.PuzzleMove;

/**
 * Performs search on the 8-puzzle problem.
 * Can be extended to provide a frontier (search type).
 */
public abstract class EightPuzzleSearch
        extends Part1SearchRunner<EightPuzzle, EightPuzzleNode, PuzzleMove> {
    @Override
    protected EightPuzzleNode createStart() {
        return new EightPuzzleNode(EightPuzzle.randomEightPuzzle());
    }

    @Override
    protected EightPuzzleNode createGoal() {
        return new EightPuzzleNode(EightPuzzle.orderedEightPuzzle());
    }
}
