package race.services;

import race.interfaces.Racer;
import race.interfaces.SpeedCalculator;
import race.interfaces.RaceResultPrinter;
import race.models.Car;
import race.services.impl.RandomSpeedCalculator;
import race.services.impl.ConsoleRaceResultPrinter;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

public class RaceCarRunnable extends Car implements Racer {
    private int passed;
    private final int distance;
    private boolean isFinish;
    private CountDownLatch latch;
    private long finishTime;
    private AtomicLong startRaceTime;
    
    private final SpeedCalculator speedCalculator;
    private final RaceResultPrinter printer;

    public RaceCarRunnable(Car car, int distance) {
        super(car.getName(), car.getMaxSpeed());
        if (distance <= 0) {
            throw new IllegalArgumentException("Distance must be positive");
        }
        this.distance = distance;
        this.passed = 0;
        this.isFinish = false;
        this.speedCalculator = new RandomSpeedCalculator();
        this.printer = new ConsoleRaceResultPrinter();
    }

    public RaceCarRunnable(Car car, int distance, CountDownLatch latch) {
        super(car.getName(), car.getMaxSpeed());
        if (distance <= 0) {
            throw new IllegalArgumentException("Distance must be positive");
        }
        if (latch == null) {
            throw new IllegalArgumentException("Latch cannot be null");
        }
        this.distance = distance;
        this.latch = latch;
        this.passed = 0;
        this.isFinish = false;
        this.speedCalculator = new RandomSpeedCalculator();
        this.printer = new ConsoleRaceResultPrinter();
    }

    public RaceCarRunnable(Car car, int distance, CountDownLatch latch, AtomicLong startRaceTime) {
        super(car.getName(), car.getMaxSpeed());
        if (distance <= 0) {
            throw new IllegalArgumentException("Distance must be positive");
        }
        if (latch == null) {
            throw new IllegalArgumentException("Latch cannot be null");
        }
        if (startRaceTime == null) {
            throw new IllegalArgumentException("Start race time cannot be null");
        }
        this.distance = distance;
        this.latch = latch;
        this.startRaceTime = startRaceTime;
        this.passed = 0;
        this.isFinish = false;
        this.speedCalculator = new RandomSpeedCalculator();
        this.printer = new ConsoleRaceResultPrinter();
    }

    public RaceCarRunnable(Car car, int distance, CountDownLatch latch, 
                          SpeedCalculator speedCalculator, RaceResultPrinter printer, 
                          AtomicLong startRaceTime) {
        super(car.getName(), car.getMaxSpeed());
        if (distance <= 0) {
            throw new IllegalArgumentException("Distance must be positive");
        }
        if (latch == null) {
            throw new IllegalArgumentException("Latch cannot be null");
        }
        if (speedCalculator == null) {
            throw new IllegalArgumentException("Speed calculator cannot be null");
        }
        if (printer == null) {
            throw new IllegalArgumentException("Printer cannot be null");
        }
        if (startRaceTime == null) {
            throw new IllegalArgumentException("Start race time cannot be null");
        }
        this.distance = distance;
        this.latch = latch;
        this.speedCalculator = speedCalculator;
        this.printer = printer;
        this.startRaceTime = startRaceTime;
        this.passed = 0;
        this.isFinish = false;
    }

    public int getRandomSpeed() {
        if (speedCalculator != null && getCarModel() != null) {
            return speedCalculator.calculateSpeed(getCarModel());
        }
        // Fallback: use maxSpeed from Car directly (as per README requirements)
        int maxSpeed = getMaxSpeed();
        int minSpeed = maxSpeed / 2;
        return new java.util.Random().nextInt(maxSpeed - minSpeed + 1) + minSpeed;
    }

    @Override
    public race.enums.RaceCarModels getCarModel() {
        return super.getCarModel();
    }

    @Override
    public long getFinishTime() {
        return finishTime;
    }

    @Override
    public boolean isFinished() {
        return isFinish;
    }

    public int getPassed() {
        return passed;
    }

    public int getDistance() {
        return distance;
    }

    public boolean isFinish() {
        return isFinish;
    }

    @Override
    public void run() {
        while (!isFinish) {
            try {
                Thread.sleep(1000); // Пауза 1 секунда
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
            // Получаем скорость в км/ч из метода getRandomSpeed
            int speedKmh = getRandomSpeed();
            // Конвертируем км/ч в м/с: 1 км/ч = 1000 м / 3600 с = 1/3.6 м/с
            // За 1 секунду машина проедет: скорость_кмч / 3.6 метров
            double speedMs = speedKmh / 3.6;
            // Расчет пройденной дистанции в метрах за 1 секунду
            passed += (int) speedMs;
            printer.printRaceProgress(getName() + " => speed: " + speedKmh + "; progress: " + passed + "/" + distance);
            // Проверка: если машина прошла дистанцию >= длины трассы, устанавливаем флаг isFinish в true
            if (passed >= distance) {
                isFinish = true;
                if (startRaceTime != null) {
                    finishTime = System.currentTimeMillis() - startRaceTime.get();
                }
                if (latch != null) {
                    latch.countDown();
                }
                // Вывести финишировавшую машину по шаблону "carName FINISHED !"
                // Доработать вывод результатов гонки, добавить время заезда каждой машины к выводу результата
                if (finishTime > 0) {
                    printer.printRaceProgress(getName() + " FINISHED ! Time: " + finishTime + " ms");
                } else {
                    printer.printRaceProgress(getName() + " FINISHED !");
                }
            }
        }
    }

}

/*public class RaceCarRunnable extends Car implements Runnable {
    private int passed;
    private int distance;
    private boolean isFinish;
    private CountDownLatch latch;

    public RaceCarRunnable(String name, int maxSpeed, int distance, CountDownLatch latch) {
        super(name, maxSpeed);
        this.distance = distance;
        this.latch = latch;
        this.passed = 0;
        this.isFinish = false;
    }

    private int getRandomSpeed() {
        Random random = new Random();
        return random.nextInt((getMaxSpeed() - getMaxSpeed() / 2) + 1) + getMaxSpeed() / 2;
    }

    @Override
    public void run() {
        while (!isFinish) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            passed += getRandomSpeed();
            System.out.println(getName() + " => speed: " + getRandomSpeed() + "; progress: " + passed + "/" + distance);
            if (passed >= distance) {
                isFinish = true;
                latch.countDown();
                System.out.println(getName() + " FINISHED!");
            }
        }
    }
}*/

