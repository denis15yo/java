package myUtil;

public class DoublePair {
    private double min;
    private double max;

    public DoublePair(double min, double max) {
        this.min = min;
        this.max = max;
    }

    public DoublePair() {
        min = 0;
        max = 0;
    }

    @Override
    public String toString() {
        return "min=" + min +
                ", max=" + max;
    }
}
