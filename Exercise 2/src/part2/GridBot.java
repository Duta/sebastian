package part2;

import java.util.ArrayList;
import java.util.List;

import lejos.nxt.Button;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.robotics.navigation.DifferentialPilot;
import util.RobotInfo;

public class GridBot {
	private DifferentialPilot pilot;
	private LightSensor leftSensor;
	private LightSensor rightSensor;
	private List<PathActions> path;
	
	private enum PathActions {
		LEFT,
		RIGHT,
		STRAIGHT;
	}

	public GridBot(DifferentialPilot differentialPilot,
			LightSensor leftSensor, LightSensor rightSensor, String string) {
		this.pilot = differentialPilot;
		this.leftSensor = leftSensor;
		this.rightSensor = rightSensor;
		
		this.path = parsePath(string);
		
		
	}
	
	private static List<PathActions> parsePath(String string) {
		List<PathActions> path = new ArrayList<PathActions>();
		
		for(char c : string.toCharArray()) {
			switch(c) {
			case 'L':
				path.add(PathActions.LEFT);
				break;
				
			case 'R':
				path.add(PathActions.RIGHT);
				break;
				
			case 'S':
				path.add(PathActions.STRAIGHT);
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
		GridBot p = new GridBot(
				RobotInfo.SEBASTIAN.getDifferentialPilot(),
				new LightSensor(SensorPort.S4),
				new LightSensor(SensorPort.S1),
				"SSRSPLSSR");
		//p.run();

	}

}
