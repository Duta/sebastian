package grid;

import rp.robotics.mapping.GridMap;

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
    }

    public Grid(GridMap gridMap) {
        this(gridMap.getGridWidth(),
             gridMap.getGridHeight());
        for(int x = 0; x < width; x++)
        for(int y = 0; y < height; y++)
        for(GridDirection dir : GridDirection.values()) {
            if(!gridMap.isValidTransition(x, y, x + dir.dx, y + dir.dy)) {
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
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int y = 0; y < height; y++) {
            for(int i = 0; i < 3; i++) {
                for(int x = 0; x < width; x++) {
                    switch(i) {
                    case 0:
                        sb.append(' ');
                        sb.append(canGo(x, y, GridDirection.UP) ? '^' : ' ');
                        sb.append(' ');
                        break;
                    case 1:
                        sb.append(canGo(x, y, GridDirection.LEFT) ? '<' : ' ');
                        sb.append(' ');
                        sb.append(canGo(x, y, GridDirection.RIGHT) ? '>' : ' ');
                        break;
                    case 2:
                        sb.append(' ');
                        sb.append(canGo(x, y, GridDirection.DOWN) ? 'v' : ' ');
                        sb.append(' ');
                        break;
                    }
                }
                sb.append('\n');
            }
        }
        return sb.toString();
    }
}
