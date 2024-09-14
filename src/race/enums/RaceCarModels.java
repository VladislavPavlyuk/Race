package race.enums;

import java.util.Random;

public enum RaceCarModels {
    ChevroletCamaro ("Chevrolet Camaro ZL1",
            " 5.86 L (358 cu in) V8 Naturally-aspirated",
            "5 forward speeds + 1 reverse Sequential",
            "manual transmission",
            " 3,200 lb (1,451 kg) minimum without driver and fuel",
            "Sunoco Green E15 98 octane race fuel",
            "Goodyear12",
            307),

    FordMustang(
            "Ford Mustang",
            "5.86 L (358 cu in) V8 Naturally-aspirated",
            "5 forward speeds + 1 reverse Sequential",
            " manual transmission",
            "3,200 lb (1,451 kg) minimum without driver and fuel",
            "Sunoco Green E15 98 octane race fuel",
            "Goodyear12",
            267),
    ToyotaTRDCamry(
            "Toyota TRD Camry",
            "5.86 L (358 cu in) V8 Naturally-aspirated",
            "5 forward speeds + 1 reverse Sequential",
            "manual transmission",
            "3,200 lb (1,451 kg) minimum without driver and fuel",
            "Sunoco Green E15 98 octane race fuel",
            "Goodyear12",
            270),

    MercedesAMG(
            "Mercedes-AMG F1 W13 E Performance",
            "1.6 L V6 turbo hybrid",
            "1000+ hp",
            "8-speed semi-automatic",
            "798 kg (1,759 lb)",
            "Petronas Primax",
            "Pirelli3",
            350),
    RedBullRacing(
            "Red Bull Racing RB18",
            "1.6 L V6 turbo hybrid",
            "1000+ hp",
            "8-speed semi-automatic",
            "798 kg (1,759 lb)",
            "ExxonMobil Synergy",
            "Pirelli3",
            330);

    String model;
    String engine;
    String power;
    String transmission;
    String weight;
    String fuel;
    String tires;
    int maxSpeed;

    RaceCarModels(String model,String engine,String power,String transmission, String weight,String fuel,String tires,int maxSpeed) {
        this.model = model;
        this.engine = engine;
        this.power = power;
        this.transmission = transmission;
        this.weight = weight;
        this.fuel = fuel;
        this.tires = tires;
        this.maxSpeed = maxSpeed;
    }
    public String getModel() {
        return model;
    }
    public String getEngine() {
        return engine;
    }
    public String getPower() {
        return power;
    }
    public String getTransmission() {
        return transmission;
    }
    public String getWeight() {
        return weight;
    }
    public String getFuel() {
        return fuel;
    }
    public String getTires() {
        return tires;
    }
    public int getMaxSpeed() {
        return maxSpeed;
    }
    private static final Random RANDOM = new Random();

    public static RaceCarModels getRandomRaceCarModel() {
        RaceCarModels[] randomRaceCarModels = values();
        return randomRaceCarModels[RANDOM.nextInt(randomRaceCarModels.length)];
    }
}
