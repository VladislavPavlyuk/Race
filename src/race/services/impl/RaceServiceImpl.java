package race.services.impl;

import race.enums.RaceCarModels;
import race.interfaces.RaceResultPrinter;
import race.interfaces.RaceService;
import race.interfaces.Racer;
import race.interfaces.SpeedCalculator;
import race.models.Car;
import race.models.Race;
import race.models.RaceResult;
import race.services.RaceCarRunnable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

import static race.enums.RaceCarModels.getRandomRaceCarModel;

public class RaceServiceImpl implements RaceService {
    private final SpeedCalculator speedCalculator;
    private final RaceResultPrinter printer;
    private final RaceStarter raceStarter;

    public RaceServiceImpl(SpeedCalculator speedCalculator, RaceResultPrinter printer, RaceStarter raceStarter) {
        if (speedCalculator == null || printer == null || raceStarter == null) {
            throw new IllegalArgumentException("Dependencies cannot be null");
        }
        this.speedCalculator = speedCalculator;
        this.printer = printer;
        this.raceStarter = raceStarter;
    }

    @Override
    public RaceResult conductRace(Race race) throws InterruptedException {
        int numberOfCars = race.getNumberOfCars();
        int raceDistance = race.getRaceDistance();
        
        CountDownLatch latch = new CountDownLatch(numberOfCars);
        AtomicLong startRaceTime = new AtomicLong();
        List<Racer> racers = new ArrayList<>();

        for (int i = 0; i < numberOfCars; i++) {
            RaceCarModels carModel = getRandomRaceCarModel();
            Car car = new Car(carModel);
            racers.add(new RaceCarRunnable(car, raceDistance, latch, 
                                          speedCalculator, printer, startRaceTime));
        }

        List<Thread> threads = new ArrayList<>();
        for (Racer racer : racers) {
            threads.add(new Thread(racer));
        }

        startRaceTime.set(System.currentTimeMillis());
        raceStarter.startRace(threads);

        latch.await();
        printer.printRaceProgress("All cars have finished the race!");

        Racer winner = racers.stream()
            .filter(Racer::isFinished)
            .min(Comparator.comparingLong(Racer::getFinishTime))
            .orElse(null);

        RaceCarModels winnerModel = winner != null ? winner.getCarModel() : null;
        long winnerTime = winner != null ? winner.getFinishTime() : 0;

        RaceResult result = new RaceResult(winnerModel, winnerTime, numberOfCars);
        printer.printRaceResult(result);
        
        return result;
    }
}

