package grid;

import java.util.ArrayList;

public class Grid {
	private final int width, height;
	
	private ArrayList<ArrayList<Junction>> junctions;
	
	public Grid(int width, int height) {
		this.width = width;
		this.height = height;
		
		this.junctions = new ArrayList<ArrayList<Junction>>();
			
		for(int x = 0; x < width; x++) {
			junctions.add(new ArrayList<Junction>());
			
			for(int y = 0; y < height; y++) {
				junctions.get(x).add(new Junction(x, y));
			}
		}
		
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				Junction j = junctions.get(x).get(y);
				
				if(y > 0) {
					j.setJunction(GridDir.UP, junctions.get(x).get(y-1));
				}
				
				if(y < height - 1) {
					j.setJunction(GridDir.DOWN, junctions.get(x).get(y+1));
				}
				
				if(x > 0) {
					j.setJunction(GridDir.LEFT, junctions.get(x-1).get(y));
				}
				
				if(x < width - 1) {
					j.setJunction(GridDir.RIGHT, junctions.get(x+1).get(y));
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
		if(other instanceof Grid) {
			return (width == ((Grid)other).width) && (height == ((Grid)other).height);
		}
		
		return false;
	}
}
