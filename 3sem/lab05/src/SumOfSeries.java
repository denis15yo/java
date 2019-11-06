public class SumOfSeries {
    private double x;
    private double eps;

    private double sum;

    private Report report;

    public SumOfSeries(){
        x = 0;
        eps = 0;
        sum = 0;
        report =  new Report();
    }
    public SumOfSeries(double x, double eps) {
        this.x = x;
        this.eps = eps;
        this.sum = 0;
        report = new Report();
    }

    public void calc() throws SeriesDivergesException{
        if(Math.abs(x) >= 3){
            throw new SeriesDivergesException("Ряд расходится при |x| >= 3.");
        }

        int k = 1;
        double term;
        double temp = 1;

        do{
            temp *= (x / 3);
            term = (k + 1) * temp;
            if(k % 2 == 1){
                term = -term;
            }
            sum += term;

            report.addData(new ComputationalStep(k, term, sum));

            ++k;
        } while (Math.abs(term) > eps);
    }

    public double getSum() {
        return sum;
    }
    public Report getReport() {
        return report;
    }
}
