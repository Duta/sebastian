package grid;

public enum GridDirection {
    UP(0, 0, -1, 1, 0f),
    DOWN(1, 0, 1, 0, 180f),
    LEFT(2, -1, 0, 3, 270f),
    RIGHT(3, 1, 0, 2, 90f); // TODO: Check LEFT and RIGHT degrees values
    
    public final int index, opposite; 
    public final int dx, dy;
    public final float degrees;
    
    private GridDirection(int index, int dx, int dy, int opposite, float degrees) {
        this.index = index;
        this.dx = dx;
        this.dy = dy;
        this.opposite = opposite;
        this.degrees = degrees;
    }
}
