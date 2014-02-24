package search.interfaces;

import java.util.ArrayList;

public abstract class Node<NodeType, ActionType> {
	protected NodeType parent;
	protected ActionType action;
	
	public NodeType getParent() {
		return parent;
	}
	
	public ActionType getAction() {
		return action;
	}
	
	public abstract ArrayList<NodeType> explore();
	public abstract boolean equals(Object other);
	public abstract boolean stateEquals(Object other);
	public abstract int heuristic(NodeType goal);

}
