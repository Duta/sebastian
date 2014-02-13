package shared;

import lejos.nxt.LCD;
import lejos.nxt.LightSensor;

public enum SensorResult {
	LEFT_BLACK,
	RIGHT_BLACK,
	BOTH_BLACK,
	NOTHING;
	
	public static SensorResult checkSensors(LightSensor left, LightSensor right, int threshold) {
		int leftVal = left.getNormalizedLightValue();
		int rightVal = right.getNormalizedLightValue();
		
		LCD.clear();
		LCD.drawInt(leftVal, 2, 2);
		LCD.drawInt(rightVal, 2, 4);
		
		if(leftVal < threshold && rightVal < threshold) {
			return SensorResult.BOTH_BLACK;
		}
		
		if(leftVal < threshold) {
			return SensorResult.LEFT_BLACK;
		}
		
		if(rightVal < threshold) {
			return SensorResult.RIGHT_BLACK;
		}
		
		return SensorResult.NOTHING;
	}
}
