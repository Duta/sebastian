package part3;

import part1.grid.Grid;
import part1.grid.GridState;

import java.util.ArrayList;
import java.util.List;

/**
 * Creates a random tour of some given points on a grid,
 * then uses simulated annealing to improve the path.
 */
public class TSPSolver
		implements Runnable {
    private String[] gridAscii;
    private String[] pointsAscii;
    private final TourImprover tourImprover;

    public TSPSolver(String[] gridAscii, String[] pointsAscii,
    		TourImprover tourImprover) {
        this.gridAscii = gridAscii;
        this.pointsAscii = pointsAscii;
        this.tourImprover = tourImprover;
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
        };
        SimulatedAnnealing simAnneal = new SimulatedAnnealing(15000, 1, 0.002);
        TSPSolver tspSolver = new TSPSolver(gridAscii, pointsAscii, simAnneal);
        tspSolver.run();
    }

    @Override
    public void run() {
        List<GridState> requiredStates = parseAsciiArt(gridAscii, pointsAscii);

        long startTime = System.currentTimeMillis();
        Tour tour = new Tour(requiredStates);
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;

        System.out.println("Initial path");
        System.out.println("------------");
        System.out.println(tour.toString());
        int initialLength = tour.getLength();

        System.out.println("Created initial path in " + elapsedTime + "ms\n");

        startTime = System.currentTimeMillis();
        tourImprover.improve(tour);
        endTime = System.currentTimeMillis();
        elapsedTime = endTime - startTime;

        System.out.println("Improved path");
        System.out.println("-------------");
        System.out.println(tour.toString());
        int improvedLength = tour.getLength();

        System.out.println("Reduced path length from " + initialLength +
                " to " + improvedLength + " in " + elapsedTime + "ms.");
    }

    private List<GridState> parseAsciiArt(String[] gridAscii,
    		String[] pointsAscii) {
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
}
