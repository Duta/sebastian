package search.interfaces;


public interface Frontier<StateType extends State<StateType, ActionType>, ActionType> {
	public void push(StateType state);
	public StateType pop();
	public boolean empty();
	public int size();
}
