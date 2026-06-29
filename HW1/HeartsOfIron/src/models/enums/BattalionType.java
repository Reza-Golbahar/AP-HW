package models.enums;

import models.Resources;

public enum BattalionType {
    Infantry(new Resources(15000, 0, 10000, 10000)),
    Panzer(new Resources(5000, 10000, 10000, 20000)),
    AirForce(new Resources(1000, 50000, 10000, 35000)),
    Navy(new Resources(5000, 30000, 10000, 50000));

    private final Resources resourcesNeeded;

    BattalionType(Resources resourcesNeeded) {
        this.resourcesNeeded = resourcesNeeded;
    }

    public Resources getResourcesNeeded() {
        return resourcesNeeded;
    }

    public int getInitialPower(CountryType countryType) {
        switch (this.name()) {
            case "Infantry" -> {
                if (countryType.equals(CountryType.Germany)) {
                    return 15;
                } else if (countryType.equals(CountryType.SovietUnion)) {
                    return 20;
                } else if (countryType.equals(CountryType.USA)) {
                    return 5;
                } else if (countryType.equals(CountryType.UK)) {
                    return 10;
                } else if (countryType.equals(CountryType.Japan)) {
                    return 10;
                }
            }
            case "Navy" -> {
                if (countryType.equals(CountryType.Germany)) {
                    return 5;
                } else if (countryType.equals(CountryType.SovietUnion)) {
                    return 10;
                } else if (countryType.equals(CountryType.USA)) {
                    return 15;
                } else if (countryType.equals(CountryType.UK)) {
                    return 20;
                } else if (countryType.equals(CountryType.Japan)) {
                    return 10;
                }
            }
            case "Panzer" -> {
                if (countryType.equals(CountryType.Germany)) {
                    return 20;
                } else if (countryType.equals(CountryType.SovietUnion)) {
                    return 15;
                } else if (countryType.equals(CountryType.USA)) {
                    return 10;
                } else if (countryType.equals(CountryType.UK)) {
                    return 5;
                } else if (countryType.equals(CountryType.Japan)) {
                    return 10;
                }
            }
            case "AirForce" -> {
                if (countryType.equals(CountryType.Germany)) {
                    return 10;
                } else if (countryType.equals(CountryType.SovietUnion)) {
                    return 5;
                } else if (countryType.equals(CountryType.USA)) {
                    return 20;
                } else if (countryType.equals(CountryType.UK)) {
                    return 15;
                } else if (countryType.equals(CountryType.Japan)) {
                    return 10;
                }
            }
        }
        return 0; //never happens
    }


    public double getBaseCapturePercentage(CountryType countryType) {
        switch (this.name()) {
            case "Infantry" -> {
                if (countryType.equals(CountryType.Germany)) {
                    return 0.5;
                } else if (countryType.equals(CountryType.SovietUnion)) {
                    return 0.2;
                } else if (countryType.equals(CountryType.USA)) {
                    return 0.3;
                } else if (countryType.equals(CountryType.UK)) {
                    return 0.6;
                } else if (countryType.equals(CountryType.Japan)) {
                    return 0.4;
                }
            }
            case "Navy" -> {
                if (countryType.equals(CountryType.Germany)) {
                    return 0.2;
                } else if (countryType.equals(CountryType.SovietUnion)) {
                    return 0.3;
                } else if (countryType.equals(CountryType.USA)) {
                    return 0.6;
                } else if (countryType.equals(CountryType.UK)) {
                    return 0.4;
                } else if (countryType.equals(CountryType.Japan)) {
                    return 0.5;
                }
            }
            case "Panzer" -> {
                if (countryType.equals(CountryType.Germany)) {
                    return 0.3;
                } else if (countryType.equals(CountryType.SovietUnion)) {
                    return 0.6;
                } else if (countryType.equals(CountryType.USA)) {
                    return 0.4;
                } else if (countryType.equals(CountryType.UK)) {
                    return 0.5;
                } else if (countryType.equals(CountryType.Japan)) {
                    return 0.2;
                }
            }
            case "AirForce" -> {
                if (countryType.equals(CountryType.Germany)) {
                    return 0.4;
                } else if (countryType.equals(CountryType.SovietUnion)) {
                    return 0.4;
                } else if (countryType.equals(CountryType.USA)) {
                    return 0.5;
                } else if (countryType.equals(CountryType.UK)) {
                    return 0.2;
                } else if (countryType.equals(CountryType.Japan)) {
                    return 0.3;
                }
            }
        }
        return 0; //never happens
    }
}
