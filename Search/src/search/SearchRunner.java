package search;

import search.interfaces.Frontier;
import search.interfaces.Node;

import java.util.Stack;

/**
 * Runs a search algorithm between two nodes,
 * and provides mechanisms for handling the result.
 *
 * The search type is parameterized by the frontier.
 */
public abstract class SearchRunner
<StateT, NodeT extends Node<StateT, NodeT, ActionT>, ActionT> {
    protected SearchRunner() {}

    public void search() {
        NodeT start = createStart();
        NodeT goal = createGoal();
        Frontier<StateT, NodeT, ActionT> frontier = createFrontier(goal);

        preSearchAction(start, goal);

        Stack<NodeT> path;
        try {
            path = Search.search(start, goal, frontier);
        } catch (PathNotFoundException e) {
            pathNotFoundAction(start, goal);
            return;
        }

        processPathAction(start, goal, path);
    }

    protected abstract NodeT createStart();
    protected abstract NodeT createGoal();
    protected abstract Frontier<StateT, NodeT, ActionT> createFrontier(NodeT goal);

    protected abstract void preSearchAction(NodeT start, NodeT goal);
    protected abstract void pathNotFoundAction(NodeT start, NodeT goal);
    protected abstract void processPathAction(NodeT start, NodeT goal, Stack<NodeT> path);
}
