package search.interfaces;

import java.util.ArrayList;

public abstract class State<T> {
	protected T parent;
	
	public T getParent() {
		return parent;
	}
	
	public abstract ArrayList<T> explore();
	public abstract boolean equals(Object other);
	public abstract int heuristic(T goal);
}
