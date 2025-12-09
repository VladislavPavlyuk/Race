package race.models;

import race.enums.RaceCarModels;

public class RaceResult {
    private final RaceCarModels winner;
    private final long finishTime;
    private final int totalCars;

    public RaceResult(RaceCarModels winner, long finishTime, int totalCars) {
        this.winner = winner;
        this.finishTime = finishTime;
        this.totalCars = totalCars;
    }

    public RaceCarModels getWinner() {
        return winner;
    }

    public long getFinishTime() {
        return finishTime;
    }

    public int getTotalCars() {
        return totalCars;
    }
}

