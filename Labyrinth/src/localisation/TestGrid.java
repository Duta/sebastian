package localisation;

import rp.robotics.mapping.GridMap;
import util.Util;
import grid.Grid;
import grid.GridDirection;

/**
 * Grid for testing.
 * Is basically a simulation of a robot moving and provides sensor readings
 * at the current actual location.
 */
public class TestGrid extends Grid {
	private int xPos, yPos;
	
	public TestGrid(GridMap gridMap) {
		super(gridMap);
		
		xPos = Util.RGEN.nextInt(getWidth());
		yPos = Util.RGEN.nextInt(getHeight());
	}
	
	/**
	 * Move the 'robot' in a direction.
	 */
	public void applyAction(GridDirection dir) {
		if(canGo(xPos, yPos, dir)) {
			xPos += dir.dx;
			yPos += dir.dy;
		}
	}
	
	/**
	 * Get the current 'robot' location.
	 */
	public Coordinate getLocation() {
		return new Coordinate(xPos, yPos);
	}
	
	/**
	 * Get the sensor reading from the 'robot' in a specific direction.
	 */
	public double sense(GridDirection dir) {
		return distanceFromPoint(xPos, yPos, dir);
	}
}
