package part1;

import java.util.ArrayList;
import java.util.List;

import util.ButtonUtil;
import util.RobotInfo;
import lejos.nxt.Button;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.util.Delay;

public class DistanceKeeper {
	private final double K = 5;
	
	private final int requiredDistance;
	private DifferentialPilot pilot;
	private UltrasonicSensor sensor;
	
	/**
	 * @param pilot The differential pilot
	 * @param sensor The sensor to detect distance with
	 * @param requiredDistance The required distance to maintain from an object - in cm.
	 */
	public DistanceKeeper(DifferentialPilot pilot, UltrasonicSensor sensor, int requiredDistance) {
		this.pilot = pilot;
		this.sensor = sensor;
		this.requiredDistance = requiredDistance;
	}
	
	public void run() {
		ButtonUtil.exitOnEscapePress();
	
		while(true) {
			int currDist = getSensorDistance(sensor, 10, 2);
			int distDiff = currDist - requiredDistance;
			
			pilot.setTravelSpeed(Math.abs(K * distDiff));
			if(distDiff > 0) {
				pilot.forward();
			} else {
				pilot.backward();
			}
		}
	}
	
	public static int removeAnomaliesAndGetAverage(List<Integer> list) {
		int sum = 0;
		for(int i = 0; i < list.size(); i++) {
			sum += list.get(i); 
		}

		int mean = sum / list.size();
		int meandiffs = 0;

		for(int i = 0; i < list.size(); i++) {
			meandiffs += ((list.get(i) - mean) * (list.get(i) - mean));
		}

		double sigma = Math.sqrt(meandiffs / list.size());

		boolean changed = false;
		for(int i = 0; i < list.size(); i++) {
			if(Math.abs(list.get(i) - mean) > sigma) {
				list.remove(i);
				i--;
				changed = true;
			}
		}

		if(changed) {
			return removeAnomaliesAndGetAverage(list);
		} else {
			return mean;
		}

	}
	
	public static int getSensorDistance(UltrasonicSensor sensor, int numChecks, int timeInterval) {
		List<Integer> vals = new ArrayList<Integer>();
		for(int i = 0; i < numChecks; i++) {
			vals.add(sensor.getDistance());
			Delay.msDelay(timeInterval);
		}
		return removeAnomaliesAndGetAverage(vals);
	}
	
	public static void main(String[] args) {
		System.out.println("Hello World!");
		System.out.println("Press any button to activate me!");
		
		Button.waitForAnyPress();
		
		// The RobotInfo class contains the useful robot-related information and functions.
		// (In the RobotUtils project, package util.RobotInfo)
		DistanceKeeper p = new DistanceKeeper(
				RobotInfo.SEBASTIAN.getDifferentialPilot(),
				new UltrasonicSensor(SensorPort.S2),
				50);
		p.run();
	}

}
