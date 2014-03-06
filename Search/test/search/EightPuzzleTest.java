package search;

import java.util.Stack;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;
import part1.eightpuzzle.EightPuzzle;
import part1.eightpuzzle.PuzzleMove;
import part1.eightpuzzle.search.EightPuzzleNode;

public class EightPuzzleTest {
    private EightPuzzleNode start, goal, badGoal;

    @BeforeClass
    public void initStates() {
        start = new EightPuzzleNode(EightPuzzle.randomEightPuzzle());
        goal = new EightPuzzleNode(EightPuzzle.orderedEightPuzzle());
        badGoal = new EightPuzzleNode(EightPuzzle.impossibleEightPuzzle());

    }

    @Test
    public void testBreadthFirst() {
        Stack<EightPuzzleNode> path = null;

        try {
            path = Search.search(start, goal, new BreadthFirstFrontier<EightPuzzle, EightPuzzleNode, PuzzleMove>());
        } catch (PathNotFoundException e) {
            fail();
        }

        EightPuzzleNode node = path.pop();
        assertEquals(node, start);

        while(!path.empty()) {
            node = node.applyAction(path.pop().getAction());
        }

        assertEquals(node, goal);
    }

    /* Comment this test out to run in a reasonable time D: */
    @Test
    public void testDepthFirst() {
        Stack<EightPuzzleNode> path = null;

        try {
            path = Search.search(start, goal, new DepthFirstFrontier<EightPuzzle, EightPuzzleNode, PuzzleMove>());
        } catch (PathNotFoundException e) {
            fail();
        }

        EightPuzzleNode node = path.pop();
        assertEquals(node, start);

        while(!path.empty()) {
            node = node.applyAction(path.pop().getAction());
        }

        assertEquals(node, goal);
    }

    @Test
    public void testAStar() {
        Stack<EightPuzzleNode> path = null;

        try {
            path = Search.search(start, goal, new AStarFrontier<EightPuzzle, EightPuzzleNode, PuzzleMove>(goal));
        } catch (PathNotFoundException e) {
            fail();
        }

        EightPuzzleNode node = path.pop();
        assertEquals(node, start);

        while(!path.empty()) {
            node = node.applyAction(path.pop().getAction());
        }

        assertEquals(node, goal);
    }
    
    /* Uncomment for unreasonably long tests */
    /*
    @Test(expectedExceptions=PathNotFoundException.class)
    public void testBreadthFirstFail() throws PathNotFoundException {
        Search.search(start, badGoal, new BreadthFirstFrontier<EightPuzzle, EightPuzzleNode, PuzzleMove>());
    }
    
    @Test(expectedExceptions=PathNotFoundException.class)
    public void testDepthFirstFail() throws PathNotFoundException {
        Search.search(start, badGoal, new DepthFirstFrontier<EightPuzzle, EightPuzzleNode, PuzzleMove>());
    }
    
    @Test(expectedExceptions=PathNotFoundException.class)
    public void testAStarFail() throws PathNotFoundException {
        Search.search(start, badGoal, new AStarFrontier<EightPuzzle, EightPuzzleNode, PuzzleMove>(badGoal));
    }
    */
}
