package essenses;

public class Seller extends Worker {
    private static final int CONST_PERCENT = 30;

    private int revenue;

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    public Seller() {
        super();
        revenue = 0;
    }

    @Override
    public double calcSalary() {
        return (double)getJobRatio() * revenue * CONST_PERCENT / 100;
    }

    @Override
    public String toString() {
        return super.toString() + String.format("%-6d", revenue);
    }
}
