package part3;

import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.util.Delay;
import util.ButtonUtil;
import util.RobotInfo;
import util.Util;

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
		ButtonUtil.exitOnEscapePress();

		pilot.setTravelSpeed(250);
		while(true) {
			if (isBumperPressed()) {
				pilot.travel(-50);
				pilot.rotate(40);
			}
			pilot.steer(-50);
			Delay.msDelay(20);
		}
	}

	private boolean isBumperPressed() {
		return bumperA.isPressed() || bumperB.isPressed();
	}

	public static void main(String[] args) {
		Util.waitForStart();

		Part3 p = new Part3(RobotInfo.SEBASTIAN.getDifferentialPilot(),
				new TouchSensor(SensorPort.S1),
				new TouchSensor(SensorPort.S4));
		p.run();
	}

}
