package localisation;

import grid.GridDirection;
import localisation.interfaces.SensorModel;

public class TestSensorModel implements SensorModel {
	private TestGrid grid;
	private double[] readings;
	
	private final double threshold = 5;

	public TestSensorModel(TestGrid grid) {
		this.grid = grid;
		this.readings = new double[4];
	}

	@Override
	public void sense() {
		for(GridDirection dir : GridDirection.values()) {
			readings[dir.index] = grid.sense(dir);
		}
	}

	@Override
	public double getResult(GridDirection dir) {
		return readings[dir.index];
	}

	@Override
	public void adjustProbabilities(ProbabilityDistribution probs) {
		for(int x = 0; x < probs.getWidth(); x++) {
			for(int y = 0; y < probs.getHeight(); y++) {
				boolean couldBeHere = true;
				for(GridDirection dir : GridDirection.values()) {
					if(Math.abs(readings[dir.index] - grid.distanceFromPoint(x, y, dir)) > threshold) {
						couldBeHere = false;
						break;
					}
				}
				
				if(couldBeHere) {
					probs.setProbability(x, y, probs.getProbability(x, y) + 0.5);
				} else {
					probs.setProbability(x, y, probs.getProbability(x, y) - 0.5);
				}				
			}
		}
		
		probs.normalise();
	}
}
