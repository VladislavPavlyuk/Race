package race.services.impl;

import race.interfaces.RaceResultPrinter;
import race.models.RaceResult;

public class ConsoleRaceResultPrinter implements RaceResultPrinter {
    @Override
    public void printRaceResult(RaceResult result) {
        if (result.getWinner() != null) {
            System.out.println("The Winner is " + result.getWinner().getModel() + 
                " with time " + result.getFinishTime() + " ms!");
            System.out.println("Maximum speed : " + result.getWinner().getMaxSpeed() + " km/h" + "\n" +
                "Engine : " + result.getWinner().getEngine() + "\n" +
                "Transmission : " + result.getWinner().getTransmission() + "\n" +
                "Power : " + result.getWinner().getPower() + "\n" +
                "Fuel : " + result.getWinner().getFuel() + "\n" +
                "Tires : " + result.getWinner().getTires());
        }
    }

    @Override
    public void printRaceProgress(String message) {
        System.out.println(message);
    }

    @Override
    public void printRaceStart(int countdown) {
        if (countdown > 0) {
            System.out.println(countdown + "...");
        } else {
            System.out.println("GO!!!");
        }
    }
}

