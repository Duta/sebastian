package localisation;

import util.Util;
import grid.Grid;
import grid.GridState;

public class ProbabilityDistribution {
	private final double[][] probabilities;
	private final Grid grid;
	
	private static final double THRESHOLD = 0.5;
	private double maxProb;
	
	public ProbabilityDistribution(Grid grid) {
		this.grid = grid;
		this.probabilities = new double[getWidth()][getHeight()];
		
		for(int x = 0; x < getWidth(); x++) {
			for(int y = 0; y < getHeight(); y++) {
				probabilities[x][y] = 1.0;
			}
		}
		
		normalise();
	}

	public void normalise() {
		double total = 0;
		maxProb = 0;
		
		for(int x = 0; x < getWidth(); x++) {
			for(int y = 0; y < getHeight(); y++) {
				total += probabilities[x][y];
			}
		}
		
		for(int x = 0; x < getWidth(); x++) {
			for(int y = 0; y < getHeight(); y++) {
				probabilities[x][y] *= 1.0 / total;
				maxProb = Math.max(maxProb, probabilities[x][y]);
			}
		}
	}
	
	public boolean locationFound() {
		return maxProb > THRESHOLD;
	}
	
	public void setProbability(int x, int y, double prob) {
		probabilities[x][y] = Math.max(0, prob);
	}

	public double getProbability(int x, int y) {
		return probabilities[x][y];
	}

	public int getWidth() {
		return grid.getWidth();
	}

	public int getHeight() {
		return grid.getHeight();
	}

	public Grid getGrid() {
		return grid;
	}

	public GridState getProbableLocation() {
		for(int x = 0; x < getWidth(); x++) {
			for(int y = 0; y < getHeight(); y++) {
				if(Util.equal(probabilities[x][y], maxProb)) {
					return new GridState(grid, x, y);
				}
			}
		}
		
		return null;
	}

	public double getMaxProbability() {
		return maxProb;
	}
	
	public double getThreshold() {
		return THRESHOLD;
	}
}
