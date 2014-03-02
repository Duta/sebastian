package grid;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents an entire grid.
 */
public class Grid {
	private final int width, height;
	
	private List<List<Junction>> junctions;
	
	private Grid(int width, int height) {
		this.width = width;
		this.height = height;
		
		this.junctions = new ArrayList<List<Junction>>();
			
		for(int x = 0; x < width; x++) {
			junctions.add(new ArrayList<Junction>());
			
			for(int y = 0; y < height; y++) {
				junctions.get(x).add(new Junction(x, y));
			}
		}
	}

    public static Grid fromAsciiArt(String[] lines) {
        int width = 0;
        for(String line : lines) {
            int lineWidth = (line.length() + 1)/2;
            if(lineWidth > width) {
                width = lineWidth;
            }
        }
        int height = (lines.length + 1)/2;

        Grid grid = new Grid(width, height);

        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                Junction junction = grid.getJunction(x, y);
                int mapX = x*2;
                int mapY = y*2;
                if(y > 0 && lines[mapY-1].charAt(mapX) == '|') {
                    junction.setJunction(GridDirection.UP, grid.getJunction(x, y-1));
                }
                if(y < height - 1 && lines[mapY+1].charAt(mapX) == '|') {
                    junction.setJunction(GridDirection.DOWN, grid.getJunction(x, y+1));
                }
                if(x > 0 && lines[mapY].charAt(mapX-1) == '-') {
                    junction.setJunction(GridDirection.LEFT, grid.getJunction(x-1, y));
                }
                if(x < width - 1 && lines[mapY].charAt(mapX+1) == '-') {
                    junction.setJunction(GridDirection.RIGHT, grid.getJunction(x+1, y));
                }
            }
        }

        return grid;
    }

    public static Grid defaultGrid() {
        return fromAsciiArt(new String[] {
            "+-+-+-+-+-+-+-+-+-+-+",
            "| | | | | | | | | | |",
            "+-+-+-+-+-+-+-+-+-+-+",
            "| | | | | | | | | | |",
            "+-+-+-+-+-+-+-+-+-+-+",
            "| | | | | | | | | | |",
            "+-+-+-+-+-+-+-+-+-+-+",
            "| | | | | | | | | | |",
            "+-+-+-+-+-+-+-+-+-+-+",
            "| | | | | | | | | | |",
            "+-+-+-+-+-+-+-+-+-+-+",
            "| | | | | | | | | | |",
            "+-+-+-+-+-+-+-+-+-+-+",
        });
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

    @Override
	public boolean equals(Object other) {
        // TODO: Improve
		return other instanceof Grid
			&& width == ((Grid)other).width
			&& height == ((Grid)other).height;
	}

    @Override
    public String toString() {
        int imgWidth = width*2 - 1;
        int imgHeight = height*2 - 1;
        char[][] img = new char[imgHeight][imgWidth];
        for(int y = 0; y < imgHeight; y++) {
            for(int x = 0; x < imgWidth; x++) {
                img[y][x] = x%2 == 0 && y%2 == 0 ? '+' : ' ';
            }
        }
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                Junction junction = getJunction(x, y);
                int mapX = x*2;
                int mapY = y*2;
                if(junction.getJunction(GridDirection.UP) != null) {
                    img[mapY-1][mapX] = '|';
                }
                if(junction.getJunction(GridDirection.DOWN) != null) {
                    img[mapY+1][mapX] = '|';
                }
                if(junction.getJunction(GridDirection.LEFT) != null) {
                    img[mapY][mapX-1] = '-';
                }
                if(junction.getJunction(GridDirection.RIGHT) != null) {
                    img[mapY][mapX+1] = '-';
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("width=").append(width);
        sb.append(",height=").append(height);
        sb.append('\n');
        for(int y = 0; y < imgHeight; y++) {
            sb.append(img[y]);
            sb.append('\n');
        }
        return sb.toString();
    }
}
