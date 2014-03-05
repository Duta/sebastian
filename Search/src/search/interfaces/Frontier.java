package search.interfaces;

/**
 * This interface defines the frontier type for the search algorithm, and the
 * implementation of it decides which search algorithm to use. 
 *
 * @param <NodeT> The type of node we are searching over (implements Node)
 * @param <ActionT> The type of actions for the node.
 */
public interface Frontier<NodeT extends Node<NodeT, ActionT>, ActionT> {
	/**
	 * Insert a node into the frontier.
	 */
	public void push(NodeT node);
	
	/**
	 * Return the first item from the frontier, and remove it.
	 */
	public NodeT pop();
	
	/**
	 * Returns true if the frontier is empty.
	 */
	public boolean empty();
	
	/**
	 * Returns the number of nodes in the frontier.
	 */
	public int size();
}
