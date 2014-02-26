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

    public static <T> void randomize(T[] array) {
        randomize(array, array.length * 2);
    }

    private static <T> void randomize(T[] array, int numSwaps) {
        for(int i = 0; i < numSwaps; i++) {
            int a = RGEN.nextInt(array.length);
            int b = RGEN.nextInt(array.length);
            T temp = array[a];
            array[a] = array[b];
            array[b] = temp;
        }
    }
}
