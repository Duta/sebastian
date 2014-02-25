package part2;

import java.util.Random;
import java.util.Stack;

import lejos.nxt.comm.RConsole;
import search.AStarFrontier;
import search.Search;
import grid.Grid;
import grid.GridDir;
import grid.GridState;
import grid.SearchNode;

public class Main {
	public static void main(String[] args) {
		RConsole.openUSB(0);
		
		int width = 11;
		int height = 7;
		
		Grid g = new Grid(width, height);
		Random r = new Random();

		int x1 = r.nextInt(width);
		int x2 = r.nextInt(width);
		int y1 = r.nextInt(height);
		int y2 = r.nextInt(height);

		SearchNode start = new SearchNode(new GridState(g, x1, y1));
		SearchNode goal = new SearchNode(new GridState(g, x2, y2));

		RConsole.println("Start: (" + x1 + ", " + y1 + ")");
		RConsole.println("End: (" + x2 + ", " + y2 + ")");

		Stack<SearchNode> path = Search.search(start, goal,
				new AStarFrontier<SearchNode, GridDir>(goal));

		if (path != null) {
			path.pop();

			RConsole.println("Path (Length " + path.size() + "):");
			while (true) {
				if (path.empty()) {
					break;
				}

				SearchNode node = path.pop();
				RConsole.println(node.getAction().toString());
			}
		}
	}
}
