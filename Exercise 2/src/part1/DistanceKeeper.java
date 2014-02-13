package part1;

import util.ButtonUtil;
import util.RobotInfo;
import util.SensorsUtil;
import util.Util;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.navigation.DifferentialPilot;

public class DistanceKeeper implements Runnable {
	private static final double K = 5;
	
	private final int requiredDistance;
	private DifferentialPilot pilot;
	private UltrasonicSensor sensor;
	
	/**
	 * @param pilot the differential pilot
	 * @param sensor the sensor to detect distance with
	 * @param requiredDistance the required distance to maintain from an object - in cm
	 */
	public DistanceKeeper(DifferentialPilot pilot, UltrasonicSensor sensor, int requiredDistance) {
		this.pilot = pilot;
		this.sensor = sensor;
		this.requiredDistance = requiredDistance;
	}
	
	@Override
	public void run() {
		ButtonUtil.exitOnEscapePress();
	
		while(true) {
			int currDist = SensorsUtil.getSensorDistance(sensor, 10, 2);
			int distDiff = currDist - requiredDistance;
			
			pilot.setTravelSpeed(Math.abs(K * distDiff));
			if(distDiff > 0) {
				pilot.forward();
			} else {
				pilot.backward();
			}
		}
	}
	
	public static void main(String[] args) {
		Util.waitForStart();
		
		DistanceKeeper p = new DistanceKeeper(
				RobotInfo.SEBASTIAN.getDifferentialPilot(),
				new UltrasonicSensor(SensorPort.S2),
				50);
		p.run();
	}

}
