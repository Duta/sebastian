package part1;

import search.SearchRunner;
import search.interfaces.Node;

import java.util.Stack;

/**
 * Extends SearchRunner with some default on-event actions.
 */
public abstract class Part1SearchRunner<NodeT extends Node<NodeT, ActionT>, ActionT> extends SearchRunner<NodeT, ActionT> {
    @Override
    protected void preSearchAction(NodeT start, NodeT goal) {
        System.out.println("Start State:");
        System.out.println(start);
        System.out.println("Goal State:");
        System.out.println(goal);
    }

    @Override
    protected void pathNotFoundAction(NodeT start, NodeT goal) {
        System.out.println("No path found.");
    }

    @Override
    protected void processPathAction(NodeT start, NodeT goal, Stack<NodeT> path) {
        // Pop the start node, since we're
        // printing actions, which the start
        // node doesn't have
        path.pop();

        // Print the path
        System.out.println("Path (Length " + path.size() + "):");
        while(!path.empty()) {
            System.out.println(path.pop().getAction());
        }
    }
}
