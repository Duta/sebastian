package search.interfaces;


public interface Frontier<NodeType extends Node<NodeType, ActionType>, ActionType> {
	public void push(NodeType node);
	public NodeType pop();
	public boolean empty();
	public int size();
}
