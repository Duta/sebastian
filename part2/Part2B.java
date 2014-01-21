package part2;

import robots.RobotInfo;
import lejos.nxt.Button;
import lejos.nxt.ButtonListener;
import lejos.nxt.LCD;
import lejos.nxt.SensorPort;
import lejos.nxt.SensorPortListener;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.util.Delay;

public class Part2B {
	private DifferentialPilot pilot;
	private SensorPort portA, portB;
	private int a, b;
	
	public Part2B(DifferentialPilot pilot, SensorPort portA, SensorPort portB) {
		this.pilot = pilot;
		this.portA = portA;
		this.portB = portB;
	}
	
	public void run() {
		Button.ESCAPE.addButtonListener(new ButtonListener() {
			@Override
			public void buttonPressed(Button b) {}

			@Override
			public void buttonReleased(Button b) {
				System.exit(0);
			}
		});
		
		SensorPortListener listener = new SensorPortListener() {
			@Override
			public void stateChanged(SensorPort source, int oldValue, int newValue) {
				if(newValue < oldValue) {
					pilot.stop();
					pilot.travel(-120);
					pilot.rotate(90);
					pilot.forward();
				}
				if(source == portA) {
					a = newValue;
				} else {
					b = newValue;
				}
				updateDisplay();
			}
		};
		
		updateDisplay();
		
		portA.addSensorPortListener(listener);
		portB.addSensorPortListener(listener);
		
		pilot.forward();
		
		while(true) { updateDisplay(); Delay.msDelay(17); }
	}

	private void updateDisplay() {
		LCD.clear();
		LCD.drawString("A: ", 0, 0);
		LCD.drawString("B: ", 0, 1);
		LCD.drawInt(a, 3, 0);
		LCD.drawInt(b, 3, 1);
		
		LCD.drawString("[SEBASTIAN       ", 0, 6, true);
		LCD.drawString("     INTENSIFIES]", 0, 7, true);
	}

	public static void main(String[] args) {
		System.out.println("Press any button to activate me!");
		LCD.drawString("[SEBASTIAN       ", 0, 6, true);
		LCD.drawString("     INTENSIFIES]", 0, 7, true);
		
		Button.waitForAnyPress();
		
		Part2B p = new Part2B(
			RobotInfo.SEBASTIAN.getDifferentialPilot(),
			SensorPort.S1,
			SensorPort.S4);
		p.run();
	}

}
