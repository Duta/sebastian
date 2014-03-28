package localisation;

import util.SensorsUtil;
import lejos.nxt.addon.OpticalDistanceSensor;
import lejos.robotics.RegulatedMotor;
import lejos.util.Delay;
import grid.GridDirection;

/**
 * Reads values from a distance sensor
 */
public class SensorReader {
	private static final GridDirection[] dirs = {
		GridDirection.UP,
		GridDirection.RIGHT,
		GridDirection.DOWN,
		GridDirection.LEFT
	};
	
	private LocalisationMover mover;
	private RegulatedMotor sensorMotor;
	private OpticalDistanceSensor sensor;
	
	public SensorReader(LocalisationMover mover, RegulatedMotor sensorMotor, OpticalDistanceSensor sensor) {
		this.mover = mover;
		this.sensorMotor = sensorMotor;
		this.sensor = sensor;
	}

	/**
	 * Reads the sensor values
	 * @param readings The array to read the values into.
	 */
	public void read(double[] readings) {
		GridDirection robotDir = mover.getCurrentDir();
		int dirIndex = 0;
		for(; dirIndex < dirs.length; dirIndex++) {
			if(dirs[dirIndex].equals(robotDir)) {
				break;
			}
		}
		
		for(int i = 0; i < dirs.length; i++) {
			int index = (i + dirIndex) % dirs.length;
			readings[dirs[index].index] = sensor.getRange();
			sensorMotor.rotate(i < dirs.length - 1 ? 90 : -270);
			Delay.msDelay(500);
		}
	}
}
