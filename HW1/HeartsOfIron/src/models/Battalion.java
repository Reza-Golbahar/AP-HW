package models;

import models.enums.BattalionType;

public class Battalion {
    private final String name;
    private final BattalionType type;
    private double basePower;
    private final double capturePercentage;
    private int level = 0;

    public Battalion(String name, BattalionType type, double power, double capturePercentage) {
        this.name = name;
        this.type = type;
        this.basePower = power;
        this.capturePercentage = capturePercentage;
    }

    public double getCapturePercentage() {
        return capturePercentage;
    }

    public double getBasePower() {
        return basePower;
    }

    public void addBasePower(double power) {
        this.basePower += power;
    }

    public void upgrade() {
        switch (level) {
            case 0 -> {
                level++;
                basePower += 5;
            }
            case 1 -> {
                level++;
                basePower += 7;
            }
            case 2 -> {
                level++;
                basePower += 10;
            }
        }
    }

    public Resources getUpgradeResources(Resources addingBattalionResources) {
        switch (level) {
            case 0 -> {
                return new Resources(Math.floor(addingBattalionResources.getManpower() * 0.5),
                        Math.floor(addingBattalionResources.getFuel() * 0.5),
                        Math.floor(addingBattalionResources.getSulfur() * 0.5),
                        Math.floor(addingBattalionResources.getSteel() * 0.5));
            }
            case 1 -> {
                return new Resources(addingBattalionResources.getManpower(),
                        addingBattalionResources.getFuel(),
                        addingBattalionResources.getSulfur(),
                        addingBattalionResources.getSteel());

            }
            case 2 -> {
                return new Resources(addingBattalionResources.getManpower() * 2.0,
                        addingBattalionResources.getFuel() * 2.0,
                        addingBattalionResources.getSulfur() * 2.0,
                        addingBattalionResources.getSteel() * 2.0);
            }
        }
        return null; //never happens
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public double calculateCaptureLoss() {
        return Math.floor(this.basePower * capturePercentage);
    }

    public String getName() {
        return name;
    }

    public BattalionType getType() {
        return type;
    }
}

