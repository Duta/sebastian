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

	public static <T> T randomChoice(T[] array) {
		return array[RGEN.nextInt(array.length)];
	}

    public static <T> void randomize(T[] array) {
        randomize(array, array.length * 2);
    }

    public static <T> void randomize(T[] array, int numSwaps) {
        int len = array.length;
        for(int i = 0; i < numSwaps; i++) {
            swap(array, RGEN.nextInt(len), RGEN.nextInt(len));
        }
    }

    public static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
