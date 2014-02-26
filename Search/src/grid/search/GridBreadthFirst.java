package grid.search;

import grid.GridDirection;
import grid.GridNode;
import search.BreadthFirstFrontier;
import search.interfaces.Frontier;

public class GridBreadthFirst extends GridSearch {
	public static void main(String[] args) {
		new GridAStar().search();
	}

	@Override
	protected Frontier<GridNode, GridDirection> getFrontier(GridNode goal) {
		return new BreadthFirstFrontier<GridNode, GridDirection>();
	}
}