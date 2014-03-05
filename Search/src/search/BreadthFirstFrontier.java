package search;

import java.util.ArrayList;

import search.interfaces.Frontier;
import search.interfaces.Node;

/**
 * This frontier implements Breadth First Search.
 * It acts like a FIFO queue.
 */
public class BreadthFirstFrontier<NodeType extends Node<NodeType, ActionType>, ActionType> implements Frontier<NodeType, ActionType> {
	private ArrayList<NodeType> frontierList;
	
	public BreadthFirstFrontier() {
		frontierList = new ArrayList<NodeType>();
	}
	
	public void push(NodeType state) {
		frontierList.add(state);
	}

	@Override
	public NodeType pop() {
		NodeType state = frontierList.get(0);
		frontierList.remove(0);
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
