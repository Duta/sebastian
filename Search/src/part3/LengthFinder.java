package part3;

import part1.grid.GridDirection;
import part1.grid.search.GridNode;
import search.AStarFrontier;
import search.SearchRunner;
import search.interfaces.Frontier;

import java.util.Stack;

/**
 * Calculates the length of the
 * path between two grid states.
 */
public class LengthFinder extends SearchRunner<GridNode, GridDirection> {
    private final GridStatePair pair;

    public LengthFinder(GridStatePair pair) {
        this.pair = pair;
    }

    public int calculate() {
        if(!pair.isLengthKnown()) {
            search();
        }
        return pair.getLength();
    }

    @Override
    protected GridNode createStart() {
        return new GridNode(pair.getStateA());
    }

    @Override
    protected GridNode createGoal() {
        return new GridNode(pair.getStateB());
    }

    @Override
    protected Frontier<GridNode, GridDirection> createFrontier(GridNode goal) {
        return new AStarFrontier<GridNode, GridDirection>(goal);
    }

    @Override
    protected void preSearchAction(GridNode start, GridNode goal) {}

    @Override
    protected void pathNotFoundAction(GridNode start, GridNode goal) {
        pair.setLength(Integer.MAX_VALUE);
    }

    @Override
    protected void processPathAction(GridNode start, GridNode goal, Stack<GridNode> path) {
        pair.setLength(path.size() - 1);
    }
}
