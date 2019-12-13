package my_util;

import essenses.Toy;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Reader {
    public static List<Toy> readListOfToys(File file) throws FileNotFoundException, NumberFormatException {
        List<Toy> l = new ArrayList<>();
        Scanner sc = new Scanner(file);
        while(sc.hasNextLine()){
            l.add(Parser.parseToToy(sc.nextLine()));
        }
        return l;
    }
}
