package part1;

public enum Shape {
    SQUARE(200, 90),
    TRIANGLE(300, 120),
    PENTAGON(250, 360 / 5);
    
    public final int dist;
    public final int theta;
    
    Shape(int dist, int theta) {
        this.dist = dist;
        this.theta = theta;
    }
}
