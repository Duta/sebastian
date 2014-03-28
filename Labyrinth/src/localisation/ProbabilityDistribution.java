package localisation;

import util.Util;
import grid.Grid;
import grid.GridState;

/**
 * Represents the probabilities of being at each location in a 
 * grid.
 */
public class ProbabilityDistribution {
	private final double[][] probabilities;
	private final Grid grid;
	
	private static final double THRESHOLD = 0.5;
	private double maxProb;
	
	/**
	 * Constructs a new distribution on a given grid
	 */
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

	/**
	 * Makes the probabilities sum to 1.0
	 */
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
	
	/**
	 * @return True if the probable location has been found.
	 */
	public boolean locationFound() {
		return maxProb > THRESHOLD;
	}
	
	/**
	 * Sets the probability at a specific location
	 * DOES NOT NORMALISE
	 */
	public void setProbability(int x, int y, double prob) {
		probabilities[x][y] = Math.max(0, prob);
	}

	/**
	 * Gets the probability at a specific location
	 */
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

	/**
	 * Returns the current most likely location. If there is more than one,
	 * then it returns the first found.
	 */
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
