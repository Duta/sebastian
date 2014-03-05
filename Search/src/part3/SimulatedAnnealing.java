package part3;

import part1.grid.GridState;
import util.ArrayUtil;
import util.Util;

/**
 * Improves a tour of points on a
 * grid using simulated annealing.
 */
public class SimulatedAnnealing {
    private final double initialTemperature;
    private final double coolTemperature;
    private final double coolingRate;
    private double temperature;

    public SimulatedAnnealing(double initialTemperature,
            double coolTemperature, double coolingRate) {
        this.initialTemperature = initialTemperature;
        this.coolTemperature = coolTemperature;
        this.coolingRate = coolingRate;
    }

    public void improve(Tour tour) {
        temperature = initialTemperature;
        while(temperature > coolTemperature) {
            improveOnce(tour);
            temperature *= 1 - coolingRate;
        }
    }

    private void improveOnce(Tour tour) {
        GridState[] states = tour.getStates();
        int numStates = states.length;
        // Ugly, but leJOS only has Arrays.copyOf
        // for primitive arrays. I assume this is
        // because it doesn't have java.lang.reflect
        GridState[] newStates = new GridState[numStates];
        for(int i = 0; i < numStates; i++) {
            newStates[i] = states[i];
        }
        int i = ArrayUtil.randomIndex(newStates);
        int j = ArrayUtil.randomIndex(newStates);
        ArrayUtil.swap(newStates, i, j);
        int distance = tour.getLength();
        tour.setStates(newStates);
        int newDistance = tour.getLength();
        if(!shouldAcceptChange(distance, newDistance)) {
            tour.setStates(states);
        }
    }

    private boolean shouldAcceptChange(int distance, int newDistance) {
        if(newDistance < distance) {
            return true;
        }
        double probability = Math.exp((distance - newDistance)/temperature);
        return probability > Util.RGEN.nextDouble();
    }
}
