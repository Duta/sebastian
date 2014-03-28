package localisation;

import util.ArrayUtil;
import util.SebastianInternalException;
import grid.GridDirection;
import localisation.interfaces.ActionModel;
import localisation.interfaces.SensorModel;

/**
 * An action model that makes entirely random moves
 * (Not on a robot)
 */
public class RandomActionModel implements ActionModel {
	GridDirection lastAction; 
	@Override
	public void takeAction(SensorModel sensorModel) {
	    lastAction = ArrayUtil.randomChoice(GridDirection.values());
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
