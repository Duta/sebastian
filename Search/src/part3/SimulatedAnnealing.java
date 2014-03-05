package part3;

import part1.grid.GridState;
import util.ArrayUtil;
import util.Util;

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
        	//System.out.println("Temp: " + temperature + ", Cool Temp: " + coolTemperature);
            improveOnce(path);
            temperature *= 1 - coolingRate;
        }
    }

    private void improveOnce(Path path) {
        GridState[] states = path.getStates();
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
