package util;

import lejos.nxt.Motor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.navigation.DifferentialPilot;

/**
 * Contains useful robot-related information and functions.
 */
public enum RobotInfo {
	SEBASTIAN(56, 56, 126, Motor.B, Motor.A);
	
	public final double LEFT_WHEEL;
	public final double RIGHT_WHEEL;
	public final double TRACK_WIDTH;
	public RegulatedMotor LEFT_MOTOR;
	public RegulatedMotor RIGHT_MOTOR;
	public final boolean REVERSE;
	
	RobotInfo(double leftWheel, double rightWheel, double trackWidth, RegulatedMotor leftMotor, RegulatedMotor rightMotor, boolean reverse) {
		this.LEFT_WHEEL = leftWheel;
		this.RIGHT_WHEEL = rightWheel;
		this.TRACK_WIDTH = trackWidth;
		this.LEFT_MOTOR = leftMotor;
		this.RIGHT_MOTOR = rightMotor;
		this.REVERSE = reverse;
		
	}
	
	RobotInfo(double leftWheel, double rightWheel, double trackWidth, RegulatedMotor leftMotor, RegulatedMotor rightMotor) {
		this(leftWheel, rightWheel, trackWidth, leftMotor, rightMotor, false);
	}

	
	public DifferentialPilot getDifferentialPilot() {
		return new DifferentialPilot(LEFT_WHEEL, RIGHT_WHEEL, TRACK_WIDTH, LEFT_MOTOR, RIGHT_MOTOR, REVERSE);
	}
	
}
