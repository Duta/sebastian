package grid;

import rp.robotics.mapping.GridMap;
import util.Util;
import lejos.geom.Line;
import lejos.geom.Rectangle;

public class Grid {
    private final boolean[][][] grid;
    private final int width;
    private final int height;
    
    private Grid(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new boolean[width][height][4];
        
        for(int x = 0; x < width; x++)
        for(int y = 0; y < height; y++)
        for(int i = 0; i < 4; i++) {
            this.grid[x][y][i] = true;
        }
        
        for(int x = 0; x < width; x++)
        for(int y = 0; y < height; y++) {
        	if(x == 0) {
        		this.grid[x][y][GridDirection.LEFT.index] = false;
        	}
        	
        	if(x == width - 1) {
        		this.grid[x][y][GridDirection.RIGHT.index] = false;
        	}
        	
        	if(y == 0) {
        		this.grid[x][y][GridDirection.UP.index] = false;
        	}
        	
        	if(y == height - 1) {
        		this.grid[x][y][GridDirection.DOWN.index] = false;
        	}
        }
    }

    public Grid(GridMap gridMap) {
        this(gridMap.getGridWidth(),
             gridMap.getGridHeight());
        for(int x = 0; x < width; x++)
        for(int y = 0; y < height; y++)
        for(GridDirection dir : GridDirection.values()) {
            float range = gridMap.rangeToObstacleFromGridPoint(x, y, dir.degrees);
            if(range < gridMap.getCellSize() * 0.8f) {
                setBlocked(x, y, dir);
            }
        }
    }

    public void setBlocked(int x, int y, GridDirection dir) {
        grid[x][y][dir.index] = false;
        grid[x + dir.dx][y + dir.dy][dir.opposite] = false;
    }
    
    public boolean canGo(int x, int y, GridDirection dir) {
        if(x < 0 || x >= width
        || y < 0 || y >= height) {
            return false;
        }

        return grid[x][y][dir.index];
    }

    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }
}
