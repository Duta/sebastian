package localisation;

import localisation.interfaces.ActionModel;
import localisation.interfaces.SensorModel;
import grid.Grid;

public class Localiser {
	public static void localise(Grid grid, SensorModel sensorModel, ActionModel actionModel, boolean debug) {
		ProbabilityDistribution probs = new ProbabilityDistribution(grid);
		
		while(!probs.locationFound()) {
			if(debug) {
				printProbabilities(grid, probs);
			}
			
			// TODO ALL THINGS:
			
			// Get sensor input.
			sensorModel.sense(); // TODO DOES NOTHING YET
			
			// Adjust probabilities based on sensors.
			sensorModel.adjustProbabilities(probs); // TODO DOES NOTHING YET
			
			// Move in a random direction (except walls / places been already (unless necessary))
			actionModel.takeAction(sensorModel); // TODO DOES NOTHING YET
			
			if(debug) {
				System.out.println("Action Taken: " + actionModel.lastAction());
			}
			
			// Adjust probabilities based on movement. 
			actionModel.adjustProbabilities(probs);
		}
		
		if(debug) {
			printProbabilities(grid, probs);
		}
	}

	private static void printProbabilities(Grid grid, ProbabilityDistribution probs) {
		for(int y = 0; y < grid.getHeight(); y++) {
			for(int x = 0; x < grid.getWidth(); x++) {
				System.out.printf("%.3f ", probs.getProbability(x, y));
			}
			System.out.println();
		}
	}
	
	public static void localise(Grid grid, SensorModel sensorModel, ActionModel actionModel) {
		localise(grid, sensorModel, actionModel, false);
	}
}
