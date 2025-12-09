package race.services.impl;

import race.interfaces.RaceResultPrinter;

import java.util.List;

public class RaceStarter {
    private final RaceResultPrinter printer;
    private final int countdownSeconds;
    private final int countdownDelayMs;

    public RaceStarter(RaceResultPrinter printer) {
        this(printer, 3, 500);
    }

    public RaceStarter(RaceResultPrinter printer, int countdownSeconds, int countdownDelayMs) {
        this.printer = printer;
        this.countdownSeconds = countdownSeconds;
        this.countdownDelayMs = countdownDelayMs;
    }

    public void startRace(List<Thread> threads) throws InterruptedException {
        for (int i = countdownSeconds; i > 0; i--) {
            printer.printRaceStart(i);
            Thread.sleep(countdownDelayMs);
        }
        printer.printRaceStart(0);
        for (Thread thread : threads) {
            thread.start();
        }
    }
}

