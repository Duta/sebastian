package localisation;

import util.ButtonUtil;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;

/**
 * A simple program to test light sensor readings
 * Used to calibrate thresholds for grid following.
 */
public class LightSensorTest {
	public static void main(String[] args) {
		LightSensor left = new LightSensor(SensorPort.S1);
		LightSensor right = new LightSensor(SensorPort.S4);
		ButtonUtil.exitOnEscapePress();
		
		while(true) {
			System.out.println("Left: " + left.getNormalizedLightValue());
			System.out.println("Right: " + right.getNormalizedLightValue());
		}
	}
}
