package localisation;

import util.ButtonUtil;
import lejos.nxt.SensorPort;
import lejos.nxt.addon.OpticalDistanceSensor;
import lejos.util.Delay;

/**
 * A program for testing the distance sensor readings
 */
public class DistanceTest {
	public static void main(String[] args) {
		OpticalDistanceSensor sensor = new OpticalDistanceSensor(SensorPort.S2);
		ButtonUtil.exitOnEscapePress();
		
		while(true) {
			System.out.println(sensor.getRange());
			Delay.msDelay(20);
		}
	}
}
