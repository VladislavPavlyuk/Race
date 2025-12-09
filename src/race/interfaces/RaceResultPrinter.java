package race.interfaces;

import race.models.RaceResult;

public interface RaceResultPrinter {
    void printRaceResult(RaceResult result);
    void printRaceProgress(String message);
    void printRaceStart(int countdown);
}

