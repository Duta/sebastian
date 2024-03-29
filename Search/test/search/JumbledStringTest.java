package search;

import java.util.Stack;

import part1.jumbledstring.CharacterSwap;
import part1.jumbledstring.JumbledString;
import part1.jumbledstring.search.JumbledStringNode;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class JumbledStringTest {
    private JumbledStringNode start, goal, badGoal;

    @BeforeClass
    public void initStates() {
        start = new JumbledStringNode(new JumbledString("ortbo"));
        goal = new JumbledStringNode(new JumbledString("robot"));
        
        badGoal = new JumbledStringNode(new JumbledString("robop"));

    }

    @Test
    public void testBreadthFirst() {
        Stack<JumbledStringNode> path = null;

        try {
            path = Search.search(start, goal, new BreadthFirstFrontier<JumbledString, JumbledStringNode, CharacterSwap>());
        } catch(PathNotFoundException e) {
            fail();
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
            path = Search.search(start, goal, new DepthFirstFrontier<JumbledString, JumbledStringNode, CharacterSwap>());
        } catch(PathNotFoundException e) {
            fail();
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
            path = Search.search(start, goal, new AStarFrontier<JumbledString, JumbledStringNode, CharacterSwap>(goal));
        } catch(PathNotFoundException e) {
            fail();
        }

        JumbledStringNode node = path.pop();
        assertEquals(node, start);

        while(!path.empty()) {
            node = node.applyAction(path.pop().getAction());
        }

        assertEquals(node, goal);
    }
    
    @Test(expectedExceptions=PathNotFoundException.class)
    public void testBreadthFirstFail() throws PathNotFoundException {
        Search.search(start, badGoal, new BreadthFirstFrontier<JumbledString, JumbledStringNode, CharacterSwap>());
    }
    
    @Test(expectedExceptions=PathNotFoundException.class)
    public void testDepthFirstFail() throws PathNotFoundException {
        Search.search(start, badGoal, new DepthFirstFrontier<JumbledString, JumbledStringNode, CharacterSwap>());
    }
    
    @Test(expectedExceptions=PathNotFoundException.class)
    public void testAStarFail() throws PathNotFoundException {
        Search.search(start, badGoal, new AStarFrontier<JumbledString, JumbledStringNode, CharacterSwap>(badGoal));
    }
 
}
