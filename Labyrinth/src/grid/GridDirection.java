package grid;

public enum GridDirection {
    UP(0, 0, -1, 1),
    DOWN(1, 0, 1, 0),
    LEFT(2, -1, 0, 3),
    RIGHT(3, 1, 0, 2);
    
    public final int index, opposite; 
    public final int dx, dy;
    
    private GridDirection(int index, int dx, int dy, int opposite) {
        this.index = index;
        this.dx = dx;
        this.dy = dy;
        this.opposite = opposite;
    }
}
