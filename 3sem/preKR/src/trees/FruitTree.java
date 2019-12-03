package trees;

import java.util.Objects;

public class FruitTree extends AbstractTree {
    int harvest;

    public FruitTree() {
        super();
    }
    public FruitTree(String name, int count, Kind kind, int harvest) {
        super(name, count, kind);
        this.harvest = harvest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FruitTree)) return false;
        if (!super.equals(o)) return false;
        FruitTree fruitTree = (FruitTree) o;
        return harvest == fruitTree.harvest;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), harvest);
    }

    @Override
    public String toString() {
        return "FruitTree{" +
                "name='" + name + '\'' +
                ", count=" + count +
                ", kind=" + kind +
                ", harvest=" + harvest +
                '}';
    }

    @Override
    public void print() {
        System.out.println(toString());
    }
}