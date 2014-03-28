package grid;

/**
 * Directions you can move in a grid.
 */
public enum GridDirection {
    UP(0, 0, -1, 1, 90f),
    DOWN(1, 0, 1, 0, -90f),
    LEFT(2, -1, 0, 3, 180f),
    RIGHT(3, 1, 0, 2, 0f);  // See Heading.java for angles. 
    
    public final int index, opposite; 
    public final int dx, dy;
    public final float degrees;
    
    /**
     * @param index The index in the grid array this dir represents
     * @param dx The x delta this direction travels in
     * @param dy The y delta this direction travels in
     * @param opposite The index of the opposite direction
     * @param degrees The angle to rotate, relative to the world origin
     */
    private GridDirection(int index, int dx, int dy, int opposite, float degrees) {
        this.index = index;
        this.dx = dx;
        this.dy = dy;
        this.opposite = opposite;
        this.degrees = degrees;
    }
}
