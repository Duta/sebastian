package localisation;

import rp.robotics.mapping.LocalisationUtils;

public class Test {
	public static void main(String[] args) {
		TestGrid g = new TestGrid(LocalisationUtils.create2014Map1());
		Coordinate loc = Localiser.localise(g, new TestSensorModel(g), new TestActionModel(g), true);
		
		System.out.println("Location Found: " + loc);
		System.out.println("Actual Location: " + g.getLocation());
	}
}
