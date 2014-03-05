package part3;

import part1.grid.Grid;
import part1.grid.GridState;

import java.util.ArrayList;
import java.util.List;


public class Main {
    private static List<GridState> parseAsciiArt(String[] gridAscii, String[] pointsAscii) {
        Grid grid = Grid.fromAsciiArt(gridAscii);

        int width = 0;
        for(String line : pointsAscii) {
            if(line.length() > width) {
                width = line.length();
            }
        }
        int height = pointsAscii.length;

        List<GridState> requiredStates = new ArrayList<GridState>();
        for(int y = 0; y < height; y++) {
            String line = pointsAscii[y];
            for(int x = 0; x < line.length(); x++) {
                if(line.charAt(x) != ' ') {
                    requiredStates.add(new GridState(grid, x, y));
                }
            }
        }
        return requiredStates;
    }

    public static void main(String[] args) {
        String[] gridAscii = {
            "+ +-+ + +-+-+-+-+-+-+",
            "| | | | | | | | | | |",
            "+ +-+-+-+-+-+-+-+-+-+",
            "| | | | | | | | | | |",
            "+ +-+-+-+-+-+-+-+-+-+",
            "| | | | | | | | | | |",
            "+ +-+-+-+-+-+-+ +-+-+",
            "| | | | | | | | | | |",
            "+-+-+-+-+-+-+-+ + +-+",
            "| | | | | | | | | | |",
            "+-+-+-+ + + +-+-+-+-+",
            "| | | | | | | | | | |",
            "+-+-+-+-+-+ +-+-+-+-+",
        };
        String[] pointsAscii = {
            "#      #   ",
            "  #        ",
            "      #    ",
            "    #    # ",
            " #         ",
            "       #   ",
            "    #      "
        };/*
        String[] pointsAscii = {
            "#          ",
            "     #     ",
            "           ",
            "         # ",
            " #         ",
            "           ",
            "    #      "
        };*/
        List<GridState> requiredStates = parseAsciiArt(gridAscii, pointsAscii);
        
        long startTime = System.currentTimeMillis();
        Path path = new Path(requiredStates);
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;

        System.out.println("Initial path");
        System.out.println("------------");
        System.out.println(path.toString());
        int initialLength = path.getLength();

        System.out.println("Created initial path in " + elapsedTime + "ms\n");

        // TODO: Play with the parameters
        SimulatedAnnealing sa = new SimulatedAnnealing(15000, 1, 0.002);

        startTime = System.currentTimeMillis();
        sa.improve(path);
        endTime = System.currentTimeMillis();
        elapsedTime = endTime - startTime;

        System.out.println("Improved path");
        System.out.println("-------------");
        System.out.println(path.toString());
        int improvedLength = path.getLength();

        System.out.println("Reduced path length from " + initialLength +
                " to " + improvedLength + " in " + elapsedTime + "ms.");
    }
}
