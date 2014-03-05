package part1.eightpuzzle.search;

import part1.eightpuzzle.EightPuzzle;
import part1.eightpuzzle.PuzzleMove;
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
