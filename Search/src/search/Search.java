package search;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import search.interfaces.Frontier;
import search.interfaces.Node;

public class Search {
	/**
	 * Performs a search algorithm, returning a path from
     * start to goal, using the provided frontier type.
	 * 
	 * @param start the start node.
	 * @param goal the goal node.
	 * @param frontier the frontier type, which
     *                 defines the algorithm used
	 * @return a stack containing the nodes in
     *         the path - the top is the start
	 *         node, the bottom is the goal
	 * @throws PathNotFoundException if a path cannot be found
	 */
	public static
	<
        StateT,
        NodeT extends Node<StateT, NodeT, ActionT>,
	    FrontierT extends Frontier<StateT, NodeT, ActionT>,
	    ActionT
    >
	Stack<NodeT> search(NodeT start, NodeT goal, FrontierT frontier) throws PathNotFoundException {
        if(!frontier.empty()) {
            throw new IllegalArgumentException("frontier must be empty");
        }
		
		List<NodeT> explored = new ArrayList<NodeT>();
		frontier.push(start);
		while(!frontier.empty()) {
			NodeT node = frontier.pop();
			if(node == null) {
				throw new IllegalArgumentException("null node encountered");
			}
			if(node.equals(goal)) {
				Stack<NodeT> stack = new Stack<NodeT>();
				while(node != null) {
					stack.push(node);
					node = node.getParent();
				}
				return stack;
			}
			if(!explored.contains(node)) {
				explored.add(node);
			}
			for(NodeT successor : node.explore()) {
				if(!explored.contains(successor)) {
					frontier.push(successor);
				}
			}
		}
		throw new PathNotFoundException();
	}
}
