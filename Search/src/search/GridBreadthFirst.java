package search;

import java.util.Random;
import java.util.Stack;

import grid.Grid;
import grid.GridDir;
import grid.GridState;
import grid.SearchNode;

public class GridBreadthFirst {
	public static void main(String[] args) {
		Grid g = new Grid(6, 6);
		Random r = new Random();

		int x1 = r.nextInt(6);
		int x2 = r.nextInt(6);
		int y1 = r.nextInt(6);
		int y2 = r.nextInt(6);

		SearchNode start = new SearchNode(new GridState(g, x1, y1));
		SearchNode goal = new SearchNode(new GridState(g, y1, y2));

		System.out.println("Start: (" + x1 + ", " + y1 + ")");
		System.out.println("End: (" + x2 + ", " + y2 + ")");

		Stack<SearchNode> path = Search.search(start, goal,
				new BreadthFirstFrontier<SearchNode, GridDir>());

		if (path != null) {

			System.out.println("Path (Length " + path.size() + "):");
			while (true) {
				if (path.empty()) {
					break;
				}

				SearchNode node = path.pop();
				System.out.println(node.getAction());
			}
		}
	}
}
