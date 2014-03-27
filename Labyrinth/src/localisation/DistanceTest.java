package localisation;

import util.ButtonUtil;
import lejos.nxt.SensorPort;
import lejos.nxt.addon.OpticalDistanceSensor;
import lejos.util.Delay;

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
