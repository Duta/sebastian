package grid;

import util.Util;
import lejos.robotics.mapping.LineMap;

public class Grid {
    private final boolean[][][] grid;
    private final int width;
    private final int height;
    
    public Grid(int width, int height) {
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
    
    public Grid(LineMap lineMap) {
        this(Util.round(lineMap.getBoundingRect().getWidth()),
             Util.round(lineMap.getBoundingRect().getHeight()));
        // TODO: add blockages
    }
    
    public void setBlocked(int x, int y, GridDirection direction) {
        grid[x][y][direction.index] = false;
        grid[x + direction.dx][y + direction.dy][direction.opposite] = false;
    }
    
    public boolean canGo(int x, int y, GridDirection direction) {
        if(x < 0 || x >= width
        || y < 0 || y >= height) {
            return false;
        }
        
        return grid[x][y][direction.index];
    }

    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }
}
