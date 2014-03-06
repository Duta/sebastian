package util;

import java.util.ArrayList;
import java.util.List;

import lejos.nxt.UltrasonicSensor;
import lejos.util.Delay;

public class SensorsUtil {
    public static int getSensorDistance(UltrasonicSensor sensor, int numChecks, int timeInterval) {
        List<Integer> vals = new ArrayList<Integer>();
        for(int i = 0; i < numChecks; i++) {
            vals.add(sensor.getDistance());
            Delay.msDelay(timeInterval);
        }
        return StatsUtil.removeAnomaliesAndGetAverage(vals);
    }
}
