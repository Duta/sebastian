package localisation;

import localisation.interfaces.ActionModel;
import localisation.interfaces.SensorModel;
import grid.Grid;

public class Localiser {
	public static void Localise(Grid grid, SensorModel sensorModel, ActionModel actionModel) {
		ProbabilityDistribution probs = new ProbabilityDistribution(grid);
		
		while(!probs.locationFound()) {
			// TODO ALL THINGS:
			
			// Get sensor input.
			sensorModel.sense(); // TODO DOES NOTHING YET
			
			// Adjust probabilities based on sensors.
			probs.adjustAfterSense(sensorModel); // TODO DOES NOTHING YET
			
			// Move in a random direction (except walls / places been already (unless necessary))
			actionModel.takeAction(sensorModel); // TODO DOES NOTHING YET
			
			// Adjust probabilities based on movement. 
			probs.adjustAfterMove(actionModel);
		}
	}
}
