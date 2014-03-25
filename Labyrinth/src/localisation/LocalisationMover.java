package localisation;

import part2.PathAction;
import part2.SensorResult;
import grid.GridDirection;
import lejos.nxt.LightSensor;
import lejos.nxt.TouchSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.util.Delay;
import util.ButtonUtil;
import util.SebastianInternalException;

/**
 * Makes the robot follow the given path of grid nodes.
 */
public class LocalisationMover {
    private static final int
        THRESHOLD = 450,
        FORWARD_SPEED = 120,
        TURN_RATE = 130,
        TURN_SPEED = 30,
        MOVE_DIST = 50;

    private final DifferentialPilot pilot;
    private final LightSensor leftSensor;
    private final LightSensor rightSensor;
    private final TouchSensor touchSensor;

    private GridDirection direction;

    public LocalisationMover(DifferentialPilot differentialPilot,
            LightSensor leftSensor, LightSensor rightSensor,
            TouchSensor touchSensor, GridDirection initialDirection) {
        this.pilot = differentialPilot;
        this.leftSensor = leftSensor;
        this.rightSensor = rightSensor;
        this.touchSensor = touchSensor;
        this.direction = initialDirection;
    }

    public void attemptMove(GridDirection dir) {
    	pilot.travel(MOVE_DIST);
    	pilot.rotate(convert(dir).getTheta());
    	
    	boolean reverse = false;
    	boolean running = true;
    	boolean onPath = false;
        
        while(running) {
            Delay.msDelay(20);
            
            if(touchSensor.isPressed()) {
            	reverse = true;
            }

            switch(checkSensors()) {
            case LEFT_BLACK:
            	onPath = true;
            	
                pilot.setTravelSpeed(TURN_SPEED);
                if(reverse) {
                	pilot.steer(TURN_RATE);
                } else {
                	pilot.steer(-TURN_RATE);
                }
                break;

            case RIGHT_BLACK:
            	onPath = true;
            	
                pilot.setTravelSpeed(TURN_SPEED);
                if(reverse) {
                	pilot.steer(-TURN_RATE);
                } else {
                	pilot.steer(TURN_RATE);
                }
                break;

            case NOTHING:
            	onPath = true;

                pilot.setTravelSpeed(FORWARD_SPEED);
                if(reverse) {
                	pilot.backward();
                } else {
                	pilot.forward();
                }
                break;

            case BOTH_BLACK:
            	if(onPath) {
            		pilot.stop();
            		running = false;
            	} else {
            		pilot.forward();
            	}
                break;
            }
        }
    }

    private SensorResult checkSensors() {
        return SensorResult.checkSensors(leftSensor, rightSensor, THRESHOLD);
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

	public GridDirection getCurrentDir() {
		return direction;
	}
}
