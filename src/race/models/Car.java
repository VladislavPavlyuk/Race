package race.models;

import race.enums.RaceCarModels;

public class Car {

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

}

