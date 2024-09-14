package race.services.car;

import race.models.Car;

public class CarPrintFull implements CarPrintable {
    @Override
    public void print(Car car) {
        System.out.println("Full information for Race car : ");
        System.out.println("\tModel : " + car.getCarModel().getModel() + "\n"
                        + "\tEngine : " + car.getCarModel().getEngine() + "\n"
                        + "\tTransmission : " + car.getCarModel().getTransmission() + "\n"
                        + "\tFuel : " + car.getCarModel().getFuel() + "\n"
                        + "\tPower : " + car.getCarModel().getPower() + "\n"
                        + "\tWeight : " + car.getCarModel().getWeight() + "\n"
                        + "\tTires : " + car.getCarModel().getTires() + "\n"
                        + "\tMaximum Speed : " + car.getCarModel().getMaxSpeed() + "n"
        );
    }
}
