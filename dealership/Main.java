import java.util.Scanner;

import models.Car;
import models.Dealership;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        
        Car[] cars = new Car[] {
            new Car("Nissan", 5000),
            new Car("Dodge", 12000),
        };
        cars[1].setMake("Sudan");
        cars[1].setPrice(8500);

        Dealership dealership = new Dealership(cars);

        System.out.println("\n************* JAVA DEALERSHIP *************");
        while (true) {
            System.out.println(dealership);
                System.out.print("Enter the spot number of the car you want to buy: ");
                if(scan.hasNextInt()) {
                    int spot = scan.nextInt();  
                    if(spot >= cars.length) {
                        System.out.println("Please choose a valid parking spot");
                        continue;
                    }
                    dealership.sell(spot); 
                } else {
                    scan.next();
                    System.out.println("INVALID INPUT");
                }scan.close();
        }
    }
}
