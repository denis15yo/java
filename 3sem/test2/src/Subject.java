import java.util.Objects;
import java.util.function.Predicate;

public class Subject implements Comparable<Subject> {
    private String name;
    private int count;

    public String getName() {
        return name;
    }

    public Subject() {
    }

    public Subject(String name, int count) {
        this.name = name;
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Subject)) return false;
        Subject subject = (Subject) o;
        return count == subject.count &&
                Objects.equals(name, subject.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, count);
    }

    @Override
    public String toString() {
        return "Subject{" +
                "name='" + name + '\'' +
                ", count=" + count +
                '}';
    }

    @Override
    public int compareTo(Subject o) {
        if(count != o.count){
            return -(count - o.count);
        }
        return name.compareTo(o.name);
    }
}
