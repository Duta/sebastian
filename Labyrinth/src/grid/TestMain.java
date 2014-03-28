package grid;

import java.util.Stack;

import rp.robotics.mapping.LocalisationUtils;

/**
 * A simple program to test the new grid implementation.
 */
public class TestMain {
    public static void main(String[] args) {
        Grid grid = new Grid(LocalisationUtils.create2014Map1());
        int startX = 0;
        int startY = 0;
        int goalX = 9;
        int goalY = 4;
        GridSearcher searcher = new GridSearcher(grid, startX, startY, goalX, goalY) {
			@Override
			protected void processPathAction(GridNode start, GridNode goal,
					Stack<GridNode> path) {
				while(!path.empty()) {
					GridNode node = path.pop();
					System.out.println(node.getAction());
				}
			}
        };
        searcher.search();
    }
}
