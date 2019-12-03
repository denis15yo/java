package my_util;

import my_exceptions.ParserException;
import trees.ForestTree;
import trees.FruitTree;
import trees.Kind;

public class Parser {
    public static ForestTree parseToForestTree(String str) throws ParserException {
        String[] fields = str.split("\\s+");
        if(fields.length != 4){
            throw new ParserException();
        }
        return new ForestTree(fields[0], Integer.parseInt(fields[1]), Kind.valueOf(fields[2]), Integer.parseInt(fields[3]));
    }
    public static FruitTree parseToFruitTree(String str) throws ParserException {
        String[] fields = str.split("\\s+");
        if(fields.length != 4){
            throw new ParserException();
        }
        return new FruitTree(fields[0], Integer.parseInt(fields[1]), Kind.valueOf(fields[2]), Integer.parseInt(fields[3]));
    }
}
