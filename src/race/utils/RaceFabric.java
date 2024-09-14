package race.utils;

import race.models.Race;

import java.util.Scanner;

public class RaceFabric {

    public static Race runRace() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите количество машин:");
        int numberOfCars = Integer.parseInt(scanner.nextLine());
        System.out.println("Введите дистанцию:");
        int raceDistance = Integer.parseInt(scanner.nextLine());

        Race race = new Race();
        race.createRace(numberOfCars,raceDistance);
        return race;
    }
}
