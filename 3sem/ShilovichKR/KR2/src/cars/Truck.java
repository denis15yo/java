package cars;

import java.util.Objects;

public class Truck extends AbstractCar {
    private int carrying;
    private int numberOfWheels;

    public Truck(String name, FuelType fuelType, int fuelConsumption, int year, int carrying, int numberOfWheels) {
        super(name, fuelType, fuelConsumption, year);
        this.carrying = carrying;
        this.numberOfWheels = numberOfWheels;
    }

    public int getCarrying() {
        return carrying;
    }

    public int getNumberOfWheels() {
        return numberOfWheels;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Truck)) return false;
        if (!super.equals(o)) return false;
        Truck truck = (Truck) o;
        return carrying == truck.carrying &&
                numberOfWheels == truck.numberOfWheels;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), carrying, numberOfWheels);
    }

    @Override
    public String toString() {
        return "Truck{" +
                "name='" + name + '\'' +
                ", fuelType=" + fuelType +
                ", fuelConsumption=" + fuelConsumption +
                ", year=" + year +
                ", carrying=" + carrying +
                ", numberOfWheels=" + numberOfWheels +
                '}';
    }
}
