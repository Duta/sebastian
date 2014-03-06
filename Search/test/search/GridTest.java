package search;

import java.util.Stack;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import part1.grid.Grid;
import part1.grid.GridDirection;
import part1.grid.GridState;
import part1.grid.search.GridNode;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class GridTest {
	private Grid grid;
    private GridNode start, goal;

    @BeforeClass
    public void initStates() {
    	grid = Grid.unblockedGrid();
    	start = new GridNode(new GridState(grid, 2, 3));
    	goal = new GridNode(new GridState(grid, 6, 2));

    }

    @Test
    public void testBreadthFirst() {
        Stack<GridNode> path = null;

        try {
            path = Search.search(start, goal, new BreadthFirstFrontier<GridState, GridNode, GridDirection>());
        } catch (PathNotFoundException e) {
            fail();
        }

        GridNode node = path.pop();
        assertEquals(node, start);

        while(!path.empty()) {
            node = node.applyAction(path.pop().getAction());
        }

        assertEquals(node, goal);
    }

    @Test
    public void testDepthFirst() {
        Stack<GridNode> path = null;

        try {
            path = Search.search(start, goal, new DepthFirstFrontier<GridState, GridNode, GridDirection>());
        } catch (PathNotFoundException e) {
            fail();
        }

        GridNode node = path.pop();
        assertEquals(node, start);

        while(!path.empty()) {
            node = node.applyAction(path.pop().getAction());
        }

        assertEquals(node, goal);
    }

    @Test
    public void testAStar() {
        Stack<GridNode> path = null;

        try {
            path = Search.search(start, goal, new AStarFrontier<GridState, GridNode, GridDirection>(goal));
        } catch (PathNotFoundException e) {
            fail();
        }

        GridNode node = path.pop();
        assertEquals(node, start);

        while(!path.empty()) {
            node = node.applyAction(path.pop().getAction());
        }

        assertEquals(node, goal);
    }
}
