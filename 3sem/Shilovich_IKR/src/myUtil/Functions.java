package myUtil;

import essenses.*;

import java.util.*;
import java.util.stream.Collectors;

public class Functions {
    public static Set<String> namesTreeSet(List<Drink> list){
        return list.stream().map(Drink::getName).collect(Collectors.toCollection(TreeSet::new));
    }

    public static Map<CoffeeType, Double> coffeeMap(List<Drink> data){
        Map<CoffeeType, Double> res = new TreeMap<>();
        Map<CoffeeType, List<Double>> temp = new TreeMap<>();
        for(Drink d : data){
            if(!(d instanceof Coffee)){
                continue;
            }
            Coffee coffee = (Coffee)d;
            CoffeeType coffeeType = coffee.getCoffeeType();
            if(temp.containsKey(coffeeType)){
                temp.get(coffeeType).add((double) coffee.getCaffeine());
            }
            else{
                List<Double> list = new ArrayList<>();
                list.add((double) coffee.getCaffeine());
                temp.put(coffeeType, list);
            }
        }
        temp.forEach((t, l) -> {
            OptionalDouble optionalAverage = l.stream().mapToDouble(Double::doubleValue).average();
            if(optionalAverage.isPresent()){
                res.put(t, optionalAverage.getAsDouble());
            }
        });
        return res;
    }

    public static Map<TeaType, Double> teaMap(List<Drink> data){
        Map<TeaType, Double> res = new TreeMap<>();
        Map<TeaType, List<Double>> temp = new TreeMap<>();
        for(Drink d : data){
            if(!(d instanceof Tea)){
                continue;
            }
            Tea tea = (Tea)d;
            TeaType teaType = tea.getTeaType();
            //noinspection DuplicatedCode
            if(temp.containsKey(teaType)){
                temp.get(teaType).add((double) tea.getCaffeine());
            }
            else{
                List<Double> list = new ArrayList<>();
                list.add((double) tea.getCaffeine());
                temp.put(teaType, list);
            }
        }
        temp.forEach((t, l) -> {
            OptionalDouble optionalAverage = l.stream().mapToDouble(Double::doubleValue).average();
            if(optionalAverage.isPresent()){
                res.put(t, optionalAverage.getAsDouble());
            }
        });
        return res;
    }
}
