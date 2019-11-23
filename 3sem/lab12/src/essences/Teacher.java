package essences;

public class Teacher extends Person {
    private int salary;
    private Subject subject;

    public Teacher() {
        super();
    }
    public Teacher(String surname, int age, Country country, int salary, Subject subject) {
        super(surname, age, country);
        this.salary = salary;
        this.subject = subject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Teacher)) return false;
        if (!super.equals(o)) return false;
        Teacher teacher = (Teacher) o;
        return salary == teacher.salary &&
                subject == teacher.subject;
    }


    public enum Subject{
        MATHS, COMPUTER_SCIENCE, MATHEMATICAL_LOGIC;
    }
}
