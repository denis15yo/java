package my_util;

import essenses.AgeBounds;
import essenses.Toy;

public class Parser {
    public static Toy parseToToy(String str){
        String[] strFields = str.split("\\s+");
        return new Toy(
                strFields[0],
                Integer.parseInt(strFields[1]),
                new AgeBounds(Integer.parseInt(strFields[2]), Integer.parseInt(strFields[3])));
    }
}
