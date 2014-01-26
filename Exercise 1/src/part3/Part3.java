package part3;

import lejos.nxt.Button;
import lejos.nxt.ButtonListener;
import lejos.nxt.LCD;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.util.Delay;
import util.LCDUtil;
import util.RobotInfo;

public class Part3 implements Runnable {
	private DifferentialPilot pilot;
	private TouchSensor bumperA, bumperB;

	public Part3(DifferentialPilot pilot,
			TouchSensor bumperA, TouchSensor bumperB) {
		this.pilot = pilot;
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

		/*
		 * Method for escaping maze:
		 * 
		 * Steer to the left.
		 * If wall hit -> rotate right.
		 * Repeat.
		 */
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
				new TouchSensor(SensorPort.S1), new TouchSensor(SensorPort.S4));
		p.run();
	}

}
