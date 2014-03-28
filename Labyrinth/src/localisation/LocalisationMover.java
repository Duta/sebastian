package localisation;

import part2.PathAction;
import part2.SensorResult;
import grid.GridDirection;
import lejos.nxt.LightSensor;
import lejos.nxt.TouchSensor;
import lejos.robotics.navigation.DifferentialPilot;
import util.SebastianInternalException;

/**
 * Makes the robot follow the given path of grid nodes, specifically
 * during the localisation process.
 */
public class LocalisationMover {
	private static final int 
		LEFT_THRESHOLD = 420,		
		RIGHT_THRESHOLD = 520,

		FORWARD_SPEED = 100,
		TURN_RATE = 130, 
		TURN_SPEED = 30,
		MOVE_DIST = 30;
	
	private final DifferentialPilot pilot;
	private final LightSensor leftSensor;
	private final LightSensor rightSensor;
	private final TouchSensor touchSensor;

	private GridDirection direction;

	private boolean hasBumped;

	public LocalisationMover(DifferentialPilot differentialPilot,
			LightSensor leftSensor, LightSensor rightSensor,
			TouchSensor touchSensor, GridDirection initialDirection) {
		this.pilot = differentialPilot;
		this.leftSensor = leftSensor;
		this.rightSensor = rightSensor;
		this.touchSensor = touchSensor;
		this.direction = initialDirection;

		hasBumped = false;
	}

	/** 
	 * RTGADFGS DFHSF HDFG HDFG HSF GHSTFH SDF HSF HDFG HSFG HSFG HFG HSFG HSGF HSGF HSG HSFG HS GHSF HTR HS FUCK FUCK FUCK FUCK FUCK SGHSF HSFH FS HSF HFG HSF GHSF HSG HF JHSF HS FHSF HFGH DFGH DFGHSFG HS HGH
	 */
	public void attemptMove(GridDirection dir) {
		pilot.setTravelSpeed(FORWARD_SPEED);
		pilot.travel(MOVE_DIST);
		pilot.rotate(convert(dir).getTheta());
		direction = dir;

		pilot.forward();

		hasBumped = false;
		boolean running = true;
		while (running) {
			if(touchSensor.isPressed()) {
				hasBumped = true;
			}
			
			switch(checkSensors()) {
			case BOTH_BLACK:
				pilot.stop();
				running = false;
				
				break;
				
			case LEFT_BLACK:
				pilot.setTravelSpeed(TURN_SPEED);
				
				if(hasBumped) {
					pilot.steerBackward(TURN_RATE);
				} else {
					pilot.steer(TURN_RATE);
				}
				
				break;
				
			case RIGHT_BLACK:
				pilot.setTravelSpeed(TURN_SPEED);
				
				if(hasBumped) {
					pilot.steerBackward(-TURN_RATE);
				} else {
					pilot.steer(-TURN_RATE);
				}
				
				break;
				
			case NOTHING:
				pilot.setTravelSpeed(FORWARD_SPEED);
				
				if(hasBumped) {
					pilot.backward();
				} else {
					pilot.forward();
				}
				
				break;
				
			default:
				throw new SebastianInternalException();
			}
		}
	}

	private SensorResult checkSensors() {
		return SensorResult.checkSensors(leftSensor, rightSensor, LEFT_THRESHOLD, RIGHT_THRESHOLD);
	}

	private PathAction convert(GridDirection action) {
		switch (action) {
		case DOWN:
			switch (direction) {
			case DOWN:
				return PathAction.STRAIGHT;
			case LEFT:
				return PathAction.LEFT;
			case RIGHT:
				return PathAction.RIGHT;
			case UP:
				return PathAction.U_TURN;
			}

		case LEFT:
			switch (direction) {
			case DOWN:
				return PathAction.RIGHT;
			case LEFT:
				return PathAction.STRAIGHT;
			case RIGHT:
				return PathAction.U_TURN;
			case UP:
				return PathAction.LEFT;
			}

		case RIGHT:
			switch (direction) {
			case DOWN:
				return PathAction.LEFT;
			case LEFT:
				return PathAction.U_TURN;
			case RIGHT:
				return PathAction.STRAIGHT;
			case UP:
				return PathAction.RIGHT;
			}

		case UP:
			switch (direction) {
			case DOWN:
				return PathAction.U_TURN;
			case LEFT:
				return PathAction.RIGHT;
			case RIGHT:
				return PathAction.LEFT;
			case UP:
				return PathAction.STRAIGHT;
			}

		default:
			throw new SebastianInternalException();
		}
	}

	public GridDirection getCurrentDir() {
		return direction;
	}

	public boolean bumped() {
		return hasBumped;
	}
}
