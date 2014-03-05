package part3;

import part1.grid.GridDirection;
import part1.grid.GridState;
import part1.grid.search.GridNode;
import search.AStarFrontier;
import search.PathNotFoundException;
import search.Search;
import util.ArrayUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class Path {
    private GridState[] states;
    private List<GridStatePair> knownLengths;

    public Path(List<GridState> requiredStates) {
        knownLengths = new ArrayList<GridStatePair>();
        states = new GridState[requiredStates.size()];
        for(int i = 0; i < requiredStates.size(); i++) {
            states[i] = requiredStates.get(i);
        }
        System.out.print("Randomizing path...");
        int iterations = 1;
        do {
            System.out.print(iterations + "x...");
            ArrayUtil.randomize(states);
            iterations++;
        } while(!isViable());
        System.out.println("Done");
    }

    public void setStates(GridState[] states) {
        this.states = states;
    }

    public GridState[] getStates() {
        return states;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Length: ").append(getLength()).append('\n');
        for(int i = 0; i < states.length; i++) {
            sb.append(i + 1).append(": ").append(states[i]).append('\n');
        }
        return sb.toString();
    }

    public boolean isViable() {
        return getLength() != Integer.MAX_VALUE;
    }

    public int getLength() {
        int totalLength = 0;
        GridState prevState = states[states.length - 1];
        for(GridState state : states) {
            int length = getLength(prevState, state);
            if(length == Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
            totalLength += length;
            prevState = state;
        }
        return totalLength;
    }

    private int getLength(GridState a, GridState b) {
        GridStatePair pair = new GridStatePair(a, b);

        for(GridStatePair knownPair : knownLengths) {
            if(pair.equals(knownPair)) {
                return knownPair.getLength();
            }
        }

        /* OLD: Will be removed soon
        Junction junctionA = a.getJunction();
        Junction junctionB = b.getJunction();
        int dx = Math.abs(junctionA.getX() - junctionB.getX());
        int dy = Math.abs(junctionA.getY() - junctionB.getY());
        int length = dx + dy;
         */
        int length;
        GridNode startNode = new GridNode(a);
        GridNode goalNode = new GridNode(b);
        try {
            Stack<GridNode> path = Search.search(startNode, goalNode, new AStarFrontier<GridNode, GridDirection>(goalNode));
            length = path.size() - 1;
        } catch(PathNotFoundException e) {
            // Can't get from a to b
            length = Integer.MAX_VALUE;
        }

        knownLengths.add(new GridStatePair(a, b, length));

        return length;
    }
}
