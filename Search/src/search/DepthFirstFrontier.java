package search;

import java.util.Stack;

import search.interfaces.Frontier;
import search.interfaces.Node;

public class DepthFirstFrontier<NodeType extends Node<NodeType, ActionType>, ActionType> implements Frontier<NodeType, ActionType> {
	private Stack<NodeType> frontierList;

	public DepthFirstFrontier() {
		frontierList = new Stack<NodeType>();
	}

	public void push(NodeType state) {
		frontierList.push(state);
	}

	@Override
	public NodeType pop() {
		NodeType state = frontierList.peek();
		frontierList.pop();
		return state;
	}

	@Override
	public boolean empty() {
		return frontierList.isEmpty();
	}

	@Override
	public int size() {
		return frontierList.size();
	}

}
