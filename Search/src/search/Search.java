package search;

import java.util.ArrayList;
import java.util.Stack;

import search.interfaces.Frontier;
import search.interfaces.Node;

public class Search {
	public static 
		<NodeType extends Node<NodeType, ActionType>, 
		FrontierType extends Frontier<NodeType, ActionType>, 
		ActionType> 
		Stack<NodeType> search(NodeType start, NodeType goal, FrontierType frontier) {

		ArrayList<NodeType> explored = new ArrayList<NodeType>();

		frontier.push(start);

		while (true) {
			System.out.println("Frontier: " + frontier.size() + ", Explored: "
					+ explored.size());

			if(frontier.empty()) {
				return null;
			}
			
			NodeType node = frontier.pop();

			if (node == null) {
				return null;
			}

			if (node.stateEquals(goal)) {
				Stack<NodeType> stack = new Stack<NodeType>();

				do {
					stack.push(node);
					node = node.getParent();
				} while (node != null);

				return stack;
			}

			if (!explored.contains(node)) {
				explored.add(node);
			}

			for (NodeType newnode : node.explore()) {
				if (!explored.contains(newnode)) {
					frontier.push(newnode);
				}
			}
		}
	}
}
