package grid;

import java.util.ArrayList;

import search.interfaces.Node;

public class SearchNode extends Node<SearchNode, GridDir> {
	private GridState gridState;
	
	private int cost;

	public SearchNode(GridState gridState) {
		this.gridState = gridState;
		this.parent = null;
		this.action = null;
		
		cost = 0;

	}

	public SearchNode(GridState gridState, SearchNode parent, GridDir action) {
		this.gridState = gridState;
		this.parent = parent;
		this.action = action;
		
		cost = parent.cost + 1;
	}

	@Override
	public ArrayList<SearchNode> explore() {
		ArrayList<SearchNode> successors = new ArrayList<SearchNode>();

		for (GridDir dir : GridDir.values()) {
			if (gridState.validDirection(dir)) {
				successors.add(new SearchNode(gridState.applyDir(dir), this,
						dir));
			}
		}

		return successors;
	}

	@Override
	public boolean equals(Object other) {/*
		if (other instanceof SearchNode) {
			return gridState.equals(((SearchNode) other).gridState)
					&& action.equals(((SearchNode) other).action);
		}

		return false;*/
		
		return stateEquals(other);
	}

	@Override
	public boolean stateEquals(Object other) {
		return other instanceof SearchNode
			? gridState.equals(((SearchNode) other).gridState)
			: false;
	}
	
	@Override
	public int heuristic(SearchNode goal) {
		Junction goalJunction = goal.gridState.getJunction();
		Junction junction = gridState.getJunction();
		
		return Math.abs(goalJunction.getX() - junction.getX()) 
			 + Math.abs(goalJunction.getY() - junction.getY());

	}

	@Override
	public int totalPathCost() {
		return cost;
	}
}
