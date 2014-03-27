package util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import lejos.nxt.Button;
import lejos.nxt.LCD;

public class Util {
    public static final Random RGEN = new Random();
    
    public static void waitForStart() {
        LCD.clear();
        LCDUtil.anyButtonActivation();
        LCDUtil.intensify();
        LCDUtil.doge();
        
        Button.waitForAnyPress();
        
        LCD.clear();
    }
    
    public static int round(double n) {
        return (int)(n + (n < 0 ? -0.5 : 0.5));
    }
    
    public static boolean equal(double d1, double d2) {
    	return equal(d1, d2, 0.001);
    }
    
    public static boolean equal(double d1, double d2, double epsilon) {
    	return Math.abs(d1 - d2) < epsilon;
    	// or  -epsilon < d1 - d2 && d1 - d2 < epsilon
    }

	public static <T> List<T> asList(T... items) {
		List<T> list = new ArrayList<T>();
		for(T t : items) {
			list.add(t);
		}
		return list;
	}
}
