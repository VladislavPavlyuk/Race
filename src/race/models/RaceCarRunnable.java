package race.models;

import race.enums.RaceCarModels;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

public class RaceCarRunnable extends Car implements Runnable {
    private int passed;
    private int distance;
    private boolean isFinish;
    private CountDownLatch latch;
    private long finishTime;
    static AtomicLong startRaceTime = new AtomicLong();

    public RaceCarRunnable(RaceCarModels carModel, int distance, CountDownLatch latch) {
        super(carModel);
        this.distance = distance;
        this.latch = latch;
        this.passed = 0;
        this.isFinish = false;
    }

    private int getRandomSpeed() {
        Random random = new Random();
        return random.nextInt((getCarModel().getMaxSpeed() - getCarModel().getMaxSpeed() / 2) + 1) + getCarModel().getMaxSpeed() / 2;
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
            System.out.println(getCarModel() + " => speed: " + getRandomSpeed() + "; progress: " + passed + "/" + distance);
            if (passed >= distance) {
                isFinish = true;
                finishTime = System.currentTimeMillis() - startRaceTime.get();
                latch.countDown();
                System.out.println(getCarModel() + " FINISHED in " + finishTime + " ms!");
            }
        }
    }

    public long getFinishTime() {
        return finishTime;
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

