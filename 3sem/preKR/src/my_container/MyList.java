package my_container;

import trees.AbstractTree;
import trees.Kind;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class MyList<T extends AbstractTree> extends ArrayList<T> {
    public MyList(int initialCapacity) {
        super(initialCapacity);
    }
    public MyList() {
    }
    public MyList(Collection<? extends T> c) {
        super(c);
    }

    public void print(){
        stream().sorted(Comparator.comparingInt(AbstractTree::getCount)).forEach(AbstractTree::print);
    }
    public int frequency(T key){
        return Collections.frequency(this, key);
    }
    public T min(){
        return Collections.min(this, (o1, o2) -> {
            if(o1.getCount() != o2.getCount()){
                return o1.getCount() - o2.getCount();
            }
            return -(o1.getName().compareTo(o2.getName()));
        });
        //stream().min
    }
    public int sum(Kind kind){
        return stream().filter(e -> e.getKind() == kind).mapToInt(AbstractTree::getCount).sum();
    }
}
