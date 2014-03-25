package localisation;

import util.ButtonUtil;
import lejos.nxt.SensorPort;
import lejos.nxt.addon.OpticalDistanceSensor;

public class DistanceTest {
	public static void main(String[] args) {
		OpticalDistanceSensor sensor = new OpticalDistanceSensor(SensorPort.S2);
		ButtonUtil.exitOnEscapePress();
		
		while(true) {
			System.out.println(sensor.getDistance() / 10);
		}
	}
}
