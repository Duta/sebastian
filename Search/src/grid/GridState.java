package grid;

/**
 * This class represents a state in the grid, i.e. the current position in it.
 */
public class GridState {
	private Grid grid;
	private Junction currentJunction;
	
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
			throw new IllegalArgumentException("Direction not valid to apply");
		}
		
		return new GridState(grid, currentJunction.getJunction(dir));
	}

    @Override
	public boolean equals(Object other) {
		if(other instanceof GridState) {
			return (grid.equals(((GridState)other).grid)) && (currentJunction.equals(((GridState)other).currentJunction));
		}
		
		return false;
	}

    @Override
    public String toString() {
        int x = currentJunction.getX();
        int y = currentJunction.getY();
        return x + ", " + y;
    }
}
