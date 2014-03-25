package localisation;

import grid.Grid;
import grid.GridDirection;
import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.nxt.addon.OpticalDistanceSensor;
import lejos.util.Delay;
import rp.robotics.mapping.LocalisationUtils;
import util.ButtonUtil;
import util.RobotInfo;
import util.Util;

public class RobotMain {
	public static void main(String[] args) {
		Util.waitForStart();
		ButtonUtil.exitOnEscapePress();
		
		RobotInfo robot = RobotInfo.SEBASTIAN;
		
		LocalisationMover lMover = new LocalisationMover(
			robot.getDifferentialPilot(), 
			new LightSensor(SensorPort.S4), 
			new LightSensor(SensorPort.S1), 
			new TouchSensor(SensorPort.S3),
			GridDirection.UP
		);
		
		SensorReader sensorReader = new SensorReader(
			lMover, 
			Motor.C,
			new OpticalDistanceSensor(SensorPort.S2)
		);
		
		Grid g = new Grid(LocalisationUtils.create2014Map1());
		Coordinate loc = Localiser.localise(
			g, 
			new RobotSensorModel(g, sensorReader),
			//new DummySensorModel(),
			new RobotActionModel(g, lMover), 
			true
		);
		
		System.out.println("Location Found: " + loc);
		
		while(true) {
			Delay.msDelay(20);
		}
	}
}
