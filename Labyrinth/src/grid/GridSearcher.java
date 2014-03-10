package grid;

import java.util.Stack;

import search.SearchRunner;

/**
 * Performs search on the grid problem.
 * Can be extended to provide a frontier (search type).
 */
public abstract class GridSearcher
        extends SearchRunner<GridState, GridNode, GridDirection> {
    protected Grid grid;
    private final int startX, startY;
    private final int goalX, goalY;

    protected GridSearcher(Grid grid, int startX,
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
    protected void processPathAction(GridNode start, GridNode goal, Stack<GridNode> path) {
        // Pop the start node, since we're
        // printing actions, which the start
        // node doesn't have
        path.pop();

        // Print the path
        System.out.println("Path (Length " + path.size() + "):");
        while(!path.empty()) {
            System.out.println(path.pop().getAction());
        }
    }
}
