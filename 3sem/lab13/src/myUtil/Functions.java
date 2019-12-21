package myUtil;

import essenses.Toy;

import java.util.*;
import java.util.stream.Collectors;

public class Functions {
    public static Map<String, IntPair> makeMap(List<Toy> list){
        Map<String, IntPair> res = new TreeMap<>();
        Set<String> set = list.stream().map(Toy::getName).collect(Collectors.toCollection(TreeSet::new));

        set.forEach(e -> {
            OptionalInt minOption = list.stream().filter(k -> k.getName().equals(e)).mapToInt(Toy::getCost).min();
            OptionalInt maxOption = list.stream().filter(k -> k.getName().equals(e)).mapToInt(Toy::getCost).max();
            if(minOption.isPresent() && maxOption.isPresent()){
                res.put(e, new IntPair(minOption.getAsInt(), maxOption.getAsInt()));
            }

        });

        return res;
    }
}
