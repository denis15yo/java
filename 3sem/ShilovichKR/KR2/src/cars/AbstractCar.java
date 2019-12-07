package cars;

import java.util.Objects;

public abstract class AbstractCar implements Comparable<AbstractCar> {
    protected String name;
    protected FuelType fuelType;
    protected  int fuelConsumption;
    protected int year;

    public String getName() {
        return name;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public int getFuelConsumption() {
        return fuelConsumption;
    }

    public int getYear() {
        return year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractCar)) return false;
        AbstractCar that = (AbstractCar) o;
        return fuelConsumption == that.fuelConsumption &&
                year == that.year &&
                Objects.equals(name, that.name) &&
                fuelType == that.fuelType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, fuelType, fuelConsumption, year);
    }

    public AbstractCar(String name, FuelType fuelType, int fuelConsumption, int year) {
        this.name = name;
        this.fuelType = fuelType;
        this.fuelConsumption = fuelConsumption;
        this.year = year;
    }

    public void print(){
        System.out.println(toString());
    }

    @Override
    public int compareTo(AbstractCar o) {
        if(getYear() != o.getYear()){
            return o.getYear() - getYear();
        }
        return getFuelConsumption() - o.getFuelConsumption();
    }
}
