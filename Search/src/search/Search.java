package search;

import java.util.ArrayList;
import java.util.Stack;

import search.interfaces.Frontier;
import search.interfaces.State;

public class Search {
	public static 
		<StateType extends State<StateType>, 
		 FrontierType extends Frontier<StateType>> 
		Stack<StateType> search(StateType start, StateType goal, FrontierType frontier) {

		ArrayList<StateType> explored = new ArrayList<StateType>();

		frontier.push(start);

		while (true) {
			System.out.println("Frontier: " + frontier.size() + ", Explored: " + explored.size());
			
			StateType node = frontier.pop();

			if (node == null) {
				return null;
			}

			if (node.equals(goal)) {
				Stack<StateType> stack = new Stack<StateType>();

				do {
					stack.push(node);
					node = node.getParent();
				} while (node != null);

				return stack;
			}

			if (!explored.contains(node)) {
				explored.add(node);
			}

			for (StateType state : node.explore()) {
				if (!explored.contains(state)) {
					frontier.push(state);
				}
			}
		}
	}
}
