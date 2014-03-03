package util;

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
}
