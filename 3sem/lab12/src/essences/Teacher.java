package essences;

public class Teacher extends Person {
    private int experience;
    private Subject subject;

    public Teacher() {
        super();
    }
    public Teacher(String surname, int age, Country country, int experience, Subject subject) {
        super(surname, age, country);
        this.experience = experience;
        this.subject = subject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Teacher)) return false;
        if (!super.equals(o)) return false;
        Teacher teacher = (Teacher) o;
        return experience == teacher.experience &&
                subject == teacher.subject;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "surname='" + surname + '\'' +
                ", age=" + age +
                ", country=" + country +
                ", experience=" + experience +
                ", subject=" + subject +
                '}';
    }

    public enum Subject{
        MATHEMATICAL_ANALYSIS, COMPUTER_SCIENCE, MATHEMATICAL_LOGIC;
    }
}
