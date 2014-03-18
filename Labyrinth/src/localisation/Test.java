package localisation;

import rp.robotics.mapping.LocalisationUtils;
import grid.Grid;

public class Test {
	public static void main(String[] args) {
		Grid g = new Grid(LocalisationUtils.create2014Map1());
		Localiser.localise(g, new DummySensorModel(), new RandomActionModel(), true);
	}
}
