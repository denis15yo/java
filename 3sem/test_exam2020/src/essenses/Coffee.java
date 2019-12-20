package essenses;

public class Coffee extends Drink {
    private CoffeeType coffeeType;

    public Coffee() {
        super();
        coffeeType = CoffeeType.BEANS; //??????
    }

    public Coffee(String name, int caffeine, int cost, CoffeeType coffeeType) {
        super(name, caffeine, cost);
        this.coffeeType = coffeeType;
    }

    @Override
    public String toString() {
        return super.toString() + String.format("%-6s", coffeeType);
    }

    public void setCoffeeType(CoffeeType coffeeType) {
        this.coffeeType = coffeeType;
    }

    public CoffeeType getCoffeeType() {
        return coffeeType;
    }
}
