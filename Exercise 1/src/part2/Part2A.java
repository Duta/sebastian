package part2;

import util.ButtonUtil;
import util.RobotInfo;
import util.Util;
import lejos.nxt.LCD;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.util.Delay;

public class Part2A {
	private DifferentialPilot pilot;
	private TouchSensor bumperA;
	private TouchSensor bumperB;
	
	public Part2A(DifferentialPilot pilot, TouchSensor bumperA, TouchSensor bumperB) {
		this.pilot = pilot;
		this.bumperA = bumperA;
		this.bumperB = bumperB;
	}
	
	public void run() {
		ButtonUtil.exitOnEscapePress();
		
		pilot.forward();
		while(true) {
			boolean bumperPressed = isBumperPressed();
			
			LCD.clear();
			LCD.drawString("Bumper pressed: ", 0, 0);
			LCD.drawString(
				bumperPressed ? "Yup!" : "NOPE.",
				5, 1, bumperPressed);
			
			// Methods to draw silly things to the screen.
			LCDUtil.doge();
			LCDUtil.intensify();
			
			if(bumperPressed) {
				pilot.stop();
				pilot.travel(-100);
				pilot.rotate(90);
				pilot.forward();
			} else {
				Delay.msDelay(20);
			}
		}
	}
	
	private boolean isBumperPressed() {
		return bumperA.isPressed() || bumperB.isPressed();
	}
	
	private void updateScreen(boolean bumperPressed) {
		LCD.clear();
		LCD.drawString("Bumper pressed: ", 0, 0);
		LCD.drawString(
			bumperPressed ? "Yup!" : "NOPE.",
			5, 1, bumperPressed);
	}

	public static void main(String[] args) {
		Util.waitForStart();
		
		Part2A p = new Part2A(
			RobotInfo.SEBASTIAN.getDifferentialPilot(),
			new TouchSensor(SensorPort.S1),
			new TouchSensor(SensorPort.S4));
		p.run();
	}

}
