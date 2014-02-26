package grid.search;

import grid.GridDirection;
import grid.GridNode;
import search.DepthFirstFrontier;
import search.interfaces.Frontier;

public class GridDepthFirst extends GridSearch {
	public static void main(String[] args) {
		new GridAStar().search();
	}

	@Override
	protected Frontier<GridNode, GridDirection> getFrontier(GridNode goal) {
		return new DepthFirstFrontier<GridNode, GridDirection>();
	}
}
