package myUtil;

public class IntPair {
    private int min;
    private int max;

    public IntPair(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public String toString() {
        return "min=" + min +
                ", max=" + max;
    }
}
