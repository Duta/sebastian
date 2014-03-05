package search;

import search.interfaces.Frontier;
import search.interfaces.Node;

import java.util.Stack;

public abstract class SearchRunner<NodeT extends Node<NodeT, ActionT>, ActionT> {
    protected SearchRunner() {}

    public void search() {
        NodeT start = createStart();
        NodeT goal = createGoal();
        Frontier<NodeT, ActionT> frontier = createFrontier(goal);

        System.out.println("Start State:");
        System.out.println(start);
        System.out.println("Goal State:");
        System.out.println(goal);

        Stack<NodeT> path;
        try {
            path = Search.search(start, goal, frontier);
        } catch (PathNotFoundException e) {
            System.out.println("No path found.");
            return;
        }

        path.pop();
        System.out.println("Path (Length " + path.size() + "):");
        while(!path.empty()) {
            NodeT node = path.pop();
            System.out.println(node.getAction());
        }
    }

    protected abstract NodeT createStart();
    protected abstract NodeT createGoal();
    protected abstract Frontier<NodeT, ActionT> createFrontier(NodeT goal);
}
