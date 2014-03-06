package part2;

import java.util.List;

import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.util.Delay;
import shared.SensorResult;
import util.ButtonUtil;
import util.RobotInfo;
import util.Util;

public class GridBot implements Runnable {
    private static final int
        THRESHOLD = 450,
        FORWARD_SPEED = 120,
        TURN_RATE = 130,
        TURN_SPEED = 30;

    private final DifferentialPilot pilot;
    private final LightSensor leftSensor;
    private final LightSensor rightSensor;
    private final List<PathAction> path;

    public GridBot(DifferentialPilot differentialPilot,
            LightSensor leftSensor, LightSensor rightSensor, List<PathAction> path) {
        this.pilot = differentialPilot;
        this.leftSensor = leftSensor;
        this.rightSensor = rightSensor;
        this.path = path;
    }
    
    @Override
    public void run() {
        ButtonUtil.exitOnEscapePress();
        
        while(true) {
            Delay.msDelay(20);
            
            switch(SensorResult.checkSensors(leftSensor, rightSensor, THRESHOLD)) {
            case LEFT_BLACK:
                pilot.setTravelSpeed(TURN_SPEED);
                pilot.steer(-TURN_RATE);
                break;
            
            case RIGHT_BLACK:
                pilot.setTravelSpeed(TURN_SPEED);
                pilot.steer(TURN_RATE);
                break;
                
            case NOTHING:
                pilot.setTravelSpeed(FORWARD_SPEED);
                pilot.forward();
                break;
                
            case BOTH_BLACK:
                junction();
                break;
            }
        }
    }
    
    @SuppressWarnings("incomplete-switch")
    private void junction() {
        if(path.isEmpty()) {
            System.exit(0);
        }
        
        PathAction action = path.remove(0);
        
        pilot.travel(60);
        
        switch(action) {
        case LEFT:
            pilot.rotate(-90);
            break;
            
        case RIGHT:
            pilot.rotate(90);
            break;
        }
    }
    
    public static void main(String[] args) {
        Util.waitForStart();
        
        GridBot gridBot = new GridBot(
                RobotInfo.SEBASTIAN.getDifferentialPilot(),
                new LightSensor(SensorPort.S4),
                new LightSensor(SensorPort.S1),
                PathAction.parse("SSRSLSSR"));
        gridBot.run();

    }

}
