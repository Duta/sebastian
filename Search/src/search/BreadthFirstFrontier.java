package search;

import java.util.ArrayList;

import search.interfaces.Frontier;
import search.interfaces.State;

public class BreadthFirstFrontier<StateType extends State<StateType, ActionType>, ActionType> implements Frontier<StateType, ActionType> {
	private ArrayList<StateType> frontierList;
	
	public BreadthFirstFrontier() {
		frontierList = new ArrayList<StateType>();
	}
	
	public void push(StateType state) {
		frontierList.add(state);
	}

	@Override
	public StateType pop() {
		StateType state = frontierList.get(0);
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
