package util;

import lejos.nxt.SensorPort;
import lejos.nxt.SensorPortListener;

/**
 * This class wraps a SensorPortListener to make in more intuitive to use with a Touch Sensor.
 * Instead of having to override stateChanged(), and deal with oldValue and newValue, it does that for
 * you and provides the abstract methds pressed() and released() */
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
