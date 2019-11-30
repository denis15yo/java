import java.util.Collection;
import java.util.function.Predicate;

public class MyUtil {
    public static <T> void printIf(Collection<T> c, Predicate<T> pred){
        for(T elem : c){
            if(pred.test(elem)){
                System.out.println(elem);
            }
        }
    }
}

