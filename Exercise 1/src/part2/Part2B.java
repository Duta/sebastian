package part2;

import util.RobotInfo;
import util.TouchSensorListener;
import util.Util;
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
		Util.waitForStart();
		
		Part2B p = new Part2B(
			RobotInfo.SEBASTIAN.getDifferentialPilot(),
			SensorPort.S1,
			SensorPort.S4);
		p.run();
	}

}
