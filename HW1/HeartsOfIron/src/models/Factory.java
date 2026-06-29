package models;

import models.enums.FactoryType;

public class Factory {
    FactoryType type;
    private double producedCount = 0.0;
    private String name;

    public Factory(String name, FactoryType type) {
        this.name = name;
        this.type = type;
    }

    public FactoryType getType() {
        return type;
    }

    public void setType(FactoryType type) {
        this.type = type;
    }

    public double getProducedCount() {
        return producedCount;
    }

    public void setProducedCount(double producedCount) {
        this.producedCount = producedCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
