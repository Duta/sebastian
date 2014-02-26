package grid;

import java.util.ArrayList;
import java.util.List;

public class Grid {
	private final int width, height;
	
	private List<List<Junction>> junctions;
	
	public Grid(int width, int height) {
		this.width = width;
		this.height = height;
		
		this.junctions = new ArrayList<List<Junction>>();
			
		for(int x = 0; x < width; x++) {
			junctions.add(new ArrayList<Junction>());
			
			for(int y = 0; y < height; y++) {
				junctions.get(x).add(new Junction(x, y));
			}
		}
		
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				Junction j = getJunction(x, y);
				if(y > 0) {
					j.setJunction(GridDirection.UP, getJunction(x, y-1));
				}
				if(y < height - 1) {
					j.setJunction(GridDirection.DOWN, getJunction(x, y+1));
				}
				if(x > 0) {
					j.setJunction(GridDirection.LEFT, getJunction(x-1, y));
				}
				if(x < width - 1) {
					j.setJunction(GridDirection.RIGHT, getJunction(x+1, y));
				}
			}
		}
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public Junction getJunction(int x, int y) {
		return junctions.get(x).get(y);
	}
	
	public boolean equals(Object other) {
		return other instanceof Grid
			&& width == ((Grid)other).width
			&& height == ((Grid)other).height;
	}
}
