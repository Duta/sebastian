package search;

import java.util.ArrayList;

import search.interfaces.Frontier;
import search.interfaces.Node;

/**
 * This frontier implements Breadth First Search.
 * It acts like a FIFO queue.
 */
public class BreadthFirstFrontier<StateT, NodeT extends Node<StateT, NodeT, ActionT>, ActionT>
        implements Frontier<StateT, NodeT, ActionT> {
    private ArrayList<NodeT> frontierList;

    public BreadthFirstFrontier() {
        frontierList = new ArrayList<NodeT>();
    }

    public void push(NodeT state) {
        frontierList.add(state);
    }

    @Override
    public NodeT pop() {
        NodeT state = frontierList.get(0);
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
