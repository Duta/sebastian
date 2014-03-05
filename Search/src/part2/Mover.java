package part2;

import part1.grid.GridDirection;
import part1.grid.search.GridNode;

import java.util.Stack;

import lejos.nxt.LightSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.util.Delay;
import util.ButtonUtil;
import util.SebastianInternalException;

public class Mover implements Runnable {
	private static final int
		THRESHOLD = 450,
		FORWARD_SPEED = 120,
		TURN_RATE = 130,
		TURN_SPEED = 30;
	private static final GridDirection
		INITIAL_DIRECTION = GridDirection.DOWN;
	
	private final DifferentialPilot pilot;
	private final LightSensor leftSensor;
	private final LightSensor rightSensor;
	private final Stack<GridNode> path;
	
	private GridDirection direction = INITIAL_DIRECTION;
	
	public Mover(DifferentialPilot differentialPilot,
			LightSensor leftSensor, LightSensor rightSensor, Stack<GridNode> path) {
		this.pilot = differentialPilot;
		this.leftSensor = leftSensor;
		this.rightSensor = rightSensor;
		this.path = path;
	}

	@Override
	public void run() {
		ButtonUtil.exitOnEscapePress();
		
		boolean running = true;
		while(running) {
			Delay.msDelay(20);
			
			switch(SensorResult.checkSensors(leftSensor, rightSensor, THRESHOLD)) {
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
		
	@SuppressWarnings("incomplete-switch")
	private boolean junction() {
		if(path.empty()) {
			return false;
		}
		
		GridNode node = path.pop();
		
		System.out.println("Direction: " + direction + ", Action: " + node.getAction());
		PathAction action = convert(node.getAction());
		direction = node.getAction();
		
		pilot.travel(60);
		
		switch(action) {
		case LEFT:
			pilot.rotate(-90);
			break;
			
		case RIGHT:
			pilot.rotate(90);
			break;
			
		case U_TURN:
			pilot.rotate(180);
			break;
		}
		
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
