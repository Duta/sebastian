package localisation;

import lejos.util.Delay;
import rp.robotics.mapping.LocalisationUtils;
import util.ButtonUtil;
import util.Util;

public class Test {
	public static void main(String[] args) {
		//Util.waitForStart();
		//ButtonUtil.exitOnEscapePress();
		
		TestGrid g = new TestGrid(LocalisationUtils.create2014Map1());
		Coordinate loc = Localiser.localise(g, new TestSensorModel(g), new TestActionModel(g), true);
		
		System.out.println("Location Found: " + loc);
		System.out.println("Actual Location: " + g.getLocation());
		
		//while(true) {
			//Delay.msDelay(20);
		//}
	}
}
