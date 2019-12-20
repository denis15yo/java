package models;

import essenses.*;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class DrinksModel implements Iterable<Drink> {
    private List<Drink> data;

    public DrinksModel() {
        data = new ArrayList<>();
    }

    public List<Drink> getData() {
        return data;
    }

    public void setData(List<Drink> data) {
        this.data = data;
    }

    public DrinksModel(List<Drink> data) {
        this.data = data;
    }

    public int size() {
        return data.size();
    }

    public Drink get(int index) {
        return data.get(index);
    }

    public boolean add(Drink Drink) {
        return data.add(Drink);
    }

    public void clear() {
        data.clear();
    }

    public Iterator<Drink> iterator() {
        return data.iterator();
    }

    public Stream<Drink> stream() {
        return data.stream();
    }

    public void forEach(Consumer<? super Drink> action) {
        data.forEach(action);
    }

    public Set<String> namesTreeSet(){ // TreeSet<String>
        Set<String> res = new TreeSet<>();
        forEach(e -> res.add(e.getName()));
        return res;
    }

    public Map<CoffeeType, Double> coffeeMap(){
        Map<CoffeeType, Double> res = new TreeMap<>();
        Map<CoffeeType, List<Double>> temp = new TreeMap<>();
        for(Drink d : data){
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

    public Map<TeaType, Double> teaMap(){
        Map<TeaType, Double> res = new TreeMap<>();
        Map<TeaType, List<Double>> temp = new TreeMap<>();
        for(Drink d : data){
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

