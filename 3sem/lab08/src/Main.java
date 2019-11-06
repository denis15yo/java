import Tree.Tree;

public class Main {
    //private static Tree<Integer> t = new Tree<Integer>();
    private static Tree<Student> t = new Tree<Student>();
    public static void main(String[] args) {
//        t.add(7);
//        t.add(4);
//        t.add(3);
//        t.add(1);
//        t.add(5);
//        t.add(6);
//        t.add(12);
//        t.add(11);
//        t.add(14);
//        t.add(13);
//        t.add(15);
//        t.add(16);
//
//        t.draw();

//        checkAdd(10);
//        checkRemove(7);
//        checkAdd(9);
//        checkRemove(14);

//        t.add(2);
//        t.add(1);
//        t.add(6);
//        t.add(5);
//        t.add(10);
//        t.add(7);
//        t.add(8);
//        t.add(9);
//        t.add(12);
//        t.add(11);
//        t.add(13);
//
//        t.draw();
//        t.remove(6);
//        t.draw();

        t.add(new Student(7, "Denis"));
        t.add(new Student(4, "Violetta"));
        t.add(new Student(3, "Katya"));
        t.add(new Student(1, "Gleb"));
        t.add(new Student(5, "Maxim"));
        t.add(new Student(6, "David"));
        t.add(new Student(12, "Ira"));
        t.add(new Student(11, "Nastya"));
        t.add(new Student(14, "Masha"));
        t.add(new Student(13, "Misha"));
        t.add(new Student(15, "Egor"));

        t.draw();

        System.out.println("Левый-Правый-Корень: " + t.goLeftRightRoot());
        System.out.println("Левый-Корень-Правый: " + t.goLeftRootRight());
        System.out.println("Корень-Левый-Правый: " + t.goRootLeftRight());

//        System.out.print("Найденные вершины: ");
//        for(int i = 0; i <= 17; ++i){
//            if(t.search(i)){
//                System.out.print(i + " ");
//            }
//        }
    }

//    private static void checkAdd(Integer value){
//        t.add(value);
//        System.out.println(value + " добавлено");
//        t.draw();
//    }
//    private static void checkRemove(Integer value){
//        t.remove(value);
//        System.out.println(value + " удалено");
//        t.draw();
//    }
}
