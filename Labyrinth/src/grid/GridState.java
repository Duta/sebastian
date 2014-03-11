package grid;

public class GridState {
    private final Grid grid;
    private final int x, y;
    
    public GridState(Grid grid, int x, int y) {
        this.grid = grid;
        this.x = x;
        this.y = y;
    }

    public Grid getGrid() {
        return grid;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public boolean canGo(GridDirection direction) {
        return grid.canGo(x, y, direction);
    }

    public GridState applyDir(GridDirection action) {
        return new GridState(grid, x + action.dx, y + action.dy);
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
            && this.grid ==that.grid;
    }
}
