import containers.AggregationContainer;
import containers.ExtendedContainer;
import essences.Person;
import essences.Student;
import myExceptions.EmtyContainerException;
import myExceptions.ParserException;
import myUtil.Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Scanner;

public class Demo {
    public static void main(String[] args) {
        ExtendedContainer<Person> container = new ExtendedContainer<>();
        try{
            Scanner scStudents = new Scanner(new File("students.txt"));
            Scanner scTeachers = new Scanner(new File("teachers.txt"));
            while(scStudents.hasNextLine()){
                container.add(Parser.parseToStudent(scStudents.nextLine()));
            }
            while(scTeachers.hasNextLine()){
                container.add(Parser.parseToTeacher(scTeachers.nextLine()));
            }

        }
        catch(ParserException | FileNotFoundException | IllegalArgumentException e){
            System.out.println("Некорректный входной файл. Программа заверешена");
            System.exit(0);
        }

        for (Person p : container) {
            System.out.println(p);
        }
        //container.sort(Person::compareTo);
        //Collections.sort(container);

//        System.out.println("After sort");
//        for (Person p : container) {
//            System.out.println(p);
//        }
        try{
            Person minPerson = container.min();
            Person maxPerson = container.max();
            System.out.print("Min by surname: ");
            System.out.println(minPerson);
            System.out.print("Max by surname: ");
            System.out.println(maxPerson);
        }
        catch(EmtyContainerException e){
            System.out.println(e.getMessage());
        }
        Student tested = new Student("Шилович", 18, Person.Country.USA, "second", 10);
        System.out.print("Count of tested student: ");
        //System.out.println(container.countIf(e -> e.getAge() > 20));
        System.out.print(container.countIf(tested::equals));
        //System.out.println(Collections.frequency(container, tested));
    }
}
