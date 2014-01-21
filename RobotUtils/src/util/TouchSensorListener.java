package util;

import lejos.nxt.SensorPort;
import lejos.nxt.SensorPortListener;

public abstract class TouchSensorListener implements SensorPortListener {
	
	private boolean wasPressed;

	@Override
	public void stateChanged(SensorPort source, int oldValue, int newValue) {
		boolean pressed = isPressed(newValue);
		if(pressed && !wasPressed) {
			pressed();
		}
		if(!pressed && wasPressed) {
			released();
		}
		wasPressed = pressed;
	}
	
	private boolean isPressed(int value) {
		return value < (183+1023)/2;
	}
	
	public abstract void pressed();
	public abstract void released();

}
