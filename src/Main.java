import race.utils.RaceFabric;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        RaceFabric raceFabric = RaceFabric.createDefault(scanner);
        raceFabric.runRace();
        scanner.close();
    }
}