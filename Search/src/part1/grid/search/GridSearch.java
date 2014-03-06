package part1.grid.search;

import part1.Part1SearchRunner;
import part1.grid.Grid;
import part1.grid.GridDirection;
import part1.grid.GridState;

import util.Util;

/**
 * Performs search on the grid problem.
 * Can be extended to provide a frontier (search type).
 */
public abstract class GridSearch
        extends Part1SearchRunner<GridState, GridNode, GridDirection> {
    protected Grid grid;

    protected GridSearch() {
        grid = createGrid();
    }

    protected Grid createGrid() {
        return Grid.unblockedGrid();
    }

    @Override
    protected GridNode createStart() {
        int x = Util.RGEN.nextInt(grid.getWidth());
        int y = Util.RGEN.nextInt(grid.getHeight());
        return new GridNode(new GridState(grid, x, y));
    }

    @Override
    protected GridNode createGoal() {
        int x = Util.RGEN.nextInt(grid.getWidth());
        int y = Util.RGEN.nextInt(grid.getHeight());
        return new GridNode(new GridState(grid, x, y));
    }
}
