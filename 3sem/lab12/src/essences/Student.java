package essences;

import enums.Gender;

public class Student extends Person {
    private String group;
    private int ball;

    public Student() {
        super();
    }

    public Student(String surname, int age, Gender gender, String group, int ball) {
        super(surname, age, gender);
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
        return "Student{" +
                "surname='" + surname + '\'' +
                ", age=" + age +
                ", country=" + gender +
                ", group='" + group + '\'' +
                ", ball=" + ball +
                '}';
    }


//    @Override
//    public int compareTo(Person o) {
//        return super.compareTo(o);
//    }
}
