package grid;

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
	
	public boolean validDirection(GridDir dir) {
		return currentJunction.getJunction(dir) != null;
	}

	public GridState applyDir(GridDir dir) {
		if(!validDirection(dir)) {
			throw new IllegalArgumentException("Direction not valid to apply");
		}
		
		return new GridState(grid, currentJunction.getJunction(dir));
	}
	
	public boolean equals(Object other) {
		if(other instanceof GridState) {
			return (grid.equals(((GridState)other).grid)) && (currentJunction.equals(((GridState)other).currentJunction));
		}
		
		return false;
	}
}