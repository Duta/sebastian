package travellingsebastian;

import grid.Grid;
import grid.GridState;

import java.util.ArrayList;
import java.util.List;

import lejos.nxt.comm.RConsole;

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
        };/*
        String[] pointsAscii = {
            "#      #   ",
            "  #        ",
            "      #    ",
            "    #    # ",
            " #         ",
            "       #   ",
            "    #      "
        };*/
        String[] pointsAscii = {
            "#          ",
            "           ",
            "           ",
            "         # ",
            " #         ",
            "           ",
            "    #      "
        };
        List<GridState> requiredStates = parseAsciiArt(gridAscii, pointsAscii);
        
        RConsole.openUSB(0);

        long startTime = System.currentTimeMillis();
        Path path = new Path(requiredStates);
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;

        RConsole.println("Initial path");
        RConsole.println("------------");
        RConsole.println(path.toString());
        int initialLength = path.getLength();

        RConsole.println("Created initial path in " + elapsedTime + "ms\n");

        // TODO: Play with the parameters
        SimulatedAnnealing sa = new SimulatedAnnealing(15000, 1, 0.002);

        startTime = System.currentTimeMillis();
        sa.improve(path);
        endTime = System.currentTimeMillis();
        elapsedTime = endTime - startTime;

        RConsole.println("Improved path");
        RConsole.println("-------------");
        RConsole.println(path.toString());
        int improvedLength = path.getLength();

        RConsole.println("Reduced path length from " + initialLength +
                " to " + improvedLength + " in " + elapsedTime + "ms.");
    }
}
