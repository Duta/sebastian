package localisation;

import grid.Grid;
import grid.GridDirection;
import localisation.interfaces.SensorModel;

/**
 * Very basic sensor model on a robot
 * Was for testing purposes only, and not expected to properly work.
 */
public class RobotSensorModel implements SensorModel {
	private final double threshold = 10;
	private final double MAX_RANGE = 152;
	private final double MIN_RANGE = 28;

	private Grid grid;
	private double[] readings;
	private SensorReader sensor;

	public RobotSensorModel(Grid grid, SensorReader sensor) {
		this.grid = grid;
		this.readings = new double[4];
		this.sensor = sensor;
	}

	@Override
	public void sense() {
		sensor.read(readings);
	}

	@Override
	public double getResult(GridDirection dir) {
		return readings[dir.index];
	}

	@Override
	public void adjustProbabilities(ProbabilityDistribution probs) {
		for (int x = 0; x < probs.getWidth(); x++) {
			for (int y = 0; y < probs.getHeight(); y++) {
				boolean couldBeHere = true, outOfRange = false;
				for (GridDirection dir : GridDirection.values()) {
					if (Math.abs(readings[dir.index]
							- grid.distanceFromPoint(x, y, dir)) > threshold) {
						if (!((grid.distanceFromPoint(x, y, dir) > MAX_RANGE + threshold) 
								|| (grid.distanceFromPoint(x, y, dir) < MIN_RANGE - threshold))) {
							couldBeHere = false;
							break;
						}
					}

				}

				if (couldBeHere) {
					probs.setProbability(x, y, probs.getProbability(x, y)
							+ (outOfRange ? 0.01 : 0.1));
				} else {
					probs.setProbability(x, y, probs.getProbability(x, y) - 0.1);
				}
			}
		}

		probs.normalise();
	}
}
