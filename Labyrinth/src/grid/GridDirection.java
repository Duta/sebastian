package grid;

public enum GridDirection {
    UP(0, 0, -1, 1, 90f),
    DOWN(1, 0, 1, 0, -900f),
    LEFT(2, -1, 0, 3, 180f),
    RIGHT(3, 1, 0, 2, 0f);  // See Heading.java for angles. 
    
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
