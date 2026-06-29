package models.enums;

public enum WeatherType {
    Sunny(1, 1),
    Rainy(0.8, 0.1),
    Blizzard(0.6, 0.3),
    Sandstorm(0.3, 0.6),
    Fog(0.2, 0.7);

    private final double attack;
    private final double airAttack;

    WeatherType(double attackEffectPercentage, double airAttackEffectPercentage) {
        this.airAttack = airAttackEffectPercentage;
        this.attack = attackEffectPercentage;
    }

    public double getAirAttack() {
        return airAttack;
    }

    public double getAttack() {
        return attack;
    }
}
