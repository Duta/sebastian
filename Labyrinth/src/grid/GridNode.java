package grid;

import search.interfaces.Node;

/**
 * Represents a node in the grid problem.
 * Used for searching the grid problem.
 */
public class GridNode
        extends Node<GridState, GridNode, GridDirection> {
    public GridNode(GridState gridState) {
        super(gridState);
    }

    public GridNode(GridState gridState, GridNode parent,
            GridDirection action) {
        super(gridState, parent, action);
    }

    @Override
    public GridDirection[] allActions() {
        return GridDirection.values();
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof GridNode
            && state.equals(((GridNode) other).state);
    }

    @Override
    public int heuristic(GridNode goal) {
        return Math.abs(goal.state.getX() - state.getX())
            +  Math.abs(goal.state.getY() - state.getY());
    }

    @Override
    public GridNode applyAction(GridDirection action) {
        return state.canGo(action)
            ? new GridNode(state.applyDir(action), this, action)
            : this;
    }
}
