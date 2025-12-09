package race.models;

import race.enums.RaceCarModels;

public class Car {
    private String name;
    private int maxSpeed;
    private RaceCarModels carModel;

    public Car(String name, int maxSpeed) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Car name cannot be null or empty");
        }
        if (maxSpeed <= 0) {
            throw new IllegalArgumentException("Max speed must be positive");
        }
        this.name = name;
        this.maxSpeed = maxSpeed;
    }

    public Car(RaceCarModels carModel) {
        if (carModel == null) {
            throw new IllegalArgumentException("Car model cannot be null");
        }
        this.carModel = carModel;
        this.name = carModel.getModel();
        this.maxSpeed = carModel.getMaxSpeed();
    }

    public String getName() {
        return name;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public RaceCarModels getCarModel() {
        return carModel;
    }
}

