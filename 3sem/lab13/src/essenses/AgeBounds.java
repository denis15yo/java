package essenses;

public class AgeBounds {
    private int min;
    private int max;

    public AgeBounds(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public AgeBounds() {
        min = 0;
        max = 100;
    }

    @Override
    public String toString() {
        return "[" + min + ", " + max + "]";
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public void setMax(int max) {
        this.max = max;
    }
}
