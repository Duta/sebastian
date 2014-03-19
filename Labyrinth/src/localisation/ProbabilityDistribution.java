package localisation;

import grid.Grid;

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

	public Coordinate getProbableLocation() {
		for(int x = 0; x < probabilities.length; x++) {
			for(int y = 0; y < probabilities[0].length; y++) {
				if(probabilities[x][y] == maxProb) {
					return new Coordinate(x, y);
				}
			}
		}
		
		return null;
	}

	
}
