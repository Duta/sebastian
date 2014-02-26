package travellingsebastian;

import grid.Grid;
import grid.GridState;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Grid grid = new Grid(11, 7);
        List<GridState> requiredGridStates = new ArrayList<GridState>();
        requiredGridStates.add(new GridState(grid, 0, 0));
        requiredGridStates.add(new GridState(grid, 7, 0));
        requiredGridStates.add(new GridState(grid, 2, 1));
        requiredGridStates.add(new GridState(grid, 6, 2));
        requiredGridStates.add(new GridState(grid, 4, 3));
        requiredGridStates.add(new GridState(grid, 9, 3));
        requiredGridStates.add(new GridState(grid, 1, 4));
        requiredGridStates.add(new GridState(grid, 7, 5));
        requiredGridStates.add(new GridState(grid, 4, 6));

        Path path = new Path(requiredGridStates);
        System.out.println("Initial path");
        System.out.println("------------");
        System.out.println(path);
        int initialLength = path.getLength();

        long startTime = System.currentTimeMillis();
        // TODO: Play with the parameters
        SimulatedAnnealing sa = new SimulatedAnnealing(15000, 1, 0.002);
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
