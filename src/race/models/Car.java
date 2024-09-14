package race.models;

import race.enums.RaceCarModels;
import race.interfaces.ICar;
import race.services.car.CarPrintable;

public class Car implements ICar {

    private RaceCarModels carModel;


    public Car(RaceCarModels carModel) {
        this.carModel = carModel;
    }
    public Car(){}

    public void setCarModel(RaceCarModels carModel) {
        this.carModel = carModel;
    }

    public RaceCarModels getCarModel() {
        return carModel;
    }

    private CarPrintable carPrintable;

    public Car(CarPrintable carPrintable) {
        this.carPrintable = carPrintable;
    }

    public void setCarPrintable(CarPrintable carPrintable) {
        this.carPrintable = carPrintable;
    }

    @Override
    public void print() {
        carPrintable.print(this);
    }
}

