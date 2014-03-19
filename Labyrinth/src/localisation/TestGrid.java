package localisation;

import rp.robotics.mapping.GridMap;
import util.Util;
import grid.Grid;
import grid.GridDirection;

public class TestGrid extends Grid {
	private int xPos, yPos;
	
	public TestGrid(GridMap gridMap) {
		super(gridMap);
		
		xPos = Util.RGEN.nextInt(getWidth());
		yPos = Util.RGEN.nextInt(getHeight());
	}
	
	public void applyAction(GridDirection dir) {
		if(canGo(xPos, yPos, dir)) {
			xPos += dir.dx;
			yPos += dir.dy;
		}
	}
	
	public Coordinate getLocation() {
		return new Coordinate(xPos, yPos);
	}
}
