package cars;

import java.util.Objects;

public class PassengerCar extends AbstractCar {
    private BodyType bodyType;

    public PassengerCar(String name, FuelType fuelType, int fuelConsumption, int year, BodyType bodyType) {
        super(name, fuelType, fuelConsumption, year);
        this.bodyType = bodyType;
    }

    public BodyType getBodyType() {
        return bodyType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PassengerCar)) return false;
        if (!super.equals(o)) return false;
        PassengerCar that = (PassengerCar) o;
        return bodyType == that.bodyType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), bodyType);
    }

    @Override
    public String toString() {
        return "PassengerCar{" +
                "name='" + name + '\'' +
                ", fuelType=" + fuelType +
                ", fuelConsumption=" + fuelConsumption +
                ", year=" + year +
                ", bodyType=" + bodyType +
                '}';
    }
}
