package part1.grid;

/**
 * Represents a junction in a grid.
 */
public class Junction {
    private int x, y;
    private Junction up, down, left, right;

    public Junction(int x, int y, Junction up, Junction down,
    		Junction left, Junction right) {
        this.x = x;
        this.y = y;
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
    }

    public Junction(int x, int y) {
        this(x, y, null, null, null, null);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Junction getJunction(GridDirection dir) {
        switch (dir) {
        case UP:    return up;
        case DOWN:  return down;
        case LEFT:  return left;
        case RIGHT: return right;
        default:    return null;
        }
    }

    public void setJunction(GridDirection dir, Junction junction) {
        switch (dir) {
        case UP:
            up = junction;
            break;

        case DOWN:
            down = junction;
            break;

        case LEFT:
            left = junction;
            break;

        case RIGHT:
            right = junction;
            break;
        }
    }

    public boolean equals(Object other) {
        if(!(other instanceof Junction)) {
            return false;
        }
        Junction that = (Junction)other;
        return x == that.x
            && y == that.y;
    }
}
