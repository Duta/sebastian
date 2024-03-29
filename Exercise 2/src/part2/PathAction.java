package part2;

import java.util.ArrayList;
import java.util.List;

public enum PathAction {
    LEFT,
    RIGHT,
    STRAIGHT;
    
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
        default:
            throw new IllegalArgumentException("'" + c + "' is invalid");
        }
    }
}
