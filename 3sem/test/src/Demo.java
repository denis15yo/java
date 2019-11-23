public class Demo {
    public static void main(String[] args) {
        Student[] array = new Student[10];
        for(int i = 0; i < array.length; ++i){
            array[i] = new Student("Denis", i);
        }
        System.out.println(MyUtil.calculateSum(array));
        try {
            System.out.println(MyUtil.calculateAverage(array));
        } catch (NoElementsException e) {
            System.out.println(e.getMessage());
        }
    }
}
