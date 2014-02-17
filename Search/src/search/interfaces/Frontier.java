package search.interfaces;


public interface Frontier<StateType extends State<StateType>> {
	public void push(StateType state);
	public StateType pop();
	public boolean empty();
	public int size();
}
