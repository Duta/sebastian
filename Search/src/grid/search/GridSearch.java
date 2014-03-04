package grid.search;

import grid.Grid;
import grid.GridDirection;
import grid.GridState;

import java.util.Stack;

import search.PathNotFoundException;
import search.Search;
import search.interfaces.Frontier;
import util.Util;

public abstract class GridSearch {
	protected GridSearch() {}
	
	public void search() {
		Grid grid = Grid.unblockedGrid();

		int x1 = Util.RGEN.nextInt(grid.getWidth());
		int x2 = Util.RGEN.nextInt(grid.getWidth());
		int y1 = Util.RGEN.nextInt(grid.getHeight());
		int y2 = Util.RGEN.nextInt(grid.getHeight());

		GridNode start = new GridNode(new GridState(grid, x1, y1));
		GridNode goal = new GridNode(new GridState(grid, x2, y2));

		System.out.println("Start: (" + x1 + ", " + y1 + ")");
		System.out.println("End: (" + x2 + ", " + y2 + ")");

		Stack<GridNode> path;
		try {
			path = Search.search(start, goal, getFrontier(goal));
		} catch (PathNotFoundException e) {
			System.out.println("No path found.");
			return;
		}

		path.pop();
		System.out.println("Path (Length " + path.size() + "):");
		while(!path.empty()) {
			GridNode node = path.pop();
			GridDirection action = node.getAction();
			System.out.println(action);
		}
	}
	
	protected abstract Frontier<GridNode, GridDirection> getFrontier(GridNode goal);
}
