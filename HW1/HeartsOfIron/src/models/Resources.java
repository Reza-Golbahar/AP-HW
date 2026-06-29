package models;

public class Resources {
    private double manpower;
    private double fuel;
    private double sulfur;
    private double steel;

    public Resources(double manpower, double fuel, double sulfur, double steel) {
        this.manpower = manpower;
        this.fuel = fuel;
        this.sulfur = sulfur;
        this.steel = steel;
    }

    //like cloning
    public Resources(Resources resources) {
        this.manpower = resources.getManpower();
        this.fuel = resources.getFuel();
        this.sulfur = resources.getSulfur();
        this.steel = resources.getSteel();
    }

    public void addResources(Resources resources) {
        this.manpower += resources.getManpower();
        this.steel += resources.getSteel();
        this.fuel += resources.getFuel();
        this.sulfur += resources.getSulfur();
    }

    public void useResources(Resources resources) {
        this.manpower -= resources.getManpower();
        this.fuel -= resources.getFuel();
        this.sulfur -= resources.getSulfur();
        this.steel -= resources.getSteel();
    }

    public double getManpower() {
        return manpower;
    }

    public double getFuel() {
        return fuel;
    }

    public double getSulfur() {
        return sulfur;
    }

    public double getSteel() {
        return steel;
    }
}

