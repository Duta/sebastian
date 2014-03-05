package part1.grid.search;

import part1.grid.GridDirection;
import part1.grid.GridState;
import search.DepthFirstFrontier;
import search.interfaces.Frontier;

/**
 * Performs DFS on the grid problem.
 */
public class GridDepthFirst extends GridSearch {
    public static void main(String[] args) {
        new GridAStar().search();
    }

    @Override
    protected Frontier<GridState, GridNode, GridDirection> createFrontier(GridNode goal) {
        return new DepthFirstFrontier<GridState, GridNode, GridDirection>();
    }
}
