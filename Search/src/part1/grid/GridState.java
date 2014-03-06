package part1.grid;

/**
 * Represents an immutable state of an grid.
 */
public class GridState {
    private final Grid grid;
    private final Junction currentJunction;

    public GridState(Grid grid, int x, int y) {
        this.grid = grid;
        this.currentJunction = grid.getJunction(x, y);
    }

    public GridState(Grid grid, Junction j) {
        this.grid = grid;
        this.currentJunction = j;
    }

    public Grid getGrid() {
        return grid;
    }

    public Junction getJunction() {
        return currentJunction;
    }

    public boolean validDirection(GridDirection dir) {
        return currentJunction.getJunction(dir) != null;
    }

    public GridState applyDir(GridDirection dir) {
        if(!validDirection(dir)) {
            throw new IllegalArgumentException("Unapplicable direction");
        }

        return new GridState(grid, currentJunction.getJunction(dir));
    }

    @Override
    public boolean equals(Object other) {
        if(!(other instanceof GridState)) {
        	return false;
        }
    	GridState that = (GridState)other;
        return grid.equals(that.grid)
        	&& currentJunction.equals(that.currentJunction);
    }

    @Override
    public String toString() {
        int x = currentJunction.getX();
        int y = currentJunction.getY();
        return x + ", " + y;
    }
}
