package grid.search;

import grid.Grid;
import grid.GridDirection;
import grid.GridState;

import search.SearchRunner;

import util.Util;

public abstract class GridSearch extends SearchRunner<GridNode, GridDirection> {
    @Override
    protected GridNode createStart() {
        Grid grid = Grid.unblockedGrid();
        int x = Util.RGEN.nextInt(grid.getWidth());
        int y = Util.RGEN.nextInt(grid.getHeight());
        return new GridNode(new GridState(grid, x, y));
    }

    @Override
    protected GridNode createGoal() {
        Grid grid = Grid.unblockedGrid();
        int x = Util.RGEN.nextInt(grid.getWidth());
        int y = Util.RGEN.nextInt(grid.getHeight());
        return new GridNode(new GridState(grid, x, y));
    }
}
