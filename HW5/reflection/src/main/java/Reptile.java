
public abstract class Reptile extends Animal {
    private boolean isDangerous;
    private boolean hasScales;

    public Reptile(String name, String DNA, String bodyColor, double averageWeight, int averageLifespan, boolean isDangerous, boolean hasScales) {
        super(name, DNA, Habitat.LAND, bodyColor, false, averageWeight, averageLifespan);
        this.isDangerous = isDangerous;
        this.hasScales = hasScales;
    }

    @Override
    public int yearlyReproductionYield(){
        return 90;
    }
}
