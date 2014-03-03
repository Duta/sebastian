package sebastiangoesforth;

import java.util.ArrayList;
import java.util.List;
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

		int x1 = Util.RGEN.nextInt(grid.getWidth());
		int x2 = Util.RGEN.nextInt(grid.getWidth());
		int y1 = Util.RGEN.nextInt(grid.getHeight());
		int y2 = Util.RGEN.nextInt(grid.getHeight());

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
		
		List<PathAction> pathActions = new ArrayList<PathAction>();
		
		GridDirection direction = GridDirection.UP;
		
		while(!path.empty()) {
			GridNode node = path.pop();
			GridDirection action = node.getAction();
			switch(action) {
			case DOWN:
				switch(direction) {
				case DOWN:
					pathActions.add(PathAction.STRAIGHT);
					break;
					
				case LEFT:
					pathActions.add(PathAction.LEFT);
					break;
					
				case RIGHT:
					pathActions.add(PathAction.RIGHT);
					break;
					
				case UP:
					pathActions.add(PathAction.U_TURN);
					break;
				}
				break;
				
			case LEFT:
				switch(direction) {
				case DOWN:
					pathActions.add(PathAction.RIGHT);
					break;
					
				case LEFT:
					pathActions.add(PathAction.STRAIGHT);
					break;
					
				case RIGHT:
					pathActions.add(PathAction.U_TURN);
					break;
					
				case UP:
					pathActions.add(PathAction.LEFT);
					break;
				}
				break;
				
			case RIGHT:
				switch(direction) {
				case DOWN:
					pathActions.add(PathAction.LEFT);
					break;
					
				case LEFT:
					pathActions.add(PathAction.U_TURN);
					break;
					
				case RIGHT:
					pathActions.add(PathAction.STRAIGHT);
					break;
					
				case UP:
					pathActions.add(PathAction.RIGHT);
					break;
				}
				break;
				
			case UP:
				switch(direction) {
				case DOWN:
					pathActions.add(PathAction.U_TURN);
					break;
					
				case LEFT:
					pathActions.add(PathAction.RIGHT);
					break;
					
				case RIGHT:
					pathActions.add(PathAction.LEFT);
					break;
					
				case UP:
					pathActions.add(PathAction.STRAIGHT);
					break;
				}
				break;
			}
		}
		
		Mover mover = new Mover(
				RobotInfo.SEBASTIAN.getDifferentialPilot(),
				new LightSensor(SensorPort.S4),
				new LightSensor(SensorPort.S1),
				pathActions);
		mover.run();
	}
}
