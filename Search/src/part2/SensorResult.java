package part2;

import lejos.nxt.LightSensor;

/**
 * Represents a state of two light sensors.
 */
public enum SensorResult {
    LEFT_BLACK,
    RIGHT_BLACK,
    BOTH_BLACK,
    NOTHING;

    public static SensorResult checkSensors(LightSensor left,
            LightSensor right, int threshold) {
        int leftVal = left.getNormalizedLightValue();
        int rightVal = right.getNormalizedLightValue();

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
