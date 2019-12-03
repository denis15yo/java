import my_container.MyList;
import my_exceptions.ParserException;
import my_util.Parser;
import trees.ForestTree;
import trees.FruitTree;
import trees.Kind;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Demo {
    public static void main(String[] args) {
        Scanner scForestTree;
        Scanner scFruitTree;
        MyList<ForestTree> forestTrees = new MyList<>();
        MyList<FruitTree> fruitTrees = new MyList<>();
        try{
            scForestTree = new Scanner(new File("input1.txt"));
            scFruitTree = new Scanner(new File("input2.txt"));
            while(scForestTree.hasNextLine()){
                forestTrees.add(Parser.parseToForestTree(scForestTree.nextLine()));
            }
            while(scFruitTree.hasNextLine()){
                fruitTrees.add(Parser.parseToFruitTree(scFruitTree.nextLine()));
            }
        }
        catch (FileNotFoundException | ParserException e) {
            System.out.println("Некорректный входной файл. Программа заверешена.");
            System.exit(0);
        }

        forestTrees.print();
        System.out.println(forestTrees.frequency(new ForestTree("Сосна", 10, Kind.ХВОЙНОЕ, 8)));
        System.out.println(forestTrees.min());
        System.out.println(forestTrees.sum(Kind.ХВОЙНОЕ));

        fruitTrees.print();
        System.out.println(fruitTrees.frequency(new FruitTree("Сосна", 10, Kind.ХВОЙНОЕ, 9)));
        System.out.println(fruitTrees.min());
        System.out.println(fruitTrees.sum(Kind.ЛИСТВЕННОЕ));

    }
}
