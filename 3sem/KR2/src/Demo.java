import cars.BodyType;
import cars.FuelType;
import cars.PassengerCar;
import cars.Truck;
import my_containers.MyContainer;
import my_exceptions.IncorrectSizeOfContainerException;
import my_exceptions.ParserException;
import my_util.Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Demo {
    public static void main(String[] args) {
        Scanner scPassengerCars;
        Scanner scTrucks;
        MyContainer<PassengerCar> passengerCars = new MyContainer<>();
        MyContainer<Truck> trucks = new MyContainer<>();
        try{
            scPassengerCars = new Scanner(new File("input1.txt"));
            scTrucks = new Scanner(new File("input2.txt"));
            while(scPassengerCars.hasNextLine()){
                passengerCars.add(Parser.parseToPassengerCar(scPassengerCars.nextLine()));
            }
            while(scTrucks.hasNextLine()){
                trucks.add(Parser.parseToTruck(scTrucks.nextLine()));
            }
        }
        catch(FileNotFoundException e){
            System.err.println("Файл не найден. Программа завершена");
            System.exit(0);
        }
        catch (ParserException | IllegalArgumentException e) {
            System.err.println("Некорректный входной файл. Программа заверешена.");
            System.exit(0);
        }

        passengerCars.print();
        System.out.println("\nКоличество определенных автомобилей: " + passengerCars.frequency(new PassengerCar(
                "BMW",
                FuelType.PETROL,
                12,
                2018,
                BodyType.SEDAN)));
        try {
            System.out.println("\nТри минимальных элемента:\n" + passengerCars.threeMinimalElements());
        } catch (IncorrectSizeOfContainerException e) {
            System.err.println(e.getMessage());
            System.err.println("Программа завершена");
            System.exit(0);
        }
        System.out.println("\nКоличество автомобилей с названием BMW: " + passengerCars.countByName("BMW"));

        System.out.println("\n");
        trucks.print();
        System.out.println("\nКоличество определенных автомобилей: " + trucks.frequency(new Truck(
                "BelAz",
                FuelType.PETROL,
                18,
                2010,
                4000,
                10
        )));
        try {
            System.out.println("\nТри минимальных элемента:\n" + trucks.threeMinimalElements());
        } catch (IncorrectSizeOfContainerException e) {
            System.err.println(e.getMessage());
            System.err.println("Программа завершена");
            System.exit(0);
        }
        System.out.println("\nКоличество автомобилей с названием MAZ: " + trucks.countByName("MAZ"));
    }
}

