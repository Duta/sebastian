package search.interfaces;

import java.util.List;

public abstract class Node<NodeT, ActionT> {
	protected NodeT parent;
	protected ActionT action;
	
	public NodeT getParent() {
		return parent;
	}
	
	public ActionT getAction() {
		return action;
	}
	
	public abstract List<NodeT> explore();
	public abstract boolean equals(Object other);
	public abstract int heuristic(NodeT goal);
}
