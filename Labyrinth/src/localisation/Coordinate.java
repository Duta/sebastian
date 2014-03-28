package localisation;

/**
 * Pair of ints representing a location
 * (Mostly unused, GridState is better)
 */
public class Coordinate {
	public final int x, y;
	
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}