package part3;

import part1.grid.GridState;
import util.ArrayUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a tour of some points on a grid.
 */
public class Tour {
    private GridState[] states;
    private List<GridStatePair> pairs;

    public Tour(List<GridState> requiredStates) {
        pairs = new ArrayList<GridStatePair>();
        states = new GridState[requiredStates.size()];
        // Fill the states array
        for(int i = 0; i < requiredStates.size(); i++) {
            states[i] = requiredStates.get(i);
        }

        // Randomize the tour
        System.out.print("Randomizing tour...");
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
        for(GridStatePair knownPair : pairs) {
            if(pair.equals(knownPair)) {
                return knownPair.getLength();
            }
        }
        pairs.add(pair);
        return pair.getLength();
    }
}
