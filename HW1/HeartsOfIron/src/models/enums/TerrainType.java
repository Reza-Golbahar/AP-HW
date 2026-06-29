package models.enums;

public enum TerrainType {
    Mountain(0.5, 1, 10, 0),
    Plain(1.2, 1.25, 1, 1),
    Forest(0.9, 1, 5, 0),
    Urban(1.1, 1, 0.1, 0.2),
    Desert(1, 1.4, 1, 1);

    //the effects it has in percent
    private final double attack;
    private final double airAttack;
    private final double factoryCost;
    private final double fuelProduction;

    TerrainType(double attack, double airAttack, double factoryCost, double fuelProduction) {
        this.airAttack = airAttack;
        this.attack = attack;
        this.factoryCost = factoryCost;
        this.fuelProduction = fuelProduction;
    }

    public double getAirAttack() {
        return airAttack;
    }

    public double getFactoryCost() {
        return factoryCost;
    }

    public double getAttack() {
        return attack;
    }

    public double getFuelProduction() {
        return fuelProduction;
    }
}
