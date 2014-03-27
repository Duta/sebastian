package localisation;

import util.ArrayUtil;
import util.RobotInfo;
import util.SebastianInternalException;
import util.Util;
import grid.Grid;
import grid.GridDirection;
import localisation.interfaces.ActionModel;
import localisation.interfaces.SensorModel;

public class RobotActionModel implements ActionModel {
	private GridDirection lastAction;
	private Grid grid;
	private LocalisationMover robot;
	
	public RobotActionModel(Grid grid, LocalisationMover robot) {
		this.grid = grid;
		this.robot = robot;
		
		lastAction = GridDirection.UP;
	}
	
	@Override
	public void takeAction(SensorModel sensorModel) {
		/*
		if(robot.bumped() || Util.RGEN.nextDouble() < 0.15) {
			lastAction = ArrayUtil.randomChoice(GridDirection.values());
		}
		*/
		robot.attemptMove(lastAction);
	}

	@Override
	public GridDirection lastAction() {
		return lastAction;
	}

	@Override
	public void adjustProbabilities(ProbabilityDistribution probs) {
		switch(lastAction) {
		case DOWN:
			for(int x = 0; x < probs.getWidth(); x++)
			for(int y = probs.getHeight() - 1; y >= 0; y--)
				changeProbability(probs, x, y);
			
			break;

		case LEFT:
			for(int x = 0; x < probs.getWidth(); x++)
			for(int y = 0; y < probs.getHeight(); y++)
				changeProbability(probs, x, y);
				
			break;
			
		case RIGHT:
			for(int x = probs.getWidth() - 1; x >= 0; x--)
			for(int y = 0; y < probs.getHeight(); y++)
				changeProbability(probs, x, y);
				
			break;
			
		case UP:
			for(int x = 0; x < probs.getWidth(); x++)
			for(int y = 0; y < probs.getHeight(); y++)
				changeProbability(probs, x, y);
				
			break;
			
		default:
			throw new SebastianInternalException("Fuck You");
		}
		
		probs.normalise();
	}

	private void changeProbability(ProbabilityDistribution probs, int x, int y) {
		double prob = 0;
		
		if(probs.getGrid().canGo(x - lastAction.dx, y - lastAction.dy, lastAction)) {
			prob += probs.getProbability(x - lastAction.dx, y - lastAction.dy);
		}
		
		if(!probs.getGrid().canGo(x, y, lastAction)) {
			prob += probs.getProbability(x, y);
		}
		
		probs.setProbability(x, y, prob);
	}
}
