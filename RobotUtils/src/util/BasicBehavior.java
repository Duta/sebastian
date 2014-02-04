import lejos.robots.subsumption.Behavior;

public abstract class BasicBehavior implements Behavior {
    protected RobotInfo robot;
    private boolean suppressed;

    public BasicBehavior(RobotInfo robot) {
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
