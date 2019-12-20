package essenses;

public class Drink {
    private String name;
    private int caffeine;
    private int cost;

    public Drink(String name, int caffeine, int cost) {
        this.name = name;
        this.caffeine = caffeine;
        this.cost = cost;
    }

    public Drink() {
        name = "";
        caffeine = 0;
        cost = 0;
    }

    @Override
    public String toString() {
        return String.format("%-15s%-10d%-10d", name, caffeine, cost);
    }

    public String getName() {
        return name;
    }

    public int getCaffeine() {
        return caffeine;
    }

    public int getCost() {
        return cost;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCaffeine(int caffeine) {
        this.caffeine = caffeine;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
