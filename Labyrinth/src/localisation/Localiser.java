package localisation;

import localisation.interfaces.ActionModel;
import localisation.interfaces.SensorModel;
import grid.Grid;
import grid.GridState;

/**
 * Performs markov localisation
 */
public class Localiser {
	/**
	 * @param grid The grid to localise in
	 * @param sensorModel The sensor model to use
	 * @param actionModel The action model to use
	 * @param debug If true, prints output to console (default false)
	 * @return The GridState containing the probable location.
	 */
	public static GridState localise(Grid grid, SensorModel sensorModel, ActionModel actionModel, boolean debug) {
		ProbabilityDistribution probs = new ProbabilityDistribution(grid);
		
		int step = 1;
		while(!probs.locationFound()) {
			if(debug) {
				System.out.println("Step: " + step);
				System.out.println("Prob: " + probs.getMaxProbability() + " / " + probs.getThreshold());
				System.out.println("Loc:  " + probs.getProbableLocation());
				System.out.println("---------");
			}

			// Get sensor input.
			sensorModel.sense();
			
			// Adjust probabilities based on sensors.
			sensorModel.adjustProbabilities(probs);
			
			// Move in a random direction (except walls / places been already (unless necessary))
			actionModel.takeAction(sensorModel);
			
			if(debug) {
				System.out.println("Action Taken: " + actionModel.lastAction());
			}
			
			// Adjust probabilities based on movement. 
			actionModel.adjustProbabilities(probs);
			
			step++;
		}
		
		return probs.getProbableLocation();
	}

	private static void printProbabilities(ProbabilityDistribution probs) {
		for(int y = 0; y < probs.getHeight(); y++) {
			for(int x = 0; x < probs.getWidth(); x++) {
				System.out.print(probs.getProbability(x, y) + ";  ");
			}
			System.out.println();
		}
	}
	
	public static GridState localise(Grid grid, SensorModel sensorModel, ActionModel actionModel) {
		return localise(grid, sensorModel, actionModel, false);
	}
}
