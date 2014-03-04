package jumbledstring.search;

import java.util.Stack;

import jumbledstring.CharacterSwap;
import jumbledstring.JumbledString;
import search.PathNotFoundException;
import search.Search;
import search.interfaces.Frontier;

public abstract class JumbledStringSearch {
    protected JumbledStringSearch() {}

    public void search() {
        JumbledStringNode start = new JumbledStringNode(new JumbledString("avja"));
        JumbledStringNode goal = new JumbledStringNode(new JumbledString("java"));

        System.out.println("Start State:");
        System.out.println(start);
        System.out.println("Goal State:");
        System.out.println(goal);

        Stack<JumbledStringNode> path = null;
        try {
            long startTime = System.currentTimeMillis();
            path = Search.search(start, goal, getFrontier(goal));
            long endTime = System.currentTimeMillis();
            System.out.println("Took " + (endTime - startTime) + "ms");
        } catch (PathNotFoundException e) {
            System.out.println("No swap sequence found.");
            return;
        }

        path.pop();
        System.out.println("Path (Length " + path.size() + "):");
        while(!path.empty()) {
            JumbledStringNode state = path.pop();
            CharacterSwap characterSwap = state.getAction();
            int indexA = characterSwap.getIndexA();
            int indexB = characterSwap.getIndexB();
            System.out.println(
                    "Swap index " + indexA + " with index " + indexB);
        }
    }

    protected abstract Frontier<JumbledStringNode, CharacterSwap> getFrontier(JumbledStringNode goal);
}
