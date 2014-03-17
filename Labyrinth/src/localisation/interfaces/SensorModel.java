package localisation.interfaces;

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
}
