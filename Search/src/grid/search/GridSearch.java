package grid.search;

import grid.Grid;
import grid.GridDirection;
import grid.GridState;

import search.SearchRunner;

import util.Util;

public abstract class GridSearch extends SearchRunner<GridNode, GridDirection> {
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
