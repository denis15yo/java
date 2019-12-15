package my_util;

import cars.BodyType;
import cars.FuelType;
import cars.PassengerCar;
import cars.Truck;
import my_exceptions.ParserException;

public class Parser {
    public static PassengerCar parseToPassengerCar(String str) throws ParserException, IllegalArgumentException {
        String[] strFields = str.split("\\s+");
        if(strFields.length != 5){
            throw new ParserException();
        }

        int fuelConsumption = Integer.parseInt(strFields[2]);
        int year = Integer.parseInt(strFields[3]);
        if(fuelConsumption < 0 || year < 0){
            throw new IllegalArgumentException();
        }

        return new PassengerCar(
                strFields[0],
                FuelType.valueOf(strFields[1]),
                fuelConsumption,
                year,
                BodyType.valueOf(strFields[4])
                );
    }

    public static Truck parseToTruck(String str) throws ParserException, IllegalArgumentException{
        String[] strFields = str.split("\\s+");
        if(strFields.length != 6){
            throw new ParserException();
        }

        int fuelConsumption = Integer.parseInt(strFields[2]);
        int year = Integer.parseInt(strFields[3]);
        int carrying = Integer.parseInt(strFields[4]);
        int numberOfWheels = Integer.parseInt(strFields[5]);

        if(fuelConsumption < 0 || year < 0 || carrying < 0 || numberOfWheels <= 0){
            throw new IllegalArgumentException();
        }

        return new Truck(
                strFields[0],
                FuelType.valueOf(strFields[1]),
                fuelConsumption,
                year,
                carrying,
                numberOfWheels
        );
    }
}
