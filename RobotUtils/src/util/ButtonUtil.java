package util;

import lejos.nxt.Button;

public class ButtonUtil {
	public static void exitOnEscapePress() {
		Button.ESCAPE.addButtonListener(new ExitOnButtonPressListener());
	}
}
