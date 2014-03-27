package grid;

import java.util.Stack;

import search.AStarFrontier;
import search.SearchRunner;
import search.interfaces.Frontier;

/**
 * Performs search on the grid to find
 * a path from one point to another.
 */
public abstract class GridSearcher
        extends SearchRunner<GridState, GridNode, GridDirection> {
    private Grid grid;
    private final int startX, startY;
    private final int goalX, goalY;

    /**
     * Creates a GridSearcher which will search
     * on the given grid from the given start
     * point to the given goal point.
     * 
     * @param grid the grid to search in
     * @param startX the start point's x co-ordinate
     * @param startY the start point's y co-ordinate
     * @param goalX the goal point's x co-ordinate
     * @param goalY the goal point's y co-ordinate
     */
    public GridSearcher(Grid grid, int startX,
            int startY, int goalX, int goalY) {
        this.grid = grid;
        this.startX = startX;
        this.startY = startY;
        this.goalX = goalX;
        this.goalY = goalY;
    }

    @Override
    protected GridNode createStart() {
        return new GridNode(new GridState(grid, startX, startY));
    }

    @Override
    protected GridNode createGoal() {
        return new GridNode(new GridState(grid, goalX, goalY));
    }
    
    @Override
    protected void preSearchAction(GridNode start, GridNode goal) {
        System.out.println("Start State:");
        System.out.println(start);
        System.out.println("Goal State:");
        System.out.println(goal);
    }

    @Override
    protected void pathNotFoundAction(GridNode start, GridNode goal) {
        System.out.println("No path found.");
    }

    @Override
    protected Frontier<GridState, GridNode, GridDirection> createFrontier(
            GridNode goal) {
        return new AStarFrontier<GridState, GridNode, GridDirection>(goal) {
            @Override
            protected int totalPathCost(GridNode node) {
                int pathCost = 0;
                while(node.getParent() != null) {
                    if(node.getAction() == node.getParent().getAction()) {
                        pathCost++;
                    } else {
                        pathCost += 3;
                    }
                    node = node.getParent();
                }
                return pathCost;
            }
        };
    }
}
