package localisation;

import util.SebastianInternalException;
import localisation.interfaces.ActionModel;
import localisation.interfaces.SensorModel;
import grid.Grid;
import grid.GridDirection;

public class ProbabilityDistribution {
	private final double[][] probabilities;
	private final Grid grid;
	
	private static final double THRESHOLD = 0.8;
	private double maxProb;
	
	public ProbabilityDistribution(Grid grid) {
		this.grid = grid;
		this.probabilities = new double[grid.getWidth()][grid.getHeight()];
		
		// Thanks Bertie for these beautifully horrific for loop designs ;)
		for(int x = 0; x < probabilities.length; x++)
			for(int y = 0; y < probabilities[0].length; y++)
				probabilities[x][y] = 1.0;
		
		normalise();
	}

	public void normalise() {
		double total = 0;
		maxProb = 0;
		
		for(int x = 0; x < probabilities.length; x++)
			for(int y = 0; y < probabilities[0].length; y++)
				total += probabilities[x][y];
		
		for(int x = 0; x < probabilities.length; x++)
			for(int y = 0; y < probabilities[0].length; y++) {
				probabilities[x][y] *= (1.0 / total);
				maxProb = Math.max(maxProb, probabilities[x][y]);
			}
				
	}
	
	public boolean locationFound() {
		return maxProb > THRESHOLD;
	}
	
	public void adjustAfterSense(SensorModel sensorModel) {
		// TODO Auto-generated method stub
		
	}

	public void adjustAfterMove(ActionModel actionModel) {
		GridDirection dir = actionModel.lastAction();
		
		switch(dir) {
		case DOWN:
			for(int x = 0; x < probabilities.length; x++)
			for(int y = probabilities[0].length - 1; y >= 0; y--)
				changeProbability(dir, x, y);
			
			break;

		case LEFT:
			for(int x = 0; x < probabilities.length; x++)
			for(int y = 0; y < probabilities[0].length; y++)
				changeProbability(dir, x, y);
				
			break;
			
		case RIGHT:
			for(int x = probabilities.length - 1; x >= 0; x--)
			for(int y = 0; y < probabilities[0].length; y++)
				changeProbability(dir, x, y);
				
			break;
			
		case UP:
			for(int x = 0; x < probabilities.length; x++)
			for(int y = 0; y < probabilities[0].length; y++)
				changeProbability(dir, x, y);
				
			break;
			
		default:
			throw new SebastianInternalException("Fuck You");
		}
		
		normalise();
		
	}

	private void changeProbability(GridDirection dir, int x, int y) {
		double prob = 0;
		
		if(grid.canGo(x - dir.dx, y - dir.dy, dir)) {
			prob += probabilities[x + dir.dy][y - dir.dy];
		}
		
		if(!grid.canGo(x, y, dir)) {
			prob += probabilities[x][y];
		}
		
		probabilities[x][y] = prob;
	}
}
