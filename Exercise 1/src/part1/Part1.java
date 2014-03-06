package part1;

import util.ButtonUtil;
import util.RobotInfo;
import util.Util;
import lejos.nxt.Button;
import lejos.nxt.ButtonListener;
import lejos.nxt.LCD;
import lejos.robotics.navigation.DifferentialPilot;

public class Part1 {
    private Shape[] shapes = {Shape.SQUARE, Shape.TRIANGLE, Shape.PENTAGON};
    private int shapeIndex;
    private DifferentialPilot pilot;
    
    public Part1(DifferentialPilot pilot) {
        this.pilot = pilot;
    }
    
    public void run() {
        ButtonUtil.exitOnEscapePress();
        
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
                // Move to the next shape
                shapeIndex = (shapeIndex + 1) % shapes.length;
            }
        });
        
        while(true) {
            Shape shape = getCurrentShape();
            pilot.travel(shape.dist);
            pilot.rotate(shape.theta);
        }
    }
    
    private Shape getCurrentShape() {
        // Shapes contains the distance and angle to travel to make various shapes.
        return shapes[shapeIndex];
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");
        System.out.println("(Press any button)");
        Button.waitForAnyPress();
        Util.waitForStart();
        
        // The RobotInfo class contains the useful robot-related information and functions.
        // (In the RobotUtils project, package util.RobotInfo)
        Part1 p = new Part1(RobotInfo.SEBASTIAN.getDifferentialPilot());
        p.run();

    }

}
