package part1.grid.search;

import part1.grid.GridDirection;
import part1.grid.GridState;
import part1.grid.Junction;

import java.util.ArrayList;
import java.util.List;

import search.interfaces.Node;

/**
 * Represents a node in the grid problem.
 * Used for searching the grid problem.
 */
public class GridNode extends Node<GridNode, GridDirection> {
	private GridState gridState;

	public GridNode(GridState gridState) {
		this(gridState, null, null);
	}

	public GridNode(GridState gridState, GridNode parent, GridDirection action) {
		this.gridState = gridState;
		this.parent = parent;
		this.action = action;
	}

	@Override
	public List<GridNode> explore() {
		List<GridNode> successors = new ArrayList<GridNode>();
		for (GridDirection direction : GridDirection.values()) {
			if (gridState.validDirection(direction)) {
				GridState successorState = gridState.applyDir(direction);
				successors.add(new GridNode(successorState, this, direction));
			}
		}
		return successors;
	}

	@Override
	public boolean equals(Object other) {
		return other instanceof GridNode
			&& gridState.equals(((GridNode) other).gridState);
	}

	@Override
	public int heuristic(GridNode goal) {
		Junction junction = gridState.getJunction();
		Junction goalJunction = goal.gridState.getJunction();
		return Math.abs(goalJunction.getX() - junction.getX())
			+ Math.abs(goalJunction.getY() - junction.getY());
	}
	
	@Override
	public GridNode applyAction(GridDirection action) {
		if(!gridState.validDirection(action)) {
			return new GridNode(gridState, parent, this.action);
		} else {
			return new GridNode(gridState.applyDir(action), this, action);
		}
	}

    @Override
    public String toString() {
        int x = gridState.getJunction().getX();
        int y = gridState.getJunction().getY();
        return x + ", " + y;
    }
}
