package part1.grid.search;

import part1.grid.GridDirection;
import search.DepthFirstFrontier;
import search.interfaces.Frontier;

public class GridDepthFirst extends GridSearch {
	public static void main(String[] args) {
		new GridAStar().search();
	}

	@Override
	protected Frontier<GridNode, GridDirection> createFrontier(GridNode goal) {
		return new DepthFirstFrontier<GridNode, GridDirection>();
	}
}
