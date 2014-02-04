package part2;

import java.util.ArrayList;
import java.util.List;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.robotics.navigation.DifferentialPilot;
import util.ButtonUtil;
import util.RobotInfo;

public class GridBot {
	private static final int THRESHOLD = 450;
	private static final int TURN_SPEED = 130;

	private DifferentialPilot pilot;
	private LightSensor leftSensor;
	private LightSensor rightSensor;
	private List<PathAction> path;
	
	private enum PathAction {
		LEFT,
		RIGHT,
		STRAIGHT;
	}
	
	private enum SensorResult {
		LEFT_BLACK,
		RIGHT_BLACK,
		BOTH_BLACK,
		NOTHING;
	}

	public GridBot(DifferentialPilot differentialPilot,
			LightSensor leftSensor, LightSensor rightSensor, String string) {
		this.pilot = differentialPilot;
		this.leftSensor = leftSensor;
		this.rightSensor = rightSensor;
		
		this.path = parsePath(string);
	}
	
	public void run() {
		ButtonUtil.exitOnEscapePress();
		
		pilot.setTravelSpeed(80);
		pilot.forward();
		
		while(true) {
			switch(checkSensors()) {
			case BOTH_BLACK:
				junction();
				break;
				
			case LEFT_BLACK:
				pilot.setTravelSpeed(30);
				pilot.steer(-TURN_SPEED);
				break;
			
			case RIGHT_BLACK:
				pilot.setTravelSpeed(30);
				pilot.steer(TURN_SPEED);
				break;
				
			case NOTHING:
				pilot.setTravelSpeed(80);
				pilot.forward();
				break;
			
			default:
				throw new RuntimeException("Well, shit D:");
			
			}
		}
	}
	
	private void junction() {
		if(path.isEmpty()) {
			System.exit(0);
		}
		
		switch(path.remove(0)) {
		case LEFT:
			pilot.travel(50);
			pilot.rotate(-90);
			break;
			
		case RIGHT:
			pilot.travel(50);
			pilot.rotate(90);
			break;
			
		case STRAIGHT:
			pilot.travel(50);
			break;
			
		default:
			throw new RuntimeException("Well, shit D:");
		
		}
	}

	private SensorResult checkSensors() {
		int lValue = leftSensor.getNormalizedLightValue();
		int rValue = rightSensor.getNormalizedLightValue();
		
		LCD.clear();
		LCD.drawInt(lValue, 2, 2);
		LCD.drawInt(rValue, 2, 4);
		
		if(lValue < THRESHOLD && rValue < THRESHOLD) {
			return SensorResult.BOTH_BLACK;
		}
		
		if(lValue < THRESHOLD) {
			return SensorResult.LEFT_BLACK;
		}
		
		if(rValue < THRESHOLD) {
			return SensorResult.RIGHT_BLACK;
		}
		
		return SensorResult.NOTHING;
	}
	
	private static List<PathAction> parsePath(String string) {
		List<PathAction> path = new ArrayList<PathAction>();
		
		for(char c : string.toCharArray()) {
			switch(c) {
			case 'L':
				path.add(PathAction.LEFT);
				break;
				
			case 'R':
				path.add(PathAction.RIGHT);
				break;
				
			case 'S':
				path.add(PathAction.STRAIGHT);
				break;
				
			default:
				throw new IllegalArgumentException("'" + c + "' is invalid");
			}
		}
		
		return path;
	}
	
	public static void main(String[] args) {
		System.out.println("Hello World!");
		System.out.println("Press any button to activate me!");
		
		Button.waitForAnyPress();
		
		// The RobotInfo class contains the useful robot-related information and functions.
		// (In the RobotUtils project, package util.RobotInfo)
		GridBot gridBot = new GridBot(
				RobotInfo.SEBASTIAN.getDifferentialPilot(),
				new LightSensor(SensorPort.S4),
				new LightSensor(SensorPort.S1),
				"SSRSLSSR");
		gridBot.run();

	}
	we've copied all your code you should have locked your laptop!!!!!

}
