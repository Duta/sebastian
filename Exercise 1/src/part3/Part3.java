package part3;

import java.util.ArrayList;
import java.util.List;

import lejos.nxt.Button;
import lejos.nxt.ButtonListener;
import lejos.nxt.LCD;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.util.Delay;
import util.LCDUtil;
import util.RobotInfo;

public class Part3 implements Runnable {
	private static final int
		DIST_LIM = 25,
		TIME_LIM = 5;
	
	private DifferentialPilot pilot;
	private UltrasonicSensor sensor;
	private int timer;
	
	public Part3(DifferentialPilot pilot, UltrasonicSensor sensor) {
		this.pilot = pilot;
		this.sensor = sensor;
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
			if(sensor.getDistance() < DIST_LIM) {
				timer++;
				if(timer > TIME_LIM) {
					chooseNewDirection();
					pilot.forward();
					timer = 0;
				}
			} else {
				timer = 0;
			}
			Delay.msDelay(25);
		}
	}

	private void chooseNewDirection() {
		LCD.clear();
		LCD.drawString("Picking new direction...", 0, 0);
		LCD.drawString("Left  dist: ", 0, 1);
		LCD.drawString("Right dist: ", 0, 2);
		LCD.drawString("Decision: ", 0, 3);
		int leftDist, rightDist;
		pilot.rotate(90);
		rightDist = getSensorDistance(10, 20);
		LCD.drawInt(rightDist, 12, 2);
		pilot.rotate(-180);
		leftDist = getSensorDistance(10, 20);
		LCD.drawInt(leftDist, 12, 1);
		if(leftDist < DIST_LIM && rightDist < DIST_LIM) {
			pilot.rotate(-90);
			LCD.drawString("BACK", 10, 3);
		} else if(rightDist > leftDist) {
			pilot.rotate(180);
			LCD.drawString("RIGHT", 10, 3);
		} else {
			LCD.drawString("LEFT", 10, 3);
		}
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

	private int getSensorDistance(int numChecks, int timeInterval) {
		List<Integer> vals = new ArrayList<Integer>();
		for(int i = 0; i < numChecks; i++) {
			vals.add(sensor.getDistance());
			Delay.msDelay(timeInterval);
		}

		return removeAnomaliesAndGetAverage(vals);
				
	}

	public static void main(String[] args) {
		System.out.println("Press any button to activate me!");
		LCDUtil.intensify();
		
		Button.waitForAnyPress();
		
		Part3 p = new Part3(
				RobotInfo.SEBASTIAN.getDifferentialPilot(),
				new UltrasonicSensor(SensorPort.S2));
		p.run();
	}

}
