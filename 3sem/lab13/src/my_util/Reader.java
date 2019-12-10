package my_util;

import essenses.Toy;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class Reader {
    public static Vector<Toy> readVectorOfToys(File file) throws FileNotFoundException {
        Vector<Toy> v = new Vector<>();
        Scanner sc = new Scanner(file);
        while(sc.hasNextLine()){
            v.add(Parser.parseToToy(sc.nextLine()));
        }
        return v;
    }
}
