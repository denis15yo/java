package essences;

import enums.Gender;
import enums.Subject;

public class Teacher extends Person {
    private int experience;
    private Subject subject;

    public Teacher() {
        super();
    }
    public Teacher(String surname, int age, Gender gender, int experience, Subject subject) {
        super(surname, age, gender);
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
                ", country=" + gender +
                ", experience=" + experience +
                ", subject=" + subject +
                '}';
    }
}
