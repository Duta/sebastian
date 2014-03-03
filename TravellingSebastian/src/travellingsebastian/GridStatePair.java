package travellingsebastian;

import grid.GridState;

public class GridStatePair {
    private final GridState stateA, stateB;
    private final int length;

    public GridStatePair(GridState stateA, GridState stateB) {
        this(stateA, stateB, -1);
    }

    public GridStatePair(GridState stateA, GridState stateB, int distance) {
        this.stateA = stateA;
        this.stateB = stateB;
        this.length = distance;
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
        return length;
    }
}
