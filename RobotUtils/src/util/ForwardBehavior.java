public class ForwardBehavior extends BasicBehavior {
    public ForwardBehavior(RobotInfo robot) {
        super(robot);
    }

    protected void beginAction() {
        robot.getPilot().forward();
    }

    protected void endAction() {
        robot.getPilot().stop
    }
}
