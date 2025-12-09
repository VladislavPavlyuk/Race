package race.models;

import race.enums.RaceCarModels;
import race.services.RaceCarRunnable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

import static race.enums.RaceCarModels.getRandomRaceCarModel;

public class Race {
    // Публичное статическое поле время старта гонки типа AtomicLong
    public static AtomicLong startRaceTime = new AtomicLong();
    
    private final int numberOfCars;
    private final int raceDistance;

    public Race(int numberOfCars, int raceDistance) {
        if (numberOfCars <= 0) {
            throw new IllegalArgumentException("Number of cars must be positive");
        }
        if (raceDistance <= 0) {
            throw new IllegalArgumentException("Race distance must be positive");
        }
        this.numberOfCars = numberOfCars;
        this.raceDistance = raceDistance;
    }

    public int getNumberOfCars() {
        return numberOfCars;
    }

    public int getRaceDistance() {
        return raceDistance;
    }

    public static void startRace(List<Thread> cars) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Цикл отсчета до старта
                    for (int i = 3; i > 0; i--) {
                        System.out.println(i + "...");
                        Thread.sleep(500); // Интервал в 500мс
                    }
                    System.out.println("GO!!!");
                    // Проинициализировать startRaceTime значением текущего системного времени на момент старта всех потоков
                    startRaceTime.set(System.currentTimeMillis());
                    // Сразу же после "GO!!!" создать цикл по списку потоков и выполнить start() каждого потока
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

