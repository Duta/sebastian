package grid;

public class Junction {
	private int xpos, ypos;
	private Junction up, down, left, right;

	public Junction(int xpos, int ypos, Junction up, Junction down, Junction left, Junction right) {
		this.xpos = xpos;
		this.ypos = ypos;
		
		this.up = up;
		this.down = down;
		this.left = left;
		this.right = right;
	}

	public Junction(int xpos, int ypos) {
		this(xpos, ypos, null, null, null, null);
	}
	
	public int getX() {
		return xpos;
	}
	
	public int getY() {
		return ypos;
	}

	public Junction getJunction(GridDirection dir) {
		switch (dir) {
		case UP:
			return up;

		case DOWN:
			return down;

		case LEFT:
			return left;

		case RIGHT:
			return right;

		default:
			throw new IllegalArgumentException("Invalid grid direction passed D:");

		}
	}
	
	public void setJunction(GridDirection dir, Junction junc) {
		switch (dir) {
		case UP:
			up = junc;
			break;

		case DOWN:
			down = junc;
			break;
			
		case LEFT:
			left = junc;
			break;

		case RIGHT:
			right = junc;
			break;

		default:
			throw new IllegalArgumentException("Invalid grid direction passed D:");

		}
	}
	
	public boolean equals(Object other) {
		if(other instanceof Junction) {
			return (xpos == ((Junction)other).xpos) && (ypos == ((Junction)other).ypos);
		}
		
		return false;
	}
}
