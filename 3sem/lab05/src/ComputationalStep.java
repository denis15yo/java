public class ComputationalStep {
    private int k;
    private double term;
    private double sum;

    public int getK() {
        return k;
    }

    public double getTerm() {
        return term;
    }

    public double getSum() {
        return sum;
    }

    public ComputationalStep(int k, double term, double sum) {
        this.k = k;
        this.term = term;
        this.sum = sum;
    }
}
