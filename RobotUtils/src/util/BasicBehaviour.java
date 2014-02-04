package util;

import lejos.robotics.subsumption.Behavior;

public abstract class BasicBehaviour implements Behavior {
    protected RobotInfo robot;
    private boolean suppressed;

    public BasicBehaviour(RobotInfo robot) {
        this.robot = robot;
    }

    public boolean takeControl() {
        return true;
    }

    public void action() {
        beginAction();
        while(!suppressed && actionCondition()) {
            Thread.yield();
        }
        endAction();
        suppressed = false;
    }

    public void suppress() {
        suppressed = true;
    }

    protected void beginAction() {}

    protected boolean actionCondition() {
        return true;
    }

    protected void endAction() {}
}
