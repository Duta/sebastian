package localisation;

import localisation.interfaces.ActionModel;
import localisation.interfaces.SensorModel;
import grid.Grid;

public class Localiser {
	public static Coordinate localise(Grid grid, SensorModel sensorModel, ActionModel actionModel, boolean debug) {
		ProbabilityDistribution probs = new ProbabilityDistribution(grid);
		
		int step = 1;
		while(!probs.locationFound()) {
			if(debug) {
				printProbabilities(grid, probs);
				
				System.out.println();
				System.out.println("Step: " + step);
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
		
		if(debug) {
			printProbabilities(grid, probs);
		}
		
		return probs.getProbableLocation();
	}

	private static void printProbabilities(Grid grid, ProbabilityDistribution probs) {
		for(int y = 0; y < grid.getHeight(); y++) {
			for(int x = 0; x < grid.getWidth(); x++) {
				System.out.printf("%.3f ", probs.getProbability(x, y));
			}
			System.out.println();
		}
	}
	
	public static Coordinate localise(Grid grid, SensorModel sensorModel, ActionModel actionModel) {
		return localise(grid, sensorModel, actionModel, false);
	}
}
