package part3;

import java.util.ArrayList;
import java.util.List;
import lejos.nxt.Button;
import lejos.nxt.ButtonListener;
import lejos.nxt.LCD;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.util.Delay;
import util.LCDUtil;
import util.RobotInfo;

public class Part3 implements Runnable {
	private static final int WALL_HUG_DIST = 5;

	private DifferentialPilot pilot;
	private UltrasonicSensor leftSensor;
	private TouchSensor bumperA, bumperB;

	public Part3(DifferentialPilot pilot, UltrasonicSensor leftSensor,
			TouchSensor bumperA, TouchSensor bumperB) {
		this.pilot = pilot;
		this.leftSensor = leftSensor;
		this.bumperA = bumperA;
		this.bumperB = bumperB;
	}

	@Override
	public void run() {
		Button.ESCAPE.addButtonListener(new ButtonListener() {
			@Override
			public void buttonPressed(Button b) {
				System.exit(0);
			}

			@Override
			public void buttonReleased(Button b) {
			}
		});

		pilot.setTravelSpeed(150);
		pilot.forward();

		while (true) {
			if (isBumperPressed()) {
				wallHit();
			}

			pilot.steer(-50);

			Delay.msDelay(20);

		}
	}

	private void wallHit() {
		pilot.travel(-50);
		pilot.rotate(40);
		pilot.forward();
	}

	private int removeAnomaliesAndGetAverage(List<Integer> list) {
		int sum = 0;
		for (int i = 0; i < list.size(); i++) {
			sum += list.get(i);
		}

		int mean = sum / list.size();
		int meandiffs = 0;

		for (int i = 0; i < list.size(); i++) {
			meandiffs += ((list.get(i) - mean) * (list.get(i) - mean));
		}

		double sigma = Math.sqrt(meandiffs / list.size());

		boolean changed = false;
		for (int i = 0; i < list.size(); i++) {
			if (Math.abs(list.get(i) - mean) > sigma) {
				list.remove(i);
				i--;
				changed = true;
			}
		}

		if (changed) {
			return removeAnomaliesAndGetAverage(list);
		} else {
			return mean;
		}

	}

	private int getSensorDistance(UltrasonicSensor sensor, int numChecks,
			int timeInterval) {
		List<Integer> vals = new ArrayList<Integer>();
		for (int i = 0; i < numChecks; i++) {
			vals.add(sensor.getDistance());
			Delay.msDelay(timeInterval);
		}
		return removeAnomaliesAndGetAverage(vals);
	}

	private boolean isBumperPressed() {
		return bumperA.isPressed() || bumperB.isPressed();
	}

	public static void main(String[] args) {
		System.out.println("Press any button to activate me!");
		LCDUtil.intensify();
		LCDUtil.doge();

		Button.waitForAnyPress();
		LCD.clear();

		Part3 p = new Part3(RobotInfo.SEBASTIAN.getDifferentialPilot(),
				new UltrasonicSensor(SensorPort.S3), new TouchSensor(
						SensorPort.S1), new TouchSensor(SensorPort.S4));
		p.run();
	}

}
