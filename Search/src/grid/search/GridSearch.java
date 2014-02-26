package grid.search;

import grid.Grid;
import grid.GridDirection;
import grid.GridState;

import java.util.Random;
import java.util.Stack;

import lejos.nxt.comm.RConsole;
import search.PathNotFoundException;
import search.Search;
import search.interfaces.Frontier;

public abstract class GridSearch {
	protected GridSearch() {}
	
	public void search() {
		search(11, 7);
	}

	public void search(int width, int height) {
		RConsole.openUSB(0);
		
		Grid g = new Grid(width, height);
		Random r = new Random();

		int x1 = r.nextInt(width);
		int x2 = r.nextInt(width);
		int y1 = r.nextInt(height);
		int y2 = r.nextInt(height);

		GridNode start = new GridNode(new GridState(g, x1, y1));
		GridNode goal = new GridNode(new GridState(g, x2, y2));

		RConsole.println("Start: (" + x1 + ", " + y1 + ")");
		RConsole.println("End: (" + x2 + ", " + y2 + ")");

		Stack<GridNode> path;
		try {
			path = Search.search(start, goal, getFrontier(goal));
		} catch (PathNotFoundException e) {
			RConsole.println("No path found.");
			return;
		}

		RConsole.println("Path (Length " + path.size() + "):");
		while (!path.empty()) {
			GridNode node = path.pop();
			GridDirection action = node.getAction();
			RConsole.println(action == null ? "null" : action.toString());
		}
	}
	
	protected abstract Frontier<GridNode, GridDirection> getFrontier(GridNode goal);
}
