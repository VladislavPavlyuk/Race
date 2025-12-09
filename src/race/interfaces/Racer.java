package race.interfaces;

import race.enums.RaceCarModels;

public interface Racer extends Runnable {
    RaceCarModels getCarModel();
    long getFinishTime();
    boolean isFinished();
}

