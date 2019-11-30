package containers;

import myExceptions.EmtyContainerException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.function.Predicate;

public class ExtendedContainer<T extends Comparable<T>> extends ArrayList<T> {
    public ExtendedContainer() {
        super();
    }
    public ExtendedContainer(int initialCapacity) {
        super(initialCapacity);
    }
    public ExtendedContainer(Collection<? extends T> c) {
        super(c);
    }

    public T min() throws EmtyContainerException {
        if(size() == 0) {
            throw new EmtyContainerException();
        }
        return Collections.min(this);
    }
    public T max() throws EmtyContainerException {
        if(size() == 0) {
            throw new EmtyContainerException();
        }
        return Collections.max(this);
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
}
