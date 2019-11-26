package containers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;

import myExceptions.EmtyContainerException;

public class AggregationContainer<T extends Comparable<T>> implements Iterable<T>{
    private List<T> list;

    public AggregationContainer() {
        list = new ArrayList<>();
    }
    public AggregationContainer(List<T> list) {
        this.list = list;
    }

    public int size(){
        return list.size();
    }
    public T get(int index){
        return list.get(index);
    }

    public boolean add(T elem){
        return list.add(elem);
    }

    public T min() throws EmtyContainerException {
        if(list.size() == 0) {
            throw new EmtyContainerException();
        }

        T minElem = list.get(0);
        for(T elem : list){
            if(elem.compareTo(minElem) < 0){
                minElem = elem;
            }
        }

        return minElem;
    }
    public T max() throws EmtyContainerException {
        if(list.size() == 0) {
            throw new EmtyContainerException();
        }

        T maxElem = list.get(0);
        for(T elem : list){
            if(elem.compareTo(maxElem) > 0){
                maxElem = elem;
            }
        }

        return maxElem;
    }

    public int countIf(Predicate<T> pred){
        int count = 0;
        for(T elem : this){
            if(pred.test(elem)){
                ++count;
            }
        }
        return count;
    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        list.forEach(action);
    }

    @Override
    public Spliterator<T> spliterator() {
        return list.spliterator();
    }
}
