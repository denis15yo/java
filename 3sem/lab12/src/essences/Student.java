package essences;

import java.util.Objects;

public class Student extends Person {
    private String group;
    private int ball;

    public Student() {
        super();
    }

    public Student(String surname, int age, Country country, String group, int ball) {
        super(surname, age, country);
        this.group = group;
        this.ball = ball;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return ball == student.ball &&
                group.equals(student.group);
    }

    @Override
    public String toString() {
        return surname + " " + age + " yo from " + country + ", " +
                "student of " + group + " group with average ball = " + ball;
    }


}
