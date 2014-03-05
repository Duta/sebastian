package search.interfaces;

import java.util.ArrayList;
import java.util.List;

/**
 * This interface defines the search nodes for the search algorithm.
 *
 * @param <StateT> the type of state stored in the node
 * @param <NodeT> the type of node
 * @param <ActionT> the actions for the node
 */
public abstract class Node<StateT, NodeT, ActionT> {
    protected final StateT state;
    protected final NodeT parent;
    protected final ActionT action;

    /**
     * Creates a node for the given state,
     * which has no parent or action.
     *
     * @param state the puzzle state to
     *               store in the node
     */
    protected Node(StateT state) {
        this(state, null, null);
    }

    /**
     * Creates a node for the given state,
     * with the given parent and action.
     *
     * @param state the state to store in the node
     * @param parent the parent node
     * @param action the action taken upon the parent
     */
    protected Node(StateT state, NodeT parent, ActionT action) {
        this.state = state;
        this.parent = parent;
        this.action = action;
    }

    /**
     * Returns a list of nodes reachable
     * from this node with one action.
     *
     * @return a list of immediately reachable nodes
     */
    public List<NodeT> explore() {
        List<NodeT> successors = new ArrayList<NodeT>();
        for (ActionT action : allActions()) {
            NodeT successor = applyAction(action);
            if(successor != this) {
                successors.add(successor);
            }
        }
        return successors;
    }

    @Override
    public String toString() {
        return state.toString();
    }

    /**
     * Returns the state stored in the node.
     *
     * @return the state
     */
    public StateT getState() {
        return state;
    }

    /**
     * Returns the parent node (null if no parent).
     *
     * @return the parent if it exists, null otherwise
     */
    public NodeT getParent() {
        return parent;
    }

    /**
     * Returns the action performed on the parent
     * to get to this node (null if no parent).
     *
     * @return the action if it exists, null otherwise
     */
    public ActionT getAction() {
        return action;
    }

    /**
     * Returns all actions
     * @return
     */
    public abstract ActionT[] allActions();

    /**
     * Returns true if the given object
     * is a node of the same type, and
     * both nodes store the same state.
     *
     * @param other the object to compare to
     * @return true iff the other node
     *         stores the same state
     */
    public abstract boolean equals(Object other);

    /**
     * Calculates a heuristic value, which
     * is an estimation of the cost to move
     * from this node to the goal node.
     *
     * @param goal the goal node
     * @return the estimated cost between this and the goal
     */
    public abstract int heuristic(NodeT goal);

    /**
     * Returns a new node with the given
     * action applied, or this if the
     * action is not applicable.
     *
     * @param action the action to apply
     * @return a new node with the action applied if
     *         the action is applicable, this otherwise
     */
    public abstract NodeT applyAction(ActionT action);
}
