package search;

import java.util.Stack;

import search.interfaces.Frontier;
import search.interfaces.Node;

/**
 * This frontier implements Depth First Search.
 * It acts like a LIFO stack.
 */
public class DepthFirstFrontier
<StateT, NodeT extends Node<StateT, NodeT, ActionT>, ActionT>
        implements Frontier<StateT, NodeT, ActionT> {
    private Stack<NodeT> frontierList;

    public DepthFirstFrontier() {
        frontierList = new Stack<NodeT>();
    }

    @Override
    public void push(NodeT state) {
        frontierList.push(state);
    }

    @Override
    public NodeT pop() {
        return frontierList.pop();
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
