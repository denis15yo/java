package Series;

public class Exponential extends Series {
    public Exponential() {
    }
    public Exponential(double first, double step) {
        super(first, step);
    }

    @Override
    public double calcElementByIndex(int index) {
        return (first * (Math.pow(step, index - 1)));
    }
}
