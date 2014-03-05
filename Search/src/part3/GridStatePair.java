package part3;

import part1.grid.GridState;

/**
 * Represents a pair of grid states
 * and the length between them.
 * The length may be unknown.
 */
public class GridStatePair {
    private final GridState stateA, stateB;
    private int length;
    private LengthFinder lengthFinder;

    public GridStatePair(GridState stateA, GridState stateB) {
        this.stateA = stateA;
        this.stateB = stateB;
        this.length = -1;
        this.lengthFinder = new LengthFinder(this);
    }

    public boolean isLengthKnown() {
        return length != -1;
    }

    public GridState getStateA() {
        return stateA;
    }

    public GridState getStateB() {
        return stateB;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof GridStatePair)) {
            return false;
        }
        GridStatePair that = (GridStatePair) obj;
        return (stateA.equals(that.stateA)
            && stateB.equals(that.stateB))
            || (stateA.equals(that.stateB)
            && stateB.equals(that.stateA));
    }

    public int getLength() {
        return lengthFinder.calculate();
    }

    public void setLength(int length) {
        this.length = length;
    }
}
