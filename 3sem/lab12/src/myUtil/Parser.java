package myUtil;

import essences.Person;
import essences.Student;
import essences.Teacher;
import myExceptions.ParserException;

public class Parser {
    public static Student parseToStudent(String str) throws ParserException, IllegalArgumentException {
        String[] fields = str.split("\\s+");
        if(fields.length != 5){
            throw new ParserException();
        }

        return new Student(fields[0], Integer.parseInt(fields[1]),
                Person.Country.valueOf(fields[2]), fields[3], Integer.parseInt(fields[4]));
    }
    public static Teacher parseToTeacher(String str) throws ParserException, NumberFormatException {
        String[] fields = str.split("\\s+");
        if(fields.length != 5){
            throw new ParserException();
        }

        return new Teacher(fields[0], Integer.parseInt(fields[1]),
                Person.Country.valueOf(fields[2]), Integer.parseInt(fields[3]), Teacher.Subject.valueOf(fields[4]));
    }
}
