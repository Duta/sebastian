package localisation;

import grid.GridDirection;
import localisation.interfaces.SensorModel;

/**
 * A sensor model that does literally nothing
 */
public class DummySensorModel implements SensorModel {
	@Override
	public void sense() {

	}

	@Override
	public double getResult(GridDirection dir) {
		return 0;
	}

	@Override
	public void adjustProbabilities(ProbabilityDistribution probs) {
		
	}

}
