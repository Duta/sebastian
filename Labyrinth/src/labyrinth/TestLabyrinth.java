package labyrinth;

import java.util.List;
import java.util.Stack;

import rp.robotics.mapping.LocalisationUtils;
import util.Util;
import grid.GridNode;
import grid.GridSearcher;
import grid.GridState;
import localisation.Localiser;
import localisation.TestActionModel;
import localisation.TestGrid;
import localisation.TestSensorModel;

public class TestLabyrinth {
	private TestGrid grid;
	private List<GridState> requiredStates;

	public TestLabyrinth(TestGrid grid, List<GridState> states) {
		this.grid = grid;
		this.requiredStates = states;
	}

	public void run() {
		GridState pos = getAssumedPosition();
		
		System.out.println("Location Found: " + pos.getX() + ", " + pos.getY());
		System.out.println("Actual Location: " + grid.getLocation().x + ", " + grid.getLocation().y);
		System.out.println();

		for (GridState requiredState : requiredStates) {
			move(pos, requiredState);
			pos = requiredState;
		}
	}

	private void move(GridState start, GridState goal) {
		GridSearcher searcher = new GridSearcher(
				grid,
				start.getX(), start.getY(),
				goal.getX(), goal.getY()) {
			
			@Override
			protected void processPathAction(GridNode start, GridNode goal,
					Stack<GridNode> path) {
				
				while(!path.empty()) {
					GridNode node = path.pop();
					System.out.println(node.getAction() + " -> " + node.getState().getX() + ", " + node.getState().getY());
				}
				
				System.out.println();
			}
		};
		
		searcher.search();
	}

	private GridState getAssumedPosition() {
		return Localiser.localise(
				grid, 
				new TestSensorModel(grid),
				new TestActionModel(grid), 
				true);
	}
	
	public static void main(String[] args) {
		TestGrid grid = new TestGrid(LocalisationUtils.create2014Map1());
		TestLabyrinth labyrinth = new TestLabyrinth(grid, Util.asList(
				new GridState(grid, 3, 6),
				new GridState(grid, 1, 2)));
		
		labyrinth.run();
	}
}
