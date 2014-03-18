package localisation.interfaces;

import localisation.ProbabilityDistribution;
import grid.GridDirection;

public interface ActionModel {
	/**
	 * Moves (or attempts to move) in a direction, based on the 
	 * provided sensor results.
	 */
	void takeAction(SensorModel sensorModel);
	
	/**
	 * Returns the direction last moved (or attempted to move in).
	 */
	GridDirection lastAction();

	/** 
	 * Adjusts the probabilites in the given distribution based on
	 * the previous action taken.
	 */
	void adjustProbabilities(ProbabilityDistribution probs);
}
