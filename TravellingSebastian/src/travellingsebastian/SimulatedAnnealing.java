package travellingsebastian;

import grid.GridState;
import util.Util;

import java.util.Arrays;

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

    public void improve(Path path) {
        temperature = initialTemperature;
        while(temperature > coolTemperature) {
            improveOnce(path);
            temperature *= 1 - coolingRate;
        }
    }

    private void improveOnce(Path path) {
        GridState[] states = path.getStates();
        int numStates = states.length;
        GridState[] newStates = Arrays.copyOf(states, numStates);
        int i = Util.RGEN.nextInt(numStates);
        int j = Util.RGEN.nextInt(numStates);
        Util.swap(newStates, i, j);
        int distance = path.getLength();
        path.setStates(newStates);
        int newDistance = path.getLength();
        if(!shouldAcceptChange(distance, newDistance)) {
            path.setStates(states);
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
