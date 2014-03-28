package localisation;

import grid.GridState;
import rp.robotics.mapping.LocalisationUtils;

/**
 * Class for testing localisation off the robot.
 */
public class Test {
	public static void main(String[] args) {
		TestGrid g = new TestGrid(LocalisationUtils.create2014Map1());
		GridState loc = Localiser.localise(g, new TestSensorModel(g), new TestActionModel(g), true);
		
		System.out.println("Location Found: " + loc);
		System.out.println("Actual Location: " + g.getLocation());
	}
}
