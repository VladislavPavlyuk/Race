package race.services.impl;

import race.enums.RaceCarModels;
import race.interfaces.SpeedCalculator;

import java.util.Random;

public class RandomSpeedCalculator implements SpeedCalculator {
    private final Random random;

    public RandomSpeedCalculator() {
        this.random = new Random();
    }

    public RandomSpeedCalculator(Random random) {
        this.random = random;
    }

    @Override
    public int calculateSpeed(RaceCarModels carModel) {
        int maxSpeed = carModel.getMaxSpeed();
        int minSpeed = maxSpeed / 2;
        return random.nextInt(maxSpeed - minSpeed + 1) + minSpeed;
    }
}

