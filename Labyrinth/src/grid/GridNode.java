package grid;

import search.interfaces.Node;

/**
 * Represents a node in the grid problem.
 * Used for searching the grid problem.
 */
public class GridNode
        extends Node<GridState, GridNode, GridDirection> {
    /**
     * Creates a node for the given state,
     * which has no parent or action.
     *
     * @param state the state to store in the node
     */
    public GridNode(GridState gridState) {
        super(gridState);
    }

    /**
     * Creates a node for the given state,
     * with the given parent and action.
     *
     * @param state the state to store in the node
     * @param parent the parent node
     * @param action the action taken upon the parent
     */
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
