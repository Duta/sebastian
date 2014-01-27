package util;

import lejos.nxt.Button;
import lejos.nxt.LCD;

public class Util {
	public static void waitForStart() {
		LCD.clear();
		LCDUtil.anyButtonActivation();
		LCDUtil.intensify();
		LCDUtil.doge();
		
		Button.waitForAnyPress();
		
		LCD.clear();
	}
}
