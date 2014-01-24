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
		TIME_LIM = 5,
		VALUES_LIM = 10;
	
	private DifferentialPilot pilot;
	private UltrasonicSensor leftSensor;
	private UltrasonicSensor forwardSensor;
	private List<Integer> leftValues;
	private List<Integer> forwardValues;
	private int timer;
	
	public Part3(DifferentialPilot pilot, UltrasonicSensor leftSensor,
			UltrasonicSensor rightSensor) {
		this.pilot = pilot;
		this.leftSensor = leftSensor;
		this.forwardSensor = rightSensor;
		this.leftValues = new ArrayList<Integer>();
		this.forwardValues = new ArrayList<Integer>();
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
			int leftValue = leftSensor.getDistance();
			int forwardValue = forwardSensor.getDistance();
			
			leftValues.add(leftValue);
			forwardValues.add(forwardValue);
			
			if(leftValues.size() > VALUES_LIM
			&& forwardValues.size() > VALUES_LIM) {
				leftValues.remove(0);
				forwardValues.remove(0);
				leftValue = removeAnomaliesAndGetAverage(leftValues);
				forwardValue = removeAnomaliesAndGetAverage(forwardValues);
				
				if(forwardValue < DIST_LIM) {
					pilot.rotate(-90);
					pilot.forward();
				} else if(leftValue > DIST_LIM) {
					pilot.steer(50, 90);
					pilot.forward();
				} else {
					pilot.forward();
				}
			}
			
			Delay.msDelay(25);
		}
	}
	
	private void chooseNewDirection2() {
		int interval = 10;
		int[] values = new int[180/interval + 1];
		for(int i = 0; i < values.length; i++) {
			pilot.rotate(i == 0 ? -90 : interval);
			int leftVal = getSensorDistance(leftSensor, 10, 10);
			int rightVal = getSensorDistance(forwardSensor, 10, 10);
			values[i] = (leftVal + rightVal)/2;
		}
		int diagLim = (int) Math.ceil(Math.sqrt(2*DIST_LIM*DIST_LIM));
		List<Region> regions = new ArrayList<Region>();
		Region currentRegion = null;
		for(int i = 0; i < values.length; i++) {
			if(values[i] >= diagLim) {
				if(currentRegion == null) {
					currentRegion = new Region(i, i);
					regions.add(currentRegion);
				} else {
					currentRegion.setEnd(i);
				}
			} else {
				currentRegion = null;
			}
		}
		if(regions.isEmpty()) {
			pilot.rotate(90);
			// Could maybe remove this:
			chooseNewDirection2();
		} else {
			Region best = null;
			for(Region region : regions) {
				if(best == null) {
					best = region;
				} else {
					int regionLength = region.getEnd() - region.getStart();
					int bestLength = best.getEnd() - best.getStart(); 
					if(regionLength > bestLength) {
						best = region;
					}
				}
			}
			int mid;
			if(best.getStart() == 0) {
				mid = 0;
			} else if(best.getEnd() == values.length - 1) {
				mid = best.getEnd();
			} else {
				mid = (best.getStart() + best.getEnd()) / 2;
			}
			int theta = interval*(mid - (values.length - 1));
			pilot.rotate(theta);
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
		rightDist = getSensorDistance(forwardSensor, 10, 20);
		LCD.drawInt(rightDist, 12, 2);
		pilot.rotate(-180);
		leftDist = getSensorDistance(leftSensor, 10, 20);
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

	private int getSensorDistance(UltrasonicSensor sensor, int numChecks, int timeInterval) {
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
				new UltrasonicSensor(SensorPort.S3),
				new UltrasonicSensor(SensorPort.S2));
		p.run();
	}

}
