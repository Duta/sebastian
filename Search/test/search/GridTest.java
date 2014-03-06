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
    private Grid grid, sillyGrid;
    private GridNode start, goal, sillyStart, sillyGoal;

    @BeforeClass
    public void initStates() {
        grid = Grid.unblockedGrid();
        start = new GridNode(new GridState(grid, 2, 3));
        goal = new GridNode(new GridState(grid, 6, 2));
        
        sillyGrid = Grid.fromAsciiArt(new String[] {
                "+-+-+-+ +-+",
                "| | |   | |",
                "+-+-+ + +-+",
                "| | |   | |",
                "+-+-+-+-+-+",
        });
        sillyStart = new GridNode(new GridState(sillyGrid, 1, 1));
        sillyGoal = new GridNode(new GridState(sillyGrid, 3, 1));


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
    
    @Test(expectedExceptions=PathNotFoundException.class)
    public void testBreadthFirstFail() throws PathNotFoundException {
        Search.search(sillyStart, sillyGoal, new BreadthFirstFrontier<GridState, GridNode, GridDirection>());
    }
    
    @Test(expectedExceptions=PathNotFoundException.class)
    public void testDepthFirstFail() throws PathNotFoundException {
        Search.search(sillyStart, sillyGoal, new DepthFirstFrontier<GridState, GridNode, GridDirection>());
    }
    
    @Test(expectedExceptions=PathNotFoundException.class)
    public void testAStarFail() throws PathNotFoundException {
        Search.search(sillyStart, sillyGoal, new AStarFrontier<GridState, GridNode, GridDirection>(sillyGoal));
    }
}
