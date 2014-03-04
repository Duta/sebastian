package sebastiangoesforth;

import java.util.Stack;

import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import grid.Grid;
import grid.GridDirection;
import grid.GridState;
import grid.search.GridNode;
import search.AStarFrontier;
import search.PathNotFoundException;
import search.Search;
import util.RobotInfo;
import util.Util;

public class Main {
	public static void main(String[] args) {
		Util.waitForStart();
		
		Grid grid = Grid.defaultGrid();

		int x1 = 9;
		int y1 = 0;
		int x2 = 0;
		int y2 = 6;

		GridNode start = new GridNode(new GridState(grid, x1, y1));
		GridNode goal = new GridNode(new GridState(grid, x2, y2));
		
		Stack<GridNode> path = null;
		try {
			path = Search.search(
					start,
					goal,
					new AStarFrontier<GridNode, GridDirection>(goal));
		} catch (PathNotFoundException e) {
			System.err.println("No path found");
			return;
		}
		
		path.pop();
		
		Mover mover = new Mover(
				RobotInfo.SEBASTIAN.getDifferentialPilot(),
				new LightSensor(SensorPort.S4),
				new LightSensor(SensorPort.S1),
				path);
		mover.run();
	}
}
