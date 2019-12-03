package trees;

import java.util.Objects;

public abstract class AbstractTree{
    private String name;
    private int count;
    private Kind kind;

    public AbstractTree() {
    }

    public AbstractTree(String name, int count, Kind kind) {
        this.name = name;
        this.count = count;
        this.kind = kind;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public Kind getKind() {
        return kind;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractTree)) return false;
        AbstractTree that = (AbstractTree) o;
        return count == that.count &&
                Objects.equals(name, that.name) &&
                kind == that.kind;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, count, kind);
    }

    public abstract void print();
}
