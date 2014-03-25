package util;

import java.util.ArrayList;
import java.util.List;

import lejos.nxt.UltrasonicSensor;
import lejos.robotics.RangeFinder;
import lejos.util.Delay;

public class SensorsUtil {
    public static int getSensorDistance(RangeFinder sensor, int numChecks, int timeInterval) {
        List<Float> vals = new ArrayList<Float>();
        for(int i = 0; i < numChecks; i++) {
            vals.add(sensor.getRange());
            Delay.msDelay(timeInterval);
        }
        return StatsUtil.removeAnomaliesAndGetAverage(vals);
    }
}
