package util;

import lejos.nxt.Button;
import lejos.nxt.ButtonListener;

public class ExitOnButtonPressListener implements ButtonListener {
	@Override
	public void buttonPressed(Button b) {
		System.exit(0);
	}

	@Override
	public void buttonReleased(Button b) {}
}
