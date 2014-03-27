package labyrinth;

import grid.Grid;
import grid.GridDirection;
import grid.GridNode;
import grid.GridSearcher;
import grid.GridState;

import java.util.List;
import java.util.Stack;

import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.nxt.addon.OpticalDistanceSensor;
import localisation.LocalisationMover;
import localisation.Localiser;
import localisation.RobotActionModel;
import localisation.RobotSensorModel;
import localisation.SensorReader;
import rp.robotics.mapping.LocalisationUtils;
import util.RobotInfo;
import util.Util;

public class Labyrinth implements Runnable {
	private Grid grid;
	private GridDirection initialDirection;
	private List<GridState> requiredStates;
	private RobotInfo robot;
	private LightSensor leftSensor;
	private LightSensor rightSensor;
	private TouchSensor touchSensor;


	public Labyrinth(Grid grid, GridDirection initialDirection,
			List<GridState> requiredStates, RobotInfo robot,
			LightSensor leftSensor, LightSensor rightSensor,
			TouchSensor touchSensor) {
		this.grid = grid;
		this.initialDirection = initialDirection;
		this.requiredStates = requiredStates;
		
		this.robot = robot;
		
		this.leftSensor = leftSensor;
		this.rightSensor = rightSensor;
		this.touchSensor = touchSensor;
	}

	@Override
	public void run() {
		// Localise
		GridState currentPosition = getAssumedPosition();
		
		// Navigate
		for(GridState requiredState : requiredStates) {
			move(currentPosition, requiredState);
			currentPosition = requiredState;
		}
	}
	
	private GridState getAssumedPosition() {
		LocalisationMover mover = new LocalisationMover(
				robot.getDifferentialPilot(), 
				leftSensor,
				rightSensor, 
				touchSensor,
				GridDirection.UP);
			
		SensorReader sensorReader = new SensorReader(
				mover,
				Motor.C,
				new OpticalDistanceSensor(SensorPort.S2));
		
		return Localiser.localise(
				grid,
				new RobotSensorModel(grid, sensorReader),
				new RobotActionModel(grid, mover),
				true);
	}
	
	private void move(GridState start, GridState goal) {
		GridSearcher searcher = new GridSearcher(
				grid,
				start.getX(),
				start.getY(),
				goal.getX(),
				goal.getY()) {
		    @Override
		    protected void processPathAction(GridNode start, GridNode goal, Stack<GridNode> path) {
		    	// Pop the start node, since the
		        // movement only cares the actions,
		        // which the start node doesn't have
		        path.pop();

		        // Make the robot follow the path
		        Mover mover = new Mover(
		                robot.getDifferentialPilot(),
		                leftSensor,
		                rightSensor,
		                path,
		                initialDirection);
		        mover.run();
		    }
		};
		searcher.search();
	}

	public static void main(String[] args) {
		Grid grid = new Grid(LocalisationUtils.create2014Map1());
		Labyrinth labyrinth = new Labyrinth(
				grid,
				GridDirection.UP,
				Util.asList(
					new GridState(grid, 3, 6),
					new GridState(grid, 1, 2)),
				RobotInfo.SEB_V2,
				new LightSensor(SensorPort.S4), 
				new LightSensor(SensorPort.S3), 
				new TouchSensor(SensorPort.S1));
		labyrinth.run();
	}
}
