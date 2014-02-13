package part1;

import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.util.Delay;
import shared.SensorResult;
import util.ButtonUtil;
import util.RobotInfo;
import util.Util;

public class LineFollower implements Runnable {
	private static final int
		TURN_RATE = 130,
		BACKWARD_TURN_RATE = 260,
		FORWARD_SPEED = 80,
		TURN_SPEED = 30,
		BACKWARD_SPEED = 30,
		THRESHOLD = 450;

	private DifferentialPilot pilot;
	private LightSensor leftSensor;
	private LightSensor rightSensor;
	
	public LineFollower(DifferentialPilot pilot, LightSensor leftSensor, LightSensor rightSensor) {
		this.pilot = pilot;
		this.leftSensor = leftSensor;
		this.rightSensor = rightSensor;
	}
	
	@Override
	public void run() {
		ButtonUtil.exitOnEscapePress();
		
		SensorResult lastSide = SensorResult.NOTHING;
		SensorResult prevResult = null;
		
		while(true) {
			SensorResult result = SensorResult.checkSensors(leftSensor, rightSensor, THRESHOLD);
			if(result != prevResult) {
				switch(result) {
					case LEFT_BLACK:
						pilot.setTravelSpeed(TURN_SPEED);
						pilot.steer(-TURN_RATE);
						
						lastSide = SensorResult.LEFT_BLACK;
						break;
										
					case RIGHT_BLACK:
						pilot.setTravelSpeed(TURN_SPEED);
						pilot.steer(TURN_RATE);
						
						lastSide = SensorResult.RIGHT_BLACK;
						break;
					
					case NOTHING:
						pilot.setTravelSpeed(FORWARD_SPEED);
						pilot.forward();
						break;
						
					case BOTH_BLACK:
						pilot.setTravelSpeed(BACKWARD_SPEED);
						do {
							switch(lastSide) {
								case LEFT_BLACK:
									pilot.steerBackward(BACKWARD_TURN_RATE);
									break;
									
								case RIGHT_BLACK:
									pilot.steerBackward(-BACKWARD_TURN_RATE);
									break;
									
								default:
									pilot.backward();
							}
						} while(SensorResult.checkSensors(leftSensor, rightSensor, THRESHOLD) != SensorResult.NOTHING);
						break;
				}
			}
			prevResult = result;
			Delay.msDelay(20);
		}
	}

	public static void main(String[] args) {
		Util.waitForStart();
		
		LineFollower p = new LineFollower(
				RobotInfo.SEBASTIAN.getDifferentialPilot(),
				new LightSensor(SensorPort.S4),
				new LightSensor(SensorPort.S1));
		p.run();

	}

}


