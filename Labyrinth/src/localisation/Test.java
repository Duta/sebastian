package localisation;

import grid.Grid;

public class Test {
	public static void main(String[] args) {
		Grid g = new Grid(11, 7);
		Localiser.localise(g, new DummySensorModel(), new RandomActionModel(), true);
	}
}
