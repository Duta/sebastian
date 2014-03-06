package part1.grid.search;

import part1.grid.GridDirection;
import part1.grid.GridState;
import part1.grid.Junction;

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
        Junction junction = state.getJunction();
        Junction goalJunction = goal.state.getJunction();
        return Math.abs(goalJunction.getX() - junction.getX())
            + Math.abs(goalJunction.getY() - junction.getY());
    }

    @Override
    public GridNode applyAction(GridDirection action) {
        return state.validDirection(action)
            ? new GridNode(state.applyDir(action), this, action)
            : this;
    }
}
