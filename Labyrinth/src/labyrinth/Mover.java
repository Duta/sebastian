package labyrinth;

import grid.GridDirection;
import grid.GridNode;

import java.util.Stack;

import part2.PathAction;
import part2.SensorResult;
import lejos.nxt.LightSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.util.Delay;
import util.ButtonUtil;
import util.SebastianInternalException;

/**
 * Makes the robot follow the given path of grid nodes.
 */
public class Mover
        implements Runnable {
    private static final int
        THRESHOLD = 450,
        FORWARD_SPEED = 120,
        TURN_RATE = 130,
        TURN_SPEED = 30;

    private final DifferentialPilot pilot;
    private final LightSensor leftSensor;
    private final LightSensor rightSensor;
    private final Stack<GridNode> path;

    private GridDirection direction;

    public Mover(DifferentialPilot differentialPilot,
            LightSensor leftSensor, LightSensor rightSensor,
            Stack<GridNode> path, GridDirection initialDirection) {
        this.pilot = differentialPilot;
        this.leftSensor = leftSensor;
        this.rightSensor = rightSensor;
        this.path = path;
        this.direction = initialDirection;
    }

    @Override
    public void run() {
        ButtonUtil.exitOnEscapePress();

        boolean running = true;
        while(running) {
            Delay.msDelay(20);

            switch(checkSensors()) {
            case LEFT_BLACK:
                pilot.setTravelSpeed(TURN_SPEED);
                pilot.steer(-TURN_RATE);
                break;

            case RIGHT_BLACK:
                pilot.setTravelSpeed(TURN_SPEED);
                pilot.steer(TURN_RATE);
                break;

            case NOTHING:
                pilot.setTravelSpeed(FORWARD_SPEED);
                pilot.forward();
                break;

            case BOTH_BLACK:
                running = junction();
                break;
            }
        }
    }

    private SensorResult checkSensors() {
        return SensorResult.checkSensors(leftSensor, rightSensor, THRESHOLD, THRESHOLD);
    }

    private boolean junction() {
        if(path.empty()) {
            return false;
        }

        GridNode node = path.pop();

        System.out.println("Direction: " + direction + ", Action: " + node.getAction());
        PathAction action = convert(node.getAction());
        direction = node.getAction();

        pilot.travel(60);
        pilot.rotate(action.getTheta());

        return true;
    }

    private PathAction convert(GridDirection action) {
        switch(action) {
        case DOWN:
            switch(direction) {
            case DOWN:  return PathAction.STRAIGHT;
            case LEFT:  return PathAction.LEFT;
            case RIGHT: return PathAction.RIGHT;
            case UP:    return PathAction.U_TURN;
            }

        case LEFT:
            switch(direction) {
            case DOWN:  return PathAction.RIGHT;
            case LEFT:  return PathAction.STRAIGHT;
            case RIGHT: return PathAction.U_TURN;
            case UP:    return PathAction.LEFT;
            }

        case RIGHT:
            switch(direction) {
            case DOWN:  return PathAction.LEFT;
            case LEFT:  return PathAction.U_TURN;
            case RIGHT: return PathAction.STRAIGHT;
            case UP:    return PathAction.RIGHT;
            }

        case UP:
            switch(direction) {
            case DOWN:  return PathAction.U_TURN;
            case LEFT:  return PathAction.RIGHT;
            case RIGHT: return PathAction.LEFT;
            case UP:    return PathAction.STRAIGHT;
            }

        default: throw new SebastianInternalException();
        }
    }
}
