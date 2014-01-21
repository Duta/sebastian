package part2;

import util.LCDUtil;
import util.RobotInfo;
import util.TouchSensorListener;
import lejos.nxt.Button;
import lejos.nxt.SensorPort;
import lejos.nxt.SensorPortListener;
import lejos.robotics.navigation.DifferentialPilot;

public class Part2B {
	private DifferentialPilot pilot;
	private SensorPort portA, portB;
	
	public Part2B(DifferentialPilot pilot, SensorPort portA, SensorPort portB) {
		this.pilot = pilot;
		this.portA = portA;
		this.portB = portB;
	}
	
	public void run() {
		SensorPortListener listener = new TouchSensorListener() {
			@Override
			public void pressed() {
				pilot.stop();
				pilot.travel(-120);
				pilot.rotate(90);
				pilot.forward();
			}

			@Override
			public void released() {}
		};
		
		portA.addSensorPortListener(listener);
		portB.addSensorPortListener(listener);
		
		pilot.forward();
		
		Button.ESCAPE.waitForPressAndRelease();
	}

	public static void main(String[] args) {
		System.out.println("Press any button to activate me!");
		LCDUtil.intensify();
		
		Button.waitForAnyPress();
		
		Part2B p = new Part2B(
			RobotInfo.SEBASTIAN.getDifferentialPilot(),
			SensorPort.S1,
			SensorPort.S4);
		p.run();
	}

}
