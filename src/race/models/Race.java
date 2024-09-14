package race.models;

import race.services.car.CarPrintFull;
import race.services.car.CarPrintable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import static race.enums.RaceCarModels.getRandomRaceCarModel;
import static race.models.RaceCarRunnable.startRaceTime;

public class Race {

    private int numberOfCars;
    private int raceDistance;

    public  Race(){}

    public  Race(int numberOfCars,int raceDistance) throws InterruptedException {
        this.numberOfCars = numberOfCars;
        this.raceDistance = raceDistance;
    }
    public static void createRace(int numberOfCars,int raceDistance) throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(numberOfCars);

        List<RaceCarRunnable> cars = new ArrayList<>();

        Car raceCar = new Car(new CarPrintFull());

        for (int i = 0; i < numberOfCars; i++) {
            raceCar.setCarModel(getRandomRaceCarModel());
            cars.add(new RaceCarRunnable(raceCar.getCarModel(),raceDistance, latch));
        }

        List<Thread> threads = new ArrayList<>();
        for (RaceCarRunnable car : cars) {
            threads.add(new Thread(car));
        }

        startRaceTime.set(System.currentTimeMillis());
        startRace(threads);

        latch.await();
        System.out.println("All cars have finished the race!");

        RaceCarRunnable winner = cars.stream().min(Comparator.comparingLong(RaceCarRunnable::getFinishTime)).orElse(null);
        if (winner != null) {
            System.out.println("The Winner is " + winner.getCarModel() + " with time " + winner.getFinishTime() + " ms!");
            winner.print();

        }
    }

    public static void startRace(List<Thread> cars) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 3; i > 0; i--) {
                        System.out.println(i + "...");
                        Thread.sleep(500);
                    }
                    System.out.println("GO!!!");
                    for (Thread car : cars) {
                        car.start();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}

/*public class Race {
    public static void main(String[] args) throws InterruptedException {
        int numberOfCars = 3;
        int raceDistance = 500;
        CountDownLatch latch = new CountDownLatch(numberOfCars);

        List<RaceCarRunnable> cars = new ArrayList<>();
        cars.add(new RaceCarRunnable("Car1", 200, raceDistance, latch));
        cars.add(new RaceCarRunnable("Car2", 180, raceDistance, latch));
        cars.add(new RaceCarRunnable("Car3", 220, raceDistance, latch));

        List<Thread> threads = new ArrayList<>();
        for (RaceCarRunnable car : cars) {
            threads.add(new Thread(car));
        }

        startRace(threads);

        latch.await();
        System.out.println("All cars have finished the race!");
    }

    public static void startRace(List<Thread> cars) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 3; i > 0; i--) {
                        System.out.println(i + "...");
                        Thread.sleep(500);
                    }
                    System.out.println("GO!!!");
                    for (Thread car : cars) {
                        car.start();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}*/

