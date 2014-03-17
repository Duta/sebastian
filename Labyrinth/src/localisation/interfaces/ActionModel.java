package localisation.interfaces;

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
}
