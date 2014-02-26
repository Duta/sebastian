package util;

import java.util.Random;

import lejos.nxt.Button;
import lejos.nxt.LCD;

public class Util {
	private static final Random RGEN = new Random();
	
	public static void waitForStart() {
		LCD.clear();
		LCDUtil.anyButtonActivation();
		LCDUtil.intensify();
		LCDUtil.doge();
		
		Button.waitForAnyPress();
		
		LCD.clear();
	}

	public static <T> T randomChoice(T[] array) {
		return array[RGEN.nextInt(array.length)];
	}
}
