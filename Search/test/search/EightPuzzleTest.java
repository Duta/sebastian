package search;

import java.util.Stack;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.testng.AssertJUnit.assertEquals;

import eightpuzzle.EightPuzzle;
import eightpuzzle.PuzzleMove;
import eightpuzzle.search.EightPuzzleNode;

public class EightPuzzleTest {
	private EightPuzzleNode start, goal;
	
	@BeforeClass
	public void initStates() {
		start = new EightPuzzleNode(EightPuzzle.randomEightPuzzle());
		goal = new EightPuzzleNode(EightPuzzle.orderedEightPuzzle());

	}
	
	@Test
	public void testBreadthFirst() {
		Stack<EightPuzzleNode> path = null;
		
		try {
			path = Search.search(start, goal, new BreadthFirstFrontier<EightPuzzleNode, PuzzleMove>());
		} catch (PathNotFoundException e) {
			e.printStackTrace();
		}
		
		EightPuzzleNode node = path.pop();
		assertEquals(node, start);
		
		while(!path.empty()) {
			node = node.applyAction(path.pop().getAction());
		}
		
		assertEquals(node, goal);
	}
	
	@Test
	public void testDepthFirst() {
		Stack<EightPuzzleNode> path = null;
		
		try {
			path = Search.search(start, goal, new DepthFirstFrontier<EightPuzzleNode, PuzzleMove>());
		} catch (PathNotFoundException e) {
			e.printStackTrace();
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
			path = Search.search(start, goal, new AStarFrontier<EightPuzzleNode, PuzzleMove>(goal));
		} catch (PathNotFoundException e) {
			e.printStackTrace();
		}
		
		EightPuzzleNode node = path.pop();
		assertEquals(node, start);
		
		while(!path.empty()) {
			node = node.applyAction(path.pop().getAction());
		}
		
		assertEquals(node, goal);
	}
}
