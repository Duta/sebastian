package util;

import lejos.nxt.LCD;

public class LCDUtil {
    public static void intensify() {
        LCD.drawString("[SEBASTIAN       ", 0, 6, true);
        LCD.drawString("     INTENSIFIES]", 0, 7, true);
    }

    public static void doge() {
        LCD.drawString("wow", 8, 2);
        LCD.drawString("much robot", 1, 3);
        LCD.drawString("such lejos", 5, 4);
        LCD.drawString("wow", 4, 5);
    }

    public static void anyButtonActivation() {
        LCD.drawString("Press any button", 0, 0);
        LCD.drawString("to activate me!", 0, 1);
    }
}
