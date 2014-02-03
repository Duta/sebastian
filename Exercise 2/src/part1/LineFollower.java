package part1;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.util.Delay;
import util.ButtonUtil;
import util.RobotInfo;

public class LineFollower {
	private DifferentialPilot pilot;
	private LightSensor leftSensor;
	private LightSensor rightSensor;
	
	private static final int TURN_SPEED = 130;
	private static final int BACKWARD_TURN_SPEED = 260;
	private static final int THRESHOLD = 450;
	
	private enum SensorResult {
		LEFT_BLACK,
		RIGHT_BLACK,
		BOTH_BLACK,
		NOTHING;
	};

	public LineFollower(DifferentialPilot pilot, LightSensor leftSensor, LightSensor rightSensor) {
		this.pilot = pilot;
		this.leftSensor = leftSensor;
		this.rightSensor = rightSensor;
	}
	
	public void run() {
		ButtonUtil.exitOnEscapePress();
		
		pilot.setTravelSpeed(130);
		
		SensorResult lastHit = SensorResult.NOTHING;
		
		while(true) {
			switch(checkSensors()) {					
				case LEFT_BLACK:
					pilot.setTravelSpeed(30);
					pilot.steer(-TURN_SPEED);
					
					lastHit = SensorResult.LEFT_BLACK;
					break;
									
				case RIGHT_BLACK:
					pilot.setTravelSpeed(30);
					pilot.steer(TURN_SPEED);
					
					lastHit = SensorResult.RIGHT_BLACK;
					break;
				
				case NOTHING:
					pilot.setTravelSpeed(80);
					pilot.forward();
					break;
					
				case BOTH_BLACK:
					pilot.setTravelSpeed(30);
					
					do {
						switch(lastHit) {
							case LEFT_BLACK:
								pilot.steerBackward(BACKWARD_TURN_SPEED);
								break;
								
							case RIGHT_BLACK:
								pilot.steerBackward(-BACKWARD_TURN_SPEED);
								break;
								
							default:
								pilot.backward();
						}
					
					} while(checkSensors() != SensorResult.NOTHING);
					
					break;
					
				default:
					break;
					
			}
			
			Delay.msDelay(20);
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

	public static void main(String[] args) {
		System.out.println("Hello World!");
		System.out.println("Press any button to activate me!");
		
		Button.waitForAnyPress();
		
		// The RobotInfo class contains the useful robot-related information and functions.
		// (In the RobotUtils project, package util.RobotInfo)
		LineFollower p = new LineFollower(
				RobotInfo.SEBASTIAN.getDifferentialPilot(),
				new LightSensor(SensorPort.S4),
				new LightSensor(SensorPort.S1));
		p.run();

	}

}


