package essenses;

public class Toy {
    String name;
    int cost;
    AgeBounds ageBounds;

    public Toy(String name, int cost, AgeBounds ageBounds) {
        this.name = name;
        this.cost = cost;
        this.ageBounds = ageBounds;
    }

    public Toy() {
        name = "";
        cost = 0;
        ageBounds = new AgeBounds();
    }

    @Override
    public String toString() {
        return name + cost + ageBounds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public AgeBounds getAgeBounds() {
        return ageBounds;
    }

    public void setAgeBounds(AgeBounds ageBounds) {
        this.ageBounds = ageBounds;
    }
}
