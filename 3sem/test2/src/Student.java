public class Student {
    private String surname;
    private Faculty faculty;
    private int course;

    public String getSurname() {
        return surname;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public int getCourse() {
        return course;
    }

    public Student() {
    }

    public Student(String surname, Faculty faculty, int course) {
        this.surname = surname;
        this.faculty = faculty;
        this.course = course;
    }

    @Override
    public String toString() {
        return "Student{" +
                "surname='" + surname + '\'' +
                ", faculty=" + faculty +
                ", course=" + course +
                '}';
    }
}
