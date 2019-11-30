import java.util.*;
import java.util.function.Predicate;

public class Demo {
    public static void main(String[] args) {
//        List<Subject> l = new ArrayList<>();
//        l.add(new Subject("Иванов", 10));
//        l.add(new Subject("Сидоров", 8));
//        l.add(new Subject("Петров", 5));
//
//        System.out.println(l);
//
//        Collections.sort(l);
//
//        System.out.println(l);
//
//        List<Subject> lCopy = new ArrayList<>(l);
//
//        lCopy.sort(Comparator.comparing(Subject::getName));
//
//        System.out.println(l);
//        System.out.println(lCopy);

    List<Student> l = new ArrayList<>();
    l.add(new Student("Shilovich", Faculty.FAMCS, 2));
    l.add(new Student("Kalinenko", Faculty.MMF, 2));
    l.add(new Student("Dorosko", Faculty.UNKNOWN, 1));

    Scanner sc = new Scanner(System.in);
    l.add(new Student(sc.next(), Faculty.valueOf(sc.next()), sc.nextInt()));
    System.out.println(l);

    MyUtil.printIf(l, student -> student.getFaculty() == Faculty.FAMCS);
    MyUtil.printIf(l, student -> student.getCourse() == 1);
    MyUtil.printIf(l, student -> true);

    Collections.min(l);
    }
}
