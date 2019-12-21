package essenses;

public abstract class Worker {
    private String surname;
    private String organization;
    private int jobRatio;
    public abstract double calcSalary();

    public Worker() {
        surname = "";
        organization = "";
        jobRatio = 0;
    }

    public String getSurname() {
        return surname;
    }

    public String getOrganization() {
        return organization;
    }

    public int getJobRatio() {
        return jobRatio;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public void setJobRatio(int jobRatio) {
        this.jobRatio = jobRatio;
    }

    @Override
    public String toString() {
        return String.format("%-15s%-15s%-6d%-10.2f", surname, organization, jobRatio, calcSalary());
    }
}
