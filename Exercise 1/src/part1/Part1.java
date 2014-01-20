package part1;

import robots.RobotInfo;
import lejos.nxt.Button;
import lejos.nxt.ButtonListener;
import lejos.nxt.LCD;
import lejos.robotics.navigation.DifferentialPilot;

public class Part1 {
	private Shape[] shapes = {Shape.SQUARE, Shape.TRIANGLE, Shape.PENTAGON, Shape.CIRCLE};
	private int shapeIndex = 0;
	private DifferentialPilot pilot;
	
	public Part1(DifferentialPilot pilot) {
		this.pilot = pilot;
	}
	
	public void run() {
		LCD.clear();
		LCD.drawString("Press enter to", 0, 0);
		LCD.drawString("change shape!", 0, 1);
		LCD.drawString("Press back", 0, 3);
		LCD.drawString("to exit!", 0, 4);
		LCD.drawString("SEBASTIANNN!", 0, 7);
		
		Button.ENTER.addButtonListener(new ButtonListener() {
			@Override
			public void buttonPressed(Button b) {}

			@Override
			public void buttonReleased(Button b) {
				shapeIndex++;
				shapeIndex %= shapes.length;
			}
			
		});
		
		Button.ESCAPE.addButtonListener(new ButtonListener() {
			@Override
			public void buttonPressed(Button b) {}

			@Override
			public void buttonReleased(Button b) {
				System.exit(0);
			}
		});
		
		while(true) {
			Shape shape = getCurrentShape();
			pilot.travel(shape.dist);
			pilot.rotate(shape.theta);
		}
	}
	
	private Shape getCurrentShape() {
		return shapes[shapeIndex];
	}

	public static void main(String[] args) {
		System.out.println("Hello World!");
		System.out.println("Press any button to activate me!");
		
		Button.waitForAnyPress();
		
		Part1 p = new Part1(RobotInfo.SEBASTIAN.getDifferentialPilot());
		p.run();

	}

}
