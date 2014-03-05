package eightpuzzle.search;

import eightpuzzle.EightPuzzle;
import eightpuzzle.PuzzleMove;
import search.SearchRunner;

public abstract class EightPuzzleSearch extends SearchRunner<EightPuzzleNode, PuzzleMove> {
    @Override
    protected EightPuzzleNode createStart() {
        return new EightPuzzleNode(EightPuzzle.randomEightPuzzle());
    }

    @Override
    protected EightPuzzleNode createGoal() {
        return new EightPuzzleNode(EightPuzzle.orderedEightPuzzle());
    }
}
