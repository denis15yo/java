import containers.AggregationContainer;
import containers.ExtendedContainer;
import essences.Person;
import essences.Student;
import myExceptions.EmtyContainerException;
import myExceptions.ParserException;
import myUtil.Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Demo {
    public static void main(String[] args) {
        ExtendedContainer<Person> container = new ExtendedContainer<>();
        try{
            Scanner sc = new Scanner(new File("input.txt"));
            while(sc.hasNextLine()){
                container.add(Parser.parseToStudent(sc.nextLine()));
            }
        }
        catch(ParserException | FileNotFoundException | NumberFormatException e){
            System.out.println("Некорректный входной файл. Программа заверешена");
            System.exit(0);
        }

        for (Person p : container) {
            System.out.println(p);
        }
        container.sort(Person::compareTo);
        System.out.println("After sort");
        for (Person p : container) {
            System.out.println(p);
        }
        try{
            Person minPerson = container.min();
            Person maxPerson = container.max();
            System.out.println("Min by surname");
            System.out.println(minPerson);
            System.out.println("Max by surname");
            System.out.println(maxPerson);
        }
        catch(EmtyContainerException e){
            System.out.println(e.getMessage());
        }
        Student tested = new Student("Шилович", 18, Person.Country.USA, "second", 10);
        System.out.println("Count of tested student:");
        System.out.println(container.countEqualsTo(tested));
    }
}
