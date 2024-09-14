package race.services.car;

import race.models.Car;

public class CarPrintBrief implements CarPrintable {
    @Override
    public void print(Car car) {
        System.out.println("Brief information for Race car :");
        System.out.println(car.getCarModel());
    }
}
