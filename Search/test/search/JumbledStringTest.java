package search;

import java.util.Stack;

import jumbledstring.CharacterSwap;
import jumbledstring.JumbledString;
import jumbledstring.search.JumbledStringNode;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;


public class JumbledStringTest {
	private JumbledStringNode start, goal;
	
	@BeforeClass
	public void initStates() {
		start = new JumbledStringNode(new JumbledString("ortbo"));
		goal = new JumbledStringNode(new JumbledString("robot"));

	}
	
	@Test
	public void testBreadthFirst() {
		Stack<JumbledStringNode> path = null;
		
		try {
			path = Search.search(start, goal, new BreadthFirstFrontier<JumbledStringNode, CharacterSwap>());
		} catch (PathNotFoundException e) {
			e.printStackTrace();
		}
		
		JumbledStringNode node = path.pop();
		assertEquals(node, start);
		
		while(!path.empty()) {
			node = node.applyAction(path.pop().getAction());
		}
		
		assertEquals(node, goal);
	}
	
	@Test
	public void testDepthFirst() {
		Stack<JumbledStringNode> path = null;
		
		try {
			path = Search.search(start, goal, new DepthFirstFrontier<JumbledStringNode, CharacterSwap>());
		} catch (PathNotFoundException e) {
			e.printStackTrace();
		}
		
		JumbledStringNode node = path.pop();
		assertEquals(node, start);
		
		while(!path.empty()) {
			node = node.applyAction(path.pop().getAction());
		}
		
		assertEquals(node, goal);
	}
	
	@Test
	public void testAStar() {
		Stack<JumbledStringNode> path = null;
		
		try {
			path = Search.search(start, goal, new AStarFrontier<JumbledStringNode, CharacterSwap>(goal));
		} catch (PathNotFoundException e) {
			e.printStackTrace();
		}
		
		JumbledStringNode node = path.pop();
		assertEquals(node, start);
		
		while(!path.empty()) {
			node = node.applyAction(path.pop().getAction());
		}
		
		assertEquals(node, goal);
	}
}
