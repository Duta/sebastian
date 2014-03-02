package search;

import java.util.ArrayList;
import java.util.List;

import search.interfaces.Frontier;
import search.interfaces.Node;

/**
 * This frontier implements A* search.
 * It inserts nodes ordered by their heuristic.
 */
public class AStarFrontier<NodeT extends Node<NodeT, ActionT>, ActionT>
		implements Frontier<NodeT, ActionT> {
	private NodeT goal;
	private List<NodeT> list;
	
	public AStarFrontier(NodeT goal) {
		this.goal = goal;
		this.list = new ArrayList<NodeT>();
	}
	
	@Override
	public void push(NodeT node) {
		int nodeHeuristic = heuristic(node);
		for(int i = 0; i < list.size(); i++) {
			if(heuristic(list.get(i)) > nodeHeuristic) {
				list.add(i, node);
				return;
			}
		}
		list.add(node);
	}
	
	@Override
	public NodeT pop() {
		NodeT state = list.get(0);
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
	
	private int heuristic(NodeT node) {
		return node.heuristic(goal) + totalPathCost(node);
	}
	
	private int totalPathCost(NodeT node) {
		int pathCost = 0;
		while(node.getParent() != null) {
			pathCost++;
			node = node.getParent();
		}
		return pathCost;
	}
}