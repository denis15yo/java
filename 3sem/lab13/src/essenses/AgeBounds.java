package essenses;

public class AgeBounds {
    private int min;
    private int max;

    public AgeBounds(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public String toString() {
        return "[" + min + ", " + max + "]";
    }

    public boolean check(AgeBounds o){
        return o.getMin() >= min && o.getMax() <= max;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }
}
