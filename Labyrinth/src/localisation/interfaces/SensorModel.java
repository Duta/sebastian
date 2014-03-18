package localisation.interfaces;

import localisation.ProbabilityDistribution;
import grid.GridDirection;

public interface SensorModel {
	/**
	 * Takes distance readings in all four directions and stores them.
	 */
	void sense();
	
	/**
	 * Returns the result of the last sensor reading in a given direction.
	 */
	double getResult(GridDirection dir);

	/** 
	 * Adjusts the probabilites in the given distribution based on
	 * the previous sensor readings.
	 */
	void adjustProbabilities(ProbabilityDistribution probs);
}
