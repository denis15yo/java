package models;

import essenses.Toy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class ToysModel implements Iterable<Toy> {
    private List<Toy> data;

    public ToysModel() {
        data = new ArrayList<>();
    }

    public ToysModel(List<Toy> data) {
        this.data = data;
    }

    public Toy get(int index) {
        return data.get(index);
    }

    public void add(Toy toy) {
        data.add(toy);
    }

    public int getSize(){
        return data.size();
    }
    
    public void clear() {
        data.clear();
    }
    
    public Iterator<Toy> iterator() {
        return data.iterator();
    }

    public List<Toy> getData() {
        return data;
    }

    public void setData(List<Toy> data) {
        this.data = data;
    }

    public Stream<Toy> stream(){
        return data.stream();
    }
}
