package search.interfaces;

import java.util.ArrayList;

public abstract class State<StateType, ActionType> {
	protected StateType parent;
	protected ActionType action;
	
	public StateType getParent() {
		return parent;
	}
	
	public ActionType getAction() {
		return action;
	}
	
	public abstract ArrayList<StateType> explore();
	public abstract boolean equals(Object other);
	public abstract int heuristic(StateType goal);
}
