package Series;

public class Linear extends Series {

    public Linear() {
    }
    public Linear(double first, double step) {
        super(first, step);
    }

    @Override
    public double calcElementByIndex(int index) {
        return (first + step * (index - 1));
    }
}
