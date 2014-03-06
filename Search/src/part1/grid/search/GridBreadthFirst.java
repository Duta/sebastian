package part1.grid.search;

import part1.grid.GridDirection;
import part1.grid.GridState;
import search.BreadthFirstFrontier;
import search.interfaces.Frontier;

/**
 * Performs BFS on the grid problem.
 */
public class GridBreadthFirst
		extends GridSearch {
    public static void main(String[] args) {
        new GridAStar().search();
    }

    @Override
    protected Frontier<GridState, GridNode, GridDirection>
    createFrontier(GridNode goal) {
        return new BreadthFirstFrontier<GridState, GridNode, GridDirection>();
    }
}
