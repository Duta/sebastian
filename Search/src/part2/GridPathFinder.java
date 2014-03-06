package part2;

import java.util.Stack;

import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import part1.grid.Grid;
import part1.grid.GridDirection;
import part1.grid.GridState;
import part1.grid.search.GridNode;
import search.AStarFrontier;
import search.SearchRunner;
import search.interfaces.Frontier;
import util.RobotInfo;
import util.Util;

/**
 * Finds a path between two points on a grid,
 * then makes a robot follow the path.
 */
public class GridPathFinder
        extends SearchRunner<GridState, GridNode, GridDirection>
        implements Runnable {
    private final Grid grid;
    private final int x1, y1;
    private final int x2, y2;
    private final GridDirection initialDirection;
    private RobotInfo robot;
    private SensorPort leftSensorPort;
    private SensorPort rightSensorPort;

    public GridPathFinder(Grid grid, int x1, int y1, int x2, int y2,
            GridDirection initialDirection, RobotInfo robot,
            SensorPort leftSensorPort, SensorPort rightSensorPort) {
        this.grid = grid;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.initialDirection = initialDirection;
        this.robot = robot;
        this.leftSensorPort = leftSensorPort;
        this.rightSensorPort = rightSensorPort;
    }

    public static void main(String[] args) {
        GridPathFinder gridPathFinder = new GridPathFinder(
                Grid.defaultGrid(),
                9, 0,
                0, 6,
                GridDirection.DOWN,
                RobotInfo.SEBASTIAN,
                SensorPort.S4,
                SensorPort.S1);
        gridPathFinder.run();
    }

    @Override
    public void run() {
        Util.waitForStart();
        search();
    }

    @Override
    protected GridNode createStart() {
        return new GridNode(new GridState(grid, x1, y1));
    }

    @Override
    protected GridNode createGoal() {
        return new GridNode(new GridState(grid, x2, y2));
    }

    @Override
    protected Frontier<GridState, GridNode, GridDirection> createFrontier(GridNode goal) {
        return new AStarFrontier<GridState, GridNode, GridDirection>(goal);
    }

    @Override
    protected void preSearchAction(GridNode start, GridNode goal) {
        System.out.println("Searching...");
    }

    @Override
    protected void pathNotFoundAction(GridNode start, GridNode goal) {
        System.out.println("Path not found.");
    }

    @Override
    protected void processPathAction(GridNode start, GridNode goal, Stack<GridNode> path) {
        // Pop the start node, since the
        // movement only cares the actions,
        // which the start node doesn't have
        path.pop();

        // Make the robot follow the path
        Mover mover = new Mover(
                robot.getDifferentialPilot(),
                new LightSensor(leftSensorPort),
                new LightSensor(rightSensorPort),
                path,
                initialDirection);
        mover.run();
    }
}
