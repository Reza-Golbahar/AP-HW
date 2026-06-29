package models.enums;

public enum FactoryType {
    SteelFactory("steel factory", 10000, 0, 10, 30000),
    SulfurFactory("sulfur factory", 20000, 0, 20, 20000),
    FuelRefinery("fuel refinery", 50000, 5000, 50, 100000);

    private final String displayName;
    private final int manPowerCost;
    private final int steelCost;
    private final int productionPerManPower;
    private final int maximumProduction;
    private final String resourceType;

    FactoryType(String displayName, int manPowerCost, int steelCost, int productionPerManPower, int maximumProduction) {
        this.displayName = displayName;
        this.manPowerCost = manPowerCost;
        this.steelCost = steelCost;
        this.maximumProduction = maximumProduction;
        this.productionPerManPower = productionPerManPower;

        if (this.name().equals("SteelFactory")) {
            resourceType = "steel";
        } else if (this.name().equals("SulfurFactory")) {
            resourceType = "sulfur";
        } else {
            resourceType = "fuel";
        }
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getManPowerCost() {
        return manPowerCost;
    }

    public int getMaximumProduction() {
        return maximumProduction;
    }

    public int getProductionPerManPower() {
        return productionPerManPower;
    }

    public int getSteelCost() {
        return steelCost;
    }

    public String getResourceType() {
        return resourceType;
    }
}
