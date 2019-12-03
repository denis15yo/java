package trees;

import java.util.Objects;

public class ForestTree extends AbstractTree {
    int wood;

    public ForestTree() {
    }
    public ForestTree(String name, int count, Kind kind, int wood) {
        super(name, count, kind);
        this.wood = wood;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ForestTree)) return false;
        if (!super.equals(o)) return false;
        ForestTree that = (ForestTree) o;
        return wood == that.wood;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), wood);
    }

    @Override
    public String toString() {
        return "ForestTree{" +
                "name='" + name + '\'' +
                ", count=" + count +
                ", kind=" + kind +
                ", wood=" + wood +
                '}';
    }

    @Override
    public void print() {
        System.out.println(toString());
    }
}
