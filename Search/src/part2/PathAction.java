package part2;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an action to take
 * upon encountering a junction.
 */
public enum PathAction {
    LEFT(-90),
    RIGHT(90),
    STRAIGHT(0),
    U_TURN(180);
    
    private final int theta;
    
    private PathAction(int theta) {
        this.theta = theta;
    }

    public int getTheta() {
        return theta;
    }

    public static List<PathAction> parse(String string) {
        List<PathAction> path = new ArrayList<PathAction>();

        for(char c : string.toCharArray()) {
            path.add(parse(c));
        }

        return path;
    }

    public static PathAction parse(char c) {
        switch(c) {
        case 'L': return LEFT;
        case 'R': return RIGHT;
        case 'S': return STRAIGHT;
        case 'U': return U_TURN;
        default:
            throw new IllegalArgumentException("'" + c + "' is invalid");
        }
    }
}
