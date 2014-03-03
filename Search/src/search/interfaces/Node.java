package search.interfaces;

import java.util.List;

/**
 * This interface defines the search nodes for the search algorithm.
 *
 * @param <NodeT> The type of node.
 * @param <ActionT> The actions for the node.
 */
public abstract class Node<NodeT, ActionT> {
	protected NodeT parent;
	protected ActionT action;
	
	/**
	 * Returns the parent node (null if no parent).
	 */
	public NodeT getParent() {
		return parent;
	}
	
	/**
	 * Returns the action performed on the parent to get to this node
	 * (null if no parent).
	 */
	public ActionT getAction() {
		return action;
	}
	
	/**
	 * Returns a list of all the nodes created after expanding this one.
	 */
	public abstract List<NodeT> explore();
	
	/**
	 * Returns true if two nodes are for the same state.
	 */
	public abstract boolean equals(Object other);
	
	/**
	 * Calculates the heuristic for the current node.
	 * @param goal The goal node
	 */
	public abstract int heuristic(NodeT goal);
	
	/**
	 * Returns a new node with the specified action applied
	 */
	public abstract NodeT applyAction(ActionT action);
}
