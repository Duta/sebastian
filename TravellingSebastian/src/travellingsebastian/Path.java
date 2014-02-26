package travellingsebastian;

import grid.GridState;
import grid.Junction;
import util.Util;

import java.util.List;

public class Path {
    private GridState[] states;

    public Path(List<GridState> requiredGridStates) {
        states = new GridState[requiredGridStates.size()];
        for(int i = 0; i < requiredGridStates.size(); i++) {
            states[i] = requiredGridStates.get(i);
        }
        Util.randomize(states);
    }

    public int getLength() {
        int length = 0;
        GridState prevState = states[states.length - 1];
        for(int i = 0; i < states.length; i++) {
            GridState state = states[i];
            length += getLength(prevState, state);
            prevState = state;
        }
        return length;
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

    private int getLength(GridState a, GridState b) {
        Junction junctionA = a.getJunction();
        Junction junctionB = b.getJunction();
        int dx = Math.abs(junctionA.getX() - junctionB.getX());
        int dy = Math.abs(junctionA.getY() - junctionB.getY());
        return dx + dy;
    }
}
