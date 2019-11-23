package containers;

import myExceptions.EmtyContainerException;

import java.util.ArrayList;
import java.util.Collection;

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

        T minElem = get(0);
        for(T elem : this){
            if(elem.compareTo(minElem) < 0){
                minElem = elem;
            }
        }

        return minElem;
    }
    public T max() throws EmtyContainerException {
        if(size() == 0) {
            throw new EmtyContainerException();
        }

        T maxElem = get(0);
        for(T elem : this){
            if(elem.compareTo(maxElem) > 0){
                maxElem = elem;
            }
        }

        return maxElem;
    }

    public int countEqualsTo(T key){
        int count = 0;
        for(T elem : this){
            if(key.equals(elem)){
                ++count;
            }
        }
        return count;
    }
}
