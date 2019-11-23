public class MyUtil {
    public static <T extends Number> int calculateSum(T[] array){
        int sum = 0;
        for(T elem : array){
            sum += elem.intValue();
        }
        return sum;
    }

    public static <T extends Number> double calculateAverage(T[] array) throws NoElementsException {
        if(array.length == 0){
            throw new NoElementsException("No elements");
        }
        return (double)calculateSum(array)/array.length;
    }
}
