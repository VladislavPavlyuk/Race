package race.utils;

import race.interfaces.RaceInputReader;
import race.interfaces.RaceResultPrinter;
import race.interfaces.RaceService;
import race.interfaces.SpeedCalculator;
import race.models.Race;
import race.models.RaceResult;
import race.services.impl.ConsoleRaceInputReader;
import race.services.impl.ConsoleRaceResultPrinter;
import race.services.impl.RaceServiceImpl;
import race.services.impl.RaceStarter;
import race.services.impl.RandomSpeedCalculator;

import java.util.Scanner;

public class RaceFabric {
    private final RaceService raceService;
    private final RaceInputReader inputReader;

    public RaceFabric(RaceService raceService, RaceInputReader inputReader) {
        if (raceService == null || inputReader == null) {
            throw new IllegalArgumentException("Dependencies cannot be null");
        }
        this.raceService = raceService;
        this.inputReader = inputReader;
    }

    public static RaceFabric createDefault(Scanner scanner) {
        SpeedCalculator speedCalculator = new RandomSpeedCalculator();
        RaceResultPrinter printer = new ConsoleRaceResultPrinter();
        RaceStarter raceStarter = new RaceStarter(printer);
        RaceService raceService = new RaceServiceImpl(speedCalculator, printer, raceStarter);
        RaceInputReader inputReader = new ConsoleRaceInputReader(scanner);
        return new RaceFabric(raceService, inputReader);
    }

    public RaceResult runRace() throws InterruptedException {
        int numberOfCars = inputReader.readNumberOfCars();
        int raceDistance = inputReader.readRaceDistance();
        
        Race race = new Race(numberOfCars, raceDistance);
        return raceService.conductRace(race);
    }
}
