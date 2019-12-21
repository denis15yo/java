package essenses;

public class SecurityGuard extends Worker {
    private static final double BASE = 2;
    private static final double CONST1 = 3;

    private int guardedArea;

    public int getGuardedArea() {
        return guardedArea;
    }

    public void setGuardedArea(int guardedArea) {
        this.guardedArea = guardedArea;
    }

    public SecurityGuard() {
        super();
        guardedArea = 0;
    }

    @Override
    public double calcSalary() {
        return BASE * getJobRatio() * guardedArea / CONST1;
    }

    @Override
    public String toString() {
        return super.toString() + String.format("%-6d", guardedArea);
    }
}
