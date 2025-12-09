package race.interfaces;

import race.models.Race;
import race.models.RaceResult;

public interface RaceService {
    RaceResult conductRace(Race race) throws InterruptedException;
}

