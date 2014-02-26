package grid.search;

import grid.GridDirection;
import search.AStarFrontier;
import search.interfaces.Frontier;

public class GridAStar extends GridSearch {
	public static void main(String[] args) {
		new GridAStar().search();
	}

	@Override
	protected Frontier<GridNode, GridDirection> getFrontier(GridNode goal) {
		return new AStarFrontier<GridNode, GridDirection>(goal);
	}
}
