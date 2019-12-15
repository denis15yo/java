package my_containers;

import cars.AbstractCar;
import my_exceptions.IncorrectSizeOfContainerException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MyContainer<T extends AbstractCar> extends ArrayList<T> {
    public MyContainer(int initialCapacity) {
        super(initialCapacity);
    }
    public MyContainer() {
    }
    public MyContainer(Collection<? extends T> c) {
        super(c);
    }

    public void print(){
        System.out.println("Мой контейнер:");
        stream().sorted().forEachOrdered(AbstractCar::print);
    }
    public int frequency(T key){
        return Collections.frequency(this, key);
    }

    public ArrayList<T> threeMinimalElements() throws IncorrectSizeOfContainerException {
        if(isEmpty()){
            throw new IncorrectSizeOfContainerException("Контейнер пуст.");
        }
        if(size() < 3){
            throw new IncorrectSizeOfContainerException("В контейнере менее трёх элементов.");
        }

        List<T> copyList = new ArrayList<>(this);
        Collections.sort(copyList);
        ArrayList<T> res = new ArrayList<>();
        res.add(copyList.get(0));
        res.add(copyList.get(1));
        res.add(copyList.get(2));
        return res;
    }

    public int countByName(String name){
        return (int) stream().filter(e -> e.getName().equals(name)).count();
    }
}
