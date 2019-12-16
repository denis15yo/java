package models;

import essenses.Worker;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class WorkersModel implements Iterable<Worker> {
    private List<Worker> list;

    public WorkersModel() {
        list = new ArrayList<>();
    }

    public void setList(List<Worker> list) {
        this.list = list;
    }

    public WorkersModel(List<Worker> list) {
        this.list = list;
    }

    public int size() {
        return list.size();
    }

    public Worker get(int index) {
        return list.get(index);
    }

    public boolean add(Worker worker) {
        return list.add(worker);
    }

    public void clear() {
        list.clear();
    }

    public Iterator<Worker> iterator() {
        return list.iterator();
    }

    public Stream<Worker> stream() {
        return list.stream();
    }

    public void forEach(Consumer<? super Worker> action) {
        list.forEach(action);
    }
}
