package Tree;

import java.util.Vector;

public class Tree<T extends Comparable<T>> {
    private static class Node<T extends Comparable<T>>{
        private T value;
        private Node<T> left, right;

        Node() {
        }

        Node(T value) {
            this.value = value;
        }
    }

    private Node<T> root;
    private Vector<Vector<String>> v = new Vector<>();

    public Tree() {
        root = null;
    }

    public void add(T value) {
        root = add(root, value);
    }
    private Node<T> add(Node<T> node, T value){
        if(node == null){
            node = new Node<>(value);
        }
        else if(value.compareTo(node.value) < 0){
            node.left = add(node.left, value);
        }
        else if(value.compareTo(node.value) >= 0){
            node.right = add(node.right, value);
        }

        return node;
    }

    public boolean search(T value){
        return (search(root, value) != null);
    }
    private Node<T> search(Node<T> node, T value){
        if(node == null){
            return null;
        }
        if(value.compareTo(node.value) < 0){
            return search(node.left, value);
        }
        else if(value.compareTo(node.value) > 0){
            return search(node.right, value);
        }
        else{
            return node;
        }
    }

    public void remove(T value){
        Node<T> current = root;
        Node<T> parent = null;

        while(current != null && !value.equals(current.value)){
            parent = current;
            if(value.compareTo(current.value) < 0){
                current = current.left;
            }
            else{
                current = current.right;
            }
        }

        if(current != null){
            if(current.left == null || current.right == null){
                Node<T> newChild;

                if(current.left != null){
                    newChild = current.left;
                }
                else if(current.right != null){
                    newChild = current.right;
                }
                else{
                    newChild = null;
                }

                if(parent == null){
                    root = newChild;
                }
                else{
                    if(parent.right == current){
                        parent.right = newChild;
                    }
                    else{
                        parent.left = newChild;
                    }
                }
            }
            else{
                Node<T> mostLeft = current.right;
                Node<T> mostLeftParent = current;

                while(mostLeft.left != null){
                    mostLeftParent = mostLeft;
                    mostLeft = mostLeft.left;
                }

                current.value = mostLeft.value;
                if(mostLeftParent.left == mostLeft){
                    mostLeftParent.left = mostLeft.right;
                }
                else{
                    mostLeftParent.right = mostLeft.right;
                }
            }
        }
    }

    public StringBuilder goRootLeftRight(){
        StringBuilder str = new StringBuilder();
        goRootLeftRight(root, str);
        return str;
    }
    private void goRootLeftRight(Node<T> node, StringBuilder str){
        if(node != null){
            str.append(node.value.toString());
            str.append(" ");
            goRootLeftRight(node.left, str);
            goRootLeftRight(node.right, str);
        }
    }

    public StringBuilder goLeftRightRoot(){
        StringBuilder str = new StringBuilder();
        goLeftRightRoot(root, str);
        return str;
    }
    private void goLeftRightRoot(Node<T> node, StringBuilder str){
        if(node != null){
            goLeftRightRoot(node.left, str);
            goLeftRightRoot(node.right, str);
            str.append(node.value.toString());
            str.append(" ");
        }
    }
    public StringBuilder goLeftRootRight(){
        StringBuilder str = new StringBuilder();
        goLeftRootRight(root, str);
        return str;
    }
    private void goLeftRootRight(Node<T> node, StringBuilder str){
        if(node != null){
            goLeftRootRight(node.left, str);
            str.append(node.value.toString());
            str.append(" ");
            goLeftRootRight(node.right, str);
        }
    }

    private int height(Node<T> node){
        int max = -1;

        if(node.left != null){
            max = Math.max(max, height(node.left));
        }
        if(node.right != null){
            max = Math.max(max, height(node.right));
        }

        return ++max;
    }

    private void addToVector(String str, int level){
        if(v.get(level) == null){
            v.set(level, new Vector<>());
        }
        v.get(level).add(str);
    }

    private void fillVectorOfLevels(Node<T> node, int level){
        if(node == root){
            addToVector(node.value.toString(), level);
        }

        if(level != height(root)){
            if(node.left != null){
                addToVector(node.left.value.toString(), level + 1);
                fillVectorOfLevels(node.left, level + 1);
            }
            else{
                addToVector("  ", level + 1);
                fillVectorOfLevels(new Node<>(), level + 1);
            }

            if(node.right != null){
                addToVector(node.right.value.toString(), level+ 1);
                fillVectorOfLevels(node.right, level + 1);
            }
            else{
                addToVector("  ", level + 1);
                fillVectorOfLevels(new Node<>(), level + 1);
            }
        }
    }

    public void draw(){
        int height = height(root);
        v.clear();
        v.setSize(height + 1);
        fillVectorOfLevels(root, 0);

        int countOfDelimiters = (1 << (height + 1));
        for(int i = 0; i <= height; ++i){
            for(int j = 0; j < countOfDelimiters/2 - 1; ++j){
                System.out.print("  ");
            }

            for(int j = 0; j < v.get(i).size(); ++j){
                System.out.print(v.get(i).get(j));
                for (int k = 0; k < countOfDelimiters - 1; ++k){
                    System.out.print("  ");
                }
            }
            countOfDelimiters /= 2;
            System.out.println();
        }
        System.out.println();
    }

}
