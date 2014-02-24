package grid;

import java.util.ArrayList;

import search.interfaces.Node;

public class SearchNode extends Node<SearchNode, GridDir> {
	private GridState gridState;
	
	public SearchNode(GridState gridState) {
		this(gridState, null, null);
	}
	
	public SearchNode(GridState gridState, SearchNode parent, GridDir action) {
		this.gridState = gridState;
		this.parent = parent;
		this.action = action;
	}

	@Override
	public ArrayList<SearchNode> explore() {
		ArrayList<SearchNode> successors = new ArrayList<SearchNode>();
		
		for(GridDir dir : GridDir.values()) {
			if(gridState.validDirection(dir)) {
				successors.add(new SearchNode(gridState.applyDir(dir), this, dir));
			}
		}
		
		return successors;
	}

	@Override
	public boolean equals(Object other) {
		if(other instanceof SearchNode) {
			return gridState.equals(((SearchNode)other).gridState) && action.equals(((SearchNode)other).action);
		}
		
		return false;
	}
	
	@Override
	public boolean stateEquals(Object other) {
		if(other instanceof SearchNode) {
			return gridState.equals(((SearchNode)other).gridState);
		}
		
		return false;
	}

	@Override
	public int heuristic(SearchNode goal) {
		return 0;
	}

}
