package part1.grid.search;

import part1.grid.Grid;
import part1.grid.GridDirection;
import part1.grid.GridState;

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
