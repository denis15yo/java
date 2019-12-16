package essenses;

public class Worker extends Human {
    private String post;
    private int salary;

    public Worker() {
        super();
        post = "";
        salary = 0;
    }

    public Worker(String surname) {
        super(surname);
    }

    public Worker(String surname, String post, int salary) {
        super(surname);
        this.post = post;
        this.salary = salary;
    }

    public String getPost() {
        return post;
    }

    public int getSalary() {
        return salary;
    }

    public String getSurname(){
        return surname;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
    public void setSurname(String surname){
        this.surname = surname;
    }

    @Override
    public int calcSkill() {
        return salary/330;
    }
}
