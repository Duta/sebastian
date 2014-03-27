package grid;

/**
 * Represents the state of being at
 * a given point in a given grid.
 */
public class GridState {
    private final Grid grid;
    private final int x, y;
    
    /**
     * Creates the state of being at
     * the given point in the given grid.
     * 
     * @param grid the inhabited grid
     * @param x the x co-ordinate
     * @param y the y co-ordinate
     */
    public GridState(Grid grid, int x, int y) {
        this.grid = grid;
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the inhabited grid.
     * 
     * @return the inhabited grid
     */
    public Grid getGrid() {
        return grid;
    }

    /**
     * Returns the x co-ordinate.
     * 
     * @return the x co-ordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the y co-ordinate.
     * 
     * @return the y co-ordinate
     */
    public int getY() {
        return y;
    }
    
    /**
     * Returns true iff it is possible
     * to move in the given direction.
     * 
     * @param direction the direction to move in
     * @return true iff the given direction is unblocked
     */
    public boolean canGo(GridDirection direction) {
        return grid.canGo(x, y, direction);
    }

    /**
     * Returns the state obtained after
     * moving in the given direction.
     * 
     * @param direction the direction to move in
     * @return the state one move in the given direction
     */
    public GridState applyDir(GridDirection direction) {
        return new GridState(grid, x + direction.dx, y + direction.dy);
    }
    
    @Override
    public String toString() {
        return x + ", " + y;
    }
    
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof GridState)) {
            return false;
        }
        GridState that = (GridState)obj;
        return this.x == that.x
            && this.y == that.y
            && this.grid == that.grid;
    }
}
