package race.services.impl;

import race.interfaces.RaceInputReader;

import java.util.Scanner;

public class ConsoleRaceInputReader implements RaceInputReader {
    private final Scanner scanner;

    public ConsoleRaceInputReader(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public int readNumberOfCars() {
        System.out.println("Введите количество машин:");
        return Integer.parseInt(scanner.nextLine());
    }

    @Override
    public int readRaceDistance() {
        System.out.println("Введите дистанцию:");
        return Integer.parseInt(scanner.nextLine());
    }
}

