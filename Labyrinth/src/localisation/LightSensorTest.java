package localisation;

import util.ButtonUtil;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;

public class LightSensorTest {
	public static void main(String[] args) {
		LightSensor left = new LightSensor(SensorPort.S4);
		LightSensor right = new LightSensor(SensorPort.S3);
		ButtonUtil.exitOnEscapePress();
		
		while(true) {
			System.out.println("Left: " + left.getNormalizedLightValue());
			System.out.println("Right: " + right.getNormalizedLightValue());
		}
	}
}
