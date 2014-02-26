package travellingsebastian;

import grid.Grid;
import grid.GridState;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static List<GridState> parseAsciiArt(String[] lines) {
        int width = 0;
        for(String line : lines) {
            if(line.length() > width) {
                width = line.length();
            }
        }
        int height = lines.length;

        Grid grid = new Grid(width, height);

        List<GridState> requiredGridStates = new ArrayList<GridState>();
        for(int y = 0; y < height; y++) {
            String line = lines[y];
            for(int x = 0; x < line.length(); x++) {
                if(line.charAt(x) != ' ') {
                    requiredGridStates.add(new GridState(grid, x, y));
                }
            }
        }
        return requiredGridStates;
    }

    public static void main(String[] args) {
        String[] asciiArt = {
            "#      #   ",
            "  #        ",
            "      #    ",
            "    #    # ",
            " #         ",
            "       #   ",
            "    #      "
        };
        List<GridState> requiredGridStates = parseAsciiArt(asciiArt);

        Path path = new Path(requiredGridStates);
        System.out.println("Initial path");
        System.out.println("------------");
        System.out.println(path);
        int initialLength = path.getLength();

        // TODO: Play with the parameters
        SimulatedAnnealing sa = new SimulatedAnnealing(15000, 1, 0.002);

        long startTime = System.currentTimeMillis();
        sa.improve(path);
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;

        System.out.println("Improved path");
        System.out.println("-------------");
        System.out.println(path);
        int improvedLength = path.getLength();

        System.out.println("Reduced path length from " + initialLength + " to " +
                improvedLength + " in " + elapsedTime + "ms.");
    }
}
