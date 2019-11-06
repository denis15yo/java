public class Student implements Comparable<Student> {
    private int id;
    private String name;

    public Student() {
        id = 0;
    }
    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return Integer.toString(id);
    }

    @Override
    public int compareTo(Student o) {
        //return (id - o.id);
        return name.compareTo(o.name);
    }
}
