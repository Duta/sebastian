package search;

import java.util.ArrayList;
import java.util.List;

import search.interfaces.Frontier;
import search.interfaces.Node;

public class AStarFrontier<NodeType extends Node<NodeType, ActionType>, ActionType>
		implements Frontier<NodeType, ActionType> {
	private NodeType goal;
	private List<NodeType> list;

	public AStarFrontier(NodeType goal) {
		this.goal = goal;
		this.list = new ArrayList<NodeType>();
 	}
	
	@Override
	public void push(NodeType node) {
		int h = heuristic(node);
		
		for(int i = 0; i < list.size(); i++) {
			if(heuristic(list.get(i)) >  h) {
				list.add(i, node);
				return;
			}
		}
		list.add(node);
	}

	@Override
	public NodeType pop() {
		NodeType state = list.get(0);
		list.remove(0);
		return state;
	}

	@Override
	public boolean empty() {
		return list.isEmpty();

	}

	@Override
	public int size() {
		return list.size();
	}

	private int heuristic(NodeType node) {
		return node.heuristic(goal) + node.totalPathCost();
	}
}
