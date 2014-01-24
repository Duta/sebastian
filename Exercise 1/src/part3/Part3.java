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
	private static final int DIST_LIM = 20;
	
	private DifferentialPilot pilot;
	private UltrasonicSensor leftSensor, rightSensor;
	private TouchSensor bumperA, bumperB;
	
	public Part3(DifferentialPilot pilot,
			UltrasonicSensor leftSensor, UltrasonicSensor rightSensor,
			TouchSensor bumperA, TouchSensor bumperB) {
		this.pilot = pilot;
		this.leftSensor = leftSensor;
		this.rightSensor = rightSensor;
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
			public void buttonReleased(Button b) {}
		});
		
		pilot.forward();
		
		while(true) {
			if(isBumperPressed()) {
				/*if(!bumperB.isPressed()) {
					pilot.travel(-10);
					pilot.rotate(15);
					pilot.forward();
				} else if(!bumperA.isPressed()) {
					pilot.travel(-10);
					pilot.rotate(-15);
					pilot.forward();
				} else {
					chooseNewDirection();
				}*/
				System.out.println("My jimmies are rustled");
				pilot.stop();
				chooseNewDirection();
			}
			
			Delay.msDelay(20);
		}
	}
	
	private void chooseNewDirection() {
		int left = getSensorDistance(leftSensor, 10, 20);
		int right = getSensorDistance(rightSensor, 10, 20);
		System.out.println();
		System.out.println("=CHOOSE DIR=");
		System.out.println("left=" + left + ", right=" + right);
		
		if(left < DIST_LIM && right < DIST_LIM) {
			System.out.println("DEAD END");
			Button.waitForAnyPress();
			// Dead end
			pilot.backward();
			while(left < DIST_LIM && right < DIST_LIM) {
				Delay.msDelay(25);
				if(left >= DIST_LIM || right >= DIST_LIM) {
					left = getSensorDistance(leftSensor, 20, 20);
					right = getSensorDistance(rightSensor, 20, 20);
				}
			}
			pilot.stop();
			pilot.rotate(left < DIST_LIM ? -90 : 90);
		} else if(left >= DIST_LIM && right >= DIST_LIM) {
			// Two options
			System.out.println("TWO OPTIONS");
			Button.waitForAnyPress();
			pilot.travel(-15);
			pilot.rotate(90);
		} else {
			System.out.println(left < DIST_LIM  ? "RIGHT" : "LEFT");
			Button.waitForAnyPress();
			pilot.travel(-15);
			pilot.rotate(left < DIST_LIM ? -90 : 90);
		}
		pilot.forward();
	}

	private int removeAnomaliesAndGetAverage(List<Integer> list) {
		int sum = 0;
		for(int i = 0; i < list.size(); i++) {
			sum += list.get(i); 
		}
		
		int mean = sum / list.size();
		int meandiffs = 0;
		
		for(int i = 0; i < list.size(); i++) {
			meandiffs += ((list.get(i) - mean) * (list.get(i) - mean));
		}
		
		double sigma = Math.sqrt(meandiffs / list.size());
		
		boolean changed = false;
		for(int i = 0; i < list.size(); i++) {
			if(Math.abs(list.get(i) - mean) > sigma) {
				list.remove(i);
				i--;
				changed = true;
			}
		}
		
		if(changed) {
			return removeAnomaliesAndGetAverage(list);
		} else {
			return mean;
		}

	}

	private int getSensorDistance(UltrasonicSensor sensor, int numChecks, int timeInterval) {
		List<Integer> vals = new ArrayList<Integer>();
		for(int i = 0; i < numChecks; i++) {
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
		
		Part3 p = new Part3(
				RobotInfo.SEBASTIAN.getDifferentialPilot(),
				new UltrasonicSensor(SensorPort.S3),
				new UltrasonicSensor(SensorPort.S2),
				new TouchSensor(SensorPort.S1),
				new TouchSensor(SensorPort.S4));
		p.run();/*
		final DifferentialPilot pilot = RobotInfo.SEBASTIAN.getDifferentialPilot();
		Button.LEFT.addButtonListener(new ButtonListener() {
			@Override
			public void buttonPressed(Button b) {}

			@Override
			public void buttonReleased(Button b) {
				pilot.rotate(90);
			}
		});
		Button.RIGHT.addButtonListener(new ButtonListener() {
			@Override
			public void buttonPressed(Button b) {}

			@Override
			public void buttonReleased(Button b) {
				pilot.rotate(-90);
			}
		});
		Button.ESCAPE.waitForPressAndRelease();*/
	}

}
