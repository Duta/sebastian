package search;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import search.interfaces.Frontier;
import search.interfaces.Node;

public class Search {
	/**
	 * Performs a search algorithm, returning a path from start to goal, using
	 * the provided frontier type.
	 * 
	 * @param start The start node.
	 * @param goal The goal node.
	 * @param frontier The frontier type. This defines what algorithm is used.
	 * @return A stack containing the nodes in the path. The top is the start node,
	 * the bottom is the goal.
	 * @throws PathNotFoundException
	 */
	public static
	// Generics can get hard to format.
	<NodeT extends Node<NodeT, ActionT>, 
	FrontierT extends Frontier<NodeT, ActionT>,
	ActionT>
	Stack<NodeT> search(NodeT start, NodeT goal, FrontierT frontier) throws PathNotFoundException {
		List<NodeT> explored = new ArrayList<NodeT>();
		frontier.push(start);
		while (!frontier.empty()) {
			// System.out.println("Frontier: " + frontier.size() + ", Explored: " + explored.size());
			NodeT node = frontier.pop();
			if (node == null) {
				throw new IllegalArgumentException("null node encountered");
			}
			if (node.equals(goal)) {
				Stack<NodeT> stack = new Stack<NodeT>();
				while (node != null) {
					stack.push(node);
					node = node.getParent();
				}
				return stack;
			}
			if (!explored.contains(node)) {
				explored.add(node);
			}
			for (NodeT successor : node.explore()) {
				if (!explored.contains(successor)) {
					frontier.push(successor);
				}
			}
		}
		throw new PathNotFoundException();
	}
}
